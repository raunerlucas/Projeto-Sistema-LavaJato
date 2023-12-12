<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="uS" value="${sessionScope.userSessao}"/>
<c:set var="empresa" value="${applicationScope.empresa}"/>
<c:import url="WEB-INF/header.jsp"/>
<c:choose>
    <c:when test="${uS != null && uS.isFuncionario() && uS.isAdmin()}">
        <c:forEach var="funcs" items="${applicationScope.funcionarios}" >
            <c:if test="${funcs.getId() == param.numF}">
                <c:set var="func" value="${funcs}"/>
            </c:if>
        </c:forEach>
        <article>
            <div class="buttonsVeiw">
                <a href="index.jsp"><button>Voltar</button></a>
                <a href="#elementoOculto"><button onclick="mostraredit()" class="btnEditar">Editar</button></a>
                <a href="deletarF?id=${func.getId()}"><button class="btnDeletar">Deletar</button></a>
            </div>
            <form action="editarF" class="viewForm" method="post">
                <fieldset>
                    <div>
                        <h2>Funcionario: </h2>
                        <h3>Nome: ${func.getNome()} ${func.getSobrenome()}</h3>
                        <h3>E-mail: ${func.getEmail()} -- Telefone ${func.getTelefone()}</h3>
                        <h3>CPF: ${func.getCPF()}</h3>
                        <h3>Admin: ${func.isAdmin()? "Sim" : "Não"}</h3>
                    </div>
                    <div>
                        <h2>Endereço: </h2>
                        <h3>CEP: ${func.getEndereco().getCEP()}</h3>
                        <h3>Número: ${func.getEndereco().getNumero()}</h3>
                        <h3>Logradouro: ${func.getEndereco().getLogradouro()}</h3>
                        <h3>Bairro: ${func.getEndereco().getBairro()}</h3>
                        <h3>Cidade: ${func.getEndereco().getCidade()} - ${func.getEndereco().getEstado()}</h3>
                        <h3>Complemento: ${func.getEndereco().getComplemento()}</h3>
                    </div>
                </fieldset>
                <fieldset id="elementoOculto" style="display: none">
                    <a><button type="button" onclick="mostraredit()">Cancelar</button></a>
                    <input type="hidden" readonly name="idc" value="${func.getId()}">
                    <input type="hidden" readonly name="idE" value="${func.getEndereco().getId()}">
                    <fieldset>
                        <label>
                            Nome
                            <input required type="text" name="nome" placeholder=" Nome" maxlength="25"
                                   value="${func.getNome()}">
                        </label>
                        <label>
                            Sobrenome
                            <input type="text" name="sobrenome" placeholder=" Sobrenome" maxlength="25"
                                   value="${func.getSobrenome()}">
                        </label>
                        <label>
                            CPF
                            <input required type="text" name="cpf" placeholder=" 000.000.000-00" oninput="formatarCPF(this)"
                                   value="${func.getCPF()}">
                        </label>
                        <label>
                            Telefone
                            <input required type="text" name="telefone" placeholder=" (##) #####-####" maxlength="15"
                                   onkeypress="formatarTelefone('(##) #####-####',this)"
                                   value="${func.getTelefone()}">
                        </label>
                    </fieldset>
                    <fieldset>
                        <label>
                            Login
                            <input type="text" name="login" placeholder=" Login" maxlength="25"
                                   value="${func.getLogin()}">
                        </label>
                        <label>
                            Senha
                            <input type="text" name="senha" placeholder=" Senha" maxlength="25"
                                   value="${func.getSenha()}">
                        </label>
                        <label>
                            Email
                            <input type="email" name="email" placeholder=" exemplo@exp.com" size="30" required
                                   value="${func.getEmail()}">
                        </label>
                        <label>
                            Administrador:
                            <label>
                                Sim
                                <input type="radio" name="admin" checked="${func.isAdmin()}" required value="sim">
                            </label>
                            <label>
                                Não
                                <input type="radio" name="admin" checked="${func.isAdmin()? false : true}" required value="nao">
                            </label>
                        </label>
                    </fieldset>
                    <fieldset>
                        <label>
                            Cep
                            <input required type="number" name="cep" placeholder=" 00000000"  maxlength="8"
                                   oninput="buscarCep(this)"
                                   value="${func.getEndereco().getCEP()}">
                        </label>
                        <label>
                            Numero
                            <input type="number" name="numero" placeholder=" Numero"
                                   value="${func.getEndereco().getNumero()}">
                        </label>
                        <label>
                            Complemento
                            <input type="text" name="complemento" placeholder=" Complemento"
                                   value="${func.getEndereco().getComplemento()}">
                        </label>
                        <label>
                            Logradouro
                            <input type="text" id="lograC" name="logradouro" placeholder=" Logradouro"
                                   value="${func.getEndereco().getLogradouro()}">

                        </label>
                        <label>
                            Bairro
                            <input type="text" id="bairroC" name="bairro" placeholder=" Bairro"
                                   value="${func.getEndereco().getBairro()}">
                        </label>
                        <label>
                            Cidade
                            <input type="text" id="cidadeC" name="cidade" placeholder=" Cidade"
                                   value="${func.getEndereco().getCidade()}">

                        </label>
                        <label>
                            Estado
                            <input  type="text" id="estadoC" name="estado" placeholder=" Estado"
                                    value="${func.getEndereco().getEstado()}">
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
