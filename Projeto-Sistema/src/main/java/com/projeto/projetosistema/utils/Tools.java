package com.projeto.projetosistema.utils;

import com.projeto.projetosistema.DAO.DAOInterface;
import com.projeto.projetosistema.DAO.ErroDAO;
import com.projeto.projetosistema.DAO.deployment.ClienteDAO;
import com.projeto.projetosistema.DAO.deployment.FuncionarioDAO;
import com.projeto.projetosistema.model.Clinte;
import com.projeto.projetosistema.model.Funcionario;

import java.util.ArrayList;
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
    public static Clinte validaCliente(String login, String senha) throws ErroDAO {
        Clinte clinte = null;
        try (ClienteDAO dao = new ClienteDAO()) {
            List<Clinte> clintes = dao.buscarLazy();
            for (Clinte c : clintes) {
                if (c.getLogin().equals(login) && c.getSenha().equals(senha)) {
                    clinte = c;
                    break;
                }
            }
        } catch (ErroDAO e) {
            throw new ErroDAO(e);
        }
        return clinte;
    }

}
