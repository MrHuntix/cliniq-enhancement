<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<!DOCTYPE html>
<html>
<head>
<!-- 	<meta charset="ISO-8859-1"> -->
	<title>Administrator</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
	<c:if test="${fn:length(adddocresult)>0 }">
		<script>
			alert("${adddocresult}")
		</script>
	</c:if>
<!-- 	nav bar -->
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <!-- Brand/logo -->
        <a class="navbar-brand" href="gotoadmin">Welcome ${profile.profileBean.firstName}</a>
        
        <!-- Links -->
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="doctormodal" data-toggle="modal" data-target="#myModal">add doctor</a>
          </li>	
           <li class="nav-item">
            <a class="nav-link" href="doctors">show doctors</a>
          </li>	
		  <li class="nav-item">
            <a class="nav-link" href="doctormodal" data-toggle="modal" data-target="#myApp">show appointments</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="intimate">get intimate report</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="logout?patientId=${profile.profileBean.userID }">logout</a>
          </li>
        </ul>
    </nav>
     <div style="padding: 20px">
     <c:choose>
     	<c:when test="${fn:length(reports)>0 }">
		<table class="table table-striped table-bordered table-hover table-dark ">
		<tr>
      			<td>appointment id</td>
      			<td>doctor id</td>
      			<td>ailment type</td>
      			<td>appointment date</td>
      			<td>appointment time</td>
      	</tr>
      	<c:forEach var="report" items="${reports }">
      		<tr>
      			<td>${report.appointmentID }</td>
      			<td>${report.doctorID }</td>
      			<td>${report.patient.ailmentType }</td>
      			<td>${report.appointmentDate }</td>
      			<td>${report.appointmentTime }</td>
      			<td>
      				<form action="updatedoctor" method="post">
      					<input type="hidden" name="aid" value="${report.appointmentID }"/>
      					<input type="hidden" name="did" value="${report.doctorID }"/>
      					<input type="hidden" name="slot" value="${report.appointmentTime }"/>
      					<input type="hidden" name="date" value="${report.appointmentDate }"/>
      					<input type="hidden" name="ailment" value="${report.patient.ailmentType }"/>
      					<input type="submit" name="update" value="update"/>
      				</form>
      			</td>
      		</tr>
      	</c:forEach>
      	</table>
	</c:when>
	<c:when test="${fn:length(appointmentsdate)>0 }">
		<table class="table table-striped table-bordered table-hover table-dark ">
			<tr>
				<td>appointment id</td>
				<td>doctor id</td>
				<td>patient id</td>
				<td>ailment type</td>
				<td>ailment details</td>
				<td>history</td>
				<td>appointment date</td>
				<td>appointment time</td>
			</tr>
		<c:forEach var="appointment" items="${appointmentsdate}">
			<tr>
				<td>${appointment.appointmentID }</td>
				<td>${appointment.doctorID }</td>
				<td>${appointment.patient.patientID }</td>
				<td>${appointment.patient.ailmentType }</td>
				<td>${appointment.patient.ailmentDetails }</td>
				<td>${appointment.patient.diagnosisHistory }</td>
				<td>${appointment.appointmentDate }</td>
				<td>${appointment.appointmentTime }</td>
			</tr>
		</c:forEach>
		</table>
	</c:when>
	<c:when test="${fn:length(suggestion)>0 }">
		<h3>suggested doctor</h3>
		<c:forEach var="s" items="${suggestion }">
			<form action="updatesuggestion" method="post">
			<table>
				<tr>
					<td>appointment id</td>
					<td><input type="text" name="aid" value="${appointmentid }"/></td>
				</tr>
				<tr>
					<td>old doctor id</td>
					<td><input type="text" name="oldid" value="${oldid }"/></td>
				</tr>
				<tr>
					<td>new doctor id</td>
					<td><input type="text" name="newid" value="${s.doctorID }"/></td>
				</tr>
				<tr>
					<td>specialization</td>
					<td><input type="text" name="splz" value="${s.specialization }"/></td>
				</tr>
				<tr>
					<td><input type="submit" name="confirm" value="confirm"/></td>
				</tr>	      			
			</table>
		</form>
		</c:forEach>
		
<%-- 		${suggestion } --%>
	</c:when>
	<c:otherwise>
		<h3>select a admin task.</h3>
	</c:otherwise>
    </c:choose>
    <div id="myApp" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h4 class="modal-title">select date for which you would like to get appointment details for</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	      <div class="modal-body">
	        <form action="showappointments" method="post">
				<div class="form-group">
					<input type="date" name="adate" /><label> select date</label>
				</div>
				<input type="submit" class="btn btn-default" name="view"/>
			</form>
	      </div>
	    </div>
	  </div>
	</div>
    
    
<!-- nav bar -->
<%-- 	<h3>welcome ${profile.profileBean.firstName }</h3>
	modal page starts
 --%>
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
          			<h4 class="modal-title">enter doctor details</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
        		</div>
        		<div class="modal-body">
					<div class="container">
        <div class="row">
            <div class="col-sm-6">
				<div class="form-group">
	               <form action="adddoc" method="post">
		             <table  width="400px" border="1">
							<tr>
							   <th style="padding: 20px">Doctor name</th>
							   <td style="padding: 10px"><input autofocus
							   style="opacity: 0.8" type="text" class="form-control "
							   name="doctorName" placeholder="enter doctor name"
							   pattern="[A-Za-z].{1,}" required /></td>
					
							</tr>
								
							<tr>
							   <th style="padding: 20px">Date Of Birth</th>
							   <td style="padding: 10px"><input autofocus
							   style="opacity: 0.8" type="date" class="form-control "
							   name="dateOfBirth" placeholder="enter dob"
							   required /></td>
					
							</tr>
							
							<tr>
							   <th style="padding: 20px">Date Of Joining</th>
							   <td style="padding: 10px"><input autofocus
							   style="opacity: 0.8" type="date" class="form-control "
							   name="dateOfJoining" placeholder="enter doj"
							   required /></td>
					
							</tr>
							
							<tr>
							   <th style="padding: 20px">Gender</th>
							   <td style="padding: 10px;"><select name="gender" class="form-control">
							   <option value="m">Male</option>
							   <option value="f">Female</option>
							   </select></td>
					
							</tr>
							
							 <tr>
							   <th style="padding: 20px">Qualification</th>
							   <td style="padding: 10px"><input autofocus
							   style="opacity: 0.8" type="text" class="form-control "
							   name="qualification" placeholder="enter qualification"
							   required /></td>
					
							</tr>
								
							<tr>
							   <th style="padding: 20px">Specilization</th>
							   <td style="padding: 10px">
									<select name="specialization">
										<option value="immunology">immunologist</option>
										<option value="cardiology">cardiologist</option>
										<option value="dermatology">dermatologist</option>
										<option value="oncology">oncologist</option>
										<option value="nephrology">nephrologist</option>
										<option value="neurology">neurologist</option>
										<option value="ophthalmology">ophthalmologist</option>
										<option value="pathology">pathologist</option>
										<option value="psychiatry">psychiatrist</option>
										<option value="urology">urologist</option>
									</select>
							   </td>
					
							</tr>
								
							<tr>
							   <th style="padding: 20px">Years Of Experience</th>
							   <td style="padding: 10px"><input autofocus
							   style="opacity: 0.8" type="text" class="form-control "
							   name="yearsOfExperience" placeholder="enter YOE"
							   required /></td>
					
							</tr>
							
							<tr>
							   <th style="padding: 20px">Street</th>
							   <td style="padding: 10px"><input autofocus
							   style="opacity: 0.8" type="text" class="form-control "
							   name="street" placeholder="enter street"
							   required /></td>
					
							</tr>
							
							<tr>
							   <th style="padding: 20px">Location</th>
							   <td style="padding: 10px"><input autofocus
							   style="opacity: 0.8" type="text" class="form-control "
							   name="location" placeholder="enter location"
							   required /></td>
					
							</tr>
							
							<tr>
							   <th style="padding: 20px">City</th>
							   <td style="padding: 10px"><input autofocus
							   style="opacity: 0.8" type="text" class="form-control "
							   name="city" placeholder="enter city"
							   required /></td>
					
							</tr>
								
							<tr>
							   <th style="padding: 20px">State</th>
							   <td style="padding: 10px"><input autofocus
							   style="opacity: 0.8" type="text" class="form-control "
							   name="state" placeholder="enter state"
							   required /></td>
					
							</tr>
							
							<tr>
							   <th style="padding: 20px">Pincode</th>
							   <td style="padding: 10px"><input autofocus
							   style="opacity: 0.8" type="text" class="form-control "
							   name="pincode" placeholder="enter pincode"
							   required /></td>
					
							</tr>
							
							<tr>
							   <th style="padding: 20px">Cell number</th>
							   <td style="padding: 10px"><input autofocus
							   style="opacity: 0.8" type="text" class="form-control "
							   name="contactNumber" placeholder="enter cell number"
							   required /></td>
					
							</tr>
							
							<tr>
							   <th style="padding: 20px">Email Id</th>
							   <td style="padding: 10px"><input autofocus
							   style="opacity: 0.8" type="email" class="form-control "
							   name="emailID" placeholder="enter mail id"
							   required /></td>
					
							</tr>
							<input type="hidden" name="days" value="m,t"/>
							<input type="hidden" name="slots" placeholder="enter slots" value="2"/>	
					
							
								<tr>
									<td colspan="2"><input type="submit" class="btn btn-tertiary btn-lg" name="add" value="add"/></td>
								</tr>
		              </table>
	               </form>
	           </div>
	      </div>
	   </div>
	 </div>
				</div>
			</div>
		</div>
<!-- 	modal ends -->
	
<!-- 	<a href="doctors">list doctors</a> -->
	
<!-- 	<a href="intimate">intimate</a> -->
		
	
<!-- 	new suggested doctor -->
	
</div>
</body>
</html>