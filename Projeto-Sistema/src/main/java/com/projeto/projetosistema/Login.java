package com.projeto.projetosistema;

import java.io.*;
import java.util.List;

import com.projeto.projetosistema.DAO.ErroDAO;
import com.projeto.projetosistema.model.Clinte;
import com.projeto.projetosistema.model.Funcionario;
import com.projeto.projetosistema.utils.Tools;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "login", value = "/login")
public class Login extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String tipo = request.getParameter("tipo");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        PrintWriter out = response.getWriter();
        if (Tools.validaValor(tipo) && Tools.validaValor(login) && Tools.validaValor(senha)) {
            HttpSession sessao = request.getSession();
            sessao.invalidate();
            try {
                if (tipo.equals("f")) {
                    Funcionario func = Tools.validaFuncionario(login, senha);
                    if (func != null) {
                        sessao = request.getSession();
                        sessao.setAttribute("userSessao", func);
                        response.sendRedirect("index.jsp");
                    } else {
                        response.sendRedirect("index.jsp?msg=FuncNaoEncotrado");
                    }
                } else if (tipo.equals("c")) {
                    Clinte clint = Tools.validaCliente(login, senha);
                    if (clint != null) {
                        sessao = request.getSession();
                        sessao.setAttribute("userSessao", clint);
                        response.sendRedirect("index.jsp");
                    } else {
                        response.sendRedirect("index.jsp?msg=ClienteNaoEncotrado");
                    }
                } else {
                    response.sendRedirect("index.jsp?msg=TipoErrado");
                }
            } catch (ErroDAO e) {
                throw new RuntimeException(e);
            }
        } else {
            response.sendRedirect("index.jsp?msg=faltaDados");
        }
    }

    public void destroy() {
    }
}