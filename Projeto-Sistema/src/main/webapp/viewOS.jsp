<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="uS" value="${sessionScope.userSessao}"/>
<c:set var="empresa" value="${applicationScope.empresa}"/>
<c:import url="WEB-INF/header.jsp"/>
<c:choose>
    <c:when test="${uS != null}">
        <c:forEach var="ordem" items="${applicationScope.ordemSevico}" >
            <c:if test="${ordem.getNumeroOS() == param.numOS}">
                <c:set var="osView" value="${ordem}"/>
            </c:if>
        </c:forEach>
        <a href="index.jsp"><button>Voltar</button></a>
        <c:if test="${uS.isFuncionario()}">
            <a href="statusOS?id=${osView.getId()}"><button>Status</button></a>
            <a><button onclick="mostraredit()">Editar</button></a>
            <c:if test="${uS.isAdmin()}">
                <a href="deletarOS?id=${osView.getId()}" ><button>Deletar</button></a>
            </c:if>
        </c:if>
        <div>
            <h1>Ordem Servico ${param.numOS}N°</h1>
            <h2>Status [[ ${osView.getStatus()} ]] </h2>
            <h2>Data Emissão [[ ${osView.getDataEmissao()} ]] </h2>
            <h2>Previsão de Termino [[ ${osView.getPrevisaoTermino()} ]] </h2>
            <h2>Valor Total: ${osView.getValorTotal()}</h2>
        </div>
        <form action="editarOS" method="post">
        <fieldset>
                <div>
                    <h2>Cliente: </h2>
                    <h3>Nome: ${osView.getClinte().getNome()} ${osView.getClinte().getSobrenome()}</h3>
                    <h3>E-mail: ${osView.getClinte().getEmail()} -- Telefone ${osView.getClinte().getTelefone()}</h3>
                    <h3>CPF: ${osView.getClinte().getCPF()}</h3>
                </div>
                <div>
                    <h2>Endereço: </h2>
                    <h3>CEP: ${osView.getClinte().getEndereco().getCEP()}</h3>
                    <h3>Número: ${osView.getClinte().getEndereco().getNumero()}</h3>
                    <h3>Logradouro: ${osView.getClinte().getEndereco().getLogradouro()}</h3>
                    <h3>Bairro: ${osView.getClinte().getEndereco().getBairro()}</h3>
                    <h3>Cidade: ${osView.getClinte().getEndereco().getCidade()} - ${osView.getClinte().getEndereco().getEstado()}</h3>
                    <h3>Complemento: ${osView.getClinte().getEndereco().getComplemento()}</h3>
                </div>
            </fieldset>
            <fieldset>
                <div>
                    <h2>Funcionario: </h2>
                    <h3>Nome: ${osView.getFuncionario().getNome()} ${osView.getFuncionario().getSobrenome()}</h3>
                    <h3>E-mail: ${osView.getFuncionario().getEmail()} -- Telefone ${osView.getFuncionario().getTelefone()}</h3>
                </div>
                <div>
                    <h2>Empresa: </h2>
                    <h2>Nome: ${empresa.getNomeFicticio()}</h2>
                    <h3>E-mail: ${empresa.getEmail()} -- Telefone ${empresa.getTelefone()}</h3>
                    <h3>CNPJ: ${empresa.getCNPJ()}</h3>
                </div>
                <div>
                    <h2>Endereço - Empresa: </h2>
                    <h3>CEP: ${empresa.getEndereco().getCEP()}</h3>
                    <h3>Número: ${empresa.getEndereco().getNumero()}</h3>
                    <h3>Logradouro: ${empresa.getEndereco().getLogradouro()}</h3>
                    <h3>Bairro: ${empresa.getEndereco().getBairro()}</h3>
                    <h3>Cidade: ${empresa.getEndereco().getCidade()} - ${empresa.getEndereco().getEstado()}</h3>
                    <h3>Complemento: ${empresa.getEndereco().getComplemento()}</h3>
                </div>
            </fieldset>
            <fieldset>
                <div>
                    <h2>Detalhes: </h2>
                    <h3>Entregar: ${osView.isEntregar()? "Sim" : "Não"}</h3>
                    <h3>Previsão de Termino: ${osView.getPrevisaoTermino()}</h3>
                    <h3>Descricão: ${osView.getDescricao()}</h3>
                    <div>
                        <h3>Veiculo:</h3>
                        <h4> Tipo: ${osView.getVeiculo().getTipo()}</h4>
                        <h4> Placa: ${osView.getVeiculo().getPlaca()}</h4>
                        <h4> Modelo: ${osView.getVeiculo().getModelo()}</h4>
                        <h4> Cor: ${osView.getVeiculo().getCor()}</h4>
                    </div>
                    <h3>Servicos:
                        <ul>
                            <c:if test="${osView.getServicosOrdem() == [null]}">
                                <li>NENHUM SERVICO POR AQUI!</li>
                            </c:if>
                            <c:forEach var="sev" items="${osView.getServicosOrdem()}">
                            <li>${sev.getDescricao()} -- R$${sev.getPreco()}</li>
                        </c:forEach>
                        </ul>
                    </h3>
                    <h2>Valor Total: ${osView.getValorTotal()}</h2>
                </div>
            </fieldset>
            <fieldset id="elementoOculto" style="display: none">
                <a><button type="button" onclick="mostraredit()">Cancelar</button></a>
                <fieldset>
                    <label id="servicosOrdemInput">
                        Seviços
                        <select name="servicoInput" size="5" multiple required onclick="somarValor(this)">
                            <c:forEach var="ser" items="${applicationScope.servicos}" >
                                <option value="${ser.getDescricao()} -- ${ser.getPreco()}">${ser.getDescricao()}</option>
                            </c:forEach>
                        </select>
                    </label>
                </fieldset>
                <input type="hidden" readonly name="id" value="${osView.getId()}">
                <input type="hidden" readonly name="idV" value="${osView.getVeiculo().getId()}">
            </fieldset>
        </form>
    </c:when>
    <c:otherwise>
        <c:redirect url="index.jsp"/>
    </c:otherwise>
</c:choose>
<c:import url="WEB-INF/footer.jsp"/>
