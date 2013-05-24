<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML>

<html lang="en-US">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>inTouch</title>
	<link id="favicon" rel="shortcut icon" href="img/green.ico" />
	

	
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css" />
	<link type="text/css" rel="stylesheet" href="css/bootstrap-responsive.css" />
	
	<link type="text/css" rel="stylesheet" href="css/style.css" />
	
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src='js/zoom/jquery.zoom.js'></script>
	<script src='js/zoom/jquery.wheelzoom.js'></script>
	
	
	<style type="text/css">
		body {
			background: url('./img/backs/fabric.png');
			padding-top: 60px;
			padding-bottom: 40px;
		}
		.brand {
		  	background: url('./img/robo.png') no-repeat left center;
		 	height: 20px;
		  	width: 80px;
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
	
		.skill-label {
		    padding: 3px 6px 3px;
		    font-size: 13.75px;
		    font-weight: bold;
		    color: #ffffff;
		    text-transform: lovercase;
		    white-space: nowrap;
		    background-color: #C0C0C0;
		    -webkit-border-radius: 3px;
		    -moz-border-radius: 3px;
		    border-radius: 50px;
		}
		.skill-label.programming {
		    background-color: #c43c35;
		}
		.skill-label.language {
		    background-color: #81EC44;
		}
		.skill-label.technology {
		    background-color: #5650F5;
		}	
	</style>
	
	<script>
		$(document).ready(function() {
			$('#avatar').wheelzoom();
			$('#avatar').zoom({
				on : 'grab'
			});
		});
	</script>


</head>

<body>

	<jsp:useBean id="member"
		class="com.epam.lab.intouch.model.member.Member" scope="session">
	</jsp:useBean>



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
						<li class="active"><a href=" "> UserProfile </a></li>
						<c:if test="${sessionScope.member.isManager()}">
							<li>
								<a href="createProject">Create new Project </a>
							</li>
						</c:if>
					</ul>

					<div id="user_signed" class="pull-right">
						<ul class="nav pull-right">
							<li class="dropdown"><a id="welcome_user" href=""
								class="dropdown-toggle" data-toggle="dropdown">  Welcome, <c:out
										value="${sessionScope.member.firstName }"></c:out> <b
									class="caret"></b>
							</a>
								<ul class="dropdown-menu">
									<li><a href="memberProfile"><i class="icon-user"></i>
											Profile</a></li>
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

	<div class="container">



		<div class="span3 well" style="display: inline-block;">
			<div class="row">

				<div class="span3">
					<a class="thumbnail"> 
						<span class='zoom' id='avatar' style='height:210px; width: 210px;'> 
							<img src="<c:url value="avatar?login=${member.login}"/>"  style='min-height:210px; min-width:210px;' alt='<c:url value="avatar?login=${member.login}"/>' />
							<span style="position: absolute; top: 3px; right: 28px; color: #555; font: bold 13px/1 sans-serif;">
									Click to zoom
							</span>
						</span>
					</a>
				</div>
				<c:choose>
					<c:when test="${member.rating lt '0' }">
					    
						<div class="progress progress-danger progress-striped  span3" style="text-align: center;" >
							<div class="bar" style="width:<c:out value="${member.rating * -10}"></c:out>%; float: right;"><strong><c:out value="${member.rating}"></c:out></strong></div>
							
						</div>
					</c:when>
					<c:otherwise>
						<div class="progress progress-striped  span3" style="text-align: center;" >
							<div class="bar" style="width:<c:out value="${member.rating * 10}"></c:out>%;"><strong><c:out value="${member.rating}"></c:out></strong></div>
						</div>
					</c:otherwise>
				
				</c:choose>
				<div class="sidebar-nav span3">
					<div class="well" style="padding: 1px 0;">
						<ul class="nav nav-list">
							<li class="nav-header">Profile</li>
							<li class="active"><a href="#"><i class="icon-user"></i>
									View </a></li>
							<li class="divider"></li>
							<c:if test="${ requestScope.member.login == null }">
								<li><a href="editProfile"><i class="icon-edit"></i>
										Edit Profile </a></li>
							</c:if>

						</ul>
					</div>
				</div>

				<c:if test="${ requestScope.member.login != null && requestScope.member.login != sessionScope.member.login }">
					<div class="span3 pagination-centered">
						<h4>Express your feelings</h4>
					</div>

					<div id="memberFeelings" class="span3 pagination-centered">
					<form action="memberFeelings" method="post" id="memberFeelingForm" >
					<input type="hidden"  id="memberLiker" name="memberLiker" value="${requestScope.member.login }" />
					<input type="hidden"  id="status" name="status" value="" />
					<input type="hidden" id="statusInDB" name="statusInDB"
										value="<c:out value="${statusInDB}"></c:out>" />
						<div class="btn-group btngroup-download">
							<button type="submit" id="LIKE" value="like" class="btn btn-primary btn-small"> <i class="icon-heart icon-white"></i>&nbsp;Like</button> 
							<button type="submit" id="DONT_CARE" value="dont_care" class="btn btn-small">I don't care</button> 
							<button type="submit" id="DISLIKE" value="dislike" class="btn btn-small btn-danger"><i class="icon-fire icon-white"></i>&nbsp;Dislike</button>
						</div>
						</form>
					</div>

				</c:if>
				<div>
					<div class="span3 pagination-centered">
							<h4>Social Achievement</h4>
					</div>
					<div class="span3 pagination-centered">
						<img src="./img/medals/wooden_medal.png"
							width='96' height='96' alt='V for Vendetta' />
					</div>
						
				
				
				</div>
				<c:choose>
					<c:when test="${requestScope.member.login != null}">

						<div class="span3 pagination-centered">
							<h4>User was on</h4>
						</div>
					</c:when>
					<c:otherwise>
						<div class="span3 pagination-centered">
							<h4>You was on</h4>
						</div>
					</c:otherwise>
				</c:choose>
				<div class="span3 pagination-centered">

					<span class="badge badge-info"><c:out
							value="${fn:length(memberProjectsHistory) }"></c:out> projects</span>
				</div>
			</div>
		</div>



		<div class="span7 well" style="display: inline-block;">

			<form class="form-horizontal" id="profile" method='post' action=''>
				<fieldset>

					<legend>
						<c:out value="${member.firstName }"></c:out>
						<c:out value="${member.lastName}"></c:out>
					</legend>

					<div class="control-group">
						<label class="control-label">Name</label>
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on"><i class="icon-user"></i></span> <span
									class="input-xlarge uneditable-input"><c:out
										value="${member.firstName} ${member.lastName }">
									</c:out> </span>
							</div>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="gender">Role</label>
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on"><i class="icon-globe"></i></span> <span style="text-transform: lowercase"
									class="input-xlarge uneditable-input"><c:out
										value="${member.role }"></c:out> </span>
							</div>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="gender">Qualification
							Level</label>
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on"><i class="icon-globe"></i></span> <span style="text-transform: lowercase;"
									class="input-xlarge uneditable-input"><c:out
										value="${member.qualificationLevel }"></c:out> </span>
							</div>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label">Email</label>
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on"><i class="icon-envelope"></i></span> <span
									class="input-xlarge uneditable-input"><c:out
										value="${member.login }"></c:out></span>
							</div>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label">Birthday</label>
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on"><i class="icon-calendar"></i></span> <span
									class="input-xlarge uneditable-input"><fmt:formatDate
										value="${member.birthday}" pattern="yyyy-MM-dd" /></span>
							</div>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label">Registration date</label>
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on"><i class="icon-calendar"></i></span> <span
									class="input-xlarge uneditable-input"><fmt:formatDate
										value="${member.registrationDate}" pattern="yyyy-MM-dd" /></span>
							</div>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="gender">Gender</label>
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on"><i class="icon-user"></i></span> <span
									style="text-transform: lowercase;" class="input-xlarge uneditable-input"  ><c:out
										value="${member.sex }"></c:out></span>
							</div>
						</div>
					</div>




					<div class="accordion" id="accordion2">


						<div class="accordion-group">
							<div class="accordion-heading">
								<a class="accordion-toggle" data-toggle="collapse"
									data-parent="#accordion2" href="#collapseTwo"> Skills </a>
							</div>
							<div id="collapseTwo" class="accordion-body collapse in">
								<div class="accordion-inner">
								<ul>
									<span class="add-on"><i class="icon-inbox"></i></span>
									<span class="input-xlarge"><b>Programing</b></span><br>
									
									<div class="row-fluid">
										<c:forEach items="${member.skills}" var="skills">
											<c:if test="${skills.skillType eq 'PROGRAMMING'}">
												<span class="skill-label programming"><c:out
														value="${skills.name}"></c:out></span>
											</c:if>
										</c:forEach>
									</div>
									<hr class="bs-docs-separator">
									<span class="add-on"><i class="icon-book"></i></span> <span
										class="input-xlarge"><b>Language</b></span><br>
										
									<div class="row-fluid">
										<c:forEach items="${member.skills}" var="skills">
											<c:if test="${skills.skillType eq 'LANGUAGE'}">
												<span class="skill-label language"><c:out
														value="${skills.name}"></c:out></span>
											</c:if>
										</c:forEach>
									</div>
									<hr class="bs-docs-separator">
									<span class="add-on"><i class="icon-wrench"></i></span> <span
									 class="input-xlarge"><b>Technology</b></span>
										
									<div class="row-fluid">
										<c:forEach items="${member.skills}" var="skills">
											<c:if test="${skills.skillType eq 'TECHNOLOGY'}">
												<span class="skill-label technology"><c:out
														value="${skills.name}"></c:out></span>
											</c:if>
										</c:forEach>
									</div>
								</ul>
								</div>
							</div>
						</div>

						<div class="accordion-group">
							<div class="accordion-heading">
								<a class="accordion-toggle" data-toggle="collapse"
									data-parent="#accordion2" href="#collapseOne"> Additional
									Info </a>
							</div>
							<div id="collapseOne" class="accordion-body collapse ">
								<div class="accordion-inner">
									
											<div class="input-prepend">
												<span class="add-on"><i class="icon-pencil"></i></span>
												<textarea readonly name="info" id="info"
											    class="input-xlarge span6" rows="6"><c:out value="${member.additionalInfo }"></c:out></textarea>
											</div>
						
								</div>
							</div>
						</div>
					</div>

				</fieldset>
			</form>
		</div>

	</div>

	

	<div class="container-fluid" id="projectHistory">
		<div class="well">
			<div class="span10 well pagination-centered" style="height: 50px">
				<h3>Projects history</h3>
			</div>

			<table class="table">
				<thead>
					<tr>
						<th>#</th>
						<th>Project Name</th>
						<th>Created Date</th>
						<th>Description</th>
						<th>Status</th>
						<th style="width: 36px;"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${memberProjectsHistory}" var="historyProjects" varStatus="projectCount">		
						<c:choose>
							<c:when test="${historyProjects.status eq 'OPEN' }">
								<tr class="info">
									<td><c:out value="${projectCount.count }"></c:out></td>
									<td><c:out value="${historyProjects.projectName }"></c:out>
									</td>
									<td><c:out value="${historyProjects.creationDate }"></c:out>
									</td>
									<td><c:out value="${historyProjects.description }"></c:out>
									</td>
									<td style="text-transform : capitalize;"><c:out value="${historyProjects.status }"></c:out></td>
									<td><a
										href="project?id=<c:out value="${historyProjects.id}" />"><i
											class="icon-eye-open"></i></a></td>

								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<td><c:out value="${projectCount.count }"></c:out></td>
									<td><c:out value="${historyProjects.projectName }"></c:out>
									</td>
									<td><c:out value="${historyProjects.creationDate }"></c:out>
									</td>
									<td><c:out value="${historyProjects.description }"></c:out>
									</td>
									<td ><c:out value="${historyProjects.status }"></c:out></td>
									<td><a
										href="project?id=<c:out value="${historyProjects.id}" />"><i
											class="icon-eye-open"></i></a></td>

								</tr>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</tbody>
			</table>
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
		$(".btn-group .btn").click(function() {
		    $("#status").val($(this).val());
		   
		}); 		
	</script>
	
	<script type="text/javascript">
		$(function() {
			var statusButton = '[id="' + $("#statusInDB").val() + '"]';
			//alert(statusInDB);
			$(statusButton).attr("disabled", true);

			$(".btn-group .btn").click(function() {
				$("#statusInDB").val($(this).val());

			});

		});
	</script>
</body>

</html>
