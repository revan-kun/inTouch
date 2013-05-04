<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>registration</title>

<link type="text/css" rel="stylesheet" href="css/bootstrap-select.css" />
<link type="text/css" rel="stylesheet" href="css/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="css/bootstrap-responsive.css" />
<link type="text/css" rel="stylesheet" href="css/style.css" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/bootstrap-select.js"></script>

</head>
<body>
	<div class="modal" id="registration">

		<div class="modal-body">

			<div class="alert alert-success">Fill up the registration form
				below to proceed</div>

			<form class="form-horizontal" id="registerHere" method='post'
				action=''>
				<fieldset>

					<legend>Create your personal inTouch account</legend>

					<div class="control-group">
						<label class="control-label">Name</label>
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on"><i class="icon-user"></i></span> <input
									type="text" class="input-xlarge" id="user_name"
									name="user_name" placeholder="Arkadiy Dobkin">
							</div>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label">Email</label>
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on"><i class="icon-envelope"></i></span> <input
									type="text" class="input-xlarge" id="user_email"
									name="user_email" placeholder="arkadiy.dobkin@epam.com">
							</div>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="input01">Password</label>
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on"><i class="icon-lock"></i></span> <input
									type="password" class="input-xlarge" id="pwd" name="pwd"
									placeholder="arkasha123">
							</div>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="input01">Confirm
							Password</label>
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on"><i class="icon-lock"></i></span> <input
									type="password" class="input-xlarge" id="cpwd" name="cpwd"
									placeholder="arkasha123">
							</div>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="role">Role</label>
						<div class="controls">
						 
							<select  name="role" id="role">
								<option value="manager" >Manager</option>
								<option value="developer" >Developer</option>
								<option value="tester" >Tester</option>
							</select>
						
						</div>

					</div>

					
					<div class="control-group">
						<label class="control-label" for="gender">Gender</label>
						<div class="controls">
							<div id="gender" name="gender" class="btn-group" data-toggle="buttons-radio">
								<button type="button" class="btn btn-info">Male</button>
								<button type="button" class="btn btn-warning active">
									<i class="icon-white icon-certificate"></i>&nbsp;God knows what
								</button>
								<button type="button" class="btn btn-info">Female</button>
							</div>				
						</div>
					</div>	
					
					

					<div class="control-group">

						<div class="controls">
							<button type="submit" class="btn btn-success" rel="tooltip"
								title="first tooltip">
								<i class="icon-edit icon-white"></i>&nbsp;Sign UP
							</button>
							<button type="reset" class="btn">
								<i class="icon-repeat icon-black"></i>&nbsp;Clear
							</button>
						</div>
					</div>

				</fieldset>
			</form>
		</div>

		<div class="modal-footer">
			<button class="btn btn-danger" data-dismiss="modal"
				area-hidden="true">
				<i class="icon-remove icon-white"></i>&nbsp;Close
			</button>
		</div>
	</div>

	<script type="text/javascript" src="js/jquery-validation.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#registerHere").validate({

				rules : {
					user_name : "required",
					user_email : {
						required : true,
						email : true
					},
					pwd : {
						required : true,
						minlength : 6
					},
					cpwd : {
						required : true,
						equalTo : "#pwd"
					},
					gender : "required"
				},

				messages : {
					user_name : "Enter your first and last name",
					user_email : {
						required : "Enter your email address",
						email : "Enter valid email address"
					},
					pwd : {
						required : "Enter your password",
						minlength : "Password must be minimum 6 characters"
					},
					cpwd : {
						required : "Confirm your password",
						equalTo : "Password and Confirm Password must match"
					},
					gender : "Select your Gender"
				},

				errorClass : "help-inline",

				//errorElement: "span",

				errorPlacement : function(error, element) {
					var type = $(element).attr("type");
					if (type === "radio") {
						error.insertAfter(element).wrap('<li/>');
					} else if (type === "checkbox") {
						error.insertAfter(element).wrap('<li/>');
					} else {
						error.insertAfter(element).wrap('<div/>');
					}
				},

				highlight : function(element, errorClass, validClass) {
					$(element).parents('.control-group').addClass('error');
				},

				unhighlight : function(element, errorClass, validClass) {
					$(element).parents('.control-group').removeClass('error');
					$(element).parents('.control-group').addClass('success');
				}
			});
		});
	</script>
</body>
</html>