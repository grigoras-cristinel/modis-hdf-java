package ro.grig.face;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import hdfextractor.StatieMeteoPoint;

public class PointTableModel extends AbstractTableModel {

   /**
    * 
    */
   private static final long serialVersionUID = 8700191344326317391L;
   private ArrayList<StatieMeteoPoint> data = new ArrayList<StatieMeteoPoint>();

   public void addRow(StatieMeteoPoint point) {
      data.add(point);
      fireTableDataChanged();
   }

   public void insertRow(int row, StatieMeteoPoint point) {
      data.add(row, point);
      fireTableDataChanged();
   }

   public StatieMeteoPoint removeRow(int row) {
      StatieMeteoPoint remove = data.remove(row);
      fireTableDataChanged();
      return remove;
   }

   @Override
   public int getRowCount() {
      return data.size();
   }

   @Override
   public int getColumnCount() {
      return 3;
   }

   @Override
   public Object getValueAt(int rowIndex, int columnIndex) {
      if (rowIndex < data.size()) {
         StatieMeteoPoint line = data.get(rowIndex);
         switch (columnIndex) {
         case 0:
            return line.getName();
         case 1:
            return line.getPoint().y;
         case 2:
            return line.getPoint().x;
         default:
            break;
         }
      }
      return null;
   }

   @Override
   public String getColumnName(int column) {
      switch (column) {
      case 0:
         return "Nume punct";
      case 1:
         return "Latitudine";
      case 2:
         return "Longitudine";
      default:
         return "noname";
      }
   }

   @Override
   public Class<?> getColumnClass(int columnIndex) {
      switch (columnIndex) {
      case 0:
         return String.class;
      case 1:
         return Double.class;
      case 2:
         return Double.class;
      default:
         return String.class;
      }
   }

   @Override
   public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
      if (rowIndex < data.size()) {
         StatieMeteoPoint line = data.get(rowIndex);
         switch (columnIndex) {
         case 0:
            line.setName((String) aValue);
            break;
         case 1:
            line.getPoint().set(line.getPoint().x, (Double) aValue);
            break;
         case 2:
            line.getPoint().set((Double) aValue, line.getPoint().y);
            break;
         default:
            break;
         }
      }
   }

   @Override
   public boolean isCellEditable(int rowIndex, int columnIndex) {
      return true;
   }

   public ArrayList<StatieMeteoPoint> getData() {
      return data;
   }

   public void clear() {
      data.clear();
      fireTableDataChanged();
   }

   public void addAll(Collection<StatieMeteoPoint> nli) {
      data.addAll(nli);
   }
}