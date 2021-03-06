<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Edit BaseEntity page</title>
</head>
<body>
	<h1>Edit BaseEntity page</h1>
	<form:form method="POST" commandName="baseEntity"
		action="${pageContext.request.contextPath}/base/edit/${baseEntity.id}.html">
		<table>
			<tbody>
				<tr>
					<td>BaseEntity name:</td>
					<td><form:input path="title" /></td>
					<td><form:errors path="title" cssStyle="color: red;" /></td>
				</tr>
				<tr>
					<td>Employees number:</td>
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