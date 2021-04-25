//IT18050554
//mam.inshaf

package com;

import model.Payment;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payments")
public class PaymentServiceResource {

	Payment payObj = new Payment(); 
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayments()
	 {
	 
			 return payObj.readPayments();
	 }
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String insertPayment(@FormParam("firstname") String firstname,
	 @FormParam("lastname") String lastname,
	 @FormParam("ammount") String ammount,
	 @FormParam("cardnumber") String cardnumber,
	 @FormParam("date") String date, 
	 @FormParam("cvv") String cvv )
	{
	 String output = payObj.insertPayment(firstname,  lastname,  ammount,  cardnumber,  date,  cvv);
	return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public String updatePayment(String paymentData)
	{
	//Convert the input string to a JSON object
	 JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
	 
	//Read the values from the JSON object
	 String paymentID = paymentObject.get("paymentID").getAsString();
	 String firstname = paymentObject.get("firstname").getAsString();
	 String lastname = paymentObject.get("lastname").getAsString();
	 String ammount = paymentObject.get("ammount").getAsString();
	 String cardnumber = paymentObject.get("cardnumber").getAsString();
	 String date = paymentObject.get("date").getAsString();
	 String cvv = paymentObject.get("cvv").getAsString();
	 
	 String output = payObj.updatePayment(paymentID, firstname,  lastname,  ammount,  cardnumber,  date,  cvv);
	 
	return output;
	
	
	}

	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_HTML)
	public String deletePayment(String paymentData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

	//Read the value from the element <paymentID>
	 String paymentID = doc.select("paymentID").text();
	 String output = payObj.deletePayment(paymentID);
	return output;
	}
	
}
