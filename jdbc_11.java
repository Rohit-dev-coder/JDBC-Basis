
package jdbcsamples;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class jdbc_11 {
    public static void main(String[] args) throws IOException{
        Connection conn = null;
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver loaded Successfully");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//mypc-PC:1521/xe","advjavabatch","students");
            System.out.println("Connected Successfully to the DB");
            Scanner kb = new Scanner(System.in);
            Statement st = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("Select a.* from allbooks a");          
            System.out.println("Enter Book id: ");
            int id = kb.nextInt();
            System.out.println("Enter Book name: ");
            kb.nextLine();
            String bname = kb.nextLine();
            System.out.println("Enter Subject: ");
            String subj = kb.nextLine();
            System.out.println("Enter Amount : ");
            double amt = kb.nextDouble();
            rs.moveToInsertRow();
            rs.updateInt(1, id);
            rs.updateString(2, bname);
            rs.updateDouble(3, amt);
            rs.updateString(4, subj);
            rs.insertRow();
            rs.moveToCurrentRow();
           
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("Cannot load the driver: "+ex);
            ex.printStackTrace();
        }
        catch(SQLException ex)
        {
            System.out.println("Problem in closing the connection "+ex);
            ex.printStackTrace();
        }
        finally
        {
            if(conn!=null)
            {
                try
                {
                    conn.close();
                    System.out.println("Disconnected Successfully from the db");
                }
                catch(SQLException ex)
                {
                    System.out.println("Problem in closing the connection "+ex);
                    ex.printStackTrace();
                }
            }
        }
      
    }
}
