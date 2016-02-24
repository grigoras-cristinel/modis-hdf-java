package ro.grig.face;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

/**
 * Porneste rularea proceselor pentru extragerea din fisiere hdf a punctelor
 * 
 * @author grig
 *
 */
@Singleton
public class RunQueryExtractPoint extends JDialog {

   public String workspace;

   private class BtnncarcaFisierActionListener implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         JFileChooser chooser = new JFileChooser();
         if (sc.getConfig().containsKey(Sc.CURRENT_WORKSPACE)) {
            chooser.setCurrentDirectory(new java.io.File(sc.getConfig().getString(Sc.CURRENT_WORKSPACE)));
         } else {
            chooser.setCurrentDirectory(new java.io.File("."));
            sc.getConfig().addProperty(Sc.SEARCH_REZULT_STORAGE_LOCATION, ".");
         }
         chooser.setDialogTitle("Select Point Query file to extract.");
         chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
         chooser.setFileFilter(new FileFilter() {

            @Override
            public String getDescription() {
               return "Fisiere CSV pentru import (*.csv)";
            }

            @Override
            public boolean accept(File f) {
               return StringUtils.endsWith(f.getName(), "-pq.properties");
            }
         });
         chooser.setAcceptAllFileFilterUsed(false);
         if (chooser.showOpenDialog(RunQueryExtractPoint.this) == JFileChooser.APPROVE_OPTION) {
            File sf = chooser.getSelectedFile();
            String mfile = chooser.getSelectedFile().getAbsolutePath();
            if (sf.exists()) {
               try {
                  currentDialogProperties.setListDelimiter('|');
                  currentDialogProperties.load(sf);
                  numeProiect = currentDialogProperties.getString(Sc.PROJECT_NAME);
                  workspace = currentDialogProperties.getString(Sc.CURRENT_WORKSPACE);
                  lblNumefisier.setText(numeProiect);
                  String[] stj = currentDialogProperties.getStringArray(Sc.PROJECT_POINTS);
                  lblNumarpunctefsier.setText("" + stj.length);
                  String exportFolder = workspace + "/" + numeProiect + "_qep";
                  currentDialogProperties.addProperty(Sc.PROJECT_FOLDER_EXPORTPOINTS, exportFolder);
                  lblOutputfoldervalue.setText(exportFolder);
               } catch (ConfigurationException e1) {
                  e1.printStackTrace();
               }
            }
         }
      }
   }

   /**
    * 
    */
   private static final long serialVersionUID = 1158487180983127529L;
   public static final String RUN_QUERY_EXTRACT_POINT_LOCATION_Y = "RunQueryExtractPoint.location.y";
   public static final String RUN_QUERY_EXTRACT_POINT_LOCATION_X = "RunQueryExtractPoint.location.x";
   /**
    * Logger for this class
    */
   private static final Logger logger = LoggerFactory.getLogger(RunQueryExtractPoint.class);
   private final JPanel contentPanel = new JPanel();
   private JLabel lblFisierIncarcat;
   private JLabel lblNumarProcese;
   private Sc sc;
   private JLabel lblNumefisier;
   private JLabel lblNumarpunctefsier;
   private JLabel lblOutputfoldervalue;
   private JComboBox<Integer> selectNumarProcese;
   PropertiesConfiguration currentDialogProperties = new PropertiesConfiguration();
   protected String numeProiect;

   /**
    * Create the dialog.
    */
   @Inject
   public RunQueryExtractPoint(Sc sc) {
      this.sc = sc;
      initialize();
   }

   protected void initialize() {
      setTitle("Extract points from HDF files");
      setIconImage(Toolkit.getDefaultToolkit()
            .getImage(RunQueryExtractPoint.class.getResource("/framedecoration/run-point-extraction-16x16.png")));
      setBounds(100, 100, 892, 467);
      UtilitareSwing.dialogLocationSaver(this, sc);
      getContentPane().setLayout(new BorderLayout());
      contentPanel.setMaximumSize(new Dimension(32767, 408));
      contentPanel.setBorder(null);
      getContentPane().add(contentPanel, BorderLayout.EAST);
      contentPanel.setLayout(new FormLayout(
            new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("270px"), FormSpecs.RELATED_GAP_COLSPEC,
                  ColumnSpec.decode("max(78dlu;default)"), FormSpecs.RELATED_GAP_COLSPEC,
                  ColumnSpec.decode("max(180dlu;default):grow(2)"), FormSpecs.RELATED_GAP_COLSPEC,
                  ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, },
            new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
                  FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
                  FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(18dlu;default)"), FormSpecs.RELATED_GAP_ROWSPEC,
                  FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("fill:default:grow"),
                  FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
                  FormSpecs.DEFAULT_ROWSPEC, }));
      {
         JLabel lblImg = new JLabel("");
         lblImg.setIcon(new ImageIcon(RunQueryExtractPoint.class.getResource("/framedecoration/Cloud_Top_Height.png")));
         contentPanel.add(lblImg, "1, 1, 2, 16");
      }
      {
         JLabel lblProiect = new JLabel("Proiect");
         lblProiect.setBorder(new EmptyBorder(5, 5, 5, 5));
         lblProiect.setFont(new Font("Tahoma", Font.PLAIN, 18));
         contentPanel.add(lblProiect, "4, 2");
      }
      {
         JLabel lblValoareproiect = new JLabel("încarca fisier pentru procesare");
         lblValoareproiect.setBorder(new EmptyBorder(5, 5, 5, 5));
         lblValoareproiect.setFont(new Font("Tahoma", Font.PLAIN, 18));
         contentPanel.add(lblValoareproiect, "6, 2, 3, 1");
      }
      {
         lblFisierIncarcat = new JLabel("Fisier incarcat");
         lblFisierIncarcat.setBorder(new EmptyBorder(5, 5, 5, 5));
         contentPanel.add(lblFisierIncarcat, "4, 4");
      }
      {
         lblNumefisier = new JLabel("NumeFisier");
         lblFisierIncarcat.setLabelFor(lblNumefisier);
         contentPanel.add(lblNumefisier, "6, 4");
      }
      {
         JButton btnncarcaFisier = new JButton("Încarca fisier");
         btnncarcaFisier.addActionListener(new BtnncarcaFisierActionListener());
         contentPanel.add(btnncarcaFisier, "8, 4, center, default");
      }
      {
         JLabel lblNewLabel = new JLabel("Numar puncte");
         contentPanel.add(lblNewLabel, "4, 6");
      }
      {
         lblNumarpunctefsier = new JLabel("numarPuncteFsier");
         contentPanel.add(lblNumarpunctefsier, "6, 6");
      }
      {
         JLabel lblOutputFolder = new JLabel("Output folder");
         contentPanel.add(lblOutputFolder, "4, 8");
      }
      {
         lblOutputfoldervalue = new JLabel("outputFolderValue");
         contentPanel.add(lblOutputfoldervalue, "6, 8");
      }
      {
         lblNumarProcese = new JLabel("Numar procese");
         contentPanel.add(lblNumarProcese, "4, 10, right, default");
      }
      {
         selectNumarProcese = new JComboBox();
         selectNumarProcese.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8" }));
         contentPanel.add(selectNumarProcese, "6, 10, left, default");
         lblNumarProcese.setLabelFor(selectNumarProcese);
      }
      {
         JButton btnRun = new JButton("Run !!!");
         contentPanel.add(btnRun, "6, 14");
      }
      {
         JButton okButton = new JButton("OK");
         contentPanel.add(okButton, "8, 14");
         okButton.setActionCommand("OK");
         getRootPane().setDefaultButton(okButton);
      }
   }

   protected JLabel getLblNumefisier() {
      return lblNumefisier;
   }
}
