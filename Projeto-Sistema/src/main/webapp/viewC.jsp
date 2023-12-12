<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="uS" value="${sessionScope.userSessao}"/>
<c:set var="empresa" value="${applicationScope.empresa}"/>
<c:import url="WEB-INF/header.jsp"/>
<c:choose>
    <c:when test="${uS != null}">
        <c:forEach var="cls" items="${applicationScope.clientes}" >
            <c:if test="${cls.getId() == param.numC}">
                <c:set var="cli" value="${cls}"/>
            </c:if>
        </c:forEach>
        <article>
            <div class="buttonsVeiw">
                <a href="index.jsp"><button>Voltar</button></a>
                <c:if test="${uS.isFuncionario()}">
                    <a href="#elementoOculto"><button class="btnEditar" onclick="mostraredit()">Editar</button></a>
                    <c:if test="${uS.isAdmin()}">
                        <a href="deletarC?id=${cli.getId()}"><button class="btnDeletar">Deletar</button></a>
                    </c:if>
                </c:if>
            </div>
            <form action="editarC" method="post" class="viewForm">
                <fieldset>
                    <div>
                        <h2>Cliente: </h2>
                        <h3>Nome: ${cli.getNome()} ${cli.getSobrenome()}</h3>
                        <h3>E-mail: ${cli.getEmail()} -- Telefone ${cli.getTelefone()}</h3>
                        <h3>CPF: ${cli.getCPF()}</h3>
                    </div>
                    <div>
                        <h2>Endereço: </h2>
                        <h3>CEP: ${cli.getEndereco().getCEP()}</h3>
                        <h3>Número: ${cli.getEndereco().getNumero()}</h3>
                        <h3>Logradouro: ${cli.getEndereco().getLogradouro()}</h3>
                        <h3>Bairro: ${cli.getEndereco().getBairro()}</h3>
                        <h3>Cidade: ${cli.getEndereco().getCidade()} - ${cli.getEndereco().getEstado()}</h3>
                        <h3>Complemento: ${cli.getEndereco().getComplemento()}</h3>
                    </div>
                </fieldset>
                <fieldset id="elementoOculto" style="display: none">
                    <a><button type="button" onclick="mostraredit()">Cancelar</button></a>
                    <input type="hidden" readonly name="idc" value="${cli.getId()}">
                    <input type="hidden" readonly name="idE" value="${cli.getEndereco().getId()}">
                    <fieldset>
                        <label>
                            Nome
                            <input required type="text" name="nome" placeholder=" Nome" maxlength="25"
                                   value="${cli.getNome()}">
                        </label>
                        <label>
                            Sobrenome
                            <input type="text" name="sobrenome" placeholder=" Sobrenome" maxlength="25"
                                   value="${cli.getSobrenome()}">
                        </label>
                        <label>
                            CPF
                            <input required type="text" name="cpf" placeholder=" 000.000.000-00" oninput="formatarCPF(this)"
                                   value="${cli.getCPF()}">
                        </label>
                        <label>
                            Telefone
                            <input required type="text" name="telefone" placeholder=" (##) #####-####" maxlength="15"
                                   onkeypress="formatarTelefone('(##) #####-####',this)"
                                   value="${cli.getTelefone()}">
                        </label>
                    </fieldset>
                    <fieldset>
                        <label>
                            Login
                            <input type="text" name="login" placeholder=" Login" maxlength="25"
                                   value="${cli.getLogin()}">
                        </label>
                        <label>
                            Senha
                            <input type="text" name="senha" placeholder=" Senha" maxlength="25"
                                   value="${cli.getSenha()}">
                        </label>
                        <label>
                            Email
                            <input type="email" name="email" placeholder=" exemplo@exp.com" size="30" required
                                   value="${cli.getEmail()}">
                        </label>
                    </fieldset>
                    <fieldset>
                        <label>
                            Cep
                            <input required type="number" name="cep" placeholder=" 00000000"  maxlength="8"
                                   oninput="buscarCep(this)"
                                   value="${cli.getEndereco().getCEP()}">
                        </label>
                        <label>
                            Numero
                            <input type="number" name="numero" placeholder=" Numero"
                                   value="${cli.getEndereco().getNumero()}">
                        </label>
                        <label>
                            Complemento
                            <input type="text" name="complemento" placeholder=" Complemento"
                                   value="${cli.getEndereco().getComplemento()}">
                        </label>
                        <label>
                            Logradouro
                            <input type="text" id="lograC" name="logradouro" placeholder=" Logradouro"
                                   value="${cli.getEndereco().getLogradouro()}">

                        </label>
                        <label>
                            Bairro
                            <input type="text" id="bairroC" name="bairro" placeholder=" Bairro"
                                   value="${cli.getEndereco().getBairro()}">
                        </label>
                        <label>
                            Cidade
                            <input type="text" id="cidadeC" name="cidade" placeholder=" Cidade"
                                   value="${cli.getEndereco().getCidade()}">

                        </label>
                        <label>
                            Estado
                            <input  type="text" id="estadoC" name="estado" placeholder=" Estado"
                                    value="${cli.getEndereco().getEstado()}">
                        </label>
                    </fieldset>
                </fieldset>
            </form>
        </article>
    </c:when>
    <c:otherwise>
        <c:redirect url="index.jsp"/>
    </c:otherwise>
</c:choose>
<c:import url="WEB-INF/footer.jsp"/>
