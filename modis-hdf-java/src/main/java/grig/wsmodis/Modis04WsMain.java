package grig.wsmodis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;

import ro.grig.face.Sc;

/**
 * Alege fisiere folosind servicul rest api de la modis. Vom face o cautare cu
 * paramtri doriti si vom cauta daca fisierele exista local. Daca nu exista vom
 * scrie calea completa intr-un fisier ce poate fi rulat cu aria2c.
 *
 */
public class Modis04WsMain extends RunProcessAbstractSupport {

   /**
    * Logger for this class
    */
   private static final Logger logger = LoggerFactory.getLogger(Modis04WsMain.class);
   final static String BASEURL = "http://modwebsrv.modaps.eosdis.nasa.gov/axis2/services/MODAPSservices/";
   static String HDF_STORAGE_LOCAITON = "t:/data/MOD04C6";
   static String WORKSPACE = "t:/data/temp";
   static String proiect = "default";
   static double[] cordExtMagurele = new double[] { 45.5, 43.5, 27, 25 };
   static double[] coordonate = cordExtMagurele;// magurele
   static String[] satelitii = new String[] { "MYD", "MOD" };
   static String product = "04";
   static String colection = "06";
   static DateTime dataStart = new DateTime(2008, 01, 01, 0, 0);
   static DateTime dataEnd = new DateTime(2008, 01, 05, 0, 0);

   /**
    * The main method.
    *
    * @param args
    *           the arguments
    */
   public static void main(String[] args) {
      System.out.println("Start ...");
      Modis04WsMain runner = new Modis04WsMain();
      runner.initComunication();
      runner.runProcess(args);
      logger.debug("End ...");
   }

   private static boolean readParametersFromEnv(String[] args) {
      boolean retval = true;
      if (System.getProperty("basefile.folder") != null) {
         HDF_STORAGE_LOCAITON = System.getProperty("basefile.folder");
         System.out.println("Basefile folder setat din property :" + HDF_STORAGE_LOCAITON);
      } else {
         retval = false;
      }
      return retval;
   }

   private static boolean readParameterFromFile(String[] args) {
      CommandLineParser parser = new DefaultParser();
      Options options = new Options();
      options.addOption("f", true, "Open *-mq.properties full path file.");
      String filename = null;
      try {
         boolean return_val = false;
         CommandLine cmdline = parser.parse(options, args);
         if (cmdline.hasOption("f")) {
            filename = cmdline.getOptionValue("f");
            logger.debug("Gasit f option cu valoarea: {}", filename);
            File mf = new File(filename);
            if (!mf.exists()) {
               throw new IllegalArgumentException(MessageFormatter
                     .arrayFormat("File argument {} not exist.", new String[] { filename }).getMessage());
            }
            return_val = true;
            PropertiesConfiguration prf = new PropertiesConfiguration(mf);
            if (prf.containsKey(Sc.CURRENT_WORKSPACE)) {
               String string = prf.getString(Sc.CURRENT_WORKSPACE);
               if (string.endsWith("/") || string.endsWith("\\")) {
                  string = StringUtils.removeEnd(string, "/");
                  string = StringUtils.removeEnd(string, "\\");
               }
               WORKSPACE = string;
            } else {
               return_val = false;
               throw new IllegalArgumentException(
                     MessageFormatter.arrayFormat("File argument {} not contains {} property.",
                           new String[] { filename, Sc.CURRENT_WORKSPACE }).getMessage());
            }
            updateLog4jConfiguration(WORKSPACE + "/" + proiect + "_logfile-mqrun_" + timestampStart + ".log");
            if (prf.containsKey(Sc.HDF_STORAGE_LOCATION)) {
               String string = prf.getString(Sc.HDF_STORAGE_LOCATION);
               HDF_STORAGE_LOCAITON = string;
            } else {
               return_val = false;
               throw new IllegalArgumentException(
                     MessageFormatter.arrayFormat("File argument {} not contains {} property.",
                           new String[] { filename, Sc.HDF_STORAGE_LOCATION }).getMessage());
            }
            if (prf.containsKey(Sc.PROJECT_SEARCH_COLECTIA)) {
               String string = prf.getString(Sc.PROJECT_SEARCH_COLECTIA);
               if ("05".equals(string)) {
                  colection = "5";
               } else if ("06".equals(string)) {
                  colection = "6";
               } else {
                  return_val = false;
                  throw new IllegalArgumentException(
                        MessageFormatter.arrayFormat("File argument {} not contains {} property.",
                              new String[] { filename, Sc.PROJECT_SEARCH_COLECTIA }).getMessage());
               }
            } else {
               return_val = false;
               throw new IllegalArgumentException(
                     MessageFormatter.arrayFormat("File argument {} not contains {} property.",
                           new String[] { filename, Sc.PROJECT_SEARCH_COLECTIA }).getMessage());
            }
            if (prf.containsKey(Sc.PROJECT_SEARCH_SATELIT)) {
               String string = prf.getString(Sc.PROJECT_SEARCH_SATELIT);
               if (Sc.PRODUCT_SATELIT_AQUA.equals(string)) {
                  satelitii = new String[] { "MYD" };
               } else if (Sc.PRODUCT_SATELIT_TERRA.equals(string)) {
                  satelitii = new String[] { "MOD" };
               } else if (Sc.PRODUCT_SATELIT_BOTH.equals(string)) {
                  satelitii = new String[] { "MOD", "MYD" };
               } else {
                  return_val = false;
                  throw new IllegalArgumentException(
                        MessageFormatter.arrayFormat("File argument {} not contains corect {} property.",
                              new String[] { filename, Sc.PROJECT_SEARCH_SATELIT }).getMessage());
               }
            } else {
               return_val = false;
               throw new IllegalArgumentException(
                     MessageFormatter.arrayFormat("File argument {} not contains {} property.",
                           new String[] { filename, Sc.PROJECT_SEARCH_SATELIT }).getMessage());
            }
            if (prf.containsKey(Sc.PROJECT_SEARCH_PRODUS)) {
               String string = prf.getString(Sc.PROJECT_SEARCH_PRODUS);
               if ("04".equals(string)) {
                  product = "04_L2";
               } else if ("06".equals(string)) {
                  product = "06_L2";
               } else {
                  return_val = false;
                  throw new IllegalArgumentException(
                        MessageFormatter.arrayFormat("File argument {} not contains corect {} property.",
                              new String[] { filename, Sc.PROJECT_SEARCH_PRODUS }).toString());
               }
            } else {
               return_val = false;
               throw new IllegalArgumentException(
                     MessageFormatter.arrayFormat("File argument {} not contains {} property.",
                           new String[] { filename, Sc.PROJECT_SEARCH_PRODUS }).toString());
            }
            if (prf.containsKey(Sc.PROJECT_SEARCH_LATITUDINE_SUD) && prf.containsKey(Sc.PROJECT_SEARCH_LATITUDINE_NORD)
                  && prf.containsKey(Sc.PROJECT_SEARCH_LONGIT_EST) && prf.containsKey(Sc.PROJECT_SEARCH_LONGIT_VEST)) {
               coordonate = new double[] { prf.getDouble(Sc.PROJECT_SEARCH_LATITUDINE_NORD),
                     prf.getDouble(Sc.PROJECT_SEARCH_LATITUDINE_SUD), prf.getDouble(Sc.PROJECT_SEARCH_LONGIT_EST),
                     prf.getDouble(Sc.PROJECT_SEARCH_LONGIT_VEST) };
            } else {
               return_val = false;
               throw new IllegalArgumentException(
                     MessageFormatter
                           .arrayFormat("File argument {} not contains one of  {},{},{},{} propertis.",
                                 new String[] { filename, Sc.PROJECT_SEARCH_LATITUDINE_SUD,
                                       Sc.PROJECT_SEARCH_LATITUDINE_NORD, Sc.PROJECT_SEARCH_LONGIT_EST,
                                       Sc.PROJECT_SEARCH_LONGIT_VEST })
                           .toString());
            }
            if (prf.containsKey(Sc.PROJECT_SEARCH_DATASTART)) {
               long dstart = prf.getLong(Sc.PROJECT_SEARCH_DATASTART);
               dataStart = new DateTime(dstart);
            } else {
               return_val = false;
               throw new IllegalArgumentException(
                     MessageFormatter.arrayFormat("File argument {} not contains {} property.",
                           new String[] { filename, Sc.PROJECT_SEARCH_DATASTART }).toString());
            }
            if (prf.containsKey(Sc.PROJECT_SEARCH_DATAEND)) {
               long dstart = prf.getLong(Sc.PROJECT_SEARCH_DATAEND);
               dataEnd = new DateTime(dstart);
            } else {
               return_val = false;
               throw new IllegalArgumentException(
                     MessageFormatter.arrayFormat("File argument {} not contains {} property.",
                           new String[] { filename, Sc.PROJECT_SEARCH_DATAEND }).toString());
            }
         }
         return return_val;
      } catch (IllegalArgumentException e) {
         throw e;
      } catch (ParseException e) {
         e.printStackTrace();
         throw new IllegalArgumentException(MessageFormatter
               .arrayFormat("Read parameters {} exception message {}.", new String[] { filename, Sc.CURRENT_WORKSPACE })
               .toString());
      } catch (ConfigurationException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return false;
   }

   private static void showAllElements(Document doc) {
      Elements docElements = doc.getAllElements();
      for (Element ne : docElements) {
         System.out.println("EL:" + ne.nodeName() + " - " + ne.ownText());
      }
   }

   @Override
   protected void runProcess(String[] args) {
      if (readParameterFromFile(args)) {
      } else if (readParametersFromEnv(args)) {
      } else {
         // use defaults
      }
      try {
         logger.debug("Workspace : {}", WORKSPACE);
         PrintWriter outMisFileData = new PrintWriter(
               WORKSPACE + "/" + proiect + "_" + colection + "_" + product + "_missing_ids.txt");
         PrintWriter oufileUrls = new PrintWriter(
               WORKSPACE + "/" + proiect + "_" + colection + "_" + product + "_download_url_list.txt");
         for (String satelit : satelitii) {
            sendHeartBeat();
            DateTime start = dataStart;
            logger.debug(" Data start {}  product {}.", start.toString("yyyy-MM-dd"), satelit);
            DateTime maxim = dataEnd;
            logger.debug(" Data sfarsit {} .", maxim.toString("yyyy-MM-dd"));
            // Nu preia fisierele deja existente
            HashMap<String, String> checksumMap = new HashMap<>();
            File checksumFisiereExistente = new File(HDF_STORAGE_LOCAITON + "/checksum.txt");
            if (checksumFisiereExistente.exists()) {
               FileInputStream fread = new FileInputStream(checksumFisiereExistente);
               BufferedReader br = new BufferedReader(new InputStreamReader(fread, Charsets.UTF_8));
               String line;
               while ((line = br.readLine()) != null) {
                  String[] split = line.split(" ");
                  if (split.length < 3) {
                     break;
                  }
                  checksumMap.put(split[2], split[0]);
               }
               br.close();
            }
            String productCod = satelit + product;
            sendMessage("interprocess Found size: " + checksumMap.size());
            logger.debug("Found size: {}", checksumMap.size());
            while (start.isBefore(maxim)) {
               String startV = start.toString("yyyy-MM-dd");
               DateTime end = start.plusMonths(1);
               String endV = end.toString("yyyy-MM-dd");
               try {
                  sendHeartBeat();
                  // Search
                  Connection connectSearch = Jsoup.connect(BASEURL + "searchForFiles?product=" + productCod
                        + "&collection=6&" + "start=" + startV + "&end=" + endV + "&coordsOrTiles=coords&north="
                        + coordonate[0] + "&south=" + coordonate[1] + "&east=" + coordonate[2] + "&west="
                        + coordonate[3] + "&dayNightBoth=DNB");
                  connectSearch.timeout(500000);
                  connectSearch.userAgent("Mozilla");
                  sendHeartBeat();
                  Document doc = connectSearch.get();
                  // showAllElements(doc);
                  Elements sffresp = doc.getElementsByTag("mws:searchforfilesresponse");
                  Element sffre = sffresp.first();
                  Elements sffrels = sffre.getElementsByTag("return");
                  ArrayList<String> fileResponses = new ArrayList<>();
                  for (Element elRetFile : sffrels) {
                     fileResponses.add(elRetFile.ownText());
                     // System.out.println("Id file:" +
                     // elRetFile.ownText());
                  }
                  StringBuilder fileIds = new StringBuilder();
                  for (String unfile : fileResponses) {
                     fileIds.append(unfile);
                     fileIds.append(",");
                  }
                  if (fileIds.toString().contains("No results")) {
                     System.out.println("No resuls");
                     start = end;
                     continue;
                  }
                  Connection connectFileInfo = Jsoup
                        .connect(BASEURL + "getFileProperties?fileIds=" + fileIds.toString());
                  connectFileInfo.timeout(500000);
                  connectFileInfo.userAgent("Mozilla");
                  Document docFileInfo = connectFileInfo.get();
                  // showAllElements(docFileInfo);
                  Elements elFileInfos = docFileInfo.getElementsByTag("mws:getfilepropertiesresponse");
                  Element rootInfos = elFileInfos.first();
                  logger.debug("Response size: " + rootInfos.children().size());
                  ArrayList<String> fileMiss = new ArrayList<>();
                  for (Element elUnfile : rootInfos.children()) {
                     // sunt pe un return
                     String fileId = elUnfile.getElementsByTag("mws:fileid").first().ownText();
                     String checksum = elUnfile.getElementsByTag("mws:checksum").first().ownText();
                     String filename = elUnfile.getElementsByTag("mws:filename").first().ownText();
                     String fileSize = elUnfile.getElementsByTag("mws:filesizebytes").first().ownText();
                     String filetype = elUnfile.getElementsByTag("mws:filetype").first().ownText();
                     File totest = new File(HDF_STORAGE_LOCAITON + "/" + filename);
                     boolean exist = totest.exists();
                     logger.debug("EL:" + fileId + " - " + checksum + "-" + filename + " - exista=" + exist);
                     if (!exist) {
                        outMisFileData.println(fileId + "," + filename + "," + checksum);
                        fileMiss.add(fileId);
                     } else if (checksumMap.containsKey(filename) && !checksum.equals(checksumMap.get(filename))) {
                        logger.debug("Wrong checksum:file=" + filename);
                        fileMiss.add(fileId);
                     }
                  }
                  if (fileMiss.isEmpty()) {
                     System.out.println("Toate fisierele exista: " + startV + " : " + endV);
                     start = end;
                     continue;
                  }
                  StringBuilder fileMissIds = new StringBuilder();
                  for (String unfile : fileMiss) {
                     fileMissIds.append(unfile);
                     fileMissIds.append(",");
                  }
                  sendHeartBeat();
                  Connection connectFileUrl = Jsoup.connect(BASEURL + "getFileUrls?fileIds=" + fileMissIds.toString());
                  connectFileUrl.timeout(500000);
                  connectFileUrl.userAgent("Mozilla");
                  Document docFileUrl = connectFileUrl.get();
                  Elements furesp = docFileUrl.getElementsByTag("mws:getFileUrlsResponse".toLowerCase());
                  Element furoot = furesp.first();
                  Elements furesps = furoot.getElementsByTag("return");
                  for (Element a1 : furesps) {
                     logger.debug("Url for file:" + a1.ownText());
                     sendHeartBeat();
                     oufileUrls.println(a1.ownText());
                  }
                  oufileUrls.flush();
                  outMisFileData.flush();
               } catch (HttpStatusException e) {
                  e.printStackTrace();
                  continue;
               }
               start = end;
            }
         }
         oufileUrls.close();
         outMisFileData.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
