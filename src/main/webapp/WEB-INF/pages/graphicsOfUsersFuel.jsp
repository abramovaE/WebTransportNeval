<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>




<html>
<head>
  <title>Помесячный график расхода топлива пользователя ${user.shortFullName} за ${year}</title>
  <style>
    <%@include file="../../resources/style.css" %>
  </style>
</head>

<body>

<header>
    <div class="container">
        <div>Помесячный график расхода топлива пользователя ${user.shortFullName} за ${year}</div>
        <c:forEach items="${topMenu}" var="btn">
            <div><a href="<c:url value="${btn.value}"/>">${btn.key}</a></div>
        </c:forEach>
    </div>
</header>




<div class="top">
    <table class="graphics">
    <c:if test="${! empty months}">
    <c:forEach items="${months}" var="month">
    <th>
        ${month}
    </th>
    </c:forEach>
    </c:if>
        <tr>
            <c:forEach begin="0" end="${months.size()-1}" varStatus="m">
                <td class="td_graphics">
                    <c:if test="${listOfTotalSumm.get(m.current) > 0}">
                        <c:choose>
                            <c:when test="${listOfTotalSumm.get(m.current) > listOfCompany.get(m.current)}">
                                <div class="rect total_color" style="height: ${listOfTotalSumm.get(m.current)/50}">
                                    <div class="text_color_total max">${listOfTotalSumm.get(m.current)}</div>
                                </div>

                                <div class="rect company_color" style="height: ${listOfCompany.get(m.current)/50}">
                                    <div class="text_color_company min">${listOfCompany.get(m.current)}</div>
                                </div>
                            </c:when>

                            <c:otherwise>
                                <div class="rect company_color" style="height: ${listOfCompany.get(m.current)/50}">
                                    <div class="text_color_company max">${listOfCompany.get(m.current)}</div>
                                </div>

                                <div class="rect total_color" style="height: ${listOfTotalSumm.get(m.current)/50}">
                                    <div class="text_color_total min">${listOfTotalSumm.get(m.current)}</div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </td>
            </c:forEach>
        </tr>
  </table>

    <div class="legend">
        <div class="legend_child">
            <div class="icon total_color text_color_total"></div>
            <div class="text_icon text_color_total"> - Компенсация ГСМ (сумма по чекам)</div>
        </div>

        <div class="legend_child">
            <div class="icon company_color text_color_company"></div>
            <div class="text_icon text_color_company"> - Проезд в нуждах компании</div>
        </div>
    </div>



</div>
</body>
</html>
