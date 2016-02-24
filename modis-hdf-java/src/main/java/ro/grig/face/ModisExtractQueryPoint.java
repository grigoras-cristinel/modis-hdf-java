package ro.grig.face;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.filechooser.FileFilter;
import javax.vecmath.Point2d;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.prefs.CsvPreference;

import com.google.inject.Inject;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import hdfextractor.StatieMeteoPoint;

/**
 * Editeaza o lista de puncte si extrage pentru ele valorile dn fisierele modis
 * puse la dispozitie
 * 
 * @author grig
 *
 */
public class ModisExtractQueryPoint extends JDialog {

   private class TableMouseListener extends MouseAdapter {

      @Override
      public void mouseReleased(final MouseEvent e) {
         int r = table.rowAtPoint(e.getPoint());
         if (r >= 0 && r < table.getRowCount()) {
            table.setRowSelectionInterval(r, r);
         } else {
            table.clearSelection();
         }
         int rowindex = table.getSelectedRow();
         if (rowindex < 0)
            return;
         if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
            JPopupMenu popup = popupMenu_1;
            mntmAdauga.setVisible(true);
            mntmSterge.setVisible(true);
            popup.show(e.getComponent(), e.getX(), e.getY());
         }
      }
   }

   private class MntmAddPointActionListener implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         logger.debug("Click location {}", panel.getClickLocation());
         logger.debug("Dimensiuni poza {}", panel.getBounds());
         int x = (int) Math.round(panel.getClickLocation().getX() - (panel.getBounds().getWidth() / 2));
         int y = (int) Math.round((panel.getBounds().getHeight() / 2) - panel.getClickLocation().getY());
         Point distFromCenter = new Point(x, y);
         logger.debug("distpoint {}", distFromCenter);
         // Gradele
         double widthDeg = (x / 256) * (360 / Math.pow(2, panel.getZoom()));
      }
   }

   private class BtnExportToCsvActionListener implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         JFileChooser chooser = new JFileChooser();
         chooser.setDialogType(JFileChooser.SAVE_DIALOG);
         if (sc.getConfig().containsKey(Sc.CURRENT_WORKSPACE)) {
            chooser.setCurrentDirectory(new java.io.File(sc.getConfig().getString(Sc.CURRENT_WORKSPACE)));
         } else {
            chooser.setCurrentDirectory(new java.io.File("."));
            sc.getConfig().addProperty(Sc.SEARCH_REZULT_STORAGE_LOCATION, ".");
         }
         chooser.setDialogTitle("Export to CSV file");
         chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
         chooser.setFileFilter(new FileFilter() {

            @Override
            public String getDescription() {
               return "Fisiere CSV export (*.csv)";
            }

            @Override
            public boolean accept(File f) {
               return StringUtils.endsWith(f.getName(), ".csv");
            }
         });
         chooser.setAcceptAllFileFilterUsed(false);
         if (chooser.showSaveDialog(ModisExtractQueryPoint.this) == JFileChooser.APPROVE_OPTION) {
            File sf = chooser.getSelectedFile();
            String mfile = chooser.getSelectedFile().getAbsolutePath();
            int confirmValue = 0;
            if (sf.exists()) {
               confirmValue = JOptionPane.showConfirmDialog(ModisExtractQueryPoint.this,
                     "Fisierul " + sf + " exista. Suprascrieti ?");
            }
            logger.debug("Confirm value = {}", confirmValue);
            if (confirmValue == JOptionPane.YES_OPTION) {
               try {
                  CsvListWriter csvr = new CsvListWriter(new FileWriter(sf), CsvPreference.EXCEL_PREFERENCE);
                  csvr.write("Nume", "Latitudine", "Longitudine");
                  ArrayList<StatieMeteoPoint> data = tableModel.getData();
                  for (StatieMeteoPoint stmp : data) {
                     csvr.write(stmp.getName(), stmp.getPoint().y, stmp.getPoint().x);
                  }
                  csvr.flush();
                  csvr.close();
                  sc.postLogMessage("EExport table to csv. File {} ", mfile);
               } catch (IOException e1) {
                  e1.printStackTrace();
               }
            }
         }
      }
   }

   private class BtnLoadFromCvsActionListener implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         JFileChooser chooser = new JFileChooser();
         if (sc.getConfig().containsKey(Sc.CURRENT_WORKSPACE)) {
            chooser.setCurrentDirectory(new java.io.File(sc.getConfig().getString(Sc.CURRENT_WORKSPACE)));
         } else {
            chooser.setCurrentDirectory(new java.io.File("."));
            sc.getConfig().addProperty(Sc.SEARCH_REZULT_STORAGE_LOCATION, ".");
         }
         chooser.setDialogTitle("Select CVS file to import.");
         chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
         chooser.setFileFilter(new FileFilter() {

            @Override
            public String getDescription() {
               return "Fisiere CSV pentru import (*.csv)";
            }

            @Override
            public boolean accept(File f) {
               return f.isDirectory() || StringUtils.endsWith(f.getName(), ".csv");
            }
         });
         chooser.setAcceptAllFileFilterUsed(false);
         if (chooser.showOpenDialog(ModisExtractQueryPoint.this) == JFileChooser.APPROVE_OPTION) {
            File sf = chooser.getSelectedFile();
            String mfile = chooser.getSelectedFile().getAbsolutePath();
            try {
               sc.postLogMessage("Deschid fisier to import points" + mfile + ".");
               CsvListReader lred = new CsvListReader(new FileReader(sf), CsvPreference.EXCEL_PREFERENCE);
               List<String> line = lred.read();
               if (line == null || line.size() != 3) {
                  JOptionPane.showMessageDialog(ModisExtractQueryPoint.this,
                        "Nu am gasit un header cu 3 coloane(Nume,Latitudine,Longitudine).");
                  return;
               }
               int ll = 1;
               ArrayList<StatieMeteoPoint> stml = new ArrayList<StatieMeteoPoint>();
               while ((line = lred.read()) != null) {
                  if (line.size() < 3) {
                     JOptionPane.showMessageDialog(ModisExtractQueryPoint.this, "Nu am gasit 3 coloane linia " + ll);
                     return;
                  }
                  try {
                     StatieMeteoPoint stmp = new StatieMeteoPoint();
                     stmp.setName(line.get(0));
                     Point2d point = new Point2d();
                     point.y = Double.parseDouble(line.get(1));
                     point.x = Double.parseDouble(line.get(2));
                     stmp.setPoint(point);
                     stml.add(stmp);
                  } catch (Exception e2) {
                     JOptionPane.showMessageDialog(ModisExtractQueryPoint.this,
                           "Exceptie " + e2.getMessage() + " linia " + ll);
                     return;
                  }
                  ll++;
               }
               lred.close();
               tableModel.clear();
               tableModel.addAll(stml);
               sc.postLogMessage("Am incarcat din {} , {} puncte", mfile, stml.size());
            } catch (Exception e1) {
               e1.printStackTrace();
            }
         } else {
            sc.postLogMessage("No file selected");
         }
      }
   }

   private class OkButtonActionListener implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         String fname = txtNumeproiect.getText() + "-pq.properties";
         sc.postLogMessage("Salveaza " + fname);
         PropertiesConfiguration prSe = new PropertiesConfiguration();
         prSe.setListDelimiter('|');
         prSe.addProperty(Sc.PROJECT_NAME, txtNumeproiect.getText());
         ArrayList<StatieMeteoPoint> data = ((PointTableModel) table.getModel()).getData();
         for (StatieMeteoPoint stmp : data) {
            prSe.addProperty(Sc.PROJECT_POINTS, stmp.toJson());
         }
         prSe.setFileName(sc.getWorkspacePath() + "/" + fname);
         try {
            prSe.save();
         } catch (ConfigurationException e1) {
            e1.printStackTrace();
         }
         ModisExtractQueryPoint.this.setVisible(false);
         ModisExtractQueryPoint.this.dispose();
      };
   }

   private class MntmAdaugaActionListener implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         ((PointTableModel) table.getModel()).addRow(new StatieMeteoPoint("New station", new Point2d()));
      }
   }

   private class MntmStergeActionListener implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         int minSelectionIndex = table.getSelectedRow();
         logger.debug("Sterg linia: {}", minSelectionIndex);
         tableModel.removeRow(minSelectionIndex);
      }
   }

   private class PopupMenuPopupMenuListener implements PopupMenuListener {

      public void popupMenuCanceled(final PopupMenuEvent e) {
      }

      public void popupMenuWillBecomeInvisible(final PopupMenuEvent e) {
      }

      public void popupMenuWillBecomeVisible(final PopupMenuEvent e) {
         int rowAtPoint = table.rowAtPoint(SwingUtilities.convertPoint(popupMenu_1, new Point(0, 0), table));
         if (rowAtPoint > -1) {
            table.setRowSelectionInterval(rowAtPoint, rowAtPoint);
         }
      }
   }

   /**
    * Logger for this class
    */
   private static final Logger logger = LoggerFactory.getLogger(ModisExtractQueryPoint.class);
   /**
    * 
    */
   private static final long serialVersionUID = 4109687083914252850L;
   Sc sc;
   private final JPanel contentPanel = new JPanel();
   private PointTableModel tableModel = new PointTableModel();
   private RXTable table;
   private JTextField textPrecizieSelectie;
   private ArrayList<Icon> pointeri = new ArrayList<>();
   private GmapJPanel panel;
   private JPopupMenu popupMenu_1;
   private JTextField txtNumeproiect;
   private JMenuItem mntmSterge;
   private JMenuItem mntmAdauga;

   /**
    * Create the dialog.
    */
   @Inject
   public ModisExtractQueryPoint(Sc sc) {
      this.sc = sc;
      initialize();
   }

   protected void initialize() {
      setMaximumSize(new Dimension(1050, 800));
      setIconImage(Toolkit.getDefaultToolkit()
            .getImage(ModisExtractQueryPoint.class.getResource("/framedecoration/point-selector-16x16.png")));
      ImageIcon ii = new ImageIcon();
      pointeri.add(ii);
      setTitle("MODIS Point Query Builder @ Grigoraș Cristinel");
      setBounds(100, 100, 956, 648);
      UtilitareSwing.dialogLocationSaver(this, sc);
      addComponentListener(new ComponentAdapter() {

         @Override
         public void componentResized(ComponentEvent e) {
            Dimension size = ModisExtractQueryPoint.this.getSize();
            Dimension max = ModisExtractQueryPoint.this.getMaximumSize();
            if (size.getWidth() > max.getWidth()) {
               ModisExtractQueryPoint.this.setSize((int) max.getWidth(), (int) size.getHeight());
            }
            if (size.getHeight() > max.getHeight()) {
               ModisExtractQueryPoint.this.setSize((int) size.getWidth(), (int) max.getHeight());
            }
         }
      });
      getContentPane().setLayout(new BorderLayout());
      contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      getContentPane().add(contentPanel, BorderLayout.CENTER);
      contentPanel.setLayout(new FormLayout(
            new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("center:max(91dlu;default)"),
                  FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
                  ColumnSpec.decode("left:max(76dlu;default)"), FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
                  ColumnSpec.decode("default:grow(2)"), FormSpecs.RELATED_GAP_COLSPEC,
                  ColumnSpec.decode("max(98dlu;default)"), FormSpecs.RELATED_GAP_COLSPEC,
                  ColumnSpec.decode("max(119dlu;pref):grow"), },
            new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(141dlu;default):grow(2)"),
                  FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
                  RowSpec.decode("fill:default"), }));
      {
         /*
          * Google static key AIzaSyC-wTC0g0CQ4IlLIlsu3vhz5_i6QCqfY08
          * https://maps.googleapis.com/maps/api/staticmap?center=46.146640,24.
          * 665703&zoom=12&size=500x400
          */
         panel = new GmapJPanel();
         panel.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
            }
         });
         contentPanel.add(panel, "1, 1, 8, 2, fill, fill");
         {
            JPopupMenu popupMenu = new JPopupMenu() {

               /**
                * 
                */
               private static final long serialVersionUID = 1L;
            };
            {
               addPopup(panel, popupMenu);
            }
            {
               JMenuItem mntmAddPoint = new JMenuItem("Add point");
               mntmAddPoint.addActionListener(new MntmAddPointActionListener());
               popupMenu.add(mntmAddPoint);
            }
            panel.repaint(250);
         }
      }
      {
         JScrollPane scrollPane = new JScrollPane();
         contentPanel.add(scrollPane, "10, 2, 3, 1, fill, fill");
         {
            popupMenu_1 = new JPopupMenu();
            popupMenu_1.addPopupMenuListener(new PopupMenuPopupMenuListener());
            addPopup(scrollPane, popupMenu_1);
            {
               mntmSterge = new JMenuItem("Sterge");
               mntmSterge.addActionListener(new MntmStergeActionListener());
               mntmSterge.setVisible(false);
               popupMenu_1.add(mntmSterge);
            }
            {
               mntmAdauga = new JMenuItem("Adauga");
               mntmAdauga.addActionListener(new MntmAdaugaActionListener());
               popupMenu_1.add(mntmAdauga);
            }
         }
         {
            table = new RXTable();
            table.addMouseListener(new TableMouseListener());
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setPreferredScrollableViewportSize(new Dimension(400, 400));
            table.setToolTipText("Lista puncte penru care se extrag valorile");
            table.setModel(tableModel);
            scrollPane.setViewportView(table);
            File mfile = new File(sc.getWorkspacePath() + "/" + sc.getProjectName() + "-pq.properties");
            if (mfile.exists()) {
               try {
                  PropertiesConfiguration prf = new PropertiesConfiguration();
                  prf.setListDelimiter('|');
                  prf.load(mfile);
                  String[] manyjsons = prf.getStringArray(Sc.PROJECT_POINTS);
                  for (String sjp : manyjsons) {
                     logger.debug("Value val sjp {}", sjp);
                     StatieMeteoPoint rowp = StatieMeteoPoint.fromJson(sjp);
                     tableModel.addRow(rowp);
                  }
                  panel.setMarkers(tableModel.getData());
               } catch (ConfigurationException e1) {
                  e1.printStackTrace();
               }
            }
         }
      }
      {
         JLabel lblZoom = new JLabel("Zoom");
         lblZoom.setBorder(new EmptyBorder(5, 5, 5, 5));
         contentPanel.add(lblZoom, "2, 4");
      }
      {
         final JSpinner spinner = new JSpinner();
         spinner.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
               panel.setZoom(((Number) spinner.getValue()).intValue());
            }
         });
         spinner.setModel(new SpinnerNumberModel(5, 0, 16, 1));
         contentPanel.add(spinner, "4, 4, fill, default");
      }
      {
         JLabel lblProiect = new JLabel("Proiect");
         contentPanel.add(lblProiect, "10, 4, right, default");
      }
      {
         txtNumeproiect = new JTextField();
         String prname = sc.getConfigProiect().getString(Sc.PROJECT_NAME);
         txtNumeproiect.setText(prname);
         contentPanel.add(txtNumeproiect, "12, 4, fill, default");
         txtNumeproiect.setColumns(10);
      }
      {
         JLabel lblPrecizieDecimal = new JLabel("Precizie decimal");
         lblPrecizieDecimal.setBorder(new EmptyBorder(5, 5, 5, 5));
         lblPrecizieDecimal.setToolTipText(
               "Pecizia, distanta fata de masuratoarea din satelit care este acceptabila pentru extragerea datelor");
         contentPanel.add(lblPrecizieDecimal, "2, 6, 4, 1, right, default");
      }
      {
         textPrecizieSelectie = new JTextField();
         contentPanel.add(textPrecizieSelectie, "6, 6, fill, default");
         textPrecizieSelectie.setColumns(10);
      }
      {
         JPanel buttonPane = new JPanel();
         buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
         getContentPane().add(buttonPane, BorderLayout.SOUTH);
         {
            JButton okButton = new JButton("OK");
            okButton.addActionListener(new OkButtonActionListener());
            {
               JButton btnLoadFromCvs = new JButton("Load CSV");
               btnLoadFromCvs.setActionCommand("LoadCSV");
               btnLoadFromCvs.addActionListener(new BtnLoadFromCvsActionListener());
               {
                  JButton btnExportToCsv = new JButton("Export CSV");
                  btnExportToCsv.addActionListener(new BtnExportToCsvActionListener());
                  buttonPane.add(btnExportToCsv);
               }
               buttonPane.add(btnLoadFromCvs);
            }
            okButton.setActionCommand("OK");
            buttonPane.add(okButton);
            getRootPane().setDefaultButton(okButton);
         }
         {
            JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(new ActionListener() {

               public void actionPerformed(ActionEvent e) {
                  sc.postLogMessage("Extract cancel save query");
                  ModisExtractQueryPoint.this.setVisible(false);
                  ModisExtractQueryPoint.this.dispose();
               }
            });
            cancelButton.setActionCommand("Cancel");
            buttonPane.add(cancelButton);
         }
      }
   }

   private void addPopup(Component component, final JPopupMenu popup) {
      component.addMouseListener(new MouseAdapter() {

         public void mousePressed(MouseEvent e) {
            if (e.isPopupTrigger()) {
               showMenu(e);
            }
         }

         public void mouseReleased(MouseEvent e) {
            if (e.isPopupTrigger()) {
               showMenu(e);
            }
         }

         private void showMenu(MouseEvent e) {
            mntmSterge.setVisible(false);
            popup.show(e.getComponent(), e.getX(), e.getY());
         }
      });
   }

   protected JPopupMenu getPopupMenu() {
      return popupMenu_1;
   }

   protected JTextField getTxtNumeproiect() {
      return txtNumeproiect;
   }
}
