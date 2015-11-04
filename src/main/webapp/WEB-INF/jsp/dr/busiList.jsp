<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html/" %>
<%@ taglib prefix="ajax" tagdir="/WEB-INF/tags/ajax/" %>
<!DOCTYPE html>
<html lang="en">
<c:import url="../include/head.jsp">
	<c:param name="pageTitle" value="业务管理"/>
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
						<a href="./">业务管理</a>
					</li>
				</ul>
			</div>
			<div class="row-fluid">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"/></i>${busi.actionDesc}业务</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<form:form class="well form-inline" modelAttribute="busi" id="busiForm" action="/busi/${busi.action} ">
						  <table class="table">
						  <tr>
						  <td>
						  名称：<form:input  path="name" class="input-medium" />
									<form:errors path="name" cssclass="error"/>
						  </td>
						  <td align="center" >
									开/关：<form:select path="active" id="selectError" class="input-medium">  
							            <form:options items="${activeMap}" />  
							        </form:select>  
								</td>
						  <td>说明：<form:textarea path="description" ></form:textarea></td>
						  <td><button type="submit" class="btn btn-primary ">${busi.actionDesc}</button></td>
						  </tr>
						  </table>
						</form:form>
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
							<i class="icon-list"></i> 列表
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
										<th>名称</th>
										<th>说明</th>
										<th>开/关</th>
										<th>时间</th>
										<th>操作</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${itemList}">
										<tr>
											<td>${item.id}</td>
											<td>${item.name}</td>
											<td>${item.description}</td>
											<td>${item.ctime}</td>
											<td>${item.activeDesc}</td>
											<td><a href="/busi/edit?id=${item.id}" class="btn btn-info">修改</a><br />
											</td>
											<td><a href="/busi/delete?id=${item.id}" class="btn btn-danger" onclick='return confirm("您确认删除吗？");'>删除</a><br />
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							
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
