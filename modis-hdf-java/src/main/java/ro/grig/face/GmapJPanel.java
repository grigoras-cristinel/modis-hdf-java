package ro.grig.face;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.geotools.geometry.DirectPosition2D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;

import hdfextractor.StatieMeteoPoint;

public class GmapJPanel extends JPanel {

   /**
    * Logger for this class
    */
   private static final Logger logger = LoggerFactory.getLogger(GmapJPanel.class);
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private Image icon;
   private Integer zoom = 5;
   private ArrayList<StatieMeteoPoint> markers = new ArrayList<StatieMeteoPoint>();
   Timer timer = new Timer(500, new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
         loadGoogleImage();
         timer.stop();
      }
   });
   LoadingCache<String, BufferedImage> cacheImagini = CacheBuilder.newBuilder().maximumSize(1000)
         .expireAfterWrite(10, TimeUnit.MINUTES).removalListener(new RemovalListener<String, BufferedImage>() {

            @Override
            public void onRemoval(RemovalNotification<String, BufferedImage> notification) {
               String key = notification.getKey();
               BufferedImage val = notification.getValue();
               saveCacheToDisk(key, val);
            }
         }).build(new CacheLoader<String, BufferedImage>() {

            @Override
            public BufferedImage load(String key) throws Exception {
               HashCode hsh = Hashing.md5().newHasher().putString(key, Charsets.UTF_8).hash();
               String fname = BaseEncoding.base32().encode(hsh.asBytes()).replace("=", "");
               File intemp = new File(System.getProperty("java.io.tmpdir") + "/" + fname);
               if (intemp.exists()) {
                  try {
                     BufferedImage e = ImageIO.read(intemp);
                     logger.debug("Reload image from temp folder {}", fname);
                     return e;
                  } catch (Exception e) {
                     intemp.delete();
                  }
               } else {
                  BufferedImage icon1 = ImageIO.read(new URL(key));
                  logger.debug("Load image {}x{} from web {}", icon1.getWidth(), icon1.getHeight(), key);
                  return icon1;
               }
               return null;
            }
         });

   public GmapJPanel() {
      super();
      setMinimumSize(new Dimension(100, 100));
      setMaximumSize(new Dimension(640, 640));
      setLayout(new BorderLayout(0, 0));
      JPanel panel = new JPanel();
      panel.setFocusable(false);
      panel.setRequestFocusEnabled(false);
      panel.setOpaque(false);
      add(panel, BorderLayout.SOUTH);
      panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
      JButton btnDown = new JButton("");
      btnDown.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            logger.debug("down");
            moveCenterDown();
         }
      });
      btnDown.setBorderPainted(false);
      btnDown.setFocusable(false);
      btnDown.setContentAreaFilled(false);
      btnDown.setIcon(new ImageIcon(GmapJPanel.class.getResource("/framedecoration/arrow-down-2.png")));
      panel.add(btnDown);
      JPanel panel_1 = new JPanel();
      panel_1.setFocusable(false);
      panel_1.setRequestFocusEnabled(false);
      panel_1.setOpaque(false);
      add(panel_1, BorderLayout.EAST);
      panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
      JButton btnRight = new JButton("");
      btnRight.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            logger.debug("right");
            moveCenterRight();
         }
      });
      btnRight.setOpaque(false);
      btnRight.setBorderPainted(false);
      btnRight.setFocusable(false);
      btnRight.setIcon(new ImageIcon(GmapJPanel.class.getResource("/framedecoration/arrow-right-1.png")));
      btnRight.setContentAreaFilled(false);
      panel_1.add(btnRight);
      JPanel panel_2 = new JPanel();
      panel_2.setFocusable(false);
      panel_2.setRequestFocusEnabled(false);
      panel_2.setOpaque(false);
      add(panel_2, BorderLayout.WEST);
      panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
      JButton btnLeft = new JButton("");
      btnLeft.setRequestFocusEnabled(false);
      btnLeft.setFocusable(false);
      btnLeft.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            logger.debug("left");
            moveCenterLeft();
         }
      });
      btnLeft.setContentAreaFilled(false);
      btnLeft.setBorderPainted(false);
      btnLeft.setIcon(new ImageIcon(GmapJPanel.class.getResource("/framedecoration/arrow-left-1.png")));
      btnLeft.setSelectedIcon(new ImageIcon(GmapJPanel.class.getResource("/framedecoration/arrow-left-1.png")));
      btnLeft.setOpaque(false);
      panel_2.add(btnLeft);
      JPanel panel_3 = new JPanel();
      panel_3.setFocusable(false);
      panel_3.setRequestFocusEnabled(false);
      panel_3.setOpaque(false);
      add(panel_3, BorderLayout.NORTH);
      JButton btnUp = new JButton("");
      btnUp.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            logger.debug("Up");
            moveCenterUp();
         }
      });
      btnUp.setBorderPainted(false);
      btnUp.setContentAreaFilled(false);
      btnUp.setFocusable(false);
      btnUp.setOpaque(false);
      btnUp.setRequestFocusEnabled(false);
      btnUp.setIcon(new ImageIcon(GmapJPanel.class.getResource("/framedecoration/arrow-up-2.png")));
      panel_3.add(btnUp);
      this.addMouseListener(new MouseAdapter() {

         @Override
         public void mousePressed(MouseEvent e) {
            clickLocation = e.getPoint();
         }
      });
      try {
         icon = ImageIO.read(IntroWindow.class.getResourceAsStream("/framedecoration/romania-pointer.jpg"));
      } catch (IOException e2) {
         e2.printStackTrace();
      }
      this.addComponentListener(new ComponentAdapter() {

         @Override
         public void componentResized(ComponentEvent e) {
            GmapJPanel.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            timer.start();
         }
      });
      loadGoogleImage();
      this.addPropertyChangeListener("zoom", new PropertyChangeListener() {

         @Override
         public void propertyChange(PropertyChangeEvent evt) {
            GmapJPanel.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            timer.start();
         }
      });
   }

   DirectPosition2D center = new DirectPosition2D(24.665703, 46.146640);

   protected String centerToString() {
      return center.getY() + "," + center.getX();
   }

   protected void moveCenterDown() {
      double step = calculateStep();
      double limitY = center.getY() - step;
      if (limitY < -85) {
         limitY = -85d;
      }
      center.setLocation(center.getX(), limitY);
      GmapJPanel.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
      timer.start();
   }

   private double calculateStep() {
      switch (zoom) {
      case 5:
      case 6:
      case 7:
         return 0.5d;
      }
      return 1d;
   }

   protected void moveCenterRight() {
      double step = calculateStep();
      double limitX = center.getX() + step;
      if (limitX > 180d) {
         limitX = 180d;
      }
      center.setLocation(limitX, center.getY());
      GmapJPanel.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
      timer.start();
   }

   protected void moveCenterUp() {
      double step = calculateStep();
      double limitY = center.getY() + step;
      if (limitY > 85) {
         limitY = 85d;
      }
      center.setLocation(center.getX(), limitY);
      GmapJPanel.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
      timer.start();
   }

   protected void moveCenterLeft() {
      double step = calculateStep();
      double limitX = center.getX() - step;
      if (limitX < -179.5d) {
         limitX = -179.5d;
      }
      center.setLocation(limitX, center.getY());
      GmapJPanel.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
      timer.start();
   }

   protected void loadGoogleImage() {
      EventQueue.invokeLater(new Runnable() {

         @Override
         public void run() {
            try {
               StringBuilder mars = new StringBuilder();
               if (markers != null && markers.size() > 0) {
                  int cont = 1;
                  for (StatieMeteoPoint smp : markers) {
                     String start = "color:red|label=" + cont + "|" + smp.getPoint().y + "," + smp.getPoint().x;
                     mars.append("&markers=" + URLEncoder.encode(start, "utf-8"));
                     cont++;
                  }
               }
               Dimension size2 = GmapJPanel.this.getSize();
               String size = (int) size2.getWidth() + "x" + (int) size2.getHeight();
               String keyhash = "https://maps.googleapis.com/maps/api/staticmap?&maptype=terrain&"
                     + "key=AIzaSyC-wTC0g0CQ4IlLIlsu3vhz5_i6QCqfY08" + "&center=" + centerToString() + "&zoom=" + zoom
                     + "&size=" + size + mars;
               BufferedImage iconh = cacheImagini.get(keyhash);
               icon = iconh;
               GmapJPanel.this.repaint();
               cacheImagini.put(keyhash, iconh);
               saveCacheToDisk(keyhash, iconh);
            } catch (ExecutionException e) {
               e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
               e.printStackTrace();
            }
            GmapJPanel.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
         };
      });
   }

   private Point clickLocation;

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage(icon, 0, 0, null);
   }

   public Point getClickLocation() {
      return clickLocation;
   }

   public Integer getZoom() {
      return zoom;
   }

   public void setZoom(Integer zoom) {
      Integer oldzoom = this.zoom;
      this.zoom = zoom;
      firePropertyChange("zoom", oldzoom, zoom);
   }

   protected void saveCacheToDisk(String key, BufferedImage val) {
      HashCode hsh = Hashing.md5().newHasher().putString(key, Charsets.UTF_8).hash();
      String fname = BaseEncoding.base32().encode(hsh.asBytes()).replace("=", "");
      File intemp = new File(System.getProperty("java.io.tmpdir") + "/" + fname);
      if (!intemp.exists()) {
         try {
            ImageIO.write(val, "png", intemp);
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }

   public ArrayList<StatieMeteoPoint> getMarkers() {
      return markers;
   }

   public void setMarkers(ArrayList<StatieMeteoPoint> markers) {
      this.markers = markers;
   }
}