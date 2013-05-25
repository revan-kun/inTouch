<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>In Touch</title>
<link id="favicon" rel="shortcut icon" href="img/red.ico" />	
<link type="text/css" rel="stylesheet" href="css/bootstrap-select.css" />
<link type="text/css" rel="stylesheet" href="css/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="css/bootstrap-responsive.css" />
<link type="text/css" rel="stylesheet" href="css/style.css" />

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/bootstrap-select.js"></script>

</head>
<body>
	<div class="modal" id="registration">

		<div class="modal-body">

			<div class="alert alert-success" align="center">Fill up the registration form
				below to proceed</div>

			<form class="form-horizontal" id="registerHere" method='post'
				action='memberRegistration'>
				<fieldset>

					<legend>Create your personal inTouch account</legend>

					<div class="control-group">
						<label class="control-label">First Name</label>
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on"><i class="icon-user"></i></span> <input
									type="text" class="input-xlarge" id="memberFirstName"
									name="memberFirstName" placeholder="Your first name...">
							</div>
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label">Last Name</label>
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on"><i class="icon-user"></i></span> <input
									type="text" class="input-xlarge" id="memberLastName"
									name="memberLastName" placeholder="Your last name...">
							</div>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label">Email</label>
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on"><i class="icon-envelope"></i></span> <input
									type="text" class="input-xlarge" id="memberLogin"
									name="memberLogin" placeholder="Your email...">
							</div>
						</div>
					</div>
					
					<div id="snap" class="alert alert-block alert-error fade in" style="display: none">
							<button type="button" class="close" data-hide="alert">×</button>
							
							<p id="errorMassage"></p>
						</div>

					<div class="control-group">
						<label class="control-label" for="input01">Password</label>
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on"><i class="icon-lock"></i></span> <input
									type="password" class="input-xlarge" id="memberPassword" name="memberPassword"
									placeholder="password...">
							</div>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="input01">Confirm
							Password</label>
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on"><i class="icon-lock"></i></span> <input
									type="password" class="input-xlarge" id="conf_memberPassword" name="conf_memberPassword"
									placeholder="password...">
							</div>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="memberProjectRole">Role</label>
						<div class="controls">
						 
							<select  name="memberProjectRole" id="memberProjectRole">
								<option value="manager" >Manager</option>
								<option value="developer" >Developer</option>
								<option value="tester" >Tester</option>
							</select>
						
						</div>

					</div>

					
					<div class="control-group">
						<label class="control-label" for="memberSex">Gender</label>
						<div class="controls">
						
						<input type="hidden" id="memberSex" name="memberSex" value="" />
							<div id="btn_memberSex" name="btn_memberSex" class="btn-group"
								data-toggle="buttons-radio">
								<button type="button"  value="Male"
									class="btn btn-info">Male</button>
								<button type="button"  value="Female"
									class="btn btn-warning active">
									<i class="icon-white icon-certificate"></i>&nbsp;God knows what
								</button>
								<button type="button"  value="Female"
									class="btn btn-info">Female</button>
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
			<a href="home" class="btn btn-danger"><i class="icon-white icon-remove"></i> Close</a>
		</div>
	</div>
	
	<script type="text/javascript">
	$(".btn-group .btn").click(function() {
	    $("#memberSex").val($(this).val());
	   
	}); 
		
	</script>

	<script type="text/javascript" src="js/jquery-validation.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#registerHere").validate({

				rules : {
					memberFirstName : "required",
					memberLastName : "required",
					memberLogin : {
						required : true,
						email : true
					},
					memberPassword : {
						required : true,
						minlength : 6
					},
					conf_memberPassword : {
						required : true,
						equalTo : "#memberPassword"
					},
					memberSex : "required"
				},

				messages : {
					memberFirstName : "Enter your first  name",
					memberLastName : "Enter your last name",
					memberLogin : {
						required : "Enter your email address",
						email : "Enter valid email address"
					},
					memberPassword : {
						required : "Enter your password",
						minlength : "Password must be minimum 6 characters"
					},
					conf_memberPassword : {
						required : "Confirm your password",
						equalTo : "Password and Confirm Password must match"
					},
					memberSex : "Select your Gender"
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
	
	<script type="text/javascript">
	$(document).ready(function(){

		$("#memberLogin").change(function() { 
		var usr = $("#memberLogin").val();
		    $.ajax({  
		    type: "POST",  
		    url: "check",  
		    data: "memberLogin="+ usr,  
		    success: function(data){  
		    	alert(data);
		    	if(data != 'OK')
		    		{
		    		$('#snap').show({
		    		});
					$("#errorMassage").text(data);
					$("#memberLogin").val("");
		    		}
		   
		 	  }
			 }); 
		   
			});		
		});
	
	</script>
</body>
</html>