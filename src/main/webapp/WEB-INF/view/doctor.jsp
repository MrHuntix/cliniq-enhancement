<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<!DOCTYPE html>
<html>
<head>
	<title>doctors</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	<style>
		body {
    		background-image:    url(${pageContext.request.contextPath}/resources/bg/docb.jpg);
    		background-size:     cover;                      /* <------ */
    		background-repeat:   no-repeat;
    		background-position: center center;              /* optional, center the image */
		}
		html, body{
  			height:100%;
    		width:100%;
    		padding:0;
    		margin:0;
		}
		td {
 			text-align: center;
 			vertical-align: middle;
		}
	</style>
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header center">
     <center> <a href="gotoadmin" style="color:red;">go home</a></center>
    </div>
  </div>
</nav>
	<table class="table table-striped table-bordered table-hover table-dark ">
		<tr>
			<th>Doctor Name</th>
			<th>Date of Birth</th>
			<th>Date of Joining</th>
			<th>Gender</th>
			<th>Qualification</th>
			<th>Specilization</th>
			<th>Years Of Experience</th>
			<th>Cell Number</th>
			<th>Email Id</th>
			<th colspan="2">Operations</th>
		</tr>
		<c:forEach var="doctor" items="${doctors }">
			<tr>
				<td>${doctor.doctorName }</td>
				<td>${doctor.dateOfBirth }</td>
				<td>${doctor.dateOfJoining }</td>
				<td>${doctor.gender }</td>
				<td>${doctor.qualification }</td>
				<td>${doctor.specialization }</td>
				<td>${doctor.yearsOfExperience }</td>
				<td>${doctor.contactNumber }</td>
				<td>${doctor.emailID }</td>
				<td>
					<form method="post" action="delete">
						<input type="hidden" name="id" value="${doctor.doctorID }" />
						<input type="submit" class="btn btn-primary" value="delete" name="delete"/>
					</form>
				</td>
				<td>
					 <button type="button" class="btn btn-info" data-toggle="collapse" data-target="#d${doctor.doctorID }d">${doctor.doctorID }</button>
					  <div id="d${doctor.doctorID }d" class="collapse">
					   	<form method="post" action ="applyleave">
					    	<input type="hidden" name="did" value="${doctor.doctorID }" />
					    	from: <input type="date" name="fromdate"/><br/>
							to: <input type="date" name="todate"/><br/>
							reason: <input type="text" name="reason" placeholder="reason"/>
							<input type="radio" name="status" value="0" checked> 0
			  				<input type="radio" name="status" value="1"> 1<br>
			  				<input type="submit" name="apply">
					    </form>
					  </div>
				</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${doctor>1 }">
		<p>doctor of id ${doctor } deleted</p>
	</c:if>
	
</body>
</html>