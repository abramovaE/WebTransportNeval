<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>


<html>
<head>
  <title>Сводный отчет графиков расхода топлива за ${year} год</title>
  <style>
    <%@include file="../../resources/style.css" %>
  </style>
</head>


<body>

<header>
  <div class="container">
    <div>Сводный отчет графиков расхода топлива за ${year} год</div>
    <c:forEach items="${topMenu}" var="btn">
      <div><a href="<c:url value="${btn.value}"/>">${btn.key}</a></div>
    </c:forEach>
  </div>
</header>

<div class="top">

  <div>
    <c:if test="${text != null}">
      <script type="text/javascript">alert('${text}');</script>
    </c:if>
  </div>

  <table class="graphics">
    <th></th>

    <c:if test="${! empty months}">
      <c:forEach items="${months}" var="month">
        <th>
            ${month}
        </th>
      </c:forEach>
    </c:if>


    <c:forEach items="${graphicsMap.keySet()}" var="user">
      <tr>
        <td id="empty">
            ${user.shortFullName}
        </td>


        <c:forEach begin="0" end="${months.size()-1}" varStatus="m">
          <td class="td_graphics">

              <%--${graphicsMap.get(user).listOfTotalSumm.}--%>

            <c:if test="${graphicsMap.get(user).listOfTotalSumm ne null && graphicsMap.get(user).listOfCompany ne null && graphicsMap.get(user).listOfTotalSumm.get(m.current) > 0}">

              <c:choose>
                <c:when test="${graphicsMap.get(user).listOfTotalSumm.get(m.current) > graphicsMap.get(user).listOfCompany.get(m.current)}">
                  <div class="rect total_color" style="height: ${graphicsMap.get(user).listOfTotalSumm.get(m.current)/50}">
                    <div class="text_color_total max">${graphicsMap.get(user).listOfTotalSumm.get(m.current)}</div>
                  </div>

                  <div class="rect company_color" style="height: ${graphicsMap.get(user).listOfCompany.get(m.current)/50}">
                    <div class="text_color_company min">${graphicsMap.get(user).listOfCompany.get(m.current)}</div>
                  </div>
                </c:when>

                <c:otherwise>
                  <div class="rect company_color" style="height: ${graphicsMap.get(user).listOfCompany.get(m.current)/50}">
                    <div class="text_color_company max">${graphicsMap.get(user).listOfCompany.get(m.current)}</div>
                  </div>

                  <div class="rect total_color" style="height: ${graphicsMap.get(user).listOfTotalSumm.get(m.current)/50}">
                    <div class="text_color_total min">${graphicsMap.get(user).listOfTotalSumm.get(m.current)}</div>
                  </div>
                </c:otherwise>
              </c:choose>
            </c:if>
          </td>
        </c:forEach>






      <tr/>
    </c:forEach>
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


