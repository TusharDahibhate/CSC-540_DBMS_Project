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
         <form style="display:inline-block" action="StaffServlet" method="get" enctype="multipart/form-data">
             <input type="submit" class="button" value="Add new">
             <input type="hidden" name="operation" value="ADD" />
         </form>
    
         <form style="display:inline-block" action="StaffServlet" method="get" enctype="multipart/form-data">
             <input type="submit" class="button" value="List all">
             <input type="hidden" name="operation" value="LIST" />
         </form>
    </div>

    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Age</th>
            <th>Gender</th>
            <th>Job Title</th>
            <th>Professional Title</th>
            <th>Phone</th>
            <th>Address</th>
            <th>Department</th>
            <th colspan="2">Actions</th>
        </tr>
        <c:forEach var="staff" items="${listStaff}">
            <tr>
                <td><c:out value="${staff.id}" /></td>
                <td><c:out value="${staff.name}" /></td>
                <td><c:out value="${staff.age}" /></td>
                <td><c:out value="${staff.gender}" /></td>
                <td><c:out value="${staff.jobtitle}" /></td>
                <td><c:out value="${staff.professionaltitle}" /></td>
                <td><c:out value="${staff.phoneno}" /></td>
                <td><c:out value="${staff.address}" /></td>
                <td><c:out value="${staff.department}" /></td>
                <td>
                    <form action="StaffServlet" method="get"
                        enctype="multipart/form-data">
                        <button type="submit" name="button" value="EDIT"><a>Edit</a></button>
                        <input type="hidden" name="operation" value="EDIT" />
                        <input type="hidden" name="id" value='${staff.id}' />
                    </form>
                </td>
                <td>
                    <form action="StaffServlet" method="get"
                        enctype="multipart/form-data">
                        <button type="submit" name="button" value="DELETE"><a>Delete</a></button>
                        <input type="hidden" name="operation" value="DELETE" />
                        <input type="hidden" name="id" value='${staff.id}' />
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    
</body>
</html>