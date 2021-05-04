package model;

import java.sql.*;
import bean.Sellers;

public class Seller {

	    // Method to connect to the DB /----------------------------------------------------------------------------------
		private static Connection connect()
		{
		Connection con = null;
		try
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
		//Add dataBase Connection usrname and password
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_sellermanagement", "root", "root");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
		}
		
		// Insert Method /-----------------------------------------------------------------------------------------------
		public String insertSeller(String name, String email, String phone, String username, String password)
		{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for inserting."; }
		String query = " insert into sellerinfo(`id`,`name`,`email`,`phone`,`username`,`password`)"
		+ " values (?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		preparedStmt.setInt(1, 0);
		preparedStmt.setString(2, name);
		preparedStmt.setString(3, email);
		preparedStmt.setString(4, phone);
		preparedStmt.setString(5, username);
		preparedStmt.setString(6, password);
		preparedStmt.execute();
		con.close();
		output = "Inserted successfully";
		}
		catch (Exception e)
		{
		output = "Error while inserting the seller info.";
		System.err.println(e.getMessage());
		}
		return output;
		}
		
		// Read Method /--------------------------------------------------------------------------------------
		public String readSeller()
		{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for reading."; }
		
		output = "<table border='1'><tr><th>Name</th><th>E-mail</th>" +
		"<th>Phone</th>" +
		"<th>User Name</th>" +
		"<th>Password</th>" +
		"<th>Update</th><th>Remove</th></tr>";
		String query = "select * from sellerinfo";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		while (rs.next())
		{
		String id = Integer.toString(rs.getInt("id"));
		String name = rs.getString("name");
		String email = rs.getString("email");
		String phone = rs.getString("phone");
		String username = rs.getString("username");
		String password = rs.getString("password");
		
		output += "<tr><td>" + name + "</td>";
		output += "<td>" + email + "</td>";
		output += "<td>" + phone + "</td>";
		output += "<td>" + username + "</td>";
		output += "<td>" + password + "</td>";

		output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
		+ "<td><form method='post' action='login.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
		+ "<input name='id' type='hidden' value='" + id
		+ "'>" + "</form></td></tr>";
		}
		con.close();
		
		output += "</table>";
		}
		catch (Exception e)
		{
		output = "Error while reading the seller info.";
		System.err.println(e.getMessage());
		}
		return output;
		}
		
		// Update Method /---------------------------------------------------------------------------------------------
		public String updateSeller(String id, String name, String email, String phone, String username, String password)
		{
			String output = "";
			try
			{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			
			String query = "UPDATE sellerinfo SET name=?,email=?,phone=?,username=?,password=?WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, email);
			preparedStmt.setString(3, phone);
			preparedStmt.setString(4, username);
			preparedStmt.setString(5, password);
			preparedStmt.setInt(6, Integer.parseInt(id));
			
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
			}
			catch (Exception e)
			{
			output = "Error while updating the seller info.";
			System.err.println(e.getMessage());
			}
			return output;
			}
		
		// Delete Method /--------------------------------------------------------------------------------------------------
		public String deleteSeller(String sellerID)
			{
			String output = "";
			try
			{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			
			String query = "delete from sellerinfo where id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			preparedStmt.setInt(1, Integer.parseInt(sellerID));
			
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
			}
			catch (Exception e)
			{
			output = "Error while deleting the seller info.";
			System.err.println(e.getMessage());
			}
			return output;
			}
		
		// Seller login Method /--------------------------------------------------------------------------------------------------
		public static String sellerLogin(Sellers sellers) {
			String status = "Invalid";
			
			try {
				Connection con = connect();
				
				String query = "select password from sellerinfo where username = ?";
				
				PreparedStatement ps = con.prepareStatement(query);
				
				ps.setString(1, sellers.getUsername());
				
				Statement statement = con.createStatement();
				ResultSet rs = statement.executeQuery(query);
				
				while(rs.next()) {
					 if (sellers.getPassword() == rs.getString("password") ) {
						 status = "Valid Seller Login";
					 }
				
			}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return status;
			
		}
	
}
