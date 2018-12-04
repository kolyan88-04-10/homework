package main.java.com.prokopchuk.hw23;

import java.sql.*;

public class App {
    public static final String url = "";
    public static final String user = "";
    public static final String password = "";

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(url, user, password)){
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM departments");
            rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
