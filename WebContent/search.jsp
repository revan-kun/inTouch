<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!--- <link type="text/css" rel="stylesheet" href="css/bootstrap.css" /> -->

<link type="text/css" rel="stylesheet" href="css/search.css" />

<title>Insert title here</title>
</head>
<body>
	<div style="float:right; width:25%; margin-top:50px">
		<form name="search_parameters" method="POST" action="search">
			<b>Gender</b><br> 
			<input type="radio" name="sex" value="male">Male<br>
			<input type="radio" name="sex" value="female">Female<br>
			<b>Qualification</b><br> 
			<input type="checkbox" name="qualification" value="junior">Junior<br> 
			<input type="checkbox" name="qualification" value="middle">Middle<br>
			<input type="checkbox" name="qualification" value="senior">Senior<br>
			<input type="checkbox" name="qualification" value="joda">Joda<br>
			<input type="checkbox" name="qualification" value="godlike">Godlike<br>
			<b>Experience</b><br> from<input type="text" name="expirienceLowerBound"><br>
			to<input type="text" name="expirienceUpperBound"><br> <b>Role</b><br>
			<input type="checkbox" name="role" value="manager">Junior<br>
			<input type="checkbox" name="role" value="developer">Middle<br>
			<input type="checkbox" name="role" value="tester">Senior<br>
			<b>Skills</b><br> <select name="skillType">
				<option value="programming">Programming</option>
				<option value="language">Language</option>
				<option value="technology">Technology</option>
			</select> <br> 
			<select name="skillName">
				<option value="Java">Java</option>
				<option value="C++">C++</option>
				<option value="Phyton">Phyton</option>
				<option value="Scala">Phyton</option>
				<option value="C#">Phyton</option>
			</select> <br> <b>Experience</b><br> 
			from<input type="text"
				name="skillExperienceLowerBound"><br> 
			to<input type="text"
				name="skillExperienceUpperBound"><br> 
			
			<b>Self assested level</b><br>
			from<input type="text" name="lowerBoundLevel"><br> to<input
				type="text" name="upperBoundLevel"><br> <input type="submit"
				value="submit">
		</form>
	</div>

	<div class="container-fluid">
		<div class="well">
			<div class="span10 well pagination-centered" style="height: 50px">
				<h3>Search</h3>
			</div>

			<table class="table">
				<thead>
					<tr>
						<th>Ordinal</th>
						<th>Login</th>
						<th>Name</th>
						<th>Surname</th>
						<th>Sex</th>
						<th>Level</th>
						<th>Experience</th>
						<th>Role</th>
						<th style="width: 36px;"></th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="member" items="${members}"
						varStatus="memberOrdinal">
						<tr>
							<td><c:out value="${memberOrdinal.count}" /></td>
							<td><c:out value="${member.login}" /></td>
							<td><c:out value="${member.firstName}" /></td>
							<td><c:out value="${member.lastName}" /></td>
							<td><c:out value="${member.sex}" /></td>
							<td><c:out value="${member.qualificationLevel}" /></td>
							<td><c:out value="${member.experience}" /></td>
							<td><c:out value="${member.role}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	</div>
</body>
</html>