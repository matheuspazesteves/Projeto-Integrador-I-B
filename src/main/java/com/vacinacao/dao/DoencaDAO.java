package com.vacinacao.dao;

import com.vacinacao.model.Doenca;
import com.vacinacao.util.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoencaDAO {

    public boolean inserir(Doenca doenca) {
        String sql = "INSERT INTO doenca (nome, descricao, gravidade) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, doenca.getNome());
            stmt.setString(2, doenca.getDescricao());
            stmt.setString(3, doenca.getGravidade().name());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir doença: " + e.getMessage());
            return false;
        }
    }

    public List<Doenca> listar() {
        List<Doenca> lista = new ArrayList<>();
        String sql = "SELECT * FROM doenca ORDER BY nome";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Doenca(
                        rs.getInt("id_doenca"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        Doenca.Gravidade.valueOf(rs.getString("gravidade"))
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar doenças: " + e.getMessage());
        }
        return lista;
    }
}
