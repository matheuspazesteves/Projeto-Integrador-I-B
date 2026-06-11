package com.vacinacao.model;

import java.time.LocalDate;

public class Vacinacao {
    private int idVacinacao;
    private int idPaciente;
    private int idVacina;
    private LocalDate dataAplicacao;
    private int numeroDose;
    private String unidadeSaude;
    private String observacoes;

    // Campos auxiliares para exibição
    private String nomePaciente;
    private String nomeVacina;

    public Vacinacao() {}

    public Vacinacao(int idPaciente, int idVacina, LocalDate dataAplicacao,
                     int numeroDose, String unidadeSaude, String observacoes) {
        this.idPaciente = idPaciente;
        this.idVacina = idVacina;
        this.dataAplicacao = dataAplicacao;
        this.numeroDose = numeroDose;
        this.unidadeSaude = unidadeSaude;
        this.observacoes = observacoes;
    }

    public Vacinacao(int idVacinacao, int idPaciente, int idVacina, LocalDate dataAplicacao,
                     int numeroDose, String unidadeSaude, String observacoes) {
        this.idVacinacao = idVacinacao;
        this.idPaciente = idPaciente;
        this.idVacina = idVacina;
        this.dataAplicacao = dataAplicacao;
        this.numeroDose = numeroDose;
        this.unidadeSaude = unidadeSaude;
        this.observacoes = observacoes;
    }

    public int getIdVacinacao() { return idVacinacao; }
    public void setIdVacinacao(int idVacinacao) { this.idVacinacao = idVacinacao; }
    public int getIdPaciente() { return idPaciente; }
    public void setIdPaciente(int idPaciente) { this.idPaciente = idPaciente; }
    public int getIdVacina() { return idVacina; }
    public void setIdVacina(int idVacina) { this.idVacina = idVacina; }
    public LocalDate getDataAplicacao() { return dataAplicacao; }
    public void setDataAplicacao(LocalDate dataAplicacao) { this.dataAplicacao = dataAplicacao; }
    public int getNumeroDose() { return numeroDose; }
    public void setNumeroDose(int numeroDose) { this.numeroDose = numeroDose; }
    public String getUnidadeSaude() { return unidadeSaude; }
    public void setUnidadeSaude(String unidadeSaude) { this.unidadeSaude = unidadeSaude; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public String getNomePaciente() { return nomePaciente; }
    public void setNomePaciente(String nomePaciente) { this.nomePaciente = nomePaciente; }
    public String getNomeVacina() { return nomeVacina; }
    public void setNomeVacina(String nomeVacina) { this.nomeVacina = nomeVacina; }

    @Override
    public String toString() {
        return String.format("[%d] Paciente: %s | Vacina: %s | Dose: %d | Data: %s | Unidade: %s",
                idVacinacao, nomePaciente != null ? nomePaciente : idPaciente,
                nomeVacina != null ? nomeVacina : idVacina,
                numeroDose, dataAplicacao, unidadeSaude);
    }
}
