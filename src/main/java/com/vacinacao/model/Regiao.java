package com.vacinacao.model;

public class Regiao {
    private int idRegiao;
    private String nome;
    private String cidade;
    private String estado;
    private double indiceDesenvolvimento;

    public Regiao() {}

    public Regiao(String nome, String cidade, String estado, double indiceDesenvolvimento) {
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.indiceDesenvolvimento = indiceDesenvolvimento;
    }

    public Regiao(int idRegiao, String nome, String cidade, String estado, double indiceDesenvolvimento) {
        this.idRegiao = idRegiao;
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.indiceDesenvolvimento = indiceDesenvolvimento;
    }

    public int getIdRegiao() { return idRegiao; }
    public void setIdRegiao(int idRegiao) { this.idRegiao = idRegiao; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public double getIndiceDesenvolvimento() { return indiceDesenvolvimento; }
    public void setIndiceDesenvolvimento(double indiceDesenvolvimento) { this.indiceDesenvolvimento = indiceDesenvolvimento; }

    @Override
    public String toString() {
        return String.format("[%d] %s - %s/%s (IDH: %.2f)", idRegiao, nome, cidade, estado, indiceDesenvolvimento);
    }
}
