package com.projeto.projetosistema.DAO.deployment;

import com.projeto.projetosistema.DAO.DAOInterface;
import com.projeto.projetosistema.DAO.ErroDAO;
import com.projeto.projetosistema.DAO.FabricaConexao;
import com.projeto.projetosistema.model.Clinte;
import com.projeto.projetosistema.model.Endereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements DAOInterface<Clinte> {
    private Connection con;

    public ClienteDAO() throws ErroDAO {
        this.con = FabricaConexao.pegaConexao();
    }

    @Override
    public void inserir(Clinte obj) throws ErroDAO {
        try {
            PreparedStatement stm = con.prepareStatement("INSERT INTO Cliente " +
                    "(id_endereco, nome, sobrenome, telefone, cpf, login, senha, email) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?);",1);
            stm.setInt(1,obj.getEndereco().getId());
            stm.setString(2,obj.getNome());
            stm.setString(3,obj.getSobrenome());
            stm.setString(4,obj.getTelefone());
            stm.setString(5,obj.getCPF());
            stm.setString(6,obj.getLogin());
            stm.setString(7,obj.getSenha());
            stm.setString(8,obj.getEmail());
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
    public void deletar(Clinte obj) throws ErroDAO {
        deletar(obj.getId());
    }

    @Override
    public void deletar(int id) throws ErroDAO {
        try {
            PreparedStatement stm = con.prepareStatement("DELETE FROM Cliente WHERE id = ?;");
            stm.setInt(1,id);
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }
    }

    @Override
    public void editar(Clinte obj) throws ErroDAO {
        try {
            //(id_endereco, nome, sobrenome, telefone, cpf, login, senha, email)
            PreparedStatement stm = con.prepareStatement("UPDATE Cliente " +
                    "SET id_endereco = ?, nome = ?, sobrenome = ?, telefone = ?, cpf = ?, login = ?, senha = ?, email = ? " +
                    "WHERE id = ?;");
            stm.setInt(1, obj.getEndereco().getId());
            stm.setString(2, obj.getNome());
            stm.setString(3, obj.getSobrenome());
            stm.setString(4, obj.getTelefone());
            stm.setString(5, obj.getCPF());
            stm.setString(6, obj.getLogin());
            stm.setString(7, obj.getSenha());
            stm.setString(8, obj.getEmail());
            stm.setInt(9, obj.getId());
            stm.close();
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }

    }

    @Override
    public Clinte buscar(int id) throws ErroDAO {
        Clinte clintes = null;
        try {
//            (id_endereco, nome, sobrenome, telefone, cpf, login, senha, email)
            PreparedStatement stm = con.prepareStatement("SELECT * FROM Cliente WHERE id =?;");
            stm.setInt(1,id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()){
                DAOInterface<Endereco> dao = new EnderecoDAO();
                Endereco endereco = dao.buscar(rs.getInt("id_endereco"));
                dao.close();

                clintes = new Clinte(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        rs.getString("telefone"),
                        rs.getString("cpf"),
                        rs.getString("login"),
                        rs.getString("senha"),
                        rs.getString("email"),
                        endereco
                );
            }
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }
        return clintes;
    }

    @Override
    public List<Clinte> buscar() throws ErroDAO {
        List<Clinte> clintes = new ArrayList<>();
        try {
//            (id_endereco, nome, sobrenome, telefone, cpf, login, senha, email)
            PreparedStatement stm = con.prepareStatement("SELECT * FROM Cliente");
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                DAOInterface<Endereco> dao = new EnderecoDAO();
                Endereco endereco = dao.buscar(rs.getInt("id_endereco"));
                dao.close();

                clintes.add(new Clinte(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        rs.getString("telefone"),
                        rs.getString("cpf"),
                        rs.getString("login"),
                        rs.getString("senha"),
                        rs.getString("email"),
                        endereco
                ));
            }
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }
        return clintes;
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
