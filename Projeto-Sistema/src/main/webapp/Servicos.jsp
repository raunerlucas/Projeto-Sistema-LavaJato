<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="uS" value="${sessionScope.userSessao}"/>
<c:set var="uS" value="${sessionScope.userSessao}"/>
<c:import url="WEB-INF/header.jsp"/>
<c:choose>
    <c:when test="${uS != null && uS.isFuncionario() && uS.isAdmin()}">
        <article class="servicos-crud">
            <a href="index.jsp"><button>Voltar</button></a>
            <section>
                <div class="servicos-cadastro">
                    <form action="cadastrarS" method="post">
                        <h2>Cadastro De Servicos</h2>
                        <label>
                            Descrição
                            <input type="text" required name="descricao" placeholder=" Descriçao" maxlength="50">
                        </label>
                        <label>
                            Preço
                            <input type="number" required name="preco" placeholder=" Preço" maxlength="10" oninput="this.value = this.value.replace(/[^0-9]/g, '')">
                        </label>
                        <input type="hidden" readonly name="id" value="">
                        <input type="submit" value="Cadastrar">
                    </form>
                </div>
                <div class="servicos-rud">
                    <h2>Serviços Disponiveis</h2>
                    <table>
                        <thead>
                        <tr>
                            <th>Descrição</th>
                            <th>Preço</th>
                            <th>Deletar</th>
                            <th>Editar</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="ser" items="${applicationScope.servicos}" >
                            <tr>
                                <td>${ser.getDescricao()}</td>
                                <td>${ser.getPreco()}</td>
                                <td>
                                    <form action="deletarS" method="post">
                                        <button type="submit" name="id" value="${ser.getId()}">Deletar</button>
                                    </form>
                                </td>
                                <td>
                                    <button type="button" value="${ser.getId()}" onclick="editarServico(this)">Editar</button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </section>
        </article>
    </c:when>
    <c:otherwise>
        <c:redirect url="index.jsp"/>
    </c:otherwise>
</c:choose>
<c:import url="WEB-INF/footer.jsp"/>