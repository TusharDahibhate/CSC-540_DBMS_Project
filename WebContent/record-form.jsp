<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Wolf Hospital</title>
<meta charset="ISO-8859-1">
    <title>Medical Records</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/css/foundation.min.css">
    <script src="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/js/foundation.min.js"></script>
</head>
<body>
	<center>
		<h1>Medical Record Management</h1>
		<h2>
			Add New Medical Record &nbsp;&nbsp;&nbsp; <a href="/records">List
				All Medical Records</a>

		</h2>
	</center>
	<div align="center">
		<c:if test="${record != null}">
			<form action="MedicalRecordServlet" method="get">
				<input type="hidden" name="operation" value="modify" />
		</c:if>
		<c:if test="${record == null}">
			<form action="MedicalRecordServlet" method="get">
				<input type="hidden" name="operation" value="create" />
		</c:if>
		<table border="1" cellpadding="5">
			<caption>
				<h2>
					<c:if test="${record != null}">
               Edit Record
              </c:if>
					<c:if test="${record == null}">
               Add New Record
              </c:if>
				</h2>
			</caption>
			<c:if test="${record != null}">
				<input type="hidden" name="id"
					value="<c:out value='${record.id}' />" />
			</c:if>

			<c:if test="${record == null}">
				<tr>
					<th>Test Id</th>
					<td><input type="number" name="id" size="45"
						value="<c:out value='${record.id}' />" /></td>
				</tr>
			</c:if>
			<tr>
				<th>Diagnosis:</th>
				<td><input type="text" name="diagnosis" size="45"
					value="<c:out value='${record.diagnosis}' />" /></td>
			</tr>
			<tr>
				<th>Check-In:</th>
				<td><select name="checkin_id">
						<c:forEach var="checkin" items="${checkins}">
							<c:if test="${record.checkin_id == checkin.id}">
								<option value='${checkin.id}' selected>${checkin.id}</option>
							</c:if>
							<c:if test="${record.checkin_id != checkin.id}">
								<option value='${checkin.id}'>${checkin.id}</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<th>Staff Id:</th>
				<td><select name="staff_id">
						<c:forEach var="staff" items="${staffs}">
							<c:if test="${record.staff_id == staff.id}">
								<option value='${staff.id}' selected>${staff.id}</option>
							</c:if>
							<c:if test="${record.staff_id != staff.id}">
								<option value='${staff.id}'>${staff.id}</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Save" /></td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>