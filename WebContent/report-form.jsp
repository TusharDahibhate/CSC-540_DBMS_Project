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
		<c:if test="${report != null}">
			<form action="TestReportServlet" method="get">
				<input type="hidden" name="operation" value="modify" />
		</c:if>
		<c:if test="${report == null}">
			<form action="TestReportServlet" method="get">
				<input type="hidden" name="operation" value="create" />
		</c:if>
		<table border="1" cellpadding="5">
			<caption>
				<h3>
					<c:if test="${report != null}">
               Edit Test Report
              </c:if>
					<c:if test="${report == null}">
               Add New Test Report
              </c:if>
				</h3>
			</caption>
			<c:if test="${report != null}">
				<input type="hidden" name="id"
					value="<c:out value='${report.id}' />" />
			</c:if>

			<tr>
				<th>Check-In Id:</th>
				<td><select name="checkin_id">
						<c:forEach var="checkin" items="${checkins}">
							<c:if test="${report.checkin_id == checkin.id}">
								<option value='${checkin.id}' selected>${checkin.id}</option>
							</c:if>
							<c:if test="${report.checkin_id != checkin.id}">
								<option value='${checkin.id}'>${checkin.id}</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<th>Test Id:</th>
				<td><select name="test_id">
						<c:forEach var="test" items="${tests}">
							<c:if test="${report.test_id == test.id}">
								<option value='${test.id}' selected>${test.id}</option>
							</c:if>
							<c:if test="${report.test_id != test.id}">
								<option value='${test.id}'>${test.id}</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<th>Result:</th>
				<td><input type="text" name="result" size="45"
					value="<c:out value='${report.result}' />" /></td>
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