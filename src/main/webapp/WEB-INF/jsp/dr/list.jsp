<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<c:import url="../include/head.jsp">
	<c:param name="pageTitle" value="发送日志"/>
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
					<li>
						<a href="./">发送日志</a>
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
						<form:form class="well form-inline" action="list" commandName="drPageRequest" method="POST">
						  <table class="table table-condensed table-bordered">
							<script src="../resources/js/dateControl.js"></script>
							<tr bgcolor="#F3F3F3">
								<td>
									<label class="control-label" >起始日期：</lable>
									<form:input  path="beginTime" class="input-medium form_datetime" />
									<form:errors path="beginTime" cssclass="error"/>
								</td>
								<td>
									<label class="control-label" >结束日期：</lable>
									<form:input  path="endTime" class="input-medium form_datetime" />
									<form:errors path="endTime" cssclass="error"/>
								</td>
								<td align="center" >
									SP：<form:select path="spId"  data-rel="chosen" class="input-medium">  
							            <form:options items="${spMap}" />  
							        </form:select>  
								</td>
								<td>
									<label class="control-label" >手机号码：</lable><form:textarea class="input-medium" path="phoneStr" placeHolder="多个号码用半角逗号隔开" />
									<form:errors path="phoneStr" cssclass="error"/>
								</td>
								<td>
									渠道：<form:select path="channelId"  data-rel="chosen" class="input-medium">  
							            <form:options items="${channelMap}" />  
							        </form:select>  
								</td>
								<td align="center"  >
									<!-- <div class="form-actions center"> -->
									  <button type="submit" class="btn btn-primary">查看</button>
									  <button type="button" class="btn">取消</button>
									  <input type="hidden" name="action" value="refresh"/>
									<!-- </div> -->
								</td>
							</tr>
						  </table>
						</form:form>
					</div>
				</div>
			</div>
			<div class="row-fluid sortable ui-sortable" >
				<div class="box span12">		
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
										<th>id</th>
										<th>SP名称</th>
										<th>手机号码</th>
										<th>服务号码</th>
										<th>短信指令</th>
										<th>状态报告</th>
										<th>费用</th>
										<th>接收时间</th>
										<th>渠道名称</th>
										<th>转发状态</th>
										<th>转发时间</th>
										<th>减扣</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${itemList}">
										<tr>
											<td>${item.id}</td>
											<td>${item.spId}</td>
											<td>${item.phone}</td>
											<td>${item.spNo}</td>
											<td>${item.msg}</td>
											<td>${item.status}</td>
											<td>${item.fee}</td>
											<td>${item.recvTime}</td>
											<td>${item.channelId}</td>
											<td>${item.forwardStatus}</td>
											<td>${item.forwardTime}</td>
											<td>${item.deductFlag}</td>
										</tr>
									</c:forEach>
			
								</tbody>
							</table>
<c:url var="firstUrl" value="list?page=1" />
<c:url var="lastUrl" value="list?page=${totalPages}" />
<c:url var="prevUrl" value="list?page=${currentIndex - 1}" />
<c:url var="nextUrl" value="list?page=${currentIndex + 1}" />

							<div class="row-fluid center">
								<div class="span4">
								<form:form action="list" commandName="drPageRequest" method="GET">每页:
								<form:input  path="size" class="input-short"/>
								<input type="submit" value="条"/><form:errors path="size" cssclass="error"/>
								</form:form>
								</div>
								<div class="span4" >
									共${total}条，总${totalPages}页。
								</div>
								<div class="span4">
								<form:form action="list" commandName="drPageRequest" method="GET">到第:
								<form:input type="text"  path="page" class="input-short"></form:input>
								<input type="submit" value="页"/>
								<form:errors path="page" cssclass="error"></form:errors></form:form>
								</div>
								
							</div>
							<div class="span12 pagination pagination-centered">
							<c:choose>
					            <c:when test="${hasPrevious}">
					                <li><a href="${firstUrl}">&lt;&lt;</a></li>
					                <li><a href="${prevUrl}">&lt;</a></li>
					            </c:when>
					        </c:choose>
					        <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
					            <c:url var="pageUrl" value="list?page=${i}" />
					            <c:choose>
					                <c:when test="${i == currentIndex}">
					                    <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
					                </c:when>
					                <c:otherwise>
					                    <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
					                </c:otherwise>
					            </c:choose>
					        </c:forEach>
					        <c:choose>
					            <c:when test="${hasNext}">
					                <li><a href="${nextUrl}">&gt;</a></li>
					                <li><a href="${lastUrl}">&gt;&gt;</a></li>
					            </c:when>
					        </c:choose>			
							</div>
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
<script type="text/javascript" src="../resources/js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="../resources/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>

<script type="text/javascript">
    $(".form_datetime").datetimepicker({
        autoclose: true,
        todayBtn: true,
        minView:2,
        pickerPosition: "bottom-left"
    });
</script>       
		
</body>
</html>
