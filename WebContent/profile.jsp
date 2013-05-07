<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>profile</title>


<link type="text/css" rel="stylesheet" href="css/smart_tab_vertical.css" />
<link type="text/css" rel="stylesheet" href="css/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="css/datepicker.css" />

<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery.smartTab.js"></script>
<script type="text/javascript" src="js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="js/jquery-validation.js"></script>
<script src="js/bootstrap.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		// Smart Tab
		$('#tabs').smartTab({
			
			autoProgress : false,
			stopOnFocus : true,
			transitionEffect : 'vSlide'
		});
	});
</script>
</head>
<body>
	<table align="center" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top">
				<!-- Tabs -->
				<div id="tabs">
					<ul>
						<li><a href="#personInfo">Tab 1<br /> <small>Person
									info</small>
						</a></li>
						<li><a href="#accountInfo">Tab 2<br /> <small>Account
									info</small>
						</a></li>
						<li><a href="#skills">Tab 3<br /> <small>Skills</small>
						</a></li>
						<li><a href="#additionalInfo">Tab 4<br /> <small>Additional
									info</small>
						</a></li>
						<li><button type="submit" class="btn btn-success span2 " rel="tooltip"
								title="first tooltip">
								<i class="icon-edit icon-white"></i>&nbsp;Update profile
							</button></li>
					</ul>
					<div class="well" id="personInfo">

						<div class="alert alert-success">Person's info</div>

						<form class="form-horizontal" id="inputPersonInfo" method='post'
							action="">


							<div class="control-group">
								<label class="control-label">First name</label>
								<div class="controls">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-user"></i></span> <input
											type="text" class="input-xlarge" id="user_first_name"
											name="user_first_name" placeholder="First name">
									</div>
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">Last name</label>
								<div class="controls">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-user"></i></span> <input
											type="text" class="input-xlarge" id="user_email"
											name="user_last_name" placeholder="Last name">
									</div>
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">Birthday</label>
								<div class="controls">
									<div class="input-append date" id="birthday"
										data-date="1991-04-28" data-date-format="yyyy-mm-dd">
										<input class="span2" size="16" type="text" value="yyyy-mm-dd"
											readonly> <span class="add-on"><i
											class="icon-calendar"></i></span>
									</div>

								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="gender">Gender</label>
								<div class="controls">
									<div id="gender" name="gender" class="btn-group"
										data-toggle="buttons-radio">
										<button type="button" class="btn btn-info">Male</button>
										<button type="button" class="btn btn-warning active">
											<i class="icon-white icon-certificate"></i>&nbsp;God knows
											what
										</button>
										<button type="button" class="btn btn-info">Female</button>
									</div>
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="gender">Qualification
									level</label>
								<div class="controls">
									<select name="qLevel" id="qLevel">
										<option value="junior">Junior</option>
										<option value="middle">Middle</option>
										<option value="senior">Senior</option>
										<option value="joda">Joda</option>
									</select>
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">Experience</label>
								<div class="controls">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-briefcase"></i></span> <input
											type="text" class="input-xlarge" id="exp" name="exp"
											placeholder="experience">
									</div>
								</div>
							</div>

						</form>
					</div>


					<div class="well" id="accountInfo">
						<div class="alert alert-success">Account info</div>

						<form class="form-horizontal" id="inputAccauntInfo" method='post'
							action="">

							<div class="control-group">
								<label class="control-label">New Email</label>
								<div class="controls">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-envelope"></i></span> <input
											type="text" class="input-xlarge" id="new_user_email"
											name="new_user_email" placeholder="arkadiy.dobkin@epam.com">
									</div>
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="input01">Old Password</label>
								<div class="controls">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-lock"></i></span> <input
											type="password" class="input-xlarge" id="old_pwd"
											name="old_pwd" placeholder="arkasha123">
									</div>
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="input01"> New Password</label>
								<div class="controls">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-lock"></i></span> <input
											type="password" class="input-xlarge" id="new_pwd"
											name="new_pwd" placeholder="arkasha123">
									</div>
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="input01">Confirm New
									Password</label>
								<div class="controls">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-lock"></i></span> <input
											type="password" class="input-xlarge" id="c_new_pwd"
											name="c_new_pwd" placeholder="arkasha123">
									</div>
								</div>
							</div>

							<div class="fileupload fileupload-new" data-provides="fileupload">
								<div class="input-append">
									<div class="uneditable-input span3">
										<i class="icon-file fileupload-exists"></i> <span
											class="fileupload-preview"></span>
									</div>
									<span class="btn btn-file"><span class="fileupload-new">Select
											file</span><span class="fileupload-exists">Change</span><input
										type="file" /></span><a href="#" class="btn fileupload-exists"
										data-dismiss="fileupload">Remove</a>
								</div>
							</div>


						</form>
					</div>

					<div class="well" id="skills">
					     <div class="alert alert-success">Skills info</div>
					       <form class="form-horizontal" id="inputSkillInfo" method='post'
							action="">
							 
							
							
							</form>
						
					</div>
					<div id="additionalInfo">
						<h2>Tab 4 Content</h2>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
							sed do eiusmod tempor incididunt ut labore et dolore magna
							aliqua. Ut enim ad minim veniam, quis nostrud exercitation
							ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis
							aute irure dolor in reprehenderit in voluptate velit esse cillum
							dolore eu fugiat nulla pariatur. Excepteur sint occaecat
							cupidatat non proident, sunt in culpa qui officia deserunt mollit
							anim id est laborum.</p>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
							sed do eiusmod tempor incididunt ut labore et dolore magna
							aliqua. Ut enim ad minim veniam, quis nostrud exercitation
							ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis
							aute irure dolor in reprehenderit in voluptate velit esse cillum
							dolore eu fugiat nulla pariatur. Excepteur sint occaecat
							cupidatat non proident, sunt in culpa qui officia deserunt mollit
							anim id est laborum.</p>
					</div>
				</div>
			</td>
		</tr>
	</table>


	<script>
		$(function() {

			$('#birthday').datepicker({
				format : 'yyyy-mm-dd'
			});

		});
	</script>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#registerHere").validate({

				rules : {
					user_first_name : "required",
					user_last_name : "required",
					new_user_email : {
						required : true,
						email : true
					},
					old_pwd : {
						required : true,
						minlength : 6
					},
					new_pwd : {
						required : true,
						minlength : 6
					},
					c_new_pwd : {
						required : true,
						equalTo : "#new_pwd"
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