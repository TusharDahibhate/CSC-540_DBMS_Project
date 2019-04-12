<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Wolf Hospital</title>
</head>
<body>
	<center>
		<h1>Test Report Management</h1>
		<h2>
			Add New Test Report &nbsp;&nbsp;&nbsp; <a href="/TestReportServlet">List
				All Test Reports</a>

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
				<h2>
					<c:if test="${report != null}">
               Edit Test Report
              </c:if>
					<c:if test="${report == null}">
               Add New Test Report
              </c:if>
				</h2>
			</caption>
			<c:if test="${report != null}">
				<input type="hidden" name="id"
					value="<c:out value='${report.id}' />" />
			</c:if>

			<c:if test="${report == null}">
				<tr>
					<th>Test Id</th>
					<td><input type="number" name="id" size="15"
						value="<c:out value='${report.id}' />" /></td>
				</tr>
			</c:if>
			<tr>
				<th>Check-In Id:</th>
				<td><input type="number" name="checkin_id" size="15"
					value="<c:out value='${report.checkin_id}' />" /></td>
			</tr>

			<tr>
				<th>Test Id:</th>
				<td><input type="number" name="test_id" size="15"
					value="<c:out value='${report.test_id}' />" /></td>
			</tr>

			<tr>
				<th>Result:</th>
				<td><input type="text" name="result" size="45"
					value="<c:out value='${report.result}' />" /></td>
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