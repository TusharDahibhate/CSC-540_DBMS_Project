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
			<form action="BedServlet" method="get" enctype="multipart/form-data">
				<button type="submit" name="button" value="ADD">ADD</button>
				<input type="hidden" name="operation" value="ADD" />
			</form>

			<form action="BedServlet" method="get" enctype="multipart/form-data">
				<button type="submit" name="button" value="LIST">LIST</button>
				<input type="hidden" name="operation" value="LIST" />
			</form>

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
					<td><c:out value="${bed.wardId}" /></td>
					<td><c:out value="${bed.rate}" /></td>
					<td><c:out value="${bed.checkinId}" /></td>
					<td>
						<form action="BedServlet" method="get"
							enctype="multipart/form-data">
							<button type="submit" name="button" value="EDIT">EDIT</button>
							<input type="hidden" name="operation" value="EDIT" /> <input
								type="hidden" name="id" value='${bed.id}' />
						</form>
					</td>
					<td>
						<form action="BedServlet" method="get"
							enctype="multipart/form-data">
							<button type="submit" name="button" value="DELETE">DELETE</button>
							<input type="hidden" name="operation" value="DELETE" /> <input
								type="hidden" name="id" value='${bed.id}' />
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>