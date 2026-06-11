package com.vacinacao;

import com.vacinacao.dao.*;
import com.vacinacao.model.*;
import com.vacinacao.util.ConexaoBD;
import com.vacinacao.util.DadosIniciais;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Sistema de Controle de Vacinação - Interface de Texto
 * Projeto Integrador I-B
 *
 * Interface de texto que permite:
 * - Cadastro de pacientes, vacinações e infectados
 * - Consulta de dados armazenados
 * - Relatórios de análise (correlações)
 * - Inserção de dados iniciais a partir de ArrayList
 */
public class SistemaVacinacao {

    private static Scanner scanner = new Scanner(System.in);
    private static PacienteDAO pacienteDAO = new PacienteDAO();
    private static VacinacaoDAO vacinacaoDAO = new VacinacaoDAO();
    private static InfectadoDAO infectadoDAO = new InfectadoDAO();
    private static RegiaoDAO regiaoDAO = new RegiaoDAO();
    private static EscolaridadeDAO escolaridadeDAO = new EscolaridadeDAO();
    private static VacinaDAO vacinaDAO = new VacinaDAO();
    private static DoencaDAO doencaDAO = new DoencaDAO();
    private static DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║     SISTEMA DE CONTROLE DE VACINAÇÃO                 ║");
        System.out.println("║     Projeto Integrador I-B                           ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");

        boolean executando = true;
        while (executando) {
            exibirMenuPrincipal();
            int opcao = lerInteiro("Opção");

            switch (opcao) {
                case 1: menuCadastro(); break;
                case 2: menuConsulta(); break;
                case 3: menuRelatorios(); break;
                case 4: inserirDadosIniciais(); break;
                case 0:
                    System.out.println("\nEncerrando o sistema...");
                    ConexaoBD.fecharConexao();
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }

    // ==================== MENU PRINCIPAL ====================

    private static void exibirMenuPrincipal() {
        System.out.println("\n┌──────────────── MENÚ PRINCIPAL ────────────────┐");
        System.out.println("│  1 - Cadastros                                 │");
        System.out.println("│  2 - Consultas                                 │");
        System.out.println("│  3 - Relatórios e Análises                     │");
        System.out.println("│  4 - Inserir Dados Iniciais (ArrayList → BD)   │");
        System.out.println("│  0 - Sair                                      │");
        System.out.println("└───────────────────────────────────────────────┘");
    }

    // ==================== MENU CADASTROS ====================

    private static void menuCadastro() {
        System.out.println("\n┌────────────── CADASTROS ──────────────┐");
        System.out.println("│  1 - Cadastrar Paciente               │");
        System.out.println("│  2 - Registrar Vacinação              │");
        System.out.println("│  3 - Registrar Infecção               │");
        System.out.println("│  0 - Voltar                           │");
        System.out.println("└───────────────────────────────────────┘");

        switch (lerInteiro("Opção")) {
            case 1: cadastrarPaciente(); break;
            case 2: registrarVacinacao(); break;
            case 3: registrarInfeccao(); break;
            case 0: return;
            default: System.out.println("Opção inválida!");
        }
    }

    private static void cadastrarPaciente() {
        System.out.println("\n=== CADASTRO DE PACIENTE ===");

        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();

        System.out.print("CPF (000.000.000-00): ");
        String cpf = scanner.nextLine().trim();

        LocalDate dataNasc = lerDate("Data de nascimento (dd/MM/aaaa)");

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine().trim();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine().trim();

        // Selecionar região
        System.out.println("\n--- Regiões disponíveis ---");
        List<Regiao> regioes = regiaoDAO.listar();
        for (Regiao r : regioes) {
            System.out.println("  " + r);
        }
        int idRegiao = lerInteiro("ID da região");

        // Selecionar escolaridade
        System.out.println("\n--- Escolaridades disponíveis ---");
        List<Escolaridade> escolaridades = escolaridadeDAO.listar();
        for (Escolaridade e : escolaridades) {
            System.out.println("  " + e);
        }
        int idEscolaridade = lerInteiro("ID da escolaridade");

        Paciente paciente = new Paciente(nome, cpf, dataNasc, telefone, endereco, idRegiao, idEscolaridade);

        if (pacienteDAO.inserir(paciente)) {
            System.out.println("✓ Paciente cadastrado com sucesso!");
        } else {
            System.out.println("✗ Erro ao cadastrar paciente.");
        }
    }

    private static void registrarVacinacao() {
        System.out.println("\n=== REGISTRO DE VACINAÇÃO ===");

        // Listar pacientes
        System.out.println("\n--- Pacientes ---");
        List<Paciente> pacientes = pacienteDAO.listar();
        for (Paciente p : pacientes) {
            System.out.println("  " + p);
        }
        int idPaciente = lerInteiro("ID do paciente");

        // Listar vacinas
        System.out.println("\n--- Vacinas ---");
        List<Vacina> vacinas = vacinaDAO.listar();
        for (Vacina v : vacinas) {
            System.out.println("  " + v);
        }
        int idVacina = lerInteiro("ID da vacina");

        LocalDate dataAplicacao = lerDate("Data de aplicação (dd/MM/aaaa)");
        int numeroDose = lerInteiro("Número da dose");

        System.out.print("Unidade de saúde: ");
        String unidade = scanner.nextLine().trim();

        System.out.print("Observações: ");
        String obs = scanner.nextLine().trim();

        Vacinacao vacinacao = new Vacinacao(idPaciente, idVacina, dataAplicacao, numeroDose, unidade, obs);

        if (vacinacaoDAO.inserir(vacinacao)) {
            System.out.println("✓ Vacinação registrada com sucesso!");
        } else {
            System.out.println("✗ Erro ao registrar vacinação.");
        }
    }

    private static void registrarInfeccao() {
        System.out.println("\n=== REGISTRO DE INFECÇÃO ===");

        System.out.println("\n--- Pacientes ---");
        List<Paciente> pacientes = pacienteDAO.listar();
        for (Paciente p : pacientes) {
            System.out.println("  " + p);
        }
        int idPaciente = lerInteiro("ID do paciente");

        System.out.println("\n--- Doenças ---");
        List<Doenca> doencas = doencaDAO.listar();
        for (Doenca d : doencas) {
            System.out.println("  " + d);
        }
        int idDoenca = lerInteiro("ID da doença");

        LocalDate dataDiag = lerDate("Data do diagnóstico (dd/MM/aaaa)");

        System.out.println("Gravidade: 1-Assintomático 2-Leve 3-Moderado 4-Grave 5-Óbito");
        int grav = lerInteiro("Gravidade");
        Infectado.GravidadeCaso[] valores = Infectado.GravidadeCaso.values();
        Infectado.GravidadeCaso gravidade = (grav >= 1 && grav <= 5) ? valores[grav - 1] : Infectado.GravidadeCaso.LEVE;

        System.out.print("Foi internado? (S/N): ");
        boolean internado = scanner.nextLine().trim().equalsIgnoreCase("S");

        System.out.print("Observações: ");
        String obs = scanner.nextLine().trim();

        Infectado infectado = new Infectado(idPaciente, idDoenca, dataDiag, gravidade, internado, obs);

        if (infectadoDAO.inserir(infectado)) {
            System.out.println("✓ Infecção registrada com sucesso!");
        } else {
            System.out.println("✗ Erro ao registrar infecção.");
        }
    }

    // ==================== MENU CONSULTAS ====================

    private static void menuConsulta() {
        System.out.println("\n┌────────────── CONSULTAS ──────────────┐");
        System.out.println("│  1 - Listar Pacientes                 │");
        System.out.println("│  2 - Listar Vacinações                │");
        System.out.println("│  3 - Listar Infectados                │");
        System.out.println("│  4 - Consultar Vacinas por Paciente   │");
        System.out.println("│  0 - Voltar                           │");
        System.out.println("└───────────────────────────────────────┘");

        switch (lerInteiro("Opção")) {
            case 1: listarPacientes(); break;
            case 2: listarVacinacoes(); break;
            case 3: listarInfectados(); break;
            case 4: consultarVacinacaoPaciente(); break;
            case 0: return;
            default: System.out.println("Opção inválida!");
        }
    }

    private static void listarPacientes() {
        System.out.println("\n=== PACIENTES CADASTRADOS ===");
        List<Paciente> lista = pacienteDAO.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
        } else {
            for (Paciente p : lista) {
                System.out.println("  " + p);
            }
            System.out.println("Total: " + lista.size() + " paciente(s)");
        }
    }

    private static void listarVacinacoes() {
        System.out.println("\n=== VACINAÇÕES REGISTRADAS ===");
        List<Vacinacao> lista = vacinacaoDAO.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma vacinação registrada.");
        } else {
            for (Vacinacao v : lista) {
                System.out.println("  " + v);
            }
            System.out.println("Total: " + lista.size() + " registro(s)");
        }
    }

    private static void listarInfectados() {
        System.out.println("\n=== INFECTADOS REGISTRADOS ===");
        List<Infectado> lista = infectadoDAO.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum infectado registrado.");
        } else {
            for (Infectado i : lista) {
                System.out.println("  " + i);
            }
            System.out.println("Total: " + lista.size() + " registro(s)");
        }
    }

    private static void consultarVacinacaoPaciente() {
        int id = lerInteiro("ID do paciente");
        Paciente p = pacienteDAO.buscarPorId(id);
        if (p == null) {
            System.out.println("Paciente não encontrado.");
            return;
        }
        System.out.println("\nPaciente: " + p.getNome());
        List<Vacinacao> vacs = vacinacaoDAO.listarPorPaciente(id);
        if (vacs.isEmpty()) {
            System.out.println("  Nenhuma vacinação registrada para este paciente.");
        } else {
            for (Vacinacao v : vacs) {
                System.out.println("  → " + v.getNomeVacina() + " | Dose " + v.getNumeroDose() + " | " + v.getDataAplicacao());
            }
        }
    }

    // ==================== RELATÓRIOS E ANÁLISES ====================

    private static void menuRelatorios() {
        System.out.println("\n┌──────── RELATÓRIOS E ANÁLISES ────────┐");
        System.out.println("│  1 - Vacinados por Região             │");
        System.out.println("│  2 - Vacinados por Escolaridade       │");
        System.out.println("│  3 - Vacinados x Infectados           │");
        System.out.println("│  4 - Vacinas mais aplicadas           │");
        System.out.println("│  5 - Esquemas vacinais incompletos    │");
        System.out.println("│  0 - Voltar                           │");
        System.out.println("└───────────────────────────────────────┘");

        switch (lerInteiro("Opção")) {
            case 1: relatorioVacinadosPorRegiao(); break;
            case 2: relatorioVacinadosPorEscolaridade(); break;
            case 3: relatorioVacinadosVsInfectados(); break;
            case 4: relatorioVacinasMaisAplicadas(); break;
            case 5: relatorioEsquemasIncompletos(); break;
            case 0: return;
            default: System.out.println("Opção inválida!");
        }
    }

    private static void relatorioVacinadosPorRegiao() {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║   RELATÓRIO: VACINADOS POR REGIÃO               ║");
        System.out.println("╠══════════════════════════════════════════════════╣");

        List<Regiao> regioes = regiaoDAO.listar();
        for (Regiao r : regioes) {
            List<Paciente> pacientesRegiao = pacienteDAO.buscarPorRegiao(r.getIdRegiao());
            long vacinados = pacientesRegiao.stream()
                    .filter(p -> !vacinacaoDAO.listarPorPaciente(p.getIdPaciente()).isEmpty())
                    .count();
            System.out.printf("║  %-20s │ Pop: %3d │ Vacinados: %3d  ║%n",
                    r.getNome(), pacientesRegiao.size(), vacinados);
        }
        System.out.println("╚══════════════════════════════════════════════════╝");
    }

    private static void relatorioVacinadosPorEscolaridade() {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║   RELATÓRIO: VACINADOS POR ESCOLARIDADE         ║");
        System.out.println("╠══════════════════════════════════════════════════╣");

        List<Escolaridade> escolaridades = escolaridadeDAO.listar();
        List<Paciente> todosPacientes = pacienteDAO.listar();

        for (Escolaridade e : escolaridades) {
            long total = todosPacientes.stream()
                    .filter(p -> p.getIdEscolaridade() == e.getIdEscolaridade())
                    .count();
            long vacinados = todosPacientes.stream()
                    .filter(p -> p.getIdEscolaridade() == e.getIdEscolaridade())
                    .filter(p -> !vacinacaoDAO.listarPorPaciente(p.getIdPaciente()).isEmpty())
                    .count();
            System.out.printf("║  %-22s │ Total: %2d │ Vac: %2d  ║%n",
                    e.getDescricao(), total, vacinados);
        }
        System.out.println("╚══════════════════════════════════════════════════╝");
    }

    private static void relatorioVacinadosVsInfectados() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║   RELATÓRIO: VACINADOS x INFECTADOS                       ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");

        List<Doenca> doencas = doencaDAO.listar();
        List<Paciente> todosPacientes = pacienteDAO.listar();
        List<Infectado> todosInfectados = infectadoDAO.listar();

        for (Doenca d : doencas) {
            // Encontrar vacinas para esta doença
            List<Vacina> vacinasDoenca = vacinaDAO.listar();
            long vacinados = vacinasDoenca.stream()
                    .filter(v -> v.getIdDoenca() == d.getIdDoenca())
                    .flatMap(v -> vacinacaoDAO.listar().stream()
                            .filter(vac -> vac.getIdVacina() == v.getIdVacina()))
                    .map(Vacinacao::getIdPaciente)
                    .distinct()
                    .count();

            long infectados = todosInfectados.stream()
                    .filter(i -> i.getIdDoenca() == d.getIdDoenca())
                    .count();

            System.out.printf("║  %-18s │ Vacinados: %3d │ Infectados: %3d  ║%n",
                    d.getNome(), vacinados, infectados);
        }
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }

    private static void relatorioVacinasMaisAplicadas() {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║   RELATÓRIO: VACINAS MAIS APLICADAS             ║");
        System.out.println("╠══════════════════════════════════════════════════╣");

        List<Vacina> vacinas = vacinaDAO.listar();
        List<Vacinacao> todasVacinacoes = vacinacaoDAO.listar();

        vacinas.sort((a, b) -> {
            long countA = todasVacinacoes.stream().filter(v -> v.getIdVacina() == a.getIdVacina()).count();
            long countB = todasVacinacoes.stream().filter(v -> v.getIdVacina() == b.getIdVacina()).count();
            return Long.compare(countB, countA);
        });

        for (Vacina v : vacinas) {
            long count = todasVacinacoes.stream()
                    .filter(vac -> vac.getIdVacina() == v.getIdVacina())
                    .count();
            System.out.printf("║  %-25s │ Aplicações: %3d       ║%n",
                    v.getNome(), count);
        }
        System.out.println("╚══════════════════════════════════════════════════╝");
    }

    private static void relatorioEsquemasIncompletos() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║   RELATÓRIO: ESQUEMAS VACINAIS INCOMPLETOS                ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");

        List<Vacinacao> todasVacinacoes = vacinacaoDAO.listar();
        List<Vacina> vacinas = vacinaDAO.listar();
        boolean encontrou = false;

        for (Vacina vac : vacinas) {
            // Pacientes que tomaram esta vacina
            todasVacinacoes.stream()
                    .filter(v -> v.getIdVacina() == vac.getIdVacina())
                    .map(Vacinacao::getIdPaciente)
                    .distinct()
                    .forEach(idPac -> {
                        long dosesTomadas = todasVacinacoes.stream()
                                .filter(v -> v.getIdVacina() == vac.getIdVacina() && v.getIdPaciente() == idPac)
                                .count();
                        if (dosesTomadas < vac.getDosesNecessarias()) {
                            Paciente p = pacienteDAO.buscarPorId(idPac);
                            System.out.printf("║  %-15s │ %-20s │ %d/%d doses  ║%n",
                                    vac.getNome(), p != null ? p.getNome() : "ID:" + idPac,
                                    dosesTomadas, vac.getDosesNecessarias());
                        }
                    });
        }

        // Verificar se encontrou algum
        for (Vacina vac : vacinas) {
            long incompletos = todasVacinacoes.stream()
                    .filter(v -> v.getIdVacina() == vac.getIdVacina())
                    .map(Vacinacao::getIdPaciente)
                    .distinct()
                    .filter(idPac -> {
                        long d = todasVacinacoes.stream()
                                .filter(v -> v.getIdVacina() == vac.getIdVacina() && v.getIdPaciente() == idPac)
                                .count();
                        return d < vac.getDosesNecessarias();
                    })
                    .count();
            if (incompletos > 0) encontrou = true;
        }

        if (!encontrou) {
            System.out.println("║  Nenhum esquema vacinal incompleto encontrado.            ║");
        }
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }

    // ==================== INSERIR DADOS INICIAIS ====================

    private static void inserirDadosIniciais() {
        System.out.println("\n=== INSERIR DADOS INICIAIS (ArrayList → Banco de Dados) ===");
        System.out.println("Esta opção carrega dados de uma estrutura ArrayList");
        System.out.println("e os insere no banco de dados MySQL.\n");

        DadosIniciais dados = new DadosIniciais();
        dados.exibirResumo();

        System.out.print("\nDeseja inserir estes dados no banco? (S/N): ");
        if (!scanner.nextLine().trim().equalsIgnoreCase("S")) {
            System.out.println("Operação cancelada.");
            return;
        }

        int sucesso = 0, falha = 0;

        // Inserir pacientes
        for (Paciente p : dados.getPacientes()) {
            if (pacienteDAO.inserir(p)) sucesso++;
            else falha++;
        }

        // Inserir vacinações
        for (Vacinacao v : dados.getVacinacoes()) {
            if (vacinacaoDAO.inserir(v)) sucesso++;
            else falha++;
        }

        // Inserir infectados
        for (Infectado i : dados.getInfectados()) {
            if (infectadoDAO.inserir(i)) sucesso++;
            else falha++;
        }

        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║   RESULTADO DA INSERÇÃO              ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.printf("║  Inseridos com sucesso: %-12d║%n", sucesso);
        System.out.printf("║  Falhas (duplicados):   %-12d║%n", falha);
        System.out.println("╚══════════════════════════════════════╝");
    }

    // ==================== UTILITÁRIOS ====================

    private static int lerInteiro(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("  Por favor, digite um número válido.");
            }
        }
    }

    private static LocalDate lerDate(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            try {
                return LocalDate.parse(scanner.nextLine().trim(), dateFmt);
            } catch (DateTimeParseException e) {
                System.out.println("  Formato inválido. Use dd/MM/aaaa.");
            }
        }
    }
}
