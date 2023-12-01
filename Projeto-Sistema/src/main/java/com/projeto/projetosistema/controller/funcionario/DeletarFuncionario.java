package com.projeto.projetosistema.controller.funcionario;

import com.projeto.projetosistema.DAO.ErroDAO;
import com.projeto.projetosistema.DAO.deployment.ClienteDAO;
import com.projeto.projetosistema.DAO.deployment.FuncionarioDAO;
import com.projeto.projetosistema.model.Funcionario;
import com.projeto.projetosistema.utils.Tools;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "deletarF", value = "/deletarF")
public class DeletarFuncionario extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //pessoal
        String idCT = request.getParameter("id");

        ServletContext aplicacao = getServletContext();
        HttpSession sessao = request.getSession();
        Funcionario userS = (Funcionario) sessao.getAttribute("userSessao");
        if (userS != null && userS.isFuncionario() && userS.isAdmin()) {
            if (Tools.validaValor(idCT)) {
                try (FuncionarioDAO dao = new FuncionarioDAO()) {
                    dao.deletar(Integer.parseInt(idCT));
                    aplicacao.setAttribute("funcionarios", Tools.getFuncionarios());
                    aplicacao.setAttribute("ordemSevico", Tools.getOS(userS));
                    response.sendRedirect("index.jsp?msg=Funcionario Deletado com sucesso");
                } catch (ErroDAO e) {
                    throw new RuntimeException(e);
                }
            } else {
                response.sendRedirect("index.jsp?msg=Faltam dados do cliente");
            }
        } else {
            response.sendRedirect("index.jsp?msg=O funcionario pracisa estar logado");
        }

    }
}
