package com;

import model.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Products")
public class ProductService {
	
	
	    // Create Product Object
		Product productObj = new Product();
		
		//	 Implement the Read Product Operation 
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readProduct()
		{
		return productObj.readProduct();
		}
		
		//	 Implement the Create/Insert Product Operation 
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertProduct(@FormParam("productCode") String productCode,
				@FormParam("productName") String productName,
				@FormParam("productPrice") String productPrice,
				@FormParam("productDesc") String productDesc)
		{
		String output = productObj.insertProduct(productCode, productName, productPrice, productDesc);
		return output;
		}
		
		//	 Implement the Update Product Operation 
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateProduct(String productData)
		{
		JsonObject productObject = new JsonParser().parse(productData).getAsJsonObject();
		
		String productID = productObject.get("productID").getAsString();
		String productCode = productObject.get("productCode").getAsString();
		String productName = productObject.get("productName").getAsString();
		String productPrice = productObject.get("productPrice").getAsString();
		String productDesc = productObject.get("productDesc").getAsString();
		String output = productObj.updateProduct(productID, productCode, productName, productPrice, productDesc);
		return output;
		}
		
		//	 Implement the Delete Product Operation 
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteProduct(String productData)
		{
		Document doc = Jsoup.parse(productData, "", Parser.xmlParser());
		
		String productID = doc.select("productID").text();
		String output = productObj.deleteProduct(productID);
		return output;
		}


}
