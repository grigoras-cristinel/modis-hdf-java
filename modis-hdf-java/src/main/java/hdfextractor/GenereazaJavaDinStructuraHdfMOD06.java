package hdfextractor;

import hdfextractor.utils.SimpleStringLineBuilder;

import java.io.File;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.tree.DefaultMutableTreeNode;

import ncsa.hdf.hdflib.HDFException;
import ncsa.hdf.object.Attribute;
import ncsa.hdf.object.FileFormat;
import ncsa.hdf.object.HObject;
import ncsa.hdf.object.h4.H4File;
import ncsa.hdf.object.h4.H4Group;
import ncsa.hdf.object.h4.H4SDS;
import ncsa.hdf.object.h4.H4Vdata;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.math.NumberUtils;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.jboss.forge.roaster.model.source.PropertySource;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import ro.grig.sat.util.StatieMeteoTO;
import ro.grig.sat.util.Util;

public class GenereazaJavaDinStructuraHdfMOD06 {
   /**
	 *
	 */
   TreeSet<ObiectDesc> gasite = new TreeSet<GenereazaJavaDinStructuraHdfMOD06.ObiectDesc>(
         new Comparator<GenereazaJavaDinStructuraHdfMOD06.ObiectDesc>() {
            @Override
            public int compare(ObiectDesc o1, ObiectDesc o2) {
               return new CompareToBuilder().append(o1.numeCamp.toLowerCase(), o2.numeCamp.toLowerCase())
                     .toComparison();
            }
         });
   TreeMap<String, Integer> dimensiuni = new TreeMap<String, Integer>();

   public class DimDesc {
      public String dimName;
      public Integer dimValue;
   }

   public class ObiectDesc implements Comparable<ObiectDesc> {
      String numeCamp;
      String tip;
      String longName;
      String parameterType;
      String unitum;
      double scale;
      double offset;
      int cellSize;// 1 sau 5

      @Override
      public String toString() {
         return "ObiectDesc [numeCamp=" + numeCamp + ", tip=" + tip + ", dimensiune=" + dimensiune + "]";
      }

      int dimSize;
      String dimensiune;
      public long crosdim;
      public String propertyType;

      int getDimSize() {
         if (dimensiune == null) {
            return dimSize;
         }
         return StringUtils.countMatches(dimensiune, ",") + 1;
      }

      @Override
      public int compareTo(ObiectDesc o) {
         return this.numeCamp.compareTo(o.numeCamp);
      }

      public String javaNumeCamp() {
         String fnup = numeCamp.toLowerCase();
         fnup = StringUtils.replace(fnup, "_", " ");
         fnup = org.apache.commons.lang3.text.WordUtils.capitalizeFully(fnup);
         fnup = StringUtils.remove(fnup, " ");
         fnup = WordUtils.uncapitalize(fnup);
         return fnup;
      }
   }

   /**
    * @param args
    */
   public static void main(String[] args) {
      System.out.println("//Start");
      GenereazaJavaDinStructuraHdfMOD06 clasamea = new GenereazaJavaDinStructuraHdfMOD06();
      clasamea.runGenerator();
      System.out.println("//Stop");
   }

   private void runGenerator() {
      try {
         // findFieldsFromDescription();
         findFieldsFromHdfFile();
         buildJavaClass(gasite);
         // staticFieldsNames(gasite);
         // staticWriteNames2(gasite);
         // staticPropsNames2(gasite);
         // staticFieldsAccesors2(gasite);
         // staticParserFields2(gasite);
         // csvHeader(gasite);
         // readFromFileByPosition(gasite);
         // staticToGenerator(gasite);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   private void buildJavaClass(TreeSet<ObiectDesc> gasite) throws Exception {
      JavaClassSource hdfconstants = Roaster.create(JavaClassSource.class);
      hdfconstants.setPackage("hdfextractor").setName("Mod06C6Constants");
      hdfconstants.addInterface(Serializable.class);
      ArrayList<String> fn = new ArrayList<String>();
      for (ObiectDesc field : gasite) {
         String fnup = field.numeCamp.toUpperCase();
         hdfconstants.addField().setName(fnup).setType(String.class).setFinal(true).setStatic(true)
               .setStringInitializer(field.numeCamp).setPublic().getJavaDoc()
               .setText("Field:" + field.longName + " Tip:" + field.tip + " Dim:" + field.dimSize);
         hdfconstants.addField().setName("P_" + fnup).setType(String.class).setFinal(true).setStatic(true)
               .setStringInitializer(field.javaNumeCamp()).setPublic().getJavaDoc()
               .setText("Property name in TO " + field.javaNumeCamp());
         fn.add(fnup);
      }
      FieldSource<JavaClassSource> fieldNames = hdfconstants.addField().setName("fieldNames").setType(String[].class)
            .setFinal(true).setStatic(true);
      fieldNames.setPublic().getJavaDoc().setText("Lista campuri in acest tip");
      fieldNames.setLiteralInitializer("new String[]{" + StringUtils.join(fn, ",") + "}");
      // System.out.println(hdfconstants.toString());
      PrintWriter pr = new PrintWriter("src/main/java/hdfextractor/" + hdfconstants.getName() + ".java");
      pr.write(hdfconstants.toString());
      pr.flush();
      pr.close();
      /*
       *
       */
      JavaClassSource modto = Roaster.create(JavaClassSource.class);
      buildJavaClassMODTO(gasite, modto);
      PrintWriter pr1 = new PrintWriter("src/main/java/hdfextractor/" + modto.getName() + ".java");
      pr1.write(modto.toString());
      pr1.flush();
      pr1.close();
      // System.out.println(modto.toString());
      JavaClassSource hdfworker = Roaster.create(JavaClassSource.class);
      String workerClassName = "H4FileWorkerC6Mod06";
      hdfworker.setPackage("hdfextractor").setName(workerClassName);
      hdfworker.addInterface(Serializable.class);
      hdfworker.addImport(H4DSWrapper.class);
      hdfworker.addImport(Serializable.class);
      hdfworker.addImport(Util.class);
      hdfworker.addImport(ArrayList.class);
      hdfworker.addImport(Iterator.class);
      hdfworker.addImport(Map.Entry.class);
      hdfworker.addImport(TreeMap.class);
      hdfworker.addImport(H4Group.class);
      hdfworker.addImport(HObject.class);
      hdfworker.addImport(Attribute.class);
      hdfworker.addImport(FileFormat.class);
      hdfworker.addImport(H4Vdata.class);
      hdfworker.addImport(DateTime.class);
      hdfworker.addImport(DateTimeZone.class);
      hdfworker.addImport(StatieMeteoTO.class);
      hdfworker.addImport(DefaultMutableTreeNode.class);
      PropertySource<JavaClassSource> sheeturi = hdfworker.addProperty("TreeMap<String,H4DSWrapper>", "sheeturi");
      sheeturi.getField().setLiteralInitializer("new TreeMap<String,H4DSWrapper>()");
      PropertySource<JavaClassSource> valori = hdfworker.addProperty("TreeMap<String," + modto.getName() + ">",
            "valori");
      valori.getField().setLiteralInitializer("new TreeMap<String," + modto.getName() + ">()");
      PropertySource<JavaClassSource> punct = hdfworker.addProperty("TreeMap<String,double[]>", "puncte");
      PropertySource<JavaClassSource> file = hdfworker.addProperty(H4File.class, "file");
      MethodSource<JavaClassSource> methodHasPostion = hdfworker.addMethod();
      methodHasPostion.setName("hasPosition");
      methodHasPostion.setReturnType(Boolean.TYPE);
      methodHasPostion.addParameter(String.class, "nume");
      methodHasPostion.addThrows(Exception.class);
      methodHasPostion.setBody("if (puncte == null || !puncte.containsKey(nume)) {" + "         return false;"
            + "      }" + "      if (valori.get(nume) != null) {"
            + "         if (valori.get(nume).getPozitie() != null) {" + "            return true;" + "         }"
            + "         return false;" + "      }" + "      return false;");
      MethodSource<JavaClassSource> cns1 = hdfworker.addMethod().setName(hdfworker.getName());
      cns1.setConstructor(true).addParameter(H4File.class, "h4file");
      cns1.addThrows(Exception.class);
      cns1.setBody("super();" + "      this.file = h4file;" + "      file.open();");
      MethodSource<JavaClassSource> cns2 = hdfworker.addMethod().setName(hdfworker.getName());
      cns2.setConstructor(true).addParameter(String.class, "h4fileLocation");
      cns2.setBody("file = new H4File(h4fileLocation, FileFormat.READ);" + "      file.open();");
      cns2.addThrows(Exception.class);
      MethodSource<JavaClassSource> cns3 = hdfworker.addMethod().setName(hdfworker.getName()).setPublic();
      cns3.setConstructor(true).addParameter(String.class, "canonicalPath");
      cns3.setConstructor(true).addParameter("TreeMap<String, double[]>", "statii");
      cns3.setBody("this(canonicalPath);" + "      puncte = statii;" + "      parseFile();");
      cns3.addThrows(Exception.class);
      MethodSource<JavaClassSource> methodFclose = hdfworker.addMethod().setName("close").setPublic();
      methodFclose
            .setBody("try {\r\n" + "         file.close();\r\n" + "      } catch (Exception e) {\r\n" + "      }");
      MethodSource<JavaClassSource> methodEFS = hdfworker.addMethod().setName("extractFieldSheet");
      methodEFS.addThrows(Exception.class);
      methodEFS.setStatic(true);
      methodEFS.addParameter(H4SDS.class, "h4sds");
      methodEFS.addParameter(String.class, "field1");
      methodEFS.addThrows(HDFException.class);
      methodEFS.setReturnType(H4DSWrapper.class);
      StringBuilder bodyEFS = new StringBuilder();
      bodyEFS.append("H4DSWrapper wrap = new H4DSWrapper(field1, h4sds);\r\n"
            + "      for (int f1 = 0; f1 < h4sds.getMetadata().size(); f1++) {\r\n"
            + "         Attribute attr = (Attribute) h4sds.getMetadata().get(f1);\r\n"
            + "         if (attr.getName().equals(\"_FillValue\")) {\r\n"
            + "            if (attr.getValue() instanceof double[]) {\r\n"
            + "               double[] vals = (double[]) attr.getValue();\r\n"
            + "               wrap.setFillValue(new Double(vals[0]));\r\n"
            + "            } else if (attr.getValue() instanceof short[]) {\r\n"
            + "               short[] vals = (short[]) attr.getValue();\r\n"
            + "               wrap.setFillValue(new Double(vals[0]));\r\n"
            + "            } else if (attr.getValue() instanceof float[]) {\r\n"
            + "               float[] vals = (float[]) attr.getValue();\r\n"
            + "               wrap.setFillValue(new Double(vals[0]));\r\n"
            + "            } else if (attr.getValue() instanceof byte[]) {\r\n"
            + "               byte[] vals = (byte[]) attr.getValue();\r\n"
            + "               wrap.setFillValue(new Double(vals[0]));\r\n" + "            } else {\r\n"
            + "               System.out.println(\"Fill value este gresit. necunoscut:\" + attr.getValue());\r\n"
            + "            }\r\n" + "         }\r\n" + "         if (attr.getName().equals(\"scale_factor\")) {\r\n"
            + "            double[] vals = (double[]) attr.getValue();\r\n" + "            wrap.setScale(vals[0]);\r\n"
            + "         }\r\n" + "         if (attr.getName().equals(\"add_offset\")) {\r\n"
            + "            double[] vals = (double[]) attr.getValue();\r\n"
            + "            wrap.setOffset(vals[0]);\r\n" + "         }\r\n" + "      }\r\n" + "      return wrap;");
      methodEFS.setBody(bodyEFS.toString());
      MethodSource<JavaClassSource> methodParse = hdfworker.addMethod().setName("parseFile");
      methodParse.addThrows(Exception.class);
      methodParse.addThrows("OutOfMemoryError");
      methodParse.setPublic();
      StringBuilder body = new StringBuilder();
      body.append("System.out.println(\"parseFile()\");\r\n"
            + "TreeMap<String, Integer> dims = Util.dimensionFieldsFromDescription(file);\r\n"
            + "      int cellAcross = dims.get(\"Cell_Across_Swath_5km\");\r\n"
            + "      int cellAllong = dims.get(\"Cell_Along_Swath_5km\");\r\n"
            + "      int cellAcross1km = dims.get(\"Cell_Across_Swath_1km\");\r\n"
            + "      int cellAlong1km = dims.get(\"Cell_Along_Swath_1km\");      H4Group testGroup = (H4Group) file.get(\"/"
            + "mod06" + "/Geolocation Fields\");\r\n"
            + "      Iterator<HObject> it = testGroup.getMemberList().iterator();\r\n"
            + "      while (it.hasNext()) {         HObject hObject = it.next();\r\n"
            + "         if (hObject instanceof H4SDS) {            H4SDS h4sds = (H4SDS) hObject;\r\n"
            + "            if (\"Latitude\".equals(h4sds.getName())) {\r\n"
            + "               sheeturi.put(\"Latitudine\", new H4DSWrapper(\"Latitudine\", h4sds));\r\n"
            + "            }            if (\"Longitude\".equals(h4sds.getName())) {\r\n"
            + "               sheeturi.put(\"Longitudine\", new H4DSWrapper(\"Longitudine\", h4sds));\r\n"
            + "            }         }      }\r\n" + "      H4Group dataFieldsGrup = (H4Group) file.get(\"/" + "mod06"
            + "/Data Fields\");\r\n"
            + "      Iterator<HObject> itDataFields = dataFieldsGrup.getMemberList().iterator();\r\n"
            + "      while (itDataFields.hasNext()) {         HObject hObject = itDataFields.next();\r\n"
            + "         if (hObject instanceof H4SDS) {            H4SDS h4sds = (H4SDS) hObject;\r\n"
            + "            for (String field : "
            + hdfconstants.getName()
            + ".fieldNames) {\r\n"
            + "               if (field.equals(h4sds.getName())) {\r\n"
            + "                  H4DSWrapper wrap = extractFieldSheet(h4sds, field);\r\n"
            + "                  sheeturi.put(field, wrap);\r\n"
            + "                  break;\r\n"
            + "               }\r\n"
            + "            }\r\n"
            + "         }\r\n"
            + "      }\r\n"
            + "      ArrayList<"
            + modto.getName()
            + "> pozitiiGasite = new ArrayList<"
            + modto.getName()
            + ">();\r\n"
            + "float[] lats = (float[]) sheeturi.get(\"Latitudine\").getH4data().getData();\r\n"
            + "      float[] longs = (float[]) sheeturi.get(\"Longitudine\").getH4data().getData();\r\n"
            + "      float[][] blats1km = Util.buildLats1km(lats, longs, cellAcross, cellAcross1km, cellAlong1km);\r\n"
            + "      for (Entry<String, double[]> oPozitie : puncte.entrySet()) {\r\n"
            + "         String numePozitie = oPozitie.getKey();\r\n"
            + "         double coord[] = oPozitie.getValue();\r\n"
            + "         if (coord.length != 2) {\r\n"
            + "            throw new IllegalArgumentException(\"Am primit array de coordonate punct de dimensiune gresita\");\r\n"
            + "         }\r\n"
            + "         Util.UnPoit findPoint5km = Util.extractNearPoint(lats, longs, coord, 0.05f);\r\n"
            + "         Util.UnPoit findPoint1km = Util.extractNearPoint(blats1km[0], blats1km[1], coord, 0.05f);\r\n"
            + "         if (findPoint5km.gasit) {\r\n"
            + "            long yPoz = findPoint5km.pozitie / cellAcross;\r\n"
            + "            long xPoz = findPoint5km.pozitie % cellAcross;\r\n"
            + "            "
            + modto.getName()
            + " valoare = new "
            + modto.getName()
            + "();\r\n"
            + "            valori.put(numePozitie, valoare);\r\n"
            + "            valoare.setLatitudine(findPoint5km.latidu);\r\n"
            + "            valoare.setLongitudine(findPoint5km.longit);\r\n"
            + "            valoare.setPozitieAcross(xPoz);\r\n"
            + "            valoare.setPozitieAlong(yPoz);\r\n"
            + "            valoare.setPozitie(findPoint5km.pozitie);\r\n"
            + "            valoare.setPozitie1km(findPoint1km.pozitie);\r\n"
            + "            valoare.setStatie(numePozitie);\r\n"
            + "            valoare.setNumePozitie(numePozitie);\r\n"
            + "            valoare.setHdfFileName(file.getName());\r\n"
            + "            pozitiiGasite.add(valoare);\r\n"
            + "         }\r\n" + "      }");
      body.append("for (" + modto.getName() + " valoare : pozitiiGasite) {\r\n"
            + "         long pozSearch = valoare.getPozitie();long pozSearch1km = valoare.getPozitie1km();"
            + "         DateTime value = null;\r\n" + "         double[] vals = (double[]) sheeturi.get("
            + hdfconstants.getName() + ".SCAN_START_TIME).getH4data().getData();\r\n"
            + "         DateTime retval = new DateTime(DateTimeZone.UTC);\r\n"
            + "         retval = retval.withDate(1993, 1, 1).dayOfYear().roundFloorCopy();\r\n"
            + "         value = retval.plusSeconds((int) vals[(int) pozSearch]);\r\n"
            + "         valoare.setTimp(value);\r\n" + "         // Start\r\n");
      for (ObiectDesc field : gasite) {
         System.out.println("pe la fields");
         String fnup = field.javaNumeCamp();
         String fnuac = WordUtils.capitalize(fnup);
         String constN = field.numeCamp.toUpperCase();
         StringBuilder unfs = new StringBuilder();
         unfs.append("{\r\n");
         unfs.append("/* cellSize km " + field.cellSize + " tipData:" + field.propertyType + "*/\r\n");
         unfs.append("H4DSWrapper wrap = sheeturi.get(");
         unfs.append(hdfconstants.getName());
         unfs.append("." + constN);
         unfs.append(");\r\n");
         unfs.append("if (wrap != null) {");
         if (field.dimSize == 2) {
            if (field.cellSize == 5) {
               unfs.append("valoare.set");
               unfs.append(fnuac);
               if ("Double".equals(field.propertyType)) {
                  unfs.append("(wrap.findDoublePozitionData(pozSearch));");
               }
               if ("Long".equals(field.propertyType)) {
                  unfs.append("(wrap.findShortPozitionData(pozSearch));");
               }
               if ("Byte".equals(field.propertyType)) {
                  unfs.append("(wrap.findBytePozitionData(pozSearch));");
               }
            }
            if (field.cellSize == 1) {
               unfs.append("valoare.set");
               unfs.append(fnuac);
               if ("Double".equals(field.propertyType)) {
                  unfs.append("(wrap.findDoublePozitionData(pozSearch1km));");
               }
               if ("Long".equals(field.propertyType)) {
                  unfs.append("(wrap.findShortPozitionData(pozSearch1km));");
               }
               if ("Byte".equals(field.propertyType)) {
                  unfs.append("(wrap.findBytePozitionData(pozSearch1km));");
               }
            }
         }
         if (field.dimSize == 3) {
         }
         unfs.append("}\r\n");
         unfs.append("}\r\n");
         body.append(unfs);
      }
      body.append("// Stop" + "\r\n}");
      methodParse.setBody(body.toString());
      PrintWriter pr2 = new PrintWriter("src/main/java/hdfextractor/" + hdfworker.getName() + ".java");
      pr2.write(hdfworker.toString());
      pr2.flush();
      pr2.close();
      System.out.println(hdfworker.toString());
   }

   private void buildJavaClassMODTO(TreeSet<ObiectDesc> gasite, JavaClassSource modto) {
      modto.setPackage("hdfextractor").setName("Mod06C06TO");
      modto.addImport(DateTimeZone.class);
      modto.addImport(SimpleStringLineBuilder.class);
      modto.addInterface(Serializable.class);
      modto.addProperty(DateTime.class, "timp").getField().setLiteralInitializer("null");
      modto.addProperty(String.class, "hdfFileName").getField().setStringInitializer("");
      modto.addProperty(String.class, "numePozitie").getField().setStringInitializer("");
      modto.addProperty(Double.TYPE, "latitudine");
      modto.addProperty(Double.TYPE, "longitudine");
      modto.addProperty(String.class, "statie");
      modto.addProperty(EnumCloudType.class, "cloudType");
      modto.addProperty(Long.class, "pozitie").getField().getJavaDoc().setText("Pozitia corespunzatoare pt 5 km");
      modto.addProperty(Long.class, "pozitieAcross").getField().getJavaDoc()
            .setText("Pozitia corespunzatoare pt 5 km perpendicular pe directie");
      modto.addProperty(Long.class, "pozitieAlong").getField().getJavaDoc()
            .setText("Pozitia corespunzatoare pt 5 km pe directie");
      modto.addProperty(Long.class, "pozitie1km").getField().getJavaDoc().setText("Pozitia corespunzatoare pt 1 km");
      modto.addProperty(Double.class, "deltaPozitieKm").getField().getJavaDoc()
            .setText("Distanta dintre punctul pe grila de 5km si cel pe grila de 1km");
      ArrayList<String> fhead = new ArrayList<String>();
      for (ObiectDesc field : gasite) {
         String fnup = field.javaNumeCamp();
         if (field.dimSize == 2) {
            PropertySource<JavaClassSource> prop = modto.addProperty(field.propertyType, fnup);
            prop.getMutator().getJavaDoc()
                  .setText("Field:" + field.longName + " Tip:" + field.tip + " Dim:" + field.dimSize);
            prop.getAccessor().getJavaDoc()
                  .setText("Field:" + field.longName + " Tip:" + field.tip + " Dim:" + field.dimSize);
         }
         if (field.dimSize == 3) {
            PropertySource<JavaClassSource> prop = modto.addProperty(field.propertyType + "[]", fnup);
            prop.getField().setLiteralInitializer("new " + field.propertyType + "[" + field.crosdim + "]");
            prop.getMutator().getJavaDoc()
                  .setText("Field:" + field.longName + " Tip:" + field.tip + " Dim:" + field.dimSize);
            prop.getAccessor().getJavaDoc()
                  .setText("Field:" + field.longName + " Tip:" + field.tip + " Dim:" + field.dimSize);
            fhead.add(fnup);
            for (int i = 0; i < field.crosdim; i++) {
               System.out.println(field.numeCamp + " Cros dim value " + i);
               String lfnup = fnup + i;
               PropertySource<JavaClassSource> propInArray = modto.addProperty(field.propertyType, lfnup);
               propInArray.getMutator().getJavaDoc()
                     .setText("Field:" + field.longName + " Tip:" + field.tip + " Dim:" + field.dimSize);
               propInArray.getAccessor().getJavaDoc()
                     .setText("Field:" + field.longName + " Tip:" + field.tip + " Dim:" + field.dimSize);
               fhead.add(lfnup);
            }
         }
      }
      MethodSource<JavaClassSource> tlline = modto.addMethod().setName("toLongLine");
      tlline.setReturnType(String.class).setPublic();
      StringBuilder tllbody = new StringBuilder();
      tllbody.append("if (cloudTopPressure != null && cloudOpticalThickness != null) {\r\n"
            + "         cloudType = EnumCloudType.selectType(cloudTopPressure,\r\n"
            + "               cloudOpticalThickness);\r\n" + "      }\r\n"
            + "      SimpleStringLineBuilder builder = new SimpleStringLineBuilder();\r\n"
            + "      DateTime timp2 = timp.withZone(DateTimeZone.UTC);\r\n");
      tllbody.append("builder.append(timp2);\r\n");
      tllbody.append("builder.append(pozitie);\r\n");
      tllbody.append("builder.append(pozitie1km);\r\n");
      for (ObiectDesc field : gasite) {
         String fnup = field.javaNumeCamp();
         if (field.dimSize == 2) {
            tllbody.append("builder.append(" + fnup + ");\r\n");
         }
      }
      tllbody.append("builder.append(numePozitie);\r\n");
      tllbody.append("builder.append(cloudType);\r\n");
      tllbody.append("builder.append(hdfFileName);\r\n return builder.toString();\r\n");
      tlline.setBody(tllbody.toString());
      MethodSource<JavaClassSource> metexp = modto.addMethod().setName("extractParameters");
      metexp.setPublic();
      metexp.setReturnType(String.class);
      metexp.addParameter("String[]", "parameters");
      StringBuilder metexpbody = new StringBuilder();
      metexpbody.append("if (cloudTopPressure != null && cloudOpticalThickness != null) {\r\n"
            + "         cloudType = EnumCloudType.selectType(cloudTopPressure, cloudOpticalThickness);\r\n"
            + "      }\r\n" + "      SimpleStringLineBuilder builder = new SimpleStringLineBuilder();\r\n"
            + "      DateTime timp2 = timp.withZone(DateTimeZone.UTC);\r\n"// split
            + "      builder.append(timp2);\r\n"// split
            + "      builder.append(pozitie);\r\n" // split
            + "      builder.append(pozitie1km);\r\n" // split
            + "      for (int i = 0; i < parameters.length; i++) {\r\n");
      metexpbody.append(" if (\"cloudType\".equals(parameters[i])) {\r\n"
            + "            builder.append(cloudType);\r\n" + "         }\r\n");
      for (ObiectDesc field : gasite) {
         metexpbody.append(" if (\"" + field.javaNumeCamp() + "\".equals(parameters[i])) {\r\n"
               + "            builder.append(" + field.javaNumeCamp() + ");\r\n" + "         }\r\n");
      }
      metexpbody.append("      }\r\n");
      metexpbody.append("      builder.append(numePozitie);\r\n"// split
            + "      builder.append(hdfFileName);\r\n"// split
            + "      return builder.toString();");
      System.out.println(metexpbody.toString());
      metexp.setBody(metexpbody.toString());
      MethodSource<JavaClassSource> extractHeader = modto.addMethod().setName("extractHeader");
      extractHeader.setReturnType(String.class).setPublic();
      extractHeader.setPublic();
      extractHeader.setReturnType(String.class);
      extractHeader.addParameter("String[]", "parameters");
      StringBuilder tlhxbody = new StringBuilder();
      tlhxbody.append("      SimpleStringLineBuilder builder = new SimpleStringLineBuilder();\r\n");
      tlhxbody.append("builder.append(\"timp2\");\r\n");
      tlhxbody.append("builder.append(\"pozitie\");\r\n" // split
            + "builder.append(\"pozitie1km\");\r\n" // split
            + "      for (int i = 0; i < parameters.length; i++) {\r\n");
      tlhxbody.append(" if (\"cloudType\".equals(parameters[i])) {\r\n"
            + "            builder.append(\"cloudType\");\r\n" + "         }\r\n");
      for (ObiectDesc field : gasite) {
         String fnup = field.javaNumeCamp();
         tlhxbody.append(" if (\"" + field.javaNumeCamp() + "\".equals(parameters[i])) {\r\n" + "builder.append(\""
               + fnup + "\");\r\n" + "         }\r\n");
      }
      tlhxbody.append("      }\r\n");
      tlhxbody.append("builder.append(\"numePozitie\");\r\n");
      tlhxbody.append("builder.append(\"hdfFileName\");\r\n return builder.toString();\r\n");
      extractHeader.setStatic(true);
      extractHeader.setBody(tlhxbody.toString());
      MethodSource<JavaClassSource> tllheader = modto.addMethod().setName("toLongHeader");
      tllheader.setReturnType(String.class).setPublic().setStatic(true);
      StringBuilder tlhbody = new StringBuilder();
      tlhbody.append("      SimpleStringLineBuilder builder = new SimpleStringLineBuilder();\r\n");
      tlhbody.append("builder.append(\"timp2\");\r\n");
      tlhbody.append("builder.append(\"pozitie\");\r\n");
      for (ObiectDesc field : gasite) {
         String fnup = field.javaNumeCamp();
         if (field.dimSize == 2) {
            tlhbody.append("builder.append(\"" + fnup + "\");\r\n");
         }
      }
      tlhbody.append("builder.append(\"numePozitie\");\r\n");
      tlhbody.append("builder.append(\"cloudType\");\r\n");
      tlhbody.append("builder.append(\"hdfFileName\");\r\n return builder.toString();\r\n");
      tllheader.setBody(tlhbody.toString());
   }

   protected void findFieldsFromHdfFile() throws Exception {
      String hdffile = "t:/data/MOD06MYD06_C6/MOD06_L2.A2012001.0950.006.2015056185525.hdf";
      File testExist = new File(hdffile);
      if (!testExist.exists()) {
         throw new IllegalArgumentException("Fisierul hdf nu exista");
      }
      System.out.println("Hdf file " + hdffile);
      H4File hfile = new H4File(hdffile);
      hfile.open();
      dimensiuni = Util.dimensionFieldsFromDescription(hfile);
      H4Group dataFieldsGrup = (H4Group) hfile.get("/mod06/Data Fields");
      Iterator<HObject> itDataFields = dataFieldsGrup.getMemberList().iterator();
      while (itDataFields.hasNext()) {
         HObject hObject = itDataFields.next();
         if (hObject instanceof H4SDS) {
            System.out.print("Obiectul meu este :" + hObject.getName());
            ObiectDesc struct = new ObiectDesc();
            struct.numeCamp = hObject.getName();
            H4SDS h4sds = (H4SDS) hObject;
            Object dataO = h4sds.getData();
            if (dataO instanceof short[]) {
               struct.tip = "DFNT_INT16";
               struct.propertyType = "Double";
            } else if (dataO instanceof byte[]) {
               struct.tip = "DFNT_INT8";
               struct.propertyType = "Byte";
            } else if (dataO instanceof double[]) {
               struct.tip = "DFNT_FLOAT32";
               struct.propertyType = "Double";
            } else if (dataO instanceof float[]) {
               struct.tip = "DFNT_FLOAT32";
               struct.propertyType = "Double";
            } else if (dataO instanceof int[]) {
               struct.tip = "DFNT_INT32";
               struct.propertyType = "Double";
            } else {
               struct.tip = "Necunoscut";
            }
            System.out.print(" tip: " + struct.tip);
            long[] dims = h4sds.getDims();
            int length = dims.length;
            struct.dimSize = length;
            if (length > 2) {
               struct.crosdim = NumberUtils.min(dims);
            }
            if (NumberUtils.max(dims) == dimensiuni.get("Cell_Along_Swath_5km")) {
               struct.cellSize = 5;
            }
            if (NumberUtils.max(dims) == dimensiuni.get("Cell_Along_Swath_1km")) {
               struct.cellSize = 1;
            }
            System.out.print(" dim: " + struct.dimSize + "");
            Iterator metit = h4sds.getMetadata().iterator();
            while (metit.hasNext()) {
               Attribute meta = (Attribute) metit.next();
               if ("long_name".equals(meta.getName())) {
                  String[] v = (String[]) meta.getValue();
                  struct.longName = v[0];
                  continue;
               }
               if ("Parameter_Type".equals(meta.getName())) {
                  String[] v = (String[]) meta.getValue();
                  struct.parameterType = v[0];
                  continue;
               }
               if ("units".equals(meta.getName())) {
                  String[] v = (String[]) meta.getValue();
                  struct.unitum = v[0];
                  continue;
               }
               if ("scale_factor".equals(meta.getName())) {
                  double[] v = (double[]) meta.getValue();
                  struct.scale = v[0];
                  continue;
               }
               if ("add_offset".equals(meta.getName())) {
                  double[] v = (double[]) meta.getValue();
                  struct.offset = v[0];
                  continue;
               }
               if (meta.getValue() instanceof double[]) {
                  // System.out.println(meta.getName() + "=" +
                  // Arrays.toString((double[]) meta.getValue()));
               } else if (meta.getValue() instanceof String[]) {
                  // System.out.println(meta.getName() + "=" +
                  // Arrays.toString((String[]) meta.getValue()));
               } else if (meta.getValue() instanceof short[]) {
                  // System.out.println(meta.getName() + "=" +
                  // Arrays.toString((short[]) meta.getValue()));
               } else if (meta.getValue() instanceof int[]) {
                  // System.out.println(meta.getName() + "=" +
                  // Arrays.toString((int[]) meta.getValue()));
               } else if (meta.getValue() instanceof byte[]) {
                  // System.out.println(meta.getName() + "=" +
                  // Arrays.toString((byte[]) meta.getValue()));
               } else if (meta.getValue() instanceof float[]) {
                  // System.out.println(meta.getName() + "=" +
                  // Arrays.toString((float[]) meta.getValue()));
               }
            }
            System.out.println(" longName: " + struct.longName + ".");
            gasite.add(struct);
         }
      }
      hfile.close();
   }
}
