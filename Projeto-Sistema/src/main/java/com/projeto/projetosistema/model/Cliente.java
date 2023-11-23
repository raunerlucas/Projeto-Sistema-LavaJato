package com.projeto.projetosistema.model;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Cliente {
    private Integer id;
    private String nome;
    private String sobrenome;
    private String CPF;
    private String telefone;
    private String login;
    private String senha;
    private String email;
    private boolean funcionario = false;

    private Endereco endereco;
    private Set<OrdemServico> ordensServicos = new LinkedHashSet<OrdemServico>();

    public Cliente() {
    }

    public Cliente(Integer id, String nome, String login, String senha) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public Cliente(Integer id, String nome, String sobrenome, String CPF, String telefone,
                   String login, String senha, String email, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.CPF = CPF;
        this.telefone = telefone;
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.endereco = endereco;
    }

    public Cliente(String nome, String sobrenome, String CPF, String telefone,
                   String login, String senha, String email, Endereco endereco) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.CPF = CPF;
        this.telefone = telefone;
        this.login = login;
        this.senha = senha;
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
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) && Objects.equals(CPF, cliente.CPF);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, CPF);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", CPF='" + CPF + '\'' +
                ", telefone='" + telefone + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", email='" + email + '\'' +
                ", endereco=" + endereco +
                '}';
    }

    public boolean isFuncionario() {
        return funcionario;
    }

    public void setFuncionario(boolean funcionario) {
        this.funcionario = funcionario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
