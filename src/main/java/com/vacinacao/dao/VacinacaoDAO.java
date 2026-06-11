package com.vacinacao.dao;

import com.vacinacao.model.Vacinacao;
import com.vacinacao.util.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VacinacaoDAO {

    public boolean inserir(Vacinacao vacinacao) {
        String sql = "INSERT INTO vacinacao (id_paciente, id_vacina, data_aplicacao, numero_dose, unidade_saude, observacoes) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vacinacao.getIdPaciente());
            stmt.setInt(2, vacinacao.getIdVacina());
            stmt.setDate(3, java.sql.Date.valueOf(vacinacao.getDataAplicacao()));
            stmt.setInt(4, vacinacao.getNumeroDose());
            stmt.setString(5, vacinacao.getUnidadeSaude());
            stmt.setString(6, vacinacao.getObservacoes());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao registrar vacinação: " + e.getMessage());
            return false;
        }
    }

    public List<Vacinacao> listar() {
        List<Vacinacao> lista = new ArrayList<>();
        String sql = "SELECT v.*, p.nome AS nome_paciente, vac.nome AS nome_vacina " +
                     "FROM vacinacao v " +
                     "JOIN paciente p ON v.id_paciente = p.id_paciente " +
                     "JOIN vacina vac ON v.id_vacina = vac.id_vacina " +
                     "ORDER BY v.data_aplicacao DESC";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Vacinacao v = new Vacinacao(
                        rs.getInt("id_vacinacao"),
                        rs.getInt("id_paciente"),
                        rs.getInt("id_vacina"),
                        rs.getDate("data_aplicacao").toLocalDate(),
                        rs.getInt("numero_dose"),
                        rs.getString("unidade_saude"),
                        rs.getString("observacoes")
                );
                v.setNomePaciente(rs.getString("nome_paciente"));
                v.setNomeVacina(rs.getString("nome_vacina"));
                lista.add(v);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar vacinações: " + e.getMessage());
        }
        return lista;
    }

    public List<Vacinacao> listarPorPaciente(int idPaciente) {
        List<Vacinacao> lista = new ArrayList<>();
        String sql = "SELECT v.*, vac.nome AS nome_vacina, p.nome AS nome_paciente " +
                     "FROM vacinacao v " +
                     "JOIN vacina vac ON v.id_vacina = vac.id_vacina " +
                     "JOIN paciente p ON v.id_paciente = p.id_paciente " +
                     "WHERE v.id_paciente = ? " +
                     "ORDER BY v.data_aplicacao";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPaciente);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Vacinacao v = new Vacinacao(
                        rs.getInt("id_vacinacao"),
                        rs.getInt("id_paciente"),
                        rs.getInt("id_vacina"),
                        rs.getDate("data_aplicacao").toLocalDate(),
                        rs.getInt("numero_dose"),
                        rs.getString("unidade_saude"),
                        rs.getString("observacoes")
                );
                v.setNomePaciente(rs.getString("nome_paciente"));
                v.setNomeVacina(rs.getString("nome_vacina"));
                lista.add(v);
            }
        } catch (SQLException e) {
            System.err.println("Erro: " + e.getMessage());
        }
        return lista;
    }
}
