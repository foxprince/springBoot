<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<!-- Le styles -->
<link href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/bootstrap/css/doc.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/bootstrap/css/bootstrap-responsive.css"/>" rel="stylesheet">
<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	      <script src="<c:url value="/resources/js/html5.js"/>">
	      </script>
	    <![endif]-->
<!-- Le fav and touch icons -->
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="<c:url value="/resources/bootstrap/ico/apple-touch-icon-144-precomposed.png"/>">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="<c:url value="/resources/bootstrap/ico/apple-touch-icon-114-precomposed.png"/>">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="<c:url value="/resources/bootstrap/ico/apple-touch-icon-72-precomposed.png"/>">
<link rel="apple-touch-icon-precomposed"
	href="<c:url value="/resources/bootstrap/ico/apple-touch-icon-57-precomposed.png"/>">
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				 name : ${message} ！
				
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	
	
	
</body>
</html>
