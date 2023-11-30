package com.projeto.projetosistema.controller.OrdemServico;


import com.projeto.projetosistema.DAO.DAOInterface;
import com.projeto.projetosistema.DAO.ErroDAO;
import com.projeto.projetosistema.DAO.deployment.ClienteDAO;
import com.projeto.projetosistema.DAO.deployment.OrdemServicoDAO;
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

@WebServlet(name = "editarOS", value = "/editarOS")
public class EditarOrdemServico extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //pessoal
        String idT = request.getParameter("id");
        //login
        String[] servicoInputs = request.getParameterValues("servicoInput");
        String descricao = request.getParameter("descricao");
        String veiculo = request.getParameter("veiculo");
        //endereco
        String dataPrevisao = request.getParameter("dataPrevisao");
        String entregar = request.getParameter("entregar");

        var dform = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        ServletContext aplicacao = getServletContext();
        HttpSession sessao = request.getSession();
        Funcionario userS = (Funcionario) sessao.getAttribute("userSessao");
        Empresa emp = (Empresa) aplicacao.getAttribute("empresa");
        if (userS != null && userS.isFuncionario()) {
            if (Tools.validaValor(idT) && Tools.validaValor(descricao)
                    && Tools.validaValor(veiculo) && Tools.validaValor(dataPrevisao)) {
                OrdemServico osEd = null;
                for (OrdemServico os : (List<OrdemServico>) aplicacao.getAttribute("ordemSevico")) {
                    if (os.getId() == Integer.parseInt(idT)) {
                        osEd = os;
                    }
                }
                try (DAOInterface<OrdemServico> dao = new OrdemServicoDAO()) {

                    osEd.setEmpresa(emp);
                    osEd.setServicosOrdem(Tools.fixServicos(servicoFiltro(servicoInputs)));
                    osEd.setDescricao(descricao);
                    osEd.setVeiculo(veiculo);
                    osEd.setEntregar(entregar.equals("sim"));
                    osEd.setPrevisaoTermino(dataPrevisao);

                    String dataEdit = osEd.getDataEmissao() + "( Editado em => " + LocalDate.now().format(dform) + " )";
                    osEd.setDataEmissao(dataEdit);

                    dao.editar(osEd);
                    aplicacao.setAttribute("ordemSevico", Tools.getOS(userS));
                    response.sendRedirect("viewOS.jsp?numOS=" + osEd.getNumeroOS() + "&msg=Ordem de Servico editada com sucesso!");
                } catch (ErroDAO e) {
                    throw new RuntimeException(e);
                }
            } else {
                response.sendRedirect("CadastrarOrdem.jsp?msg=Faltam dados para Editar a OS");
            }
        } else {
            response.sendRedirect("index.jsp?msg=O funcionario pracisa estar logado");
        }

    }

    private List<Servico> servicoFiltro(String[] servicos) {
        List<Servico> desVal = new ArrayList<>();
        for (String s : servicos) {
            String[] split = s.split(" -- ");
            desVal.add(new Servico(split[0], Float.valueOf(split[1])));
        }
        return desVal;
    }

    private String getCPF(String c) {
        return c.split(" -- ")[1];
    }

}

