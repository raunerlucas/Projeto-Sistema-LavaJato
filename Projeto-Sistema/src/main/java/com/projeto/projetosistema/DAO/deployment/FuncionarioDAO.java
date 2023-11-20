package com.projeto.projetosistema.DAO.deployment;

import com.projeto.projetosistema.DAO.DAOInterface;
import com.projeto.projetosistema.DAO.ErroDAO;
import com.projeto.projetosistema.DAO.FabricaConexao;
import com.projeto.projetosistema.model.Funcionario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioDAO implements DAOInterface<Funcionario> {
    private Connection con;

    public FuncionarioDAO() throws ErroDAO {
        this.con = FabricaConexao.pegaConexao();
    }

    @Override
    public void inserir(Funcionario obj) throws ErroDAO {

    }

    @Override
    public void deletar(Funcionario obj) throws ErroDAO {

    }

    @Override
    public void deletar(int id) throws ErroDAO {

    }

    @Override
    public void editar(Funcionario ogj) throws ErroDAO {

    }

    @Override
    public Funcionario buscar(int id) throws ErroDAO {
        return null;
    }

    @Override
    public List<Funcionario> buscar() throws ErroDAO {
        return null;
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
