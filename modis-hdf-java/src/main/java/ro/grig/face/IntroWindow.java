package ro.grig.face;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

/**
 * Fereastra principala.
 *
 * @author grig
 *
 */
@Singleton
public class IntroWindow extends JFrame {

   private class BntFilterAndExportActionListener implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         getTextArea().append("Deschide formular filtrare.\n");
         FilterAndExport sm = injector.getInstance(FilterAndExport.class);
         sm.pack();
         sm.setVisible(true);
      }
   }

   /**
    * Logger for this class
    */
   private static final Logger logger = LoggerFactory.getLogger(IntroWindow.class);
   /**
    *
    */
   private static final long serialVersionUID = -1503486769221773418L;
   private JTextArea textArea;
   private JToolBar toolBar;
   @Inject
   Injector injector;
   Sc sc;

   @Inject
   public void injectEventBus(EventBus eventBus) {
      eventBus.register(this);
   }

   /**
    * Create the application.
    */
   @Inject
   public IntroWindow(Sc sc) {
      this.sc = sc;
      initialize();
   }

   @Subscribe
   public void recordLogMessage(LogtextEvent text) {
      textArea.append(text.getText());
      if (!text.getText().endsWith("\n")) {
         textArea.append("\n");
      }
   }

   AtomicLong contor = new AtomicLong(0);
   private JPanel statusPanel;
   private JLabel forStatusBar;

   @Subscribe
   public void recordHeartBeath(HearthBeathEvent text) {
      contor.incrementAndGet();
      getForStatusBar().setText(text.getProcKey() + ":" + contor);
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize() {
      setBounds(100, 100, 1062, 701);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JMenuBar menuBar = new JMenuBar();
      setJMenuBar(menuBar);
      JMenu mnConfig = new JMenu("Config");
      mnConfig.setFont(new Font("Segoe UI", Font.PLAIN, 11));
      mnConfig.setMnemonic('c');
      menuBar.add(mnConfig);
      JMenuItem mntmHdfStorageLocation = new JMenuItem("Hdf STORAGE location");
      mntmHdfStorageLocation.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            sc.postLogMessage("Selecteaza locatia fisierelor HDF.\n");
            JFileChooser chooser = new JFileChooser();
            if (sc.getConfig().containsKey(Sc.HDF_STORAGE_LOCATION)) {
               chooser.setCurrentDirectory(new java.io.File(sc.getConfig().getString(Sc.HDF_STORAGE_LOCATION)));
            } else {
               chooser.setCurrentDirectory(new java.io.File("."));
               sc.getConfig().addProperty(Sc.HDF_STORAGE_LOCATION, ".");
            }
            chooser.setDialogTitle("Select HDF files storage.");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            //
            // disable the "All files" option.
            //
            chooser.setAcceptAllFileFilterUsed(false);
            //
            if (chooser.showOpenDialog(IntroWindow.this) == JFileChooser.APPROVE_OPTION) {
               sc.getConfig().setProperty(Sc.HDF_STORAGE_LOCATION, chooser.getSelectedFile().getAbsolutePath());
               sc.postLogMessage("Selected: " + chooser.getSelectedFile().getAbsolutePath() + ".\n");
            } else {
               sc.postLogMessage("No selection.");
            }
         }
      });
      mnConfig.add(mntmHdfStorageLocation);
      JMenuItem mntmOuputSearchLocation = new JMenuItem("Ouput search location");
      mntmOuputSearchLocation.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            sc.postLogMessage("Selecteaza locatia rezultatelor cautarii.\n");
            JFileChooser chooser = new JFileChooser();
            if (sc.getConfig().containsKey(Sc.SEARCH_REZULT_STORAGE_LOCATION)) {
               chooser.setCurrentDirectory(
                     new java.io.File(sc.getConfig().getString(Sc.SEARCH_REZULT_STORAGE_LOCATION)));
            } else {
               chooser.setCurrentDirectory(new java.io.File("."));
               sc.getConfig().addProperty(Sc.SEARCH_REZULT_STORAGE_LOCATION, ".");
            }
            chooser.setDialogTitle("Select search results storage.");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showOpenDialog(IntroWindow.this) == JFileChooser.APPROVE_OPTION) {
               sc.getConfig().setProperty(Sc.SEARCH_REZULT_STORAGE_LOCATION,
                     chooser.getSelectedFile().getAbsolutePath());
               getTextArea().append("Selected: " + chooser.getSelectedFile().getAbsolutePath() + ".\n");
            } else {
               getTextArea().append("No selection.");
            }
         }
      });
      mnConfig.add(mntmOuputSearchLocation);
      JCheckBoxMenuItem chckbxShowToolbar = new JCheckBoxMenuItem("View Toolbar");
      chckbxShowToolbar.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            if (((JCheckBoxMenuItem) e.getSource()).isSelected()) {
               logger.debug("Show toolbar.");
               toolBar.setVisible(true);
            } else {
               logger.debug("Hide toolbar.");
               toolBar.setVisible(false);
            }
         }
      });
      chckbxShowToolbar.setSelected(true);
      mnConfig.add(chckbxShowToolbar);
      JMenuItem mntmResetWindowPositions = new JMenuItem("Reset window positions");
      mntmResetWindowPositions.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            int confi = JOptionPane.showConfirmDialog(IntroWindow.this,
                  "Sunteti de acord sa resetati configuratia la default ?");
            if (confi == 0) {
               sc.resetWindowsPositions();
               sc.flushConfig();
            }
         }
      });
      mnConfig.add(mntmResetWindowPositions);
      JMenu mnSearch = new JMenu("Search");
      menuBar.add(mnSearch);
      JMenuItem mntmSearchModisWs = new JMenuItem("Search MODIS WS");
      mntmSearchModisWs.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            getTextArea().append("Deschide formular cautare.\n");
            SearchModis sm = injector.getInstance(SearchModis.class);
            sm.pack();
            sm.setVisible(true);
            logger.debug("Size = W{}px,H{}px", sm.getWidth(), sm.getHeight());
         }
      });
      mnSearch.add(mntmSearchModisWs);
      JMenuItem mntmOpenLastSearch = new JMenuItem("Open last search results");
      mntmOpenLastSearch.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            getTextArea().append("Deschide folder cautari anterioare.\n");
            if (sc.getConfig().containsKey(Sc.SEARCH_REZULT_STORAGE_LOCATION)) {
               try {
                  Runtime.getRuntime().exec("explorer.exe /select, "
                        + sc.getConfig().getString(sc.getConfig().getString(Sc.SEARCH_REZULT_STORAGE_LOCATION)));
               } catch (IOException e1) {
                  e1.printStackTrace();
               }
            }
         }
      });
      mnSearch.add(mntmOpenLastSearch);
      if (sc.getConfig().containsKey(Sc.HDF_STORAGE_LOCATION)) {
         EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
               textArea.append("Am gasit ");
               if (sc.getConfig().containsKey(Sc.HDF_STORAGE_LOCATION)) {
                  File hdfStorage = new File(sc.getConfig().getString(Sc.HDF_STORAGE_LOCATION));
                  String[] list = hdfStorage.list(new SuffixFileFilter(".hdf"));
                  textArea.append(list.length + " fisiere hdf in locatia "
                        + sc.getConfig().getString(Sc.HDF_STORAGE_LOCATION) + ".\n");
               } else {
                  textArea.append("0 fisiere hdf.");
               }
            }
         });
      }
      addWindowListener(new WindowAdapter() {

         @Override
         public void windowClosing(WindowEvent e) {
            sc.flushConfig();
         }
      });
      UtilitareSwing.dialogLocationSaver(this, sc);
      setFont(new Font("Tahoma", Font.BOLD, 12));
      getContentPane().setFont(new Font("Tahoma", Font.BOLD, 12));
      JPanel panel = new JPanel();
      getContentPane().add(panel, BorderLayout.CENTER);
      panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
      textArea = new JTextArea();
      JScrollPane scrolll = new JScrollPane(textArea);
      scrolll.setBorder(new EmptyBorder(4, 4, 4, 4));
      scrolll.setPreferredSize(new Dimension(600, 200));
      panel.add(scrolll);
      toolBar = new JToolBar();
      getContentPane().add(toolBar, BorderLayout.NORTH);
      JButton btnConfiguration = new JButton("");
      btnConfiguration.setBackground(Color.WHITE);
      btnConfiguration.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            GlobalConfigurationEditor cnf = injector.getInstance(GlobalConfigurationEditor.class);
            cnf.pack();
            cnf.setVisible(true);
         }
      });
      btnConfiguration.setToolTipText("Configureaza Aplicatia");
      btnConfiguration.setMnemonic('C');
      btnConfiguration.setIcon(new ImageIcon(IntroWindow.class.getResource("/toolbar/configure-app-64x64.png")));
      toolBar.add(btnConfiguration);
      JButton btnModisQueryBuilder = new JButton("");
      btnModisQueryBuilder.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            getTextArea().append("Deschide formular cautare.\n");
            SearchModis sm = injector.getInstance(SearchModis.class);
            sm.pack();
            sm.setVisible(true);
         }
      });
      btnModisQueryBuilder.setToolTipText("Build Modis Query");
      btnModisQueryBuilder.setMnemonic('Q');
      btnModisQueryBuilder.setIcon(new ImageIcon(IntroWindow.class.getResource("/toolbar/ws-query-builder-64x64.png")));
      toolBar.add(btnModisQueryBuilder);
      JButton btnRunQueryFromFile = new JButton("");
      btnRunQueryFromFile.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            RunWsSearch ws = injector.getInstance(RunWsSearch.class);
            ws.pack();
            ws.setVisible(true);
         }
      });
      btnRunQueryFromFile.setMnemonic('E');
      btnRunQueryFromFile.setToolTipText("Run Query From File");
      btnRunQueryFromFile.setIcon(new ImageIcon(IntroWindow.class.getResource("/toolbar/toolbar-run-query-64x64.png")));
      toolBar.add(btnRunQueryFromFile);
      JButton btnModisExtractQueryPoint = new JButton("");
      btnModisExtractQueryPoint.setName("MEQP");
      btnModisExtractQueryPoint.setVerticalAlignment(SwingConstants.BOTTOM);
      btnModisExtractQueryPoint.setHorizontalTextPosition(SwingConstants.CENTER);
      btnModisExtractQueryPoint
            .setIcon(new ImageIcon(IntroWindow.class.getResource("/framedecoration/point-selector-64x64.png")));
      btnModisExtractQueryPoint.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            ModisExtractQueryPoint qp = injector.getInstance(ModisExtractQueryPoint.class);
            qp.pack();
            qp.setVisible(true);
         }
      });
      toolBar.add(btnModisExtractQueryPoint);
      JButton btnModisRunExtractQueryPoint = new JButton("");
      btnModisRunExtractQueryPoint
            .setIcon(new ImageIcon(IntroWindow.class.getResource("/toolbar/run-point-extraction-64x64.png")));
      btnModisRunExtractQueryPoint.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            RunQueryExtractPoint qp = injector.getInstance(RunQueryExtractPoint.class);
            qp.pack();
            qp.setVisible(true);
         }
      });
      toolBar.add(btnModisRunExtractQueryPoint);
      JButton bntFilterAndExport = new JButton("");
      bntFilterAndExport.addActionListener(new BntFilterAndExportActionListener());
      bntFilterAndExport.setOpaque(false);
      bntFilterAndExport.setContentAreaFilled(false);
      bntFilterAndExport
            .setIcon(new ImageIcon(IntroWindow.class.getResource("/framedecoration/frameFilterAndExport.png")));
      toolBar.add(bntFilterAndExport);
      setForeground(Color.LIGHT_GRAY);
      setIconImage(
            Toolkit.getDefaultToolkit().getImage(IntroWindow.class.getResource("/ro/grig/face/icons/application.png")));
      setTitle("Doctorat @ Grigoraș Cristnel");
      textArea.append(sc.configurationAsText());
      statusPanel = new JPanel();
      statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.DARK_GRAY, Color.GRAY, null, null));
      getContentPane().add(statusPanel, BorderLayout.SOUTH);
      forStatusBar = new JLabel();
      statusPanel.add(forStatusBar);
   }

   public JTextArea getTextArea() {
      return textArea;
   }

   public JToolBar getToolBar() {
      return toolBar;
   }

   public JPanel getStatusPanel() {
      return statusPanel;
   }

   public JLabel getForStatusBar() {
      return forStatusBar;
   }
}
