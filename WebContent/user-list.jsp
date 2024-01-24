<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>User Management Application</title>
    <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
    <style>
        @media print {
            .no-print {
                display: none;
            }
        }
    </style>
    <script>
        function openPrintPreview() {
            var printWindow = window.open('', '_blank');
            var content = document.getElementById('print-content').innerHTML;
            printWindow.document.open();
            printWindow.document.write('<html><head><title>User Management Application - Print Preview</title></head><body>');
            printWindow.document.write(content);
            printWindow.document.write('</body></html>');
            printWindow.document.close();
            printWindow.print();
        }
    </script>
</head>
<body>

    <header>
        <nav class="navbar navbar-expand-md navbar-dark"
            style="background-color: tomato">
            <div>
                <a href="https://www.javaguides.net" class="navbar-brand"> User Card Details </a>
            </div>

            <ul class="navbar-nav">
                <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
            </ul>
        </nav>
    </header>
    <br>

    <div class="row">
        <div class="container">
            <h3 class="text-center">List of Users</h3>
            <hr>
            <div class="container text-left">

                <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New User</a>
                <button class="btn btn-primary" onclick="openPrintPreview()">Print Preview</button>
            </div>
            <br>
            <div id="print-content">
                <table class="table table-bordered">
                    <thead>
					<tr>
						<th>ID</th>
						<th>Knit Card Balance</th>
						<th>Knit Card Number</th>
						<th>Knit Card Quantity</th>
						<th>Requisition Quantity</th>
						<th>Trnumber</th>
						<th class="no-print">Actions</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="user" items="${listUser}">

						<tr>
							<td><c:out value="${user.id}" /></td>
							<td><c:out value="${user.knitCardBalance}" /></td>
							<td><c:out value="${user.knitCardNumber}" /></td>
							<td><c:out value="${user.knitCardQuantity}" /></td>
							<td><c:out value="${user.requisitionQuantity}" /></td>
							<td><c:out value="${user.trNumber}" /></td>
							<td><a href="edit?id=<c:out value='${user.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${user.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
