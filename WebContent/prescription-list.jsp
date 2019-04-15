<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Prescription Management</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/css/foundation.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/js/foundation.min.js"></script>
</head>
<body>
	<div style="text-align: center">
		<h2>Prescription List</h2>
		<form action="PrescriptionServlet" method="get"
			enctype="multipart/form-data">
			<input type="submit" class="button" value="ADD">
			<input type="hidden" name="operation" value="ADD" />
		</form>

		<form action="PrescriptionServlet" method="get"
			enctype="multipart/form-data">
			<input type="submit" class="button" value="LIST">
			<input type="hidden" name="operation" value="LIST" />
		</form>
		
		<form action="homepage.jsp" method="get" enctype="multipart/form-data">
			<input type="submit" class="button" value="HOME">
		</form>	
	</div>

	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>List of Prescriptions</h2>
			</caption>
			<tr>
				<th>ID</th>
				<th>Quantity</th>
				<th>Medication Id</th>
				<th>Record Id</th>
				<th>Edit</th>
				<th>Delete</th>
				<th>Transactions</th>
			</tr>
			<c:forEach var="prescription" items="${listPrescription}">
				<tr>
					<td><c:out value="${prescription.id}" /></td>
					<td><c:out value="${prescription.quantity}" /></td>
					<td><c:out value="${prescription.medicationId}" /></td>
					<td><c:out value="${prescription.recordId}" /></td>
					<td>
						<form action="PrescriptionServlet" method="get"
							enctype="multipart/form-data">
							<button type="submit" name="button" value="EDIT"><a>EDIT</a></button>
							<input type="hidden" name="operation" value="EDIT" /> <input
								type="hidden" name="id" value='${prescription.id}' />
						</form>
					</td>
					<td>
						<form action="PrescriptionServlet" method="get"
							enctype="multipart/form-data">
							<button type="submit" name="button" value="DELETE"><a>DELETE</a></button>
							<input type="hidden" name="operation" value="DELETE" /> <input
								type="hidden" name="id" value='${prescription.id}' />

						</form>
					</td>

					<td>
						<form action="PrescriptionServlet" method="get"
							enctype="multipart/form-data">
							<button type="submit" name="button" value="submit"><a>Test
								Transaction</a></button>
							<input type="hidden" name="operation" value="testTransaction" />
							<input type="hidden" name="id" value='${prescription.id}' />

						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>