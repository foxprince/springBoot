<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html/" %>
<%@ taglib prefix="ajax" tagdir="/WEB-INF/tags/ajax/" %>
<!DOCTYPE html>
<html lang="en">
<c:import url="../include/head.jsp">
	<c:param name="pageTitle" value="业务-省份配置"/>
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
						<a href="../">首页</a> <span class="divider">/</span>
					</li>
					<li>
						<a href="./">业务-省份配置</a>
					</li>
				</ul>
			</div>
			<div class="row-fluid">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2>
						<i class="icon-edit"/></i>业务</h2>
						<select name="channelId" class="form-control" onchange="window.location.href=(this.options[this.selectedIndex].value)">
							<option value="#">请选择</option>
							<c:forEach var="item" items="${busiMap}">
							<option value="/busiProvince/?relateId=${item.key}" ${(not empty busi) && (item.key == busi.id) ? 'selected="selected"' : ''}>${item.value}</option>
							</c:forEach>
						</select>
						
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid sortable ui-sortable" >
				<div class="box span12">		
					<c:if test="${not empty message}"> 
					<div class="center alert alert-info">
					<button type="button" class="close" data-dismiss="alert">×</button><strong>${message}</strong></div>
					</c:if>
					<form:form method="POST" modelAttribute="busiProvinceForm" action="/busiProvince/addBatch">
						<div class="box-header well" >
							<h2>
								<i class="icon-list"></i>  ${busi.name } 省份配置 
							</h2>
							<div class="box-icon">
								<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a> <a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a> <a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
							</div>
						</div>
<style type="text/css">
	label { display: inline;margin-inline-end:15px }
</style>
						<div class="box-content">
							<form:checkboxes  items="${provinceMap}" path="provinceList" />
						</div>
					
					<div class="form-actions">
						<input type="hidden" name="busiId" value="${busi.id }"/>
						<button type="submit" class="btn btn-primary">提交</button>
						<button type="reset" class="btn">Cancel</button>
						</div>
					</form:form>
				
				</div>
				<!--/span-->
			</div>			
		  </div><!--/#content.span10-->
		</div><!--/fluid-row-->
				
		<hr>
<!-- footer -->
		<%@ include file="../include/footer.jspf" %>
		
	</div><!--/.fluid-container-->
    <%@ include file="../include/script.jspf" %>
</body>
</html>
