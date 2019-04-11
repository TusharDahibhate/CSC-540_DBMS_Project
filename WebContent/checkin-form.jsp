<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Checkin</title>
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
    </div>
    
    <c:if test="${checkin != null}">
        <form action="CheckInServlet" method="post">
        <input type="hidden" name="operation" value="UPDATE" />
    </c:if>
    
    <c:if test="${checkin == null}">
        <form action="CheckInServlet" method="post">
        <input type="hidden" name="operation" value="INSERT" />
    </c:if>
    
	    <table border="1" cellpadding="5">
	        <c:if test="${checkin != null}">
	            <input type="hidden" name="id" value="<c:out value='${checkin.id}' />" />
	        </c:if>
	        
	        <tr>
	            <th>Patient id</th>
	            <td><input class="form-control" type="number" name="patient_id" required size="45"
	                value="<c:out value='${checkin.patientid}' />" /></td>
	        </tr>
	        <tr>
	            <th>Staff id</th>
	            <td><input class="form-control" type="number" name="staff_id" required size="45"
	                value="<c:out value='${checkin.staffid}' />" /></td>
	        </tr>
	        <tr>
	            <th>Start Date</th>
	            <td><input class="form-control" type="date" name="start_date" required size="45"
	                value="<c:out value='${checkin.startdate}' />" /></td>
	        </tr>
	        <tr>
	            <th>End date</th>
	            <td><input class="form-control" type="date" name="end_date" required size="45"
	                value="<c:out value='${checkin.enddate}' />" /></td>
	        </tr>
	        <tr>
	            <td colspan="2" align="center"><button type="submit" value="Save" class="button">Submit</button></td>
	        </tr>
	    </table>
    </form>
</body>
</html>