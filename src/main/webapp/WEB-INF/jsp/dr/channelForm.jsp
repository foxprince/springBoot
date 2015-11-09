<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html/" %>
<%@ taglib prefix="ajax" tagdir="/WEB-INF/tags/ajax/" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="cn">
<c:import url="../include/head.jsp">
	<c:param name="pageTitle" value="渠道管理"/>
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
					<li><a href="../">首页</a> <span class="divider">/</span></li>
					<li><a href="../channel/">渠道列表</a> <span class="divider">/</span></li>
					<li>${channel.actionDesc}渠道</li>
				</ul>
			</div>
			
			<div class="row-fluid sortable ui-sortable" >
				<div class="box span12">		
					<div class="box-header well" data-original-title>
						<h2>
							<i class="icon-edit"></i> ${channel.actionDesc}渠道
						</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a> <a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a> <a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<html:form modelAttribute="channel"  id="add-user-form" formUrl="/channel/${channel.action}">
							  <html:inputField name="name" label="渠道名称" />
							  <html:inputField name="corp" label="公司名称" />
							  <html:inputField name="linkman" label="联系人" />
							  <html:inputField name="phone" label="联系电话" />
							  <html:inputField name="email" label="电子邮箱" />
							  <html:inputField name="qq" label="QQ" />
							  <div class="control-group" >
								<label class="control-label">开/关</label> 
								<div class="controls">
									<form:select path="active" id="selectError" class="input-medium">  
							            <form:options items="${activeMap}" />  
							        </form:select>
								</div>
							  </div>
							 	
							  <div class="form-actions">
								<button type="submit" class="btn btn-primary">提交</button>
								<button type="reset" class="btn">Cancel</button>
							  </div>
							  <input type="hidden" name="id" value="${channel.id }"/>
						</html:form>  
							
					</div>
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
	<ajax:formPartialRefresh validateUrl="/channel/${channel.action}.json" formName="add-user-form"/>
</body>
</html>
