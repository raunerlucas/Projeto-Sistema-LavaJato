package com.projeto.projetosistema.DAO.deployment;

import com.projeto.projetosistema.DAO.DAOInterface;
import com.projeto.projetosistema.DAO.ErroDAO;
import com.projeto.projetosistema.DAO.FabricaConexao;
import com.projeto.projetosistema.model.Veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO implements DAOInterface<Veiculo> {

    private Connection con;

    public VeiculoDAO() throws ErroDAO {
        con = FabricaConexao.pegaConexao();
    }

    @Override
    public void inserir(Veiculo obj) throws ErroDAO {
        try {
            PreparedStatement stm = con.prepareStatement("INSERT INTO veiculo (placa, modelo, tipo, cor) " +
                    "VALUES (?, ?, ?, ?)", 1);
            stm.setString(1, obj.getPlaca());
            stm.setString(2, obj.getModelo());
            stm.setString(3, obj.getTipo());
            stm.setString(4, obj.getCor());
            stm.executeUpdate();
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                obj.setId(rs.getInt("id"));
            }
            stm.close();
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }
    }

    @Override
    public void deletar(Veiculo obj) throws ErroDAO {
        deletar(obj.getId());
    }

    @Override
    public void deletar(int id) throws ErroDAO {
        try {
            PreparedStatement stm = con.prepareStatement("DELETE FROM veiculo " +
                    "WHERE id = ?");
            stm.setInt(1, id);
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }

    }

    @Override
    public void editar(Veiculo obj) throws ErroDAO {
        try {
            PreparedStatement stm = con.prepareStatement("UPDATE veiculo " +
                    "SET placa = ?, modelo = ?, tipo = ?, cor = ? " +
                    "WHERE id = ?;");
            stm.setString(1, obj.getPlaca());
            stm.setString(2, obj.getModelo());
            stm.setString(3, obj.getTipo());
            stm.setString(4, obj.getCor());
            stm.setInt(5, obj.getId());
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }

    }

    @Override
    public Veiculo buscar(int id) throws ErroDAO {
        Veiculo v = null;
        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM veiculo where id = ?");
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                v = new Veiculo(rs.getInt("id"), rs.getString("placa"),
                        rs.getString("modelo"), rs.getString("tipo"), rs.getString("cor"));
            }
            stm.close();
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }
        return v;
    }

    public Veiculo buscar(String placa) throws ErroDAO {
        Veiculo v = null;
        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM veiculo where placa = ?");
            stm.setString(1, placa);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                v = new Veiculo(rs.getInt("id"), rs.getString("placa"),
                        rs.getString("modelo"), rs.getString("tipo"), rs.getString("cor"));
            }
            stm.close();
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }
        return v;
    }

    public List<Veiculo> buscarTipo(String tipo) throws ErroDAO {
        List<Veiculo> v = new ArrayList<>();
        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM veiculo where tipo = ?");
            stm.setString(1, tipo);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                v.add(new Veiculo(rs.getInt("id"), rs.getString("placa"),
                        rs.getString("modelo"), rs.getString("tipo"), rs.getString("cor")));
            }
            stm.close();
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }
        return v;
    }

    @Override
    public List<Veiculo> buscar() throws ErroDAO {
        List<Veiculo> v = new ArrayList<>();
        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM veiculo ");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                v.add(new Veiculo(rs.getInt("id"), rs.getString("placa"),
                        rs.getString("modelo"), rs.getString("tipo"), rs.getString("cor")));
            }
            stm.close();
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }
        return v;
    }

    @Override
    public void close() throws ErroDAO {
        try {
            con.close();
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }
    }
}
