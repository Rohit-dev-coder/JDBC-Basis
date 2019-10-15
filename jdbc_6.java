
package jdbcsamples;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class jdbc_6 {
    public static void main(String[] args) throws IOException{
        Connection conn = null;
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver loaded Successfully");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//mypc-PC:1521/xe","advjavabatch","students");
            System.out.println("Connected Successfully to the DB");
            Scanner kb = new Scanner(System.in);
            System.out.println("Enter Subject ID: ");
            String sid = kb.nextLine();
            PreparedStatement ps = conn.prepareStatement("Select * from allbooks where bid = ?");
            ps.setString(1, sid);
            ResultSet rs = ps.executeQuery();
            int count =0;
            while(rs.next())
            {
                count++;
                String bname = rs.getString("bookname");
                System.out.println("Book Name : "+bname);
                
                double bprice = rs.getDouble("bookprice");
                System.out.println("Book price :"+bprice);
                
                String bsuj = rs.getString("subject");
                System.out.println("Subject :"+bsuj);
            }
            if (count == 0)
                System.out.println("Book id not exits");
            
           
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
