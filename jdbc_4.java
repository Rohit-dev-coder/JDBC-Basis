
package jdbcsamples;
import java.sql.*;

public class jdbc_4 {
    public static void main(String[] args) {
        Connection conn = null;
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver loaded Successfully");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//mypc-PC:1521/xe","advjavabatch","students");
            System.out.println("Connected Successfully to the DB");
            PreparedStatement ps = conn.prepareStatement("insert into allbooks values (?,?,?,?)");
            ps.setInt(1,108);
            ps.setString(2,"Rich Python");
            ps.setInt(3,550);
            ps.setString(4, "Python");
            int ans = ps.executeUpdate();
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
