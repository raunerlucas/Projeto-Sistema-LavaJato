package com.projeto.projetosistema.DAO.deployment;

import com.projeto.projetosistema.DAO.DAOInterface;
import com.projeto.projetosistema.DAO.ErroDAO;
import com.projeto.projetosistema.DAO.FabricaConexao;
import com.projeto.projetosistema.model.Endereco;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EnderecoDAO implements DAOInterface<Endereco> {
    private Connection con;

    public EnderecoDAO() throws ErroDAO {
        this.con = FabricaConexao.pegaConexao();
    }

    @Override
    public void inserir(Endereco obj) throws ErroDAO {

    }

    @Override
    public void deletar(Endereco obj) throws ErroDAO {

    }

    @Override
    public void deletar(int id) throws ErroDAO {

    }

    @Override
    public void editar(Endereco ogj) throws ErroDAO {

    }

    @Override
    public Endereco buscar(int id) throws ErroDAO {
        return null;
    }

    @Override
    public List<Endereco> buscar() throws ErroDAO {
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
