//IT18050554
//mam.inshaf


package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import util.DBconnection;
import java.sql.Connection;


public class Payment {
	
	
	
	public String insertPayment(String firstname, String lastname, String ammount, String cardnumber, String date, String cvv) {
		
		String output = "";
		 try
		 {
		 Connection con = DBconnection.connect();
		 if (con == null)
		 {return "Error while connecting to the database for inserting."; 
		 }
		 // create a prepared statement
		 String query = " insert into payments (`paymentID`,`firstname`,`lastname`,`ammount`,`cardnumber`,`date`,`cvv`)"+ " values (?, ?, ?, ?, ?, ?,?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, firstname);
		 preparedStmt.setString(3, lastname);
		 preparedStmt.setDouble(4, Double.parseDouble(ammount));
		 preparedStmt.setString(5, cardnumber);
		 preparedStmt.setString(6, date);
		 preparedStmt.setString(7, cvv);
		 
		
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while inserting the payment.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 
		
		
		
		
		
	
	
	public String readPayments() {
		
		String output = "";
		try
		 {
			Connection con = DBconnection.connect();
		 if (con == null)
		 {
		 return "Error while connecting to the database for reading.";
		 }
		 // Prepare the html table to be displayed
		 
		output = "<table border=\"1\"><tr><th>First Name</th><th>Last Name</th><th>Ammount</th><th>Card Number</th><th>Date</th><th>Cvv</th><th>Update</th><th>Remove</th></tr>";  
		 
		
		 
		 String query = "select * from payments";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 
		 
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String paymentID = Integer.toString(rs.getInt("paymentID"));
		 String firstname = rs.getString("firstname");
		 String lastname = rs.getString("lastname");
		 String ammount = Double.toString(rs.getDouble("ammount"));
		 String cardnumber = rs.getString("cardnumber");
		 String date = rs.getString("date");
		 String cvv = rs.getString("cvv");
		 
		 
		 // Add into the html table
		
		 output += "<tr><td>" + firstname + "</td>";
		 output += "<td>" + lastname + "</td>";
		 output += "<td>" + ammount + "</td>";
		 output += "<td>" + cardnumber + "</td>";
		 output += "<td>" + date + "</td>";
		 output += "<td>" + cvv + "</td>";
		 
		 // buttons
		 
		 output += "<td><input name=\"btnUpdate\" type=\"button\"value= \"Update\" "
		 		+ "class=\"btn btn-secondary\"></td>"
		 + "<td><form method=\"post\" action=\"payments.jsp\">"
		 + "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">"
		 + "<input name=\"paymentID\" type=\"hidden\" value=\"" + paymentID
		 + "\">" + "</form></td></tr>"; 
		 
		 
		 
		 
		 
		 
		 }
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 
		 } 
		 
		 catch (Exception e)
		 {
		 output = "Error while reading the payments.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		
		
			
		
		
		
	};
	
		
		
		public String deletePayment(String paymentID){
		 {
		 String output = "";
		 try
		 {
			 Connection con = DBconnection.connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for deleting."; 
		 }
		 // create a prepared statement
		 String query = "delete from payments where paymentID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(paymentID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the payment.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		
		
		
		
	};
	
	public String updatePayment(String paymentID, String firstname, String lastname, String ammount, String cardnumber, String date, String cvv)
	 {
	 String output = "";
	 try
	 {
		 Connection con = DBconnection.connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE payments SET firstname=?,lastname=?,ammount=?,cardnumber=?,date=?,cvv=? WHERE paymentID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	
	 preparedStmt.setString(1, firstname);
	 preparedStmt.setString(2, lastname);
	 preparedStmt.setDouble(3, Double.parseDouble(ammount));
	 preparedStmt.setString(4, cardnumber);
	 preparedStmt.setString(5, date);
	 preparedStmt.setString(6, cvv);
	 preparedStmt.setInt(7,Integer.parseInt(paymentID));
	 
	 
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the payment.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
}
	
	
	
	
	
	
	

	

	
	
	
	
	
	
