
package jdbcsamples;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class jdbc_12 {
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
            ArrayList<String> booklist = new ArrayList<>();
            ResultSet rs = st.executeQuery("Select bookname from allbooks");          
            while(rs.next())
            {
                String bname = rs.getString(1);
                System.out.println(bname + " Delete (Yes/No): ");
                String choice = kb.next();
                if(choice.equalsIgnoreCase("yes"))
                {
                    rs.deleteRow();
                    booklist.add(bname);
                }
            }
            System.out.println("Book Deleted: "+booklist.size());
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
