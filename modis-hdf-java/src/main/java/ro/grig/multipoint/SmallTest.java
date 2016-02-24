package ro.grig.multipoint;

public class SmallTest {

   public static void main(String[] args) {
      DatabaseTools dbt = new DatabaseTools();
      try {
         dbt.start();
      } catch (Exception e) {
         e.printStackTrace();
         System.exit(1);
      }
      System.out.println(dbt.generateDaoInsert());
   }
}
