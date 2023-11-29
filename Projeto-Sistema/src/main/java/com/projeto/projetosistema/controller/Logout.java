package com.projeto.projetosistema.controller;

import com.projeto.projetosistema.DAO.DAOInterface;
import com.projeto.projetosistema.DAO.ErroDAO;
import com.projeto.projetosistema.DAO.deployment.EmpresaDAO;
import com.projeto.projetosistema.model.Empresa;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "logout", value = "/logout")
public class Logout extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        ServletContext aplicacao = getServletContext();
        HttpSession sessao = request.getSession();
        if (sessao.getAttribute("userSessao") != null) {
            sessao.invalidate();
            if (aplicacao.getAttribute("clientes") != null){
                aplicacao.removeAttribute("clientes");
                if (aplicacao.getAttribute("funcionarios") != null)
                    aplicacao.removeAttribute("funcionarios");
                if (aplicacao.getAttribute("ordemSevico") != null)
                    aplicacao.removeAttribute("ordemSevico");
            }
            response.sendRedirect("index.jsp?msg=Tchau");
        } else {
            response.sendRedirect("index.jsp?msg=Ninguem Logado");
        }
    }
}