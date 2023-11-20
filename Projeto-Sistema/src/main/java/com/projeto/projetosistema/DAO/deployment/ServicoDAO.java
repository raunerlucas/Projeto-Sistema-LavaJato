package com.projeto.projetosistema.DAO.deployment;

import com.projeto.projetosistema.DAO.DAOInterface;
import com.projeto.projetosistema.DAO.ErroDAO;
import com.projeto.projetosistema.DAO.FabricaConexao;
import com.projeto.projetosistema.model.Servico;

import java.sql.Connection;
import java.util.List;

public class ServicoDAO implements DAOInterface<Servico> {
    private Connection con;
    public ServicoDAO() throws ErroDAO {
        con = FabricaConexao.pegaConexao();
    }

    @Override
    public void inserir(Servico obj) throws ErroDAO {

    }

    @Override
    public void deletar(Servico obj) throws ErroDAO {

    }

    @Override
    public void deletar(int id) throws ErroDAO {

    }

    @Override
    public void editar(Servico ogj) throws ErroDAO {

    }

    @Override
    public Servico buscar(int id) throws ErroDAO {
        return null;
    }

    @Override
    public List<Servico> buscar() throws ErroDAO {
        return null;
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
