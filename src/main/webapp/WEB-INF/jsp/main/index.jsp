<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../include/checkLogin.jspf"%>
<!DOCTYPE html>
<html lang="en">
<c:import url="../include/head.jsp">
	<c:param name="pageTitle" value="首页"/>
</c:import>

<body>
	<!-- topbar -->
	<%@ include file="../include/topbar.jspf" %>
	<div class="container-fluid">
		<div class="row-fluid">
			<!-- left menu-->
			<%@ include file="../include/menu.jspf" %>
			<div id="content" class="span10">
			<!-- content starts -->
				<div>
					<ul class="breadcrumb">
						<li>
							<a href="../main/">首页</a> <span class="divider">/</span>
						</li>
					</ul>
				</div>
				<div class="row-fluid">
					<div class="box span12">
						<div class="box-header well">
							<h2><i class="icon-info-sign"></i> 系统介绍</h2>
							<div class="box-icon">
								<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
								<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
								<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
							</div>
						</div>
						<div class="box-content">
							<h1>功能说明 <small></small></h1>
							<p>MOSCREEN 自有移动运营业务支撑系统。</p>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>
			<!-- content ends -->
			</div><!--/#content.span10-->
		</div><!--/fluid-row-->
		<hr>
		<!-- footer -->
		<%@ include file="../include/footer.jspf" %>
	</div><!--/.fluid-container-->
	<%@ include file="../include/script.jspf" %>
		
</body>
</html>
