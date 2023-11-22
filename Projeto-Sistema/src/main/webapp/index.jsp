<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="WEB-INF/header.jsp"/>
<article>
    <c:choose>
        <c:when test="${sessionScope.userSessao == null}">
            <c:import url="WEB-INF/login.jsp"/>
        </c:when>
        <c:otherwise>
            <c:import url="WEB-INF/main.jsp"/>
        </c:otherwise>
    </c:choose>
</article>
<c:import url="WEB-INF/footer.jsp"/>