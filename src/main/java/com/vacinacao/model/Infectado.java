package com.vacinacao.model;

import java.time.LocalDate;

public class Infectado {
    public enum GravidadeCaso { ASSINTOMATICO, LEVE, MODERADO, GRAVE, OBITO }

    private int idInfectado;
    private int idPaciente;
    private int idDoenca;
    private LocalDate dataDiagnostico;
    private GravidadeCaso gravidadeCaso;
    private boolean foiInternado;
    private String observacoes;

    // Campos auxiliares
    private String nomePaciente;
    private String nomeDoenca;

    public Infectado() {}

    public Infectado(int idPaciente, int idDoenca, LocalDate dataDiagnostico,
                     GravidadeCaso gravidadeCaso, boolean foiInternado, String observacoes) {
        this.idPaciente = idPaciente;
        this.idDoenca = idDoenca;
        this.dataDiagnostico = dataDiagnostico;
        this.gravidadeCaso = gravidadeCaso;
        this.foiInternado = foiInternado;
        this.observacoes = observacoes;
    }

    public Infectado(int idInfectado, int idPaciente, int idDoenca, LocalDate dataDiagnostico,
                     GravidadeCaso gravidadeCaso, boolean foiInternado, String observacoes) {
        this.idInfectado = idInfectado;
        this.idPaciente = idPaciente;
        this.idDoenca = idDoenca;
        this.dataDiagnostico = dataDiagnostico;
        this.gravidadeCaso = gravidadeCaso;
        this.foiInternado = foiInternado;
        this.observacoes = observacoes;
    }

    public int getIdInfectado() { return idInfectado; }
    public void setIdInfectado(int idInfectado) { this.idInfectado = idInfectado; }
    public int getIdPaciente() { return idPaciente; }
    public void setIdPaciente(int idPaciente) { this.idPaciente = idPaciente; }
    public int getIdDoenca() { return idDoenca; }
    public void setIdDoenca(int idDoenca) { this.idDoenca = idDoenca; }
    public LocalDate getDataDiagnostico() { return dataDiagnostico; }
    public void setDataDiagnostico(LocalDate dataDiagnostico) { this.dataDiagnostico = dataDiagnostico; }
    public GravidadeCaso getGravidadeCaso() { return gravidadeCaso; }
    public void setGravidadeCaso(GravidadeCaso gravidadeCaso) { this.gravidadeCaso = gravidadeCaso; }
    public boolean isFoiInternado() { return foiInternado; }
    public void setFoiInternado(boolean foiInternado) { this.foiInternado = foiInternado; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public String getNomePaciente() { return nomePaciente; }
    public void setNomePaciente(String nomePaciente) { this.nomePaciente = nomePaciente; }
    public String getNomeDoenca() { return nomeDoenca; }
    public void setNomeDoenca(String nomeDoenca) { this.nomeDoenca = nomeDoenca; }

    @Override
    public String toString() {
        return String.format("[%d] Paciente: %s | Doença: %s | Data: %s | Gravidade: %s | Internado: %s",
                idInfectado, nomePaciente != null ? nomePaciente : idPaciente,
                nomeDoenca != null ? nomeDoenca : idDoenca,
                dataDiagnostico, gravidadeCaso, foiInternado ? "Sim" : "Não");
    }
}
