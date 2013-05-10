<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>profile</title>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


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
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}
</style>
</head>
<body>
	<jsp:useBean id="member"
		class="com.epam.lab.intouch.model.member.Member" scope="session">
	</jsp:useBean>

	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">

				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="index.html">inTouch</a>

				<div class="nav-collapse collapse">
					<ul class="nav nav-pills">
						<li><a href="index.html"> Home </a></li>
						<li><a href="error404.html"> More Information </a></li>
						<li class="active"><a href="error404.html"> TestProfile </a>
						</li>
					</ul>
				</div>

				<form class="navbar-search pull-right hidden-phone" id="search_form"
					action="http://intouch.com/search/" method="get">
					<input type="text" autocomplete="off" class="search-query span2"
						placeholder="search..." name="query" id="search_query" value="">
				</form>

			</div>
		</div>
	</div>

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
						<li><button type="submit" class="btn btn-success span2 "
								rel="tooltip" title="first tooltip">
								<i class="icon-edit icon-white"></i>&nbsp;Update profile
							</button></li>
					</ul>
					<div class="well" id="personInfo">

						<div class="alert alert-success">Person's info</div>

						<form class="form-horizontal" id="inputPersonInfo" method='post'
							action="profile">


							<div class="control-group">
								<label class="control-label">First name</label>
								<div class="controls">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-user"></i></span> <input
											type="text" class="input-xlarge" id="memberFirstName"
											name="memberFirstName"
											value='<jsp:getProperty property="firstName" name="member"/>' />
									</div>
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">Last name</label>
								<div class="controls">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-user"></i></span> <input
											type="text" class="input-xlarge" id="memberLastName"
											name="memberLastName"
											value='<jsp:getProperty property="lastName" name="member"/>'>
									</div>
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">Birthday</label>
								<div class="controls">
									<div class="input-append date" id="memberBirthday"
										data-date="1990-01-01" data-date-format="yyyy-mm-dd">
										<input class="span2" name="memberBirthday" size="16"
											type="text" placeholder="yyyy-mm-dd"
											value='<jsp:getProperty property="birthday" name="member"/>'
											readonly> <span class="add-on"><i
											class="icon-calendar"></i></span>
									</div>

								</div>
							</div>


							<div class="control-group">
								<label class="control-label" for="memberQualification">Qualification
									level</label>
								<div class="controls">
									<select name="memberQualification" id="memberQualification">

										<option value="JUNIOR">Junior</option>
										<option value="MIDDLE">Middle</option>
										<option value="SENIOR">Senior</option>
										<option value="JODA">Joda</option>
										<option value="GODLIKE">GodLike</option>
									</select>
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">Experience</label>
								<div class="controls">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-briefcase"></i></span> <input
											type="text" class="input-xlarge" id="memberExperience"
											name="memberExperience" placeholder="experience"
											value='<jsp:getProperty property="experience" name="member"/> ' />
									</div>
								</div>
							</div>
							<button type="submit" class="btn btn-success span2 "
								rel="tooltip" title="first tooltip">
								<i class="icon-edit icon-white"></i>&nbsp;Update profile
							</button>

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
											name="new_user_email" placeholder="arkadiy.dobkin@epam.com"
											value="<jsp:getProperty property="login" name="member"/> ">
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
							<div class="bs-docs-example">
								<div class="accordion" id="accordionSkills">
									<div class="accordion-group">
										<div class="accordion-heading">
											<a class="accordion-toggle" data-toggle="collapse"
												data-parent="#accordionSkills" href="#ProgrammingSkills">
												Programming Skills </a>
										</div>
										<div id="ProgrammingSkills" class="accordion-body collapse in">
											<div class="accordion-inner"></div>
											<div class="well">
												<div class="row-fluid">
													<c:forEach items="${member.skills}" var="skill">
													   
														<!--First Row -->
														<div class="span3">
															<label>Skill Name</label> <input type="text"
																class="span12" id="input01" value="${skill.name}">
														</div>
														<div class="span3">
															<label>Skill Level</label> <input type="text"
																class="span12" id="input01" value="${skill.level}">
														</div>
													</c:forEach>
												</div>
											</div>

											


										</div>
									</div>
									<div class="accordion-group">
										<div class="accordion-heading">
											<a class="accordion-toggle" data-toggle="collapse"
												data-parent="#accordionSkills" href="#technologySkills">
												Technology Skills </a>
										</div>
										<div id="technologySkills" class="accordion-body collapse">
											<div class="accordion-inner">Anim pariatur cliche
												reprehenderit, enim eiusmod high life accusamus terry
												richardson ad squid. 3 wolf moon officia aute, non cupidatat
												skateboard dolor brunch. Food truck quinoa nesciunt laborum
												eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird
												on it squid single-origin coffee nulla assumenda shoreditch
												et. Nihil anim keffiyeh helvetica, craft beer labore wes
												anderson cred nesciunt sapiente ea proident. Ad vegan
												excepteur butcher vice lomo. Leggings occaecat craft beer
												farm-to-table, raw denim aesthetic synth nesciunt you
												probably haven't heard of them accusamus labore sustainable
												VHS.</div>
										</div>
									</div>
									<div class="accordion-group">
										<div class="accordion-heading">
											<a class="accordion-toggle" data-toggle="collapse"
												data-parent="#accordionSkills" href="#languageSkills">
												Language Skills </a>
										</div>
										<div id="languageSkills" class="accordion-body collapse">
											<div class="accordion-inner">Anim pariatur cliche
												reprehenderit, enim eiusmod high life accusamus terry
												richardson ad squid. 3 wolf moon officia aute, non cupidatat
												skateboard dolor brunch. Food truck quinoa nesciunt laborum
												eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird
												on it squid single-origin coffee nulla assumenda shoreditch
												et. Nihil anim keffiyeh helvetica, craft beer labore wes
												anderson cred nesciunt sapiente ea proident. Ad vegan
												excepteur butcher vice lomo. Leggings occaecat craft beer
												farm-to-table, raw denim aesthetic synth nesciunt you
												probably haven't heard of them accusamus labore sustainable
												VHS.</div>
										</div>
									</div>
								</div>
							</div>


						</form>

					</div>
					<div id="additionalInfo" class="well">
						<div class="alert alert-success">Additional info</div>
						<form class="bs-docs-example form-inline">
							<textarea class="field span8" id="textarea" name="user_input"
								placeholder="Type here your additional info..." rows="8" ><jsp:getProperty property="additionalInfo" name="member"/></textarea>
						</form>

					</div>
				</div>
			</td>
		</tr>
	</table>


	<script>
		$(function() {

			$('#memberBirthday').datepicker({
				format : 'yyyy-mm-dd'
			});
			$('#memberBirthday').datepicker().on('changeDate', function(ev) {

				$('#memberBirthday').datepicker('hide');
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