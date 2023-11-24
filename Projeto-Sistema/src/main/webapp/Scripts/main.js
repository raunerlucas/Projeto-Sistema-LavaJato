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
    document.getElementById("popApps").innerHTML = formC;
}

function cadatrarFuncio(){
    document.getElementById("popApps").innerHTML = formF;
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
                estado.value = response.uf;})
    }
}