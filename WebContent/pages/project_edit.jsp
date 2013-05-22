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
	
	<style type="text/css">
	body {
		background: url('./img/backs/fabric.png');
		height: 100%;
		padding-top: 60px;
		padding-bottom: 40px;
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
	
	.btn-custom {
	  background-color: hsl(280, 76%, 25%) !important;
	  background-repeat: repeat-x;
	  filter: progid:DXImageTransform.Microsoft.gradient(startColorstr="#a123e1", endColorstr="#4f0f70");
	  background-image: -khtml-gradient(linear, left top, left bottom, from(#a123e1), to(#4f0f70));
	  background-image: -moz-linear-gradient(top, #a123e1, #4f0f70);
	  background-image: -ms-linear-gradient(top, #a123e1, #4f0f70);
	  background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #a123e1), color-stop(100%, #4f0f70));
	  background-image: -webkit-linear-gradient(top, #a123e1, #4f0f70);
	  background-image: -o-linear-gradient(top, #a123e1, #4f0f70);
	  background-image: linear-gradient(#a123e1, #4f0f70);
	  border-color: #4f0f70 #4f0f70 hsl(280, 76%, 18.5%);
	  color: #fff !important;
	  text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.42);
	  -webkit-font-smoothing: antialiased;
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

				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="home">inTouch</a>

				<div class="nav-collapse collapse">
					<ul class="nav nav-pills">
						<li>
							<a href="home"> Home </a>
						</li>
						<li class="active">
							<a href=""> Project </a>
						</li>
						<li>
							<a href="add_project">Create new Project </a>
						</li>
					</ul>

					<div class="pull-right">
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
	
	
		
	<div class="alert alert-block alert-error fade in" style="display: none">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<h4 class="alert-heading">Oh snap! You got an error!</h4>
		<p>Change this and that and try again. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Cras mattis consectetur purus sit amet fermentum.</p>
		<p>
			<a class="btn btn-danger" href="#">Take this action</a> 
			<a class="btn" href="#">Or do this</a>
		</p>
	</div>
	
	<div class="modal hide fade" id="contact">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">X</button>
			<h3>Update project <c:out value="${project.projectName}"/></h3>
		</div>

		<div class="modal-body">
			<div class="alert alert-success" style="color: #4E4A4D">Edit the form below to update</div>
			<div class="span6">
				<form class="form-horizontal" id="updateProject" method='post' action='update_project'>
					<fieldset>
					
						<input type="hidden" id="projectId" name="projectId" value="<c:out value="${project.id}"/>" />
						
						<div class="control-group">
							<label class="control-label">Customer</label>
							<div class="controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-globe"></i></span> 
									<input type="text" class="input-xlarge" id="projectCustomer" name="projectCustomer" value="<c:out value="${project.customer}" />">
								</div>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">Estimated Completion</label>					
							<div class="controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-calendar"></i></span> 
									<input type="text" class="input-xlarge" id="projectEstimatedCompletion" name="projectEstimatedCompletion" value="<fmt:formatDate value="${project.estimatedCompletionDate}" pattern="yyyy-MM-dd"/>" />
								</div>
							</div>
						</div>
	
						<div class="control-group">
							<label class="control-label">Description</label>				
								<div class="controls">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-pencil"></i></span>	
										
											<textarea name="projectDescription" id="projectDescription" class="input-xlarge span3" rows="4"><c:out value="${project.description}" /></textarea>
															
								</div>
							</div>
						</div>				
	
						<div class="control-group">
							<div class="controls">
								<button type="submit" class="btn btn-success" title="first tooltip">
									<i class="icon-edit icon-white"></i>&nbsp;Update
								</button>
								<!-- <button type="reset" class="btn">
									<i class="icon-repeat icon-black"></i>&nbsp;Clear
								</button> -->
							</div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>

		<!-- <div class="modal-footer">
			<button class="btn btn-danger" data-dismiss="modal">
				<i class="icon-remove icon-white"></i>&nbsp;Close
			</button>
		</div> -->
	</div>


	<div class="container">


		<div class="span8 offset2">

			<form class="form-horizontal" id="profile" method='post' action=''>
				<!-- <form class="form-horizontal span8 offset2"> -->

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
									<input type="checkbox" contenteditable="false"/>
								</div>
							</c:when>
						</c:choose>
						<br/>
						<a href="#contact" data-toggle="modal" class="btn btn-custom"> 
							<i class="icon-edit icon-white"></i>
							<span>
								<strong>Project: <c:out value="${requestScope.project.projectName}" /></strong>
							</span>
						</a>					
						<%-- <i class="icon-edit"></i> Project: <c:out value="${requestScope.project.projectName}" /> --%>
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
												<c:forEach var="member" items="${project.members}">
													<c:if test="${member.role eq 'MANAGER'}">
														<c:set var="managerFirstName" value="${member.firstName}"/>
														<c:set var="managerLastName" value="${member.lastName}"/>
													</c:if>
												</c:forEach>
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

									<div class="control-group" style="display: none;">
										<label class="control-label">Completion Date</label>
										<div class="controls">
											<div class="input-prepend">
												<span class="add-on"><i class="icon-calendar"></i></span>
												<span class="input-xlarge uneditable-input"><fmt:formatDate value="${project.completionDate}" pattern="yyyy-MM-dd"/></span>
											</div>
										</div>
									</div>
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

		<script>
			$("#status").click(
					function () { 
						$(this).effect("pulsate", { times:3 }, 2000); 
					});
		</script>

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
									<img src='./img/user_avatar/<c:out value="${member.photoLink }"></c:out>' width='250' height='250'/>
									<span style="position: absolute; top: 9px; right: 23px; color: #555; font: bold 13px/1 sans-serif;">Click to view</span>
								</span>
							</a>
						</div>
	
						<div class="span2 pagination-centered">
							<p>
								<strong><c:out value="${member.firstName} ${member.lastName}" /></strong>
							</p>
						</div>
	
						<c:if test="${member.role ne 'MANAGER'}">
							<div class="span2 pagination-centered">	            
								<a href="#" class="btn btn-primary" onClick="rem('<c:out value="${loop.index}"/>', '<c:out value="${member.login}"/>', '<c:out value="${project.id}"/>');" >
									<i class="icon-remove icon-white"></i> 
									<strong>Remove</strong>
								</a>
							</div>
						</c:if>
	
					</div>
				</div>
			</c:forEach>
			
			<div class="span2 well">
				<div class="row">

					<div class="span2">
						<a href="add_member_search?id=<c:out value="${project.id}" />" class="thumbnail"> 
							<span class='zoom' id='avatar'> 
								<img src='./img/add_new.gif' width='250' height='250' alt='V for Vendetta' />
								<span style="position: absolute; top: 9px; right: 23px; color: #555; font: bold 13px/1 sans-serif;">
									Click to add
								</span>
							</span>
						</a>
					</div>

				</div>
			</div>
		</div>
		
		<script type="text/javascript">		
			function rem(index, member, project) {
				$('#'+index).remove();
				$.ajax({
			    	type : 'POST',
				    url : 'delete_member',
			    	data : "projectID="+project+"&memberLogin="+member,
			    	success : function(data) {
			     		alert('request sent!');
			    	}
			  	});
			}
		</script>
	
		<script>
			$('#status').on('switch-change', function () {
			    project = '<c:out value="${project.id}"/>';
			    $('#status').bootstrapSwitch('setActive', false);
				$('#status').bootstrapSwitch('setState', false);
				$.ajax({
			    	type : 'POST',
				    url : 'close_project',
			    	data : "projectID="+project,
			    	success : function() {
			    		location.reload();
			    	}
			  	});
				
			});
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
								<!-- <th>Date registered</th>  -->
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

		<!-- <div class="container-fluid">
			<div class="row-fluid">
				<div class="span4 well">area 1</div>
			</div>
		</div>

		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span2 well">area 3</div>
				<div class="span2 well">area 4</div>
				<div class="span2 well">area 5</div>
			</div>
		</div> -->

		<!-- <div class="span1">
			<a href="#" class="btn btn-primary"> <i class="icon-pencil icon-white"></i> <span><strong>Write</strong></span>
			</a>
		</div>

		<div class="span1">
			<a href="#" class="btn btn-primary"> <i class="icon-eye-open icon-white"></i> <span><strong>View</strong></span>
			</a>
		</div>

		<div class="span1">
			<a href="#" class="btn btn-primary"> <i class="icon-edit icon-white"></i> <span><strong>Edit</strong></span>
			</a>
		</div>

		<div class="span1">
			<a href="#" class="btn btn-primary"> <i class="icon-trash icon-white"></i> <span><strong>Delete</strong></span>
			</a>
		</div> -->

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
		});
	</script> -->

</body>

</html>
