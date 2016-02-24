package hdfextractor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Coordonate statii extragere date
 *
 * @author Grigoras Cristinel
 *
 */
public class StatiiMeteoModis06 {

   public static TreeMap<String, double[]> statii = new TreeMap<String, double[]>();

   static {
      statii.put("anm_back", new double[] { 44.512585, 26.080127 });
      statii.put("EFORIE", new double[] { 44.0749500, 28.632222 });
      statii.put("INOE", new double[] { 44.348610, 26.030623 });
      statii.put("CLUJ", new double[] { 46.768, 23.551 });
      statii.put("BRASOV", new double[] { 45.679800, 25.597984 });
      // verifica coordonate
      statii.put("TIMISOARA", new double[] { 45.746, 21.227 });
      statii.put("MOLDOVA", new double[] { 47.000, 28.816 });
      statii.put("IASI", new double[] { 44.348, 26.030 });
      statii.put("GLORIA", new double[] { 44.600, 29.360 });
   }

   /**
    *
    */
   private static String md5;

   public static String getMd5() {
      if (md5 == null) {
         md5 = buildMd5();
      }
      return md5;
   }

   private static String buildMd5() {
      String retval = "err";
      try {
         MessageDigest md = MessageDigest.getInstance("MD5");
         StringBuilder alltext = new StringBuilder();
         for (Entry<String, double[]> el : statii.entrySet()) {
            alltext.append(el.getKey());
            alltext.append(Arrays.toString(el.getValue()));
         }
         byte[] digest = md.digest(alltext.toString().getBytes());
         StringBuffer sb = new StringBuffer();
         for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
         }
         retval = sb.toString();
      } catch (NoSuchAlgorithmException e) {
         e.printStackTrace();
      }
      return retval;
   }
}
