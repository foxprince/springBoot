<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html/" %>
<%@ taglib prefix="ajax" tagdir="/WEB-INF/tags/ajax/" %>
<!DOCTYPE html>
<html lang="en">
<c:import url="../include/head.jsp">
	<c:param name="pageTitle" value="渠道-业务代码-省份配置"/>
</c:import>	
<style type="text/css">
label { display: inline;margin-inline-end:15px }
</style>
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
						<a href="./">渠道-业务代码-省份配置</a>
					</li>
				</ul>
			</div>
			
			<form:form cssClass="form-horizontal"  method="POST" modelAttribute="channelCodeProvinceForm" action="/channelCodeProvince/addBatch">
			<div class="row-fluid " >
				<div class="box span12">		
					<c:if test="${not empty message}"> 
					<div class="center alert alert-info">
					<button type="button" class="close" data-dismiss="alert">×</button><strong>${message}</strong></div>
					</c:if>
					<div class="box-head well">
						<table class="table table-condensed">
							<tr bgcolor="#F3F3F3">
								<td>
									<label class="control-label" >渠道：</label>
									<form:select path='channelId' onchange="window.location.href='/channelCodeProvince/?channelId='+(this.options[this.selectedIndex].value)"  class="input-medium">
										<option value="#">请选择</option>
										<form:options items="${channelMap}" />  
									</form:select>
								</td>
								<td>
									<label class="control-label" >业务代码：</label>
									<form:select path="codeId" onchange="window.location.href='/channelCodeProvince/?channelId=${channelCodeProvinceForm.channelId}&codeId='+(this.options[this.selectedIndex].value)"  class="input-medium">
										<option value="#">请选择</option>
										<form:options items="${checkedCodeMap}" />  
									</form:select>
								</td>					
								
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="row-fluid sortable ui-sortable" >		
						<div class="box-content ">
							<form:checkboxes cssStyle="display:inline;overflow:auto" items="${provinceMap}" path="provinceList" />
						</div>
						<div class="form-actions">
							<button type="submit" class="btn btn-primary">提交</button>
							<button type="reset" class="btn">Cancel</button>
						</div>
			</div>
			</form:form>
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
