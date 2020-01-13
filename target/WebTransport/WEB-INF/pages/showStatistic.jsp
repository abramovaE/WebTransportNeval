<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>



<html>
<head>
    <title>${title}</title>
    <style>
        <%@include file="../../resources/style.css" %>
    </style>
</head>


<body>


<header>
    <div class="container">
        <div>${title}</div>
        <c:forEach items="${topMenu}" var="btn">
            <div><a href="<c:url value="${btn.value}"/>">${btn.key}</a></div>
        </c:forEach>
    </div>
</header>

<div class="top">
    <c:if test="${!empty users}">
        <table class="small_table">

            <tr>
                <th>Водитель</th>
                <th>Проезд в нуждах компании, руб</th>
                <th>Компенсация амортизации, руб</th>
                <th>Количество отработанных дней</th>
            </tr>
            <th>
            </th>

            <c:forEach items="${users}" var="user">
                <tr>
                    <td>
                        ${user.user.shortFullName}
                    </td>

                    <td>
                        ${user.company}
                    </td>
                    <td>
                            ${user.totalAmort}
                    </td>
                    <td>
                            ${user.workingDays}
                    </td>
                </tr>

            </c:forEach>


        </table>

    </c:if>




</div>
</body>
</html>



