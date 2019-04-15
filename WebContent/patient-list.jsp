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
		<form action="patient-form.jsp" method="get"
			enctype="multipart/form-data">
			<input type="submit" class="button" value="ADD">			
		</form>

		<form action="PatientServlet" method="get"
			enctype="multipart/form-data">			
			<input type="submit" class="button" value="LIST">
			<input type="hidden" name="operation" value="list" />
		</form>
	</div>

	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h3>List of Patients</h3>
			</caption>
			<tr>
				<th>ID</th>
				<th>Age</th>
				<th>Name</th>
				<th>SSN</th>
				<th>DOB</th>
				<th>Gender</th>
				<th>Phone No</th>
				<th>Address</th>
				<th>Edit Patient</th>
				<th>Delete Patient</th>
				<th>Medical History</th>
				<th>Starting Range</th>
				<th>Ending Range</th>
			</tr>
			<c:forEach var="pat" items="${patients}">
				<tr>
					<td><c:out value="${pat.id}" /></td>
					<td><c:out value="${pat.age}" /></td>
					<td><c:out value="${pat.name}" /></td>
					<td><c:out value="${pat.ssn}" /></td>
					<td><c:out value="${pat.dob}" /></td>
					<td><c:out value="${pat.gender}" /></td>
					<td><c:out value="${pat.phoneNo}" /></td>
					<td><c:out value="${pat.address}" /></td>
					<td>
						<form action="PatientServlet" method="get"
							enctype="multipart/form-data">
							<button type="submit" name="button" value="EDIT"><a>EDIT</a></button>
							<input type="hidden" name="operation" value="update" /> <input
								type="hidden" name="id" value='${pat.id}' />
						</form>
					</td>
					<td>
						<form action="PatientServlet" method="get"
							enctype="multipart/form-data">
							<button type="submit" name="button" value="DELETE"><a>DELETE</a></button>
							<input type="hidden" name="operation" value="delete" /> <input
								type="hidden" name="id" value='${pat.id}' />
						</form>
					</td>
					<form action="PatientServlet" method="get"
						enctype="multipart/form-data">
						<td>
							<button type="submit" name="button" value="MEDICAL_HISTORY"><a>MEDICAL
								HISTORY</a></button> <input type="hidden" name="operation"
							value="medical_history" /> <input type="hidden" name="id"
							value='${pat.id}' />
						</td>
						<td><input type="date" name="start" /></td>
						<td><input type="date" name="end" /></td>
					</form>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>