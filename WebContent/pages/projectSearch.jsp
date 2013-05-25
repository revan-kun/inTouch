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
	<link type="text/css" rel="stylesheet" href="css/checkbox/prettyCheckable.css">
	<link type="text/css" rel="stylesheet" href="css/choosen/chosen.css" />
	<link type="text/css" rel="stylesheet" href="css/bootstrapSwitch.css" />
	
	<link type="text/css" rel="stylesheet" href="css/kendo/kendo.css" />
	<link type="text/css" rel="stylesheet" href="css/kendo/kendo.common.min.css" />
	<link type="text/css" rel="stylesheet" href="css/kendo/kendo.default.min.css" />
	
	<script src="js/jquery.min.js"></script>	
	<script src="js/bootstrap.js"></script>
	<script src="js/prettyCheckable.js"></script>
	<script src="js/bootstrapSwitch.js"></script>
	<script src="js/kendo/kendo.web.min.js"></script>
	
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
						<li class="active"><a href=""> Advanced Search </a></li>
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
	
	<div class="container">
	
	<div style="text-align:center;">
		<div id="type" class="switch" data-on="info" data-off="success" data-on-label="Member Search" data-off-label="Project Search" style="width: 250px"
			data-trigger="hover" data-placement="bottom" data-content="Slide to change search type">
			<input type="checkbox"/>
		</div>
	</div>
	
	<br>
	
	<div class="well" style="float:right; width:20%;">
		<form name="search_parameters" method="POST" action="project_search">
			
			<b>Creation date</b><br> 
            <div class="demo-section" style="width:160px"> 
                from: <input id="createdLowerBound" class="datepicker" name="createdLowerBound" placeholder="yyyy-MM-dd" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" style="width: 110px;" />
                <br>
                to: <input id="createdUpperBound" class="datepicker" name="createdUpperBound" placeholder="yyyy-MM-dd" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" style="width: 110px;" />
           	</div>
           	
           	<hr class="bs-docs-separator">
				
			<b>Estimated Completion date</b><br> 
			<div class="demo-section" style="width:160px"> 
                from: <input id="estimatedLowerBound" class="datepicker" name="estimatedLowerBound" placeholder="yyyy-MM-dd" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" style="width: 110px;" />
                <br>
                to: <input id="estimatedUpperBound" class="datepicker" name="estimatedUpperBound" placeholder="yyyy-MM-dd" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" style="width: 110px;" />
           	</div>
           	
			<hr class="bs-docs-separator">
				
			<b>Completion date</b><br> 
			<div class="demo-section" style="width:160px"> 
                from: <input id="completedLowerBound" class="datepicker" name="completedLowerBound" placeholder="yyyy-MM-dd" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" style="width: 110px;" />
                <br>
                to: <input id="completedUpperBound" class="datepicker" name="completedUpperBound" placeholder="yyyy-MM-dd" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" style="width: 110px;" />
           	</div>
           	
            <hr class="bs-docs-separator">
				
			<b>Customer</b><br> 
			<div class="input-prepend">
				<span class="add-on"><i class="icon-globe"></i></span>
				<input class="span2" type="text" name="customer" style="width: 150px;" maxlength="30"> 
			</div>

			<hr class="bs-docs-separator">	
								
			<b>Status</b><br> 
			<input type="radio" class="prettyBox" name="status" value="all" data-label="All" checked/><br>
			<input type="radio" class="prettyBox" name="status" value="open" data-label="Open" data-color="green"/><br> 
			<input type="radio" class="prettyBox" name="status" value="closed" data-label="Closed" data-color="red"/><br>
		
			<br>
			<div style="text-align:center;">
   				<button class="btn btn-info" type="submit">
   				<i class="icon-search icon-white"></i>
   					Perform Search
   				</button>
			</div>
		</form>
	</div>
	
	<div class="span8 well pagination-centered" style="height: 50px">
		<h3>Search Results</h3>
	</div>

	<div class="span8 well pagination-centered">
		<table class="table table-striped table-hover" data-provides="rowlink">
			<thead>
				<tr>
					<th class="header">#</th>
					<th class="header">Name</th>
					<th class="header">Creation date</th>
					<th class="header">Estimated completion</th>
					<th class="header">Completion date</th>
					<th class="header">Customer</th>
					<th class="header">Status</th>
					<th style="width: 20px;"></th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="project" items="${projects}" varStatus="projectOrdinal">
					<tr>						
						<td><a href="project?id=<c:out value="${project.id}" />">
							<c:out value="${projectOrdinal.count}" /></a>
						</td>
						<td><c:out value="${project.projectName}" /></td>
						<td><c:out value="${project.creationDate}" /></td>
						<td><c:out value="${project.estimatedCompletionDate}" /></td>
						<td><c:out value="${project.completionDate}" /></td>
						<td><c:out value="${project.customer}" /></td>
						<td><c:out value="${project.status}" /></td>
					</tr>
					
				</c:forEach>
			</tbody>
		</table>
	</div>

	</div>

	
	<div class="row-fluid">
		<div class="span12 well" style="height: 100px">
			<p class="lead" style="text-align: center">
				The <strong>inTouch</strong> <br /> May The Force Be With Us, © 2013 inTouchTeam
			</p>
		</div>
	</div>
	
	
	<hr class="bs-docs-separator">	
	
	<script>
		$(function () { 
			$('#type').popover();  
		});
		$('input.prettyBox').prettyCheckable();
		$('input.datepicker').kendoDatePicker({
			format: "yyyy-MM-dd",
			start: "yyyy-MM-dd",
			depth: "yyyy-MM-dd"
		});
	</script>
	
	<script>		
		$('#type').on('switch-change', function() {
			$('#type').bootstrapSwitch('setActive', false);
			setTimeout(function(){				
				document.location.href='member_search';		
		 	}, 1000);				
		});
	</script>
</body>
</html>
