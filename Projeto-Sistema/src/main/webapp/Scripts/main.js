onload = function () {

}

function mudarLogin(evento){
    if (document.forms[0][0].getAttribute("value") === "f") {
        document.getElementsByTagName("h3")[0].innerHTML = "Cliente"
        document.forms[0][0].setAttribute("value", "c")
        evento.target.innerHTML = "Funcionario";
    } else {
        document.getElementsByTagName("h3")[0].innerHTML = "Funcionario"
        document.forms[0][0].setAttribute("value", "f");
        evento.target.innerHTML = "Cliente";
    }
}

function cadatrarCleinte(){
    let formC = `<h3>Cadastro de Cliente</h3>
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
                                    <input type="text" name="logradouro" placeholder=" Logradouro" >
                                </label>
                                 <label>
                                    Bairro
                                    <input type="text" name="bairro" placeholder=" Bairro" >
                                </label>
                                 <label>
                                    Cidade
                                    <input type="text" name="cidade" placeholder=" Cidade" >
                                </label>
                                 <label>
                                    Estado
                                </label>
                                    <input  type="text" name="estado" placeholder=" Estado" >
                            </fieldset>
                            <input type="submit" value="Cadastrar">
                        </form>
    `
    document.getElementById("popApps").innerHTML = formC;
}

function formatarCPF(i){
    var v = i.value;
    i.setAttribute("maxlength", "14");
    if(isNaN(v[v.length-1])){
        i.value = v.substring(0, v.length-1);
        return;
    }
    if (v.length == 3 || v.length == 7) i.value += ".";
    if (v.length == 11) i.value += "-";
}
function formatarTelefone(mascara, documento) {
    let i = documento.value.length;
    let saida = '#';
    let texto = mascara.substring(i);
    while (texto.substring(0, 1) != saida && texto.length ) {
        documento.value += texto.substring(0, 1);
        i++;
        texto = mascara.substring(i);
    }
}
async function buscarCep(i){
    if (i.value.length === 8) {
        let logra = document.forms[0][13];
        let bairro = document.forms[0][14];
        let cidade = document.forms[0][15];
        let estado = document.forms[0][16];

        fetch("https://viacep.com.br/ws/" + i.value + "/json/")
            .then(response => response.json())
            .then(response => {
                logra.value = response.logradouro
                bairro.value = response.bairro;
                cidade.value = response.localidade;
                estado.value = response.uf;})
    }
}