package com.vacinacao.model;

public class Escolaridade {
    private int idEscolaridade;
    private String descricao;

    public Escolaridade() {}

    public Escolaridade(String descricao) {
        this.descricao = descricao;
    }

    public Escolaridade(int idEscolaridade, String descricao) {
        this.idEscolaridade = idEscolaridade;
        this.descricao = descricao;
    }

    public int getIdEscolaridade() { return idEscolaridade; }
    public void setIdEscolaridade(int idEscolaridade) { this.idEscolaridade = idEscolaridade; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    @Override
    public String toString() {
        return String.format("[%d] %s", idEscolaridade, descricao);
    }
}
