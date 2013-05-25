<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<html>

<head>
<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>inTouch</title>
	<link id="favicon" rel="shortcut icon" href="img/green.ico" />	

	<link type="text/css" rel="stylesheet" href="css/bootstrap.css" />
	<link type="text/css" rel="stylesheet" href="css/bootstrap-responsive.css" />

	<script src="js/jquery.min.js"></script>	
	<script src="js/bootstrap.js"></script>
	
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
		
		table.center {
		    margin-left:auto; 
		    margin-right:auto;
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
						<li><a href="home"> Home </a></li>
						<li class="active"><a href=""> Projects </a></li>
						<c:if test="${sessionScope.member.isManager()}">
							<li>
								<a href="createProject">Create new Project </a>
							</li>
						</c:if>
					</ul>

					<div id="user_signed" class="pull-right">
						<ul class="nav pull-right">
							<li class="dropdown">
								<a id="welcome_user" href="#" class="dropdown-toggle" data-toggle="dropdown"> 
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
	
	<div class="container">
		<div class="offset2 span7 well pagination-centered" style="height: 60px">
			<c:choose>
				<c:when test="${requestScope.all}">
					<h3 style="color: #808080">All Projects</h3>
				</c:when>
				<c:otherwise>
					<h3 style="color: #808080">Latest Created Projects</h3>
				</c:otherwise>
			</c:choose>
		</div>
	
		<div class="offset1 span9 well">
			<c:forEach var="project" items="${projects}" varStatus="projectOrdinal">
				<div class="span3 well" style="margin: 40px">
					<div class="row">
						<div class="sidebar-nav span3">
							<div class="well" style="padding: 1px 0;">
								<ul class="nav nav-list">								
									<li class="nav-header" style="padding-top: 13px">
										<i class="icon-edit"></i>
										<c:out value="${project.projectName}"></c:out>
										<div style="float: right;">
											<c:choose>
												<c:when test="${project.status eq 'CLOSED'}">
													<span class="badge badge-warning">CLOSED<br /></span>
												</c:when>
												<c:otherwise>
													<span class="badge badge-success">OPEN<br /></span>
												</c:otherwise>
											</c:choose>	
										</div>
									</li>
									
									<li class="divider"></li>
									
									<c:if test="${project.status ne 'CLOSED'}">
										<li>
											<c:forEach var="member" items="${project.members}">
												<c:if test="${member.isManager()}">
													<c:set var="manager" value="${member}" />
												</c:if>
											</c:forEach>
											<a href='member?login=<c:out value="${manager.login}"/>'>
												<i class="icon-user"></i>
												Manager: <c:out value="${manager.firstName }"></c:out>
												<c:out value="${manager.lastName}"></c:out>
											</a>
										</li>
									</c:if>	
									<li>	
										<a href="">			
											<i class="icon-globe"></i>
											Customer: <c:out value="${project.customer}"></c:out>
										</a>						
									</li>
									<li class="active">
										<a href="project?id=<c:out value="${project.id}" />">
											<i class="icon-eye-open"></i>
											Click to View
										</a>
									</li>
								</ul>
							</div>					
						</div>			
					</div>
				</div>
			</c:forEach>
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