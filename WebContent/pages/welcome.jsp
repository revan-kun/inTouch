<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>inTouch</title>
	<c:choose>
		<c:when test="${not empty sessionScope.member}">
			<link id="favicon" rel="shortcut icon" href="img/green.ico" />
		</c:when>
		<c:otherwise>
			<link id="favicon" rel="shortcut icon" href="img/red.ico" />
		</c:otherwise>
	</c:choose>
	
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css" />
	<link type="text/css" rel="stylesheet" href="css/bootstrap-responsive.css" />
	
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('.carousel').carousel({
				interval : 4000
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
						<c:if test="${sessionScope.member.isManager()}">
							<li>
								<a href="createProject">Create new Project </a>
							</li>
						</c:if>
					</ul>
					
					<c:choose>
						<c:when test="${not empty sessionScope.member}">
							<div id="user_signed" class="pull-right">
								<ul class="nav pull-right">
									<li class="dropdown">
										<a id="welcome_user" href="" class="dropdown-toggle" data-toggle="dropdown"> 
											Welcome, <c:out value="${sessionScope.member.firstName }"></c:out>
											<b class="caret"></b>
										</a>
										<ul class="dropdown-menu">
											<li><a href="memberProfile"><i class="icon-user"></i> Profile</a></li>
											<li class="divider"></li>
											<li><a href="logout"><i class="icon-off"></i> Logout</a></li>
										</ul>
									</li>
								</ul>
							</div>
						</c:when>
						<c:otherwise>
							<div id="user_unsigned" class="pull-right">
								<ul class="nav pull-right">
									<li><a href="registration">Sign Up</a></li>
									<li class="divider-vertical"></li>
									<li class="dropdown"><a class="dropdown-toggle" href="#" data-toggle="dropdown">Sign In <strong
											class="caret"></strong></a>
										<div class="dropdown-menu" style="padding: 15px; padding-bottom: 0px;">
											<form method="post" action="login" accept-charset="UTF-8">
												<input style="margin-bottom: 15px;" type="text" placeholder="logIn" id="memberLogin" name="memberLogin"> 
												<input style="margin-bottom: 15px;" type="password" placeholder="Password" id="memberPassword" name="memberPassword">
												<a href="#forgotPasword" style="float: left; margin-right: 10px; margin-bottom: 10px;"  data-toggle="modal">Forgot password</a> 
												
												 
												<input class="btn btn-primary btn-block" type="submit" id="signin" value="Sign In">
		
											</form>
										</div>
									</li>
								</ul>
							</div>						
						</c:otherwise>
					</c:choose>

				</div>

				<form class="navbar-search form-search pull-right text-center" id="search_form" action="member_search" method="get">
					<div class="input-append">
						<input type="search" class="search-query span3" name="query" autocomplete="off" placeholder="search..." tabindex="1">
						<button  class="btn" id="search" data-trigger="hover" data-placement="bottom" data-content="Press for advanced search">
							<!-- <span class="caret"></span> -->
							<i class="icon-plus"></i>
							<i class="icon-search icon-large"></i>							
						</button>
					</div>										
				</form>

			</div>
		</div>
	</div>

	<header class="hidden-phone hidden-tablet">
		<div id="hero" class="container">
			<div class="hero-unit" title="Click to hide image"
				style="background: url('./img/header.jpg') no-repeat; color: #FFFFFF; background-size: cover; height: 150px; width: auto;">
				<span style="opacity: 0.85"></span> <font color="white" size="6">Stay in touch...</font>
			</div>
		</div>
	</header>
	
	<div class="modal hide fade" id="contact">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">X</button>
			<h3>What's on your mind?</h3>
		</div>
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
	</div>
	
	<div class="modal hide fade" id="forgotPasword">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">X</button>
			<h3>What's on your mind?</h3>
		</div>
		<div class="modal-body">
			<div class="alert alert-success">Fill up the registration form below to proceed</div>
			<div class="span3">
			
				<div class="controls controls-row">
				 <input id="userMail" name="userMail"
						type="email" class="span3" placeholder="Email address">
				</div>
				<div class="controls">
					<button id="contact-submit" onclick="sendpassword($('#userMail').val());" type="submit" class="btn btn-primary input-medium pull-right">Send</button>
				</div>
				
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row-fluid">
			<div class="container">
				<div class="span12">
					<div class="navbar">
						<div class="navbar-inner">
							<ul class="nav">
								<li class="active"><a href="">General</a></li>

								<li class="divider-vertical"></li>
								
								<c:if test="${not empty sessionScope.member}">
									<li class="dropdown">
										<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 
											Members<b class="caret"></b>
										</a>
										<ul class="dropdown-menu">
											<li><a href="allMembers?number=5">New Members</a></li>
											<li><a href="allMembers">All Members</a></li>
											<li><a href="member_search">Find Member</a></li>
										</ul>
									</li>

									<li class="dropdown">
										<a class="dropdown-toggle" data-toggle="dropdown" href="#">
											Projects<b class="caret"></b>
										</a>
										<ul class="dropdown-menu">
											<li><a href="allProjects?number=5">New Projects</a></li>
											<li><a href="allProjects">All Projects</a></li>
											<li><a href="project_search">Find Project</a></li>
										</ul>
									</li>		
								</c:if>	

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
				<div class="span12">
					<h1 style="color: #808080">Welcome to inTouch!</h1>

					<div class="container">
						<div class="span6 offset3 well">
							<div id="myC" class="carousel">
								<div class="carousel-inner">
									<div class="item active">
										<img src="./img/dummy.jpg">
									</div>
									<div class="item">
										<img src="./img/carousel/picture2.png">
									</div>
									<div class="item">
										<img src="./img/carousel/picture3.png">
									</div>
									<div class="item">
										<img src="./img/carousel/picture4.png">
									</div>
									<div class="item">
										<img src="./img/carousel/picture6.png">
									</div>
									<div class="item">
										<img src="./img/carousel/picture8.png">
									</div>

								</div>
								<a class="carousel-control left" href="#myC" data-slide="prev">&lsaquo;</a> <a class="carousel-control right"
									href="#myC" data-slide="next">&rsaquo;</a>
							</div>
						</div>
					</div>

					<div class="content-main">
						<p style="color: #4E4A4D; font-family:segoe ui; font-size:18px">
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

	<script>
		$('#hero').click(function() {
			$(this).hide(2000, function() {
				//alert('it was Michelangelo by the way, not some dickhead..');
			});
		});
	</script>
	
	<script>
		$(function () { 
			$("#search").popover();  
		});
	</script>
	<script type="text/javascript">		
		function sendpassword(userEmail) {
			
			$.ajax({
		    	type : 'POST',
			    url : 'forgotPassword',
		    	data : "userMail="+userEmail,
		    	success : function(data) {
		    		alert('success');
		    	},
				error: function() {
					alert('failure');
					
			  	}
		  	});
		}
	</script>	
	
</body>

</html>
