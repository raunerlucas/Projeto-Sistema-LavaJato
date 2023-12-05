package com.projeto.projetosistema.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.projeto.projetosistema.model.Status.AGUARDANDO;

public class OrdemServico {
    private Integer id;
    private Integer numeroOS;
    private String dataEmissao;
    private String previsaoTermino;
    private boolean entregar;
    private Status status = AGUARDANDO;
    private String descricao;
    private Veiculo veiculo;
    private Funcionario funcionario;
    private Cliente cliente;
    private Empresa empresa;
    private List<Servico> servicosOrdem = new ArrayList<>();

    public OrdemServico() {
    }

    public OrdemServico(Integer id, Integer numeroOS, String dataEmissao, String previsaoTermino, boolean entregar,
                        Status status, String descricao, Veiculo veiculo, Funcionario funcionario, Cliente cliente,
                        Empresa empresa, List<Servico> servicosOrdem) {
        this.id = id;
        this.numeroOS = numeroOS;
        this.dataEmissao = dataEmissao;
        this.previsaoTermino = previsaoTermino;
        this.entregar = entregar;
        this.status = status;
        this.descricao = descricao;
        this.veiculo = veiculo;
        if (veiculo != null)
            veiculo.addOrdemSevico(this);
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.empresa = empresa;
        this.servicosOrdem = servicosOrdem;
    }

    public OrdemServico(Integer numeroOS, String dataEmissao, String previsaoTermino, boolean entregar,
                        String descricao,Veiculo veiculo, Funcionario funcionario, Cliente cliente, Empresa empresa, List<Servico> servicosOrdem) {
        this.numeroOS = numeroOS;
        this.dataEmissao = dataEmissao;
        this.previsaoTermino = previsaoTermino;
        this.entregar = entregar;
        this.descricao = descricao;
        this.veiculo = veiculo;
        if (veiculo != null)
            veiculo.addOrdemSevico(this);
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.empresa = empresa;
        this.servicosOrdem = servicosOrdem;

    }


    public void addServico(Servico s) {
        servicosOrdem.add(s);
    }

    public void removeOrdemServico(Servico s) {
        if (!servicosOrdem.isEmpty()) {
            servicosOrdem.remove(s);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrdemServico)) return false;
        OrdemServico that = (OrdemServico) o;
        return Objects.equals(id, that.id) && Objects.equals(numeroOS, that.numeroOS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numeroOS);
    }

    @Override
    public String toString() {
        return "OrdemServico{" +
                "id=" + id +
                ", numeroOS=" + numeroOS +
                ", dataEmissao=" + dataEmissao +
                ", previsaoTermino=" + previsaoTermino +
                ", entregar=" + entregar +
                ", status=" + status +
                ", descricao=" + descricao +
                ", veiculo=" + veiculo +
                ", funcionario=" + funcionario +
                ", cliente=" + cliente +
                ", empresa=" + empresa +
                ", servicosOrdem=" + servicosOrdem +
                '}';
    }

    public String jsonCreate() {
        StringBuilder txt = new StringBuilder("[");
        for (Servico s : servicosOrdem) {
            txt.append("{\"id_servico\": ").append(s.getId()).append("},");
        }
        if (txt.charAt(txt.length() - 1) == ',') {
            txt.deleteCharAt(txt.length() - 1);
        }
        txt.append("]");
        return txt.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroOS() {
        return numeroOS;
    }

    public void setNumeroOS(Integer numeroOS) {
        this.numeroOS = numeroOS;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getPrevisaoTermino() {
        return previsaoTermino;
    }

    public void setPrevisaoTermino(String previsaoTermino) {
        this.previsaoTermino = previsaoTermino;
    }

    public boolean isEntregar() {
        return entregar;
    }

    public void setEntregar(boolean entregar) {
        this.entregar = entregar;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
        veiculo.addOrdemSevico(this);
    }

    public Float getValorTotal() {
        Float valor = 0F;
        for (Servico sv: servicosOrdem) {
            valor += sv.getPreco();
        }
        return valor;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Cliente getClinte() {
        return cliente;
    }

    public void setClinte(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Servico> getServicosOrdem() {
        return servicosOrdem;
    }

    public void setServicosOrdem(List<Servico> servicosOrdem) {
        this.servicosOrdem = servicosOrdem;
    }
}

