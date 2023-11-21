package com.projeto.projetosistema.DAO.deployment;

import com.projeto.projetosistema.DAO.DAOInterface;
import com.projeto.projetosistema.DAO.ErroDAO;
import com.projeto.projetosistema.DAO.FabricaConexao;
import com.projeto.projetosistema.model.Empresa;
import com.projeto.projetosistema.model.Endereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDAO implements DAOInterface<Empresa> {

    private Connection con;

    public EmpresaDAO() throws ErroDAO {
        this.con = FabricaConexao.pegaConexao();
    }

    @Override
    public void inserir(Empresa obj) throws ErroDAO {
        try {
            PreparedStatement stm = con.prepareStatement("INSERT INTO Empresa(nomeFicticio, telefone, CNPJ, email, id_endereco) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, obj.getNomeFicticio());
            stm.setString(2, obj.getTelefone());
            stm.setString(3, obj.getCNPJ());
            stm.setString(4, obj.getEmail());
            stm.setInt(5, obj.getEndereco().getId());
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
    public void deletar(Empresa obj) throws ErroDAO {
        deletar(obj.getId());
    }

    @Override
    public void deletar(int id) throws ErroDAO {
        try {
            PreparedStatement stm = con.prepareStatement("DELETE FROM Empresa WHERE id = ?");
            stm.setInt(1,id);
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }
    }

    @Override
    public void editar(Empresa ogj) throws ErroDAO {
        try {
            PreparedStatement stm = con.prepareStatement("UPDATE Empresa SET nomeFicticio = ?, telefone = ?, CNPJ = ?, email = ?, id_endereco = ? WHERE id = ?");
            stm.setString(1, ogj.getNomeFicticio());
            stm.setString(2, ogj.getTelefone());
            stm.setString(3, ogj.getCNPJ());
            stm.setString(4, ogj.getEmail());
            stm.setInt(5,ogj.getEndereco().getId());
            stm.setInt(6,ogj.getId());
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }
    }

    @Override
    public Empresa buscar(int id) throws ErroDAO {
        Empresa empresa = null;
        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM Empresa WHERE id = ?;");
            ResultSet rs = stm.executeQuery();
            //nomeFicticio, telefone, CNPJ, email, id_endereco
            if (rs.next()){
                DAOInterface<Endereco> dao = new EnderecoDAO();
                Endereco enderecoDao = dao.buscar(rs.getInt("id_endereco"));
                dao.close();

                empresa = new Empresa(rs.getInt("id"),
                        rs.getString("nomeFicticio"),
                        rs.getString("telefone"),
                        rs.getString("CNPJ"),
                        rs.getString("email"),
                        enderecoDao);
            }
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }
        return empresa;
    }

    @Override
    public List<Empresa> buscar() throws ErroDAO {
        List<Empresa> empresas = new ArrayList<Empresa>();
        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM Empresa;");
            ResultSet rs = stm.executeQuery();
            //nomeFicticio, telefone, CNPJ, email, id_endereco
            while (rs.next()){
                DAOInterface<Endereco> dao = new EnderecoDAO();
                Endereco enderecoDao = dao.buscar(rs.getInt("id_endereco"));
                dao.close();

                empresas.add(new Empresa(rs.getInt("id"),
                        rs.getString("nomeFicticio"),
                        rs.getString("telefone"),
                        rs.getString("CNPJ"),
                        rs.getString("email"),
                        enderecoDao));
            }
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }
        return empresas;
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
