<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="uS" value="${sessionScope.userSessao}"/>
<section class="mainFirst">
    <div class="userS">
        <c:set var="uS" value="${sessionScope.userSessao}"/>
        <c:choose>
            <c:when test="${uS != null}">
                <h4> Bem vindo ${uS.getNome()}</h4>
                <c:choose>
                    <c:when test="${uS.isFuncionario()}">
                        <h4> Tipo - Funcionario </h4>
                    </c:when>
                    <c:otherwise>
                        <h4> Tipo - Cliente </h4>
                    </c:otherwise>
                </c:choose>
            </c:when>
        </c:choose>
    </div>
    <div class="busca-div">
        <form action="buscar" >
            <input name="buscar" type="text" placeholder=" Buscar Ordem De Serviço" maxlength="25"/>
            <input type="submit"value="Buscar"/>
        </form>
        <div id="buscas">
            <ul>
                <c:forEach var="buss" items="${applicationScope.buscados}">
                    <li>
                        <a href="${buss.getLink()}">
                            <button>
                                <span>${buss.getResumo()}</span><br>
                            </button>
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <div id="popApps"></div>
</section>
<section class="mainSecond">
    <div class="ordens-servico">
        <h2> Ordem de Servico </h2>
        <div>
            <c:if test="${uS.isFuncionario()}">
                <a href="CadastrarOrdem.jsp"><button>NOVA</button></a>
                <c:if test="${uS.isAdmin()}">
                    <a href="Servicos.jsp"><button>SERVIÇOS</button></a>
                </c:if>
            </c:if>
        </div>
        <ul>
            <c:forEach var="ordem" items="${applicationScope.ordemSevico}" >
                <li>
                    <a href="viewOS.jsp?numOS=${ordem.getNumeroOS()}">
                        <button>
                            <span>Numero: ${ordem.getNumeroOS()}</span><br>
                            <span>Status: ${ordem.getStatus()}</span>
                        </button>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
    <c:if test="${uS.isFuncionario()}">
        <div class="cliente">
            <h2> Clientes </h2>
            <button type="button" onclick="cadatrarCleinte()">CADASTRAR CLIENTE</button>
            <ol>
                <c:forEach var="cli" items="${applicationScope.clientes}" >
                    <li>
                        <a href="viewC.jsp?numC=${cli.getId()}">
                            <button>
                                <span>Nome: ${cli.getNome()}</span><br>
                                <span>CPF: ${cli.getCPF()}</span>
                            </button>
                        </a>
                    </li>
                </c:forEach>
            </ol>
        </div>
        <c:if test="${uS.isAdmin()}">
            <div class="funcionarios">
                <h2> Funcionarios </h2>
                <button type="button" onclick="cadatrarFuncio()">CADASTRAR FUNCIONARIO</button>
                <ol>
                    <c:forEach var="func" items="${applicationScope.funcionarios}" >
                        <li>
                            <a href="viewF.jsp?numF=${func.getId()}">
                                <button>
                                    <span>Nome: ${func.getNome()}</span><br>
                                    <span>CPF: ${func.getCPF()}</span><br>
                                    <span>Admin: ${func.isAdmin() ? "Sim" : "Não"}</span>
                                </button>
                            </a>
                        </li>
                    </c:forEach>
                </ol>
            </div>
        </c:if>
    </c:if>
</section>
