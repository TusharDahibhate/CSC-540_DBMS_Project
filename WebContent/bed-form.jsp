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
			<a href="new">Add New Bed</a> &nbsp;&nbsp;&nbsp; <a href="list">List
				All Beds</a>

		</h2>
	</center>
	<div align="center">
		<c:if test="${bed != null}">
			<form action="update" method="post">
		</c:if>
		<c:if test="${bed == null}">
			<form action="insert" method="post">
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
			
			<c:if test="${bed == null}">
				<tr>
					<th> Ward id</th>
					<td><input type="number" name="ward_id" size="45"
					value="<c:out value='${bed.wardId}' />" /></td>
				</tr>
			</c:if>					
			
			<tr>
				<th>Rate</th>
				<td><input type="number" name="rate" size="45"
					value="<c:out value='${bed.rate}' />" /></td>
			</tr>
			<tr>
				<th>Check in Id:</th>
				<td><input type="number" name="checkin_id" size="15"
					value="<c:out value='${bed.checkinId}' />" /></td>
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