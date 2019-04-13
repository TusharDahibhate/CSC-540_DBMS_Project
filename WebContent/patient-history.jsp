<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Patient Management</title>
</head>
<body>
	<center>
		<h1>Medical History</h1>
	</center>

	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>Medical History</h2>
			</caption>
			<tr>				
				<th>Age</th>
				<th>Name</th>				
				<th>DOB</th>
				<th>Gender</th>				
				<th>Diagnosis</th>
				<th>Staff Name</th>          	
            	<th>Start Date</th>
            	<th>End Date</th>
			</tr>
			<c:forEach var="h" items="${history}">
				<tr>					
					<td><c:out value="${h.patient.age}" /></td>
					<td><c:out value="${h.patient.name}" /></td>					
					<td><c:out value="${h.patient.dob}" /></td>
					<td><c:out value="${h.patient.gender}" /></td>									
					<td><c:out value="${h.record.diagnosis}" /></td>
					<td><c:out value="${h.staff.name}" /></td>													
	                <td><c:out value="${h.checkin.startdate}" /></td>
	                <td><c:out value="${h.checkin.enddate}" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>