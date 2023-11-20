package com.projeto.projetosistema.DAO.deployment;

import com.projeto.projetosistema.DAO.DAOInterface;
import com.projeto.projetosistema.DAO.ErroDAO;
import com.projeto.projetosistema.DAO.FabricaConexao;
import com.projeto.projetosistema.model.Clinte;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ClienteDAO implements DAOInterface<Clinte> {
    private Connection con;

    public ClienteDAO() throws ErroDAO {
        this.con = FabricaConexao.pegaConexao();
    }

    @Override
    public void inserir(Clinte obj) throws ErroDAO {

    }

    @Override
    public void deletar(Clinte obj) throws ErroDAO {

    }

    @Override
    public void deletar(int id) throws ErroDAO {

    }

    @Override
    public void editar(Clinte ogj) throws ErroDAO {

    }

    @Override
    public Clinte buscar(int id) throws ErroDAO {
        return null;
    }

    @Override
    public List<Clinte> buscar() throws ErroDAO {
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
