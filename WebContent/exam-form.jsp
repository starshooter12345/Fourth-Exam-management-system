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
		<div class="container-col-md-5">
			<div class="card">
				<div class="card-body">
					<c:if test = "${exam != null }">
						<form action = "update" method="post">
				</c:if>
				<c:if test="${exam == null}">
					<form action = "insert" method="post">
				</c:if>
				
				<caption>
					<h2>
						<c:if test="${exam != null}"> 
						Edit exam
					</c:if>
					<c:if test="${exam == null}">
						Add new exam
					</c:if>
				</h2>
				</caption>
				<c:if test="${exam != null}">
					<input type="hidden" name="examid" value="<c:out value='${exam.examid}'/>" /> 
				</c:if>
				
				<fieldset class="form-group">
				<label>Subject Name </label> <input type="text"
					value="<c:out value='${exam.subjectname}' />" class="form-control"
					name="subjectname" required="required">
				</fieldset>
				
				<fieldset class="form-group">
				<label>Date and time</label> <input type="text"
					value="<c:out value='${exam.dateandtime}' />" class="form-control"
					name="dateandtime" required="required">
				</fieldset>
				
				<fieldset class="form-group">
				<label>Type</label> <input type="text"
					value="<c:out value='${exam.type}' />" class="form-control"
					name="type">
				</fieldset>
				<button type="submit" class="btn btn-success">Save</button>
				</form>
				
				
				</div>
			</div>
		</div>

</body>
</html>