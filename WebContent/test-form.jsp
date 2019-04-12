<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Wolf Hospital</title>
</head>
<body>
	<center>
		<h1>Test Management</h1>
		<h2>
				Add New Test &nbsp;&nbsp;&nbsp; <a href="/tests">List
				All Tests</a>

		</h2>
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
				<h2>
					<c:if test="${test != null}">
               Edit Test
              </c:if>
					<c:if test="${test == null}">
               Add New Test
              </c:if>
				</h2>
			</caption>
			<c:if test="${test != null}">
				<input type="hidden" name="id" value="<c:out value='${test.id}' />" />
			</c:if>

			<c:if test="${test == null}">
				<tr>
					<th>Test Id</th>
					<td><input type="number" name="id" size="45"
						value="<c:out value='${test.id}' />" /></td>
				</tr>
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
				<td>
	            	<select name="staff_id">
						<c:forEach var="staff" items="${staffs}">
								<option value='${staff.id}'>${staff.id}</option>
						</c:forEach>
					</select>	            	
	            </td>
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