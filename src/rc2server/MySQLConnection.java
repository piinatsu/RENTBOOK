package rc2server;

import java.sql.*;
import javax.swing.*;

public class MySQLConnection {
    Connection conn = null;
    public static Connection ConnectDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rc2database", "root", "");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
