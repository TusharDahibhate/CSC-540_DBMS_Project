<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Beds</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/css/foundation.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/js/foundation.min.js"></script>
</head>
<body>
	<div style="text-align: center">
		<h2>Bed Management</h2>
		<form action="BedServlet" method="get" enctype="multipart/form-data">
			<input type="submit" class="button" value="ADD">			
			<input type="hidden" name="operation" value="ADD" />
		</form>

		<form action="BedServlet" method="get" enctype="multipart/form-data">
			<input type="submit" class="button" value="LIST">
			<input type="hidden" name="operation" value="LIST" />
		</form>	
		
		<form action="homepage.jsp" method="get" enctype="multipart/form-data">
			<input type="submit" class="button" value="HOME">
		</form>		
	</div>
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
				<h3>
					<c:if test="${bed != null}">
               Edit Bed
              </c:if>
					<c:if test="${bed == null}">
               Add New Bed
              </c:if>
				</h3>
			</caption>
			<c:if test="${bed != null}">
				<input type="hidden" name="id" value="<c:out value='${bed.id}' />" />
			</c:if>

			<%-- <c:if test="${bed != null}">
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
 --%>
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

			<c:if test="${bed == null}">
				<tr>
					<th>Ward Id</th>
					<td><select name="ward_id">
							<c:forEach var="ward" items="${wards}">
								<option value='${ward.id}'>${ward.id}</option>
							</c:forEach>
					</select></td>

				</tr>
			</c:if>

			<c:if test="${bed != null}">
				<tr>
					<th>Ward Id</th>
					<td><input type="number" name="ward_id" size="45"
						value="${bed.wardId}" readonly></td>
				</tr>
			</c:if>


			<tr>
			<td colspan="2" align="center"><button type="submit" value="Save" class="button">Submit</button></td>				
			</tr>
		</table>
		</form>
	</div>
</body>
</html>