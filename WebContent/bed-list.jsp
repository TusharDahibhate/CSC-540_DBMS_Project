<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Beds</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/css/foundation.min.css">
    <script src="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/js/foundation.min.js"></script>
</head>
<body>
    <div style="text-align:center">
         <h2>Bed Management</h2>
         <form style="display:inline-block" action="BedServlet" method="get" enctype="multipart/form-data">
             <input type="submit" class="button" value="Add new">
             <input type="hidden" name="operation" value="ADD" />
         </form>
    
         <form style="display:inline-block" action="BedServlet" method="get" enctype="multipart/form-data">
             <input type="submit" class="button" value="List all">
             <input type="hidden" name="operation" value="LIST" />
         </form>
         
         <form style="display:inline-block" action="BedServlet" method="get" enctype="multipart/form-data">
             <input type="submit" class="button" value="Show only empty beds">
             <input type="hidden" name="operation" value="LISTEMPTY" />
             <input type="hidden" name="ward_id" value="${ward_id}" />
         </form>
         
         <form action="homepage.jsp" method="get" enctype="multipart/form-data">
			<input type="submit" class="button" value="HOME">
		</form>	
    </div>

	<table border="1" cellpadding="5">
		<tr>
			<th>ID</th>
			<th>Ward Id</th>
			<th>Rate</th>
			<th>Checkin Id</th>
			<th colspan="3">Actions</th>
		</tr>
		<c:forEach var="bed" items="${listBed}">
			<tr>
				<td><c:out value="${bed.id}" /></td>
				<td><c:out value="${bed.wardId}" /></td>
				<td><c:out value="${bed.rate}" /></td>
				<td><c:out value="${bed.checkinId}" /></td>
				<td>
					<form action="BedServlet" method="get"
						enctype="multipart/form-data">
						<button type="submit" name="button" value="EDIT"><a>Edit</a></button>
						<input type="hidden" name="operation" value="EDIT" /> <input
							type="hidden" name="id" value='${bed.id}' /> <input
							type="hidden" name="ward_id" value='${bed.wardId}' />
					</form>
				</td>
				<c:if test="${bed.checkinId == 0}">
				<td>
					<form action="BedServlet" method="get"
						enctype="multipart/form-data">
						<button type="submit" name="button" value="DELETE"><a>Delete</a></button>
						<input type="hidden" name="operation" value="DELETE" /> <input
							type="hidden" name="id" value='${bed.id}' /> <input
							type="hidden" name="ward_id" value='${bed.wardId}' />
					</form>
				</td>
				
				   <td>
					   <form action="BedServlet" method="get" enctype="multipart/form-data">
                            <button type="submit" name="button" value="Assign"><a>Assign</a></button>
                            <input type="hidden" name="operation" value="EDIT" />
                            <input type="hidden" name="id" value='${bed.id}' />
                            <input type="hidden" name="ward_id" value='${bed.wardId}' />
                        </form>
                       </td>
                </c:if>
			</tr>
		</c:forEach>
	</table>
</body>
</html>