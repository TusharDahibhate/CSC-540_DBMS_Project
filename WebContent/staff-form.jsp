<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Staff</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/css/foundation.min.css">
    <script src="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/js/foundation.min.js"></script>
</head>
<body>
     <div style="text-align:center">
	     <h2>Staff Management</h2>
	     <form action="StaffServlet" method="get" enctype="multipart/form-data">
	         <input type="submit" class="button" value="Add new">
	         <input type="hidden" name="operation" value="ADD" />
	     </form>
	
	     <form action="StaffServlet" method="get" enctype="multipart/form-data">
	         <input type="submit" class="button" value="List all">
	         <input type="hidden" name="operation" value="LIST" />
	     </form>
    </div>
    
    <c:if test="${staff != null}">
        <form action="StaffServlet" method="post">
        <input type="hidden" name="operation" value="UPDATE" />
    </c:if>
    
    <c:if test="${staff == null}">
        <form action="StaffServlet" method="post">
        <input type="hidden" name="operation" value="INSERT" />
    </c:if>
    
    <table border="1" cellpadding="5">
        <c:if test="${staff != null}">
            <input type="hidden" name="id" value="<c:out value='${staff.id}' />" />
        </c:if>
        
        <tr>
            <th>Name</th>
            <td><input class="form-control" type="text" name="name" required size="45"
                value="<c:out value='${staff.name}' />" /></td>
        </tr>
        <tr>
            <th>Age</th>
            <td><input class="form-control" type="number" name="age" required size="45"
                value="<c:out value='${staff.age}' />" /></td>
        </tr>
        <tr>
            <th>Gender</th>
            <td><input class="form-control" type="text" name="gender" required size="45"
                value="<c:out value='${staff.gender}' />" /></td>
        </tr>
        <tr>
            <th>Job Title</th>
            <td><input class="form-control" type="text" name="job_title" required size="45"
                value="<c:out value='${staff.jobtitle}' />" /></td>
        </tr>
        <tr>
            <th>Professional Title</th>
            <td><input class="form-control" type="text" name="professional_title" required size="45"
                value="<c:out value='${staff.professionaltitle}' />" /></td>
        </tr>
        <tr>
            <th>Phone</th>
            <td><input class="form-control" type="number" name=phone_no required size="45"
                value="<c:out value='${staff.phoneno}' />" /></td>
        </tr>
        <tr>
            <th>Address</th>
            <td><input class="form-control" type="text" name="address" required size="45"
                value="<c:out value='${staff.address}' />" /></td>
        </tr>
        <tr>
            <th>Department</th>
            <td><input class="form-control" type="text" name="department" required size="45"
                value="<c:out value='${staff.department}' />" /></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><button type="submit" value="Save" class="button">Submit</button></td>
        </tr>
    </table>
    </form>
</body>
</html>