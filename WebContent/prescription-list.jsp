<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>PRESCRIPTION MANAGEMENT</title>
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
		<table border="1" cellpadding="5">
			<caption>
				<h2>List of Prescriptions</h2>
			</caption>
			<tr>
				<th>ID</th>
				<th>Quantity</th>
				<th>Medication Id</th>
				<th>Record Id</th>

				<th>Actions</th>
			</tr>
			<c:forEach var="prescription" items="${listPrescription}">
				<tr>
					<td><c:out value="${prescription.id}" /></td>
					<td><c:out value="${prescription.quantity}" /></td>
					<td><c:out value="${prescription.medicationId}" /></td>
					<td><c:out value="${prescription.recordId}" /></td>
					<td>
						<form action="PrescriptionServlet" method="get"
							enctype="multipart/form-data">
							<button type="submit" name="button" value="EDIT">EDIT</button>
							<input type="hidden" name="operation" value="EDIT" /> <input
								type="hidden" name="id" value='${prescription.id}' />
						</form>
					</td>
					<td>
						<form action="PrescriptionServlet" method="get"
							enctype="multipart/form-data">
							<button type="submit" name="button" value="DELETE">DELETE</button>
							<input type="hidden" name="operation" value="DELETE" /> <input
								type="hidden" name="id" value='${prescription.id}' />

						</form>
					</td>
					
					<td>
                        <form action="PrescriptionServlet" method="get"
                            enctype="multipart/form-data">
                            <button type="submit" name="button" value="submit">Test Transaction</button>
                            <input type="hidden" name="operation" value="testTransaction" /> <input
                                type="hidden" name="id" value='${prescription.id}' />

                        </form>
                    </td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>