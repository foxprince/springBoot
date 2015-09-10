<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BaseEntity List page</title>
</head>
<body>
	<h1>BaseEntity List page</h1>
	<table style="text-align: center;" border="1px" cellpadding="0"
		cellspacing="0">
		<thead>
			<tr>
				<th width="25px">id</th>
				<th width="150px">title</th>
				<th width="25px">description</th>
				<th width="50px">actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="baseEntity" items="${baseEntityList}">
				<tr>
					<td>${baseEntity.id}</td>
					<td>${baseEntity.title}</td>
					<td>${baseEntity.description}</td>
					<td><a
						href="${pageContext.request.contextPath}/base/edit/${baseEntity.id}.html">Edit</a><br />
						<a
						href="${pageContext.request.contextPath}/base/delete/${baseEntity.id}.html">Delete</a><br />
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="${pageContext.request.contextPath}/">Home page</a>
</body>
</html>