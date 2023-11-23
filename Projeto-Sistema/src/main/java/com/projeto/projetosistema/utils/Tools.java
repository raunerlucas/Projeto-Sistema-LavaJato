package com.projeto.projetosistema.utils;

import com.projeto.projetosistema.DAO.ErroDAO;
import com.projeto.projetosistema.DAO.deployment.ClienteDAO;
import com.projeto.projetosistema.DAO.deployment.EnderecoDAO;
import com.projeto.projetosistema.DAO.deployment.FuncionarioDAO;
import com.projeto.projetosistema.model.Cliente;
import com.projeto.projetosistema.model.Endereco;
import com.projeto.projetosistema.model.Funcionario;

import java.util.List;

public class Tools {
    public static boolean validaValor(String obj) {
        if (obj != null && !obj.isBlank() && !obj.isEmpty())
            return true;
        else
            return false;
    }

    public static Funcionario validaFuncionario(String login, String senha) throws ErroDAO {
        Funcionario funcionario = null;
        try (FuncionarioDAO dao = new FuncionarioDAO()) {
            List<Funcionario> funcionarios = dao.buscarLazy();
            for (Funcionario f : funcionarios) {
                if (f.getLogin().equals(login) && f.getSenha().equals(senha)) {
                    funcionario = f;
                    break;
                }
            }
        } catch (ErroDAO e) {
            throw new ErroDAO(e);
        }
        return funcionario;
    }

    public static Endereco validaEndereco(int cep) throws ErroDAO {
        Endereco en = null;
        try (EnderecoDAO dao = new EnderecoDAO()) {
            en = dao.buscarCep(cep);
        } catch (ErroDAO e) {
            throw new ErroDAO(e);
        }
        return en;
    }
    public static Cliente validaCliente(String login, String senha) throws ErroDAO {
        Cliente cliente = null;
        try (ClienteDAO dao = new ClienteDAO()) {
            List<Cliente> clientes = dao.buscarLazy();
            for (Cliente c : clientes) {
                if (c.getLogin().equals(login) && c.getSenha().equals(senha)) {
                    cliente = c;
                    break;
                }
            }
        } catch (ErroDAO e) {
            throw new ErroDAO(e);
        }
        return cliente;
    }

    public static String trataCPF(String cpf){
        return cpf.replaceAll("[.-]", "");
    }
    public static String trataTelefone(String telefone) {
        // Remove parênteses, espaços, traço e retorna apenas os dígitos
        return telefone.replaceAll("[()\\s-]", "");
    }

}
