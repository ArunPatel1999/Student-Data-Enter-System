<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link type="text/css" rel="stylesheet" href="CSS/style.css">

<link type="text/css" rel="stylesheet" href="CSS/add-student-style.css">

</head>
<body>
<div>
<h3> Admin Login </h3>
<form action="validate" method="post">
<input type="hidden" name="command" value="LOGIN"/>
<table>
<tr>
<td><label> UserName  : </label></td>
<td><input  type="text" name="username" required="required"/></td>
</tr>

<tr>
<td><label> Password : </label></td>
<td><input  type="Password" name="password" required="required" /></td>
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
<a href="forgetpassword.jsp">   Forget Password </a>
 </p>
</div>
</body>
</html>