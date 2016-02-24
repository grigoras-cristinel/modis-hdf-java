package grig.wsmodis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.io.Charsets;
import org.joda.time.DateTime;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Alege fisiere folosind servicul rest api de la modis. Vom face o cautare cu
 * paramtri doriti si vom cauta daca fisierele exista local. Daca nu exista vom
 * scrie calea completa intr-un fisier ce poate fi rulat cu aria2c.
 *
 */
public class Modis06WsMain {
   final static String BASEURL = "http://modwebsrv.modaps.eosdis.nasa.gov/axis2/services/MODAPSservices/";
   static String BASEFILES_FOLDER = "t:/data/MOD06MYD06_C6";
   static double[] cordExtMagurele = new double[] { 47, 43, 27, 24 };
   static double[] cordNSEW = cordExtMagurele;// magurele

   /**
    * The main method.
    *
    * @param args
    *           the arguments
    */
   public static void main(String[] args) {
      System.out.println("Start ...");
      if (System.getProperty("basefile.folder") != null) {
         BASEFILES_FOLDER = System.getProperty("basefile.folder");
         System.out.println("Basefile folder setat din property :" + BASEFILES_FOLDER);
      }
      String[] products = new String[] { "MYD06_L2", "MOD06_L2" };
      try {
         PrintWriter outMisFileData = new PrintWriter(BASEFILES_FOLDER + "/../MOD06_missing_ids.txt");
         PrintWriter oufileUrls = new PrintWriter(BASEFILES_FOLDER + "/../MOD06_missing_aa.txt");
         File f1 = new File(BASEFILES_FOLDER + "/checksum.txt");
         if (!f1.exists()) {
            f1.createNewFile();
         }
         HashMap<String, String> checksumMap = new HashMap<>();
         if (f1.exists()) {
            FileInputStream fread = new FileInputStream(BASEFILES_FOLDER + "/checksum.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fread, Charsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
               String[] split = line.split(" ");
               if (split.length < 3) {
                  System.out.println("Split line wrong:" + line);
               } else {
                  checksumMap.put(split[2], split[0]);
               }
            }
         }
         for (String product : products) {
            DateTime start = new DateTime(2003, 1, 1, 0, 0);
            DateTime maxim = new DateTime(2015, 8, 1, 0, 0);
            //
            System.out.println("Found size:" + checksumMap.size());
            while (start.isBefore(maxim)) {
               System.out.println("Data start " + start.toString("yyyy-MM-dd"));
               String startV = start.toString("yyyy-MM-dd");
               DateTime end = start.plusMonths(1);
               String endV = end.toString("yyyy-MM-dd");
               try {
                  // Search
                  String surl = BASEURL + "searchForFiles?product=" + product + "&collection=6&" + "start=" + startV
                        + "&end=" + endV + "&coordsOrTiles=coords&north=" + cordNSEW[0] + "&south=" + cordNSEW[1]
                        + "&east=" + cordNSEW[2] + "&west=" + cordNSEW[3] + "&dayNightBoth=DNB";
                  System.out.println("URL: " + surl);
                  Connection connectSearch = Jsoup.connect(surl);
                  connectSearch.timeout(500000);
                  connectSearch.userAgent("Mozilla");
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
                  Connection connectFileInfo = Jsoup.connect(BASEURL + "getFileProperties?fileIds="
                        + fileIds.toString());
                  connectFileInfo.timeout(500000);
                  connectFileInfo.userAgent("Mozilla");
                  Document docFileInfo = connectFileInfo.get();
                  // showAllElements(docFileInfo);
                  Elements elFileInfos = docFileInfo.getElementsByTag("mws:getfilepropertiesresponse");
                  Element rootInfos = elFileInfos.first();
                  ArrayList<String> fileMiss = new ArrayList<>();
                  for (Element elUnfile : rootInfos.children()) {
                     // sunt pe un return
                     String fileId = elUnfile.getElementsByTag("mws:fileid").first().ownText();
                     String checksum = elUnfile.getElementsByTag("mws:checksum").first().ownText();
                     String filename = elUnfile.getElementsByTag("mws:filename").first().ownText();
                     String fileSize = elUnfile.getElementsByTag("mws:filesizebytes").first().ownText();
                     String filetype = elUnfile.getElementsByTag("mws:filetype").first().ownText();
                     File totest = new File(BASEFILES_FOLDER + "/" + filename);
                     boolean exist = totest.exists();
                     // System.out.println("EL:" + fileId + " - " +
                     // filename
                     // + " - exista=" + exist);
                     if (!exist) {
                        outMisFileData.println(fileId + "," + filename + "," + checksum);
                        System.out.println("Not exist:file=" + checksum + "-" + filename);
                        fileMiss.add(fileId);
                     } else if (!checksum.equals(checksumMap.get(filename))) {
                        System.out.println("Wrong checksum:file=" + filename);
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
                  Connection connectFileUrl = Jsoup.connect(BASEURL + "getFileUrls?fileIds=" + fileMissIds.toString());
                  connectFileUrl.timeout(500000);
                  connectFileUrl.userAgent("Mozilla");
                  Document docFileUrl = connectFileUrl.get();
                  Elements furesp = docFileUrl.getElementsByTag("mws:getFileUrlsResponse".toLowerCase());
                  Element furoot = furesp.first();
                  Elements furesps = furoot.getElementsByTag("return");
                  for (Element a1 : furesps) {
                     System.out.println("Url for file:" + a1.ownText());
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
      System.out.println("End ...");
   }

   private static void showAllElements(Document doc) {
      Elements docElements = doc.getAllElements();
      for (Element ne : docElements) {
         System.out.println("EL:" + ne.nodeName() + " - " + ne.ownText());
      }
   }
}
