# Sistema de Controle de Vacinação - Projeto Integrador I-B

## Estrutura do Projeto

```
├── sql/
│   └── banco_vacinacao.sql       -- Script SQL completo (criação + dados + consultas)
│
└── src/main/java/com/vacinacao/
    ├── SistemaVacinacao.java     -- Classe principal (interface de texto)
    │
    ├── model/                    -- Classes de modelo (POJOs)
    │   ├── Paciente.java
    │   ├── Vacina.java
    │   ├── Vacinacao.java
    │   ├── Doenca.java
    │   ├── Infectado.java
    │   ├── Regiao.java
    │   └── Escolaridade.java
    │
    ├── dao/                      -- Data Access Objects (JDBC)
    │   ├── PacienteDAO.java
    │   ├── VacinacaoDAO.java
    │   ├── InfectadoDAO.java
    │   ├── VacinaDAO.java
    │   ├── DoencaDAO.java
    │   ├── RegiaoDAO.java
    │   └── EscolaridadeDAO.java
    │
    └── util/
        ├── ConexaoBD.java        -- Conexão JDBC com MySQL
        └── DadosIniciais.java    -- ArrayList com dados fictícios
```

## Pré-requisitos

- Java 11+
- MySQL 8.0+
- mysql-connector-java (JDBC driver)

## Configuração

### 1. Criar o banco de dados

```bash
mysql -u root -p < sql/banco_vacinacao.sql
```

### 2. Configurar conexão

Edite `ConexaoBD.java` com suas credenciais:
- URL (padrão: `jdbc:mysql://localhost:3306/db_vacinacao`)
- Usuário (padrão: `root`)
- Senha (padrão: vazia)

### 3. Compilar e executar

```bash
# Compilar
javac -cp "lib/mysql-connector-java-8.x.x.jar:." src/main/java/com/vacinacao/**/*.java -d out

# Executar
java -cp "lib/mysql-connector-java-8.x.x.jar:out" com.vacinacao.SistemaVacinacao
```

## Funcionalidades

- **Cadastros:** Pacientes, Vacinações, Infecções
- **Consultas:** Listar pacientes, vacinações, infectados; consultar vacinas por paciente
- **Relatórios:**
  - Vacinados por Região (correlação cobertura x localização)
  - Vacinados por Escolaridade (correlação cobertura x educação)
  - Vacinados x Infectados (eficácia vacinal)
  - Vacinas mais aplicadas
  - Esquemas vacinais incompletos
- **Inserção de dados iniciais:** Carrega dados de ArrayList para o BD

## Modelagem do Banco de Dados

### Tabelas
| Tabela        | Descrição                                    |
|---------------|----------------------------------------------|
| `regiao`      | Regiões/bairros com índice socioeconômico    |
| `escolaridade`| Níveis de escolaridade                       |
| `paciente`    | Dados pessoais (nome, CPF, idade, endereço)  |
| `doenca`      | Doenças com nível de gravidade               |
| `vacina`      | Vacinas com fabricante e doses necessárias   |
| `vacinacao`   | Registro de doses aplicadas (tabela central) |
| `infectado`   | Registro de infecções pós-vacinação          |

### Relacionamentos para Análise
- **Vacinado x Região:** `paciente → regiao` + `vacinacao`
- **Vacinado x Escolaridade:** `paciente → escolaridade` + `vacinacao`
- **Vacinado x Doente:** `vacinacao` vs `infectado` por doença

## Estrutura de Dados

O projeto utiliza **ArrayList** como estrutura de dados intermediária.
A classe `DadosIniciais` armazena 10 pacientes, 30 vacinações e 5 infecções
em memória antes de inseri-las no banco de dados via JDBC.
