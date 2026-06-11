package com.vacinacao.model;

public class Doenca {
    public enum Gravidade { BAIXA, MEDIA, ALTA, CRITICA }

    private int idDoenca;
    private String nome;
    private String descricao;
    private Gravidade gravidade;

    public Doenca() {}

    public Doenca(String nome, String descricao, Gravidade gravidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.gravidade = gravidade;
    }

    public Doenca(int idDoenca, String nome, String descricao, Gravidade gravidade) {
        this.idDoenca = idDoenca;
        this.nome = nome;
        this.descricao = descricao;
        this.gravidade = gravidade;
    }

    public int getIdDoenca() { return idDoenca; }
    public void setIdDoenca(int idDoenca) { this.idDoenca = idDoenca; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Gravidade getGravidade() { return gravidade; }
    public void setGravidade(Gravidade gravidade) { this.gravidade = gravidade; }

    @Override
    public String toString() {
        return String.format("[%d] %s (Gravidade: %s)", idDoenca, nome, gravidade);
    }
}
