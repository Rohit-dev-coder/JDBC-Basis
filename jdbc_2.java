
package jdbcsamples;
import java.sql.*;

public class jdbc_2 {
    public static void main(String[] args) {
        Connection conn = null;
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver loaded Successfully");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//mypc-PC:1521/xe","advjavabatch","students");
            System.out.println("Connected Successfully to the DB");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Select bookname,bookprice from allbooks");
            while(rs.next())
            {
                String name = rs.getString(1);
                int pr = rs.getInt(2);
                System.out.println(name +"\t"+pr);
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
