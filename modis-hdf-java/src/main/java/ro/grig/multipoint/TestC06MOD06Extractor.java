package ro.grig.multipoint;

import hdfextractor.H4FileWorkerC6Mod06A;

import java.util.TreeMap;

public class TestC06MOD06Extractor {

   public static final String FileTOTest = "d:/grig/work/satelit/D06_C6/MYD06_L2.A2003335.0955.006.2014011022310.hdf";

   public static void main(String[] args) {
      System.out.println("//start test");
      try {
         TreeMap<String, double[]> pozitii = new TreeMap<String, double[]>();
         pozitii.put("pozitia_unu", new double[] { 34.81137, 58.807713 });
         H4FileWorkerC6Mod06A worker = new H4FileWorkerC6Mod06A(FileTOTest, pozitii);
         Long findPozitiaUnu = worker.findPozitie("pozitia_unu");
         System.out.println("Pozitia unu are indexul:" + findPozitiaUnu);
      } catch (Exception e) {
         e.printStackTrace();
      }
      System.out.println("//end test");
   }
}
