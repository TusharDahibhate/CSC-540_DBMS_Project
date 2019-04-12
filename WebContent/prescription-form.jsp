<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Wolf Hospital</title>
</head>
<body>
	<center>
		<h1>Prescription Management</h1>
		<h2>
			<form action="PrescriptionServlet" method="get"
				enctype="multipart/form-data">
				<button type="submit" name="button" value="ADD">ADD</button>
				<input type="hidden" name="operation" value="ADD" />
			</form>

			<form action="PrescriptionServlet" method="get"
				enctype="multipart/form-data">
				<button type="submit" name="button" value="LIST">LIST</button>
				<input type="hidden" name="operation" value="LIST" />
			</form>


		</h2>
	</center>
	<div align="center">
		<c:if test="${prescription != null}">
			<form action="PrescriptionServlet" method="post">
				<input type="hidden" name="operation" value="UPDATE" />
		</c:if>
		<c:if test="${prescription == null}">
			<form action="PrescriptionServlet" method="post">
				<input type="hidden" name="operation" value="INSERT" />
		</c:if>
		<table border="1" cellpadding="5">

			<caption>
				<h2>
					<c:if test="${prescription != null}">
               Edit Prescription
              </c:if>
					<c:if test="${prescription == null}">
               Add New Prescription
              </c:if>
				</h2>
			</caption>

			<c:if test="${prescription != null}">
				<input type="hidden" name="id"
					value="<c:out value='${prescription.id}' />" />
			</c:if>

			<c:if test="${prescription != null}">
				<tr>
					<th>Quantity</th>
					<td><input type="number" name="quantity" size="45"
						value="<c:out value='${prescription.quantity}' />" /></td>
				</tr>
			</c:if>

			<c:if test="${prescription == null}">
				<tr>
					<th>Quantity</th>
					<td><input type="number" name="quantity" size="45" value=" "></td>
				</tr>
			</c:if>

			<c:if test="${prescription == null}">
				<tr>
					<th>Medication Id:</th>
					<td><input type="number" name="medication_id" size="15"
						value=""></td>
				</tr>
			</c:if>

			<c:if test="${prescription != null}">
				<tr>
					<th>Medication Id:</th>
					<td><input type="number" name="medication_id" size="15"
						value="<c:out value='${prescription.medicationId}' />" /></td>
				</tr>
			</c:if>

			<c:if test="${prescription == null}">
				<tr>
					<th>Record Id:</th>
					<td><input type="number" name="record_id" size="15" value=""></td>
				</tr>
			</c:if>

			<c:if test="${prescription != null}">
				<tr>
					<th>Record Id:</th>
					<td><input type="number" name="record_id" size="15"
						value="<c:out value='${prescription.recordId}' />" /></td>
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