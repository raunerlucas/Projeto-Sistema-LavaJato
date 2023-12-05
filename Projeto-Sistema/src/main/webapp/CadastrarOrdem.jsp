<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="uS" value="${sessionScope.userSessao}"/>
<c:import url="WEB-INF/header.jsp"/>
<c:choose>
    <c:when test="${uS.isFuncionario()}">
        <h3>Criar nova Ordem de Servico</h3>
        <a href="index.jsp"><button>Voltar</button></a>
        <form method="post" action="cadastrarOS">
            <fieldset>
                <label id="clientesLabel">
                    Cliente
                    <input required type="text" name="cliente" autocomplete="off"
                           placeholder=" Nome  -- CPF" maxlength="25" list="clientes" oninput="haveCliente(this)">
                    <datalist id="clientes">
                        <c:forEach var="cli" items="${applicationScope.clientes}" >
                            <option value="${cli.getNome()} -- ${cli.getCPF()}">
                        </c:forEach>
                    </datalist>
                </label>
            </fieldset>
            <fieldset>
                <label>
                    Veiculo tipo:
                    <select name="veiculo" required>
                        <option value="carro">Carro de Passeio</option>
                        <option value="suv">SUV / Crossover</option>
                        <option value="caminhao">Caminhão</option>
                        <option value="moto">Motocicleta</option>
                        <option value="van">Van</option>
                        <option value="frota">Veículo de Frota</option>
                    </select>
                </label>
                <label>
                    Placa
                    <input required name="placa" placeholder=" Placa" maxlength="25">
                </label>
                <label>
                    Modelo
                    <input required name="modelo" placeholder=" Modelo" maxlength="50">
                </label>
                <label>
                    cor
                    <input name="cor" placeholder=" Cor" maxlength="25">
                </label>
            </fieldset>
            <fieldset>
                <label id="servicosOrdemInput">
                    Seviços
                    <select name="servicoInput" size="5" multiple required onclick="somarValor(this)">
                        <c:forEach var="ser" items="${applicationScope.servicos}" >
                            <option value="${ser.getDescricao()} -- ${ser.getPreco()}">${ser.getDescricao()}</option>
                        </c:forEach>
                    </select>
                </label>
                <label>
                    Descrição Veiculo:
                    <textarea required name="descricao" cols="30" rows="8">
OBS: ***
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
                    <input type="number" name="valorTotal" readonly>
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
