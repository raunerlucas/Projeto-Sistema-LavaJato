package com.projeto.projetosistema.model;

import java.util.Objects;

public class Buscado {
    public String link;
    public String resumo;

    public Buscado(String link, String resumo) {
        this.link = link;
        this.resumo = resumo;
    }

    @Override
    public String toString() {
        return "Buscado{" +
                "link='" + link + '\'' +
                ", resumo='" + resumo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Buscado)) return false;
        Buscado buscado = (Buscado) o;
        return Objects.equals(getLink(), buscado.getLink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLink());
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }
}
