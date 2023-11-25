onload = function () {
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.get('cds') === "T") {
        cadatrarCleinte();
    }
    ;
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
    label.removeChild(botao.previousElementSibling)
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