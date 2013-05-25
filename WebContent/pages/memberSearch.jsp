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
	
	<script src="js/jquery.min.js"></script>	
	<script src="js/bootstrap.js"></script>
	<script src="js/prettyCheckable.js"></script>
	<script src="js/bootstrapSwitch.js"></script>
	
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
						<c:choose>
							<c:when test="${not empty requestScope.queryString}">
								<input type="search" class="search-query span3" name="query" autocomplete="off" value="<c:out value="${requestScope.queryString}"/>" tabindex="1">
							</c:when>
							<c:otherwise>
								<input type="search" class="search-query span3" name="query" autocomplete="off" placeholder="search..." tabindex="1" maxlength="30">
							</c:otherwise>
						</c:choose>
						<!-- <input type="search" class="search-query span3" name="query" autocomplete="off" placeholder="search..." tabindex="1"> -->
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
			<input type="checkbox" checked />
		</div>
	</div>	
	
	<br>
	
	<div class="well" style="float:right; width:20%;">
		<form name="search_parameters" method="POST" action="member_search">
			

			<b>Gender</b><br> 
			<input type="radio" class="prettyBox" value="all" name="sex" data-label="Doesn't matter" data-color="green" checked/><br>
			<input type="radio" class="prettyBox" value="male" name="sex" data-label="Male"/><br>
			<input type="radio" class="prettyBox" value="female" name="sex" data-label="Female" data-color="red"/>
			
			<hr class="bs-docs-separator">
			
			<b>Qualification</b><br>
			<input type="checkbox" class="prettyBox" value="junior" name="qualification" data-label="Junior" data-color="green"/><br>
			<input type="checkbox" class="prettyBox" value="middle" name="qualification" data-label="Middle" data-color="green"/><br>
			<input type="checkbox" class="prettyBox" value="senior" name="qualification" data-label="Senior" data-color="green"/><br>
			<input type="checkbox" class="prettyBox" value="joda" name="qualification" data-label="Joda" data-color="red"/><br>
			<input type="checkbox" class="prettyBox" value="godlike" name="qualification" data-label="Godlike" data-color="yellow"/>
			
			<hr class="bs-docs-separator">
			
			<b>Experience</b><br> 
			<div class="input-prepend">
				<span class="add-on">from</span>
				<!-- <input class="span2" id="prependedInput" type="text" placeholder="Username"> -->
				<input class="span2" type="number" name="expirienceLowerBound" style="width: 35px;text-align: center;" min="0" max="30" value="0" > 
			</div>
			<div class="input-prepend">
				<span class="add-on">to</span>
				<input class="span2" type="number" name="expirienceUpperBound" style="width: 35px;text-align: center;" min="0" max="30" value="30" > 
			</div>
			
			<hr class="bs-docs-separator">
			
			<b>Role</b><br>
			<input type="checkbox" class="prettyBox" name="role" value="manager" data-label="Manager"/><br>
			<input type="checkbox" class="prettyBox" name="role" value="developer" data-label="Developer"/><br>
			<input type="checkbox" class="prettyBox" name="role" value="tester" data-label="QA"/><br>
			
			<hr class="bs-docs-separator">

			<b>Skills</b><br> 
			<div>
				<select name="skillName" data-placeholder="select member skills..." style="width:185px;" class="chzn-select" multiple tabindex="6">
					<option value=""></option>
					
					<optgroup label="Programming">
						<c:forEach var="skill" items="${programmingSkills}" >
							<option value="<c:out value="${skill.name}"/>" style="text-transform: lowercase">
								<c:out value="${skill.name}"/>
							</option>
						</c:forEach>						
					</optgroup>
					
					<optgroup label="Language">
						<c:forEach var="skill" items="${languageSkills}" >
							<option value="<c:out value="${skill.name}"/>" style="text-transform: lowercase">
								<c:out value="${skill.name}"/>
							</option>
						</c:forEach>
					</optgroup>
					
					<optgroup label="Technology">
						<c:forEach var="skill" items="${technologySkills}" >
							<option value="<c:out value="${skill.name}"/>" style="text-transform: lowercase">
								<c:out value="${skill.name}"/>
							</option>
						</c:forEach>
					</optgroup>
					
				</select>
			</div>
			
			<hr class="bs-docs-separator">

			<div style="text-align:center;">
   				<button class="btn btn-info" type="submit">
   				<i class="icon-search icon-white"></i>
   					Perform Search
   				</button>
			</div>
		</form>
	</div>
	
	
	<script src="js/chosen.jquery.js" type="text/javascript"></script>
	<script type="text/javascript"> 
	    var config = {
	      '.chzn-select'           : {},
	      '.chzn-select-deselect'  : {allow_single_deselect:true},
	      '.chzn-select-no-single' : {disable_search_threshold:10},
	      '.chzn-select-no-results': {no_results_text:'Oops, nothing found!'},
	      '.chzn-select-width'     : {width:"95%"}
	    }
	    for (var selector in config) {
	      $(selector).chosen(config[selector]);
	    }
	</script>

	
	<div class="span8 well pagination-centered" style="height: 50px">
		<h3>Search Results</h3>
	</div>

	<div class="span8 well pagination-centered">
		<table class="table table-striped table-hover" data-provides="rowlink">
			<thead>
				<tr>
					<th class="header" style="text-align: center;">Ordinal</th>
					<th class="header" style="text-align: center;">Name</th>
					<th class="header" style="text-align: center;">Surname</th>
					<th class="header" style="text-align: center;">Sex</th>
					<th class="header" style="text-align: center;">Level</th>
					<th class="header" style="text-align: center;">Experience</th>
					<th class="header" style="text-align: center;">Role</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="member" items="${members}" varStatus="memberOrdinal">
					<tr>					
						<td style="text-align: center;">
							<a href="member?login=<c:out value="${member.login}" />">
							<c:out value="${memberOrdinal.count}" /></a>
						</td>
						<td style="text-align: center;"><c:out value="${member.firstName}" /></td>
						<td style="text-align: center;"><c:out value="${member.lastName}" /></td>
						<td style="text-align: center;"><c:out value="${member.sex}" /></td>
						<td style="text-align: center;"><c:out value="${member.qualificationLevel}" /></td>
						<td style="text-align: center;"><c:out value="${member.experience}" /></td>
						<td style="text-align: center;"><c:out value="${member.role}" /></td>
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
	
	<script>
		$(function () { 
			$('#type').popover();  
		});
		$('input.prettyBox').prettyCheckable();
	</script>
	
	<script>		
		$('#type').on('switch-change', function() {
			$('#type').bootstrapSwitch('setActive', false);
			setTimeout(function(){				
				document.location.href='project_search';		
		 	}, 1000);				
		});
	</script>
</body>
</html>