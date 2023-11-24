<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section>
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
    <div id="popApps">

    </div>
    <div>
        <h2> Ordem de Servico </h2>
    </div>
</section>