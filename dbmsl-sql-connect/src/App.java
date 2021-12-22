import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.*;
public class App {

   public static void main(String[] args) throws Exception {

      // variables
      Scanner in = new Scanner(System.in);
      final String url = "jdbc:mysql://127.0.0.1:3306/DBMSL_ASSIGNMENT_2";
      final String user = "anupam";
      final String password = "Anupam#0704";

      // establish the connection
      Connection con = DriverManager.getConnection(url, user, password);

      // create JDBC statement object
      Statement st = con.createStatement();

      while(true) {
         System.out.println("Select Type of Query:\n1)Insert\n2)Update\n3)Delete\n4)Exit");
         System.out.print("Input Choice: ");
         int ch = in.nextInt();

         switch (ch) {
            case 1:
               System.out.println("Insert:");
               int id = 30;
               String prof_fname = "Yann";
               String prof_lname = "LeCunn";
               int dept_id = 2;
               String designation = "Sub-HOD";
               int salary = 10000000;
               String doj = "2021-09-20";
               String email = "yle@nyu.edu";
               String phone = "9999999999";
               String city = "New York";

               String query = "INSERT INTO Professors VALUES(" + id + ",\"" + prof_fname + "\",\"" + prof_lname + "\"," + dept_id + ",\"" + designation + "\"," + salary + ",\"" + doj + "\",\"" + email + "\"," + phone + ",\"" + city + "\");";
               System.out.println(query);
               try {
                  int statx = st.executeUpdate(query);
                  System.out.println(statx);

               } catch (Exception e) {
                  //  Block of code to handle errors
                  System.out.println(e);
               }
               break;

            case 2:
               System.out.println("Update:");
               System.out.print("Column to update: ");
               String coltoupdate = in.next();
               System.out.print("New Value to insert: ");
               String newval = in.next();
               System.out.print("Condition for updating: ");
               String conditionforupdate = in.next();

               String query4update = "UPDATE Professors SET " + coltoupdate + "=" + "\""+ newval+"\""+  " WHERE " + conditionforupdate + ";";

               try {
                  int statx = st.executeUpdate(query4update);
                  System.out.println(statx);

               } catch (Exception e) {
                  //  Block of code to handle errors
                  System.out.println(e);
               }
               break;

            case 3:
               System.out.println("Delete: ");
               System.out.print("Delete Condition: ");
               String condition4delete = in.next();
               String query4delete = "DELETE FROM Professors WHERE " + condition4delete + ";";
               try {
                  int statx = st.executeUpdate(query4delete);
                  System.out.println(statx);

               } catch (Exception e) {
                  //  Block of code to handle errors
                  System.out.println(e);
               }
               break;

            case 4:
               st.close();
               con.close();
               System.exit(0);
               break;
            default:
               System.out.println("Wrong Choice Entered");

         }

      }

   } 
}