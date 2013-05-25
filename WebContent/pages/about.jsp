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
	<link type="text/css" rel="stylesheet" href="css/polaroid.css" />

	<script src="js/jquery.min.js"></script>	
	<script src="js/bootstrap.js"></script>
	<script src='js/zoom/jquery.zoom.js'></script>
	
	<style type="text/css">
		body {
			background: url('./img/backs/fabric.png');
			height: 100%;
			padding-top: 60px;
			padding-bottom: 40px;
		}
		
		.brand {
		  	background: url('./img/robo.png') no-repeat left center;
		 	height: 20px;
		}
		
		.zoom {
			display: inline-block;
			position: relative;
		}
		
		.zoom:after {
			content: '';
			display: block;
			width: 33px;
			height: 33px;
			position: absolute;
			top: 0;
			right: 0;
			background: url(./img/zoom/icon.png);
		}
		
		.zoom img {
			display: block;
		}
		
		.zoom img::selection {
			background-color: transparent;
		}

	</style>
	
	<script>
		$(document).ready(function() {
			$('.zoom').zoom({
				on : 'grab'
			});
		});
	</script>
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
						<li><a href="home"> Home </a></li>
						<li class="active"><a href=""> Members </a></li>
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
						<input type="search" class="search-query span3" name="query" autocomplete="off" placeholder="search..." tabindex="1" maxlength="30">
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
	

	
	<div class="container pagination-centered">
		<div class="photo-album">
			<h1 style="color: #7D5682"><span>inTouch Team</span></h1>
				
			<a href="member?login=iruna.kov@gmail.com" class="large polaroid img1">
				<!-- <img src="./img/team/andrew.png"> -->
				<img src="./img/team/ira.png" alt="">
					<strong>Ірина Ковальовська</strong>
					<br>Львівська державна фінансова академія
			</a>
						
			<a href="member?login=zatorsky.danylo@gmail.com" class="medium polaroid img2">
				<img src="./img/team/danylo.jpg">
					<strong>Данило Заторський</strong>
					<br>Львівська державна фінансова академія		
			</a>			
			
			<a href="member?login=ovchar.andr@gmail.com" class="medium polaroid img0">
				<img src="./img/team/andrew.png">
					<strong>Андрій Овчар</strong>
					<br>НУ "Львівська політехніка"
			</a>								
			
			<a href="member?login=kononchukserg@gmail.com" class="medium polaroid img4">
				<img src="./img/team/serhij.png" alt="">
					<strong>Сергій Конончук</strong>
					<br>Львівська державна фінансова академія
			</a>
			
			<a href="member?login=serge.pasternak@hotmail.co.uk" class="medium polaroid img5">
				<img src="./img/team/serge.png" alt="">
					<strong>Сергій Пастернак</strong>
					<br>НУ "Львівська політехніка"
			</a>				
		</div>
	</div>
	
	<div class="row-fluid">
		<div class="span12 well" style="height: 100px">
			<p class="lead" style="text-align: center">
				The <strong>inTouch</strong> <br /> May The Force Be With Us, © 2013 inTouchTeam
			</p>
		</div>
	</div>
</body>
</html>