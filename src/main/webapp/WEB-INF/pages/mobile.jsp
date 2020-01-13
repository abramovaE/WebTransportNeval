<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>


<html>
<head>
    <title>Компенсация мобильной связи за ${year} год</title>
    <style>
        <%@include file="../../resources/style.css" %>
    </style>
</head>


<body>

<header>
    <div class="container">
        <div>Компенсация мобильной связи за ${year} год</div>
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
    <%--<c:url var="saveMobileAction" value="/mobile/save"/>--%>
<%--<form:form action="${saveMobileAction}" commandName="reportsMap">--%>

    <table class="mobile_table">
        <th></th>

        <c:if test="${! empty months}">
            <c:forEach items="${months}" var="month">
                <th>
                        ${month}
                </th>
            </c:forEach>
        </c:if>


        <c:forEach items="${reportsMap.keySet()}" var="user">
            <tr>
                <td id="empty">
                        ${user.shortFullName}
                </td>
                <c:forEach begin="0" end="${months.size()-1}" varStatus="m">
                <c:choose>
                <c:when test="${reportsMap.get(user).get(m.current) eq null}">
                <td>-</td>
                </c:when>

                <c:otherwise>
                <td>

                    <c:choose>
                        <c:when test="${reportsMap.get(user).get(m.current).closed}">
                            <c:if test="${reportsMap.get(user).get(m.current).mobile ne null}">
                                ${reportsMap.get(user).get(m.current).mobile}
                            </c:if>

                            <c:if test="${reportsMap.get(user).get(m.current).mobile eq null && reportsMap.get(user).get(m.current).mobileWeeks ne null }">
                                ${reportsMap.get(user).get(m.current).mobileWeeks * 100}
                            </c:if>

                        </c:when>
                        <c:otherwise>
                            <c:if test="${reportsMap.get(user).get(m.current).mobile ne null}">
                                <a id="save_button" href="<c:url value="/mobile/add/${reportsMap.get(user).get(m.current).id}"/>">${reportsMap.get(user).get(m.current).mobile}</a>
                            </c:if>

                            <c:if test="${reportsMap.get(user).get(m.current).mobile eq null && reportsMap.get(user).get(m.current).mobileWeeks ne null }">
                                <a id="save_button" href="<c:url value="/mobile/add/${reportsMap.get(user).get(m.current).id}"/>">${reportsMap.get(user).get(m.current).mobileWeeks * 100}</a>
                            </c:if>

                        </c:otherwise>
                    </c:choose>



                    <%--<form:input path="${reportsMap.get(user).get(m.current).mobile}"/>--%>

                        <%--<div class="display_table">--%>

                        <%--<div id="week">${reportsMap.get(user).get(m.current).mobileWeeks}</div>--%>

                        <%--<div id="select">--%>
                        <%--<select onchange="with (this) if (selectedIndex) location = options [selectedIndex].value">--%>
                        <%--<option></option>--%>
                        <%--<c:forEach items="${numberOfWeeks}" varStatus="i">--%>

                        <%--&lt;%&ndash;для сервера&ndash;%&gt;--%>
                        <%--<option value="/WebTransport/increase/${reportsMap.get(user).get(m.current).id}/${i.current}"/>${i.current}</option>--%>


                        <%--&lt;%&ndash;для локалки&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<option value="/increase/${reportsMap.get(user).get(m.current).id}/${i.current}"/><div class="color_blue">${i.current}</div></option>&ndash;%&gt;--%>

                        <%--</c:forEach>--%>
                        <%--</select>--%>
                        <%--</div>--%>

                        <%--</div>--%>

                </td>

                </c:otherwise>
                </c:choose>
                </c:forEach>
            <tr/>
        </c:forEach>

        <%--<tr>--%>
            <%--<td></td>--%>
            <%--<td><input id="submit_button" type="submit" value="<spring:message text="Сохранить"/>"/></td>--%>
        <%--</tr>--%>
    </table>
<%--</form:form>--%>




</div>
</body>
</html>


