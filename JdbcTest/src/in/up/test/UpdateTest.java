package in.up.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateTest {
	public static void main(String[] args) throws Exception{
		
		// load the driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver Loaded");
		
		// Connect the Database
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useSSL=false","root","2314");
		System.out.println("Databse Connected");
		
		// Update a value in the table
		PreparedStatement ps = con.prepareStatement("UPDATE register SET city=? WHERE email=?");
		ps.setString(1, "Kanpur");
		ps.setString(2, "srk@gmail.com");
		int i = ps.executeUpdate();
		
		if(i > 0) {
			System.out.println("Updated");
		} else {
			System.out.println("failed");
		}
		
		// Delete query
		PreparedStatement ps1 = con.prepareStatement("DELETE FROM register WHERE name=?");
		ps1.setString(1, "Shah Rukh Khan");
		int j = ps1.executeUpdate();
		
		if(j > 0) {
			System.out.println("Deleted");
		} else {
			System.out.println("Failed");
		}
		con.close();
		}
}
