<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="uS" value="${sessionScope.userSessao}"/>
<c:import url="WEB-INF/header.jsp"/>
<c:choose>
    <c:when test="${uS.isFuncionario()}">
        <h3>Criar nova Ordem de Servico</h3>
        <a href="index.jsp">Voltar</a>
        <form method="post" action="cadastrarOS">
            <fieldset>
                <label id="clientesLabel">
                    Cliente
                    <input required type="text" name="cliente" autocomplete="off" placeholder=" Nome  -- CPF" maxlength="25" list="clientes" oninput="haveCliente(this)">
                    <datalist id="clientes">
                        <c:forEach var="cli" items="${applicationScope.clientes}" >
                            <option value="${cli.getNome()} -- ${cli.getCPF()}">
                        </c:forEach>
                    </datalist>
                </label>
            </fieldset>
            <fieldset>
                <label id="servicosOrdemInput">
                    Seviços
                    <input type="text" list="servicos" required name="servicoInput"
                    autocomplete="off" placeholder="Descricao -- valor" maxlength="25" >
                    <button type="button" onclick="addServico(this)"> <samp>+</samp> </button>
                    <datalist id="servicos">
                    <c:forEach var="ser" items="${applicationScope.servicos}" >
                        <option value="${ser.getDescricao()} -- ${ser.getPreco()}">
                            </c:forEach>
                    </datalist>
                </label>
                <label>
                    Descrição Veiculo:
                    <textarea required name="descricao" cols="30" rows="10">
Modelo: ***;
Placa: ***;
Cor: ***;
OBS: ***;
                                    </textarea>
                </label>
            </fieldset>
            <fieldset>
                <label>
                    Previsão de entrega
                    <input required type="date" name="dataPrevisao">
                </label>
                <label>
                    Entregar:
                    <label>
                        Sim
                        <input type="radio" name="entregar" required value="sim">
                    </label>
                    <label>
                        Não
                        <input type="radio" name="entregar" required value="nao">
                    </label>
                </label>
                <label>
                    Valor Total
                    <input required type="number" name="valorTotal">
                </label>
            </fieldset>
            <input type="submit" value="CRIAR">
        </form>
    </c:when>
    <c:otherwise>
        <c:redirect url="index.jsp"/>
    </c:otherwise>
</c:choose>
<c:import url="WEB-INF/footer.jsp"/>
