package com;

import model.Seller;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;

import bean.Sellers;

import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Seller")
public class SellerService {

		// Create Seller Object
		Seller sellerObj = new Seller();
		
		//	 Implement the Read Seller Operation 
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readSeller()
		{
		return sellerObj.readSeller();
		}
		
		//	 Implement the Create/Insert Seller Operation 
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertSeller(@FormParam("name") String name,
				@FormParam("email") String email,
				@FormParam("phone") String phone,
				@FormParam("username") String username,
				@FormParam("password") String password)
		{
		String output = sellerObj.insertSeller(name, email, phone, username, password);
		return output;
		}
		
		//	 Implement the Update Seller Operation 
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateSeller(String sellerData)
		{
		JsonObject sellerObject = new JsonParser().parse(sellerData).getAsJsonObject();
		
		String id = sellerObject.get("id").getAsString();
		String name = sellerObject.get("name").getAsString();
		String email = sellerObject.get("email").getAsString();
		String phone = sellerObject.get("phone").getAsString();
		String username = sellerObject.get("username").getAsString();
		String password = sellerObject.get("password").getAsString();
		String output = sellerObj.updateSeller(id, name, email, phone, username, password);
		return output;
		}
		
		//	 Implement the Delete Seller Operation 
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteSeller(String sellerData)
		{
		Document doc = Jsoup.parse(sellerData, "", Parser.xmlParser());
		
		String sellerID = doc.select("sellerID").text();
		String output = sellerObj.deleteSeller(sellerID);
		return output;
		}
		
		
		// Implement the Login Seller Operation 
				@POST
				@Path("/login")
				@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
				@Produces(MediaType.TEXT_PLAIN)
				public String loginSeller(@FormParam("username") String username,
						@FormParam("password") String password) {
					
					String status = "Invalid Seller";
					
					Sellers u = new Sellers(username, password);
					
					status = Seller.sellerLogin(u);
					
					return status;
				}
		
		
	
	
}
