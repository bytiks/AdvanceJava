package in.sp.test;

import java.sql.*;

public class InsertTest {
    public static void main(String[] args) throws Exception {
        // Load MySQL JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("✅ JDBC Driver Loaded Successfully.");

        // Connect to the database
        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/db?useSSL=false", "root", "2314");
        System.out.println("✅ Connection to the database established.\n");

        // ----------------------------
        // 1. INSERT USING PreparedStatement (hardcoded values)
        // ----------------------------
        String query1 = "INSERT INTO register(name, email, password, gender, city) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps1 = con.prepareStatement(query1);
        ps1.setString(1, "Saif Ali Khan");
        ps1.setString(2, "saifalikhan@gmail.com");
        ps1.setString(3, "Saif");
        ps1.setString(4, "Male");
        ps1.setString(5, "Kanpur");

        int rows1 = ps1.executeUpdate();
        System.out.println(rows1 > 0
            ? "✅ Record inserted using PreparedStatement (hardcoded values)."
            : "❌ Insert failed using PreparedStatement (hardcoded).");

        // ----------------------------
        // 2. INSERT USING Statement (not recommended)
        // ----------------------------
        Statement stmt = con.createStatement();
        String query2 = "INSERT INTO register(name, email, password, gender, city) " +
                        "VALUES ('Kareena Kapoor', 'kareena@gmail.com', 'Kareena', 'Female', 'Mumbai')";
        int rows2 = stmt.executeUpdate(query2);
        System.out.println(rows2 > 0
            ? "✅ Record inserted using Statement."
            : "❌ Insert failed using Statement.");

        // ----------------------------
        // 3. INSERT USING PreparedStatement with variables
        // ----------------------------
        String name = "Shah Rukh Khan";
        String email = "srk@gmail.com";
        String password = "SRK";
        String gender = "Male";
        String city = "Mumbai";

        String query3 = "INSERT INTO register(name, email, password, gender, city) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps3 = con.prepareStatement(query3);
        ps3.setString(1, name);
        ps3.setString(2, email);
        ps3.setString(3, password);
        ps3.setString(4, gender);
        ps3.setString(5, city);

        int rows3 = ps3.executeUpdate();
        System.out.println(rows3 > 0
            ? "✅ Record inserted using PreparedStatement with variables."
            : "❌ Insert failed using PreparedStatement with variables.");

        // ----------------------------
        // Display all records in the table
        // ----------------------------
        System.out.println("\n--- 📄 Records in 'register' Table ---");
        PreparedStatement psFetch = con.prepareStatement("SELECT * FROM register");
        ResultSet rs = psFetch.executeQuery();

        while (rs.next()) {
            System.out.println(
                rs.getString("name") + " | " +
                rs.getString("email") + " | " +
                rs.getString("password") + " | " +
                rs.getString("gender") + " | " +
                rs.getString("city")
            );
        }

        // Close connection
        con.close();
        System.out.println("\n✅ Database connection closed.");
    }
}
