<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BaseEntity List page</title>
</head>
<body>
	<h1>BaseEntity List page</h1>
	<table style="text-align: center;" border="1px" cellpadding="0"
		cellspacing="0">
		<thead>
			<tr>
				<th width="25px">id</th>
				<th width="150px">title</th>
				<th width="25px">description</th>
				<th width="50px">actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="baseEntity" items="${itemList}">
				<tr>
					<td>${baseEntity.id}</td>
					<td>${baseEntity.title}</td>
					<td>${baseEntity.description}</td>
					<td><a
						href="${pageContext.request.contextPath}/base/edit/${baseEntity.id}">Edit</a><br />
						<a
						href="${pageContext.request.contextPath}/base/delete/${baseEntity.id}">Delete</a><br />
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
<c:url var="firstUrl" value="/base/list?page=1" />
<c:url var="lastUrl" value="/base/list?page=${totalPages}" />
<c:url var="prevUrl" value="/base/list?page=${currentIndex - 1}" />
<c:url var="nextUrl" value="/base/list?page=${currentIndex + 1}" />

<div class="pagination">
    <ul>共${total}条，总${totalPages}页</ul>
    <ul>
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
    </ul>
    <ul>
    
		<table>
			<tbody>
				<tr>
					<td><form:form commandName="pageRequest" method="GET">到第:
					<form:input type="text" size="2" path="page" ></form:input>
					<input type="submit" value="页"/>
					<form:errors path="page" cssclass="error"></form:errors></form:form>
					</td>
					<td><form:form commandName="pageRequest" method="GET">每页:
					<form:input size="2" path="size" />
					<input type="submit" value="条"/><form:errors path="size" cssclass="error"/>
					</form:form></td>
				</tr>
			</tbody>
		</table>
	
    </ul>
</div>
	<a href="${pageContext.request.contextPath}/base/create">Createa new baseEntity</a><br/>
	<a href="${pageContext.request.contextPath}/">Home page</a>
</body>
</html>