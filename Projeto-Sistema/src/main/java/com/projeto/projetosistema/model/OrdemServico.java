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
    private Float valorTotal;
    private Funcionario funcionario;
    private Clinte clinte;
    private Empresa empresa;
    private List<Servico> servicosOrdem = new ArrayList<>();

    public OrdemServico() {
    }

    public OrdemServico(Integer id, Integer numeroOS, Date dataEmissao, Date previsaoTermino, boolean entregar,
                        Status status, Float valorTotal, Funcionario funcionario, Clinte clinte,
                        Empresa empresa, List<Servico> servicosOrdem) {
        this.id = id;
        this.numeroOS = numeroOS;
        this.dataEmissao = dataEmissao;
        this.previsaoTermino = previsaoTermino;
        this.entregar = entregar;
        this.status = status;
        this.valorTotal = valorTotal;
        this.funcionario = funcionario;
        this.clinte = clinte;
        this.empresa = empresa;
        this.servicosOrdem = servicosOrdem;
    }

    public OrdemServico(Integer numeroOS, Date dataEmissao, Date previsaoTermino, boolean entregar, Status status,
                        Float valorTotal, Funcionario funcionario, Clinte clinte, Empresa empresa) {
        this.numeroOS = numeroOS;
        this.dataEmissao = dataEmissao;
        this.previsaoTermino = previsaoTermino;
        this.entregar = entregar;
        this.status = status;
        this.valorTotal = valorTotal;
        this.funcionario = funcionario;
        this.clinte = clinte;
        this.empresa = empresa;
    }


    public void addServico(Servico s){
        servicosOrdem.add(s);
    }
    public void removeOrdemServico(Servico s){
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
                ", valorTotal=" + valorTotal +
                ", funcionario=" + funcionario +
                ", clinte=" + clinte +
                ", empresa=" + empresa +
                ", servicosOrdem=" + servicosOrdem +
                '}';
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

    public Clinte getClinte() {
        return clinte;
    }

    public void setClinte(Clinte clinte) {
        this.clinte = clinte;
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

