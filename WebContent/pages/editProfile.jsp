<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>In Touch</title>
<link id="favicon" rel="shortcut icon" href="img/green.ico" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<link type="text/css" rel="stylesheet" href="css/smart_tab_vertical.css" />
<link type="text/css" rel="stylesheet" href="css/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="css/datepicker.css" />
<link type="text/css" rel="stylesheet"
	href="css/bootstrap-responsive.css" />
<link type="text/css" rel="stylesheet"
	href="css/bootstrap-fileupload.css" />
<link type="text/css" rel="stylesheet" href="css/choosen/chosen.css" />


<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery.smartTab.js"></script>
<script type="text/javascript" src="js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="js/jquery-validation.js"></script>
<script type="text/javascript" src="js/bootstrap-filestyle.min.js"></script>
<script type="text/javascript" src="js/chosen.jquery.js"></script>
<script src="js/bootstrap.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		// Smart Tab
		$('#tabs').smartTab({

			autoProgress : false,
			stopOnFocus : true,
			autoHeight : true,
			transitionEffect : 'vSlide'
		});
	});
</script>
<style type="text/css">
body {
	background: url('./img/backs/fabric.png');
	padding-top: 90px;
	padding-bottom: 300px;
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
						<li><a href="home"> Home </a></li>
						<!-- <li><a href="error404.html"> More Information </a></li> -->
						<li class="active"><a href="editProfile.jsp"> Edit
								Profile </a></li>
					</ul>
					<div id="user_signed" class="pull-right">
						<ul class="nav pull-right">
							<li class="dropdown"><a id="welcome_user" href="#"
								class="dropdown-toggle" data-toggle="dropdown"> <b
									class="caret"></b> Welcome, <c:out value="${member.firstName }"></c:out>
							</a>
								<ul class="dropdown-menu">
									<li><a href="member_profile.jsp"><i class="icon-user"></i>
											Profile</a></li>
									<li><a href="/help/support"><i class="icon-envelope"></i>
											Contact Support</a></li>
									<li class="divider"></li>
									<li><a href="logout"><i class="icon-off"></i> Logout</a></li>
								</ul></li>
						</ul>
					</div>
				</div>

				<form class="navbar-search form-search pull-right text-center"
					id="search_form" action="member_search" method="get">
					<div class="input-append">
						<input type="search" class="search-query span3" name="query"
							autocomplete="off" placeholder="search..." tabindex="1">
						<button type="submit" class="btn" id="search" data-trigger="hover"
							data-placement="bottom" data-content="Press for advanced search">
							<!-- <span class="caret"></span> -->
							<i class="icon-plus"></i> <i class="icon-search icon-large"></i>
						</button>
					</div>
				</form>

			</div>
		</div>
	</div>



	<table align="center" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top">

				<div id="tabs" style="height:500px">
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
					<div class="well pagination-centered" id="personInfo"
						style="width: 710px">

						<div class="alert alert-success">Person's info</div>

						<form class="form-horizontal" id="inputPersonInfo" method='post'
							action="updateProfile">


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
									<div class="input-append date" id="memberBirthday"
										data-date="2013-05-01">
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
										value="null,Junior,Middle,Senior,Joda,Godlike"
										scope="application"></c:set>
									<select name="memberQualification" id="memberQualification">
										<c:forEach items="${fn:split(qLevels, ',')}" var="qLevel">
											<option value="${qLevel}"
												${ member.qualificationLevel.qualificationLevel == qLevel ? 'selected' : ''}>${qLevel}</option>

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
											type="number" min="0" max="30" class="span1"
											id="memberExperience" name="memberExperience"
											placeholder="experience"
											value='<c:out value="${member.experience }"></c:out>' />
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


					<div class="well" id="accountInfo" style="width: 710px">
						<div class="alert alert-success">Account info</div>

						<form class="form-horizontal" id="inputAccauntInfo" method='post'
							action="updateProfile">

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
						<form id="file_attachment" action="fileUpload"
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

					<div class="well pagination-centered" style="width: 710px"
						id="skills">
						<div class="alert alert-success">Skills info</div>
						<form class="form-horizontal" id="inputSkillInfo" method='post'
							action="">
							<legend>Contact Form</legend>


							<div>
								<c:forEach items="${memberProgrammingSkills}" var="memProgSkill"
									varStatus="counter">
									<select data-placeholder="Your Favorite Programming language"
										style="width: 350px;" multiple class="chzn-select"
										name="skillName" id="skillName">
										<c:forEach items="${programmingSkills}" var="progSkill">
											<option value="${progSkill.name}"
												${ memProgSkill.name == progSkill.name ? 'selected' : ''}>${progSkill.name}</option>
										</c:forEach>
									</select>

								</c:forEach>
							</div>
							
							<legend>Contact Form</legend>


							<div>
								<c:forEach items="${memberProgrammingSkills}" var="memProgSkill"
									varStatus="counter">
									<select data-placeholder="Your Favorite Programming language"
										style="width: 350px;" multiple class="chzn-select"
										name="skillName" id="skillName">
										<c:forEach items="${programmingSkills}" var="progSkill">
											<option value="${progSkill.name}"
												${ memProgSkill.name == progSkill.name ? 'selected' : ''}>${progSkill.name}</option>
										</c:forEach>
									</select>

								</c:forEach>
							</div>
							
							<legend>Contact Form</legend>


							<div>
								<c:forEach items="${memberProgrammingSkills}" var="memProgSkill"
									varStatus="counter">
									<select data-placeholder="Your Favorite Programming language"
										style="width: 350px;" multiple class="chzn-select"
										name="skillName" id="skillName">
										<c:forEach items="${programmingSkills}" var="progSkill">
											<option value="${progSkill.name}"
												${ memProgSkill.name == progSkill.name ? 'selected' : ''}>${progSkill.name}</option>
										</c:forEach>
									</select>

								</c:forEach>
							</div>





							<%-- <div class="container span5 ">
								<div class="accordion" id="accordionSkills">

									 <div class="accordion-group">
										<div class="accordion-heading">
											<a class="accordion-toggle" data-toggle="collapse"
												data-parent="#accordionSkills" href="#ProgrammingSkills">
												Programming Skills </a>
										</div>
										<div id="ProgrammingSkills" class="accordion-body collapse in">
											<div class="accordion-inner">
												<div>
													<c:forEach items="${memberProgrammingSkills}"
														var="memProgSkill" varStatus="counter">

														<select
															data-placeholder="Your Favorite Programming language"
															style="width: 350px;" multiple class="chzn-select"
															name="skillName" id="skillName">
															<c:forEach items="${programmingSkills}" var="progSkill">
																<option value="${progSkill.name}"
																	${ memProgSkill.name == progSkill.name ? 'selected' : ''}>${progSkill.name}</option>
															</c:forEach>
														</select>

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
											<div class="accordion-inner"></div>
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


														<div class="row">
															<input type="text" class="span2" value="${skill1.name}">
														</div>

													</c:forEach>

												</div>
											</div>
										</div>
									</div>
								</div>
							</div> --%>


						</form>

					</div>

					<div class="well pagination-centered" id="additionalInfo"
						style="width: 710px">
						<div class="alert alert-success">Additional info</div>
						<form class="contact-us form-horizontal" id="inputAccauntInfo"
							method='post' action="updateProfile">
							<div class="control-group">
								<label class="control-label">Additional Information</label>
								<div class="controls">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-pencil"></i></span>
										<textarea name="memberAdditionalInfo" class="span5" rows="8"
											placeholder="Type here your additional info...">
											<c:out value="${member.additionalInfo}"></c:out>
										</textarea>
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
						<!--  <form class="bs-docs-example form-inline">
								<textarea class="field span8" id="textarea" name="user_input"
									placeholder="" rows="8"><jsp:getProperty
										property="additionalInfo" name="member" /></textarea>
							</form>
							-->
					</div>
				</div>
			</td>
		</tr>
	</table>
	
	<div>
								<c:forEach items="${memberProgrammingSkills}" var="memProgSkill"
									varStatus="counter">
									<select data-placeholder="Your Favorite Programming language"
										style="width: 350px;" multiple class="chzn-select"
										name="skillName" id="skillName">
										<c:forEach items="${programmingSkills}" var="progSkill">
											<option value="${progSkill.name}"
												${ memProgSkill.name == progSkill.name ? 'selected' : ''}>${progSkill.name}</option>
										</c:forEach>
									</select>

								</c:forEach>
							</div>
<script type="text/javascript">
$(function() {
    var els = jQuery(".chzn-select");
    els.chosen({no_results_text: "No results matched"});
    els.on("liszt:showing_dropdown", function () {
            $(this).parents("table").css("overflow", "visible");
            
        });
    els.on("liszt:hiding_dropdown", function () {
            $(this).parents("table").css("overflow", "");
        });
});
</script>

<script>
/* var parentEls = $("b").parents()
            .map(function () {
                  return this.tagName;
                })
            .get().join(", ");
$("b").append("<strong>" + parentEls + "</strong>"); */
</script>

	 <div class="accordion" id="accordionSkills1"> 

		 <div class="accordion-group">
			<div class="accordion-heading">
				<a class="accordion-toggle" data-toggle="collapse"
					data-parent="#accordionSkills1" href="#ProgrammingSkills11">
					Programming Skills </a>
			</div>
			<div id="ProgrammingSkills11" class="accordion-body collapse in">
				<div class="accordion-inner">

					<c:forEach items="${memberProgrammingSkills}" var="memProgSkill"
						varStatus="counter">

						<select data-placeholder="Your Favorite Programming language"
							style="width: 350px;" multiple class="chzn-select"
							name="skillName" id="skillName">
							<c:forEach items="${programmingSkills}" var="progSkill">
								<option value="${progSkill.name}"
									${ memProgSkill.name == progSkill.name ? 'selected' : ''}>${progSkill.name}</option>
							</c:forEach>
						</select>

					</c:forEach>

				</div>
			</div>
		</div>
		
		<div class="accordion-group">
			<div class="accordion-heading">
				<a class="accordion-toggle" data-toggle="collapse"
					data-parent="#accordionSkills1" href="#111">
					Programming Skills </a>
			</div>
			<div id="111" class="accordion-body collapse">
				<div class="accordion-inner">

					<c:forEach items="${memberProgrammingSkills}" var="memProgSkill"
						varStatus="counter">

						<select data-placeholder="Your Favorite Programming language"
							style="width: 350px;" multiple class="chzn-select"
							name="skillName" id="skillName">
							<c:forEach items="${programmingSkills}" var="progSkill">
								<option value="${progSkill.name}"
									${ memProgSkill.name == progSkill.name ? 'selected' : ''}>${progSkill.name}</option>
							</c:forEach>
						</select>

					</c:forEach>

				</div>
			</div>
		</div>
	</div> 




	<div class="row-fluid">
		<div class="span12 well" style="height: 100px">
			<p class="lead" style="text-align: center">
				The <strong>inTouch</strong> <br /> May The Force Be With Us,
				&nbsp;© 2013 inTouchTeam
			</p>
		</div>
	</div>
	<script type="text/javascript">
	var config = {
		      '.chzn-select'           : {},
		      '.chzn-select-deselect'  : {allow_single_deselect:true},
		      '.chzn-select-no-single' : {disable_search_threshold:10},
		      '.chzn-select-no-results': {no_results_text:'Oops, nothing found!'},
		      '.chzn-select-width'     : {width:"95%"}
		    }
		    
		for ( var selector in config) {
			$(selector).chosen(config[selector]);
		}
	</script>

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
				onRender : function(date) {
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
														email : true,
														maxlength : 40
													},
													old_pwd : {
														required : true,
														minlength : 6,
														maxlength : 20
													},
													memberPassword : {
														required : true,
														minlength : 6,
														maxlength : 20
													},
													conf_memberPassword : {
														required : true,
														equalTo : "#memberPassword"
													},

												},

												messages : {

													memberLogin : {
														required : "Enter your email address",
														email : "Enter valid email address",
														maxlength : "Login lenght must be no longer then 40 characters"
													},
													memberPassword : {
														required : "Enter your password",
														minlength : "Password must be minimum 6 characters",
														maxlength : "Password lenght must be no longer then 20 characters"
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