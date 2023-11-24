<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <script type="text/javascript" defer src="Scripts/draw.js"></script>

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
        <c:if test="${param.msg != null}">
            <div id="msg-div">
                <p>${param.msg}</p>
            </div>
        </c:if>
    </header>
    <div class="cadastros">
        <c:set var="uS" value="${sessionScope.userSessao}"/>
        <c:choose>
            <c:when test="${uS != null}">
                <h4> Bem vindo ${uS.getNome()}</h4>
                <c:choose>
                    <c:when test="${uS.isFuncionario()}">
                        <h4> Tipo - Funcionario </h4>
                        <button type="button" onclick="cadatrarCleinte()">CADASTRAR CLIENTE</button>
                        <c:if test="${uS.isAdmin()}">
                            <button type="button" onclick="cadatrarFuncio()">CADASTRAR FUNCIONARIO</button>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <h4> Tipo - Cliente </h4>
                    </c:otherwise>
                </c:choose>
            </c:when>
        </c:choose>
    </div>