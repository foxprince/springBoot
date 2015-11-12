<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html/" %>
<%@ taglib prefix="ajax" tagdir="/WEB-INF/tags/ajax/" %>
<!DOCTYPE html>
<html lang="en">
<c:import url="../include/head.jsp">
	<c:param name="pageTitle" value="手机号段"/>
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
						<a href="./">手机号段</a>
					</li>
				</ul>
			</div>
			<div class="row-fluid">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"/></i>搜索号段</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<html:form id="phoneHeadForm" modelAttribute="pageRequest" formUrl="/phoneHead/list">
						  <table class="table table-condensed">
							<tr bgcolor="#F3F3F3">
								<td>
									<label class="control-label" >省份：</label>
									<form:select path='province' onchange="window.location.href='/phoneHead/list?province='+(this.options[this.selectedIndex].value)" data-rel="chosen" class="input-medium">
										<option value="/phoneHead/list">--全部--</option>
										<form:options items="${provinceMap}" />  
									</form:select>
								</td>
								<td>
									<label class="control-label" >城市：</label>
									<form:select path="city" onchange="window.location.href='/phoneHead/list?province=${pageRequest.province}&city='+(this.options[this.selectedIndex].value)" data-rel="chosen" class="input-medium">
										<option value="">--全部--</option>
										<form:options items="${cityMap}" />  
									</form:select>
								</td>					
								<td>
									<html:inputField name="phone" label="手机号码"/>
								</td>
								<td align="center" >
									  <button type="submit" class="btn btn-primary">搜</button>
									  <input type="hidden" name="action" value="query"/>
								</td>
							</tr>
						  </table>
						</html:form>
					</div>
				</div>
			</div>
			<div class="row-fluid sortable ui-sortable" >
				<div class="box span12">		
					<c:if test="${not empty message}"> 
					<div class="center alert alert-info">
					<button type="button" class="close" data-dismiss="alert">×</button><strong>${message}</strong></div>
					</c:if>
					<div class="box-header well" >
						<h2>
							<i class="icon-list"></i> 手机号段列表
						</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a> <a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a> <a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
							<table class="table table-bordered bootstrap-datatable" >
								<thead>
									<tr>
										<th>编号</th>
										<th>省份</th>
										<th>城市</th>
										<th>邮编</th>
										<th>手机号段</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${itemList}">
										<tr>
											<td>${item.id}</td>
											<td>${item.province}</td>
											<td>${item.city}</td>
											<td>${item.postcode}</td>
											<td>${item.head}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<html:page url="list?1=1" />
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

</body>
</html>
