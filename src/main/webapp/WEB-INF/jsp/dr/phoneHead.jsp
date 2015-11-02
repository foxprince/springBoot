<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html/" %>
<%@ taglib prefix="ajax" tagdir="/WEB-INF/tags/ajax/" %><!DOCTYPE html>
<html lang="en">
<c:import url="../include/head.jsp">
	<c:param name="pageTitle" value="手机号段管理"/>
</c:import>	

<body>
	<!-- topbar -->
	<%@ include file="../include/topbar.jspf" %>
	<div class="container-fluid">
		<div class="row-fluid">
			<!-- left menu-->
			<%@ include file="../include/menu.jspf" %>
			<div id="content" class="span10">
			<div>
				<ul class="breadcrumb">
					<li>
						<a href="../">首页</a> <span class="divider">/</span>
					</li>
					<li>
						<a href="list">手机号段管理</a>
					</li>
				</ul>
			</div>
			<div class="row-fluid">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"/></i>查询条件</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<form class="well form-inline" name="noSelect" method="post">
						  <table class="table table-condensed">
							<tr bgcolor="#F3F3F3">
								<td>
									<label class="control-label" >省份：</lable>
									<select name='province' onchange="window.location.href=(this.options[this.selectedIndex].value)">
										<option value="phoneHead.jsp">--全部--</option>
										<%for(String p : rs.getAllProvince()){
											%><option value="phoneHead.jsp?action=change&province=<%=p %>" <%=province!=null&&province.equals(p)?"selected=true":""%>><%=p %></option><%
										} %>
									</select>
								</td>
								<td>
									<label class="control-label" >城市：</lable>
									<select name="city">
										<option value="">--全部--</option>
										<%if(province!=null){for(String c : rs.getAllCities(province)){ %>
											<option value="<%=c%>" <%=city!=null&&city.equalsIgnoreCase(c)?"selected=true":"" %>><%=c %></option>
										<%} }%>
									</select>
								</td>					
								<td>
									<label class="control-label" >手机号码：</lable>
									<input class="input-small" type="text" name="phone" value="<%=phone!=null?phone:""%>"/>
								</td>
								<td align="center" >
									  <button type="submit" class="btn btn-primary">查看</button>
									  <input type="hidden" name="action" value="query"/>
								</td>
							</tr>
						  </table>
						</form>
					</div>
				</div>
			</div>
			<div class="row-fluid sortable ui-sortable" >
				<div class="box span12">		
					<div class="box-header well" >
						<h2>
							<i class="icon-list"></i>手机号段列表
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
			<%
			org.springframework.data.domain.Page<PhoneHead> resultPage = rs.find(province, city, phone, pageNumber, pageSize);
			for (PhoneHead ph : resultPage) {
			%>
			<tr  align="center" >
				<td  ><%=ph.getId()%></td>
				<td  ><%=ph.getProvince()%></td>
				<td  ><%=ph.getCity()%></td>
				<td  ><%="0"+ph.getPostcode()%></td>
				<td  ><%=ph.getHead()%></td>
			</tr>
			<%}%>
								</tbody>
							</table>
							<div class="row-fluid center">
								<div class="span4">
								<form name="per"  action="phoneHead.jsp" method="post" class="form-inline">
									<div class="input-prepend input-append">
									<span class="add-on">每页</span>
									<input type="text" name="pageSize" class="span2" value="<%=pageSize%>"/>
									<input class="btn btn-primary" type="submit" value="条"/>
									<%if(province!=null){ %>
									<input type="hidden" name=province value="<%=province%>"/>
									<%} %>
									<%if(city!=null){ %>
									<input type="hidden" name="city" value="<%=city%>"/>
									<%} %>
									<%if(phone!=null){ %>
									<input type="hidden" name="msg" value="<%=phone%>"/>
									<%} %><input type="hidden" name="action" value="query"/>								
									</div>
								</form>
								</div>
								<div class="span4" >
									共<%=resultPage.getTotalElements()%>条记录，<%=resultPage.getTotalPages()%>页
								</div>
								<div class="span4">
								<form name="order" action="phoneHead.jsp" method="post" class="form-inline">
									<div class="input-prepend input-append">
									<span class="add-on">到第</span>
									<input type="text" name="pageNumber" class="span2" value="<%=pageNumber%>"/>
									<%if(province!=null){ %>
									<input type="hidden" name=province value="<%=province%>"/>
									<%} %>
									<%if(city!=null){ %>
									<input type="hidden" name="city" value="<%=city%>"/>
									<%} %>
									<%if(phone!=null){ %>
									<input type="hidden" name="msg" value="<%=phone%>"/>
									<%} %>
									<input type="hidden" name="action" value="query"/>
									<input class="btn btn-primary" type="submit" value="页"/>
									</div>
								</form>
								</div>
							</div>
							<div class="span12 pagination pagination-centered">
										<ul>
											<%
											int current = resultPage.getNumber() + 1;
										  int begin = Math.max(1, current - 5);
										  int end = Math.min(begin + 10, resultPage.getTotalPages()); 
											if(!resultPage.isFirstPage()){
												%><li><a href="phoneHead.jsp?action=query<%=province==null?"":"&province="+province %><%=city==null?"":"&city="+city %><%=phone==null?"":"&phone="+phone %>&pageSize=<%=pageSize %>&pageNumber=1">&lt;&lt;</a></li><%}
										  if(resultPage.hasPreviousPage()){
												%><li><a href="phoneHead.jsp?action=query<%=province==null?"":"&province="+province %><%=city==null?"":"&city="+city %><%=phone==null?"":"&phone="+phone %>&pageSize=<%=pageSize %>&pageNumber=<%=pageNumber-1 %>">&lt;</a></li><%}
										  for(int i=begin;i<=end;i++) {
												%>
											 	<li <%=i==current?"class='active'":"" %>><a href="phoneHead.jsp?action=query<%=province==null?"":"&province="+province %><%=city==null?"":"&city="+city %><%=phone==null?"":"&phone="+phone %>&pageSize=<%=pageSize %>&pageNumber=<%=i %>"><%=i%></a></li>
											 	<%
											}
											if(resultPage.hasNextPage()){
												%><li><a href="phoneHead.jsp?action=query<%=province==null?"":"&province="+province %><%=city==null?"":"&city="+city %><%=phone==null?"":"&phone="+phone %>&pageSize=<%=pageSize %>&pageNumber=<%=pageNumber+1 %>">&gt;</a></li><%}
											if(!resultPage.isLastPage()){
												%><li><a href="phoneHead.jsp?action=query<%=province==null?"":"&province="+province %><%=city==null?"":"&city="+city %><%=phone==null?"":"&phone="+phone %>&pageSize=<%=pageSize %>&pageNumber=<%=resultPage.getTotalPages() %>">&gt;&gt;</a></li><%}
											%>
										</ul>
								</div>
						</div>
					</div><!--/span-->
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
