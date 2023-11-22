<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: rauner.lucas
  Date: 20/11/2023
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" href="images/logo.ico">
    <link rel="stylesheet" type="text/css" href="Styles/styles.css">
    <script type="text/javascript" defer src="Scripts/main.js"></script>

    <title>Lava Bem</title>

    <%--    Css normalize--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css"
          integrity="sha512-oHDEc8Xed4hiW6CxD7qjbnI+B07vDdX7hEPTvn9pSZO1bcRqHp8mj9pyr+8RVC2GmtEfI2Bi9Ke9Ass0as+zpg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>

</head>
<body>
    <header>
        <img src="images/logo.ico">
        <h1>Lava Bem</h1>
        <nav>
            <c:if test="${sessionScope.userSessao != null}">
                <ul>
                    <li><a href="logout">SAIR</a></li>
                    <li><button type="button" onclick="editarUserSessao()">EDITAR</button></li>
                </ul>
            </c:if>
        </nav>
    </header>