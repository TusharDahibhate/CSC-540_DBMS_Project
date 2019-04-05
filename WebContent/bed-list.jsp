<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>BED MANAGEMENT</title>
</head>
<body>
	<center>
		<h1>User Management</h1>
		<h2>
			<a href="new">Add New Bed</a> &nbsp;&nbsp;&nbsp; 
			<a href="list">List All Beds</a>
		</h2>
	</center>

	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>List of Beds</h2>
			</caption>
			<tr>
				<th>ID</th>
				<th>Ward Id</th>
				<th>Rate</th>
				<th>Checkin Id</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="bed" items="${listBed}">
				<tr>
					<td><c:out value="${bed.id}" /></td>
					<td><c:out value="${bed.ward_id}" /></td>
					<td><c:out value="${bed.rate}" /></td>
					<td><c:out value="${bed.checkin_id}" /></td>
					<td><a href="edit?id=<c:out value='${bed.id}' />">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="delete?id=<c:out value='${bed.id}' />">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>