<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>TEST MANAGEMENT</title>
<meta charset="ISO-8859-1">
    <title>Tests</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/css/foundation.min.css">
    <script src="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/js/foundation.min.js"></script>
</head>
<body>
	<center>
		<h1>Tests Management</h1>
		<div style="text-align:center">
			<form action="TestServlet" method="get" enctype="multipart/form-data">
				<input type="submit" class="button" value="ADD">
             <input type="hidden" name="operation" value="add" />
			</form>

			<form action="TestServlet" method="get" enctype="multipart/form-data">
				<button type="submit" name="button" value="list">LIST</button>
				<input type="hidden" name="operation" value="list" />
			</form>

		</div>
	</center>

	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>List of Tests</h2>
			</caption>
			<tr>
				<th>ID</th>
				<th>Price</th>
				<th>Name</th>
				<th>Staff Id</th>
			</tr>
			<c:forEach var="test" items="${tests}">
				<tr>
					<td><c:out value="${test.id}" /></td>
					<td><c:out value="${test.name}" /></td>
					<td><c:out value="${test.price}" /></td>
					<td><c:out value="${test.staff_id}" /></td>
					<td>
						<form action="TestServlet" method="get"
							enctype="multipart/form-data">
							<button type="submit" name="button" value="EDIT">EDIT</button>
							<input type="hidden" name="operation" value="update" /> <input
								type="hidden" name="id" value='${test.id}' />
						</form>
					</td>
					<td>
						<form action="TestServlet" method="get"
							enctype="multipart/form-data">
							<button type="submit" name="button" value="DELETE">DELETE</button>
							<input type="hidden" name="operation" value="delete" /> <input
								type="hidden" name="id" value='${test.id}' />
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>