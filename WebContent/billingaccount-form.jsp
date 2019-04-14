<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Billing Account Enter / Update Form</title>
</head>
<body>
	<center>
		<h1>BIlling Account Management</h1>
		<h2>
			<a href="/BillingAccountServlet">List All Billing Accounts</a>
		</h2>
	</center>
	<div align="center">
		<c:if test="${billingaccounts != null}">
			<form action="BillingAccountServlet" method="get">
			<input type="hidden" name="operation" value="modify" />
		</c:if>
		<c:if test="${billingaccounts == null}">
			<form action="BillingAccountServlet" method="get">
			<input type="hidden" name="operation" value="create" />
		</c:if>
		<table border="1" cellpadding="5">
			<caption>
				<h2>
					<c:if test="${billingaccounts != null}">
               Edit Billing Account
              </c:if>
					<c:if test="${billingaccounts == null}">
               Add New Billing Account
              </c:if>
				</h2>
			</caption>
			<c:if test="${billingaccounts != null}">
				<input type="hidden" name="id" value="<c:out value='${billingaccounts.id}' />" />
			</c:if>

			<c:if test="${billingaccounts == null}">
				<tr>
					<th> Billing Account id</th>
					<td><input type="number" name="id" size="45"
					value="<c:out value='${billingaccounts.id}' />" /></td>
				</tr>
			</c:if>

			<tr>
				<th>Staff ID:</th>
				<td><input type="number" name="staff_id" size="45"
					value="<c:out value='${billingaccounts.staff_id}' />" /></td>
			</tr>
			<tr>
				<th>Checkin ID:</th>
				<td><input type="number" name="checkin_id" size="15"
					value="<c:out value='${billingaccounts.checkin_id}' />" /></td>
			</tr>
			<tr>
				<th>Paid by Person:</th>
				<td><input type="number" name="paid_by_person" size="15"
					value="<c:out value='${billingaccounts.paid_by_person}' />" /></td>
			</tr>
			<tr>
				<th>Paid by Insurance:</th>
				<td><input type="number" name="paid_by_insurance" size="15"
					value="<c:out value='${billingaccounts.paid_by_insurance}' />" /></td>
			</tr>
			<tr>
				<th>Payment Info:</th>
				<td><input type="text" name="payment_info"
					value="<c:out value='${billingaccounts.payment_info}' />" /></td>
			</tr>
			<tr>
				<th>Payee SSN:</th>
				<td><input type="text" name="payee_ssn"
					value="<c:out value='${billingaccounts.payee_ssn}' />" /></td>
			</tr>
			<tr>
				<th>Billing Address:</th>
				<td><input type="text" name="billing_address"
					value="<c:out value='${billingaccounts.billing_address}' />" /></td>
			</tr>
			<tr>
				<th>Total Charge:</th>
				<td><input type="number" name="total_charge" size="15"
					value="<c:out value='${billingaccounts.total_charge}' />" /></td>
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