package hdfextractor.ozon;

import flanagan.math.Fmath;

/**
 * Introduc aici tripletul lunar si il folosesc pentru a
 * 
 * @author Grig
 * 
 */
public class CalculeazaOzonLaSol {

   /**
    * A,B,C
    */
   private double[][] tripletLunar = new double[][] { { 345.6, 22.01, 4.826 }, { 368.0, 21.55, 4.913 },
         { 376.5, 21.54, 5.036 }, { 373.2, 21.65, 5.076 }, { 359.3, 22.02, 4.970 }, { 343.5, 22.41, 4.970 },
         { 320.2, 22.98, 4.897 }, { 309.9, 23.32, 4.867 }, { 296.1, 23.61, 4.817 }, { 288.1, 23.55, 4.779 },
         { 294.5, 23.19, 4.763 }, { 319.7, 22.52, 4.749 } };

   /**
    * @param luna
    *           , baza 0
    * @param ozonTotal
    *           , valoarea total in loc de A
    * @return valoarea calculata
    */
   public double calculeazaU(int luna, double ozonTotal, double h) {
      double[] triplet = tripletLunar[luna];
      double A = ozonTotal;
      double B = triplet[1];
      double C = triplet[2];
      double numarator = 1 + Math.exp(-B / C);
      double numitor = 1 + Math.exp((h - B) / C);
      double retval = A * numarator / numitor;
      return retval;
   }

   double mukg = 1.660538e-27;// kg

   /**
    * Concentratia in micrograme pe m3 in coloana de ozont dintre sol si
    * inaltimea data
    * 
    * @param luna
    * @param ozonTotal
    * @param h
    *           inaltimea fata de sol
    * @return
    */
   public double calculeazaConcentratieugm3(int luna, double ozonTotal, double h) {
      double diferentaDouaNiveleInDobson = calculeazaDiferentaDouaNiveleInDobson(luna, ozonTotal, h);
      double v2_ugm3 = ((diferentaDouaNiveleInDobson * 2.69e20 * mukg * 48) / h) * 1e6;
      return v2_ugm3;
   }

   public double calculeazaConcentratiePtDobsonCunoscutugm3(double dobsonOzonDat, double h) {
      double diferentaDouaNiveleInDobson = dobsonOzonDat;
      double v2_ugm3 = ((diferentaDouaNiveleInDobson * 2.69e20 * mukg * 48) / h) * 1e6;
      return v2_ugm3;
   }

   public double calculeazaConcentratieugm3Interval10m(int luna, double ozonTotal, double h) {
      double dobsonH = calculeazaDiferentaDouaNiveleInDobson(luna, ozonTotal, h);
      double dobsonHfara10 = calculeazaDiferentaDouaNiveleInDobson(luna, ozonTotal, h - 10);
      double v2_ugm3 = (((dobsonHfara10 - dobsonH) * 2.69e20 * mukg * 48) / h) * 1e6;
      return v2_ugm3;
   }

   /**
    * Metoda nu este corecta deoarece profilele sunt de la sol si nu trebuie
    * scazut din valoare dobson data
    * 
    * @param luna
    * @param ozonTotal
    * @param h
    * @return
    */
   private double calculeazaDiferentaDouaNiveleInDobson(int luna, double ozonTotal, double h) {
      double ozonulLaNivelul_h_InDobson;
      {
         /*
          * calculeaza ozonul la un anumit nivel in dobson
          */
         double[] triplet = tripletLunar[luna];
         double A = ozonTotal;
         double B = triplet[1];
         double C = triplet[2];
         double numarator = 1 + Math.exp(-B / C);
         double numitor = 1 + Math.exp((h - B) / C);
         ozonulLaNivelul_h_InDobson = A * numarator / numitor;
      }
      // diferenta
      double dif2 = ozonTotal - ozonulLaNivelul_h_InDobson;
      return dif2;
   }

   /**
    * Profilul de ozon se calculeza de la sol deci functia are valori apropiate
    * de 0 aproape de sol
    * 
    * @param luna
    * @param ozonTotal
    * @param h
    * @return
    */
   public double calculeazaDobsonProfil(int luna, double ozonTotal, double h) {
      double ozonulLaNivelul_h_InDobson;
      {
         /*
          * calculeaza ozonul la un anumit nivel in dobson
          */
         double[] triplet = tripletLunar[luna];
         double A = ozonTotal;
         double B = triplet[1];
         double C = triplet[2];
         double numarator = 1 + Math.exp(-B / C);
         double numitor = 1 + Math.exp((h - B) / C);
         ozonulLaNivelul_h_InDobson = A * numarator / numitor;
      }
      // diferenta
      return ozonulLaNivelul_h_InDobson;
   }

   public double calculeazaUprim(int luna, double ozonTotal, double h) {
      double[] triplet = tripletLunar[luna];
      double A = ozonTotal;
      double B = triplet[1];
      double C = triplet[2];
      double f1 = (1 + Math.exp(-B / C)) / C;
      double f2n = Math.exp((h - B) / C);
      double f2u = Math.pow(1 + Math.exp((h - B) / C), 2);
      double f2 = f2n / f2u;
      double retval = -A * f1 * f2;
      return retval;
   }

   static double R_AER = 8.31432e03;
   static double miuozon = 48d;

   public double calculeazaRaportMasic(int luna, double ozonTotal, double h) {
      double uprim = calculeazaUprim(luna, ozonTotal, h);
      double raportR = R_AER / Fmath.R_GAS;
      double retval = -raportR * Fmath.N_AVAGADRO * miuozon * 1e-5 * uprim;
      return retval;
   }
}
