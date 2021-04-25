package com;

import Model.ProductCategory;

//For REST SERVICE

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For Json

import com.google.gson.*;

//for XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/")

public class ProductCategoryService {

	ProductCategory pcobject = new ProductCategory();

	// create read operation

	@GET
	@Path("/categoryandtag")
	@Produces(MediaType.TEXT_HTML)
	
	public String readCategories()
	{
		
		return pcobject.readCategories(); 
		
	}

	// create insert operation

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertCategories(@FormParam("ID") Integer ID, @FormParam("CategoryID") String CategoryID,@FormParam("CategoryName")String CategoryName, 
								@FormParam("Description") String Description,
								@FormParam("TagCode") String TagCode,
								@FormParam("TagName") String TagName)
	{
		String output = pcobject.insertCategories(ID ,CategoryID, CategoryName, Description, TagCode, TagName);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateCategories(String categoryData)
	{
		//convert the input string to a JSON object
		JsonObject Cobject = new JsonParser().parse(categoryData).getAsJsonObject();
		
		//Read the values from the JSON object
		
		String ID = Cobject.get("ID").getAsString();
		String CategoryID = Cobject.get("CategoryID").getAsString();
		String CategoryName = Cobject.get("CategoryName").getAsString();
		String Description = Cobject.get("Description").getAsString();
		String TagCode = Cobject.get("TagCode").getAsString();
		String TagName = Cobject.get("TagName").getAsString();
		
		String output = pcobject.updateCategories(ID, CategoryID, CategoryName, Description, TagCode, TagName);
		
		return output;
	
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String deleteCategories(String categoryData) {
		
		//Convert input string to an XML Document
		Document doc = Jsoup.parse(categoryData,"",Parser.xmlParser());
		
		//Read the value from the element <ID>
		
		String ID = doc.select("ID").text();
		
		String output = pcobject.deleteCategories(ID);
		
		return output;
		
		
	} 
	
	
}