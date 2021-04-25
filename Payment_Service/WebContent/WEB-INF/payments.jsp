<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


     <form id="formPayment" name="formPayment" method="post" action="payments.jsp">
 
<br> First Name:
<input id="firstName" name="firstName" type="text"
 class="form-control form-control-sm">
<br> Last Name:
<input id="lastName" name="lastName" type="text"
 class="form-control form-control-sm">
<br> Amount:
<input id="payAmount" name="payAmount" type="text"
 class="form-control form-control-sm">
<br>
<br> Card Number:
<input id="cardNum" name="cardNum" type="text"
 class="form-control form-control-sm">
<br>
<br> Date:
<input id="date" name="date" type="text"
 class="form-control form-control-sm">
<br>
<br> CVV:
<input id="cvv" name="cvv" type="text"
 class="form-control form-control-sm">
<br>

<input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
</form>







</body>
</html>