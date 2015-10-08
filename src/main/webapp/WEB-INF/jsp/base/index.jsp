<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home page</title>
</head>
<body>
	<h1>Home page</h1>
	<p>
		Welcome to "BaseEntity application".<br /> <i>${message}</i><br /> 
		<a href="${pageContext.request.contextPath}/base/create">Createa new baseEntity</a><br /> 
		<a
			href="${pageContext.request.contextPath}/base/list">View all
			baseEntitys</a><br />
	</p>
</body>
</html>