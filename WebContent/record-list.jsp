<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>MEDICAL RECORD MANAGEMENT</title>
<meta charset="ISO-8859-1">
    <title>Medical Records</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/css/foundation.min.css">
    <script src="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/js/foundation.min.js"></script>
</head>
<body>
	<center>
		<h1>Medical Record Management</h1>
		<h2>
			<form action="MedicalRecordServlet" method="get"
				enctype="multipart/form-data">
				<input type="submit" class="button" value="ADD"> <input
					type="hidden" name="operation" value="add" />
			</form>

			<form action="MedicalRecordServlet" method="get"
				enctype="multipart/form-data">
				<input type="submit" class="button" value="LIST">
				<input type="hidden" name="operation" value="list" />
			</form>

		</h2>
	</center>

	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>List of Medical Records</h2>
			</caption>
			<tr>
				<th>ID</th>
				<th>Diagnosis</th>
				<th>Check-In Id</th>
				<th>Patient Name</th>
				<th>Doctor Id</th>
				<th>Doctor Name</th>
			</tr>
			<c:forEach var="rec" items="${records}">
				<tr>
					<td><c:out value="${rec.id}" /></td>
					<td><c:out value="${rec.diagnosis}" /></td>
					<td><c:out value="${rec.checkin_id}" /></td>
					<td><c:out value="${rec.patient_name}" /></td>
					<td><c:out value="${rec.staff_id}" /></td>
					<td><c:out value="${rec.doctor_name}" /></td>
					<td>
						<form action="MedicalRecordServlet" method="get"
							enctype="multipart/form-data">
							<button type="submit" name="button" value="EDIT"><a>EDIT</a></button>
							<input type="hidden" name="operation" value="update" /> <input
								type="hidden" name="id" value='${rec.id}' />
						</form>
					</td>
					<td>
						<form action="MedicalRecordServlet" method="get"
							enctype="multipart/form-data">
							<button type="submit" name="button" value="DELETE"><a>DELETE</a></button>
							<input type="hidden" name="operation" value="delete" /> <input
								type="hidden" name="id" value='${rec.id}' />
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>