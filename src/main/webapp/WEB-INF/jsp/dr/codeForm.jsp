<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html/" %>
<%@ taglib prefix="ajax" tagdir="/WEB-INF/tags/ajax/" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:import url="../include/head.jsp">
	<c:param name="pageTitle" value="代码配置"/>
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
					<li><a href="../main/">首页</a> <span class="divider">/</span></li>
					<li><a href="../dr/codeMng.jsp">代码列表</a> <span class="divider">/</span></li>
					<li>新增/修改代码</li>
				</ul>
			</div>
			
			<div class="row-fluid sortable ui-sortable" >
				<div class="box span12">		
					<div class="box-header well" data-original-title>
						<h2>
							<i class="icon-edit"></i> 新增/修改代码
						</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a> <a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a> <a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<html:form modelAttribute="codeConfig"  id="add-user-form" formUrl="/code/codeConfig.htm">
							  <html:inputField name="spId" label="SP名称" />
							  <html:inputField name="code" label="指令代码" />
							  <html:inputField name="spNo" label="服务号码" />
							  <html:inputField name="matchType" label="匹配方式" />
							  <html:inputField name="fee" label="计费值(分)" />
							  <html:inputField name="channelId" label="渠道名称" />
							  <div class="form-actions">
								<button type="submit" class="btn btn-primary">提交</button>
								<button type="reset" class="btn">Cancel</button>
							  </div>
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
	<ajax:formPartialRefresh validateUrl="/code/codeConfig.json" formName="add-user-form"/>
</body>
</html>
