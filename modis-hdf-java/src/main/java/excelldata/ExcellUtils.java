package excelldata;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

/**
 * Diverse utilitare pentru intelegerea datelor
 *
 * @author GEO-Track iDX264
 */
public abstract class ExcellUtils {
   public static final String TEXT_LINIE_FINALA = "";

   public static Integer readIntegerValue(Row row, int i) {
      Long temp = readLongValue(row, i);
      if (temp != null) {
         return temp.intValue();
      }
      return null;
   }

   public static Long readLongValue(Row row, int i) {
      if (row == null) {
         return null;
      }
      if (row.getCell(i) != null) {
         Cell cel0 = row.getCell(i);
         switch (cel0.getCellType()) {
         case Cell.CELL_TYPE_BLANK:
            return null;
         case Cell.CELL_TYPE_BOOLEAN:
            return null;
         case Cell.CELL_TYPE_NUMERIC:
            return (long) cel0.getNumericCellValue();
         case Cell.CELL_TYPE_STRING:
            return NumberUtils.toLong(cel0.getStringCellValue());
         case Cell.CELL_TYPE_ERROR:
            System.out.println("Cell type gresit:" + cel0.getCellType());
            return null;
         case Cell.CELL_TYPE_FORMULA:
            switch (cel0.getCachedFormulaResultType()) {
            case Cell.CELL_TYPE_BLANK:
               return null;
            case Cell.CELL_TYPE_BOOLEAN:
               return null;
            case Cell.CELL_TYPE_NUMERIC:
               return (long) cel0.getNumericCellValue();
            case Cell.CELL_TYPE_STRING:
               return NumberUtils.toLong(cel0.getStringCellValue());
            case Cell.CELL_TYPE_ERROR:
               System.out.println("Cell type gresit:" + cel0.getCellType());
               return null;
            default:
               break;
            }
            return null;
         default:
            break;
         }
      }
      return null;
   }

   public static Long readLongValueWithDefault(Row row, int i, long defVal) {
      if (row == null) {
         return defVal;
      }
      if (row.getCell(i) != null) {
         Cell cel0 = row.getCell(i);
         switch (cel0.getCellType()) {
         case Cell.CELL_TYPE_BLANK:
            return defVal;
         case Cell.CELL_TYPE_BOOLEAN:
            return defVal;
         case Cell.CELL_TYPE_NUMERIC:
            return (long) cel0.getNumericCellValue();
         case Cell.CELL_TYPE_STRING:
            return NumberUtils.toLong(cel0.getStringCellValue());
         case Cell.CELL_TYPE_ERROR:
            System.out.println("Cell type gresit:" + cel0.getCellType());
            return defVal;
         case Cell.CELL_TYPE_FORMULA:
            switch (cel0.getCachedFormulaResultType()) {
            case Cell.CELL_TYPE_BLANK:
               return defVal;
            case Cell.CELL_TYPE_BOOLEAN:
               return defVal;
            case Cell.CELL_TYPE_NUMERIC:
               return (long) cel0.getNumericCellValue();
            case Cell.CELL_TYPE_STRING:
               return NumberUtils.toLong(cel0.getStringCellValue());
            case Cell.CELL_TYPE_ERROR:
               System.out.println("Cell type gresit:" + cel0.getCellType());
               return defVal;
            default:
               break;
            }
            return defVal;
         default:
            break;
         }
      }
      return defVal;
   }

   public static Date readTimeValueWithDefault(Row row, int i, Date defVal) {
      if (row == null) {
         return defVal;
      }
      if (row.getCell(i) != null) {
         Cell cel0 = row.getCell(i);
         switch (cel0.getCellType()) {
         case Cell.CELL_TYPE_BLANK:
            return defVal;
         case Cell.CELL_TYPE_BOOLEAN:
            return defVal;
         case Cell.CELL_TYPE_NUMERIC:
            if (DateUtil.isCellDateFormatted(cel0)) {
               return cel0.getDateCellValue();
            } else {
               return defVal;
            }
         case Cell.CELL_TYPE_STRING:
            try {
               return new SimpleDateFormat("HH:mm").parse(cel0.getStringCellValue());
            } catch (ParseException e) {
               e.printStackTrace();
               return defVal;
            }
         case Cell.CELL_TYPE_ERROR:
            System.out.println("Cell type gresit:" + cel0.getCellType());
            return defVal;
         case Cell.CELL_TYPE_FORMULA:
            switch (cel0.getCachedFormulaResultType()) {
            case Cell.CELL_TYPE_BLANK:
               return defVal;
            case Cell.CELL_TYPE_BOOLEAN:
               return defVal;
            case Cell.CELL_TYPE_NUMERIC:
               if (DateUtil.isCellDateFormatted(cel0)) {
                  return cel0.getDateCellValue();
               } else {
                  return defVal;
               }
            case Cell.CELL_TYPE_STRING:
               try {
                  return new SimpleDateFormat("HH:mm").parse(cel0.getStringCellValue());
               } catch (ParseException e) {
                  e.printStackTrace();
                  return defVal;
               }
            case Cell.CELL_TYPE_ERROR:
               System.out.println("Cell type gresit:" + cel0.getCellType());
               return defVal;
            default:
               break;
            }
            return defVal;
         default:
            break;
         }
      }
      return defVal;
   }

   public static Date readTimeValueWithDefault(Row row, int i, Date defVal, TimeZone timeZone) {
      if (row == null) {
         return defVal;
      }
      if (row.getCell(i) != null) {
         Cell cel0 = row.getCell(i);
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
         simpleDateFormat.setTimeZone(timeZone);
         switch (cel0.getCellType()) {
         case Cell.CELL_TYPE_BLANK:
            return defVal;
         case Cell.CELL_TYPE_BOOLEAN:
            return defVal;
         case Cell.CELL_TYPE_NUMERIC:
            if (DateUtil.isCellDateFormatted(cel0)) {
               return cel0.getDateCellValue();
            } else {
               return defVal;
            }
         case Cell.CELL_TYPE_STRING:
            try {
               return simpleDateFormat.parse(cel0.getStringCellValue());
            } catch (ParseException e) {
               e.printStackTrace();
               return defVal;
            }
         case Cell.CELL_TYPE_ERROR:
            System.out.println("Cell type gresit:" + cel0.getCellType());
            return defVal;
         case Cell.CELL_TYPE_FORMULA:
            switch (cel0.getCachedFormulaResultType()) {
            case Cell.CELL_TYPE_BLANK:
               return defVal;
            case Cell.CELL_TYPE_BOOLEAN:
               return defVal;
            case Cell.CELL_TYPE_NUMERIC:
               if (DateUtil.isCellDateFormatted(cel0)) {
                  return cel0.getDateCellValue();
               } else {
                  return defVal;
               }
            case Cell.CELL_TYPE_STRING:
               try {
                  return simpleDateFormat.parse(cel0.getStringCellValue());
               } catch (ParseException e) {
                  e.printStackTrace();
                  return defVal;
               }
            case Cell.CELL_TYPE_ERROR:
               System.out.println("Cell type gresit:" + cel0.getCellType());
               return defVal;
            default:
               break;
            }
            return defVal;
         default:
            break;
         }
      }
      return defVal;
   }

   public static Date readDateValueWithDefault(Row row, int i, Date defVal) {
      if (row == null) {
         return defVal;
      }
      if (row.getCell(i) != null) {
         Cell cel0 = row.getCell(i);
         switch (cel0.getCellType()) {
         case Cell.CELL_TYPE_BLANK:
            return defVal;
         case Cell.CELL_TYPE_BOOLEAN:
            return defVal;
         case Cell.CELL_TYPE_NUMERIC:
            if (DateUtil.isCellDateFormatted(cel0)) {
               return cel0.getDateCellValue();
            } else {
               return defVal;
            }
         case Cell.CELL_TYPE_STRING:
            try {
               return new SimpleDateFormat("dd/MM/yyyy").parse(cel0.getStringCellValue());
            } catch (ParseException e) {
               e.printStackTrace();
               return defVal;
            }
         case Cell.CELL_TYPE_ERROR:
            // System.out.println("Cell type gresit:" + cel0.getCellType());
            return defVal;
         case Cell.CELL_TYPE_FORMULA:
            switch (cel0.getCachedFormulaResultType()) {
            case Cell.CELL_TYPE_BLANK:
               return defVal;
            case Cell.CELL_TYPE_BOOLEAN:
               return defVal;
            case Cell.CELL_TYPE_NUMERIC:
               if (DateUtil.isCellDateFormatted(cel0)) {
                  return cel0.getDateCellValue();
               } else {
                  return defVal;
               }
            case Cell.CELL_TYPE_STRING:
               try {
                  return new SimpleDateFormat("dd/MM/yyyy").parse(cel0.getStringCellValue());
               } catch (ParseException e) {
                  e.printStackTrace();
                  return defVal;
               }
            case Cell.CELL_TYPE_ERROR:
               System.out.println("Cell type gresit:" + cel0.getCellType());
               return defVal;
            default:
               break;
            }
            return defVal;
         default:
            break;
         }
      }
      return defVal;
   }

   public static BigDecimal readDoubleValueOrZero(Row row, int i) {
      BigDecimal retval = null;
      try {
         retval = readDoubleValue(row, i);
      } catch (Exception e) {
         // e.printStackTrace();
      }
      if (retval == null) {
         return BigDecimal.ZERO;
      }
      return retval;
   }

   public static Double readDouble(Row row, int i) {
      BigDecimal gasit = null;
      try {
         gasit = readDoubleValue(row, i);
      } catch (Exception e) {
         //
      }
      if (gasit != null) {
         return gasit.doubleValue();
      }
      return null;
   }

   public static Double readDoubleOrZero(Row row, int i) throws Exception {
      BigDecimal gasit = readDoubleValueOrZero(row, i);
      if (gasit != null) {
         return gasit.doubleValue();
      }
      return 0d;
   }

   public static Boolean readBoolean(Row row, int i) throws Exception {
      if (row == null) {
         return null;
      }
      if (row.getCell(i) != null) {
         Cell cel0 = row.getCell(i);
         switch (cel0.getCellType()) {
         case Cell.CELL_TYPE_BLANK:
            return null;
         case Cell.CELL_TYPE_BOOLEAN:
            return cel0.getBooleanCellValue();
         case Cell.CELL_TYPE_NUMERIC:
            return null;
         case Cell.CELL_TYPE_STRING:
            if (StringUtils.isNotBlank(cel0.getStringCellValue())) {
               return new Boolean(cel0.getStringCellValue().trim());
            } else {
               return null;
            }
         case Cell.CELL_TYPE_FORMULA:
            switch (cel0.getCachedFormulaResultType()) {
            case Cell.CELL_TYPE_BLANK:
               return null;
            case Cell.CELL_TYPE_BOOLEAN:
               return cel0.getBooleanCellValue();
            case Cell.CELL_TYPE_NUMERIC:
               return null;
            case Cell.CELL_TYPE_STRING:
               return new Boolean(cel0.getStringCellValue().trim());
            case Cell.CELL_TYPE_ERROR:
               System.out.println("Cell type gresit:" + cel0.getCellType());
               return null;
            default:
               break;
            }
            return null;
         default:
            break;
         }
      }
      return null;
   }

   public static BigDecimal readDoubleValue(Row row, int i) throws Exception {
      if (row == null) {
         return null;
      }
      if (row.getCell(i) != null) {
         Cell cel0 = row.getCell(i);
         try {
            switch (cel0.getCellType()) {
            case Cell.CELL_TYPE_BLANK:
               return null;
            case Cell.CELL_TYPE_BOOLEAN:
               return null;
            case Cell.CELL_TYPE_NUMERIC:
               return new BigDecimal(cel0.getNumericCellValue(), new MathContext(12));
            case Cell.CELL_TYPE_STRING:
               if (StringUtils.isNotBlank(cel0.getStringCellValue())) {
                  return new BigDecimal(cel0.getStringCellValue().trim(), new MathContext(12));
               } else {
                  return null;
               }
            case Cell.CELL_TYPE_FORMULA:
               switch (cel0.getCachedFormulaResultType()) {
               case Cell.CELL_TYPE_BLANK:
                  return null;
               case Cell.CELL_TYPE_BOOLEAN:
                  return null;
               case Cell.CELL_TYPE_NUMERIC:
                  return new BigDecimal(cel0.getNumericCellValue());
               case Cell.CELL_TYPE_STRING:
                  return new BigDecimal(cel0.getStringCellValue().trim());
               case Cell.CELL_TYPE_ERROR:
                  System.out.println("Cell type gresit:" + cel0.getCellType());
                  return null;
               default:
                  break;
               }
               return null;
            default:
               break;
            }
         } catch (NumberFormatException nf) {
            throw new Exception("Format numar gresit [" + i + "] val=" + row.getCell(i));
         }
      }
      return null;
   }

   public static String readStringValue(Row row, int i) {
      if (row == null) {
         return null;
      }
      if (row.getCell(i) != null) {
         Cell cel0 = row.getCell(i);
         return readStringFromCell(cel0);
      }
      return null;
   }

   public static String readStringFromCell(Cell cel0) {
      switch (cel0.getCellType()) {
      case Cell.CELL_TYPE_BLANK:
         return null;
      case Cell.CELL_TYPE_BOOLEAN:
         return null;
      case Cell.CELL_TYPE_NUMERIC:
         return cel0.getNumericCellValue() + "";
      case Cell.CELL_TYPE_STRING:
         return cel0.getStringCellValue().trim();
      default:
         break;
      }
      return null;
   }

   public static boolean maiMareCa0(BigDecimal nr) {
      if (nr == null) {
         return false;
      }
      if (nr.compareTo(BigDecimal.ZERO) > 0) {
         return true;
      }
      return false;
   }

   public static boolean isEmpty(BigDecimal nr) {
      if (nr == null) {
         return true;
      }
      if (nr.compareTo(BigDecimal.ZERO) == 0) {
         return true;
      }
      return false;
   }

   public static MathContext matC() {
      return new MathContext(10, RoundingMode.HALF_UP);
   }

   public static boolean diferitDe1(BigDecimal nr) {
      if (nr == null) {
         return false;
      }
      if (nr.compareTo(BigDecimal.ONE) != 0) {
         return true;
      }
      return false;
   }

   public static BigDecimal sumaArray(BigDecimal[] lista) {
      BigDecimal retval = BigDecimal.ZERO;
      for (BigDecimal aval : lista) {
         if (aval != null) {
            retval = retval.add(aval);
         }
      }
      return retval;
   }

   public static String arrayToString(ArrayList<String> stringList) {
      if (stringList == null) {
         return "";
      }
      StringBuilder ret = new StringBuilder();
      for (int i = 0; i < stringList.size(); i++) {
         ret.append(stringList.get(i));
         if (i + 1 < stringList.size()) {
            ret.append(",");
         }
      }
      return ret.toString();
   }
}
