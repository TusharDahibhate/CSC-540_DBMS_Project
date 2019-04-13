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
		<h1>Patient Management</h1>
		<h2>
			<form action="patient-form.jsp" method="get"
				enctype="multipart/form-data">
				<button type="submit" name="button" value="create">ADD</button>
			</form>

			<form action="PatientServlet" method="get"
				enctype="multipart/form-data">
				<button type="submit" name="button" value="list">LIST</button>
				<input type="hidden" name="operation" value="list" />
			</form>
		</h2>
	</center>

	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>List of Patients</h2>
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
							<button type="submit" name="button" value="EDIT">EDIT</button>
							<input type="hidden" name="operation" value="update" /> <input
								type="hidden" name="id" value='${pat.id}' />
						</form>
					</td>
					<td>
						<form action="PatientServlet" method="get"
							enctype="multipart/form-data">
							<button type="submit" name="button" value="DELETE">DELETE</button>
							<input type="hidden" name="operation" value="delete" /> <input
								type="hidden" name="id" value='${pat.id}' />
						</form>
					</td>
					<form action="PatientServlet" method="get"
						enctype="multipart/form-data">
					<td>
						<button type="submit" name="button" value="MEDICAL_HISTORY">MEDICAL
							HISTORY</button> <input type="hidden" name="operation"
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