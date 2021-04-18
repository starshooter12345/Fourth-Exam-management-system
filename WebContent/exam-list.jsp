<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>

<title>Exam Management System</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWrx9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
		style="background-color: tomato">
		<div>
		<a href="https://www.javaguides.net" class="navbar-brand"> Exam management app </a>
		
		</div>
		<ul class="navbar-nav">
		   <li><a href="<%=request.getContextPath()%>/list"
		   		class="nav-link">Exams</a></li>
		   		</ul>
		  </nav>

		</header>
		<br>
		<div class="row">
			
			<div class="container">
			 	<h3 class="text-center"> List of exams</h3>
			 	<hr>
			 	<div class="container text-left">
			 		<a href="<%=request.getContextPath() %>/new" class="btn btn-success">Add new exam</a>
			</div>
			<br>
			<table class="table bordered">
			<thead>
			<tr>
			<th>ExamID</th>
			<th>Subject name</th>
			<th>Date and time</th>
			<th>Type</th>
			<th>Actions</th>
			</tr>
			</thead>
			<tbody>
			<!--  for(Todo todo: todos) { -->
			<c:forEach var="exam" items="${listExam}"> 
				<tr>
				<td><c:out value="${exam.examid}" /></td> 
				<td><c:out value="${exam.subjectname}" /></td> 
				<td><c:out value="${exam.dateandtime}" /></td> 
				<td><c:out value="${exam.type}" /></td> 
				<td><a href="edit?examid=<c:out value='${exam.examid}' />">Edit</a> 
					&nbsp;&nbsp;&nbsp;&nbsp; <a
					href="delete?examid=<c:out value='${exam.examid}' />">Delete</a></td>
				</tr>
				</c:forEach>
				<!-- } -->
			</tbody>
			
			</table>
		</div>
		</div>

</body>
</html>