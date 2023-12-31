package com.projeto.projetosistema.controller;

import java.io.*;
import java.util.List;

import com.projeto.projetosistema.DAO.DAOInterface;
import com.projeto.projetosistema.DAO.ErroDAO;
import com.projeto.projetosistema.DAO.deployment.*;
import com.projeto.projetosistema.model.*;
import com.projeto.projetosistema.utils.Tools;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "login", value = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-16");

        String tipo = request.getParameter("tipo");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        if (Tools.validaValor(tipo) && Tools.validaValor(login) && Tools.validaValor(senha)) {
            ServletContext aplicacao = getServletContext();
            HttpSession sessao = request.getSession();
            sessao.invalidate();
            try {
                if (tipo.equals("f")) {
                    Funcionario func = Tools.validaFuncionario(login, senha);
                    if (func != null) {
                        sessao = request.getSession();
                        sessao.setAttribute("userSessao", func);
                        aplicacao.setAttribute("empresa", getEmpresa());

                        aplicacao.setAttribute("clientes", Tools.getClientes());
                        aplicacao.setAttribute("servicos", Tools.getServicos());
                        if (func.isAdmin())
                            aplicacao.setAttribute("funcionarios", Tools.getFuncionarios());
                        aplicacao.setAttribute("ordemSevico", Tools.getOS(func));
                        response.sendRedirect("index.jsp");

                    } else {
                        response.sendRedirect("index.jsp?msg=Funcionario não Encotrado");
                    }
                } else if (tipo.equals("c")) {
                    Cliente clint = Tools.validaCliente(login, senha);
                    if (clint != null) {
                        sessao = request.getSession();
                        sessao.setAttribute("userSessao", clint);
                        aplicacao.setAttribute("empresa", getEmpresa());
                        aplicacao.setAttribute("ordemSevico", getOS(clint));

                        response.sendRedirect("index.jsp");
                    } else {
                        response.sendRedirect("index.jsp?msg=Cliente não Encotrado");
                    }
                } else {
                    response.sendRedirect("index.jsp?msg=Tipo Errado");
                }
            } catch (ErroDAO e) {
                throw new RuntimeException(e);
            }
        } else {
            response.sendRedirect("index.jsp?msg=Falta Dados");
        }
    }


    private Empresa getEmpresa() throws ErroDAO {
        DAOInterface<Empresa> dao = new EmpresaDAO();
        Empresa e = dao.buscar(1);
        return e;
    }

    private List<OrdemServico> getOS(Cliente userS) throws ErroDAO {
        OrdemServicoDAO dao = new OrdemServicoDAO();
        List<OrdemServico> os = dao.buscarPorCliente(userS);
        dao.close();
        return os;
    }

}