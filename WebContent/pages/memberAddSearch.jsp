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
		
		table.center {
		    margin-left:auto; 
		    margin-right:auto;
		}
		
		.btn-custom {
			  background-color: hsl(195, 60%, 35%) !important;
			  background-repeat: repeat-x;
			  filter: progid:DXImageTransform.Microsoft.gradient(startColorstr="#2d95b7", endColorstr="#23748e");
			  background-image: -khtml-gradient(linear, left top, left bottom, from(#2d95b7), to(#23748e));
			  background-image: -moz-linear-gradient(top, #2d95b7, #23748e);
			  background-image: -ms-linear-gradient(top, #2d95b7, #23748e);
			  background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #2d95b7), color-stop(100%, #23748e));
			  background-image: -webkit-linear-gradient(top, #2d95b7, #23748e);
			  background-image: -o-linear-gradient(top, #2d95b7, #23748e);
			  background-image: linear-gradient(#2d95b7, #23748e);
			  border-color: #23748e #23748e hsl(195, 60%, 32.5%);
			  color: #fff !important;
			  text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.16);
			  -webkit-font-smoothing: antialiased;
		}  
	</style>
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
						<li><a href="home"> Home </a></li>
						<li class="active"><a href=""> Add Member Search </a></li>
					</ul>

					<div id="user_signed" class="pull-right">
						<ul class="nav pull-right">
							<li class="dropdown">
								<a id="welcome_user" href="#" class="dropdown-toggle" data-toggle="dropdown">
							 		Welcome, <c:out value="${member.firstName}" /> <b class="caret"></b>
								</a>
								<ul class="dropdown-menu">
									<li><a href="/user/preferences"><i class="icon-cog"></i> Preferences</a></li>
									<li><a href="/help/support"><i class="icon-envelope"></i> Contact Support</a></li>
									<li class="divider"></li>
									<li><a href="logout"><i class="icon-off"></i> Logout</a></li>
								</ul>
							</li>
						</ul>
					</div>
	
				</div>

				<form class="navbar-search form-search pull-right text-center" id="search_form" action="search" method="get">
					<div class="input-append">
						<input type="search" class="search-query span3" name="query" autocomplete="off" placeholder="search..." tabindex="1">
						<button type="submit" class="btn">
							<i class="icon-search icon-large"></i>
						</button>
					</div>
				</form>

			</div>
		</div>
	</div>
	
	<div class="container">
	
	<div style="text-align:center;">
		<a href="project?id=<c:out value="${requestScope.project.id}" />" class="btn btn-custom"> 
			<i class="icon-edit icon-white"></i>
			<span>
				<strong>Get back to <c:out value="${requestScope.project.projectName}" /> project</strong>
			</span>
		</a>
	</div>	
	
	<br>
	
	<div class="well" style="float:right; width:20%;">
		<form name="search_parameters" method="POST" action="add_member_search" >
			

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
			

			<!-- <div class="control-group">
				<label class="control-label">from</label>
				<div class="controls">
					<div class="row-fluid">
						<div class="span3">
							<input type="text" name="expirienceLowerBound" class="input-block-level" autocomplete="off" maxlength="3" pattern="\d{3}" title="Three digits at back of your card">
						</div>
					</div>
				</div>
			</div>
			<br> -->
			
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
			<input type="radio" class="prettyBox" name="role" value="manager" data-label="Doesn't matter" data-color="green" checked/><br>
			<input type="radio" class="prettyBox" name="role" value="developer" data-label="Developer"/><br>
			<input type="radio" class="prettyBox" name="role" value="tester" data-label="QA"/><br>
			
			<hr class="bs-docs-separator">
			
			<!-- <select name="skillType">
				<option value="programming">Programming</option>
				<option value="language">Language</option>
				<option value="technology">Technology</option>
			</select>
			<br> 
			
			<select name="skillName">
			<option value=""></option>
				<option value="Java">Java</option>
				<option value="ENGLISH">ENGLISH</option>
				<option value="CHINESE">CHINESE</option>
				<option value="C#">C#</option>
			</select>  -->
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
			
			<input type="hidden" id="id" name="id" value="<c:out value="${project.id}"/>" />

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
					<th class="header">Ordinal</th>
					<th class="header">Name</th>
					<th class="header">Surname</th>
					<th class="header">Sex</th>
					<th class="header">Level</th>
					<th class="header">Experience</th>
					<th class="header">Role</th>
					<th class="header"></th>
					<!-- <th style="width: 20px;"></th> -->
				</tr>
			</thead>

			<tbody>
				<c:forEach var="member" items="${members}" varStatus="memberOrdinal">
					
					<c:if test="${empty member.activeProjects}">
						<tr id="<c:out value="${memberOrdinal.count}"/>">
							
							<td><a href="member?login=<c:out value="${member.login}" />">
							<c:out value="${memberOrdinal.count}" /></a>
							</td>
							<%-- <td>
								<a href="<c:url value="member?login=${member.login}"/>" class="thumbnail"> 
									<span class='zoom' id='avatar'> 
										<img src='<c:url value="${member.photoLink}"/>' width='50' height='W50'/>
										<span style="position: absolute; top: 9px; right: 23px; color: #555; font: bold 13px/1 sans-serif;">Click to view</span>
									</span>
								</a>
							</td> --%>
							<td><c:out value="${member.firstName}" /></td>
							<td><c:out value="${member.lastName}" /></td>
							<td><c:out value="${member.sex}" /></td>
							<td><c:out value="${member.qualificationLevel}" /></td>
							<td><c:out value="${member.experience}" /></td>
							<td><c:out value="${member.role}" /></td>
							<td>
								<a onClick="addMember('btn-<c:out value="${memberOrdinal.count}"/>','<c:out value="${member.login}"/>', '<c:out value="${project.id}"/>');" class="btn btn-success"
									id="btn-<c:out value="${memberOrdinal.count}"/>"> 
									<i class="icon-plus icon-white"></i>
									<span>Add</span>
								</a>					
							</td>
						</tr>
					</c:if>
					
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
	
	<script type="text/javascript">		
			function addMember(index, member, project) {
				$('#'+index).remove();
				//$('#'+index).prop('value', 'Save');
				$.ajax({
			    	type : 'POST',
				    url : 'add_member',
			    	data : "projectId="+project+"&memberLogin="+member,
			    	success : function(data) {
			     		alert('request sent!');
			    	}
			  	});
			}
		</script>
</body>
</html>