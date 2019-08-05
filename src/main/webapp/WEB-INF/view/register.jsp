<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>register</title>
</head>
<body>
	<body id="page">
	<ul class="cb-slideshow">
		<li><span>Image 01</span>
			<div>
				<h3>world-class facilities</h3>
			</div></li>
		<li><span>Image 02</span>
			<div>
				<h3>Expert-doctors</h3>
			</div></li>
		<li><span>Image 03</span>
			<div>
				<h3>state-of-the-art</h3>
			</div></li>
		<li><span>Image 04</span>
			<div>
				<h3>top-ranked</h3>
			</div></li>
		<li><span>Image 05</span>
			<div>
				<h3>patient-friendly</h3>
			</div></li>
		<li><span>Image 06</span>
			<div>
				<h3>globally acclaimed</h3>
			</div></li>
	</ul>
	<%-- 	<c:if test="${profile.loginStatus==1}"> --%>
	<%-- 		<c:redirect url="patient"></c:redirect> --%>
	<%-- 	</c:if>	 --%>
	<div class="container">
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark ">
			<a class="navbar-brand" href="#">Welcome To CLINIQUE Services Pvt
				Ltd.</a>
			<!-- Links -->
			<!-- Trigger the modal with a button -->

			<button type="button" class="btn btn-info btn-lg" data-toggle="modal"
				data-target="#myModal"
				style="float: right; position: absolute; top: 3px; right: 10px">Login</button>

			<button type="button" class="btn btn-info btn-lg" data-toggle="modal"
				data-target="#myModalreg"
				style="float: right; position: absolute; top: 3px; right: 120px">Register
				Here</button>

			<!-- Modal -->
			<div class="modal fade" id="myModal" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">Please provide Login Details</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">
							<form method="POST" action="login">
								<table width="100%">
									<tr>
										<td>User Name:</td>
										<td><input type="text" name="username"
											class="form-control" placeholder="Username"></td>
									</tr>
									<tr>
										<td>Password:</td>
										<td><input type="password" name="password"
											class="form-control" placeholder="Password"></td>
									</tr>
									<tr>
										<td colspan="2"><input type="submit"
											class="btn btn-primary" name="login" value="Login" /></td>

									</tr>
								</table>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
			</div>



			<div class="input-group pull-right"
				style="width: 50%; position: absolute; top: 9px; right: 50px;">

				<c:if test="${fn:length(message)>0 }">
					<p class="badge badge-danger">${message }</p>
				</c:if>
				<c:if test="${fn:length(username)>0 }">
					<p class="badge badge-success">Use ${username } as username for future logins.</p>
				</c:if>
			</div>
		</nav>
		<br>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-8"></div>
				<div class="modal fade" id="myModalreg" role="dialog">
					<div class="modal-dialog">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">New Patient? Register Here!</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>
							<div class="modal-body">
								<div class="col-sm-4">
									<div class="form-group">
										
										<form method="post" action="add">
											<center>
												<table frame="box" width="400px">
													<tr>
														<th style="padding: 20px">First name</th>
														<td style="padding: 10px"><input autofocus
															style="opacity: 0.8" type="text" class="form-control "
															name="firstName" placeholder="enter first name"
															pattern="[A-Za-z].{1,}" required /></td>

													</tr>
													<tr>
														<th style="padding: 20px">Last name</th>
														<td style="padding: 10px"><input style="opacity: 0.8"
															type="text" name="lastName" class="form-control"
															placeholder="enter last name" pattern="[A-Za-z].{1,}"
															required /></td>

													</tr>
													<tr>
														<th style="padding: 20px">DOB</th>
														<td style="padding: 10px"><input style="opacity: 0.8"
															type="date" name="dateOfBirth" class="form-control"
															placeholder="enter dob" required></td>

													</tr>
													<tr>
														<th style="padding: 20px">Gender</th>
														<td style="padding: 10px;"><select
															class="form-control" name="gender">
																<option value="m">Male</option>
																<option value="f">Female</option>
																<option value="o">Other</option>
														</select></td>

													</tr>
													<tr>
														<th style="padding: 20px">Street</th>
														<td style="padding: 10px"><input style="opacity: 0.8"
															type="text" name="street" class="form-control"
															placeholder="enter street" required/></td>

													</tr>
													<tr>
														<th style="padding: 20px">Location</th>
														<td style="padding: 10px"><input style="opacity: 0.8"
															type="text" name="location" class="form-control"
															placeholder="enter location" required /></td>

													</tr>
													<tr>
														<th style="padding: 20px">City</th>
														<td style="padding: 10px"><input style="opacity: 0.8"
															type="text" name="city" class="form-control"
															placeholder="enter city" required/></td>

													</tr>
													<tr>
														<th style="padding: 20px">State</th>
														<td style="padding: 10px"><input style="opacity: 0.8"
															type="text" name="state" class="form-control"
															placeholder="enter state" required/></td>

													</tr>
													<tr>
														<th style="padding: 20px">Pincode</th>
														<td style="padding: 10px"><input style="opacity: 0.8"
															type="text" name="pincode" class="form-control" required
															placeholder="enter pincode" pattern="[0-9]{6}" /></td>

													</tr>
													<tr>
														<th style="padding: 20px">Cell No</th>
														<td style="padding: 10px"><input style="opacity: 0.8"
															type="text" name="mobileNo" class="form-control"
															placeholder="enter cell number" pattern="[0-9]{10}"
															required /></td>

													</tr>
													<tr>
														<th style="padding: 20px">Mail id</th>
														<td style="padding: 10px"><input style="opacity: 0.8"
															type="email" name="emailID" class="form-control"
															placeholder="enter mail id" required /></td>

													</tr>
													<tr>
														<th style="padding: 20px">Password</th>
														<td style="padding: 10px"><input style="opacity: 0.8"
															type="password" name="password" class="form-control"
															placeholder="enter password" required /></td>

													</tr>
													<tr>
														<td></td>

														<td><input type="hidden" name="userType" value="p" /></td>

													</tr>
													<tr>
														<td colspan="2">
															<center>
																<input class="btn btn-tertiary btn-lg"
																	style="opacity: 0.55; background-color: rgb(28, 28, 204); align-content: center; font-weight: bold"
																	type="submit" name="register" value="Register" />
															</center>

														</td>
													</tr>
												</table>
											</center>
										</form>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
						</div>

					</div>
				</div>
			</div>
</body>
</html>