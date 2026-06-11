package com.vacinacao.dao;

import com.vacinacao.model.Infectado;
import com.vacinacao.util.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InfectadoDAO {

    public boolean inserir(Infectado infectado) {
        String sql = "INSERT INTO infectado (id_paciente, id_doenca, data_diagnostico, gravidade_caso, foi_internado, observacoes) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, infectado.getIdPaciente());
            stmt.setInt(2, infectado.getIdDoenca());
            stmt.setDate(3, java.sql.Date.valueOf(infectado.getDataDiagnostico()));
            stmt.setString(4, infectado.getGravidadeCaso().name());
            stmt.setBoolean(5, infectado.isFoiInternado());
            stmt.setString(6, infectado.getObservacoes());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao registrar infecção: " + e.getMessage());
            return false;
        }
    }

    public List<Infectado> listar() {
        List<Infectado> lista = new ArrayList<>();
        String sql = "SELECT i.*, p.nome AS nome_paciente, d.nome AS nome_doenca " +
                     "FROM infectado i " +
                     "JOIN paciente p ON i.id_paciente = p.id_paciente " +
                     "JOIN doenca d ON i.id_doenca = d.id_doenca " +
                     "ORDER BY i.data_diagnostico DESC";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Infectado i = new Infectado(
                        rs.getInt("id_infectado"),
                        rs.getInt("id_paciente"),
                        rs.getInt("id_doenca"),
                        rs.getDate("data_diagnostico").toLocalDate(),
                        Infectado.GravidadeCaso.valueOf(rs.getString("gravidade_caso")),
                        rs.getBoolean("foi_internado"),
                        rs.getString("observacoes")
                );
                i.setNomePaciente(rs.getString("nome_paciente"));
                i.setNomeDoenca(rs.getString("nome_doenca"));
                lista.add(i);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar infectados: " + e.getMessage());
        }
        return lista;
    }
}
