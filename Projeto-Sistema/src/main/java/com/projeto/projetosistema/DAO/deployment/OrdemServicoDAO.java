package com.projeto.projetosistema.DAO.deployment;

import com.projeto.projetosistema.DAO.DAOInterface;
import com.projeto.projetosistema.DAO.ErroDAO;
import com.projeto.projetosistema.DAO.FabricaConexao;
import com.projeto.projetosistema.model.OrdemServico;

import java.sql.Connection;
import java.util.List;

public class OrdemServicoDAO implements DAOInterface<OrdemServico> {

    private Connection con;

    public OrdemServicoDAO() throws ErroDAO {
        this.con = FabricaConexao.pegaConexao();
    }

    @Override
    public void inserir(OrdemServico obj) throws ErroDAO {

    }

    @Override
    public void deletar(OrdemServico obj) throws ErroDAO {

    }

    @Override
    public void deletar(int id) throws ErroDAO {

    }

    @Override
    public void editar(OrdemServico ogj) throws ErroDAO {

    }

    @Override
    public OrdemServico buscar(int id) throws ErroDAO {
        return null;
    }

    @Override
    public List<OrdemServico> buscar() throws ErroDAO {
        return null;
    }

    @Override
    public void close() throws ErroDAO {

    }
}
