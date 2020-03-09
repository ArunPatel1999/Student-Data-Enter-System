<!DOCTYPE html>
<html>
<head>
<title> Update Student </title>

<link type="text/css" rel="stylesheet" href="CSS/style.css">

<link type="text/css" rel="stylesheet" href="CSS/add-student-style.css">

</head>
<body>
<div id="container">
<h3> Update Student Data </h3>
<form action="studentDBcontrol">
<input type="hidden" name="command" value="UPDATE"/> 
<input type="hidden" name="studentID" value="${THE_STUDENT.id}"/> 
<table>
<tbody>
<tr>
<td><label> First Name : </label></td>
<td><input  type="text" name="fname"  value="${THE_STUDENT.fname}"/></td>
</tr>

<tr>
<td><label> Last Name : </label></td>
<td><input  type="text" name="lname" value="${THE_STUDENT.lname}"/></td>
</tr>

<tr>
<td><label> Email : </label></td>
<td><input  type="text" name="email" value="${THE_STUDENT.email}"/></td>
</tr>

<tr>
<td><label></label></td>
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