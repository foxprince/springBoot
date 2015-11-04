<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html/" %>
<%@ taglib prefix="ajax" tagdir="/WEB-INF/tags/ajax/" %>
<!DOCTYPE html>
<html lang="en">
<c:import url="../include/head.jsp">
	<c:param name="pageTitle" value="黑白名单"/>
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
						<a href="./">黑白名单</a>
					</li>
				</ul>
			</div>
			<div class="row-fluid">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"/></i>添加黑白名单</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<form class="well form" method="post" name="noSelect" action="/phone/addPhone"  enctype="multipart/form-data">
						  <table class="table">
						  <tr>
						  <td>类型：
						  <select name="stype">
						  	<option value="B">黑名单</option>
						  	<option value="W">白名单</option>
						  </select>
						  </td>
						  <td>手机号码：<textarea name="phoneStr" class="input-medium"  placeholder="手机号码，多个号码空格或换行分隔"></textarea></td>
						  <td><label class="control-label" >文件：</label><input type="file" name="file"/></td>
						  <td>
						  <button type="submit" class="btn btn-primary ">添加</button></td>
						  </tr>
						  </table>
						</form>
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
							<div class="input-prepend input-append">
								<form:form class="navbar-search pull-right" modelAttribute="pageRequest" action="/phone/list">
									<span class="add-on">手机号码：</span><form:input path="phone"  id="appendedInputButton" size="13"/><button class="btn" type="submit">搜</button>
								</form:form>	
							</div>
							<table class="table table-bordered bootstrap-datatable" >
								<thead>
									<tr>
										<th>手机号码</th>
										<th>类型</th>
										<th>加入时间</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${itemList}">
										<tr>
											<td>${item.phone}</td>
											<td>${item.stypeDesc}</td>
											<td>${item.ctime}</td>
											<td>
											<a href="/phone/delete/${item.id}" class="btn btn-warning">删除</a><br />
											</td>
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
