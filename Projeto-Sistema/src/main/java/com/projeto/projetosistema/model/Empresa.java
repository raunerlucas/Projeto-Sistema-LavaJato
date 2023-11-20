package com.projeto.projetosistema.model;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Empresa {

    private Integer id;
    private String nomeFicticio;
    private String telefone;
    private String CNPJ;
    private String email;
    private Endereco endereco;
    private Set<OrdemServico> ordensServicos = new LinkedHashSet<OrdemServico>();

    public Empresa() {
    }

    public Empresa(Integer id, String nomeFicticio, String telefone, String CNPJ
            , String email, Endereco endereco, Set<OrdemServico> ordensServicos) {
        this.id = id;
        this.nomeFicticio = nomeFicticio;
        this.telefone = telefone;
        this.CNPJ = CNPJ;
        this.email = email;
        this.endereco = endereco;
        this.ordensServicos = ordensServicos;
    }

    public Empresa(String nomeFicticio, String telefone, String CNPJ, String email, Endereco endereco) {
        this.nomeFicticio = nomeFicticio;
        this.telefone = telefone;
        this.CNPJ = CNPJ;
        this.email = email;
        this.endereco = endereco;
    }

    public void addOrdemServico(OrdemServico os){
        ordensServicos.add(os);
    }
    public void removeOrdemServico(OrdemServico os){
        if (!ordensServicos.isEmpty()) {
            ordensServicos.remove(os);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empresa)) return false;
        Empresa empresa = (Empresa) o;
        return Objects.equals(getId(), empresa.getId()) && Objects.equals(getCNPJ(), empresa.getCNPJ());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCNPJ());
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "id=" + id +
                ", nomeFicticio='" + nomeFicticio + '\'' +
                ", telefone='" + telefone + '\'' +
                ", CNPJ='" + CNPJ + '\'' +
                ", email='" + email + '\'' +
                ", endereco=" + endereco +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeFicticio() {
        return nomeFicticio;
    }

    public void setNomeFicticio(String nomeFicticio) {
        this.nomeFicticio = nomeFicticio;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Set<OrdemServico> getOrdensServicos() {
        return ordensServicos;
    }

    public void setOrdensServicos(Set<OrdemServico> ordensServicos) {
        this.ordensServicos = ordensServicos;
    }
}
