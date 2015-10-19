<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <link rel="stylesheet" href="./css/world.css" type="text/css"/>
    <title>
      <spring:message code="country.details"/>
    </title>
  </head>
  <body>
    <h1><spring:message code="application.name"/></h1>
    <form:form modelAttribute="country" action="countryForm.html" method="post">
      <table>
         <tr>
          <td align="right" valign="bottom">
            <c:choose>
              <c:when test="${country.create}">
                <button type="submit"
                        name="create">
                  Create
                </button>
              </c:when>
              <c:otherwise>
                <button type="submit"
                        name="delete"
                        onclick="return confirm('Are you sure you want to delete ${country.name}?')">
                  Delete
                </button>
                <button type="submit"
                        name="update">
                  Update
                </button>
              </c:otherwise>
            </c:choose>
          </td>
        </tr>
        <tr>
          <td>
            <table class="silver">
              <tr>
                <th colspan=2><spring:message code="country.details"/></th>
              </tr>
              <tr>
                <td><spring:message code="country.name"/></td>
                <td>
                  <form:input path="name" size="20" maxlength="50" />
                  <form:errors path="name" cssClass="errors"/>
                </td>
              </tr>
              <tr>
                <td><spring:message code="country.area"/></td>
                <td>
                  <form:input path="area" size="8" maxlength="8" />
                  <form:errors path="area" cssClass="errors"/>
                </td>
              </tr>
              <tr>
                <td><spring:message code="country.population"/></td>
                <td>
                  <form:input path="population" size="10" maxlength="10" />
                  <form:errors path="population" cssClass="errors"/>
                </td>
              </tr>
              <tr>
                <td><spring:message code="country.updatedOn"/></td>
                <td>
                  <form:input path="populationLastUpdated" size="12" maxlength="12" />
                  <form:errors path="populationLastUpdated" cssClass="errors"/>
                </td>
              </tr>
              <tr>
                <td><spring:message code="country.currency"/></td>
                <td>
                  <form:input path="currency" size="20" maxlength="50" />
                  <form:errors path="currency" cssClass="errors"/>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </form:form>
    <a href="countryList.html">
      &lt;&lt;<spring:message code="navigation.back"/>
    </a>
  </body>
</html>
