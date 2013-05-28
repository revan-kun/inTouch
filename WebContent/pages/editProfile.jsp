<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html lang="en-US">
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
			autoHeight : false,
			transitionEffect : 'vSlide'
		});
	});
</script>
<style type="text/css">
body {
	//background: url('./img/backs/fabric.png');
	padding-top: 90px;
	padding-bottom: 90px;
}
.brand {
		  	background: url('./img/robo.png') no-repeat left center;
		 	height: 20px;
		  	
		}
.well
{
//min-height: 100%;


}


</style>
</head>
<body>
	<jsp:useBean id="member"
		class="com.epam.lab.intouch.model.member.Member" scope="session">
	</jsp:useBean>
	
	<jsp:useBean id="currentDate" class="java.util.Date" scope="page" />


	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">

				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a>
				<a class="brand" href="home">
					&nbsp   inTouch
				</a>

				<div class="nav-collapse collapse">
					<ul class="nav nav-pills">
						<li><a href="home"> Home </a></li>
						<!-- <li><a href="error404.html"> More Information </a></li> -->
						<li class="active"><a href="editProfile"> Edit
								Profile </a></li>
					</ul>
					<div id="user_signed" class="pull-right">
						<ul class="nav pull-right">
							<li class="dropdown">
								<a id="welcome_user" href="#" class="dropdown-toggle" data-toggle="dropdown"> 
								Welcome, <c:out value="${member.firstName }"/> 
								<b class="caret"></b>
							</a>
								<ul class="dropdown-menu">
									<li><a href="memberProfile"><i class="icon-user"></i>
											Profile</a></li>
									<!-- <li><a href="/help/support"><i class="icon-envelope"></i>
											Contact Support</a></li> -->
									<li class="divider"></li>
									<li><a href="logout"><i class="icon-off"></i> Logout</a></li>
								</ul></li>
						</ul>
					</div>
				</div>

				<form class="navbar-search form-search pull-right text-center"
					id="search_form" action="member_search" method="get">
					<div class="input-append">
						<input type="search" maxlength="30" class="search-query span3" name="query"
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

				<div id="tabs" style="height: 500px">
					<ul style="text-align: center;">
						<li><a href="#personInfo"><img src='./img/icon-new/personal_info2.png' width='50' height='50' alt='V for Vendetta' /><br /> 
						<small>Person info</small>
						</a></li>
						<li><a href="#accountInfo"><img src='./img/icon-new/account.png' width='50' height='50' alt='V for Vendetta' /><br /> <small>Account
									info</small>
						</a></li>
						<li><a href="#skills"><img src='./img/icon-new/skill.png' width='50' height='50' alt='V for Vendetta' /><br /> <small>Skills</small>
						</a></li>
						<li><a href="#additionalInfo"><img src='./img/icon-new/add_info.png' width='50' height='50' alt='V for Vendetta' /><br /> <small>Additional
									info</small>
						</a></li>

					</ul>
					<div class="well" id="personInfo"
						style="width: 600px">

						<div class="alert alert-success">Person's info</div>

						<form class="form-horizontal" id="inputPersonInfo" method='post'
							action="updateProfile">


							<div class="control-group">
								<label class="control-label">First name</label>
								<div class="controls">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-user"></i></span> <input
											type="text" class="input-xlarge" id="memberFirstName"
											name="memberFirstName" required="required"
											value='<c:out value="${member.firstName }"></c:out>' 
											maxlength="20"/>
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
											value='<c:out value="${member.lastName }"></c:out>' maxlength="20">
									</div>
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">Birthday</label>
								<div class="controls">
									<div class="input-append date" id="memberBirthday"
										data-date="<fmt:formatDate value="${currentDate}" pattern="MM.dd.yyyy" />">
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
										value="Junior,Middle,Senior,Joda,Godlike"
										scope="application"></c:set>
									<select data-placeholder="Choose level" class="chzn-select" style="width:150px;" tabindex="2" name="memberQualification" id="memberQualification">
										<option value=""></option>
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
										value="<c:out value="${member.sex }"></c:out>" />
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
										    style="width: 50px;text-align: center;"
											type="number" min="0" max="30" required="required" step="any" maxlength="2"
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
										<i class="icon-edit icon-white"></i>&nbsp;Update
									</button>
								</div>
							</div>

						</form>
					</div>


					<div class="well" id="accountInfo" style="width: 600px">
						<div class="alert alert-success">Account info</div>

						<form class="form-horizontal" id="inputAccauntInfo" method='post'
							action="updateProfile">

							<div class="control-group">
								<label class="control-label">New Email</label>
								<div class="controls">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-envelope"></i></span> 
										<input type="text" maxlength="40" class="input-xlarge" id="memberLogin"
											name="login" placeholder="james.doe@epam.com"
											value="<c:out value="${member.login}"></c:out>">
									</div>
								</div>
							</div>
							
							<div id="snap" class="alert alert-block alert-error fade in" style="display: none">
								<button type="button" class="close" onclick="hideSnap('#snap', '#memberLogin')">×</button>			
								<p id="errorMessage"></p>
							</div>

							<div class="control-group">
								<label class="control-label" for="input01">Old Password</label>
								<div class="controls">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-lock"></i></span> <input
											type="password" class="input-xlarge" id="password"
											name="password" maxlength="20" placeholder="johny123old">
									</div>
								</div>
							</div>
							
							
							<div id="snap2" class="alert alert-block alert-error fade in" style="display: none">
								<button type="button" class="close" onclick="hideSnap('#snap2', '#password')">×</button>			
								<p id="errorMessage2"></p>
							</div>

							<div class="control-group">
								<label class="control-label" for="input01"> New Password</label>
								<div class="controls">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-lock"></i></span> <input
											type="password" class="input-xlarge" id="memberPassword"
											name="memberPassword" maxlength="20" placeholder="johny123new">
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
											name="conf_memberPassword" maxlength="20" placeholder="johny123new">
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
						
						<div id="snap3" class="alert alert-block alert-error fade in" style="display: none">
							<button type="button" class="close" onclick="hideAvatarSnap('#snap3')">×</button>
							<h4 class="alert-heading">Bollocks!! You got an error!</h4>
							<p id="errorUploadMassage"></p>
						</div>
						
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

					<div class="well" style="width: 600px" id="skills">
						<div class="alert alert-success">Skills info</div>
						<form class="form-horizontal" id="inputSkillInfo" method='post'
							action="updateSkills">
							<legend>Programming Skills </legend>
							<div class="controls">
									<select data-placeholder="Your Favorite Programming language"
										style="width: 350px;" multiple class="chzn-select"
										name="memProgSkills" id="memProgSkills">
										<option value=""></option>
									
										<c:forEach items="${programmingSkills}" var="progSkill">
											<option value="${progSkill.name}"
											<c:forEach items="${memberProgrammingSkills}" var="memProgSkill">
												${ memProgSkill.name == progSkill.name ? 'selected' : ''} </c:forEach> >${progSkill.name}</option>
										</c:forEach>
									</select>
							</div>

							<legend>Language Skills</legend>
							<div class="controls">
									<select data-placeholder="Your Favorite Programming language"
										style="width: 350px;" multiple class="chzn-select"
										name="memLangSkills" id="memLangSkills">
										<option value=""></option>
								
										<c:forEach items="${languageSkills}" var="langSkill">
											
											<option value="${langSkill.name}"
											<c:forEach items="${memberLanguageSkills}" var="memLangSkill">
												${ memLangSkill.name == langSkill.name ? 'selected' : ''} </c:forEach> >${langSkill.name}</option>
										
										</c:forEach>
									</select>

							</div>

							<legend>Technology Skills </legend>
							<div class="control-group">
							<div class="controls">
									<select data-placeholder="Your Favorite Programming language"
										style="width: 350px;" multiple class="select_fix chzn-select"
										name="memTechSkills" id="memTechSkills">
										<option value=""></option>
										<c:forEach items="${technologySkills}" var="techSkill">
										
											<option value="${techSkill.name}"
											<c:forEach items="${memberTechnologySkills}" var="memTechSkill">
												${ memTechSkill.name == techSkill.name ? 'selected' : ''} </c:forEach> >${techSkill.name}</option>
										
										</c:forEach>
									</select>

								
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

					</div>

					<div class="well pagination-centered" id="additionalInfo"
						style="width: 600px">
						<div class="alert alert-success">Additional info</div>
						<form class="contact-us form-horizontal" id="inputAccauntInfo"
							method='post' action="updateProfile">
							<div class="control-group">
								<label class="control-label">Additional Information</label>
								<div class="controls">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-pencil"></i></span>
										<textarea name="memberAdditionalInfo" class="span4" rows="8"
											placeholder="Type here your additional info..." maxlength="200"><c:out value="${member.additionalInfo}"></c:out>
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
						
					</div>
				</div>
			</td>
		</tr>
	</table>


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
			'.chzn-select' : {},
			'.chzn-select-deselect' : {
				allow_single_deselect : true
			},
			'.chzn-select-no-single' : {
				disable_search_threshold : 10
			},
			'.chzn-select-no-results' : {
				no_results_text : 'Oops, nothing found!'
			},
			'.chzn-select-width' : {
				width : "95%"
			}
		};

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
		$(':file').change(function() {

			var file = this.files[0];
			size = file.size;
			type = file.type;
			error = "";

			if (size > 2097152)
				error = "File to big, choose less then 2 Mb file. ";

			if (type.indexOf("image") == -1)
				error = error + "File is not an image";
			
			if (size == 0)
				error = "Please, choose file first!";

			if (error == "") {

			} else {
				$('#errorUploadMassage').text(error);
				$('#snap3').show({
		    		duration : 1200
		    	});
				$("#file_attachment").each(function() {
					this.reset();
				});
			}
		});	
	</script>
	
	<!-- <script type="text/javascript">
		$(function(){
		    $("[data-hide]").on("click", function(){
		        $("." + $(this).attr("data-hide")).hide();
		    });
		});
    </script> -->

<script type="text/javascript">
function hideAvatarSnap(snap) {
	$(snap).hide();
	
}


</script>


	<script type="text/javascript">
		$(function() {
			var gender = '[id="' + $("#memberSex").val() + '"]';
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
		$(document).ready(function(){
			$("#memberLogin").change(function() { 
				checkMail();
			});	
			$("#password").change(function() { 
				checkPassword();
			});	
			
		});
	
		function hideSnap(snap, field) {
			$(snap).hide();
			$(field).parents('.control-group').removeClass('error');
		}
		
		function checkMail() {
			var member = $("#memberLogin").val();
			
		    $.ajax({  
			    type: "POST",  
			    url: "check",  
			    data: "login=" + member,  
			    success: function(data) { 
			    	if(data === "true") {
			    		$('#snap').show();
						$("#errorMessage").text('Email '+ member + ' is already in use!');
						$("#memberLogin").val("");
						$("#memberLogin").parents('.control-group').removeClass('success');
						$("#memberLogin").parents('.control-group').addClass('error');
			    	}		   
				}
			 }); 
		}
		
		function checkPassword() {
			var password = $("#password").val();
			
		    $.ajax({  
			    type: "POST",  
			    url: "check",  
			    data: "password=" + password,  
			    success: function(data) {  
			    	if(data === "false") {
			    		$('#snap2').show();
						$("#errorMessage2").text('You\'ve entered old password incorrectly!');
						$("#password").val("");
						$("#password").parents('.control-group').removeClass('success');
						$("#password").parents('.control-group').addClass('error');
			    	}		   
				}
			 }); 
		}	
	</script>

	<script type="text/javascript">
	    $(document) .ready(function () {
	        $("#inputAccauntInfo").validate({
	            rules: {
		                memberLogin: {
	                    required: false,
	                    email: true,
	                    maxlength: 40
	                },
	                memberOldPassword: {
	                    required: false,
	                    minlength: 6,
	                    maxlength: 20
	                },
	                memberPassword: {
	                    required: false,
	                    minlength: 6,
	                    maxlength: 20
	                },
	                conf_memberPassword: {
	                    required: false,
	                    equalTo: "#memberPassword"
	                },
	            },
	
	            messages: {
	                memberLogin: {
	                    required: "Enter your email address",
	                    email: "Enter valid email address",
	                    maxlength: "Login lenght must be no longer then 40 characters"
	                },
	                memberOldPassword: {
	                    required: "Enter your password",
	                    minlength: "Password must be minimum 6 characters",
	                    maxlength: "Password lenght must be no longer then 20 characters"
	
	                },
	                memberPassword: {
	                    required: "Enter your password",
	                    minlength: "Password must be minimum 6 characters",
	                    maxlength: "Password lenght must be no longer then 20 characters"
	                },
	                conf_memberPassword: {
	                    required: "Confirm your password",
	                    equalTo: "Password and Confirm Password must match"
	                },
	            },
	            errorClass: "help-inline",
	
	            errorPlacement: function (
	                error, element) {
	                var type = $(element).attr("type");
	                if (type === "radio") { 
	                	error.insertAfter(element).wrap('<li/>');
	                } else if (type === "checkbox") {
	                    error.insertAfter(element).wrap('<li/>');
	                } else {
	                    error.insertAfter(element).wrap('<div/>');
	                }
	            },
	
	            highlight: function (element,
	                errorClass, validClass) {
	                $(element).parents('.control-group').addClass('error');
	            },
	
	            unhighlight: function (element,
	                errorClass, validClass) {
	                $(element).parents('.control-group').removeClass('error');
	                $(element).parents('.control-group').addClass('success');
	            }
	        });
	    });
	</script>

</body>
</html>