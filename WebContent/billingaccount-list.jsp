<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Billing Account</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/css/foundation.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/js/foundation.min.js"></script>
</head>
<body>
	<div style="text-align: center">
		<h2>Billing Account Management</h2>
		<form action="billingaccount-form.jsp" method="get"
			enctype="multipart/form-data">			
			<input type="submit" class="button" value="CREATE">
		</form>

		<form action="BillingAccountServlet" method="get"
			enctype="multipart/form-data">			
			<input type="submit" class="button" value="LIST">
			<input type="hidden" name="operation" value="list" />
		</form>
		
		<form action="homepage.jsp" method="get" enctype="multipart/form-data">
			<input type="submit" class="button" value="HOME">
		</form>	
	</div>

	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h3>List of Billing Accounts</h3>
			</caption>
			<tr>
				<th>ID</th>
				<th>Staff_ID</th>
				<th>Checkin_ID</th>
				<th>Paid_by_Person</th>
				<th>Paid_by_Insurance</th>
				<th>Payment_info</th>
				<th>Payee_ssn</th>
				<th>Billing_Address</th>
				<th>Total_Charge</th>
			</tr>
			<c:forEach var="bac" items="${billingaccounts}">
				<tr>
					<td><c:out value="${bac.id}" /></td>
					<td><c:out value="${bac.staff_id}" /></td>
					<td><c:out value="${bac.checkin_id}" /></td>
					<td><c:out value="${bac.paid_by_person}" /></td>
					<td><c:out value="${bac.paid_by_insurance}" /></td>
					<td><c:out value="${bac.payment_info}" /></td>
					<td><c:out value="${bac.payee_ssn}" /></td>
					<td><c:out value="${bac.billing_address}" /></td>
					<td><c:out value="${bac.total_charge}" /></td>
					<td>
						<form action="BillingAccountServlet" method="get"
							enctype="multipart/form-data">
							<button type="submit" name="button" value="EDIT"><a>EDIT</a></button>
							<input type="hidden" name="operation" value="update" /> <input
								type="hidden" name="id" value='${bac.id}' />
						</form>
					</td>
					<td>
						<form action="BillingAccountServlet" method="get"
							enctype="multipart/form-data">
							<button type="submit" name="button" value="DELETE"><a>DELETE</a></button>
							<input type="hidden" name="operation" value="delete" /> <input
								type="hidden" name="id" value='${bac.id}' />
						</form>
					</td>
					<td>
						<form action="BillingAccountServlet" method="get"
							enctype="multipart/form-data">
							<button type="submit" name="button" value="submit"><a>Test
								Transaction</a></button>
							<input type="hidden" name="operation" value="testTransaction" />
							<input type="hidden" name="id" value='${bac.id}' />
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>