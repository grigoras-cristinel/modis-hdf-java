package ro.grig.face;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.nnsoft.guice.lifegycle.AfterInjectionModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

/**
 * Intrarea in aplicatia swing
 * 
 * @author grig
 *
 */
@Singleton
public class MainInterface implements PropertyChangeListener {

   /**
    * Logger for this class
    */
   private static final Logger logger = LoggerFactory.getLogger(MainInterface.class);
   Toolkit tk = Toolkit.getDefaultToolkit();
   @Inject
   private Sc sc;
   @Inject
   Injector injector;

   /**
    * Logger for this class
    */
   static void renderSplashFrame(Graphics2D g, int frame) {
      if (logger.isDebugEnabled()) {
         logger.debug("renderSplashFrame(Graphics2D, int) - start"); //$NON-NLS-1$
      }
      final String[] comps = { "load config start ", "load config end", "start frame" };
      g.setComposite(AlphaComposite.Clear);
      g.fillRect(220, 450, 300, 50);
      g.setPaintMode();
      g.setFont(Font.decode("sans-bold-20"));
      g.setColor(Color.WHITE);
      g.drawString("Loading " + comps[frame] + "...", 220, 500);
      logger.debug("Render draw string:{}. ", "");
   }

   public MainInterface() {
      super();
   }

   public static void main(String[] args) {
      logger.debug("String[] - start"); //$NON-NLS-1$
      Injector injector = Guice.createInjector(new AfterInjectionModule(), new MainGuiceModule());
      MainInterface main = injector.getInstance(MainInterface.class);
      main.startApplication();
      logger.debug("String[] - end"); //$NON-NLS-1$
   }

   protected void startApplication() {
      logger.debug("<no args> - start"); //$NON-NLS-1$
      final SplashScreen splash = SplashScreen.getSplashScreen();
      if (splash == null) {
         System.out.println("SplashScreen.getSplashScreen() returned null");
         logger.debug("<no args> - end"); //$NON-NLS-1$
         return;
      }
      final Graphics2D g = splash.createGraphics();
      if (g == null) {
         System.out.println("g is null");
         logger.debug("<no args> - end"); //$NON-NLS-1$
         return;
      }
      splash.update();
      renderSplashFrame(g, 0);
      splash.update();
      sc.getConfig();
      renderSplashFrame(g, 1);
      splash.update();
      EventQueue.invokeLater(new Runnable() {

         public void run() {
            logger.debug("<no args> - start"); //$NON-NLS-1$
            try {
               renderSplashFrame(g, 2);
               splash.update();
               IntroWindow window = injector.getInstance(IntroWindow.class);
               window.pack();
               splash.close();
               window.setVisible(true);
            } catch (Exception e) {
               logger.error("<no args>", e); //$NON-NLS-1$
               e.printStackTrace();
            }
            logger.debug("<no args> - end"); //$NON-NLS-1$
         }
      });
      EventQueue.invokeLater(new Runnable() {

         public void run() {
            InterProcesServer interserver = injector.getInstance(InterProcesServer.class);
         }
      });
      logger.debug("<no args> - end"); //$NON-NLS-1$
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt) {
      logger.debug("PropertyChangeEvent - start {} ", evt.getPropertyName()); //$NON-NLS-1$
      logger.debug("PropertyChangeEvent - end"); //$NON-NLS-1$
   }
}
