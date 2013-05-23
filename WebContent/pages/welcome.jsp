<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>inTouch</title>
	<link id="favicon" rel="shortcut icon" href="img/red.ico" />
	
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css" />
	<link type="text/css" rel="stylesheet" href="css/bootstrap-responsive.css" />
	
	<!-- <link type="text/css" rel="stylesheet" href="css/style.css" /> -->
	
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('.carousel').carousel({
				interval : 5000
			});
		});
	</script>
	
	<style type="text/css">
	body {
		background: url('./img/backs/fabric.png');
		padding-top: 60px;
		padding-bottom: 40px;
	}
	
	.popover {
	    right: 40px; 
	    width: 80%;
	    text-align: center;
	}

	.brand {
	  	background: url('./img/robo.png') no-repeat left center;
	 	height: 20px;
	  	width: 80px;
	}

	</style>

</head>

<body>
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
						<li class="active"><a href=""> Home </a></li>
					</ul>

					<div id="user_unsigned" class="pull-right" style="display: none;">
						<ul class="nav pull-right">
							<li><a href="registration">Sign Up</a></li>
							<li class="divider-vertical"></li>
							<li class="dropdown"><a class="dropdown-toggle" href="#" data-toggle="dropdown">Sign In <strong
									class="caret"></strong></a>
								<div class="dropdown-menu" style="padding: 15px; padding-bottom: 0px;">
									<form method="post" action="login" accept-charset="UTF-8">
										<input style="margin-bottom: 15px;" type="text" placeholder="logIn" id="memberLogin" name="memberLogin"> 
										<input style="margin-bottom: 15px;" type="password" placeholder="Password" id="memberPassword" name="memberPassword"> 
										<input style="float: left; margin-right: 10px;" type="checkbox" name="remember-me" id="remember-me" value="1">
										<label class="string optional" for="user_remember_me"> Remember me </label> 
										<input class="btn btn-primary btn-block" type="submit" id="signin" value="Sign In">

									</form>
								</div></li>
						</ul>
					</div>

					<div id="user_signed" class="pull-right" style="display: none;">
						<ul class="nav pull-right">
							<li class="dropdown"><a id="welcome_user" href="#" class="dropdown-toggle" data-toggle="dropdown"> <b
									class="caret"></b>
							</a>
								<ul class="dropdown-menu">
									<li><a href="memberProfile"><i class="icon-user"></i> Profile</a></li>
									<li class="divider"></li>
									<li><a href="logout"><i class="icon-off"></i> Logout</a></li>
								</ul></li>
						</ul>
					</div>

				</div>

				<form class="navbar-search form-search pull-right text-center" id="search_form" action="member_search" method="get">
					<div class="input-append">
						<input type="search" class="search-query span3" name="query" autocomplete="off" placeholder="search..." tabindex="1">
						<button type="submit" class="btn" id="search" data-trigger="hover" data-placement="bottom" data-content="Press for advanced search">
							<!-- <span class="caret"></span> -->
							<i class="icon-plus"></i>
							<i class="icon-search icon-large"></i>							
						</button>
					</div>										
				</form>

			</div>
		</div>
	</div>


	<script>
		$(function () { 
			$("#search").popover();  
		});
	</script>

	<!-- 
	<div class="container">
		<div class="logo-main pull-left">
		    <img src="logo.png" alt="" />
		</div>
		<form class="well form-search">
			<input type="text" class="span2" placeholder="logIn..."/>
			<input type="text" class="span2" placeholder="password..."/>
			<button class="btn btn-primary">LogIn</button>
			
			<input type="text" class="span2 search-query pull-right" placeholder="search"/>
			<button class="btn pull-right">Search</button>
		</form>
	</div>
	-->


	<header class="hidden-phone hidden-tablet">
		<div id="hero" class="container">
			<div class="hero-unit"
				style="background: url('./img/header.jpg') no-repeat; color: #FFFFFF; background-size: cover; height: 150px; width: auto;">
				<span style="opacity: 0.85"></span> <font color="white" size="6">Stay in touch...</font>
			</div>
		</div>
	</header>

	<script>
		$('#hero').click(function() {
			$(this).hide(2000, function() {
				//alert('it was Michelangelo by the way, not some dickhead..');
			});
		});
	</script>

	<div class="modal hide fade" id="contact">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">X</button>
			<h3>What's on your mind?</h3>
		</div>


		<!-- <div class="modal-body" style="text-align: center;">
			<div class="row-fluid">
				<div class="span10 offset1">
					<div id="modalTab">
						<div class="tab-content">
							<div class="tab-pane active" id="login">
								<form method="post" action='' name="login_form">
									<p>
										<input type="text" class="span12" name="eid" id="email" placeholder="Email">
									</p>
									<p>
										<input type="password" class="span12" name="passwd" placeholder="Password">
									</p>
									<p>
										<button type="submit" class="btn btn-primary">Sign in</button>
										<a href="#forgotpassword" data-toggle="tab">Forgot Password?</a>
									</p>
								</form>
							</div>
							<div class="tab-pane fade" id="forgotpassword">
								<form method="post" action='' name="forgot_password">
									<p>Hey this stuff happens, send us your email and we'll reset it for you!</p>
									<input type="text" class="span12" name="eid" id="email" placeholder="Email">
									<p>
										<button type="submit" class="btn btn-primary">Submit</button>
										<a href="#login" data-toggle="tab">Wait, I remember it now!</a>
									</p>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div> -->


		<div class="modal-body">
			<div class="alert alert-success">Fill up the registration form below to proceed</div>

			<div class="span6">

				<div class="controls controls-row">
					<input id="name" name="name" type="text" class="span3" placeholder="Name"> <input id="email" name="email"
						type="email" class="span3" placeholder="Email address">
				</div>
				<div class="controls">
					<textarea id="message" name="message" class="span6" placeholder="Your Message" rows="5"></textarea>
				</div>

				<div class="controls">
					<button id="contact-submit" type="submit" class="btn btn-primary input-medium pull-right">Send</button>
				</div>

			</div>
		</div>

		<!-- <div class="modal-footer">
			<button class="btn btn-danger" data-dismiss="modal">
				<i class="icon-remove icon-white"></i>&nbsp;Close
			</button>
		</div> -->
	</div>

	<div class="container">
		<div class="row-fluid">
			<div class="container">
				<div class="span12">
					<div class="navbar">
						<div class="navbar-inner">
							<ul class="nav">
								<li class="active"><a href="#">General</a></li>

								<li class="divider-vertical"></li>

								<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"> Members<b class="caret"></b>
								</a>
									<ul class="dropdown-menu">
										<li><a href="#">New Members</a></li>
										<li><a href="#">All Members</a></li>
										<li><a href="#">Find Member</a></li>
									</ul></li>

								<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"> Projects<b
										class="caret"></b>
								</a>
									<ul class="dropdown-menu">
										<li><a href="#">New Projects</a></li>
										<li><a href="#">All Projects</a></li>
										<li><a href="#">Find Project</a></li>
									</ul></li>

								<li><a href="#">About</a></li>

								<li class="divider-vertical"></li>

								<li><a href="#contact" data-toggle="modal"> Contact Us </a></li>

							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>



		<div class="container">
			<div class="row-fluid" style="text-align: center;">
				<!-- <div class="span2">
					<ul class="nav nav-pills nav-stacked hidden-phone">
						<li class="active"><a href="">Button 1</a></li>
						<li><a href="">Button 2</a></li>
						<li><a href="">Button 3</a></li>
						<li><a href="">Button 4</a></li>
						<li><a href="">Button 5</a></li>
					</ul>
				</div>  -->

				<div class="span12">
					<!-- <div class="page-header"> -->
						<h1 style="color: #808080">Welcome to inTouch!</h1>
				<!-- 	</div> -->

					<div class="container">
						<div class="span6 offset3 well">
							<div id="myC" class="carousel">
								<div class="carousel-inner">
									<div class="item active">
										<img src="./img/dummy.jpg">
									</div>
								</div>
								<a class="carousel-control left" href="#myC" data-slide="prev">&lsaquo;</a> <a class="carousel-control right"
									href="#myC" data-slide="next">&rsaquo;</a>
							</div>
						</div>
					</div>

					<div class="content-main">
						<p style="color: #4E4A4D; text-align:center; font-family:segoe ui; font-size:18px">
							InTouch is social networking service, that helps you to maintain communication with your teammates. 
							<br>Users must register before using the site, after which they may create a personal profile and update it with additional 
							information, add their photos, lists of personal skills, contact information, etc. The like buttons allows to rate other 
							members of InTouch.	Besides, if user is a manager, he can create projects, update it and add users to the projects.
							Advanced search system allows to find other members or projects with specified 
							<br>qualifications or parameters accordingly.						
							<br><br>Develop and be informed of your co-workers progress. Keep track of interesting projects and take part in their implementation. 
							Constantly upgrade your profile with your latest skills or achievements and 
							<br>let the others know how great you are ;)				
						</p>						
					</div>

				</div>
			</div>
		</div>

	</div>


	<div class="row-fluid">
		<div class="span12 well" style="height: 100px">
			<p class="lead" style="text-align: center">
				The <strong>inTouch</strong> <br /> May The Force Be With Us, © 2013 inTouchTeam
			</p>
		</div>
	</div>


	<!-- <script type="text/javascript">
		$(document).ready(function() {
			alert('loaded');
	 		$.ajax({
			    'url' : 'login',
		    	'type' : 'POST',
		    	'data' : {
		     		'login' : 'revan-ps@hotmial.com'
		      		'password' : 'qwerty'
		    	},
		    	/* 'success' : function(data) {
		     		if (data == "success") {
		       			alert('request sent!');
		      		}
		    	} */
		  	});
		});
	</script> -->

	<script type="text/javascript">
		$(document).ready(function() {
			$.getJSON('./check', function(data) {

				/*  $.each(data, function(key, val){
				  alert(key);
				  alert(val);
				 }); */

				$('#user_unsigned').hide();
				$('#user_signed').show();
				$("#welcome_user").text('Welcome, ' + data.login);
				$("#welcome_user").append('&nbsp<b class="caret"></b>');
				$("#favicon").attr("href", "img/green.ico");
			}).error(function() {
				$('#user_unsigned').show();
			});
			/* }).complete(function() {
				
			}); */
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
</body>

</html>
