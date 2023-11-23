package com.projeto.projetosistema.DAO.deployment;

import com.projeto.projetosistema.DAO.DAOInterface;
import com.projeto.projetosistema.DAO.ErroDAO;
import com.projeto.projetosistema.DAO.FabricaConexao;
import com.projeto.projetosistema.model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdemServicoDAO implements DAOInterface<OrdemServico> {

    private Connection con;

    public OrdemServicoDAO() throws ErroDAO {
        this.con = FabricaConexao.pegaConexao();
    }

    @Override
    public void inserir(OrdemServico obj) throws ErroDAO {
        // Setar a empresa pelo contesto da aplicacao
        try {
            PreparedStatement stm = con.prepareStatement("INSERT INTO OrdemServico " +
                    "(id_funcionario, id_cliente, id_empresa, numOS, status, observacao, entregar, servicosOrdem, dataEmissao, previsaoTermino, ValorTotal)\n" +
                    "VALUES (? , ?, ?, ?, ?, ?, ?, ?::jsonb, ?, ?, ?);", 1);
            stm.setInt(1, obj.getFuncionario().getId());
            stm.setInt(2, obj.getClinte().getId());
            stm.setInt(3, obj.getEmpresa().getId());
            stm.setInt(4, obj.getNumeroOS());
            stm.setString(5, obj.getStatus().name());
            stm.setString(6, obj.getDescricao());
            stm.setBoolean(7, obj.isEntregar());
            stm.setString(8, obj.jsonCreate());
            stm.setDate(9, obj.getDataEmissao());
            stm.setDate(10, obj.getPrevisaoTermino());
            stm.setFloat(11, obj.getValorTotal());
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
    public void deletar(OrdemServico obj) throws ErroDAO {
        deletar(obj.getId());
    }

    @Override
    public void deletar(int id) throws ErroDAO {
        try {
            PreparedStatement stm = con.prepareStatement("DELETE FROM OrdemServico " +
                    "WHERE id = ?;");
            stm.setInt(1, id);
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }
    }

    @Override
    public void editar(OrdemServico obj) throws ErroDAO {
        try {
            PreparedStatement stm = con.prepareStatement("UPDATE OrdemServico " +
                    "SET  id_funcionario = ?, id_cliente = ?, id_empresa = ?, numOS = ?, status = ?, observacao = ?," +
                    " entregar = ?, servicosOrdem  = ?::jsonb, dataEmissao = ?, previsaoTermino = ?, ValorTotal = ? " +
                    "WHERE id = ?;");
            stm.setInt(1, obj.getFuncionario().getId());
            stm.setInt(2, obj.getClinte().getId());
            stm.setInt(3, obj.getEmpresa().getId());
            stm.setInt(4, obj.getNumeroOS());
            stm.setString(5, obj.getStatus().name());
            stm.setString(6, obj.getDescricao());
            stm.setBoolean(7, obj.isEntregar());
            stm.setString(8, obj.jsonCreate());
            stm.setDate(9, obj.getDataEmissao());
            stm.setDate(10, obj.getPrevisaoTermino());
            stm.setFloat(11, obj.getValorTotal());
            stm.setFloat(12, obj.getId());
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }

    }

    @Override
    public OrdemServico buscar(int id) throws ErroDAO {
        OrdemServico ordemServicos = null;
        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM OrdemServico WHERE id = ?;");
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
//            Pegar a empra pelo contesto da aplicacao
            if (rs.next()) {
                DAOInterface<Funcionario> daoF = new FuncionarioDAO();
                Funcionario funcionario = daoF.buscar(rs.getInt("id_funcionario"));
                daoF.close();

                DAOInterface<Cliente> daoC = new ClienteDAO();
                Cliente cliente = daoC.buscar(rs.getInt("id_cliente"));
                daoC.close();

                List<Servico> servicosDaOrdem = new ArrayList<>();
                DAOInterface<Servico> daoS = new ServicoDAO();

                // Obtenha a lista de serviços a partir do JSONB
                JSONArray jsonServicos = new JSONArray(rs.getString("servicosOrdem"));
                for (int i = 0; i < jsonServicos.length(); i++) {
                    JSONObject jsonServico = jsonServicos.getJSONObject(i);
                    int idServico = jsonServico.getInt("id_servico");
                    Servico servico = daoS.buscar(idServico);
                    servicosDaOrdem.add(servico);
                }
                daoS.close();

                ordemServicos = new OrdemServico(rs.getInt("id"),
                        rs.getInt("numOS"),
                        rs.getDate("dataEmissao"),
                        rs.getDate("previsaoTermino"),
                        rs.getBoolean("entregar"),
                        Status.valueOf(rs.getString("status")),
                        rs.getString("observacao"),
                        rs.getFloat("ValorTotal"),
                        funcionario,
                        cliente,
                        null,
                        servicosDaOrdem);
            }
            stm.close();
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }
        return ordemServicos;
    }

    @Override
    public List<OrdemServico> buscar() throws ErroDAO {
        List<OrdemServico> ordemServicos = new ArrayList<>();
        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM OrdemServico;");
            ResultSet rs = stm.executeQuery();
//            Pegar a empra pelo contesto da aplicacao
            while (rs.next()) {
                DAOInterface<Funcionario> daoF = new FuncionarioDAO();
                Funcionario funcionario = daoF.buscar(rs.getInt("id_funcionario"));
                daoF.close();

                DAOInterface<Cliente> daoC = new ClienteDAO();
                Cliente cliente = daoC.buscar(rs.getInt("id_cliente"));
                daoC.close();

                List<Servico> servicosDaOrdem = new ArrayList<>();
                DAOInterface<Servico> daoS = new ServicoDAO();

                // Obtenha a lista de serviços a partir do JSONB
                JSONArray jsonServicos = new JSONArray(rs.getString("servicosOrdem"));
                for (int i = 0; i < jsonServicos.length(); i++) {
                    JSONObject jsonServico = jsonServicos.getJSONObject(i);
                    int idServico = jsonServico.getInt("id_servico");
                    Servico servico = daoS.buscar(idServico);
                    servicosDaOrdem.add(servico);
                }
                daoS.close();

                ordemServicos.add(new OrdemServico(rs.getInt("id"),
                        rs.getInt("numOS"),
                        rs.getDate("dataEmissao"),
                        rs.getDate("previsaoTermino"),
                        rs.getBoolean("entregar"),
                        Status.valueOf(rs.getString("status")),
                        rs.getString("observacao"),
                        rs.getFloat("ValorTotal"),
                        funcionario,
                        cliente,
                        null,
                        servicosDaOrdem));
            }
            stm.close();
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }
        return ordemServicos;
    }

    public List<OrdemServico> buscarPorCliente(Cliente obj) throws ErroDAO {
        List<OrdemServico> ordensServico = new ArrayList<>();

        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM OrdemServico WHERE id_cliente = ?;");
            stm.setInt(1, obj.getId());
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                DAOInterface<Funcionario> daoF = new FuncionarioDAO();
                Funcionario funcionario = daoF.buscar(rs.getInt("id_funcionario"));
                daoF.close();

                List<Servico> servicosDaOrdem = new ArrayList<>();
                DAOInterface<Servico> daoS = new ServicoDAO();

                // Obtenha a lista de serviços a partir do JSONB
                JSONArray jsonServicos = new JSONArray(rs.getString("servicosOrdem"));
                for (int i = 0; i < jsonServicos.length(); i++) {
                    JSONObject jsonServico = jsonServicos.getJSONObject(i);
                    int idServico = jsonServico.getInt("id_servico");
                    Servico servico = daoS.buscar(idServico);
                    servicosDaOrdem.add(servico);
                }
                daoS.close();

                OrdemServico ordemServico = new OrdemServico(
                        rs.getInt("id"),
                        rs.getInt("numOS"),
                        rs.getDate("dataEmissao"),
                        rs.getDate("previsaoTermino"),
                        rs.getBoolean("entregar"),
                        Status.valueOf(rs.getString("status")),
                        rs.getString("observacao"),
                        rs.getFloat("ValorTotal"),
                        funcionario,
                        obj,
                        null,
                        servicosDaOrdem
                );

                ordensServico.add(ordemServico);
            }
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }

        return ordensServico;
    }

    public List<OrdemServico> buscarPorFuncionario(Funcionario obj) throws ErroDAO {
        List<OrdemServico> ordensServico = new ArrayList<>();

        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM OrdemServico WHERE id_funcionario = ?;");
            stm.setInt(1, obj.getId());
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                DAOInterface<Cliente> daoC = new ClienteDAO();
                Cliente cliente = daoC.buscar(rs.getInt("id_cliente"));
                daoC.close();

                List<Servico> servicosDaOrdem = new ArrayList<>();
                DAOInterface<Servico> daoS = new ServicoDAO();

                JSONArray jsonServicos = new JSONArray(rs.getString("servicosOrdem"));
                for (int i = 0; i < jsonServicos.length(); i++) {
                    JSONObject jsonServico = jsonServicos.getJSONObject(i);
                    int idServico = jsonServico.getInt("id_servico");
                    Servico servico = daoS.buscar(idServico);
                    servicosDaOrdem.add(servico);
                }
                daoS.close();

                OrdemServico ordemServico = new OrdemServico(
                        rs.getInt("id"),
                        rs.getInt("numOS"),
                        rs.getDate("dataEmissao"),
                        rs.getDate("previsaoTermino"),
                        rs.getBoolean("entregar"),
                        Status.valueOf(rs.getString("status")),
                        rs.getString("observacao"),
                        rs.getFloat("ValorTotal"),
                        obj,
                        cliente,
                        null,
                        servicosDaOrdem
                );

                ordensServico.add(ordemServico);
            }
        } catch (SQLException e) {
            throw new ErroDAO(e);
        }

        return ordensServico;
    }

    @Override
    public void close() throws ErroDAO {

    }
}
