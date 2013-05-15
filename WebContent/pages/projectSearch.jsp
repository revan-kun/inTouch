<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!--- <link type="text/css" rel="stylesheet" href="css/bootstrap.css" /> -->

 <link type="text/css" rel="stylesheet" href="css/search.css" /> 

<title>Advanced search for projects</title>
</head>
<body>
	<div style="float:right; width:25%; margin-top:50px">
		<form name="search_parameters" method="POST" action="project_search">

			<br> <b>Created</b><br> 
			from<input type="text"
				name="createdLowerBound"><br> 
			to<input type="text"
				name="createdUpperBound"><br> 
				
			<br> <b>Estimated Completion</b><br> 
			from<input type="text"
				name="estimatedLowerBound"><br> 
			to<input type="text"
				name="estimatedUpperBound"><br> 
				
			<br> <b>Completed</b><br> 
			from<input type="text"
				name="completedLowerBound"><br> 
			to<input type="text"
				name="completedUpperBound"><br> 
				
			<br> <b>Customer</b><br> 
			<input type="text" name="customer"><br>
			
			<b>Status</b><br> 
			<input type="checkbox" name="status" value="open">Open<br> 
			<input type="checkbox" name="status" value="closed">Closed<br>
			<input type="checkbox" name="status" value="frozen">Frozen<br>
			<input type="checkbox" name="status" value="abandoned">Abandoned<br>
			
			<input type="submit" value="submit">
		</form>
	</div>

	<div class="container-fluid">
		<div class="well">
			<div class="span10 well pagination-centered" style="height: 50px">
				<h3>Test project search</h3>
			</div>

			<table class="table">
				<thead>
					<tr>
						<th>Ordinal</th>
						<th>Project name</th>
						<th>Creation date</th>
						<th>Estimated completion date</th>
						<th>Completion date</th>
						<th>Customer</th>
						<th>Status</th>
						<th style="width: 36px;"></th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="project" items="${projects}"
						varStatus="counter">
						<tr>
							<td><c:out value="${counter.count}" /></td>
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
</body>
</html>