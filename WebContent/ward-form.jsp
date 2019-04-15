<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ward Management</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/css/foundation.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/js/foundation.min.js"></script>
</head>
<body>
	<div style="text-align: center">
		<h2>Ward Management</h2>
		<form action="WardServlet" method="get" enctype="multipart/form-data">
			<input type="submit" class="button" value="ADD"> <input
				type="hidden" name="operation" value="ADD" />
		</form>

		<form action="WardServlet" method="get" enctype="multipart/form-data">
			<input type="submit" class="button" value="LIST"> <input
				type="hidden" name="operation" value="LIST" />
		</form>
		
		<form action="homepage.jsp" method="get" enctype="multipart/form-data">
			<input type="submit" class="button" value="HOME">
		</form>	
	</div>
	<div align="center">
		<c:if test="${ward != null}">
			<form action="WardServlet" method="post">
				<input type="hidden" name="operation" value="UPDATE" />
		</c:if>
		<c:if test="${ward == null}">
			<form action="WardServlet" method="post">
				<input type="hidden" name="operation" value="INSERT" />
		</c:if>
		<table border="1" cellpadding="5">

			<caption>
				<h3>
					<c:if test="${ward != null}">
               Edit Ward
              </c:if>
					<c:if test="${ward == null}">
               Add New Ward
              </c:if>
				</h3>
			</caption>

			<c:if test="${ward != null}">
				<input type="hidden" name="id" value="<c:out value='${ward.id}' />" />
			</c:if>

			<c:if test="${ward != null}">
				<tr>
					<th>Staff id</th>
					<td><input type="number" name="staff_id" size="45"
						value="<c:out value='${ward.staffId}' />" /></td>
				</tr>
			</c:if>

			<c:if test="${ward == null}">
				<tr>
					<th>Staff id</th>
					<td><input type="number" name="staff_id" size="45" value=" "></td>
				</tr>
			</c:if>

			<c:if test="${ward == null}">
				<tr>
					<th>Type:</th>
					<td><input type="number" name="type" size="15" value=""></td>
				</tr>
			</c:if>

			<c:if test="${ward != null}">
				<tr>
					<th>Type:</th>
					<td><input type="number" name="type" size="15"
						value="<c:out value='${ward.type}' />" /></td>
				</tr>
			</c:if>

			<tr>
				<td colspan="2" align="center"><button type="submit"
						value="Save" class="button">Submit</button></td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>