package com.vacinacao.util;

import com.vacinacao.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Estrutura de dados utilizada para armazenar informações iniciais
 * antes da inserção no banco de dados.
 * Utiliza ArrayList (Lista) como estrutura de dados principal.
 */
public class DadosIniciais {

    // Listas (ArrayList) que armazenam os dados em memória
    private ArrayList<Paciente> pacientes;
    private ArrayList<Vacinacao> vacinacoes;
    private ArrayList<Infectado> infectados;

    public DadosIniciais() {
        pacientes = new ArrayList<>();
        vacinacoes = new ArrayList<>();
        infectados = new ArrayList<>();
        gerarDados();
    }

    /**
     * Gera dados fictícios realistas para popular o sistema.
     * Usa as listas ArrayList como estrutura de dados intermediária
     * antes da persistência no banco de dados MySQL.
     */
    private void gerarDados() {
        // ===== PACIENTES =====
        // Regiões: 1-10 (conforme SQL)
        // Escolaridades: 1-8 (conforme SQL)

        pacientes.add(new Paciente(1, "Ana Silva", "111.111.111-11",
                LocalDate.of(1990, 3, 15), "(11) 99100-0001",
                "Rua A, 100", 1, 7));

        pacientes.add(new Paciente(2, "Bruno Costa", "222.222.222-22",
                LocalDate.of(1985, 7, 22), "(11) 99200-0002",
                "Av. B, 250", 2, 6));

        pacientes.add(new Paciente(3, "Carla Oliveira", "333.333.333-33",
                LocalDate.of(1995, 11, 8), "(11) 99300-0003",
                "Rua C, 75", 3, 3));

        pacientes.add(new Paciente(4, "Daniel Santos", "444.444.444-44",
                LocalDate.of(2000, 1, 30), "(11) 99400-0004",
                "Rua D, 300", 4, 8));

        pacientes.add(new Paciente(5, "Elena Ferreira", "555.555.555-55",
                LocalDate.of(1978, 5, 12), "(11) 99500-0005",
                "Rua E, 50", 5, 2));

        pacientes.add(new Paciente(6, "Felipe Lima", "666.666.666-66",
                LocalDate.of(1992, 9, 25), "(11) 99600-0006",
                "Rua F, 180", 6, 5));

        pacientes.add(new Paciente(7, "Gabriela Souza", "777.777.777-77",
                LocalDate.of(1988, 2, 18), "(11) 99700-0007",
                "Rua G, 90", 7, 1));

        pacientes.add(new Paciente(8, "Hugo Almeida", "888.888.888-88",
                LocalDate.of(2005, 6, 5), "(11) 99800-0008",
                "Rua H, 400", 8, 8));

        pacientes.add(new Paciente(9, "Isabela Pereira", "999.999.999-99",
                LocalDate.of(1998, 12, 10), "(11) 99900-0009",
                "Av. I, 150", 9, 4));

        pacientes.add(new Paciente(10, "João Ribeiro", "101.010.101-01",
                LocalDate.of(1975, 4, 20), "(11) 99000-0010",
                "Rua J, 220", 10, 3));

        // ===== VACINAÇÕES =====
        // Vacinas 1-9, Pacientes 1-10

        vacinacoes.add(new Vacinacao(1, 1, 1, LocalDate.of(2023, 3, 1), 1, "UBS Centro", " Primeira dose"));
        vacinacoes.add(new Vacinacao(2, 1, 1, LocalDate.of(2023, 3, 29), 2, "UBS Centro", " Segunda dose"));
        vacinacoes.add(new Vacinacao(3, 1, 3, LocalDate.of(2023, 5, 10), 1, "UBS Centro", " Gripe anual"));

        vacinacoes.add(new Vacinacao(4, 2, 2, LocalDate.of(2023, 2, 15), 1, "Posto Vila Madalena", " Primeira dose"));
        vacinacoes.add(new Vacinacao(5, 2, 2, LocalDate.of(2023, 3, 8), 2, "Posto Vila Madalena", " Segunda dose"));
        vacinacoes.add(new Vacinacao(6, 2, 4, LocalDate.of(2023, 4, 20), 1, "Posto Vila Madalena", " Tríplice viral"));
        vacinacoes.add(new Vacinacao(7, 2, 4, LocalDate.of(2023, 5, 20), 2, "Posto Vila Madalena", " Reforço tríplice"));

        vacinacoes.add(new Vacinacao(8, 3, 1, LocalDate.of(2023, 6, 1), 1, "UBS Paraisópolis", " Primeira dose"));
        // Paciente 3 não tomou 2ª dose - esquema incompleto

        vacinacoes.add(new Vacinacao(9, 4, 2, LocalDate.of(2023, 1, 10), 1, "UBS Jardins", " Primeira dose"));
        vacinacoes.add(new Vacinacao(10, 4, 2, LocalDate.of(2023, 2, 1), 2, "UBS Jardins", " Segunda dose"));
        vacinacoes.add(new Vacinacao(11, 4, 6, LocalDate.of(2023, 3, 1), 1, "UBS Jardins", " Hepatite B - 1ª dose"));
        vacinacoes.add(new Vacinacao(12, 4, 6, LocalDate.of(2023, 4, 1), 2, "UBS Jardins", " Hepatite B - 2ª dose"));

        vacinacoes.add(new Vacinacao(13, 5, 9, LocalDate.of(2023, 2, 20), 1, "UBS Capão Redondo", " BCG"));
        vacinacoes.add(new Vacinacao(14, 5, 3, LocalDate.of(2023, 4, 15), 1, "UBS Capão Redondo", " Gripe"));

        vacinacoes.add(new Vacinacao(15, 6, 1, LocalDate.of(2023, 5, 5), 1, "UBS Tatuapé", " COVID 1ª dose"));
        vacinacoes.add(new Vacinacao(16, 6, 1, LocalDate.of(2023, 6, 2), 2, "UBS Tatuapé", " COVID 2ª dose"));
        vacinacoes.add(new Vacinacao(17, 6, 5, LocalDate.of(2023, 7, 10), 1, "UBS Tatuapé", " Febre amarela"));

        vacinacoes.add(new Vacinacao(18, 7, 1, LocalDate.of(2023, 8, 1), 1, "UBS Grajaú", " COVID 1ª dose"));
        // Paciente 7 não completou esquema

        vacinacoes.add(new Vacinacao(19, 8, 2, LocalDate.of(2023, 1, 20), 1, "UBS Moema", " COVID 1ª dose"));
        vacinacoes.add(new Vacinacao(20, 8, 2, LocalDate.of(2023, 2, 10), 2, "UBS Moema", " COVID 2ª dose"));
        vacinacoes.add(new Vacinacao(21, 8, 4, LocalDate.of(2023, 3, 15), 1, "UBS Moema", " Tríplice viral"));
        vacinacoes.add(new Vacinacao(22, 8, 7, LocalDate.of(2023, 4, 1), 1, "UBS Moema", " VOP 1ª dose"));
        vacinacoes.add(new Vacinacao(23, 8, 7, LocalDate.of(2023, 5, 1), 2, "UBS Moema", " VOP 2ª dose"));
        vacinacoes.add(new Vacinacao(24, 8, 7, LocalDate.of(2023, 6, 1), 3, "UBS Moema", " VOP 3ª dose"));

        vacinacoes.add(new Vacinacao(25, 9, 3, LocalDate.of(2023, 5, 20), 1, "UBS Pirituba", " Gripe"));

        vacinacoes.add(new Vacinacao(26, 10, 1, LocalDate.of(2023, 4, 10), 1, "UBS Itaquera", " COVID 1ª dose"));
        vacinacoes.add(new Vacinacao(27, 10, 1, LocalDate.of(2023, 5, 8), 2, "UBS Itaquera", " COVID 2ª dose"));
        vacinacoes.add(new Vacinacao(28, 10, 6, LocalDate.of(2023, 6, 1), 1, "UBS Itaquera", " Hep B 1ª dose"));
        vacinacoes.add(new Vacinacao(29, 10, 6, LocalDate.of(2023, 7, 1), 2, "UBS Itaquera", " Hep B 2ª dose"));
        vacinacoes.add(new Vacinacao(30, 10, 6, LocalDate.of(2023, 8, 1), 3, "UBS Itaquera", " Hep B 3ª dose"));

        // ===== INFECTADOS =====
        // Alguns pacientes contraíram doenças (vacinados e não vacinados)

        infectados.add(new Infectado(1, 3, 1, LocalDate.of(2023, 7, 15),
                Infectado.GravidadeCaso.LEVE, false, "Paciente vacinado - caso leve"));

        infectados.add(new Infectado(2, 5, 1, LocalDate.of(2023, 8, 20),
                Infectado.GravidadeCaso.GRAVE, true, "Paciente com esquema incompleto - internado"));

        infectados.add(new Infectado(3, 7, 1, LocalDate.of(2023, 9, 5),
                Infectado.GravidadeCaso.MODERADO, false, "Paciente com esquema incompleto"));

        infectados.add(new Infectado(4, 3, 2, LocalDate.of(2023, 6, 10),
                Infectado.GravidadeCaso.LEVE, false, "Influenza - paciente sem vacina da gripe"));

        infectados.add(new Infectado(5, 10, 3, LocalDate.of(2023, 10, 1),
                Infectado.GravidadeCaso.MODERADO, false, "Sarampo - paciente sem tríplice viral"));
    }

    public ArrayList<Paciente> getPacientes() { return pacientes; }
    public ArrayList<Vacinacao> getVacinacoes() { return vacinacoes; }
    public ArrayList<Infectado> getInfectados() { return infectados; }

    /**
     * Exibe um resumo dos dados armazenados nas listas.
     */
    public void exibirResumo() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║   DADOS EM MEMÓRIA (ArrayList)           ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.printf("║  Pacientes:  %-28d║%n", pacientes.size());
        System.out.printf("║  Vacinações: %-28d║%n", vacinacoes.size());
        System.out.printf("║  Infectados: %-28d║%n", infectados.size());
        System.out.println("╚══════════════════════════════════════════╝");
    }
}
