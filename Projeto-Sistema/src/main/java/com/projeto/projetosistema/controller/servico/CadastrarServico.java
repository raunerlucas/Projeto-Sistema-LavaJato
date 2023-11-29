package com.projeto.projetosistema.controller.servico;


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

@WebServlet(name = "cadastrarS", value = "/cadastrarS")
public class CadastrarServico extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        //Data
        String descricao = request.getParameter("descricao");
        String preco = request.getParameter("preco");

        ServletContext aplicacao = getServletContext();
        HttpSession sessao = request.getSession();
        Funcionario userS = (Funcionario) sessao.getAttribute("userSessao");
        if (userS != null && userS.isFuncionario() && userS.isAdmin()) {
            if (Tools.validaValor(preco) && Tools.validaValor(descricao)) {
                Servico newS = new Servico(descricao, Float.parseFloat(preco));
                try (DAOInterface<Servico> dao = new ServicoDAO()){
                    dao.inserir(newS);
                    aplicacao.setAttribute("servicos",Tools.getServicos());
                    aplicacao.setAttribute("ordemSevico",Tools.getOS(userS));
                    response.sendRedirect("Servicos.jsp?msg=Servico Cadastrada com sucesso!");
                } catch (ErroDAO e) {
                    throw new RuntimeException(e);
                }
            } else {
                response.sendRedirect("Servicos.jsp?msg=Faltam dados para o Servico");
            }
        } else {
            response.sendRedirect("index.jsp?msg=O funcionario pracisa estar logado");
        }

    }
}

