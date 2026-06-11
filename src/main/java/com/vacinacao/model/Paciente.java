package com.vacinacao.model;

import java.time.LocalDate;
import java.time.Period;

public class Paciente {
    private int idPaciente;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;
    private String endereco;
    private int idRegiao;
    private int idEscolaridade;

    // Campos auxiliares para exibição
    private String nomeRegiao;
    private String descricaoEscolaridade;

    public Paciente() {}

    public Paciente(String nome, String cpf, LocalDate dataNascimento, String telefone,
                    String endereco, int idRegiao, int idEscolaridade) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.endereco = endereco;
        this.idRegiao = idRegiao;
        this.idEscolaridade = idEscolaridade;
    }

    public Paciente(int idPaciente, String nome, String cpf, LocalDate dataNascimento,
                    String telefone, String endereco, int idRegiao, int idEscolaridade) {
        this.idPaciente = idPaciente;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.endereco = endereco;
        this.idRegiao = idRegiao;
        this.idEscolaridade = idEscolaridade;
    }

    public int getIdade() {
        if (dataNascimento == null) return 0;
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    public int getIdPaciente() { return idPaciente; }
    public void setIdPaciente(int idPaciente) { this.idPaciente = idPaciente; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public int getIdRegiao() { return idRegiao; }
    public void setIdRegiao(int idRegiao) { this.idRegiao = idRegiao; }
    public int getIdEscolaridade() { return idEscolaridade; }
    public void setIdEscolaridade(int idEscolaridade) { this.idEscolaridade = idEscolaridade; }
    public String getNomeRegiao() { return nomeRegiao; }
    public void setNomeRegiao(String nomeRegiao) { this.nomeRegiao = nomeRegiao; }
    public String getDescricaoEscolaridade() { return descricaoEscolaridade; }
    public void setDescricaoEscolaridade(String descricaoEscolaridade) { this.descricaoEscolaridade = descricaoEscolaridade; }

    @Override
    public String toString() {
        return String.format("[%d] %s | CPF: %s | Idade: %d | Região: %s | Escolaridade: %s",
                idPaciente, nome, cpf, getIdade(),
                nomeRegiao != null ? nomeRegiao : idRegiao,
                descricaoEscolaridade != null ? descricaoEscolaridade : idEscolaridade);
    }
}
