package com.projeto.projetosistema.model;

import java.util.Objects;

public class Servico {
    private Integer id;
    private String descricao;
    private Float preco;

    public Servico() {
    }

    public Servico(Integer id, String descricao, Float preco) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Servico(String descricao, Float preco) {
        this.descricao = descricao;
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Servico)) return false;
        Servico servico = (Servico) o;
        return Objects.equals(descricao, servico.descricao) && Objects.equals(preco, servico.preco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descricao, preco);
    }

    @Override
    public String toString() {
        return "Servico{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                '}';
    }

    public String jsonbServico(){
        return "\"id_servico\": "+id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }
}
