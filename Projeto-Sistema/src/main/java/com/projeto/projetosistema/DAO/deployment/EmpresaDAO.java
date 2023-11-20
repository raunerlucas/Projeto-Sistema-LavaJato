package com.projeto.projetosistema.DAO.deployment;

import com.projeto.projetosistema.DAO.DAOInterface;
import com.projeto.projetosistema.DAO.ErroDAO;
import com.projeto.projetosistema.DAO.FabricaConexao;
import com.projeto.projetosistema.model.Empresa;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EmpresaDAO implements DAOInterface<Empresa> {

    private Connection con;

    public EmpresaDAO() throws ErroDAO {
        this.con = FabricaConexao.pegaConexao();
    }

    @Override
    public void inserir(Empresa obj) throws ErroDAO {

    }

    @Override
    public void deletar(Empresa obj) throws ErroDAO {

    }

    @Override
    public void deletar(int id) throws ErroDAO {

    }

    @Override
    public void editar(Empresa ogj) throws ErroDAO {

    }

    @Override
    public Empresa buscar(int id) throws ErroDAO {
        return null;
    }

    @Override
    public List<Empresa> buscar() throws ErroDAO {
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
