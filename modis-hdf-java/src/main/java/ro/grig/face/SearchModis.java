package ro.grig.face;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.StringUtils;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.toedter.calendar.JDateChooser;

@Singleton
public class SearchModis extends JDialog {

   /**
    *
    */
   private static final long serialVersionUID = -5642134400614098746L;
   Sc sc;
   private final JPanel contentPanel = new JPanel();
   private JTextField textLatitudineSud;
   private JTextField textLongitudineEst;
   private JComboBox<String> comboColectia;
   private JLabel lblPerioadaSfarsit;
   private JLabel lblPerioadaStart;
   private JDateChooser dateSfarsit;
   private JDateChooser dateStart;
   private JLabel lblDenumireProiect;
   private JTextField textProiect;
   private JTextField textLatitudineNord;
   private JTextField textLongitudineVest;
   private JComboBox<String> comboProdus;
   private JComboBox<String> comboSatelit;

   /**
    * Create the dialog.
    *
    * @wbp.parser.constructor
    */
   @Inject
   public SearchModis(Sc sc) {
      this.sc = sc;
      initialize();
   }

   @SuppressWarnings({ "unchecked", "rawtypes" })
   protected void initialize() {
      setTitle("MODIS Search Builder @ Grigoras Cristinel");
      setBounds(100, 100, 921, 470);
      setMinimumSize(new Dimension(850, 480));
      UtilitareSwing.dialogLocationSaver(this, sc);
      setResizable(false);
      this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
      this.setVisible(true);
      setIconImage(
            Toolkit.getDefaultToolkit().getImage(SearchModis.class.getResource("/ro/grig/face/icons/modis-icon.png")));
      getContentPane().setLayout(new BorderLayout());
      contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      getContentPane().add(contentPanel, BorderLayout.CENTER);
      contentPanel.setLayout(new FormLayout(
            new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(61dlu;default)"),
                  FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(61dlu;default)"),
                  FormSpecs.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("max(102dlu;pref)"),
                  FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(94dlu;pref)"), FormSpecs.RELATED_GAP_COLSPEC,
                  ColumnSpec.decode("max(102dlu;pref)"), FormSpecs.RELATED_GAP_COLSPEC,
                  ColumnSpec.decode("left:max(10dlu;default):grow"), },
            new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(137dlu;default)"),
                  FormSpecs.LINE_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, RowSpec.decode("28px"),
                  FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
                  FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("fill:default"), }));
      {
         JLabel lblPoza = new JLabel("");
         lblPoza.setBackground(Color.WHITE);
         lblPoza.setIcon(new ImageIcon(SearchModis.class.getResource("/framedecoration/aqua-black-300.png")));
         contentPanel.add(lblPoza, "2, 2, 5, 1, default, top");
      }
      {
         JPanel panel = new JPanel();
         contentPanel.add(panel, "8, 2, 3, 1, fill, top");
         panel.setLayout(new GridLayout(2, 1, 5, 5));
         {
            lblDenumireProiect = new JLabel("Denumire proiect:");
            lblDenumireProiect.setPreferredSize(new Dimension(180, 30));
            lblDenumireProiect.setSize(new Dimension(100, 0));
            lblDenumireProiect.setBorder(new EmptyBorder(5, 5, 5, 5));
            lblDenumireProiect.setAlignmentX(Component.CENTER_ALIGNMENT);
            lblDenumireProiect.setFont(new Font("Tahoma", Font.PLAIN, 18));
            panel.add(lblDenumireProiect);
         }
         {
            textProiect = new JTextField();
            textProiect.setPreferredSize(new Dimension(6, 15));
            textProiect.setToolTipText("Introducere denumire proiect");
            textProiect.setFont(new Font("Tahoma", Font.PLAIN, 16));
            panel.add(textProiect);
            textProiect.setColumns(10);
         }
      }
      {
         JLabel lblNewLabel_1 = new JLabel("");
         contentPanel.add(lblNewLabel_1, "12, 2");
      }
      {
         JLabel lblProdus = new JLabel("Produs");
         lblProdus.setBorder(new EmptyBorder(0, 0, 0, 10));
         contentPanel.add(lblProdus, "2, 4, 3, 1, right, default");
      }
      {
         comboProdus = new JComboBox<String>();
         comboProdus.setMinimumSize(new Dimension(50, 20));
         comboProdus.setModel(new DefaultComboBoxModel<String>(new String[] { "04", "06" }));
         contentPanel.add(comboProdus, "6, 4");
      }
      {
         JLabel lblSatelit = new JLabel("Satelit");
         lblSatelit.setBorder(new EmptyBorder(5, 5, 5, 10));
         contentPanel.add(lblSatelit, "8, 4, right, default");
      }
      {
         comboSatelit = new JComboBox<String>();
         comboSatelit.setModel(new DefaultComboBoxModel(
               new String[] { Sc.PRODUCT_SATELIT_TERRA, Sc.PRODUCT_SATELIT_AQUA, Sc.PRODUCT_SATELIT_BOTH }));
         contentPanel.add(comboSatelit, "10, 4, fill, default");
      }
      {
         JLabel lblLatitudineSud = new JLabel("Latitudine sud");
         lblLatitudineSud.setToolTipText("Decimal degree");
         lblLatitudineSud.setBorder(new EmptyBorder(0, 0, 0, 10));
         contentPanel.add(lblLatitudineSud, "2, 5, 3, 1, right, default");
      }
      {
         textLatitudineSud = new JTextField();
         textLatitudineSud.setBorder(new EmptyBorder(5, 5, 5, 5));
         textLatitudineSud.setText("43.22");
         contentPanel.add(textLatitudineSud, "6, 5");
         textLatitudineSud.setColumns(10);
      }
      {
         JLabel lblLongitudine = new JLabel("Longitudine est");
         lblLongitudine.setBorder(new EmptyBorder(5, 5, 0, 10));
         contentPanel.add(lblLongitudine, "8, 5, right, default");
      }
      {
         textLongitudineEst = new JTextField();
         textLongitudineEst.setBorder(new EmptyBorder(5, 5, 5, 5));
         textLongitudineEst.setText("30.04");
         contentPanel.add(textLongitudineEst, "10, 5, fill, default");
         textLongitudineEst.setColumns(10);
      }
      {
         JLabel lblNewLabel = new JLabel("Latitudine nord");
         lblNewLabel.setBorder(new EmptyBorder(5, 5, 5, 10));
         contentPanel.add(lblNewLabel, "2, 7, 3, 1, right, default");
      }
      {
         textLatitudineNord = new JTextField();
         textLatitudineNord.setText("48.54");
         textLatitudineNord.setBorder(new EmptyBorder(5, 5, 0, 0));
         contentPanel.add(textLatitudineNord, "6, 7, fill, default");
         textLatitudineNord.setColumns(10);
      }
      {
         JLabel lblLongitudineVest = new JLabel("Longitudine vest");
         lblLongitudineVest.setBorder(new EmptyBorder(5, 5, 5, 10));
         contentPanel.add(lblLongitudineVest, "8, 7, right, default");
      }
      {
         textLongitudineVest = new JTextField();
         textLongitudineVest.setText("19.80");
         textLongitudineVest.setBorder(new EmptyBorder(5, 5, 5, 5));
         contentPanel.add(textLongitudineVest, "10, 7, fill, default");
         textLongitudineVest.setColumns(10);
      }
      {
         lblPerioadaStart = new JLabel("Perioada start");
         lblPerioadaStart.setBorder(new EmptyBorder(3, 3, 3, 10));
         contentPanel.add(lblPerioadaStart, "2, 9, 3, 1, right, default");
      }
      {
         dateStart = new JDateChooser();
         dateStart.setPreferredSize(new Dimension(81, 20));
         dateStart.setDateFormatString("dd MM yyyy");
         lblPerioadaStart.setLabelFor(dateStart);
         contentPanel.add(dateStart, "6, 9, fill, center");
      }
      {
         lblPerioadaSfarsit = new JLabel("Perioada sfarsit");
         lblPerioadaSfarsit.setBorder(new EmptyBorder(5, 5, 5, 10));
         Font font = lblPerioadaSfarsit.getFont();
         Map attributes = font.getAttributes();
         attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
         lblPerioadaSfarsit.setFont(font.deriveFont(attributes));
         lblPerioadaSfarsit.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
               sc.postLogMessage("Open url perioada disponobila: "
                     + "http://modis-atmos.gsfc.nasa.gov/products_calendar.html" + "\n");
               try {
                  Desktop.getDesktop().browse(new URI("http://modis-atmos.gsfc.nasa.gov/products_calendar.html"));
                  sc.postLogMessage("Deschide disponibilitatea produsului 06");
               } catch (IOException e1) {
                  // TODO Auto-generated catch block
                  e1.printStackTrace();
               } catch (URISyntaxException e1) {
                  // TODO Auto-generated catch block
                  e1.printStackTrace();
               }
            }
         });
         contentPanel.add(lblPerioadaSfarsit, "8, 9, right, default");
      }
      {
         dateSfarsit = new JDateChooser();
         dateSfarsit.setDateFormatString("dd MM yyyy");
         lblPerioadaSfarsit.setLabelFor(dateSfarsit);
         contentPanel.add(dateSfarsit, "10, 9, fill, center");
      }
      {
         JLabel lblColection = new JLabel("Colection");
         lblColection.setBorder(new EmptyBorder(5, 5, 5, 10));
         contentPanel.add(lblColection, "2, 11, 3, 1, right, default");
      }
      {
         comboColectia = new JComboBox();
         comboColectia.setBorder(new CompoundBorder(null, new EmptyBorder(5, 5, 5, 5)));
         comboColectia.setToolTipText("Alegeti colectia de date");
         comboColectia.setModel(new DefaultComboBoxModel(new String[] { "06", "05" }));
         contentPanel.add(comboColectia, "6, 11, fill, default");
      }
      loadConfiguration();
      {
         JPanel buttonPane = new JPanel();
         buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
         getContentPane().add(buttonPane, BorderLayout.SOUTH);
         {
            JButton okButton = new JButton("OK");
            okButton.addActionListener(new ActionListener() {

               public void actionPerformed(ActionEvent e) {
                  if (getDataStart().getDate() == null) {
                     JOptionPane.showMessageDialog(SearchModis.this, "Completeaza data start.");
                     return;
                  }
                  if (getDataSfarsit().getDate() == null) {
                     JOptionPane.showMessageDialog(SearchModis.this, "Completeaza data sfarsit.");
                     return;
                  }
                  PropertiesConfiguration prSe = new PropertiesConfiguration();
                  String proiect = textProiect.getText().trim();
                  if (StringUtils.isBlank(proiect)) {
                     JOptionPane.showMessageDialog(SearchModis.this, "Completeaza un nume pentru proiect.");
                     return;
                  }
                  if (StringUtils.containsWhitespace(proiect)) {
                     JOptionPane.showMessageDialog(SearchModis.this, "Numele de proiect nu trebuie sa contina spatii.");
                     return;
                  }
                  Sc sci = sc;
                  File towrite = new File(sci.getConfig().getProperty(Sc.CURRENT_WORKSPACE).toString() + "/" + proiect
                        + "-mq.properties");
                  int scriu = 0;
                  if (towrite.exists()) {
                     scriu = JOptionPane.showConfirmDialog(SearchModis.this, "Proiectul deja exista, suprascriu ?");
                  }
                  if (scriu == 0) {
                     sc.putGlobalValue(Sc.LAST_PROIECT, proiect);
                     sc.putProjectValue(Sc.PROJECT_LAST_MQFILE, towrite.getAbsolutePath());
                     prSe.addProperty(Sc.PROJECT_NAME, proiect);
                     prSe.addProperty(Sc.HDF_STORAGE_LOCATION, sc.getConfig().getProperty(Sc.HDF_STORAGE_LOCATION));
                     prSe.addProperty(Sc.CURRENT_WORKSPACE,
                           sci.getConfig().getProperty(Sc.CURRENT_WORKSPACE).toString());
                     prSe.addProperty(Sc.PROJECT_SEARCH_LATITUDINE_NORD,
                           Double.valueOf(textLatitudineNord.getText()).toString());
                     prSe.addProperty(Sc.PROJECT_SEARCH_LATITUDINE_SUD,
                           Double.valueOf(textLatitudineSud.getText()).toString());
                     prSe.addProperty(Sc.PROJECT_SEARCH_LONGIT_EST,
                           Double.valueOf(textLongitudineEst.getText()).toString());
                     prSe.addProperty(Sc.PROJECT_SEARCH_LONGIT_VEST,
                           Double.valueOf(textLongitudineVest.getText()).toString());
                     prSe.addProperty(Sc.PROJECT_SEARCH_DATASTART, dateStart.getCalendar().getTimeInMillis() + "");
                     prSe.addProperty(Sc.PROJECT_SEARCH_DATAEND, dateSfarsit.getCalendar().getTimeInMillis() + "");
                     prSe.addProperty(Sc.PROJECT_SEARCH_COLECTIA, comboColectia.getSelectedItem().toString());
                     prSe.addProperty(Sc.PROJECT_SEARCH_SATELIT, comboSatelit.getSelectedItem().toString());
                     prSe.addProperty(Sc.PROJECT_SEARCH_PRODUS, comboProdus.getSelectedItem().toString());
                     if (scriu == 0) {
                        try {
                           prSe.setFile(towrite);
                           sc.getConfigProiect().copy(prSe);
                           prSe.save();
                           sc.postLogMessage("Export search setting in : {}.", towrite.getAbsolutePath());
                        } catch (ConfigurationException e1) {
                           e1.printStackTrace();
                        }
                     }
                     sc.flushConfig();
                     SearchModis.this.setVisible(false);
                     SearchModis.this.dispose();
                  }
               }
            });
            okButton.setActionCommand("OK");
            buttonPane.add(okButton);
            getRootPane().setDefaultButton(okButton);
         }
         {
            JButton cancelButton = new JButton("Cancel");
            cancelButton.setMinimumSize(new Dimension(500, 400));
            cancelButton.addActionListener(new ActionListener() {

               public void actionPerformed(ActionEvent e) {
                  SearchModis.this.setVisible(false);
                  SearchModis.this.dispose();
                  sc.postLogMessage("searchmodis.proiectsave " + textProiect.getText() + " Cancel");
               }
            });
            cancelButton.setActionCommand("Cancel");
            buttonPane.add(cancelButton);
         }
      }
   }

   private void loadConfiguration() {
      String prjn = sc.getConfigProiect().getString(Sc.PROJECT_NAME);
      this.textProiect.setText(prjn);
      if (sc.getConfigProiect().containsKey(Sc.PROJECT_SEARCH_LATITUDINE_NORD)) {
         Double pr1 = sc.getConfigProiect().getDouble(Sc.PROJECT_SEARCH_LATITUDINE_NORD);
         this.textLatitudineNord.setText(pr1.toString());
      }
      if (sc.getConfigProiect().containsKey(Sc.PROJECT_SEARCH_LATITUDINE_SUD)) {
         Double pr2 = sc.getConfigProiect().getDouble(Sc.PROJECT_SEARCH_LATITUDINE_SUD);
         this.textLatitudineSud.setText(pr2.toString());
      }
      if (sc.getConfigProiect().containsKey(Sc.PROJECT_SEARCH_LONGIT_EST)) {
         Double pr3 = sc.getConfigProiect().getDouble(Sc.PROJECT_SEARCH_LONGIT_EST);
         this.textLongitudineEst.setText(pr3.toString());
      }
      if (sc.getConfigProiect().containsKey(Sc.PROJECT_SEARCH_LONGIT_VEST)) {
         Double pr4 = sc.getConfigProiect().getDouble(Sc.PROJECT_SEARCH_LONGIT_VEST);
         this.textLongitudineVest.setText(pr4.toString());
      }
      if (sc.getConfigProiect().containsKey(Sc.PROJECT_SEARCH_COLECTIA)) {
         String vc = sc.getConfigProiect().getString(Sc.PROJECT_SEARCH_COLECTIA);
         comboColectia.getModel().setSelectedItem(vc);
      }
      if (sc.getConfigProiect().containsKey(Sc.PROJECT_SEARCH_PRODUS)) {
         String vc = sc.getConfigProiect().getString(Sc.PROJECT_SEARCH_PRODUS);
         comboProdus.getModel().setSelectedItem(vc);
      }
      if (sc.getConfigProiect().containsKey(Sc.PROJECT_SEARCH_SATELIT)) {
         String vc = sc.getConfigProiect().getString(Sc.PROJECT_SEARCH_SATELIT);
         comboSatelit.getModel().setSelectedItem(vc);
      }
      if (sc.getConfigProiect().containsKey(Sc.PROJECT_SEARCH_DATASTART)) {
         long vc = sc.getConfigProiect().getLong(Sc.PROJECT_SEARCH_DATASTART);
         Calendar c = Calendar.getInstance();
         c.setTimeInMillis(vc);
         dateStart.setCalendar(c);
      }
      if (sc.getConfigProiect().containsKey(Sc.PROJECT_SEARCH_DATAEND)) {
         long vc = sc.getConfigProiect().getLong(Sc.PROJECT_SEARCH_DATAEND);
         Calendar c = Calendar.getInstance();
         c.setTimeInMillis(vc);
         dateSfarsit.setCalendar(c);
      }
   }

   public JDateChooser getDataSfarsit() {
      return dateSfarsit;
   }

   public JDateChooser getDataStart() {
      return dateStart;
   }

   public JComboBox getComboProdus() {
      return comboProdus;
   }

   public JComboBox getComboSatelit() {
      return comboSatelit;
   }
}
