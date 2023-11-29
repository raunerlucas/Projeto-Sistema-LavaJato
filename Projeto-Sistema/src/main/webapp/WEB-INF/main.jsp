<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="popApps">
</div>
<section>
    <c:set var="uS" value="${sessionScope.userSessao}"/>
    <div class="ordens-servico">
        <h2> Ordem de Servico </h2>
        <c:if test="${uS.isFuncionario()}">
            <a href="CadastrarOrdem.jsp"><button>NOVA</button></a>
            <c:if test="${uS.isAdmin()}">
                <a href="Servicos.jsp"><button>SERVIÇOS</button></a>
            </c:if>
        </c:if>
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
    </div>
    <c:if test="${uS.isFuncionario()}">
        <div class="cliente">
            <h2> Clientes </h2>
            <button type="button" onclick="cadatrarCleinte()">CADASTRAR CLIENTE</button>
            <ol>
                <c:forEach var="cli" items="${applicationScope.clientes}" >
                    <li>
                        <button>
                            <span>Nome: ${cli.getNome()}</span><br>
                            <span>CPF: ${cli.getCPF()}</span>
                        </button>
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
                            <button>
                                <span>Nome: ${func.getNome()}</span><br>
                                <span>CPF: ${func.getCPF()}</span><br>
                                <span>Admin: ${func.isAdmin() ? "Sim" : "Não"}</span>
                            </button>
                        </li>
                    </c:forEach>
                </ol>
            </div>
        </c:if>
    </c:if>
</section>