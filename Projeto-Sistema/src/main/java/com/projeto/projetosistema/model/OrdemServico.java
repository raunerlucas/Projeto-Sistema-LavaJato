package com.projeto.projetosistema.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.projeto.projetosistema.model.Status.AGUARDANDO;

public class OrdemServico {
    private Integer id;
    private Integer numeroOS;
    private Date dataEmissao;
    private Date previsaoTermino;
    private boolean entregar;
    private Status status = AGUARDANDO;
    private String descricao;
    private Float valorTotal;
    private Funcionario funcionario;
    private Cliente cliente;
    private Empresa empresa;
    private List<Servico> servicosOrdem = new ArrayList<>();

    public OrdemServico() {
    }

    public OrdemServico(Integer id, Integer numeroOS, Date dataEmissao, Date previsaoTermino, boolean entregar,
                        Status status, String descricao, Float valorTotal, Funcionario funcionario, Cliente cliente,
                        Empresa empresa, List<Servico> servicosOrdem) {
        this.id = id;
        this.numeroOS = numeroOS;
        this.dataEmissao = dataEmissao;
        this.previsaoTermino = previsaoTermino;
        this.entregar = entregar;
        this.status = status;
        this.descricao = descricao;
        this.valorTotal = valorTotal;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.empresa = empresa;
        this.servicosOrdem = servicosOrdem;
    }

    public OrdemServico(Integer numeroOS, Date dataEmissao, Date previsaoTermino, boolean entregar, Status status,
                        String descricao, Float valorTotal, Funcionario funcionario, Cliente cliente, Empresa empresa) {
        this.numeroOS = numeroOS;
        this.dataEmissao = dataEmissao;
        this.previsaoTermino = previsaoTermino;
        this.entregar = entregar;
        this.status = status;
        this.descricao = descricao;
        this.valorTotal = valorTotal;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.empresa = empresa;
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
                ", valorTotal=" + valorTotal +
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

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Date getPrevisaoTermino() {
        return previsaoTermino;
    }

    public void setPrevisaoTermino(Date previsaoTermino) {
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

    public Float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
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

