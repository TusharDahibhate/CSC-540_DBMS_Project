<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>TEST MANAGEMENT</title>
</head>
<body>
	<center>
		<h1>Tests Management</h1>
		<h2>
			<form action="test-form.jsp" method="get" enctype="multipart/form-data">
				<button type="submit" name="button" value="create">ADD</button>
			</form>

			<form action="TestServlet" method="get" enctype="multipart/form-data">
				<button type="submit" name="button" value="list">LIST</button>
				<input type="hidden" name="operation" value="list" />
			</form>

		</h2>
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