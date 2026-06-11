package com.vacinacao.dao;

import com.vacinacao.model.Escolaridade;
import com.vacinacao.util.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EscolaridadeDAO {

    public boolean inserir(Escolaridade esc) {
        String sql = "INSERT INTO escolaridade (descricao) VALUES (?)";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, esc.getDescricao());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir escolaridade: " + e.getMessage());
            return false;
        }
    }

    public List<Escolaridade> listar() {
        List<Escolaridade> lista = new ArrayList<>();
        String sql = "SELECT * FROM escolaridade ORDER BY id_escolaridade";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Escolaridade(
                        rs.getInt("id_escolaridade"),
                        rs.getString("descricao")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar escolaridades: " + e.getMessage());
        }
        return lista;
    }

    public Escolaridade buscarPorId(int id) {
        String sql = "SELECT * FROM escolaridade WHERE id_escolaridade = ?";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Escolaridade(rs.getInt("id_escolaridade"), rs.getString("descricao"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar escolaridade: " + e.getMessage());
        }
        return null;
    }
}
