<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> Give The Answer</title>
<link type="text/css" rel="stylesheet" href="CSS/style.css">

<link type="text/css" rel="stylesheet" href="CSS/add-student-style.css">

</head>
<body>
<form action="validate" method="post">
<input type="hidden" name="username" value="${username}"/>
<input type="hidden" name="command" value="QUESTION"/>
<table>
<tr>
<td><label> Give the Question Answer  : </label></td>
<td><label><b>${Question}</b></label></td>
</tr>

<tr>
<td><label> Enter Answer : </label></td>
<td><input  type="text" name="answer" required="required"/></td>
</tr>

<tr>
<td><label></label></td>
</tr>

<tr>
<td><label></label></td>
<td><input  type="submit" value="Submit" class="submit"/></td>
</tr>
</table>
</form>

<p>     
<a href="NewAdmin.jsp">Create New Admin       </a>  ||   
<a href="login.jsp">   Login </a>
 </p>
</div>
</body>
</html>