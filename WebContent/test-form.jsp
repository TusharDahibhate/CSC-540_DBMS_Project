<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>TEST MANAGEMENT</title>
<meta charset="ISO-8859-1">
<title>Tests</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/css/foundation.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/js/foundation.min.js"></script>
</head>
<body>
	<center>
		<h1>Tests Management</h1>
		<div style="text-align: center">
			<form action="TestServlet" method="get" enctype="multipart/form-data">
				<input type="submit" class="button" value="ADD"> <input
					type="hidden" name="operation" value="add" />
			</form>

			<form action="TestServlet" method="get" enctype="multipart/form-data">
				<input type="submit" class="button" value="LIST"> <input
					type="hidden" name="operation" value="list" />
			</form>
			
			<form action="homepage.jsp" method="get" enctype="multipart/form-data">
			<input type="submit" class="button" value="HOME">
		</form>	

		</div>
	</center>
	<div align="center">
		<c:if test="${test != null}">
			<form action="TestServlet" method="get">
				<input type="hidden" name="operation" value="modify" />
		</c:if>
		<c:if test="${test == null}">
			<form action="TestServlet" method="get">
				<input type="hidden" name="operation" value="create" />
		</c:if>
		<table border="1" cellpadding="5">
			<caption>
				<h3>
					<c:if test="${test != null}">
               Edit Test
              </c:if>
					<c:if test="${test == null}">
               Add New Test
              </c:if>
				</h3>
			</caption>
			<c:if test="${test != null}">
				<input type="hidden" name="id" value="<c:out value='${test.id}' />" />
			</c:if>

			<tr>
				<th>Name:</th>
				<td><input type="text" name="name" size="45"
					value="<c:out value='${test.name}' />" /></td>
			</tr>
			<tr>
				<th>Price:</th>
				<td><input type="number" name="price" size="45"
					value="<c:out value='${test.price}' />" /></td>
			</tr>
			<tr>
				<th>Staff Id:</th>
				<td><select name="staff_id">
						<c:forEach var="staff" items="${staffs}">
							<c:if test="${test.staff_id == staff.id}">
								<option value='${staff.id}' selected>${staff.id}</option>
							</c:if>
							<c:if test="${test.staff_id != staff.id}">
								<option value='${staff.id}'>${staff.id}</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button type="submit" value="Save" class="button">Submit</button></td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>