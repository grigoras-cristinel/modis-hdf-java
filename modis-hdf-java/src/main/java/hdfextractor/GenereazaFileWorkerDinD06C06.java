package hdfextractor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

import ncsa.hdf.object.Attribute;
import ncsa.hdf.object.HObject;
import ncsa.hdf.object.h4.H4File;
import ncsa.hdf.object.h4.H4Group;
import ncsa.hdf.object.h4.H4SDS;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

/**
 * The Class GenereazaJavaDinStructuraHdf pentru C06D06.
 */
public class GenereazaFileWorkerDinD06C06 {

   /**
    * Logger for this class
    */
   private static final Logger logger = LoggerFactory.getLogger(GenereazaFileWorkerDinD06C06.class);

   public static final String hdfFile = "d:/grig/work/satelit/D06_C6/MYD06_L2.A2003335.0955.006.2014011022310.hdf";
   public static final String javaFileName = "D:/grig/workspace/hdfextractor/src/main/java/hdfextractor/H4FileWorkerMod06A.java";
   /**
	 *
	 */
   TreeSet<ObiectDesc> gasite = new TreeSet<GenereazaFileWorkerDinD06C06.ObiectDesc>();
   TreeMap<String, Integer> dimensiuni = new TreeMap<String, Integer>();

   class DimDesc {

      String dimName;
      Integer dimValue;
   }

   class ObiectDesc implements Comparable<ObiectDesc> {

      String numeCamp;
      String tip;
      String longName;
      String parameterType;

      @Override
      public String toString() {
         return "ObiectDesc [numeCamp=" + numeCamp + ", tip=" + tip + ", dimensiune=" + dimensiune + "]";
      }

      int dimSize;
      String dimensiune;

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
   }

   /**
    * @param args
    */
   public static void main(String[] args) {
      System.out.println("//Start");
      GenereazaFileWorkerDinD06C06 clasamea = new GenereazaFileWorkerDinD06C06();
      clasamea.runGenerator();
      System.out.println("//Stop");
   }

   private void runGenerator() {
      if (logger.isDebugEnabled()) {
         logger.debug("runGenerator() - start"); //$NON-NLS-1$
      }
      try {
         TreeSet<ObiectDesc> fields = findFieldsFromHdfFile();
         System.out.println("Numar fields :" + fields);
         // staticStringNames(gasite);
         // staticFieldsNames(gasite);
         // staticWriteNames2(gasite);
         // staticPropsNames2(gasite);
         // staticFieldsAccesors2(gasite);
         // staticParserFields2(gasite);
         // csvHeader(gasite);
         // readFromFileByPosition(gasite);
         // staticToGenerator(gasite);
      } catch (Exception e) {
         logger.error("runGenerator()", e); //$NON-NLS-1$
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      if (logger.isDebugEnabled()) {
         logger.debug("runGenerator() - end"); //$NON-NLS-1$
      }
   }

   protected TreeSet<ObiectDesc> findFieldsFromHdfFile() throws Exception {
      System.out.println("Hdf file " + hdfFile);
      H4File hfile = new H4File(hdfFile);
      hfile.open();
      H4Group dataFieldsGrup = (H4Group) hfile.get("/mod04/Data Fields");
      Iterator<HObject> itDataFields = dataFieldsGrup.getMemberList().iterator();
      while (itDataFields.hasNext()) {
         HObject hObject = itDataFields.next();
         if (hObject instanceof H4SDS) {
            System.out.println("Obiectul meu este un:" + hObject);
            ObiectDesc struct = new ObiectDesc();
            struct.numeCamp = hObject.getName();
            H4SDS h4sds = (H4SDS) hObject;
            Object dataO = h4sds.getData();
            if (dataO instanceof short[]) {
               struct.tip = "DFNT_INT16";
            } else if (dataO instanceof byte[]) {
               struct.tip = "DFNT_INT8";
            } else if (dataO instanceof double[]) {
               struct.tip = "DFNT_FLOAT32";
            } else if (dataO instanceof float[]) {
               struct.tip = "DFNT_FLOAT32";
            } else if (dataO instanceof int[]) {
               struct.tip = "DFNT_INT32";
            } else {
               struct.tip = "Necunoscut";
            }
            int length = h4sds.getDims().length;
            struct.dimSize = length;
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
            gasite.add(struct);
         }
      }
      hfile.close();
      return gasite;
   }

   private void findFieldsFromDescription() throws FileNotFoundException {
      Scanner fileScanner = new Scanner(new File("d:/grig/docs/modis04_c6-structure-hdf.txt"));
      boolean startGrupDataField = false;
      boolean startGrupDimensionField = false;
      boolean startObiect = false;
      boolean startDimObiect = false;
      ObiectDesc struct = null;
      DimDesc dimStruct = null;
      while (fileScanner.hasNextLine()) {
         String linie = fileScanner.nextLine();
         linie = linie.trim();
         if (StringUtils.equals("GROUP=DataField", linie)) {
            startGrupDataField = true;
            continue;
         }
         if (startGrupDataField) {
            if (StringUtils.equals("END_GROUP=DataField", linie)) {
               startGrupDataField = false;
               continue;
            }
            if (StringUtils.startsWith(linie, "OBJECT=")) {
               startObiect = true;
               struct = new GenereazaFileWorkerDinD06C06().new ObiectDesc();
               continue;
            }
            if (startObiect) {
               if (StringUtils.startsWith(linie, "END_OBJECT=")) {
                  startObiect = false;
                  gasite.add(struct);
                  // System.out.println("?" + struct);
                  continue;
               }
               if (StringUtils.startsWith(linie, "DataFieldName=")) {
                  struct.numeCamp = StringUtils.remove(linie.substring(14), "\"");
                  continue;
               }
               if (StringUtils.startsWith(linie, "DataType=")) {
                  struct.tip = StringUtils.remove(linie.substring(9), "\"");
                  continue;
               }
               if (StringUtils.startsWith(linie, "DimList=")) {
                  struct.dimensiune = StringUtils.remove(linie.substring(8), "\"");
                  continue;
               }
            }
         }
         if (StringUtils.equals("GROUP=Dimension", linie)) {
            startGrupDimensionField = true;
            continue;
         }
         if (startGrupDimensionField) {
            if (StringUtils.equals("END_GROUP=Dimension", linie)) {
               startGrupDimensionField = false;
               continue;
            }
            if (StringUtils.startsWith(linie, "OBJECT=")) {
               startDimObiect = true;
               dimStruct = new GenereazaFileWorkerDinD06C06().new DimDesc();
               continue;
            }
            if (startDimObiect) {
               if (StringUtils.startsWith(linie, "END_OBJECT=")) {
                  startDimObiect = false;
                  dimensiuni.put(dimStruct.dimName, dimStruct.dimValue);
                  // System.out.println("?" + struct);
                  continue;
               }
               if (StringUtils.startsWith(linie, "DimensionName=")) {
                  dimStruct.dimName = StringUtils.remove(linie.substring(14), "\"");
                  continue;
               }
               if (StringUtils.startsWith(linie, "Size=")) {
                  dimStruct.dimValue = Integer.decode(linie.substring(5));
                  continue;
               }
            }
         }
      }
      fileScanner.close();
   }

   protected void staticToGenerator(TreeSet<ObiectDesc> gasite2) {
      StringBuilder stb = new StringBuilder();
   }

   protected void staticFieldsAccesors2(TreeSet<ObiectDesc> gasite) {
      for (ObiectDesc str : gasite) {
         String numeCampFiltrat = str.numeCamp.replace("_", "").replace(".", "");
         String numeVariabila = WordUtils.uncapitalize(numeCampFiltrat);
         String numeAccesor = numeCampFiltrat;
         if (str.getDimSize() == 3) {
            String[] dimensiunile = StringUtils.split(StringUtils.substringBetween(str.dimensiune, "(", ")"), ",");
            Integer val1 = dimensiuni.get(dimensiunile[0]);
            if (val1 > 4) {
               System.out.println("//Skip: " + str + " : first dimension too big = " + val1);
               continue;
            }
            System.out.println("//cazul 3=" + val1);
            for (int i = 0; i < val1; i++) {
               String numeVariabilaL = numeVariabila + i;
               String numeAccesorL = numeAccesor + i;
               if ("DFNT_INT8".equals(str.tip)) {
                  String javaType = "Byte";
                  buildDeclaratie(str, numeVariabilaL, numeAccesorL, javaType);
               }
               if ("DFNT_INT16".equals(str.tip) || "DFNT_INT32".equals(str.tip) || "DFNT_FLOAT32".equals(str.tip)
                     || "DFNT_FLOAT64".equals(str.tip)) {
                  String javaType = "Double";
                  buildDeclaratie(str, numeVariabilaL, numeAccesorL, javaType);
               }
            }
         } else if (str.getDimSize() == 2) {
            if ("DFNT_INT8".equals(str.tip)) {
               String javaType = "Byte";
               buildDeclaratie(str, numeVariabila, numeAccesor, javaType);
            }
            if ("DFNT_INT16".equals(str.tip) || "DFNT_INT32".equals(str.tip) || "DFNT_FLOAT32".equals(str.tip)
                  || "DFNT_FLOAT64".equals(str.tip)) {
               String javaType = "Double";
               buildDeclaratie(str, numeVariabila, numeAccesor, javaType);
            }
         }
      }
   }

   /**
    * Codul pentru citit campurile
    * 
    * @param gasite
    */
   protected void staticParserFields2(TreeSet<ObiectDesc> gasite) {
      StringBuilder stb = new StringBuilder();
      for (ObiectDesc str : gasite) {
         String numeCampFiltrat = str.numeCamp.replace("_", "").replace(".", "");
         String numeAccesor = numeCampFiltrat;
         if (str.getDimSize() == 3) {
            String[] dimensiunile = StringUtils.split(StringUtils.substringBetween(str.dimensiune, "(", ")"), ",");
            Integer val1 = dimensiuni.get(dimensiunile[0]);
            if (val1 > 4) {
               continue;
            }
            stb.append("//cazul 3=" + val1 + "\n");
            for (int i = 0; i < val1; i++) {
               String numeAccesorL = numeAccesor + i;
               if ("DFNT_INT8".equals(str.tip)) {
                  stb.append("{\n	H4DSWrapper wrap = sheeturi.get(Mod04Constants." + str.numeCamp.toUpperCase()
                        + ");\n	valoare.set" + numeAccesorL + "(wrap.findBytePozitionData(new int[]{ " + i + ","
                        + "valoare.getPozitieAlong().intValue()," + "valoare.getPozitieAcross().intValue() }));\n}\n");
               }
               if ("DFNT_INT16".equals(str.tip) || "DFNT_INT32".equals(str.tip) || "DFNT_FLOAT32".equals(str.tip)
                     || "DFNT_FLOAT64".equals(str.tip)) {
                  stb.append("{\n	H4DSWrapper wrap = sheeturi.get(Mod04Constants." + str.numeCamp.toUpperCase()
                        + ");\n	valoare.set" + numeAccesorL + "(wrap.findDoublePozitionData(new int[]{ " + i + ","
                        + "valoare.getPozitieAlong().intValue()," + "valoare.getPozitieAcross().intValue() }));\n}\n");
               }
            }
         } else if (str.getDimSize() == 2) {
            if ("DFNT_INT8".equals(str.tip)) {
               stb.append("{\n	H4DSWrapper wrap = sheeturi.get(Mod04Constants." + str.numeCamp.toUpperCase()
                     + ");\n	valoare.set" + numeAccesor + "(wrap.findBytePozitionData(pozSearch));\n}\n");
            }
            if ("DFNT_INT16".equals(str.tip) || "DFNT_INT32".equals(str.tip) || "DFNT_FLOAT32".equals(str.tip)
                  || "DFNT_FLOAT64".equals(str.tip)) {
               stb.append("{\n	H4DSWrapper wrap = sheeturi.get(Mod04Constants." + str.numeCamp.toUpperCase()
                     + ");\n	valoare.set" + numeAccesor + "(wrap.findDoublePozitionData(pozSearch));\n}\n");
            }
         } else {
            stb.append("\n// Field:" + str.numeCamp + ". ( dimSize != 2 && dimSize != 3) . Found: " + str.getDimSize()
                  + ".\n");
         }
      }
      System.out.println(stb.toString());
   }

   private void buildDeclaratie(ObiectDesc str, String numeVariabila, String numeAccesor, String javaType) {
      System.out.println("/** \n  * Field:" + str.numeCamp + " \n  * Dimensiune: " + str.getDimSize() + "\n  * Tip: "
            + str.tip + "\n  * Desc: " + str.longName + "\n*/");
      System.out.println(" private " + javaType + " " + numeVariabila + " ;");
      System.out.println("/** \n  * Field:" + str.numeCamp + " \n  * Dimensiune: " + str.getDimSize() + "\n  * Tip: "
            + str.tip + "\n  * Desc: " + str.longName + "\n*/");
      System.out.println(" public " + javaType + " get" + numeAccesor + "()\n { return " + numeVariabila + ";\n }");
      System.out.println("/** \n  * Field:" + str.numeCamp + " \n  * Dimensiune: " + str.dimensiune + "\n  * Tip: "
            + str.tip + "\n  * Desc: " + str.longName + "\n*/");
      System.out.println(" public void set" + numeAccesor + "(" + javaType + " " + numeVariabila + ")\n { this."
            + numeVariabila + "=" + numeVariabila + ";\n }");
   }

   protected static void readFromFileByPosition(TreeSet<ObiectDesc> gasite) {
      for (ObiectDesc str : gasite) {
         System.out.println("{\n H4DSWrapper wrap = sheeturi.get(Mod04TO." + str.numeCamp.toUpperCase() + ");");
         if ("DFNT_INT8".equals(str.tip)) {
            System.out.println(" valoare.set" + str.numeCamp.replace("_", "")
                  + "(wrap.findBytePozitionData(pozSearch));\n}");
         }
         if ("DFNT_INT16".equals(str.tip)) {
            System.out.println(" valoare.set" + str.numeCamp.replace("_", "")
                  + "(wrap.findShortPozitionData(pozSearch));\n}");
         }
         if ("DFNT_INT32".equals(str.tip)) {
            System.out.println(" valoare.set" + str.numeCamp.replace("_", "")
                  + "(wrap.findShortPozitionData(pozSearch));\n}");
         }
         if ("DFNT_FLOAT32".equals(str.tip)) {
            System.out.println(" valoare.set" + str.numeCamp.replace("_", "")
                  + "(wrap.findDoublePozitionData(pozSearch));\n}");
         }
         if ("DFNT_FLOAT64".equals(str.tip)) {
            System.out.println(" valoare.set" + str.numeCamp.replace("_", "")
                  + "(wrap.findDoublePozitionData(pozSearch));\n}");
         }
      }
   }

   protected static void staticFieldsNames(TreeSet<ObiectDesc> gasite) {
      System.out.print("\nprivate static final String[]fieldNames = new String[] {");
      for (ObiectDesc str : gasite) {
         System.out.print(str.numeCamp.replace(".", "").toUpperCase() + ",");
      }
      System.out.println("};");
   }

   protected static void staticPropsNames2(TreeSet<ObiectDesc> gasite) {
      int contor = 1;
      for (ObiectDesc str : gasite) {
         if (str.getDimSize() == 2) {
            System.out.println(".append(\"" + WordUtils.capitalize(str.numeCamp.replace(".", "").replace("_", ""))
                  + "\")// Pozitie:" + contor);
            contor++;
         }
      }
      System.out.println(";");
   }

   protected static void staticWriteNames2(TreeSet<ObiectDesc> gasite) {
      int contor = 1;
      for (ObiectDesc str : gasite) {
         if (str.getDimSize() == 2) {
            System.out.println(".append(" + WordUtils.uncapitalize(str.numeCamp.replace(".", "").replace("_", ""))
                  + ")// Pozitie:" + contor);
            contor++;
         }
      }
      System.out.println(";");
   }

   protected static void staticStringNames(TreeSet<ObiectDesc> gasite) {
      for (ObiectDesc str : gasite) {
         System.out.println("/** \n  * Field:" + str.numeCamp + " \n  * Dimensiune: " + str.dimensiune + "\n  * Tip: "
               + str.tip + "\n*/");
         System.out.println(" public static final String " + str.numeCamp.replace(".", "").toUpperCase() + " = \""
               + str.numeCamp + "\" ;");
      }
   }

   protected void csvHeader(TreeSet<ObiectDesc> gasite) {
      StringBuilder header = new StringBuilder("static final String[] headers1=new String[]{");
      int contor = 6;
      for (ObiectDesc str : gasite) {
         String numeCampFiltrat = str.numeCamp.replace("_", "").replace(".", "");
         String numeVariabila = WordUtils.uncapitalize(numeCampFiltrat);
         if (str.getDimSize() == 3) {
            String[] dimensiunile = StringUtils.split(StringUtils.substringBetween(str.dimensiune, "(", ")"), ",");
            Integer val1 = dimensiuni.get(dimensiunile[0]);
            if (val1 > 4) {
               continue;
            }
            for (int i = 0; i < val1; i++) {
               String numeVariabilaL = "\"" + numeVariabila + i + "\"";
               contor++;
               if ("DFNT_INT8".equals(str.tip)) {
                  header.append(numeVariabilaL + ",");
               } else if ("DFNT_INT16".equals(str.tip) || "DFNT_INT32".equals(str.tip)
                     || "DFNT_FLOAT32".equals(str.tip) || "DFNT_FLOAT64".equals(str.tip)) {
                  header.append(numeVariabilaL + ",// " + contor + "\n");
               }
            }
         } else if (str.getDimSize() == 2) {
            contor++;
            if ("DFNT_INT8".equals(str.tip)) {
               header.append("\"" + numeVariabila + "\",// " + contor + "\n");
            }
            if ("DFNT_INT16".equals(str.tip) || "DFNT_INT32".equals(str.tip) || "DFNT_FLOAT32".equals(str.tip)
                  || "DFNT_FLOAT64".equals(str.tip)) {
               header.append("\"" + numeVariabila + "\",// " + contor + "\n");
            }
         }
      }
      for (int k = 7; k <= contor; k++) {
         System.out.println("new Optional(),// " + k + "");
      }
      System.out.println(header);
   }
}
