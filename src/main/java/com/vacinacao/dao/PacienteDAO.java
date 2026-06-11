package com.vacinacao.dao;

import com.vacinacao.model.Paciente;
import com.vacinacao.util.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public boolean inserir(Paciente paciente) {
        String sql = "INSERT INTO paciente (nome, cpf, data_nascimento, telefone, endereco, id_regiao, id_escolaridade) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setDate(3, java.sql.Date.valueOf(paciente.getDataNascimento()));
            stmt.setString(4, paciente.getTelefone());
            stmt.setString(5, paciente.getEndereco());
            stmt.setInt(6, paciente.getIdRegiao());
            stmt.setInt(7, paciente.getIdEscolaridade());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir paciente: " + e.getMessage());
            return false;
        }
    }

    public List<Paciente> listar() {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT p.*, r.nome AS nome_regiao, e.descricao AS desc_escolaridade " +
                     "FROM paciente p " +
                     "LEFT JOIN regiao r ON p.id_regiao = r.id_regiao " +
                     "LEFT JOIN escolaridade e ON p.id_escolaridade = e.id_escolaridade " +
                     "ORDER BY p.nome";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Paciente p = new Paciente(
                        rs.getInt("id_paciente"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("telefone"),
                        rs.getString("endereco"),
                        rs.getInt("id_regiao"),
                        rs.getInt("id_escolaridade")
                );
                p.setNomeRegiao(rs.getString("nome_regiao"));
                p.setDescricaoEscolaridade(rs.getString("desc_escolaridade"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar pacientes: " + e.getMessage());
        }
        return lista;
    }

    public Paciente buscarPorId(int id) {
        String sql = "SELECT p.*, r.nome AS nome_regiao, e.descricao AS desc_escolaridade " +
                     "FROM paciente p " +
                     "LEFT JOIN regiao r ON p.id_regiao = r.id_regiao " +
                     "LEFT JOIN escolaridade e ON p.id_escolaridade = e.id_escolaridade " +
                     "WHERE p.id_paciente = ?";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Paciente p = new Paciente(
                        rs.getInt("id_paciente"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("telefone"),
                        rs.getString("endereco"),
                        rs.getInt("id_regiao"),
                        rs.getInt("id_escolaridade")
                );
                p.setNomeRegiao(rs.getString("nome_regiao"));
                p.setDescricaoEscolaridade(rs.getString("desc_escolaridade"));
                return p;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar paciente: " + e.getMessage());
        }
        return null;
    }

    public List<Paciente> buscarPorRegiao(int idRegiao) {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT p.* FROM paciente p WHERE p.id_regiao = ?";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idRegiao);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Paciente(
                        rs.getInt("id_paciente"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("telefone"),
                        rs.getString("endereco"),
                        rs.getInt("id_regiao"),
                        rs.getInt("id_escolaridade")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro: " + e.getMessage());
        }
        return lista;
    }
}
