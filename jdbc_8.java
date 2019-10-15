
package jdbcsamples;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class jdbc_8 {
    public static void main(String[] args) throws IOException{
        Connection conn = null;
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver loaded Successfully");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//mypc-PC:1521/xe","advjavabatch","students");
            System.out.println("Connected Successfully to the DB");
            Scanner kb = new Scanner(System.in);
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("Select bookname,bookprice from allbooks");
            while(rs.next())
            {
                String name = rs.getString(1);
                int pr = rs.getInt(2);
                System.out.println(name +"\t"+ pr);
            }
            System.out.println("Forward Movement done press any key to move backwards");
            System.in.read();
            while(rs.previous())
            {
                String name = rs.getString(1);
                int pr = rs.getInt(2);
                System.out.println(name +"\t"+ pr);
            }
           
           
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
