<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Re-Enter Password</title>
<link type="text/css" rel="stylesheet" href="CSS/style.css">

<link type="text/css" rel="stylesheet" href="CSS/add-student-style.css">

</head>
<body>

<h3> RE-Enter New Password </h3>
<form action="validate" method="post">
<input type="hidden" name="command" value="PASSWORD"/>
<input type="hidden" name="username" value="${username}"/>
<table>
<tr>
<td><label> New Password : </label></td>
<td><input  type="password" name="password" required="required"/></td>
</tr>

<tr>
<td><label> Re-Enter New Password : </label></td>
<td><input  type="Password" name="repassword" required="required" /></td>
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

</body>
</html>