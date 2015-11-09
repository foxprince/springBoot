<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<head>
	<meta charset="utf-8">
	<title>${param.pageTitle}_<spring:message code="website.name"/></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
	<meta name="author" content="Muhammad Usman">
	
	<!-- The styles -->
	<link id="bs-css" href="../resources/css/bootstrap-united.css" rel="stylesheet">
	<style type="text/css">
	  body {
		padding-bottom: 40px;
	  }
	  .sidebar-nav {
		padding: 9px 0;
	  }
	</style>
	<link href="../resources/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="../resources/css/charisma-app.css" rel="stylesheet">
	<link href="../resources/css/jquery-ui-1.8.21.custom.css" rel="stylesheet">
	<link href='../resources/css/chosen.css' rel='stylesheet'>
	<link href='../resources/css/uniform.default.css' rel='stylesheet'>
	<link href='../resources/css/colorbox.css' rel='stylesheet'>
	<link href='../resources/css/opa-icons.css' rel='stylesheet'>
	<link href='../resources/css/uploadify.css' rel='stylesheet'>
	<link href="../resources/css/datetimepicker.css" rel="stylesheet" media="screen">
	<!-- <link href='../resources/css/data_table.css' rel='stylesheet'> -->

	<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
	  <script src="../resources/js/html5.js"></script>
	<![endif]-->
  
	<!-- The fav icon -->
	<link rel="shortcut icon" href="../resources/img/favicon.ico">
	
</head>