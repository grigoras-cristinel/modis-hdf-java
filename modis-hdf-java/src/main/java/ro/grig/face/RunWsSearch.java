package ro.grig.face;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.StringUtils;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import grig.wsmodis.Modis04WsMain;

/**
 * Fereastra pentru pornire proiect de search.
 * 
 * @author grig
 *
 */
@Singleton
public class RunWsSearch extends JDialog {

   private class CancelButtonActionListener implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         RunWsSearch.this.setVisible(false);
         RunWsSearch.this.dispose();
         sc.postLogMessage("Exit run search window. Process status: ?");
      }
   }

   private class OkButtonActionListener implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         RunWsSearch.this.setVisible(false);
         RunWsSearch.this.dispose();
         sc.postLogMessage("Exit run search window. Process status: ?");
      }
   }

   /**
    * 
    */
   private static final long serialVersionUID = -721016335126171443L;
   private final JPanel contentPanel = new JPanel();
   private JLabel lblPleaseOpenProiect;
   private JLabel lblAlegeProiect;
   private Sc sc;
   private PropertiesConfiguration searchConfig = new PropertiesConfiguration();

   /**
    * Create the dialog.
    */
   @Inject
   public RunWsSearch(Sc sc) {
      this.sc = sc;
      initialize();
   }

   protected void initialize() {
      setTitle("Web Service Query Execute @ Grigoras Cristinel");
      setPreferredSize(new Dimension(660, 400));
      setResizable(false);
      setBounds(100, 100, 642, 286);
      UtilitareSwing.dialogLocationSaver(this, sc);
      setIconImage(Toolkit.getDefaultToolkit()
            .getImage(RunWsSearch.class.getResource("/framedecoration/run-query-16x16.png")));
      getContentPane().setLayout(new BorderLayout());
      contentPanel.setPreferredSize(new Dimension(640, 250));
      contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      getContentPane().add(contentPanel, BorderLayout.CENTER);
      contentPanel.setLayout(new FormLayout(
            new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
                  FormSpecs.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("97px"), ColumnSpec.decode("center:min"),
                  FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("249dlu"), },
            new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.LINE_GAP_ROWSPEC,
                  RowSpec.decode("44px"), FormSpecs.LINE_GAP_ROWSPEC, FormSpecs.PREF_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
                  FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));
      {
         JLabel lblBanner = new JLabel("");
         lblBanner.setIcon(new ImageIcon(RunWsSearch.class.getResource("/framedecoration/modis-atmosphere612x30.png")));
         contentPanel.add(lblBanner, "1, 2, 7, 1, left, default");
      }
      {
         JLabel lblTextProiect = new JLabel("Proiect");
         lblTextProiect.setFont(new Font("Tahoma", Font.PLAIN, 18));
         lblTextProiect.setBorder(new EmptyBorder(5, 5, 5, 5));
         contentPanel.add(lblTextProiect, "4, 4, fill, fill");
      }
      {
         lblPleaseOpenProiect = new JLabel("alege proiect search modis ...");
         lblPleaseOpenProiect.setFont(new Font("Tahoma", Font.PLAIN, 18));
         lblPleaseOpenProiect.setBorder(new EmptyBorder(5, 5, 5, 5));
         contentPanel.add(lblPleaseOpenProiect, "5, 4, 3, 1");
      }
      {
         JButton btnOpenProiect = new JButton("Alege Proiect MS");
         btnOpenProiect.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
               sc.postLogMessage("Start selectie proiect pentru WS run");
               JFileChooser chooser = new JFileChooser();
               if (sc.getConfig().containsKey(Sc.CURRENT_WORKSPACE)) {
                  chooser.setCurrentDirectory(new java.io.File(sc.getConfig().getString(Sc.CURRENT_WORKSPACE)));
               } else {
                  chooser.setCurrentDirectory(new java.io.File("."));
                  sc.getConfig().addProperty(Sc.HDF_STORAGE_LOCATION, ".");
               }
               chooser.setDialogTitle("Select Modis Search Proiect (-mq.properties).");
               chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
               chooser.setFileFilter(new FileFilter() {

                  @Override
                  public String getDescription() {
                     return "Fisiere pentru search (-mq.properties)";
                  }

                  @Override
                  public boolean accept(File f) {
                     return StringUtils.endsWith(f.getName(), "-mq.properties");
                  }
               });
               //
               // disable the "All files" option.
               //
               chooser.setAcceptAllFileFilterUsed(false);
               //
               if (chooser.showOpenDialog(RunWsSearch.this) == JFileChooser.APPROVE_OPTION) {
                  String mfile = chooser.getSelectedFile().getAbsolutePath();
                  try {
                     searchConfig.setFile(chooser.getSelectedFile());
                     searchConfig.load();
                     sc.postLogMessage("Deschid fisier " + mfile + ".");
                     sc.postLogMessage("Proiect search: {}.", searchConfig.getString(Sc.PROJECT_NAME));
                  } catch (ConfigurationException e1) {
                     e1.printStackTrace();
                  }
               } else {
                  sc.postLogMessage("No selection.");
               }
            }
         });
         btnOpenProiect.setToolTipText("Alege proiect Modis search");
         contentPanel.add(btnOpenProiect, "5, 6");
      }
      {
         JLabel lblRezultate = new JLabel("Rezultate:");
         lblRezultate.setBorder(new EmptyBorder(5, 5, 5, 0));
         contentPanel.add(lblRezultate, "4, 7");
      }
      {
         JButton btnNewButton = new JButton("Start Search");
         btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
               sc.postLogMessage("Start rulare proiect input file :{}", searchConfig.getFileName());
               String javaHome = System.getProperty("java.home");
               String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
               String classpath = System.getProperty("java.class.path");
               String className = Modis04WsMain.class.getCanonicalName();
               ProcessBuilder wsproc = new ProcessBuilder(javaBin, "-cp", classpath, className, "-f",
                     searchConfig.getFile().getAbsolutePath());
               wsproc.environment().put("processname", "RunWsSearch");
               sc.runProcess(wsproc);
            }
         });
         {
            lblAlegeProiect = new JLabel("alege proiect");
            lblAlegeProiect.setBorder(new EmptyBorder(5, 5, 5, 5));
            contentPanel.add(lblAlegeProiect, "5, 7, 3, 1");
         }
         {
            JLabel lblNewLabel = new JLabel("");
            contentPanel.add(lblNewLabel, "7, 7");
         }
         btnNewButton.setBorder(new EmptyBorder(5, 5, 5, 5));
         contentPanel.add(btnNewButton, "5, 9");
      }
      {
         JPanel buttonPane = new JPanel();
         buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
         getContentPane().add(buttonPane, BorderLayout.SOUTH);
         {
            JButton okButton = new JButton("OK");
            okButton.addActionListener(new OkButtonActionListener());
            okButton.setActionCommand("OK");
            buttonPane.add(okButton);
            getRootPane().setDefaultButton(okButton);
         }
         {
            JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(new CancelButtonActionListener());
            cancelButton.setActionCommand("Cancel");
            buttonPane.add(cancelButton);
         }
      }
   }

   protected JLabel getLblPleaseOpenProiect() {
      return lblPleaseOpenProiect;
   }

   protected JLabel getLblAlegeProiect() {
      return lblAlegeProiect;
   }
}
