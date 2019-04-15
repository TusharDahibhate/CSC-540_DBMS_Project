<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Check Out</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/css/foundation.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/js/foundation.min.js"></script>
</head>
<body>
<form action="homepage.jsp" method="get" enctype="multipart/form-data">
			<input type="submit" class="button" value="HOME">
		</form>	
	<div>The assigned bed has be released.</div>
	<div>Registration fee was "${regFee}" dollars.</div>
	<div>Prescription bill: "${pBill}" dollars</div>
	<div>
		<table border="1" cellpadding="5">
			<caption>
				<h2>Prescription Report</h2>
			</caption>
			<tr>
				<th>Patient Id</th>
				<th>Check-In Id</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th>Diagnosis</th>
				<th>Medicine</th>
				<th>Quantity</th>
				<th>Price</th>

			</tr>
			<c:forEach var="prescbill" items="${prescbill}">
				<tr>
					<td><c:out value="${prescbill.patient_id}" /></td>
					<td><c:out value="${prescbill.checkin_id}" /></td>
					<td><c:out value="${prescbill.start_date}" /></td>
					<td><c:out value="${prescbill.end_date}" /></td>
					<td><c:out value="${prescbill.diagnosis}" /></td>
					<td><c:out value="${prescbill.med_name}" /></td>
					<td><c:out value="${prescbill.quantity}" /></td>
					<td><c:out value="${prescbill.price}" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div>Test Report bill: "${tBill} dollars"</div>
	<div>
		<table border="1" cellpadding="5">
			<caption>
				<h2>Test Report</h2>
			</caption>
			<tr>
				<th>Check-In Id</th>
				<th>Patient Name</th>
				<th>Test Name</th>
				<th>Price</th>
				<th>Result</th>

			</tr>
			<c:forEach var="testbill" items="${testbill}">
				<tr>
					<td><c:out value="${testbill.checkin_id}" /></td>
					<td><c:out value="${testbill.patient_name}" /></td>
					<td><c:out value="${testbill.test_name}" /></td>
					<td><c:out value="${testbill.price}" /></td>
					<td><c:out value="${testbill.result}" /></td>

				</tr>
			</c:forEach>
		</table>
	</div>
	<div>Bed bill: "${bBill} dollars"</div>
	<div>
		<table border="1" cellpadding="5">
			<caption>
				<h2>Bed Bill</h2>
			</caption>
			<tr>
				<th>Patient Id</th>
				<th>Check-In Id</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th>Rate</th>

			</tr>
			<tr>
				<td><c:out value="${wardbill.patient_id}" /></td>
				<td><c:out value="${wardbill.checkin_id}" /></td>
				<td><c:out value="${wardbill.start_date}" /></td>
				<td><c:out value="${wardbill.end_date}" /></td>
				<td><c:out value="${wardbill.rate}" /></td>

			</tr>
		</table>
	</div>
	<div>Total bill: "${total} dollars"</div>
	<div>GoodBye !</div>

</body>
</html>