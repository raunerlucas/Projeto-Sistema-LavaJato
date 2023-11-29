package com.projeto.projetosistema.controller.OrdemServico;


import com.projeto.projetosistema.DAO.DAOInterface;
import com.projeto.projetosistema.DAO.ErroDAO;
import com.projeto.projetosistema.DAO.deployment.ClienteDAO;
import com.projeto.projetosistema.DAO.deployment.OrdemServicoDAO;
import com.projeto.projetosistema.DAO.deployment.ServicoDAO;
import com.projeto.projetosistema.model.*;
import com.projeto.projetosistema.utils.Tools;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "deletarOS", value = "/deletarOS")
public class DeletarOrdemServico extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String idT = request.getParameter("id");

        ServletContext aplicacao = getServletContext();
        HttpSession sessao = request.getSession();
        Funcionario userS = (Funcionario) sessao.getAttribute("userSessao");
        if (userS != null && userS.isFuncionario() && userS.isAdmin()) {
            if (Tools.validaValor(idT)) {
                try (DAOInterface<OrdemServico> dao = new OrdemServicoDAO()){
                    dao.deletar(Integer.parseInt(idT));
                    aplicacao.setAttribute("ordemSevico",Tools.getOS(userS));
                    response.sendRedirect("index.jsp?msg=Ordem de Servico Deletada com sucesso!");
                } catch (ErroDAO e) {
                    throw new RuntimeException(e);
                }
            } else {
                response.sendRedirect("index.jsp?msg=Faltam dados para Deletar a OS");
            }
        } else {
            response.sendRedirect("index.jsp?msg=O funcionario pracisa estar logado");
        }
    }
}

