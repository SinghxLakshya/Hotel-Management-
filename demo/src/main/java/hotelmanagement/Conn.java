package hotelmanagement;

import java.sql.*;

public class Conn {

    public Connection c;
    public Statement s;

    public Conn() {
        try {
            // Fix 1: Fixed capitalization in driver class name
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Fix 2: Removed extra slash before database name
            c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hotelmanagementsystem", 
                "root", 
                "Lakshya1610w"
            );

            s = c.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}