<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>profile</title>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<link type="text/css" rel="stylesheet" href="css/smart_tab_vertical.css" />
<link type="text/css" rel="stylesheet" href="css/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="css/datepicker.css" />
<link type="text/css" rel="stylesheet"
	href="css/bootstrap-fileupload.css" />


<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery.smartTab.js"></script>
<script type="text/javascript" src="js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="js/jquery-validation.js"></script>
<script type="text/javascript" src="js/bootstrap-filestyle.min.js"></script>
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
											value='<c:out value="${member.firstName }"></c:out>' />
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
									<div class="input-append date" id="memberBirthday" data-date="2013-05-01" >
										<input class="span2" id="memberBirthday" name="memberBirthday"
											size="16" type="text" placeholder="yyyy-MM-dd"
											value='<fmt:formatDate value="${member.birthday}" pattern="yyyy-MM-dd"/>'
											readonly> <span class="add-on"><i
											class="icon-calendar"></i></span>
									</div>

								</div>
							</div>


							<div class="control-group">
								<label class="control-label" for="memberQualification">Qualification
									level</label>
								<div class="controls">
									<c:set var="qLevels"
										value="null,Junior,Middle,Senior,Joda,GodLike"
										scope="application"></c:set>
									<select name="memberQualification" id="memberQualification">
										<c:forEach items="${fn:split(qLevels, ',')}" var="qLevel">
											<option value="${qLevel}"
											
												${ member.qualificationLevel.qualificationLevel == qLevel ? 'selected' : ''}>${qLevel}</option>
									<!--  	<c:out value="${ member.qualificationLevel}"></c:out> -->
										</c:forEach>

									</select>
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="memberSex">Gender</label>
								<div class="controls">

									<input type="hidden" id="memberSex" name="memberSex"
										value="<jsp:getProperty property="sex" name="member"/>" />
									<div id="btn_memberSex" name="btn_memberSex" class="btn-group"
										data-toggle="buttons-radio">
										<button type="button" id="MALE" value="Male" class="btn ">Male</button>

										<button type="button" id="FEMALE" value="Female" class="btn ">Female</button>
									</div>



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
							<div class="control-group">

								<div class="controls">
									<button type="submit" class="btn btn-success span2 "
										rel="tooltip" title="first tooltip">
										<i class="icon-edit icon-white"></i>&nbsp;Update profile
									</button>
								</div>
							</div>

						</form>
					</div>


					<div class="well" id="accountInfo">
						<div class="alert alert-success">Account info</div>

						<form class="form-horizontal" id="inputAccauntInfo" method='post'
							action="profile">

							<div class="control-group">
								<label class="control-label">New Email</label>
								<div class="controls">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-envelope"></i></span> <input
											type="text" class="input-xlarge" id="memberLogin"
											name="memberLogin" placeholder="arkadiy.dobkin@epam.com"
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
											type="password" class="input-xlarge" id="memberPassword"
											name="memberPassword" placeholder="arkasha123">
									</div>
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="input01">Confirm New
									Password</label>
								<div class="controls">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-lock"></i></span> <input
											type="password" class="input-xlarge" id="conf_memberPassword"
											name="conf_memberPassword" placeholder="arkasha123">
									</div>
								</div>
							</div>

							<div class="control-group">

								<div class="controls">
									<button type="submit" class="btn btn-success"
										title="first tooltip">
										<i class="icon-edit icon-white"></i>&nbsp;Update
									</button>


								</div>
							</div>
						</form>
						<form id="file_attachment" action="attachment"
							enctype="multipart/form-data" method="post">
							<div class="control-group">

								<div class="controls">

									<input type="file" id="avatarUpload" name="avatarUpload"
										accept="image/*">

									<button type="submit" class="btn btn-info"
										title="first tooltip">
										<i class="icon-edit icon-white"></i>&nbsp;Upload image
									</button>

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

												<label>Skill Name</label>

												<c:forEach items="${memberProgrammingSkills}"
													var="memProgSkill" varStatus="counter">
													<div class="row">
														<select name="skillName" id="skillName">
															<c:forEach items="${programmingSkills}" var="progSkill">
																<option value="${progSkill.name}"
																	${ memProgSkill.name == progSkill.name ? 'selected' : ''}>${progSkill.name}</option>
															</c:forEach>

														</select>

													</div>

												</c:forEach>


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
											<div class="accordion-inner">
												<div class="well">

													<label>Skill Name</label>
													<c:forEach items="${languageSkills}" var="skill1">

														<!--First Row -->
														<div class="row">
															<input type="text" class="span2" value="${skill1.name}">
														</div>

													</c:forEach>

												</div>
											</div>
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
								placeholder="Type here your additional info..." rows="8"><jsp:getProperty
									property="additionalInfo" name="member" /></textarea>
						</form>

					</div>
				</div>
			</td>
		</tr>
	</table>


	<script type="text/javascript">
		$('#avatarUpload').filestyle({
			icon : false
		});
	</script>
	<script type="text/javascript">
		$(document).ready(function() {

			$(':file').change(function() {

				var file = this.files[0];
				size = file.size;
				type = file.type;
				error = "";

				if (size > 2097152)
					error = "File to big choose less then 2 Mb\n";

				if (type.indexOf("image") == -1)
					error = error + "File is not image";

				if (error == "") {
					
				} else {
					alert(error);
					$("#file_attachment").each(function() {
						this.reset();
					});
				}
			});
		});
	</script>

	<script type="text/javascript">
		$(function() {
			var gender = '[id="' + $("#memberSex").val() + '"]';
			//alert(gender);
			$(gender).button('toggle');

			$(".btn-group .btn").click(function() {
				$("#memberSex").val($(this).val());

			});

		});
	</script>


	<script>
		 $(function() {

		
			var nowTemp = new Date();
			var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(),
					nowTemp.getDate(), 0, 0, 0, 0);
			
			
			 $('#memberBirthday').datepicker({
				  format : 'yyyy-mm-dd',
				onRender : function(date) {
					return date.valueOf() > now.valueOf() ? 'disabled' : '';
				}
			}); 
			
			var checkout = $('#memberBirthday').datepicker({
				  onRender: function(date) {
				    return date.valueOf() > now.valueOf() ? 'disabled' : '';
				  }
				}).on('changeDate', function(ev) {
				  checkout.hide();
				}).data('datepicker');

		}); 
		
	</script>



	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$("#inputAccauntInfo")
									.validate(
											{

												rules : {

													memberLogin : {
														required : true,
														email : true
													},
													old_pwd : {
														required : true,
														minlength : 6
													},
													memberPassword : {
														required : true,
														minlength : 6
													},
													conf_memberPassword : {
														required : true,
														equalTo : "#memberPassword"
													},
													
												},

												messages : {

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
													
												},

												errorClass : "help-inline",

												//errorElement: "span",

												errorPlacement : function(
														error, element) {
													var type = $(element).attr(
															"type");
													if (type === "radio") {
														error.insertAfter(
																element).wrap(
																'<li/>');
													} else if (type === "checkbox") {
														error.insertAfter(
																element).wrap(
																'<li/>');
													} else {
														error.insertAfter(
																element).wrap(
																'<div/>');
													}
												},

												highlight : function(element,
														errorClass, validClass) {
													$(element).parents(
															'.control-group')
															.addClass('error');
												},

												unhighlight : function(element,
														errorClass, validClass) {
													$(element).parents(
															'.control-group')
															.removeClass(
																	'error');
													$(element)
															.parents(
																	'.control-group')
															.addClass('success');
												}
											});
						});
	</script>

</body>
</html>