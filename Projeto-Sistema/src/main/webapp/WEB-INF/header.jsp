<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% request.setCharacterEncoding("UTF-8"); %>

<%--
  Created by IntelliJ IDEA.
  User: rauner.lucas
  Date: 20/11/2023
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" href="images/logo.png">
    <link rel="stylesheet" type="text/css" href="Styles/styles.css">
    <script type="text/javascript" defer src="Scripts/main.js"></script>
    <script type="text/javascript" defer src="Scripts/draw.js"></script>

    <title>Lava Bem</title>

    <%--    Css normalize--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css"
          integrity="sha512-oHDEc8Xed4hiW6CxD7qjbnI+B07vDdX7hEPTvn9pSZO1bcRqHp8mj9pyr+8RVC2GmtEfI2Bi9Ke9Ass0as+zpg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>

</head>
<body>
    <header class="header">
        <img src="images/logo.png">
        <h1>Lava Bem</h1>
        <nav>
            <c:if test="${sessionScope.userSessao != null}">
                <ul>
                    <li><a href="logout"><button>SAIR</button></a></li>
                </ul>
            </c:if>
        </nav>
        <c:if test="${param.msg != null}">
            <div id="msg" >
                <p>${param.msg}</p>
            </div>
        </c:if>
    </header>