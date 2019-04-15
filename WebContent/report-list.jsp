<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>TEST REPORT MANAGEMENT</title>
<meta charset="ISO-8859-1">
<title>Test Reports</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/css/foundation.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/js/foundation.min.js"></script>
</head>
<body>
	<center>
		<h1>Test Reports Management</h1>
		<h2>
			<form action="TestReportServlet" method="get"
				enctype="multipart/form-data">
				<input type="submit" class="button" value="ADD"> <input
					type="hidden" name="operation" value="add" />
			</form>

			<form action="TestReportServlet" method="get"
				enctype="multipart/form-data">
				<input type="submit" class="button" value="LIST"> <input
					type="hidden" name="operation" value="list" />
			</form>
			
			<form action="homepage.jsp" method="get" enctype="multipart/form-data">
			<input type="submit" class="button" value="HOME">
		</form>	

		</h2>
	</center>

	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>List of Test Reports</h2>
			</caption>
			<tr>
				<th>ID</th>
				<th>Check-In Id</th>
				<th>Patient Name</th>
				<th>Test Id</th>
				<th>Test Name</th>
				<th>Result</th>
			</tr>
			<c:forEach var="rep" items="${reports}">
				<tr>
					<td><c:out value="${rep.id}" /></td>
					<td><c:out value="${rep.checkin_id}" /></td>
					<td><c:out value="${rep.patient_name}" /></td>
					<td><c:out value="${rep.test_id}" /></td>
					<td><c:out value="${rep.test_name}" /></td>
					<td><c:out value="${rep.result}" /></td>
					<td>
						<form action="TestReportServlet" method="get"
							enctype="multipart/form-data">
							<button type="submit" name="button" value="EDIT"><a>EDIT</a></button>
							<input type="hidden" name="operation" value="update" /> <input
								type="hidden" name="id" value='${rep.id}' />
						</form>
					</td>
					<td>
						<form action="TestReportServlet" method="get"
							enctype="multipart/form-data">
							<button type="submit" name="button" value="DELETE"><a>DELETE</a></button>
							<input type="hidden" name="operation" value="delete" /> <input
								type="hidden" name="id" value='${rep.id}' />
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>