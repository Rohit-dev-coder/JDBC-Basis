
package jdbcsamples;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class jdbc_5 {
    public static void main(String[] args) throws IOException{
        Connection conn = null;
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver loaded Successfully");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//mypc-PC:1521/xe","advjavabatch","students");
            System.out.println("Connected Successfully to the DB");
            Scanner kb = new Scanner(System.in);
            int ans = 0;
            char choice;
            do{
                PreparedStatement ps = conn.prepareStatement("insert into allbooks values (?,?,?,?)");
                System.out.println("Enter Book Id: ");
                int id = kb.nextInt();
                ps.setInt(1,id);
                
                System.out.println("Enter Book Name: ");
                kb.nextLine();
                String bn = kb.nextLine();
                ps.setString(2,bn);
                
                System.out.println("Enter Book price");
                int bp = kb.nextInt();
                ps.setInt(3,bp);
                
                System.out.println("Enter Book Subject: ");
                kb.nextLine();
                String bsub = kb.nextLine();
                ps.setString(4, bsub);
                ans = ps.executeUpdate()+ans;
                System.out.println("Insert more press Y/N");
                choice = (char) System.in.read();
            }while(choice != 'N');
            
            System.out.println("Row updated: "+ans);
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
