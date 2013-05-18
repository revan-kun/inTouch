<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>inTouch</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link type="text/css" rel="stylesheet" href="css/bootstrap.css" />
<link type="text/css" rel="stylesheet"
	href="css/bootstrap-responsive.css" />

<link type="text/css" rel="stylesheet" href="css/style.css" />

<script src="js/jquery.min.js"></script>

<script src='js/zoom/jquery.zoom.js'></script>
<script src='js/zoom/jquery.wheelzoom.js'></script>

<script src="js/bootstrap.js"></script>

<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
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

	<div class="container">


		
			<div class="span3 well" style="display: inline-block;">
				<div class="row">

					<div class="span3">
						<a class="thumbnail"> <span class='zoom' id='avatar'> <img
								src="./img/user_avatar/<jsp:getProperty
											property="photoLink" name="member" />" width='250' height='250'
								alt='V for Vendetta' />
								<span
									style="position: absolute; top: 3px; right: 28px; color: #555; font: bold 13px/1 sans-serif;">
									Click to zoom</span>
						</span>
						</a>
					</div>

					<div class="progress progress-striped active span3">
						<div class="bar" style="width: 90%;">Rating</div>
					</div>

					<div class="sidebar-nav span3">
						<div class="well" style="padding: 1px 0;">
							<ul class="nav nav-list">
								<li class="nav-header">Profile</li>
								<li class="active"><a href="#"><i class="icon-user"></i>
										View </a></li>
								<!--  <li><a href="#"><i class="icon-envelope"></i> Messages
										<span class="badge badge-info">4</span></a></li>
								<li><a href="#"><i class="icon-comment"></i> Comments <span
										class="badge badge-info">10</span></a></li>-->
								<li class="divider"></li>
								<li><a href="profileCall"><i class="icon-edit"></i>
										Edit Profile </a></li>
								
							</ul>
						</div>
					</div>

					<div class="span3 pagination-centered">
						<h4>Express your feelings</h4>
					</div>

					<div class="span3 pagination-centered">
						<div class="btn-group btngroup-download">
							<a href="" class="btn btn-primary btn-small">
							<i class="icon-heart icon-white"></i>&nbsp;Like</a> 
							<a href="" class="btn btn-small">I don't care</a> 
							<a href=""
								class="btn btn-small btn-danger"><i
								class="icon-fire icon-white"></i>&nbsp;Dislike</a>
						</div>
					</div>

					<div class="span3 pagination-centered">
						<span class="badge badge-warning">8 projects</span> <span
							class="badge badge-info">7 smth else</span>
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
										class="input-xlarge uneditable-input"><c:out value="${member.firstName} ${member.lastName }"> </c:out>
							 </span>
								</div>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="gender">Role</label>
							<div class="controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-globe"></i></span> <span
										class="input-xlarge uneditable-input"><c:out value="${member.role }"></c:out> </span>
								</div>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="gender">Qualification
								Level</label>
							<div class="controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-globe"></i></span> <span
										class="input-xlarge uneditable-input"><c:out value="${member.qualificationLevel }"></c:out> </span>
								</div>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Email</label>
							<div class="controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-envelope"></i></span> <span
										class="input-xlarge uneditable-input"><c:out value="${member.login }"></c:out></span>
								</div>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Birthday</label>
							<div class="controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-calendar"></i></span> <span
										class="input-xlarge uneditable-input"><fmt:formatDate value="${member.birthday}" pattern="yyyy-MM-dd"/></span>
								</div>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Registration date</label>
							<div class="controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-calendar"></i></span> <span
										class="input-xlarge uneditable-input"><fmt:formatDate value="${member.registrationDate}" pattern="yyyy-MM-dd"/></span>
								</div>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="gender">Gender</label>
							<div class="controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-user"></i></span> <span
										class="input-xlarge uneditable-input"><c:out value="${member.sex }"></c:out></span>
								</div>
							</div>
						</div>




						<div class="accordion" id="accordion2">


							<div class="accordion-group">
								<div class="accordion-heading">
									<a class="accordion-toggle" data-toggle="collapse"
										data-parent="#accordion2" href="#collapseTwo"> Skills </a>
								</div>
								<div id="collapseTwo" class="accordion-body collapse">
									<div class="accordion-inner">
										<table  class="table">
											<thead>
												<tr>
													<th class="span2">Type</th>
													<th>Name</th>
													<th>Experience</th>
													<th>Level</th>
													<th>Description</th>


												</tr>
											</thead>
											<tbody>
												<tr class="info">
													<td colspan="5">Programing</td>
												</tr>
												<c:forEach items="${member.skills}" var="skills">
													<c:if test="${skills.skillType eq 'PROGRAMMING'}">
														<tr>
															<th style="width: 36px;"></th>
															<td><c:out value="${skills.name}"></c:out></td>
															<td><c:out value="${skills.experience}"></c:out></td>
															<td><c:out value="${skills.level}"></c:out></td>
															<td><c:out value="${skills.description}"></c:out></td>
														</tr>
													</c:if>
												</c:forEach>
											</tbody>
											<tbody>
												<tr  class="info">
													<td colspan="5">Language</td>
												</tr>
												<c:forEach items="${member.skills}" var="skills">
													<c:if test="${skills.skillType eq 'LANGUAGE'}">
														<tr>
															<th style="width: 36px;"></th>
															<td><c:out value="${skills.name}"></c:out></td>
															<td><c:out value="${skills.experience}"></c:out></td>
															<td><c:out value="${skills.level}"></c:out></td>
															<td><c:out value="${skills.description}"></c:out></td>
														</tr>
													</c:if>
												</c:forEach>
											</tbody>
											<tbody>
												<tr class="info">
													<td colspan="5">Technology</td>
												</tr>
												<c:forEach items="${member.skills}" var="skills">
													<c:if test="${skills.skillType eq 'TECHNOLOGY'}">
														<tr>
															<th style="width: 36px;"></th>
															<td><c:out value="${skills.name}"></c:out></td>
															<td><c:out value="${skills.experience}"></c:out></td>
															<td><c:out value="${skills.level}"></c:out></td>
															<td><c:out value="${skills.description}"></c:out></td>
														</tr>
													</c:if>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>

							<div class="accordion-group">
								<div class="accordion-heading">
									<a class="accordion-toggle" data-toggle="collapse"
										data-parent="#accordion2" href="#collapseOne"> Additional
										Info </a>
								</div>
								<div id="collapseOne" class="accordion-body collapse in">
									<div class="accordion-inner">
										<div class="span6">
											<!-- <label>Additional Info</label>  -->
											<textarea readonly name="info" id="info"
												class="input-xlarge span6" rows="6"><c:out value="${member.additionalInfo }"></c:out>   </textarea>
										</div>
									</div>
								</div>
							</div>
						</div>

					</fieldset>
				</form>
			</div>

		</div>

		<!--
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span2 well">area 3</div>
				<div class="span2 well">area 4</div>
				<div class="span2 well">area 5</div>
			</div>
		</div>

		<!--------------------------------------------------------------------->

	<!--	
	<div class="container-fluid">
		<div class="row">
			<div class="span11 well pagination-centered" style="height:30px">
				<h4>Member projects history</h4>
			</div>
		</div>
	</div>
	-->

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
						<tr>
							<td>1</td>
							<td>Enigma</td>
							<td>I don't remember, actually...</td>
							<td>Genuine WWII Enigma Machine</td>
							<td>Completed</td>
							<td><a href="user.html"><i class="icon-pencil"></i></a> <a
								href="#myModal"  data-toggle="modal"><i
									class="icon-remove"></i></a></td>
						</tr>
						<tr>
							<td>2</td>
							<td>Colossus</td>
							<td>1 June 1944</td>
							<td>were used by British codebreakers</td>
							<td>Completed</td>
							<td><a href="user.html"><i class="icon-pencil"></i></a> <a
								href="#myModal"  data-toggle="modal"><i
									class="icon-remove"></i></a></td>
						</tr>

					</tbody>
				</table>
			</div>

			<!-- <div>
				<ul class="pager">
					<li class="previous"><a href="#">&larr; Older</a></li>
					<li class="next"><a href="#">Newer &rarr;</a></li>
				</ul>
			</div> -->

		</div>

<!--  
		<div class="modal small hide fade" id="myModal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">Ã—</button>
				<h3 id="myModalLabel">Delete Confirmation</h3>
			</div>
			<div class="modal-body">
				<p class="error-text">Are you sure you want to delete this
					project from your history?</p>
			</div>
			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
				<button class="btn btn-danger" data-dismiss="modal">Delete</button>
			</div>
		</div>
-->
		<div class="alert alert-block">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<h4>Warning!</h4>
			This is dummy member, don't get too fancy..
		</div>


		<div class="row-fluid">
			<div class="span12 well" style="height: 100px">
				<p class="lead" style="text-align: center">
					The <strong>inTouch</strong> <br /> May The Force Be With Us,
					&nbsp;© 2013 inTouchTeam
				</p>
			</div>
		</div>

	</div>
</body>

</html>
