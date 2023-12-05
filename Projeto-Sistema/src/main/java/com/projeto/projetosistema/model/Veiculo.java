package com.projeto.projetosistema.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Veiculo {
    private Integer id;
    private String placa;
    private String modelo;
    private String tipo;
    private String cor;
    private List<OrdemServico> ordemServicos= new ArrayList<>();

    public Veiculo() {
    }

    public Veiculo(String placa, String modelo, String tipo, String cor) {
        this.placa = placa;
        this.modelo = modelo;
        this.tipo = tipo;
        this.cor = cor;
    }

    public Veiculo(Integer id, String placa, String modelo, String tipo, String cor) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.tipo = tipo;
        this.cor = cor;
    }

    public void addOrdemSevico(OrdemServico os){
        ordemServicos.add(os);
    }
    public void removeOrdemSevico(OrdemServico os){
        if (!ordemServicos.isEmpty())
            ordemServicos.remove(os);
    }
    @Override
    public String toString() {
        return "Veiculo{" +
                "id=" + id +
                ", placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", cor='" + cor + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Veiculo)) return false;
        Veiculo veiculo = (Veiculo) o;
        return Objects.equals(placa, veiculo.placa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placa);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<OrdemServico> getOrdemServicos() {
        return ordemServicos;
    }

    public void setOrdemServicos(List<OrdemServico> ordemServicos) {
        this.ordemServicos = ordemServicos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
