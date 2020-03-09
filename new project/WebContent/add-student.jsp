<!DOCTYPE html>
<html>
<head>
<title> Add Student </title>

<link type="text/css" rel="stylesheet" href="CSS/style.css">

<link type="text/css" rel="stylesheet" href="CSS/add-student-style.css">

</head>
<body>
<div id="container">
<h3> Add Student </h3>
<form action="studentDBcontrol">
<input type="hidden" name="command" value="ADD"/> 
<table>
<tbody>
<tr>
<td><label> First Name : </label></td>
<td><input  type="text" name="fname"/></td>
</tr>

<tr>
<td><label> Last Name : </label></td>
<td><input  type="text" name="lname"/></td>
</tr>

<tr>
<td><label> Email : </label></td>
<td><input  type="text" name="email"/></td>
</tr>

<tr>
<td><label><label></label></label></td>
<td><input  type="submit" value="Save" class="save"/></td>
</tr>

</tbody>

</table>
</form>
<p>
<a href="studentDBcontrol"> Back To List </a>
</p>
</div>

</body>
</html>