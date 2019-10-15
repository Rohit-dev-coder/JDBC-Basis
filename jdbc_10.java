
package jdbcsamples;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class jdbc_10 {
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
            ArrayList <String> booklist = new ArrayList <>();
            ResultSet rs = st.executeQuery("Select bookprice,subject,bookname from allbooks");          
            while (rs.next())
            {
                String subject = rs.getString(2);
                if(subject.equalsIgnoreCase("python"))
                {
                    double currprice = rs.getDouble(1);
                    double newprice = currprice + currprice * 0.1;
                    rs.updateDouble(1, newprice);
                    rs.updateRow();
                    String bname = rs.getString(3);
                    booklist.add(bname);
                }
            }
            System.out.println("Book updated: "+booklist.size());
            if(booklist.size()>0)
            {
                System.out.println("Thier names are :");
                for(String s: booklist)
                {
                    System.out.println(s);
                }
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
