<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Patient Management</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/css/foundation.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/js/foundation.min.js"></script>
</head>
<body>
	<div style="text-align: center">
		<h2>Patient Management</h2>
		<form action="PatientServlet" method="get"
			enctype="multipart/form-data">
			<input type="submit" class="button" value="LIST"> <input
				type="hidden" name="operation" value="list" />
		</form>
		
		<form action="homepage.jsp" method="get" enctype="multipart/form-data">
			<input type="submit" class="button" value="HOME">
		</form>	
	</div>


	<div align="center">
		<c:if test="${patient != null}">
			<form action="PatientServlet" method="get">
				<input type="hidden" name="operation" value="modify" />
		</c:if>
		<c:if test="${patient == null}">
			<form action="PatientServlet" method="get">
				<input type="hidden" name="operation" value="create" />
		</c:if>
		<table border="1" cellpadding="5">
			<caption>
				<h2>
					<c:if test="${patient != null}">
               Edit patient
              </c:if>
					<c:if test="${patient == null}">
               Add New patient
              </c:if>
				</h2>
			</caption>
			<c:if test="${patient != null}">
				<input type="hidden" name="id"
					value="<c:out value='${patient.id}' />" />
			</c:if>

			<c:if test="${patient == null}">
				<tr>
					<th>Patient id</th>
					<td><input type="number" name="id" size="45"
						value="<c:out value='${patient.id}' />" /></td>
				</tr>
			</c:if>

			<tr>
				<th>Age</th>
				<td><input type="number" name="age" size="45"
					value="<c:out value='${patient.age}' />" /></td>
			</tr>
			<tr>
				<th>Name:</th>
				<td><input type="text" name="name" size="15"
					value="<c:out value='${patient.name}' />" /></td>
			</tr>
			<tr>
				<th>SSN:</th>
				<td><input type="number" name="ssn" size="15"
					value="<c:out value='${patient.ssn}' />" /></td>
			</tr>
			<tr>
				<th>Phone No:</th>
				<td><input type="text" name="phoneNo" size="15"
					value="<c:out value='${patient.phoneNo}' />" /></td>
			</tr>
			<tr>
				<th>Gender:</th>
				<td><input type="text" name="gender" size="15"
					value="<c:out value='${patient.gender}' />" /></td>
			</tr>
			<tr>
				<th>Dob:</th>
				<td><input type="date" name="dob" size="15"
					value="<c:out value='${patient.dob}' />" /></td>
			</tr>
			<tr>
				<th>Address:</th>
				<td><input type="text" name="address" size="15"
					value="<c:out value='${patient.address}' />" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button type="submit"
						value="Save" class="button">Submit</button></td>
			</tr>
		</table>
		</form>
	</div>

</body>
</html>
