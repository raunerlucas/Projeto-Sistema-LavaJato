const  formC = `
                        <button type="button" onclick="clearformC()">Voltar</button>
                        <h3>Cadastro de Cliente</h3>
                        <form method="post" action="cadastrarCliente">
                            <fieldset>
                                <label>
                                    Nome
                                    <input required type="text" name="nome" placeholder=" Nome" maxlength="25">
                                </label>
                                <label>
                                    Sobrenome
                                    <input type="text" name="sobrenome" placeholder=" Sobrenome" maxlength="25">
                                </label>
                                <label>
                                    CPF
                                    <input required type="text" name="cpf" placeholder=" 000.000.000-00" oninput="formatarCPF(this)" >
                                </label>
                                <label>
                                    Telefone
                                    <input required type="text" name="telefone" placeholder=" (##) #####-####" maxlength="15" onkeypress="formatarTelefone('(##) #####-####',this)">
                                </label>
                            </fieldset>
                            <fieldset>
                                <label>
                                    Login
                                    <input type="text" name="login" placeholder=" Login" maxlength="25">
                                </label>
                                <label>
                                    Senha
                                    <input type="text" name="senha" placeholder=" Senha" maxlength="25">
                                </label>
                                <label>
                                    Email
                                    <input type="email" name="email" placeholder=" exemplo@exp.com" size="30" required >
                                </label>
                            </fieldset>
                            <fieldset>
                                <label>
                                    Cep
                                    <input required type="number" name="cep" placeholder=" 00000000"  maxlength="8" oninput="buscarCep(this)">
                                </label>
                                <label>
                                    Numero
                                    <input type="number" name="numero" placeholder=" Numero" >
                                </label>
                                <label>
                                    Complemento
                                    <input type="text" name="complemento" placeholder=" Complemento" >
                                </label>
                                <label>
                                    Logradouro
                                    <input type="text" id="lograC" name="logradouro" placeholder=" Logradouro" >
                                </label>
                                 <label>
                                    Bairro
                                    <input type="text" id="bairroC" name="bairro" placeholder=" Bairro" >
                                </label>
                                 <label>
                                    Cidade
                                    <input type="text" id="cidadeC" name="cidade" placeholder=" Cidade" >
                                </label>
                                 <label>
                                    Estado
                                    <input  type="text" id="estadoC" name="estado" placeholder=" Estado" >
                                </label>
                            </fieldset>
                            <input type="submit" value="Cadastrar">
                        </form>
    `;

const formF = `
                    <button type="button" onclick="clearformC()">Voltar</button>
                    <h3>Cadastro de Funcionario</h3>
                        <form method="post" action="cadastrarFuncionario">
                            <fieldset>
                                <label>
                                    Nome
                                    <input required type="text" name="nome" placeholder=" Nome" maxlength="25">
                                </label>
                                <label>
                                    Sobrenome
                                    <input type="text" name="sobrenome" placeholder=" Sobrenome" maxlength="25">
                                </label>
                                <label>
                                    CPF
                                    <input required type="text" name="cpf" placeholder=" 000.000.000-00" oninput="formatarCPF(this)" >
                                </label>
                                <label>
                                    Telefone
                                    <input required type="text" name="telefone" placeholder=" (##) #####-####" maxlength="15" onkeypress="formatarTelefone('(##) #####-####',this)">
                                </label>
                            </fieldset>
                            <fieldset>
                                <label>
                                    Login
                                    <input type="text" name="login" placeholder=" Login" maxlength="25">
                                </label>
                                <label>
                                    Senha
                                    <input type="text" name="senha" placeholder=" Senha" maxlength="25">
                                </label>
                                <label>
                                    Email
                                    <input type="email" name="email" placeholder=" exemplo@exp.com" size="30" required >
                                </label>
                                <label>
                                    Administrador: 
                                    <label>
                                        Sim
                                        <input type="radio" name="admin" required value="sim"> 
                                    </label>
                                    <label>
                                        Não
                                        <input type="radio" name="admin" required value="nao"> 
                                    </label>                                    
                                </label>
                            </fieldset>
                            <fieldset>
                                <label>
                                    Cep
                                    <input required type="number" name="cep" placeholder=" 00000000"  maxlength="8" oninput="buscarCep(this)">
                                </label>
                                <label>
                                    Numero
                                    <input type="number" name="numero" placeholder=" Numero" >
                                </label>
                                <label>
                                    Complemento
                                    <input type="text" name="complemento" placeholder=" Complemento" >
                                </label>
                                <label>
                                    Logradouro
                                    <input type="text" id="lograC" name="logradouro" placeholder=" Logradouro" >
                                </label>
                                 <label>
                                    Bairro
                                    <input type="text" id="bairroC" name="bairro" placeholder=" Bairro" >
                                </label>
                                 <label>
                                    Cidade
                                    <input type="text" id="cidadeC" name="cidade" placeholder=" Cidade" >
                                </label>
                                 <label>
                                    Estado
                                    <input  type="text" id="estadoC" name="estado" placeholder=" Estado" >
                                </label>
                            </fieldset>
                            <input type="submit" value="Cadastrar">
                        </form>
    `;

function formOSEdit(data) {
    const dias = data[2].textContent.split(": ")[1].split("-")
    const dataCerta = dias[2]+"-"+dias[1]+"-"+dias[0];
    return`
            <label>
                    Previsão de entrega
                    <input required type="date"name="dataPrevisao" value="${dataCerta}">
                </label>
            <label>
                Entregar:
                <label>
                    Sim
                    <input type="radio" name="entregar" required value="sim" 
                    checked="${data[1].textContent === "Entregar: Sim"}">
                </label>
                <label>
                    Não
                    <input type="radio" name="entregar" required value="nao"
                    checked="${data[1].textContent === "Entregar: Não"}">
                </label>
            </label>
            <label>
                Descrição Veiculo:
                <textarea required name="descricao" cols="30" rows="8">${data[3].textContent.split("Descricão: ")[1]}</textarea>
            </label>
            <label>
                Veiculo
                <select name="veiculo" required >
                    <option value="carro">Carro de Passeio</option>
                    <option value="moto">Motocicleta</option>
                    <option value="suv">SUV / Crossover</option>
                    <option value="caminhao">Caminhão</option>
                    <option value="van">Van</option>
                    <option value="frota">Veículo de Frota</option>
                </select>
            </label>
            <label>
                Valor Total
                <input type="number" name="valorTotal" required readonly>
            </label>
            <input type="submit" value="Editar">
    `
}