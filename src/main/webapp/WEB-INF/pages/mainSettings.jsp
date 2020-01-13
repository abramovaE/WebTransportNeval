<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>



<html>
<head>
  <title>Основные настройки</title>
  <style>
    <%@include file="../../resources/style.css" %>
  </style>
</head>


<body>

<header>
  <div class="container">
    <div>Основные настройки</div>
      <c:forEach items="${topMenu}" var="btn">
          <div><a href="<c:url value="${btn.value}"/>">${btn.key}</a></div>
      </c:forEach>
  </div>
</header>


<div class="top">
  <c:if test="${text != null}">
    <script type="text/javascript">alert('${text}');</script>
  </c:if>


  <c:url var="mainSettingsSaveAction" value="/mainSettings/save"/>
  <form:form action="${mainSettingsSaveAction}" commandName="ms">


        <table class="create_table" id="edit_form">

            <tr><form:hidden path="id"/></tr>

          <tr>
            <td><form:label path="techOfficeAdress"><spring:message text="Технический офис"/></form:label></td>
            <td>
                <%--<input path="techOfficeId" type="hidden" value="${ms.techOffice.id}"/>--%>
                <form:input path="techOfficeAdress" value="${ms.techOffice.address}" list="adressesList" autocomplete="off"/>
                  <datalist id="adressesList">
                      <c:forEach var="adr" items="${allAdresses}">
                          <option value="${adr.address}"></option>
                      </c:forEach>
                  </datalist>
          </td>
          </tr>

          <tr>
            <td><form:label path="officialOfficeAdress"><spring:message text="Главный офис"/></form:label></td>
              <td>
                  <form:input path="officialOfficeAdress" value="${ms.officialOffice.address}" list="adressesList" autocomplete="off"/>
                  <datalist id="adressesList">
                      <c:forEach var="adr" items="${allAdresses}">
                          <option value="${adr.address}"></option>
                      </c:forEach>
                  </datalist>
              </td>
          </tr>

          <tr>
            <td><form:label path="genDirUsername"><spring:message text="Генеральный директор"/></form:label></td>
            <td>
                <form:input path="genDirUsername" value="${ms.genDir.login}" list="usersList" autocomplete="off"/>
                <datalist id="usersList">
                    <c:forEach var="usr" items="${allUsers}">
                        <option label="${usr.shortFullName}" value="${usr.login}"></option>
                    </c:forEach>
                </datalist>
              </td>
          </tr>

          <tr>
            <td><form:label path="finDirUsername"><spring:message text="Финансовый директор"/></form:label></td>
            <td>
                <form:input path="finDirUsername" value="${ms.finDir.login}" list="usersList" autocomplete="off"/>
                <datalist id="usersList">
                    <c:forEach var="usr" items="${allUsers}">
                        <option label="${usr.shortFullName}" value="${usr.login}"></option>
                    </c:forEach>
                </datalist>
            </td>
          </tr>

          <tr>
            <td><form:label path="glavBuhUsername"><spring:message text="Главный бухгалтер"/></form:label></td>
            <td>
                <form:input path="glavBuhUsername" value="${ms.glavBuh.login}" list="usersList" autocomplete="off"/>
                <datalist id="usersList">
                    <c:forEach var="usr" items="${allUsers}">
                        <option label="${usr.shortFullName}" value="${usr.login}"></option>
                    </c:forEach>
                </datalist>
            </td>
          </tr>

            <%--<tr>--%>
                <%--<td><form:label path="emailAdress"><spring:message text="e-mail для отправки"/></form:label></td>--%>
                <%--<td><form:input path="emailAdress"/></td>--%>
            <%--</tr>--%>

            <%--<tr>--%>
                <%--<td><form:label path="emailPassword"><spring:message text="пароль для отправки"/></form:label></td>--%>
                <%--<td><form:input path="emailPassword"/></td>--%>
            <%--</tr>--%>

            <tr>
                <td><form:label path="devEmailAdress"><spring:message text="e-mail разработчика"/></form:label></td>
                <td><form:input path="devEmailAdress"/></td>
            </tr>

            <%--<tr>--%>
                <%--<td><form:label path="pathToSource"><spring:message text="Путь к файлам"/></form:label></td>--%>
                <%--<td><form:input path="pathToSource"/></td>--%>
            <%--</tr>--%>

            <%--<tr>--%>
                <%--<td><form:label path="stsPath"><spring:message text="Путь к СТС"/></form:label></td>--%>
                <%--<td><form:input path="stsPath"/></td>--%>
            <%--</tr>--%>

            <%--<tr>--%>
                <%--<td><form:label path="osagoPath"><spring:message text="Путь к ОСАГО"/></form:label></td>--%>
                <%--<td><form:input path="osagoPath"/></td>--%>
            <%--</tr>--%>


            <%--<tr>--%>
                <%--<td><form:label path="vuPath"><spring:message text="Путь к ВУ"/></form:label></td>--%>
                <%--<td><form:input path="vuPath"/></td>--%>
            <%--</tr>--%>

            <%--<tr>--%>
                <%--<td><form:label path="schemePath"><spring:message text="Путь к папке со схемами включения"/></form:label></td>--%>
                <%--<td><form:input path="schemePath"/></td>--%>
            <%--</tr>--%>


        <%--<tr>--%>
                <%--<td><form:label path="resultReportFile"><spring:message text="путь к шаблону отчета"/></form:label></td>--%>
                <%--<td><form:input path="resultReportFile"/></td>--%>
            <%--</tr>--%>



            <tr>
                <td></td>
                <td><input id="submit_button" type="submit" value="<spring:message text="Сохранить"/>"/></td>
            </tr>
        </table>

  </form:form>





  </div>
</div>
</body>
</html>
