/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc2server;

/**
 *
 * @author Udmin
 */
import com.sun.rowset.JdbcRowSetImpl;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.RowSet;
import javax.sql.rowset.JdbcRowSet;

/**
 *
 * @author Lava
 */
class DBConn {
    
    private static String username = "root";
    private static String password = "";//"12345";
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/rc2database?useSSL=false";
    public static JdbcRowSet rowSet;
	
    public static void setConnection() {
        try {
            Class.forName(driver);
            // Create a row set 
			rowSet = new JdbcRowSetImpl();
			// Set RowSet properties
			rowSet.setUrl(url);
			rowSet.setUsername(username);
			rowSet.setPassword(password);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
