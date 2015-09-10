<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New BaseEntity page</title>
</head>
<body>
	<h1>New BaseEntity page</h1>
	<form:form method="POST" commandName="baseEntity"
		action="${pageContext.request.contextPath}/base/create.html">
		<table>
			<tbody>
				<tr>
					<td>BaseEntity title:</td>
					<td><form:input path="title" /></td>
					<td><form:errors path="title" cssStyle="color: red;" /></td>
				</tr>
				<tr>
					<td>description:</td>
					<td><form:input path="description" /></td>
					<td><form:errors path="description" cssStyle="color: red;" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Create" /></td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</form:form>
	<a href="${pageContext.request.contextPath}/">Home page</a>
</body>
</html>