package ro.grig.sat.util;

import java.io.Serializable;

import com.google.common.collect.ComparisonChain;

public class StatieMeteoTO implements Serializable, Comparable<StatieMeteoTO> {

   /**
    * 
    */
   private static final long serialVersionUID = 618596016300311898L;
   private String cod;
   private double lat;
   private double lon;

   public StatieMeteoTO(String cod, double lat, double lon) {
      super();
      this.cod = cod;
      this.lat = lat;
      this.lon = lon;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      long temp;
      temp = Double.doubleToLongBits(lat);
      result = prime * result + (int) (temp ^ (temp >>> 32));
      temp = Double.doubleToLongBits(lon);
      result = prime * result + (int) (temp ^ (temp >>> 32));
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
      StatieMeteoTO other = (StatieMeteoTO) obj;
      if (Double.doubleToLongBits(lat) != Double.doubleToLongBits(other.lat))
         return false;
      if (Double.doubleToLongBits(lon) != Double.doubleToLongBits(other.lon))
         return false;
      return true;
   }

   public String getCod() {
      return cod;
   }

   @Override
   public String toString() {
      return "StatieMeteoTO [cod=" + cod + ", lat=" + lat + ", lon=" + lon + "]";
   }

   public void setCod(String cod) {
      this.cod = cod;
   }

   public double getLat() {
      return lat;
   }

   public void setLat(double lat) {
      this.lat = lat;
   }

   public double getLon() {
      return lon;
   }

   public void setLon(double lon) {
      this.lon = lon;
   }

   @Override
   public int compareTo(StatieMeteoTO o) {
      return ComparisonChain.start().compare(lat, o.lat).compare(lon, o.lon).result();
   }
}
