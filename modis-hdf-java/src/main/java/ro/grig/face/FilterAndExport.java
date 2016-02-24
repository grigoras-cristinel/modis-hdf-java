package ro.grig.face;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import hdfextractor.Mod04C6Constants;
import hdfextractor.Mod06C6Constants;

@Singleton
public class FilterAndExport extends JDialog {

   /**
    * Logger for this class
    */
   private static final Logger logger = LoggerFactory.getLogger(FilterAndExport.class);

   private class BtnMovetoleftActionListener implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         DefaultListModel<String> dmr = (DefaultListModel<String>) listRight.getModel();
         String elem = listRight.getSelectedValue();
         if (dmr.contains(elem)) {
            dmr.removeElement(elem);
         }
      }
   }

   private class ComboProdusSatelitItemListener implements ItemListener {

      public void itemStateChanged(final ItemEvent e) {
         if (e.getStateChange() == ItemEvent.SELECTED) {
            logger.debug("Am selectat valoarea :{}", e.getItem().toString());
            DefaultListModel<String> dmod = (DefaultListModel<String>) listLeft.getModel();
            dmod.clear();
            if ("04".equals(e.getItem().toString())) {
               String[] fieldnames = Mod04C6Constants.fieldNames;
               for (String field04 : fieldnames) {
                  dmod.addElement(field04);
               }
            } else {
               if ("06".equals(e.getItem().toString())) {
                  String[] fieldnames = Mod06C6Constants.fieldNames;
                  for (String field06 : fieldnames) {
                     dmod.addElement(field06);
                  }
               }
            }
         }
      }
   }

   private class CancelButtonActionListener implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         FilterAndExport.this.setVisible(false);
         FilterAndExport.this.dispose();
      }
   }

   private class OkButtonActionListener implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         FilterAndExport.this.setVisible(false);
         FilterAndExport.this.dispose();
      }
   }

   private class ButtonActionListener implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         DefaultListModel<String> dmr = (DefaultListModel<String>) listRight.getModel();
         String elem = listLeft.getSelectedValue();
         if (!dmr.contains(elem)) {
            dmr.addElement(elem);
         }
      }
   }

   /**
    * 
    */
   private static final long serialVersionUID = -7265584413592388996L;
   private final JPanel contentPanel = new JPanel();
   private Sc sc;
   private JTextField txtNumeproiect;
   private JList<String> listLeft;
   private JList<String> listRight;
   private JButton btMoveToRight;
   private JButton btnMovetoleft;
   private JLabel lblProdusSatelit;
   private JComboBox<String> comboProdusSatelit;
   private JScrollPane scrollPane;
   private JScrollPane scrollPane_1;

   /**
    * Create the dialog.
    */
   @Inject
   public FilterAndExport(Sc sc) {
      this.sc = sc;
      initialize();
   }

   protected void initialize() {
      setPreferredSize(new Dimension(600, 500));
      setIconImage(Toolkit.getDefaultToolkit()
            .getImage(FilterAndExport.class.getResource("/framedecoration/frameFilterAndExport.png")));
      setBounds(100, 100, 637, 493);
      UtilitareSwing.dialogLocationSaver(this, sc);
      getContentPane().setLayout(new BorderLayout());
      contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      getContentPane().add(contentPanel, BorderLayout.CENTER);
      contentPanel.setLayout(new FormLayout(
            new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(134dlu;default)"),
                  FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(40dlu;default)"), FormSpecs.RELATED_GAP_COLSPEC,
                  ColumnSpec.decode("max(111dlu;default)"), },
            new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
                  FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), }));
      {
         JLabel lblProiect = new JLabel("Proiect");
         contentPanel.add(lblProiect, "2, 2, right, default");
      }
      {
         txtNumeproiect = new JTextField();
         txtNumeproiect.setText("numeProiect");
         contentPanel.add(txtNumeproiect, "4, 2, 3, 1");
         txtNumeproiect.setColumns(10);
      }
      {
         lblProdusSatelit = new JLabel("Produs satelit");
         contentPanel.add(lblProdusSatelit, "2, 4, right, default");
      }
      {
         comboProdusSatelit = new JComboBox<String>();
         comboProdusSatelit.addItemListener(new ComboProdusSatelitItemListener());
         comboProdusSatelit.setModel(new DefaultComboBoxModel<String>(new String[] { "Select", "04", "06" }));
         contentPanel.add(comboProdusSatelit, "4, 4, fill, default");
      }
      {
         scrollPane = new JScrollPane();
         contentPanel.add(scrollPane, "2, 6, fill, fill");
         {
            listLeft = new JList<String>();
            scrollPane.setViewportView(listLeft);
            listLeft.setModel(new DefaultListModel<String>());
         }
      }
      {
         JPanel panel = new JPanel();
         contentPanel.add(panel, "4, 6, fill, center");
         {
            btMoveToRight = new JButton(">");
            btMoveToRight.setAlignmentX(Component.CENTER_ALIGNMENT);
            btMoveToRight.addActionListener(new ButtonActionListener());
            panel.setLayout(new GridLayout(2, 1, 2, 20));
            panel.add(btMoveToRight);
         }
         {
            btnMovetoleft = new JButton("<");
            btnMovetoleft.addActionListener(new BtnMovetoleftActionListener());
            btnMovetoleft.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(btnMovetoleft);
         }
      }
      {
         scrollPane_1 = new JScrollPane();
         contentPanel.add(scrollPane_1, "6, 6, fill, fill");
         {
            listRight = new JList<String>();
            scrollPane_1.setViewportView(listRight);
            listRight.setModel(new DefaultListModel<String>());
         }
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

   protected JList getListLeft() {
      return listLeft;
   }

   protected JList getListRight() {
      return listRight;
   }

   protected JButton getBtMoveToRight() {
      return btMoveToRight;
   }

   protected JButton getBtnMovetoleft() {
      return btnMovetoleft;
   }
}
