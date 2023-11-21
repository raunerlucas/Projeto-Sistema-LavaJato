package com.projeto.projetosistema.DAO.deployment;

import com.projeto.projetosistema.DAO.DAOInterface;
import com.projeto.projetosistema.DAO.ErroDAO;
import com.projeto.projetosistema.DAO.FabricaConexao;
import com.projeto.projetosistema.model.Endereco;
import com.projeto.projetosistema.model.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;

public class FuncionarioDAO implements DAOInterface<Funcionario> {
    private Connection con;

    public FuncionarioDAO() throws ErroDAO {
        this.con = FabricaConexao.pegaConexao();
    }

    @Override
    public void inserir(Funcionario obj) throws ErroDAO {
        try {
            PreparedStatement stm = con.prepareStatement("INSERT INTO Funcionario " +
                    "(id_endereco, nome, sobrenome, cpf, telefone, login, senha, admin, email)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);", 1);
            stm.setInt(1, obj.getEndereco().getId());
            stm.setString(2, obj.getNome());
            stm.setString(3, obj.getSobrenome());
            stm.setString(4, obj.getCPF());
            stm.setString(5, obj.getTelefone());
            stm.setString(6, obj.getLogin());
            stm.setString(7, obj.getSenha());
            stm.setBoolean(8, obj.isAdmin());
            stm.setString(9, obj.getEmail());
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
    public void deletar(Funcionario obj) throws ErroDAO {
        deletar(obj.getId());
    }

    @Override
    public void deletar(int id) throws ErroDAO {
        try {
            PreparedStatement stm = con.prepareStatement("DELETE FROM Funcionario " +
                    "WHERE id = ?;");
            stm.setInt(1, id);
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }
    }

    @Override
    public void editar(Funcionario obj) throws ErroDAO {
        try {
//            (id_endereco, nome, sobrenome, cpf, telefone, login, senha, admin, email)
            PreparedStatement stm = con.prepareStatement("UPDATE Funcionario " +
                    "SET id_endereco = ?, nome = ?, sobrenome = ?, cpf = ?, telefone = ?, login = ?, senha = ?, admin = ?, email = ? " +
                    "WHERE id = ?;");
            stm.setInt(1, obj.getEndereco().getId());
            stm.setString(2, obj.getNome());
            stm.setString(3, obj.getSobrenome());
            stm.setString(4, obj.getCPF());
            stm.setString(5, obj.getTelefone());
            stm.setString(6, obj.getLogin());
            stm.setString(7, obj.getSenha());
            stm.setBoolean(8, obj.isAdmin());
            stm.setString(9, obj.getEmail());
            stm.setInt(10, obj.getId());
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }

    }

    @Override
    public Funcionario buscar(int id) throws ErroDAO {
        Funcionario funcionarios = null;
        try {
//        id_endereco, nome, sobrenome, cpf, telefone, login, senha, admin, email
            PreparedStatement stm = con.prepareStatement("SELECT * FROM Funcionario;");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                DAOInterface<Endereco> dao = new EnderecoDAO();
                Endereco endereco = dao.buscar(rs.getInt("id_endereco"));
                dao.close();

                funcionarios = new Funcionario(rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("login"),
                        rs.getString("senha"),
                        rs.getString("email"),
                        rs.getBoolean("admin"),
                        endereco
                );
            }
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }
        return funcionarios;
    }

    @Override
    public List<Funcionario> buscar() throws ErroDAO {
        List<Funcionario> funcionarios = new ArrayList<>();
        try {
//        id_endereco, nome, sobrenome, cpf, telefone, login, senha, admin, email
            PreparedStatement stm = con.prepareStatement("SELECT * FROM Funcionario;");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                DAOInterface<Endereco> dao = new EnderecoDAO();
                Endereco endereco = dao.buscar(rs.getInt("id_endereco"));
                dao.close();

                funcionarios.add(new Funcionario(rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("login"),
                        rs.getString("senha"),
                        rs.getString("email"),
                        rs.getBoolean("admin"),
                        endereco
                ));
            }
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }
        return funcionarios;
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
