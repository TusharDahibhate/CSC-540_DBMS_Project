<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>MEDICAL RECORD MANAGEMENT</title>
</head>
<body>
	<center>
		<h1>Medical Record Management</h1>
		<h2>
			<form action="record-form.jsp" method="get"
				enctype="multipart/form-data">
				<button type="submit" name="button" value="create">ADD</button>
			</form>

			<form action="MedicalRecordServlet" method="get"
				enctype="multipart/form-data">
				<button type="submit" name="button" value="list">LIST</button>
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
				<th>Staff Id</th>
			</tr>
			<c:forEach var="rec" items="${records}">
				<tr>
					<td><c:out value="${rec.id}" /></td>
					<td><c:out value="${rec.diagnosis}" /></td>
					<td><c:out value="${rec.checkin_id}" /></td>
					<td><c:out value="${rec.staff_id}" /></td>
					<td>
						<form action="MedicalRecordServlet" method="get"
							enctype="multipart/form-data">
							<button type="submit" name="button" value="EDIT">EDIT</button>
							<input type="hidden" name="operation" value="update" /> <input
								type="hidden" name="id" value='${rec.id}' />
						</form>
					</td>
					<td>
						<form action="MedicalRecordServlet" method="get"
							enctype="multipart/form-data">
							<button type="submit" name="button" value="DELETE">DELETE</button>
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