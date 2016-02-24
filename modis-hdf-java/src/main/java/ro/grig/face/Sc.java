package ro.grig.face;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Static Config Support
 * 
 * @author grig
 *
 */
@Singleton
public class Sc {

   /**
    * Logger for this class
    */
   private static final Logger logger = LoggerFactory.getLogger(Sc.class);
   /**
    * Logger for this class
    */
   public static final String MAININTERFACE_CONFIG = "maininterface.properties";
   private PropertiesConfiguration configGlobal;
   private PropertiesConfiguration configProiect;
   private boolean configuratieGlobalaModificata = false;
   private boolean configuratieProiectModificata = false;
   private EventBus eventBus;
   private int intercomunicationPort;

   @Inject
   public void injectEventBus(EventBus eventBus) {
      this.eventBus = eventBus;
      eventBus.register(this);
   }

   public Sc() {
      init();
   }

   public void registerOnBus(Object obj) {
      eventBus.register(obj);
   }

   public void postLogMessage(String loggerText) {
      logger.debug("LogTextEvent: {}", loggerText);
      eventBus.post(new LogtextEvent(loggerText));
   }

   public void postHeartBeath(String evt) {
      logger.debug("HearthBeathEvent: {}", evt);
      eventBus.post(new HearthBeathEvent(evt, evt));
   }

   public void postLogMessage(String loggerText, Object... arguments) {
      FormattingTuple ft = MessageFormatter.arrayFormat(loggerText, arguments);
      logger.debug(ft.getMessage());
      eventBus.post(new LogtextEvent(ft.getMessage()));
   }

   private void init() {
      configGlobal = new PropertiesConfiguration();
      try {
         configGlobal.load(MAININTERFACE_CONFIG);
         configGlobal.setFileName(MAININTERFACE_CONFIG);
         configGlobal.setAutoSave(false);
         configGlobal.setProperty("run.main.lasttime", new Date());
         logger.info("Configuration loaded from " + configGlobal.getFile().getAbsolutePath());
      } catch (ConfigurationException e) {
         logger.error("Exceptie citire configuratie. Creez configuratie noua.");
         try {
            configGlobal.setFileName(MAININTERFACE_CONFIG);
            configGlobal.addProperty("config-initialized", new Date());
            configGlobal.save(MAININTERFACE_CONFIG);
            configGlobal.setAutoSave(false);
         } catch (ConfigurationException e1) {
            logger.error("Exceptie initializare configuratie.", e1);
         }
      }
      String lastproj = (String) configGlobal.getProperty(Sc.LAST_PROIECT);
      String lastprojfolder = (String) configGlobal.getProperty(Sc.LAST_PROIECT_FOLDER);
      configProiect = new PropertiesConfiguration();
      if (lastproj != null && lastprojfolder != null) {
         File lastProjFol = new File(lastprojfolder);
         if (lastProjFol.exists()) {
            File projconfig = new File(lastProjFol + "/doctorat.properties");
            if (projconfig.exists()) {
               try {
                  configProiect.load(projconfig);
                  configProiect.setFileName(projconfig.getAbsolutePath());
                  configProiect.setAutoSave(false);
                  configProiect.setProperty("run.main.lasttime", new Date());
                  logger.info("Project Configuration loaded from " + configProiect.getFile().getAbsolutePath());
               } catch (ConfigurationException e) {
                  logger.error("Exceptie citire configuratie proiect.");
               }
            }
         }
      }
   }

   public String configurationAsText() {
      StringBuilder lastconfig = new StringBuilder();
      if (configGlobal.containsKey(HDF_STORAGE_LOCATION)) {
         lastconfig.append(HDF_STORAGE_LOCATION + " = " + configGlobal.getString(HDF_STORAGE_LOCATION) + "\n");
      }
      if (configGlobal.containsKey(SEARCH_REZULT_STORAGE_LOCATION)) {
         lastconfig.append(
               SEARCH_REZULT_STORAGE_LOCATION + " = " + configGlobal.getString(SEARCH_REZULT_STORAGE_LOCATION) + "\n");
      }
      if (configGlobal.containsKey(CURRENT_WORKSPACE)) {
         lastconfig.append(CURRENT_WORKSPACE + " = " + configGlobal.getString(CURRENT_WORKSPACE) + "\n");
      }
      return lastconfig.toString();
   }

   public static final String HDF_STORAGE_LOCATION = "hdf.storage.location";
   public static final String SEARCH_REZULT_STORAGE_LOCATION = "search.results.location";
   public static final String LAST_PROIECT = "searchmodis.lastproiect";
   public static final String CURRENT_WORKSPACE = "global.proiect.workspace";
   public static final String LAST_PROIECT_FOLDER = "global.proiect.lastfolder";
   public static final String PROJECT_NAME = "project.name";
   public static final String PROJECT_SEARCH_LATITUDINE_NORD = "project.searchmodis.latitudineNord";
   public static final String PROJECT_SEARCH_LATITUDINE_SUD = "project.searchmodis.latitudineSud";
   public static final String PROJECT_SEARCH_LONGIT_EST = "project.searchmodis.longitudineEst";
   public static final String PROJECT_SEARCH_LONGIT_VEST = "project.searchmodis.longitudineVest";
   public static final String PROJECT_SEARCH_DATASTART = "project.searchmodis.dataStart";
   public static final String PROJECT_SEARCH_DATAEND = "project.searchmodis.dataEnd";
   public static final String PROJECT_SEARCH_COLECTIA = "project.searchmodis.colectia";
   public static final String PROJECT_SEARCH_SATELIT = "project.searchmodis.satelit";
   public static final String PROJECT_SEARCH_PRODUS = "project.searchmodis.produs";
   public static final String PROJECT_LAST_MQFILE = "proiect.lastmq.filename";
   public static final String PRODUCT_SATELIT_AQUA = "AQUA";
   public static final String PRODUCT_SATELIT_TERRA = "TERRA";
   public static final String PRODUCT_SATELIT_BOTH = "TERRA & AQUA";
   public static final String PROJECT_POINTS = "project.points";
   public static final String PROJECT_FOLDER_EXPORTPOINTS = "project.exportpoints";

   public PropertiesConfiguration getConfig() {
      return configGlobal;
   }

   public void setConfig(PropertiesConfiguration config) {
      this.configGlobal = config;
   }

   public void putProjectValue(String key, String value) {
      configuratieProiectModificata = true;
      configProiect.clearProperty(key);
      configProiect.setProperty(key, value);
   }

   public void delProjectValue(String key, String value) {
      configuratieProiectModificata = true;
      configProiect.clearProperty(key);
   }

   public void putGlobalValue(String key, String value) {
      configuratieGlobalaModificata = true;
      configGlobal.clearProperty(key);
      configGlobal.setProperty(key, value);
   }

   public void delGlobalValue(String key, String value) {
      configuratieGlobalaModificata = true;
      configGlobal.clearProperty(key);
   }

   public String getConfigValue(String key) {
      return (String) configGlobal.getProperty(key);
   }

   public void flushConfig() {
      try {
         if (configuratieGlobalaModificata) {
            configGlobal.save();
            logger.debug("Salvare configuratie. {}", configGlobal.getFile().getAbsolutePath());
            configuratieGlobalaModificata = false;
         }
         if (configuratieProiectModificata) {
            if (configProiect.getFileName() != null) {
               logger.debug("Salvare configuratie proiect. {}", configProiect.getFile().getAbsolutePath());
               configProiect.save();
            }
            configuratieProiectModificata = false;
         }
      } catch (ConfigurationException e) {
         e.printStackTrace();
      }
   }

   public void resetWindowsPositions() {
      Iterator<String> keys = configGlobal.getKeys();
      while (keys.hasNext()) {
         String string = (String) keys.next();
         if (string.endsWith("location.x")) {
            configGlobal.clearProperty(string);
         }
         if (string.endsWith("location.y")) {
            configGlobal.clearProperty(string);
         }
      }
   }

   public PropertiesConfiguration getConfigProiect() {
      return configProiect;
   }

   public void setConfigProiect(PropertiesConfiguration configProiect) {
      this.configProiect = configProiect;
   }

   public void changeProject() {
      String cale = configGlobal.getString(Sc.CURRENT_WORKSPACE);
      putGlobalValue(Sc.LAST_PROIECT_FOLDER, cale);
      putProjectValue(Sc.CURRENT_WORKSPACE, cale);
      putProjectValue(Sc.PROJECT_NAME, configGlobal.getString(Sc.LAST_PROIECT));
      String fn = cale + "/doctorat.properties";
      File lastProjFileConfig = new File(fn);
      if (lastProjFileConfig.exists()) {
         logger.debug("Config proiect filename {}", fn);
         try {
            configProiect.load(lastProjFileConfig);
            configProiect.setFileName(lastProjFileConfig.getAbsolutePath());
            configProiect.setAutoSave(false);
            configProiect.setProperty("run.main.lasttime", new Date());
            configuratieProiectModificata = true;
            postLogMessage("Project configuration loaded from {}.", fn);
         } catch (ConfigurationException e) {
            logger.error("Exceptie citire configuratie proiect.");
         }
      } else {
         logger.debug("Config proiect filename {}", fn);
         configProiect.setFileName(fn);
         try {
            configProiect.save();
         } catch (ConfigurationException e) {
            e.printStackTrace();
         }
      }
      postLogMessage("Project workspace changed to {}.", cale);
   }

   public void runProcess(final ProcessBuilder process) {
      EventQueue.invokeLater(new Runnable() {

         public void run() {
            process.redirectErrorStream(true);
            process.redirectInput(Redirect.INHERIT);
            process.redirectOutput(Redirect.INHERIT);
            process.environment().put("intercomunication.port", "" + getIntercomunicationPort());
            try {
               Process start = process.start();
               if (start.isAlive()) {
                  postLogMessage("Wait for process.");
                  start.waitFor();
               }
               postLogMessage("Process end.");
            } catch (IOException e1) {
               e1.printStackTrace();
            } catch (InterruptedException e1) {
               e1.printStackTrace();
            }
         }
      });
   }

   public void setIntercomunicationPort(int port) {
      this.intercomunicationPort = port;
   }

   public int getIntercomunicationPort() {
      return intercomunicationPort;
   }

   public String getWorkspacePath() {
      return (String) configProiect.getProperty(Sc.CURRENT_WORKSPACE);
   }

   public String getProjectName() {
      return (String) configProiect.getProperty(Sc.PROJECT_NAME);
   }
}
