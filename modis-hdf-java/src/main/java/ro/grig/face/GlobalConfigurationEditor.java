package ro.grig.face;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.commons.lang3.StringUtils;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import ro.grig.face.util.ValidJavaIdentifierString;

@Singleton
public class GlobalConfigurationEditor extends JDialog {

   private class BtnAlegeFolderHdfActionListener implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
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
         if (chooser.showOpenDialog(GlobalConfigurationEditor.this) == JFileChooser.APPROVE_OPTION) {
            String mfile = chooser.getSelectedFile().getAbsolutePath();
            sc.getConfig().setProperty(Sc.HDF_STORAGE_LOCATION, mfile);
            sc.postLogMessage("Selected: " + mfile + ".\n");
            lblHdfstoragevalue.setText(mfile);
         } else {
            sc.postLogMessage("No selection.");
         }
      }
   }

   private class BtnSelectNewLocationActionListener implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         sc.postLogMessage("Schimba locatia curenta.\n");
         JFileChooser chooser = new JFileChooser();
         if (sc.getConfig().containsKey(Sc.CURRENT_WORKSPACE)) {
            chooser.setCurrentDirectory(new java.io.File(sc.getConfig().getString(Sc.CURRENT_WORKSPACE)));
         } else {
            chooser.setCurrentDirectory(new java.io.File("."));
         }
         chooser.setDialogTitle("Alege un folder pentru proiect");
         chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
         //
         // disable the "All files" option.
         //
         chooser.setAcceptAllFileFilterUsed(false);
         //
         if (chooser.showOpenDialog(GlobalConfigurationEditor.this) == JFileChooser.APPROVE_OPTION) {
            String mfile = chooser.getSelectedFile().getAbsolutePath();
            sc.putGlobalValue(Sc.CURRENT_WORKSPACE, mfile);
            sc.putProjectValue(Sc.CURRENT_WORKSPACE, mfile);
            sc.changeProject();
            sc.postLogMessage("Selected: " + mfile + ".\n");
            lblworkspacevalue.setText(mfile);
         } else {
            sc.postLogMessage("No selection.");
         }
      }
   }

   /**
    * 
    */
   private static final long serialVersionUID = 661151100568938353L;
   private final JPanel contentPanel = new JPanel();
   private Sc sc;
   private JLabel lblHdfstoragevalue;
   private JLabel lblworkspacevalue;
   private JTextField txtNumelastProiect;

   /**
    * Create the dialog.
    */
   @Inject
   public GlobalConfigurationEditor(Sc sc) {
      this.sc = sc;
      initialize();
   }

   protected void initialize() {
      setTitle("Proiect folders @ Grigoras Cristinel");
      setIconImage(Toolkit.getDefaultToolkit()
            .getImage(GlobalConfigurationEditor.class.getResource("/framedecoration/configure-app16x16.png")));
      setBounds(100, 100, 601, 405);
      UtilitareSwing.dialogLocationSaver(this, sc);
      getContentPane().setLayout(new BorderLayout());
      contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      getContentPane().add(contentPanel, BorderLayout.CENTER);
      contentPanel.setLayout(new FormLayout(
            new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
                  ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, },
            new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
                  FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));
      {
         JLabel lblProiect = new JLabel("Proiect");
         contentPanel.add(lblProiect, "2, 2, right, default");
      }
      {
         txtNumelastProiect = new JTextField();
         txtNumelastProiect.setInputVerifier(new ValidJavaIdentifierString(this, txtNumelastProiect,
               "Trebuie un string fara spatii sau caractere speciale"));
         String text = sc.getConfig().getString(Sc.LAST_PROIECT);
         if (StringUtils.isNotBlank(text)) {
            txtNumelastProiect.setText(text);
         }
         contentPanel.add(txtNumelastProiect, "4, 2, fill, default");
         txtNumelastProiect.setColumns(10);
      }
      {
         JLabel lblWorkspaceLocation = new JLabel("Workspace location");
         lblWorkspaceLocation.setBorder(new EmptyBorder(5, 5, 5, 5));
         contentPanel.add(lblWorkspaceLocation, "2, 4");
      }
      {
         lblworkspacevalue = new JLabel("lblWorkspaceValue");
         String text = sc.getConfig().getString(Sc.LAST_PROIECT_FOLDER);
         if (StringUtils.isNotBlank(text)) {
            lblworkspacevalue.setText(text);
         }
         lblworkspacevalue.setBorder(new EmptyBorder(5, 5, 5, 5));
         contentPanel.add(lblworkspacevalue, "4, 4");
         sc.getConfigValue(Sc.CURRENT_WORKSPACE);
      }
      {
         JButton btnSelectNewLocation = new JButton("Select new location");
         btnSelectNewLocation.addActionListener(new BtnSelectNewLocationActionListener());
         contentPanel.add(btnSelectNewLocation, "6, 4");
      }
      {
         JLabel lblHdfStorage = new JLabel("HDF storage");
         lblHdfStorage.setBorder(new EmptyBorder(5, 5, 5, 5));
         contentPanel.add(lblHdfStorage, "2, 6");
      }
      {
         lblHdfstoragevalue = new JLabel("hdfStorageValue");
         lblHdfstoragevalue.setBorder(new EmptyBorder(5, 5, 5, 5));
         contentPanel.add(lblHdfstoragevalue, "4, 6");
         lblHdfstoragevalue.setText("initi");
         String text = sc.getConfig().getString(Sc.HDF_STORAGE_LOCATION);
         if (StringUtils.isNotBlank(text)) {
            lblHdfstoragevalue.setText(text);
         }
      }
      {
         JButton btnAlegeFolderHdf = new JButton("Alege folder hdf");
         btnAlegeFolderHdf.addActionListener(new BtnAlegeFolderHdfActionListener());
         contentPanel.add(btnAlegeFolderHdf, "6, 6");
      }
      {
         JPanel buttonPane = new JPanel();
         buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
         getContentPane().add(buttonPane, BorderLayout.SOUTH);
         {
            JButton okButton = new JButton("Salveaza și Închide");
            okButton.addActionListener(new ActionListener() {

               public void actionPerformed(ActionEvent e) {
                  sc.putGlobalValue(Sc.LAST_PROIECT, StringUtils.trim(txtNumelastProiect.getText()).toUpperCase());
                  sc.putGlobalValue(Sc.HDF_STORAGE_LOCATION, StringUtils.trim(lblHdfstoragevalue.getText()));
                  sc.putGlobalValue(Sc.LAST_PROIECT_FOLDER, StringUtils.trim(lblworkspacevalue.getText()));
                  sc.changeProject();
                  sc.flushConfig();
                  GlobalConfigurationEditor.this.setVisible(false);
                  GlobalConfigurationEditor.this.dispose();
               }
            });
            okButton.setActionCommand("OK");
            buttonPane.add(okButton);
            getRootPane().setDefaultButton(okButton);
         }
      }
   }

   public JLabel getLblHdfstoragevalue() {
      return lblHdfstoragevalue;
   }

   public JLabel getLblworkspacevalue() {
      return lblworkspacevalue;
   }
}
