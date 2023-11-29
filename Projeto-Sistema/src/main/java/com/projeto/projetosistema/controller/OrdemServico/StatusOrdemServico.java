package com.projeto.projetosistema.controller.OrdemServico;


import com.projeto.projetosistema.DAO.DAOInterface;
import com.projeto.projetosistema.DAO.ErroDAO;
import com.projeto.projetosistema.DAO.deployment.OrdemServicoDAO;
import com.projeto.projetosistema.model.Empresa;
import com.projeto.projetosistema.model.Funcionario;
import com.projeto.projetosistema.model.OrdemServico;
import com.projeto.projetosistema.model.Status;
import com.projeto.projetosistema.utils.Tools;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "statusOS", value = "/statusOS")
public class StatusOrdemServico extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String idT = request.getParameter("id");

        ServletContext aplicacao = getServletContext();
        HttpSession sessao = request.getSession();
        Funcionario userS = (Funcionario) sessao.getAttribute("userSessao");
        Empresa emp = (Empresa) aplicacao.getAttribute("empresa");
        if (userS != null && userS.isFuncionario()){
            if (Tools.validaValor(idT)) {
                OrdemServico osEd = null;
                for (OrdemServico os: (List<OrdemServico>) aplicacao.getAttribute("ordemSevico")                     ) {
                    if (os.getId() == Integer.parseInt(idT)){
                        osEd = os;
                    }
                }
                osEd.setStatus(modifyStatus(osEd.getStatus()));
                osEd.setEmpresa(emp);
                try (DAOInterface<OrdemServico> dao = new OrdemServicoDAO()){
                    dao.editar(osEd);
                    aplicacao.setAttribute("ordemSevico",Tools.getOS(userS));
                    response.sendRedirect("viewOS.jsp?numOS="+osEd.getNumeroOS()+"&msg=Status da Ordem de Servico atualizado com sucesso!");
                } catch (ErroDAO e) {
                    throw new RuntimeException(e);
                }
            } else {
                response.sendRedirect("viewOS.jsp?numOS="+idT+"&msg=Faltam dados para atualizar a OS");
            }
        } else {
            response.sendRedirect("index.jsp?msg=O funcionario pracisa estar logado");
        }
    }

    private Status modifyStatus(Status status) {
        switch (status) {
            case AGUARDANDO:
                return Status.INICIADO;
            case INICIADO:
                return Status.FINALIZADO;
            case FINALIZADO:
                return Status.INICIADO;
            default:
                return status;
        }
    }
}

