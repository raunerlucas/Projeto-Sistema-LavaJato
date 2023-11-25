package com.projeto.projetosistema.DAO.deployment;

import com.projeto.projetosistema.DAO.DAOInterface;
import com.projeto.projetosistema.DAO.ErroDAO;
import com.projeto.projetosistema.DAO.FabricaConexao;
import com.projeto.projetosistema.model.Servico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO implements DAOInterface<Servico> {
    private Connection con;

    public ServicoDAO() throws ErroDAO {
        con = FabricaConexao.pegaConexao();
    }

    @Override
    public void inserir(Servico obj) throws ErroDAO {
        try {
            PreparedStatement stm = con.prepareStatement("INSERT INTO Servico(descricao,preco) values (?,?)", Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, obj.getDescricao());
            stm.setFloat(2, obj.getPreco());
            stm.executeUpdate();
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                obj.setId(rs.getInt(1));
            }
            stm.close();
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }

    }

    @Override
    public void deletar(Servico obj) throws ErroDAO {
        deletar(obj.getId());
    }

    @Override
    public void deletar(int id) throws ErroDAO {
        try {
            PreparedStatement stm = con.prepareStatement("DELETE FROM Servico WHERE id = ?");
            stm.setInt(1, id);
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }
    }

    @Override
    public void editar(Servico obj) throws ErroDAO {
        try {
            PreparedStatement stm = con.prepareStatement("UPDATE Servico SET descricao = ?, preco = ? WHERE id = ?");
            stm.setString(1, obj.getDescricao());
            stm.setFloat(2, obj.getPreco());
            stm.setInt(3, obj.getId());
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }
    }

    @Override
    public Servico buscar(int id) throws ErroDAO {
        Servico sv = null;
        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM Servico WHERE id = ?");
            stm.setInt(1,id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                sv = new Servico(rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getFloat("preco"));
            }
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }
        return sv;
    }

    public Servico buscar(Servico sBuscar) throws ErroDAO {
        Servico sv = null;
        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM Servico WHERE descricao like ?");
            stm.setString(1,sBuscar.getDescricao());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                sv = new Servico(rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getFloat("preco"));
            }
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }
        return sv;
    }

    @Override
    public List<Servico> buscar() throws ErroDAO {
        List<Servico> servicoList = new ArrayList<>();
        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM Servico");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                servicoList.add(new Servico(rs.getInt(1),
                        rs.getString(2),
                        rs.getFloat(3)));
            }
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }
        return servicoList;
    }

    @Override
    public void close() throws ErroDAO {
        try {
            con.close();
        } catch (Exception e) {
            throw new ErroDAO(e);
        }
    }
}
