<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Wolf Hospital</title>
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
				<h2>
					<c:if test="${ward != null}">
               Edit Ward
              </c:if>
					<c:if test="${ward == null}">
               Add New Ward
              </c:if>
				</h2>
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
				<td colspan="2" align="center"><input type="submit"
					value="Save" /></td>


			</tr>
		</table>
		</form>
	</div>
</body>
</html>