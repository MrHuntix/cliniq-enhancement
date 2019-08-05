<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<c:if test="${profile.loginStatus==0 }">
		<c:redirect url="home"></c:redirect>
	</c:if>
    <title>Patient</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
	<c:if test="${fn:length(getappointmentsfordatemsg)>0 }">
		<script >
			alert("${getappointmentsfordatemsg}")
		</script>
	</c:if>
<%-- 	<h3>Welcome ${profile.profileBean.firstName}, status ${profile.loginStatus }</h3> --%>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <!-- Brand/logo -->
        <a class="navbar-brand" href="patienthome">Welcome ${profile.profileBean.firstName}</a>
        
        <!-- Links -->
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="showappointmentsp?patientId=${profile.profileBean.userID }">view appointments</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="getappointments">get appointments</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="logout?patientId=${profile.profileBean.userID }">logout</a>
          </li>
        </ul>
      </nav>
      <div style="padding: 20px">
      <c:choose>
      <c:when test="${fn:length(appointments)>0 }">
      		<h3>Appointment details</h3><hr/>
      		<table class="table table-striped table-bordered table-hover table-dark ">
      		<tr>
      			<td>doctor id</td>
      			<td>appointment date</td>
      			<td>slot</td>
      			<td>ailment type</td>
      			<td>ailment details</td>
      			<td>diagnosis history</td>
      		</tr>
      		<c:forEach var="appointment" items="${appointments }" >
      			<tr>
      				<td>${appointment.doctorID }</td>
      				<td>${appointment.appointmentDate }</td>
      				<td>${appointment.appointmentTime }</td>
      				<td>${appointment.patient.ailmentType }</td>
      				<td>${appointment.patient.ailmentDetails }</td>
      				<td>${appointment.patient.diagnosisHistory }</td>#d${doctor.doctorID }d
      				<td><input type="submit" name="edit" value="edit" data-toggle="modal" data-target="#d${appointment.doctorID }d"/></td>
      				<div id="d${appointment.doctorID }d" class="modal fade" role="dialog">
      					<div class="modal-dialog">
      						<div class="modal-content">
      							<div class="modal-header">
      								<h4 class="modal-title">enter new ailment details</h4>
      							</div>
      							<div class="modal-body">
      							<form action="updateailmentsconfirmation" method="post">
      								<input type="text" name="pid" value="${appointment.patient.patientID }"/>
      								<select name="ailment">								
										<option value="immunology">Immunologist</option>
										<option value="cardiology">Cardiologist</option>
										<option value="dermatology">Dermatologist</option>
										<option value="oncology">Oncologist</option>
										<option value="nephrology">Nephrologist</option>
										<option value="neurology">Neurologist</option>
										<option value="ophthalmology">Ophthalmologist</option>
										<option value="pathology">Pathologist</option>
										<option value="psychiatry">Psychiatrist</option>
										<option value="urology">Urologist</option>	
									</select>
									<input type="text" name="details" placeholder="enter ailment details"/>
									<input type="text" name="history" placeholder="enter history"/>
									<input type="submit" name="save" value="save"/>	
      							</form>
      							</div>
      						</div>
      					</div>
      				</div>
      			</tr>
      		</c:forEach>
      		</table><hr/>
      </c:when>
      <c:when test="${fn:length(doctors)>0 }">
      	<h3>Doctor details</h3><hr/>
      	<table class="table table-striped table-bordered table-hover table-dark ">
      		<tr>
      			<td>name</td>
      			<td>qualification</td>
      			<td>specilization</td>
      			<td>mail id</td>
      			<td>select date to view appointments:</td>
      		</tr>
      		<c:forEach var="doctor" items="${doctors }">
      			<tr>
      				<td>${doctor.doctorName }</td>
      				<td>${doctor.qualification }</td>
      				<td>${doctor.specialization }</td>
      				<td>${doctor.emailID }</td>
      				<td>
      					<form action="getappointmentsfordate" method="post">
      						<input type="hidden" name="did" value="${doctor.doctorID }"/>
      						
      						<input type="date" name="date" min="${date }"/>
      						<input type="submit" name="show"/>
      					</form>
      				</td>
      			</tr>
      		</c:forEach><hr/>
      </c:when>
      <c:when test="${fn:length(doctorapp)>0 }">
      	<h3>Appointment details</h3><hr/>
      	<table class="table table-striped table-bordered table-hover table-dark ">
      		<tr>
      			<td>appointment date</td>
      			<td>appointment slot</td>
      		</tr>
      	<c:forEach var="appointment" items="${doctorapp }">
      		<tr>
      			<td>${appointment.appointmentDate }</td>
      			<td>${appointment.appointmentTime }</td>
      		</tr>
      	</c:forEach><hr/>
      </c:when>
      <c:otherwise>
		<h3>fill up form to book an appointment</h3>
		<form action="bookappointments" method="post" >
			<div class="form-group">
				<label>select day</label>
				<input  class="form-control" type="date" id="datefield" name="date" min="${date }" required/>
			</div>
			<div class="form-group">
				<label>select time slot</label>
				<select name="slot"  class="form-control">								
					<option value="900-915">9.00 AM to 9.15 AM</option>
					<option value="915-930">9.15 AM to 9.30 AM</option>
					<option value="930-945">9.30 AM to 9.45 AM</option>
					<option value="945-1000">9.45 AM to 10.00 AM</option>
					
					<option value="1000-1015">10.00 AM to 10.15 AM</option>
					<option value="1015-1030">10.15 AM to 10.30 AM</option>
					<option value="1030-1045">10.30 AM to 10.45 AM</option>
					<option value="1045-1100">10.45 AM to 11.00 AM</option>
					
					<option value="1100-1115">11.00 AM to 11.15 AM</option>
					<option value="1115-1130">11.15 AM to 11.30 AM</option>
					<option value="1130-1145">11.30 AM to 11.45 AM</option>
					<option value="1145-1200">11.45 AM to 12.00 PM</option>
					
					<option value="0200-0215">02.00 PM to 02.15 PM</option>
					<option value="0215-0230">02.15 PM to 02.30 PM</option>
					<option value="0230-0245">02.30 PM to 02.45 PM</option>
					<option value="0245-0300">02.45 PM to 03.00 PM</option>
					
					<option value="0300-0315">03.00 PM to 03.15 PM</option>
					<option value="0315-0330">03.15 PM to 03.30 PM</option>
					<option value="0330-0345">03.30 PM to 03.45 PM</option>
					<option value="0345-0400">03.45 PM to 04.00 PM</option>
					
					<option value="0400-0415">04.00 PM to 04.15 PM</option>
					<option value="0415-0430">04.15 PM to 04.30 PM</option>
					<option value="0430-0445">04.30 PM to 04.45 PM</option>
					<option value="0445-0500">04.45 PM to 05.00 PM</option>					
				</select>
			</div>
			<div class="form-group">
				<label>select category</label>
				<select name="ailment"  class="form-control">								
					<option value="immunology">Immunologist</option>
					<option value="cardiology">Cardiologist</option>
					<option value="dermatology">Dermatologist</option>
					<option value="oncology">Oncologist</option>
					<option value="nephrology">Nephrologist</option>
					<option value="neurology">Neurologist</option>
					<option value="ophthalmology">Ophthalmologist</option>
					<option value="pathology">Pathologist</option>
					<option value="psychiatry">Psychiatrist</option>
					<option value="urology">Urologist</option>	
				</select>
			</div>
			<div class="form-group">
				<label>ailment details</label>
				<textarea class="form-control" name="details" rows="3" placeholder="enter ailment details"></textarea>
<!-- 				<input type="text" name="details" placeholder="enter ailment details"/> -->
			</div>
			<div class="form-group">
				<label>appointment history</label>
				<textarea class="form-control" name="history" name="details" rows="3" placeholder="enter history"></textarea>
<!-- 				<input type="text" name="history" placeholder="enter history"/>	 -->
			</div>
         	<input type="hidden" name="pid" value="${profile.profileBean.userID}"/>
         	<input type="submit" class="btn btn-primary" name="book" value="book"/>
			</form>
			</c:otherwise>
			</c:choose>
      </div>
</body>
</html>