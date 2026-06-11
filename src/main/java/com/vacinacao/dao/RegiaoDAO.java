package com.vacinacao.dao;

import com.vacinacao.model.Regiao;
import com.vacinacao.util.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegiaoDAO {

    public boolean inserir(Regiao regiao) {
        String sql = "INSERT INTO regiao (nome, cidade, estado, indice_desenvolvimento) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, regiao.getNome());
            stmt.setString(2, regiao.getCidade());
            stmt.setString(3, regiao.getEstado());
            stmt.setDouble(4, regiao.getIndiceDesenvolvimento());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir região: " + e.getMessage());
            return false;
        }
    }

    public List<Regiao> listar() {
        List<Regiao> regioes = new ArrayList<>();
        String sql = "SELECT * FROM regiao ORDER BY nome";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                regioes.add(new Regiao(
                        rs.getInt("id_regiao"),
                        rs.getString("nome"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getDouble("indice_desenvolvimento")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar regiões: " + e.getMessage());
        }
        return regioes;
    }

    public Regiao buscarPorId(int id) {
        String sql = "SELECT * FROM regiao WHERE id_regiao = ?";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Regiao(
                        rs.getInt("id_regiao"),
                        rs.getString("nome"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getDouble("indice_desenvolvimento")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar região: " + e.getMessage());
        }
        return null;
    }
}
