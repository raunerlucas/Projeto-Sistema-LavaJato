onload = function () {
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.get('cds') === "T") {
        cadatrarCleinte();
    }
}

function mudarLogin(evento) {
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

function cadatrarCleinte() {
    document.getElementById("popApps").innerHTML = formC;
}

function cadatrarFuncio() {
    document.getElementById("popApps").innerHTML = formF;
}

function haveCliente(i) {
    if (i.value.length > 16) {
        const cpf = i.value.split(" -- ")[1]
        const options = document.querySelectorAll("#clientes option");
        let have = false;
        options.forEach(option => {
            let nums = option.value.split(" -- ")
            if (nums[1] === cpf) {
                have = true;
                return;
            }
        })
        if (!have) {
            if (document.getElementById("msgClien") == null) {
                let labClient = document.getElementById("clientesLabel")
                labClient.innerHTML += `<p id="msgClien">Cliente não encontrado cadastre ele primeiro => 
                                        <a href="index.jsp?cds=T">Cadastrar</a></p>`;
            }
        } else {
            if (document.getElementById("msgClien") != null) {
                const pai = document.getElementById("msgClien").parentNode
                pai.removeChild(document.getElementById("msgClien"));
            }
        }
    }
}
function removerServico(botao){
    let label = document.getElementById("servicosOrdemInput")
    let valorR = parseFloat(botao.previousElementSibling.value.split(" -- ")[1]);
    let inpValt = document.getElementsByName("valorTotal").item(0);
    inpValt.value = parseFloat(inpValt.value) - valorR;
    label.removeChild(botao.previousElementSibling);
    label.removeChild(botao)
}
function addServico(botao){
    const input = `<input type="text" list="servicos" required name="servicoInput"
       autocomplete="off" placeholder="Descricao -- valor" maxlength="25"
        value="${botao.previousElementSibling.value}">
       <button type="button" onclick="removerServico(this)"> <samp>-</samp> </button>`
    let label = document.getElementById("servicosOrdemInput")
    label.innerHTML += input;
}

function formatarCPF(i) {
    var v = i.value;
    i.setAttribute("maxlength", "14");
    if (isNaN(v[v.length - 1])) {
        i.value = v.substring(0, v.length - 1);
        return;
    }
    if (v.length == 3 || v.length == 7) i.value += ".";
    if (v.length == 11) i.value += "-";
}

function formatarTelefone(mascara, documento) {
    let i = documento.value.length;
    let saida = '#';
    let texto = mascara.substring(i);
    while (texto.substring(0, 1) != saida && texto.length) {
        documento.value += texto.substring(0, 1);
        i++;
        texto = mascara.substring(i);
    }
}

function somarValor(botao){
    let inpValt = document.getElementsByName("valorTotal").item(0);
    let valor = 0;
    for (const s of botao.children) {
        if (s.selected){
            valor += parseFloat(s.value.split(" -- ")[1])
        }
    }
    inpValt.value = valor
}

function editarUserSessao(user){
}
function editarServico(botao){
    let form = document.forms[0];
    form.action = "editarS";
    if (document.getElementById("cancEdit") == null){
        form.innerHTML += `<button type="button" id="cancEdit" onclick="restFormSer()">Cancelar</button>`
    }
    form[3].value = "Editar";
    form[2].value = `${botao.value}`;

    const dataS = botao.parentElement.parentElement.cells;
    form[0].value = dataS[0].textContent;
    form[0].placeholder = dataS[0].textContent;
    form[1].value = parseFloat(dataS[1].textContent);
    form[1].placeholder = parseFloat(dataS[1].textContent);
}
function restFormSer(){
    let form = document.forms[0];
    form.action = "cadastrarS";
    form[3].value = "Cadastrar";
    form[0].value = '';
    form[0].placeholder = " Descrição";
    form[1].value = '';
    form[1].placeholder = " Preço";
    form[2].value = '';

    form.removeChild(document.getElementById("cancEdit"));
}

async function buscarCep(i) {
    if (i.value.length === 8) {
        let logra = document.getElementById("lograC");
        let bairro = document.getElementById("bairroC");
        let cidade = document.getElementById("cidadeC");
        let estado = document.getElementById("estadoC");

        fetch("https://viacep.com.br/ws/" + i.value + "/json/")
            .then(response => response.json())
            .then(response => {
                logra.value = response.logradouro
                bairro.value = response.bairro;
                cidade.value = response.localidade;
                estado.value = response.uf;
            })
    }
}

