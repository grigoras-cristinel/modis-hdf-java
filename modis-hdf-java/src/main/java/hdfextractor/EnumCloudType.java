package hdfextractor;

public enum EnumCloudType {
   CIRRUS(440d, 50d, 3.6d, 0d), CIRROSTRATUS(440d, 50d, 23d, 3.6d), DEEPCONVECTION(440d, 50d, 379d, 23d), ALTOCUMULUS(
         680d, 440d, 3.6d, 0d), ALTOSTRATUS(680d, 440d, 23d, 3.6d), NIMBOSTRATUS(680d, 440d, 379d, 23d), CUMULUS(1000d,
         680d, 3.6d, 0d), STRATOCUMULUS(1000d, 680d, 23d, 3.6d), STRATUS(1000d, 680d, 379d, 23d);
   Double cloudTopPresMax;
   Double cloudTopPresMin;
   Double cloudOticMax;
   Double cloudOpticMin;

   private EnumCloudType(Double cloudTopPresMax, Double cloudTopPresMin, Double cloudOticMax, Double cloudOpticMin) {
      this.cloudTopPresMax = cloudTopPresMax;
      this.cloudTopPresMin = cloudTopPresMin;
      this.cloudOticMax = cloudOticMax;
      this.cloudOpticMin = cloudOpticMin;
   }

   public static EnumCloudType selectType(Double topPression, Double grosimeOptica) {
      double grosimeOpticaTranslatata = 3.65 * grosimeOptica;
      EnumCloudType gasit = null;
      for (EnumCloudType el : EnumCloudType.values()) {
         if (el.cloudTopPresMin <= topPression && topPression <= el.cloudTopPresMax) {
            if (el.cloudOpticMin <= grosimeOpticaTranslatata && grosimeOpticaTranslatata <= el.cloudOticMax) {
               return el;
            }
         }
      }
      return gasit;
   }

   public static EnumCloudType selectType(Long topPression, Long grosimeOptica) {
      double grosimeOpticaTranslatata = 3.65 * grosimeOptica;
      EnumCloudType gasit = null;
      for (EnumCloudType el : EnumCloudType.values()) {
         if (el.cloudTopPresMin <= topPression && topPression <= el.cloudTopPresMax) {
            if (el.cloudOpticMin <= grosimeOpticaTranslatata && grosimeOpticaTranslatata <= el.cloudOticMax) {
               return el;
            }
         }
      }
      return gasit;
   }
}
