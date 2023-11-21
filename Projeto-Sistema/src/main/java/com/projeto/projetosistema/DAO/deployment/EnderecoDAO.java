package com.projeto.projetosistema.DAO.deployment;

import com.projeto.projetosistema.DAO.DAOInterface;
import com.projeto.projetosistema.DAO.ErroDAO;
import com.projeto.projetosistema.DAO.FabricaConexao;
import com.projeto.projetosistema.model.Endereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO implements DAOInterface<Endereco> {
    private Connection con;

    public EnderecoDAO() throws ErroDAO {
        this.con = FabricaConexao.pegaConexao();
    }

    @Override
    public void inserir(Endereco obj) throws ErroDAO {
        try {
            PreparedStatement stm = con.prepareStatement("INSERT INTO Endereco (cep, logradouro, numero, complemento, bairro, cidade, estado) VALUES (?, ?, ?, ?, ?, ?,?);", Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, obj.getCEP());
            stm.setString(2, obj.getLogradouro());
            stm.setString(3, obj.getComplemento());
            stm.setString(4, obj.getBairro());
            stm.setString(5, obj.getCidade());
            stm.setString(6, obj.getEstado());
            stm.executeUpdate();
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()){
                obj.setId(rs.getInt("id"));
            }
            stm.close();
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }

    }

    @Override
    public void deletar(Endereco obj) throws ErroDAO {
        deletar(obj.getId());
    }

    @Override
    public void deletar(int id) throws ErroDAO {
        try {
            PreparedStatement stm = con.prepareStatement("DELETE FROM Endereco WHERE id = ?");
            stm.setInt(1,id);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editar(Endereco ogj) throws ErroDAO {
        try {
            PreparedStatement stm = con.prepareStatement("UPDATE Endereco SET cep = ?, logradouro = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, estado = ?  WHERE id = ?;");
            stm.setInt(1,ogj.getCEP());
            stm.setString(2,ogj.getLogradouro());
            stm.setInt(3,ogj.getNumero());
            stm.setString(4,ogj.getComplemento());
            stm.setString(5,ogj.getBairro());
            stm.setString(6,ogj.getCidade());
            stm.setString(7,ogj.getEstado());
            stm.setInt(8,ogj.getId());
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Endereco buscar(int id) throws ErroDAO {
        Endereco enderecos = null;
        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM Endereco WHERE id = ?;");
            stm.setInt(1,id);
            ResultSet rs = stm.executeQuery();
            //(cep, logradouro, numero, complemento, bairro, cidade, estado)
            while (rs.next()){
                enderecos = new Endereco(rs.getInt("id"),
                        rs.getInt("cep"),
                        rs.getInt("numero"),
                        rs.getString("logradouro"),
                        rs.getString("complemento"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado")
                );
            }
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }
        return enderecos;
    }

    @Override
    public List<Endereco> buscar() throws ErroDAO {
        List<Endereco> enderecos = new ArrayList<>();
        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM Endereco;");
            ResultSet rs = stm.executeQuery();
            //(cep, logradouro, numero, complemento, bairro, cidade, estado)
            while (rs.next()){
                enderecos.add(new Endereco(rs.getInt("id"),
                        rs.getInt("cep"),
                        rs.getInt("numero"),
                        rs.getString("logradouro"),
                        rs.getString("complemento"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado")
                ));
            }
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }
        return enderecos;
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
