<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
	<title>reporter</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <style>
		body {
			background-image:    url(${pageContext.request.contextPath}/resources/bg/repb.jpg);
			background-size:     cover;                      /* <------ */
			background-repeat:   no-repeat;
/* 			background-position: center center;              /* optional, center the image */ */
		}
	</style>
</head>
<body>
	<c:if test="${fn:length(reportermsg)>0 }">
		<script>
			alert("${reportermsg}")
		</script>
	</c:if>
<%-- 	<h3>Welcome reporter ${profile.profileBean.firstName}, status ${profile.loginStatus }</h3> --%>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <!-- Brand/logo -->
        <a class="navbar-brand" href="reporterhome">Welcome ${profile.profileBean.firstName}</a>
        <!-- Links -->
        <ul class="navbar-nav">
          <li class="nav-item">
             <a class="nav-link" href="genreport">generate report</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="logout?patientId=${profile.profileBean.userID }">logout</a>
          </li>
        </ul>
      </nav>
      <div style="padding: 20px">
      <c:choose>
      	<c:when test="${fn:length(appointments)>0 }">
      		<h3>absent doctors with appointments</h3>
      		<table class="table table-striped table-bordered table-hover table-dark ">
      			<tr>
      			<td>appointment id</td>
				<td>doctor id</td>
				<td>patient id</td>
				<td>user id</td>
				<td>ailment type</td>
				<td>ailment details</td>
				<td>history</td>
				<td>appointment date</td>
				<td>appointment time</td>
      			</tr>
      			<c:forEach var="appointment" items="${appointments }">
      				<tr>
      					<td>${appointment.appointmentID }</td>
      					<td>${appointment.doctorID }</td>
      					<td>${appointment.patient.patientID }</td>
      					<td>${appointment.patient.userID }</td>
      					<td>${appointment.patient.ailmentType }</td>
      					<td>${appointment.patient.ailmentDetails }</td>
      					<td>${appointment.patient.diagnosisHistory }</td>
      					<td>${appointment.appointmentDate }</td>
      					<td>${appointment.appointmentTime }</td>
      				</tr>
      			</c:forEach>
      		</table>
      	</c:when>
      	<c:when test="${fn:length(availdoctors)>0 }">
      		<h3>available doctors for the slot</h3>
      		<table class="table table-striped table-bordered table-hover table-dark">
      			<tr>
      				<td>doctor id</td>
      				<td>doctor name</td>
      				<td>qualification</td>
      				<td>specialization</td>
      			</tr>
      			<c:forEach var="doctor" items="${availdoctors }">
      				<tr>
      					<td>${doctor.doctorID }</td>
      					<td>${doctor.doctorName }</td>
      					<td>${doctor.qualification }</td>
      					<td>${doctor.specialization }</td>
      				</tr>
      			</c:forEach>
      		</table>
      	</c:when>
      	<c:otherwise>
      	<form method="post" action="getavaildoctors" >
      		Select a Date to Generate the List<br/>
      		<input id="datefield" type="date" name="cdate"><br/>
      		Select a Time Slot<br/>
      		<select name="slot">								
  								<option value="900-915">9.00 AM to 9.15 AM</option>
  								<option value="915-930">9.15 AM to 9.30 AM</option>
  								<option value="930-945">9.30 AM to 9.45 AM</option>
  								<option value="945-1000">9.45 AM to 10.00 AM</option>
  								<option disabled>----------------</option>
  								<option value="1000-1015">10.00 AM to 10.15 AM</option>
  								<option value="1015-1030">10.15 AM to 10.30 AM</option>
  								<option value="1030-1045">10.30 AM to 10.45 AM</option>
  								<option value="1045-1100">10.45 AM to 11.00 AM</option>
  								<option disabled>----------------</option>
  								<option value="1100-1115">11.00 AM to 11.15 AM</option>
  								<option value="1115-1130">11.15 AM to 11.30 AM</option>
  								<option value="1130-1145">11.30 AM to 11.45 AM</option>
  								<option value="1145-1200">11.45 AM to 12.00 PM</option>
  								<option disabled>----------------</option>
  								<option value="0200-0215">02.00 PM to 02.15 PM</option>
  								<option value="0215-0230">02.15 PM to 02.30 PM</option>
  								<option value="0230-0245">02.30 PM to 02.45 PM</option>
  								<option value="0245-0300">02.45 PM to 03.00 PM</option>
  								<option disabled>----------------</option>
  								<option value="0300-0315">03.00 PM to 03.15 PM</option>
  								<option value="0315-0330">03.15 PM to 03.30 PM</option>
  								<option value="0330-0345">03.30 PM to 03.45 PM</option>
  								<option value="0345-0400">03.45 PM to 04.00 PM</option>
  								<option disabled>----------------</option>
  								<option value="0400-0415">04.00 PM to 04.15 PM</option>
  								<option value="0415-0430">04.15 PM to 04.30 PM</option>
  								<option value="0430-0445">04.30 PM to 04.45 PM</option>
  								<option value="0445-0500">04.45 PM to 05.00 PM</option>					
							</select><br/>
      		
      		<input type="submit" value="generate"/>
      	</form>
      	</c:otherwise>
      	</c:choose>
      </div>
      <script>
			var today = new Date();
			var dd = today.getDate();
			var mm = today.getMonth()+1; 
			var yyyy = today.getFullYear();
			 if(dd<10){
				dd='0'+dd
			} 
			if(mm<10){
				mm='0'+mm
			} 
			today = yyyy+'-'+mm+'-'+dd;
			document.getElementById("datefield").setAttribute("min", today);
			
	   </script>
</body>
</html>