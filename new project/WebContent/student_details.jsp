<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Student Details</title>

<link type="text/css" rel="stylesheet" href="CSS/style.css">
</head>
<body>

<div id="a">
	<div id="b">
		<h1>University Student Data</h1>	
	</div>
</div>

<div id="c">
	<div id="d">

<input type="button" value="Add Student"
onclick="window.location.href='add-student.jsp';return false;"
class="add-student-button"
/>

		<table>
			<tr>
			<th> First Name </th>
			<th> Last Name </th>
			<th> Email </th>
			<th> Action </th>
			</tr>
		<c:forEach var="tempstudent" items="${STUDENTS_LIST}">
			<c:url var="templink" value="studentDBcontrol">
			<c:param name="command" value="LOAD"/>
			<c:param name="studentID" value="${tempstudent.id}"/>
			</c:url>
			
			<c:url var="deletelink" value="studentDBcontrol">
			<c:param name="command" value="DELETE"/>
			<c:param name="studentID" value="${tempstudent.id}"/>
			</c:url>
			
			<tr> 
			<td>${tempstudent.fname}</td>
			<td>${tempstudent.lname}</td>
			<td>${tempstudent.email}</td>
			<td><a href="${templink}">Update</a>
			|
			<a href="${deletelink}" onclick="if(!(confirm('Are You Sure Delete The Student Recorde'))) return false">Delete</a>
			</td>
			</tr>
		</c:forEach>
		
		</table>
	</div>
</div>
</body>
</html>