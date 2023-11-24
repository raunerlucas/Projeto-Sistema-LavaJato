<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section>
    <div id="popApps">
    </div>
    <div class="ordens-servico">
        <h2> Ordem de Servico </h2>
    </div>
    <c:set var="uS" value="${sessionScope.userSessao}"/>
    <c:if test="${uS.isFuncionario()}">
        <div class="cliente">
            <h2> Clientes </h2>
            <ol>
                <c:forEach var="cli" items="${applicationScope.clientes}" >
                    <li>
                        <button>
                            <span>Nome: ${cli.getNome()}</span>
                            <span>cpf: ${cli.getCPF()}</span>
                        </button>
                    </li>
                </c:forEach>
            </ol>
        </div>
        <c:if test="${uS.isAdmin()}">
            <div class="funcionarios">
                <h2> Funcionarios </h2>
                <ol>
                    <c:forEach var="func" items="${applicationScope.funcionarios}" >
                        <li>
                            <button>
                                <span>Nome: ${func.getNome()}</span>
                                <span>cpf: ${func.getCPF()}</span>
                                <span>Admin: ${func.isAdmin() ? "Sim" : "Não"}</span>
                            </button>
                        </li>
                    </c:forEach>
                </ol>
            </div>
        </c:if>
    </c:if>
</section>