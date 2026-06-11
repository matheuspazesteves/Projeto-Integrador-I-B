package com.vacinacao.model;

public class Vacina {
    private int idVacina;
    private String nome;
    private String fabricante;
    private int idDoenca;
    private int dosesNecessarias;
    private int intervaloDias;

    public Vacina() {}

    public Vacina(String nome, String fabricante, int idDoenca, int dosesNecessarias, int intervaloDias) {
        this.nome = nome;
        this.fabricante = fabricante;
        this.idDoenca = idDoenca;
        this.dosesNecessarias = dosesNecessarias;
        this.intervaloDias = intervaloDias;
    }

    public Vacina(int idVacina, String nome, String fabricante, int idDoenca, int dosesNecessarias, int intervaloDias) {
        this.idVacina = idVacina;
        this.nome = nome;
        this.fabricante = fabricante;
        this.idDoenca = idDoenca;
        this.dosesNecessarias = dosesNecessarias;
        this.intervaloDias = intervaloDias;
    }

    public int getIdVacina() { return idVacina; }
    public void setIdVacina(int idVacina) { this.idVacina = idVacina; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getFabricante() { return fabricante; }
    public void setFabricante(String fabricante) { this.fabricante = fabricante; }
    public int getIdDoenca() { return idDoenca; }
    public void setIdDoenca(int idDoenca) { this.idDoenca = idDoenca; }
    public int getDosesNecessarias() { return dosesNecessarias; }
    public void setDosesNecessarias(int dosesNecessarias) { this.dosesNecessarias = dosesNecessarias; }
    public int getIntervaloDias() { return intervaloDias; }
    public void setIntervaloDias(int intervaloDias) { this.intervaloDias = intervaloDias; }

    @Override
    public String toString() {
        return String.format("[%d] %s (Fab: %s) - %d dose(s)", idVacina, nome, fabricante, dosesNecessarias);
    }
}
