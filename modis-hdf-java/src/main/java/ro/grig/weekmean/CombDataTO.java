package ro.grig.weekmean;

import org.joda.time.DateTime;

/**
 * Structura date pentru comparatie masuratori aerosolo (AOD si Angstrom)
 * satelit cu aeronet. Datele sunt mediate zilnic.
 *
 * @author grig
 *
 */
public class CombDataTO {
   private DateTime data;
   private Integer year;
   private Integer month;
   private Integer dayOfYear;
   private Integer dayOfWeek;
   private Integer weekOfYear;
   private String statie;
   private Double aeoronetAOD;
   private Double satelitAOD;
   private Double aeoronetAngstrom;
   private Double satelitAngstrom;
   private String weekKey;
   private boolean workDayValue = false;
   private boolean weekendValue = false;

   public void makeWeekKEy() {
      weekKey = statie + year + data.getWeekOfWeekyear();
      dayOfWeek = data.getDayOfWeek();
      switch (dayOfWeek) {
      case 1:// Monday
      case 6:// Saturday
      case 7:// Sunday
         weekendValue = true;
         break;
      case 2:// Tuesday
      case 3:// Wedsday
      case 4:// Thursday
      case 5:// Friday
         workDayValue = true;
         break;
      default:
         break;
      }
      weekOfYear = data.getWeekOfWeekyear();
   }

   public CombDataTO buildMean() {
      CombDataTO mean = new CombDataTO();
      mean.setMonth(getMonth());
      mean.setSatelitAngstrom(getSatelitAngstrom() / getMonth());
      mean.setSatelitAOD(getSatelitAOD() / getMonth());
      mean.setAeoronetAOD(getAeoronetAOD() / getMonth());
      mean.setAeoronetAngstrom(getAeoronetAngstrom() / getMonth());
      return mean;
   }

   public DateTime getData() {
      return data;
   }

   public void setData(DateTime data) {
      this.data = data;
   }

   public String getStatie() {
      return statie;
   }

   public void setStatie(String statie) {
      this.statie = statie;
   }

   public Double getAeoronetAOD() {
      return aeoronetAOD;
   }

   public void setAeoronetAOD(Double aeoronetAOD) {
      this.aeoronetAOD = aeoronetAOD;
   }

   public Double getSatelitAOD() {
      return satelitAOD;
   }

   public void setSatelitAOD(Double satelitAOD) {
      this.satelitAOD = satelitAOD;
   }

   public Double getAeoronetAngstrom() {
      return aeoronetAngstrom;
   }

   public void setAeoronetAngstrom(Double aeoronetAngstrom) {
      this.aeoronetAngstrom = aeoronetAngstrom;
   }

   public Double getSatelitAngstrom() {
      return satelitAngstrom;
   }

   public void setSatelitAngstrom(Double satelitAngstrom) {
      this.satelitAngstrom = satelitAngstrom;
   }

   public Integer getYear() {
      return year;
   }

   public void setYear(Integer year) {
      this.year = year;
   }

   public Integer getMonth() {
      return month;
   }

   public void setMonth(Integer month) {
      this.month = month;
   }

   public Integer getDayOfYear() {
      return dayOfYear;
   }

   public void setDayOfYear(Integer dayOfYear) {
      this.dayOfYear = dayOfYear;
   }

   public String getWeekKey() {
      return weekKey;
   }

   public void setWeekKey(String weekKey) {
      this.weekKey = weekKey;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((weekKey == null) ? 0 : weekKey.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      CombDataTO other = (CombDataTO) obj;
      if (weekKey == null) {
         if (other.weekKey != null)
            return false;
      } else if (!weekKey.equals(other.weekKey))
         return false;
      return true;
   }

   public Integer getDayOfWeek() {
      return dayOfWeek;
   }

   public void setDayOfWeek(Integer dayOfWeek) {
      this.dayOfWeek = dayOfWeek;
   }

   public boolean isWorkDayValue() {
      return workDayValue;
   }

   public void setWorkDayValue(boolean workDayValue) {
      this.workDayValue = workDayValue;
   }

   public boolean isWeekendValue() {
      return weekendValue;
   }

   public void setWeekendValue(boolean weekendValue) {
      this.weekendValue = weekendValue;
   }

   public void add(CombDataTO elm) {
      this.satelitAOD = (this.satelitAOD == null ? 0 : this.satelitAOD) + elm.getSatelitAOD();
      this.satelitAngstrom = (this.satelitAngstrom == null ? 0 : this.satelitAngstrom) + elm.getSatelitAngstrom();
      this.aeoronetAOD = (this.aeoronetAOD == null ? 0 : this.aeoronetAOD) + elm.getAeoronetAOD();
      this.aeoronetAngstrom = (this.aeoronetAngstrom == null ? 0 : this.aeoronetAngstrom) + elm.getAeoronetAngstrom();
      if (month == null) {
         month = 0;
      }
      month += 1;
   }

   public Integer getWeekOfYear() {
      return weekOfYear;
   }

   public void setWeekOfYear(Integer weekOfYear) {
      this.weekOfYear = weekOfYear;
   }

   @Override
   public String toString() {
      return "CombDataTO [statie=" + statie + ", year=" + year + ", weekOfYear=" + weekOfYear + ", dayOfWeek="
            + dayOfWeek + ", data=" + data + ", month=" + month + ", dayOfYear=" + dayOfYear + ", aeoronetAOD="
            + aeoronetAOD + ", satelitAOD=" + satelitAOD + ", aeoronetAngstrom=" + aeoronetAngstrom
            + ", satelitAngstrom=" + satelitAngstrom + ", weekKey=" + weekKey + ", workDayValue=" + workDayValue
            + ", weekendValue=" + weekendValue + "]";
   }
}
