<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Checkins</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/css/foundation.min.css">
    <script src="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/js/foundation.min.js"></script>
</head>
<body>
    <div style="text-align:center">
         <h2>Checkin Management</h2>
         <form action="CheckInServlet" method="get" enctype="multipart/form-data">
             <input type="submit" class="button" value="Add new">
             <input type="hidden" name="operation" value="ADD" />
         </form>
    
         <form action="CheckInServlet" method="get" enctype="multipart/form-data">
             <input type="submit" class="button" value="List all">
             <input type="hidden" name="operation" value="LIST" />
         </form>
         
         <form action="CheckInServlet" method="get" enctype="multipart/form-data">
             <input type="submit" class="button" value="Show Stats">
             <input type="hidden" name="operation" value="STATS" />
         </form>
         
    </div>

    <table border="1" cellpadding="5">
        <tr>
            <th>Id</th>
            <th>Patient Id</th>
            <th>Staff Id</th>
            <th>Start Date</th>
            <th>End Date</th>
        </tr>
        <c:forEach var="checkin" items="${listCheckin}">
            <tr>
                <td><c:out value="${checkin.id}" /></td>
                <td><c:out value="${checkin.patientid}" /></td>
                <td><c:out value="${checkin.staffid}" /></td>
                <td><c:out value="${checkin.startdate}" /></td>
                <td><c:out value="${checkin.enddate}" /></td>
                <td>
                    <form action="CheckInServlet" method="get"
                        enctype="multipart/form-data">
                        <button type="submit" name="button" value="EDIT"><a>Edit</a></button>
                        <input type="hidden" name="operation" value="EDIT" />
                        <input type="hidden" name="id" value='${checkin.id}' />
                    </form>
                </td>
                <td>
                    <form action="CheckInServlet" method="get"
                        enctype="multipart/form-data">
                        <button type="submit" name="button" value="DELETE"><a>Delete</a></button>
                        <input type="hidden" name="operation" value="DELETE" />
                        <input type="hidden" name="id" value='${checkin.id}' />
                    </form>
                </td>
                <c:if test="${checkin.enddate == null}">
	                <td>
	                    <form action="CheckoutServlet" method="get"
	                        enctype="multipart/form-data">
	                        <button type="submit" name="button" value="CHECKOUT"><a>Checkout</a></button>
	                        <input type="hidden" name="operation" value="CHECKOUT" />
	                        <input type="hidden" name="id" value='${checkin.id}' />
	                    </form>
	                </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    
    <c:if test="${curr_checkins != null}">
        <div>Number of Patients Currently Checked-in : <button > ${curr_checkins} </button> </div>
        <div>
        <c:forEach var="type" items="${patientsByMonth}">
		   Month: ${type.key} had a total of: ${type.value} Patients <br/>
		</c:forEach>
        </div>
    </c:if>
    
</body>
</html>