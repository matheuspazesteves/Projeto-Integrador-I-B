-- ============================================================
-- PROJETO INTEGRADOR I-B - SISTEMA DE CONTROLE DE VACINAÇÃO
-- Banco de Dados: MySQL
-- ============================================================

-- Criação do banco de dados
DROP DATABASE IF EXISTS db_vacinacao;
CREATE DATABASE db_vacinacao
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE db_vacinacao;

-- ============================================================
-- TABELA: regiao
-- Armazena as regiões/bairros de moradia dos pacientes
-- Permite análise de correlação: Vacinado x Região
-- ============================================================
CREATE TABLE regiao (
    id_regiao INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL DEFAULT 'Não informado',
    estado VARCHAR(2) NOT NULL DEFAULT 'XX',
    indice_desenvolvimento DECIMAL(5,2) DEFAULT NULL COMMENT 'Índice socioeconômico da região',
    UNIQUE KEY uk_regiao_nome_cidade (nome, cidade)
) ENGINE=InnoDB;

-- ============================================================
-- TABELA: escolaridade
-- Armazena os níveis de escolaridade
-- Permite análise de correlação: Vacinado x Escolaridade
-- ============================================================
CREATE TABLE escolaridade (
    id_escolaridade INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL UNIQUE
) ENGINE=InnoDB;

-- ============================================================
-- TABELA: paciente
-- Armazena informações pessoais dos pacientes
-- ============================================================
CREATE TABLE paciente (
    id_paciente INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    data_nascimento DATE NOT NULL,
    telefone VARCHAR(15),
    endereco VARCHAR(200),
    id_regiao INT,
    id_escolaridade INT,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_regiao) REFERENCES regiao(id_regiao)
        ON UPDATE CASCADE ON DELETE SET NULL,
    FOREIGN KEY (id_escolaridade) REFERENCES escolaridade(id_escolaridade)
        ON UPDATE CASCADE ON DELETE SET NULL
) ENGINE=InnoDB;

-- ============================================================
-- TABELA: doenca
-- Armazena as doenças contra as quais as vacinas protegem
-- Permite análise de correlação: Vacinado x Doente
-- ============================================================
CREATE TABLE doenca (
    id_doenca INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    descricao TEXT,
    gravidade ENUM('BAIXA', 'MEDIA', 'ALTA', 'CRITICA') NOT NULL DEFAULT 'MEDIA'
) ENGINE=InnoDB;

-- ============================================================
-- TABELA: vacina
-- Armazena os tipos de vacinas disponíveis
-- ============================================================
CREATE TABLE vacina (
    id_vacina INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    fabricante VARCHAR(100),
    id_doenca INT NOT NULL,
    doses_necessarias INT NOT NULL DEFAULT 1,
    intervalo_dias INT DEFAULT 0 COMMENT 'Dias entre doses',
    UNIQUE KEY uk_vacina_nome (nome),
    FOREIGN KEY (id_doenca) REFERENCES doenca(id_doenca)
        ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB;

-- ============================================================
-- TABELA: vacinacao
-- Registra cada dose de vacina aplicada em um paciente
-- Tabela central que conecta paciente x vacina x data/dose
-- ============================================================
CREATE TABLE vacinacao (
    id_vacinacao INT AUTO_INCREMENT PRIMARY KEY,
    id_paciente INT NOT NULL,
    id_vacina INT NOT NULL,
    data_aplicacao DATE NOT NULL,
    numero_dose INT NOT NULL DEFAULT 1,
    unidade_saude VARCHAR(150),
    observacoes TEXT,
    FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (id_vacina) REFERENCES vacina(id_vacina)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    UNIQUE KEY uk_paciente_vacina_dose (id_paciente, id_vacina, numero_dose)
) ENGINE=InnoDB;

-- ============================================================
-- TABELA: infectado
-- Registra pacientes que contraíram doenças
-- Permite análise: Vacinado x Doente (eficácia)
-- ============================================================
CREATE TABLE infectado (
    id_infectado INT AUTO_INCREMENT PRIMARY KEY,
    id_paciente INT NOT NULL,
    id_doenca INT NOT NULL,
    data_diagnostico DATE NOT NULL,
    gravidade_caso ENUM('ASSINTOMATICO', 'LEVE', 'MODERADO', 'GRAVE', 'OBITO') NOT NULL,
    foi_internado BOOLEAN DEFAULT FALSE,
    observacoes TEXT,
    FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (id_doenca) REFERENCES doenca(id_doenca)
        ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB;

-- ============================================================
-- ÍNDICES para melhorar performance das consultas
-- ============================================================
CREATE INDEX idx_paciente_regiao ON paciente(id_regiao);
CREATE INDEX idx_paciente_escolaridade ON paciente(id_escolaridade);
CREATE INDEX idx_paciente_nome ON paciente(nome);
CREATE INDEX idx_vacinacao_data ON vacinacao(data_aplicacao);
CREATE INDEX idx_vacinacao_paciente ON vacinacao(id_paciente);
CREATE INDEX idx_infectado_paciente ON infectado(id_paciente);
CREATE INDEX idx_infectado_doenca ON infectado(id_doenca);

-- ============================================================
-- DADOS INICIAIS
-- ============================================================

-- Escolaridades
INSERT INTO escolaridade (descricao) VALUES
    ('Analfabeto'),
    ('Fundamental Incompleto'),
    ('Fundamental Completo'),
    ('Médio Incompleto'),
    ('Médio Completo'),
    ('Superior Incompleto'),
    ('Superior Completo'),
    ('Pós-Graduação');

-- Regiões
INSERT INTO regiao (nome, cidade, estado, indice_desenvolvimento) VALUES
    ('Centro', 'São Paulo', 'SP', 85.50),
    ('Vila Madalena', 'São Paulo', 'SP', 92.30),
    ('Paraisópolis', 'São Paulo', 'SP', 45.20),
    ('Jardins', 'São Paulo', 'SP', 95.80),
    ('Capão Redondo', 'São Paulo', 'SP', 38.70),
    ('Tatuapé', 'São Paulo', 'SP', 72.10),
    ('Grajaú', 'São Paulo', 'SP', 35.40),
    ('Moema', 'São Paulo', 'SP', 91.60),
    ('Pirituba', 'São Paulo', 'SP', 55.30),
    ('Itaquera', 'São Paulo', 'SP', 60.80);

-- Doenças
INSERT INTO doenca (nome, descricao, gravidade) VALUES
    ('COVID-19', 'Doença causada pelo coronavírus SARS-CoV-2', 'ALTA'),
    ('Influenza', 'Gripe causada pelo vírus Influenza', 'MEDIA'),
    ('Sarampo', 'Doença viral altamente contagiosa', 'ALTA'),
    ('Febre Amarela', 'Doença viral transmitida por mosquitos', 'CRITICA'),
    ('Hepatite B', 'Inflamação do fígado pelo vírus HBV', 'MEDIA'),
    ('Tétano', 'Infecção bacteriana que afeta o sistema nervoso', 'CRITICA'),
    ('Poliomielite', 'Paralisia infantil causada pelo poliovírus', 'ALTA'),
    ('Tuberculose', 'Infecção bacteriana que afeta os pulmões', 'ALTA');

-- Vacinas
INSERT INTO vacina (nome, fabricante, id_doenca, doses_necessarias, intervalo_dias) VALUES
    ('Coronavac', 'Butantan', 1, 2, 28),
    ('Pfizer-BioNTech', 'Pfizer', 1, 2, 21),
    ('Influenza Tetravalente', 'Butantan', 2, 1, 0),
    ('Tríplice Viral', 'Fiocruz', 3, 2, 30),
    ('Febre Amarela', 'Bio-Manguinhos', 4, 1, 0),
    ('Hepatite B', 'Butantan', 5, 3, 60),
    ('dT (Dupla Adulto)', 'Butantan', 6, 3, 60),
    ('VOP (Vacina Oral Polio)', 'Butantan', 7, 3, 60),
    ('BCG', 'Fundação Ataulpho de Paiva', 8, 1, 0);

-- ============================================================
-- CONSULTAS DE ANÁLISE (EXEMPLOS)
-- ============================================================

-- 1. Quantidade de vacinados por região
-- SELECT r.nome AS regiao, COUNT(DISTINCT v.id_paciente) AS total_vacinados
-- FROM vacinacao v
-- JOIN paciente p ON v.id_paciente = p.id_paciente
-- JOIN regiao r ON p.id_regiao = r.id_regiao
-- GROUP BY r.nome
-- ORDER BY total_vacinados DESC;

-- 2. Quantidade de vacinados por escolaridade
-- SELECT e.descricao AS escolaridade, COUNT(DISTINCT v.id_paciente) AS total_vacinados
-- FROM vacinacao v
-- JOIN paciente p ON v.id_paciente = p.id_paciente
-- JOIN escolaridade e ON p.id_escolaridade = e.id_escolaridade
-- GROUP BY e.descricao
-- ORDER BY total_vacinados DESC;

-- 3. Taxa de infecção entre vacinados vs não vacinados por doença
-- SELECT d.nome AS doenca,
--        COUNT(DISTINCT v.id_paciente) AS vacinados,
--        COUNT(DISTINCT i.id_paciente) AS infectados,
--        COUNT(DISTINCT v.id_paciente) - COUNT(DISTINCT i.id_paciente) AS protegidos
-- FROM doenca d
-- LEFT JOIN vacina vac ON d.id_doenca = vac.id_doenca
-- LEFT JOIN vacinacao v ON vac.id_vacina = v.id_vacina
-- LEFT JOIN infectado i ON d.id_doenca = i.id_doenca
-- GROUP BY d.nome;

-- 4. Vacinas mais aplicadas
-- SELECT vac.nome AS vacina, COUNT(v.id_vacinacao) AS total_aplicacoes
-- FROM vacina vac
-- JOIN vacinacao v ON vac.id_vacina = v.id_vacina
-- GROUP BY vac.nome
-- ORDER BY total_aplicacoes DESC;

-- 5. Pacientes com esquema vacinal incompleto
-- SELECT p.nome, vac.nome AS vacina, vac.doses_necessarias,
--        COUNT(v.id_vacinacao) AS doses_tomadas
-- FROM vacinacao v
-- JOIN paciente p ON v.id_paciente = p.id_paciente
-- JOIN vacina vac ON v.id_vacina = vac.id_vacina
-- GROUP BY p.nome, vac.nome, vac.doses_necessarias
-- HAVING COUNT(v.id_vacinacao) < vac.doses_necessarias;
