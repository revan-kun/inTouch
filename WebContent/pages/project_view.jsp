<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link id="favicon" rel="shortcut icon" href="img/green.ico" />
	
	<title>inTouch</title>
	
	<link rel="stylesheet" href="css/bootstrapSwitch.css" />
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css" />
	<link type="text/css" rel="stylesheet" href="css/bootstrap-responsive.css" />
		
	<script src="js/jquery.min.js"></script>
		
	<script src="js/jquery-1.9.1.js"></script>
	<script src="js/jquery-ui.js"></script>
	
	<script src="js/bootstrap.js"></script>
		
	<script src='js/zoom/jquery.zoom.js'></script>
	<script src='js/zoom/jquery.wheelzoom.js'></script>
	<script src="js/bootstrapSwitch.js"></script>
	<script src="js/mambo/jquery.mambo.js"></script>
	<script src="js/mambo/jquery.mambo.min.js"></script>
	
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
		  	//width: 80px;
		}
		
		.zoom {
			display: inline-block;
			position: relative;
		}
		
		.thumbnail > img {
		    display: block;
		    height: 50px; /* add this */
		    margin-left: auto;
		    margin-right: auto;
		    max-width: 20%;
		}
		
		.zoom:after {
			content: '';
			display: block;
			width: 33px;
			height: 33px;
			position: absolute;
			top: 0px;
			right: 0px;
			background: url(./img/zoom/eye.png);
		}
	</style>
	
	<!-- 	<script>
			$(document).ready(function(){
				$('#avatar').wheelzoom();
				$('#avatar').zoom({ on:'grab' });
			});
		</script> -->
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
						<li>
							<a href="home"> Home </a>
						</li>
						<li class="active">
							<a href=""> Project </a>
						</li>
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
									Welcome, <c:out value="${sessionScope.member.firstName }" />
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

		<c:choose>
			<c:when test="${requestScope.project.status eq 'OPEN'}">
				<div class="span2 well" style="display: inline-block;margin-top: 115px"> 
					<h3 style="text-align: center; color: #4E4A4D">Days left</h3>
					<canvas class="label-value" width="140" height="140" style="width: 140px; height: 140px;"></canvas>
				</div>
				<div class="span8" style="display: inline-block;">
			</c:when>
			<c:otherwise>
				<div class="span8 offset2">
			</c:otherwise>
		</c:choose>
		

			<form class="form-horizontal" id="profile" method='post' action=''>
				<fieldset>

					<legend>			
						<c:set var="status" value="${requestScope.project.status}" scope="page"></c:set>
						<c:choose>
							<c:when test="${status eq 'OPEN'}">
								<div id="status" class="switch" data-on="info" data-off="warning" data-on-label="Open" data-off-label="Closed">
									<input type="checkbox" checked />
								</div>							
							</c:when>
							<c:when test="${status eq 'CLOSED'}">
								<div id="status" class="switch" data-on="info" data-off="warning" data-on-label="Open" data-off-label="Closed">
									<input type="checkbox"/>
								</div>
							</c:when>
						</c:choose>
						<br/>				
						<i class="icon-edit"></i> Project: <c:out value="${requestScope.project.projectName}" />
					</legend>


					<div class="accordion" id="accordion2">

						<div class="accordion-group">
							<div class="accordion-heading">
								<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
									General Info
								</a>
							</div>
							<div id="collapseOne" class="accordion-body collapse in">
								<div class="accordion-inner">

									<div class="control-group">
										<label class="control-label">Manager</label>
										<div class="controls">
											<div class="input-prepend">
												<span class="add-on"><i class="icon-user"></i></span>
												<c:choose>
													<c:when test="${status eq 'CLOSED'}">
														<c:forEach var="member" items="${history}">
															<c:if test="${member.role eq 'MANAGER'}">
																<c:set var="managerFirstName" value="${member.firstName}"/>
																<c:set var="managerLastName" value="${member.lastName}"/>
															</c:if>
														</c:forEach>
													</c:when>
													<c:otherwise>
														<c:forEach var="member" items="${project.members}">
															<c:if test="${member.role eq 'MANAGER'}">
																<c:set var="managerFirstName" value="${member.firstName}"/>
																<c:set var="managerLastName" value="${member.lastName}"/>
															</c:if>
														</c:forEach>
													</c:otherwise>
												</c:choose>
												<span class="input-xlarge uneditable-input"><c:out value="${managerFirstName}"/> <c:out value="${managerLastName}"/></span>
											</div>
										</div>
									</div>

									<div class="control-group">
										<label class="control-label">Customer</label>
										<div class="controls">
											<div class="input-prepend">
												<span class="add-on"><i class="icon-globe"></i></span> 
												<span class="input-xlarge uneditable-input"><c:out value="${project.customer}" /></span>
											</div>
										</div>
									</div>

									<div class="control-group">
										<label class="control-label">Creation Date</label>
										<div class="controls">
											<div class="input-prepend">
												<span class="add-on"><i class="icon-calendar"></i></span>
												<span class="input-xlarge uneditable-input"><fmt:formatDate value="${project.creationDate}" pattern="yyyy-MM-dd"/></span>
											</div>
										</div>
									</div>

									<div class="control-group">
										<label class="control-label">Estimated Completion</label>
										<div class="controls">
											<div class="input-prepend">
												<span class="add-on"><i class="icon-calendar"></i></span> 
												<span class="input-xlarge uneditable-input"><fmt:formatDate value="${project.estimatedCompletionDate}" pattern="yyyy-MM-dd"/></span>
											</div>
										</div>
									</div>

									<c:if test="${status eq 'CLOSED'}">
										<div class="control-group">
											<label class="control-label">Completion Date</label>
											<div class="controls">
												<div class="input-prepend">
													<span class="add-on"><i class="icon-calendar"></i></span>
													<span class="input-xlarge uneditable-input"><fmt:formatDate value="${project.completionDate}" pattern="yyyy-MM-dd"/></span>
												</div>
											</div>
										</div>
									</c:if>
								</div>
							</div>
						</div>

						<div class="accordion-group">
							<div class="accordion-heading">
								<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapse2"> Description
								</a>
							</div>
							<div id="collapse2" class="accordion-body collapse">
								<div class="accordion-inner">
									<div class="span6">
										<textarea readonly name="description" id="description" class="input-xlarge span6" rows="4"><c:out value="${project.description}" /></textarea>
									</div>
								</div>
							</div>
						</div>
					</div>

				</fieldset>
			</form>
		</div>

		<c:if test="${status eq 'OPEN'}">
			<div class="row-fluid">
				<div class="span12 well pagination-centered" style="height: 90px">
					<h3 class="text-warning">Active Members</h3>
				</div>
			</div>
	
				
			<div id="members" class="container-fluid span12">
				
				<c:forEach var="member" items="${project.members}" varStatus="loop">
					<div class="span2 well" id="<c:out value="${loop.index}"/>">
						<div class="row">
		
							<div class="span2 pagination-centered">
								<p>
									<c:choose>
										<c:when test="${member.role eq 'MANAGER'}">
											<span class="badge badge-important">Manager<br /></span>
										</c:when>
										<c:when test="${member.role eq 'DEVELOPER'}">
											<span class="badge badge-success">Developer<br /></span>
										</c:when>
										<c:otherwise>
											<span class="badge badge-info">QA<br /></span>
										</c:otherwise>
									</c:choose>								
								</p>
							</div>
		
							<div class="span2">
								<a href="<c:url value="member?login=${member.login}"/>" class="thumbnail"> 
									<span class='zoom' id='avatar'> 
										<img src='<c:url value="${member.photoLink}"/>' width='250' height='250'/>
										<span style="position: absolute; top: 9px; right: 23px; color: #555; font: bold 13px/1 sans-serif;">Click to view</span>
									</span>
								</a>
							</div>
		
							<div class="span2 pagination-centered">
								<p>
									<strong><c:out value="${member.firstName} ${member.lastName}" /></strong>
								</p>
							</div>
		
						</div>
					</div>
				</c:forEach>
			</div>
		</c:if>	
	
		<script>
			$('#status').bootstrapSwitch('setActive', false);
			//$('#status').bootstrapSwitch('setState', false);
		</script>

		<div class="row-fluid">
			<div class="span12 well pagination-centered" style="height: 90px">
				<h3 class="text-info">History</h3>
			</div>
			<div class="row pagination-centered">
				<div class="span8 offset2">
					<table class="table table-striped table-condensed">
						<thead>
							<tr>
								<th>Member Name</th>
								<th>Role</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="member" items="${history}">
							<tr>
								<td>
									<c:out value="${member.firstName} ${member.lastName}" />
								</td>
								<td>
									<c:choose>
										<c:when test="${member.role eq 'MANAGER'}">
											<span class="badge badge-important">Manager<br /></span>
										</c:when>
										<c:when test="${member.role eq 'DEVELOPER'}">
											<span class="badge badge-success">Developer<br /></span>
										</c:when>
										<c:otherwise>
											<span class="badge badge-info">QA<br /></span>
										</c:otherwise>
									</c:choose>			
								</td>
								<td><span class="label label-important">Removed</span></td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
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
		
	<input type="hidden" id="first" value="<fmt:formatDate value="${project.creationDate}" pattern="MM/dd/yyyy"/>"/>
	<input type="hidden" id="second" value="<fmt:formatDate value="${project.estimatedCompletionDate}" pattern="MM/dd/yyyy"/>"/>

	<script>
		$(".label-value").mambo({
			percentage: get(),
			label: get(),
			displayValue: false,
			circleColor: '#9136C7',
			ringColor: "#632587"
		});
		
		function parseDate(str) {
		    var mdy = str.split('/');
		    return new Date(mdy[2], mdy[0]-1, mdy[1]);
		}

		function daydiff(second) {
			var today = new Date();
			var dd = today.getDate();
			var mm = today.getMonth()+1;

			var yyyy = today.getFullYear();
			if(dd<10) {
				dd='0'+dd;
			} if(mm<10) {
				mm='0'+mm;
			} 
			
			today = mm+'/'+dd+'/'+yyyy;
			
		    return (second-parseDate(today))/(1000*60*60*24);
		}

		function get() {
			return daydiff(parseDate($('#second').val()));
		}

	</script>
</body>

</html>
