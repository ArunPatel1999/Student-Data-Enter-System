<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title> Add New Admin </title>

<link type="text/css" rel="stylesheet" href="CSS/style.css">

<link type="text/css" rel="stylesheet" href="CSS/add-student-style.css">

</head>
<body>
<div id="container">
<h3> Enter New Admin </h3>
<form action="validate" method="post">
<input type="hidden" name="command" value="ADD"/>
<table>
<tbody>
<tr>
<td><label> User Name : </label></td>
<td><input  type="text" name="username" required="required"/></td>
</tr>

<tr>
<td><label> Full Name : </label></td>
<td><input  type="text" name="name" required="required"/></td>
</tr>


<tr>
<td><label> Password : </label></td>
<td><input  type="password" name="password" required="required"/></td>
</tr>

<tr>
<td><label> Re-Enter Password : </label></td>
<td><input  type="password" name="repassword" required="required"/></td>
</tr>

<tr>
<td><label> Chose One : </label></td>
<td><select name="question">
<option value="1">Enter Your Home Name</option>
<option value="2">Enter Your First Phone Number</option>
<option value="3">Enter Your Best Teacher Name</option>
<option value="4">Enter Your Favorite Song Name</option>
</select>
</tr>

<tr>
<td><label> Enter Answer : </label></td>
<td><input  type="text" name="answer" required="required"/></td>
</tr>

<tr>
<td><label><label></label></label></td>
<td><input  type="submit" value="Save" class="save"/></td>
</tr>

</tbody>

</table>
</form>
<p>
<a href="login.jsp"> Back To Login Page </a>
</p>
</div>

</body>
</html>