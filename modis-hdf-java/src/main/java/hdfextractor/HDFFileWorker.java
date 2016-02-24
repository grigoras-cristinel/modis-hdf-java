package hdfextractor;

import java.io.Serializable;

import org.joda.time.DateTime;

import hdfextractor.ozon.OmiMeasurementQualityFlags;
import hdfextractor.ozon.OmiProcessingQualityFlags;

public interface HDFFileWorker extends Serializable {

   public boolean hasOzon(String nume) throws Exception;

   public DateTime findTimpMediu() throws Exception;

   public DateTime findTimp(String nume) throws Exception;

   public Double findPresiuneSuprafata(String nume) throws Exception;

   public Double findTemperaturaSuprafata(String nume) throws Exception;

   public Double findOzon(String nume) throws Exception;

   public Double findCloudFraction(String nume) throws Exception;

   public Long findPozitie(String nume) throws Exception;

   public boolean hasPosition(String nume) throws Exception;

   public OmiMeasurementQualityFlags getQualityFlags(String nume) throws Exception;

   public OmiProcessingQualityFlags getProcessingQualityFlags(String nume) throws Exception;

   public void close();

   public Float findViewingZenithAngle(String siteName) throws Exception;

   public Integer getLatime();
}
