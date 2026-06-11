package com.vacinacao.dao;

import com.vacinacao.model.Vacina;
import com.vacinacao.util.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VacinaDAO {

    public boolean inserir(Vacina vacina) {
        String sql = "INSERT INTO vacina (nome, fabricante, id_doenca, doses_necessarias, intervalo_dias) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vacina.getNome());
            stmt.setString(2, vacina.getFabricante());
            stmt.setInt(3, vacina.getIdDoenca());
            stmt.setInt(4, vacina.getDosesNecessarias());
            stmt.setInt(5, vacina.getIntervaloDias());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir vacina: " + e.getMessage());
            return false;
        }
    }

    public List<Vacina> listar() {
        List<Vacina> lista = new ArrayList<>();
        String sql = "SELECT * FROM vacina ORDER BY nome";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Vacina(
                        rs.getInt("id_vacina"),
                        rs.getString("nome"),
                        rs.getString("fabricante"),
                        rs.getInt("id_doenca"),
                        rs.getInt("doses_necessarias"),
                        rs.getInt("intervalo_dias")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar vacinas: " + e.getMessage());
        }
        return lista;
    }

    public Vacina buscarPorId(int id) {
        String sql = "SELECT * FROM vacina WHERE id_vacina = ?";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Vacina(
                        rs.getInt("id_vacina"),
                        rs.getString("nome"),
                        rs.getString("fabricante"),
                        rs.getInt("id_doenca"),
                        rs.getInt("doses_necessarias"),
                        rs.getInt("intervalo_dias")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar vacina: " + e.getMessage());
        }
        return null;
    }
}
