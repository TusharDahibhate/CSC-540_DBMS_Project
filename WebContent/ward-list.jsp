<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Wards</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/css/foundation.min.css">
    <script src="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.3/dist/js/foundation.min.js"></script>
</head>
<body>
    <div style="text-align:center">
         <h2>Ward Management</h2>
         <form style="display:inline-block" action="WardServlet" method="get" enctype="multipart/form-data">
             <input type="submit" class="button" value="Add new">
             <input type="hidden" name="operation" value="ADD" />
         </form>
    
         <form style="display:inline-block" action="WardServlet" method="get" enctype="multipart/form-data">
             <input type="submit" class="button" value="List all">
             <input type="hidden" name="operation" value="LIST" />
         </form>
         
         <form style="display:inline-block" action="WardServlet" method="get" enctype="multipart/form-data">
             <input type="submit" class="button" value="Show Ward usage Percentage">
             <input type="hidden" name="operation" value="usgpercent" />
         </form>
         
         <form action="homepage.jsp" method="get" enctype="multipart/form-data">
			<input type="submit" class="button" value="HOME">
		</form>	
    </div>

   <table border="1" cellpadding="5">
		<tr>
			<th>ID</th>
			<th>Staff Id</th>
			<th>Type</th>
			<th colspan="4">Actions</th>
		</tr>
		<c:forEach var="ward" items="${listWard}">
			<tr>
				<td><c:out value="${ward.id}" /></td>
				<td><c:out value="${ward.staffId}" /></td>
				<td><c:out value="${ward.type}" /></td>
				<td>
					<form action="WardServlet" method="get"
						enctype="multipart/form-data">
						<button type="submit" name="button" value="EDIT"><a>Edit</a></button>
						<input type="hidden" name="operation" value="EDIT" /> <input
							type="hidden" name="id" value='${ward.id}' />
					</form>
				</td>
				<td>
					<form action="WardServlet" method="get"
						enctype="multipart/form-data">
						<button type="submit" name="button" value="DELETE"><a>Delete</a></button>
						<input type="hidden" name="operation" value="DELETE" /> <input
							type="hidden" name="id" value='${ward.id}' />
					</form>
				</td>
				<td>
                    <form action="BedServlet" method="get"
                        enctype="multipart/form-data">
                        <button type="submit" name="button" value="submit"><a>Show all beds</a></button>
                        <input type="hidden" name="operation" value="SHOW_BY_WARD" />
                        <input type="hidden" name="empty" value='0' />
                        <input type="hidden" name="ward_id" value='${ward.id}' />
                    </form>
                </td>
                <td>
                    <form action="BedServlet" method="get"
                        enctype="multipart/form-data">
                        <button type="submit" name="button" value="L"><a>Show empty beds</a></button>
                        <input type="hidden" name="operation" value="SHOW_BY_WARD" />
                        <input type="hidden" name="ward_id" value='${ward.id}' />
                        <input type="hidden" name="empty" value='1' />
                        <input type="hidden" name="type" value='empty' />
                    </form>
                </td>
			</tr>
		</c:forEach>
	</table>
	
	<c:if test="${usgpercentward != null}">
        <c:forEach var="type" items="${usgpercentward}">
		   Ward ID: ${type.key} is ${type.value} % available. <br/>
		</c:forEach>
        </div>
    </c:if>
</body>
</html>