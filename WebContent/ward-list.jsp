<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>WARD MANAGEMENT</title>
</head>
<body>
	<center>
		<h1>Ward Management</h1>
		<h2>
			<form action="WardServlet" method="get" enctype="multipart/form-data">
				<button type="submit" name="button" value="ADD">ADD</button>
				<input type="hidden" name="operation" value="ADD" />
			</form>

			<form action="WardServlet" method="get" enctype="multipart/form-data">
				<button type="submit" name="button" value="LIST">LIST</button>
				<input type="hidden" name="operation" value="LIST" />
			</form>

		</h2>
	</center>

	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>List of Wards</h2>
			</caption>
			<tr>
				<th>ID</th>
				<th>Staff Id</th>
				<th>Type</th>

				<th>Actions</th>
			</tr>
			<c:forEach var="ward" items="${listWard}">
				<tr>
					<td><c:out value="${ward.id}" /></td>
					<td><c:out value="${ward.staffId}" /></td>
					<td><c:out value="${ward.type}" /></td>
					<td>
						<form action="WardServlet" method="get"
							enctype="multipart/form-data">
							<button type="submit" name="button" value="EDIT">EDIT</button>
							<input type="hidden" name="operation" value="EDIT" /> <input
								type="hidden" name="id" value='${ward.id}' />
						</form>
					</td>
					<td>
						<form action="WardServlet" method="get"
							enctype="multipart/form-data">
							<button type="submit" name="button" value="DELETE">DELETE</button>
							<input type="hidden" name="operation" value="DELETE" /> <input
								type="hidden" name="id" value='${ward.id}' />

						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>