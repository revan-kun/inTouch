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
					<c:forEach var="member" items="${members}" varStatus="memberOrdinal">
						<tr>
							<td><c:out value="${memberOrdinal.count}"/></td>
							<td><c:out value="${member.login}"/></td>
							<td><c:out value="${member.firstName}"/></td>
							<td><c:out value="${member.lastName}"/></td>
							<td><c:out value="${member.sex}"/></td>
							<td><c:out value="${member.qualificationLevel}"/></td>
							<td><c:out value="${member.experience}"/></td>
							<td><c:out value="${member.role}"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	</div>
</body>
</html>