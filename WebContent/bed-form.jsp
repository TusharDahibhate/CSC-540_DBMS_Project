<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Wolf Hospital</title>
</head>
<body>
	<center>
		<h1>Bed Management</h1>
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
		<c:if test="${bed != null}">
			<form action="BedServlet" method="post">
				<input type="hidden" name="operation" value="UPDATE" />
		</c:if>
		<c:if test="${bed == null}">
			<form action="BedServlet" method="post">
				<input type="hidden" name="operation" value="INSERT" />
		</c:if>
		<table border="1" cellpadding="5">
			<caption>
				<h2>
					<c:if test="${bed != null}">
               Edit Bed
              </c:if>
					<c:if test="${bed == null}">
               Add New Bed
              </c:if>
				</h2>
			</caption>
			<c:if test="${bed != null}">
				<input type="hidden" name="id" value="<c:out value='${bed.id}' />" />
			</c:if>

			<c:if test="${bed != null}">
				<tr>
					<th>Ward id</th>
					<td><input type="number" name="ward_id" size="45"
						value="<c:out value='${bed.wardId}' />" /></td>
				</tr>
			</c:if>

			<c:if test="${bed == null}">
				<tr>
					<th>Ward id</th>
					<td><input type="number" name="ward_id" size="45" value=" "></td>
				</tr>
			</c:if>

			<tr>
				<th>Rate</th>
				<td><input type="number" name="rate" size="45"
					value="<c:out value='${bed.rate}' />" /></td>
			</tr>

			<c:if test="${bed != null}">
				<tr>
					<th>Check in Id:</th>
					<td><input type="number" name="checkin_id" size="15"
						value="<c:out value='${bed.checkinId}' />" /></td>
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