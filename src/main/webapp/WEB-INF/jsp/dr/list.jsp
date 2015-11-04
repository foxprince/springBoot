<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html/" %>
<%@ taglib prefix="ajax" tagdir="/WEB-INF/tags/ajax/" %>
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
						<form:form class="well form-inline" action="list" commandName="pageRequest" method="POST">
						  <table class="table table-condensed table-bordered">
							<script src="../resources/js/dateControl.js"></script>
							<tr bgcolor="#F3F3F3">
								<td>
									<label class="control-label" >起始日期：</label>
									<form:input  path="beginTime" class="input-medium form_datetime" />
									<form:errors path="beginTime" cssclass="error"/>
								</td>
								<td>
									<label class="control-label" >结束日期：</label>
									<form:input  path="endTime" class="input-medium form_datetime" />
									<form:errors path="endTime" cssclass="error"/>
								</td>
								<td align="center" >
									SP：<form:select path="spId" id="selectError" data-rel="chosen" class="input-medium">  
							            <option value="0">请选择</option>
							            <form:options items="${spMap}" />  
							        </form:select>  
								</td>
								<td>
									<label class="control-label" >手机号码：</label><form:textarea class="input-medium" path="phoneStr" placeHolder="多个号码用半角逗号隔开" />
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
