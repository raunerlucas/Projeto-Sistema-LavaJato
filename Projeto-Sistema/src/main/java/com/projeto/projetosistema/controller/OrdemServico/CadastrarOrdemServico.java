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
import java.util.*;

@WebServlet(name = "cadastrarOS", value = "/cadastrarOS")
public class CadastrarOrdemServico extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //pessoal
        String clienteT = request.getParameter("cliente");
        //login
        String[] servicoInputs = request.getParameterValues("servicoInput");
        String descricao = request.getParameter("descricao");
        //veiculo
        String tipoVeiculo = request.getParameter("veiculo");
        String placa = request.getParameter("placa").toUpperCase();
        String modelo = request.getParameter("modelo");
        String cor = request.getParameter("cor");
        //endereco
        String dataPrevisao = request.getParameter("dataPrevisao");
        String entregar = request.getParameter("entregar");

        var dform = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        ServletContext aplicacao = getServletContext();
        HttpSession sessao = request.getSession();
        Funcionario userS = (Funcionario) sessao.getAttribute("userSessao");
        if (userS != null && userS.isFuncionario()) {
            if (Tools.validaValor(clienteT) && Tools.validaValor(descricao)
                    && Tools.validaValor(tipoVeiculo) && Tools.validaValor(dataPrevisao)) {

                OrdemServico osNew = new OrdemServico(Integer.MAX_VALUE,
                        LocalDate.now().format(dform),
                        LocalDate.parse(dataPrevisao,DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                                .format(dform),entregar.equals("sim"),
                        descricao,null, null,null,null,null);

                String numOS = "";
                Funcionario func = (Funcionario) sessao.getAttribute("userSessao");
                osNew.setFuncionario(func);
                numOS += func.getId().toString();
                Empresa empresa = (Empresa) aplicacao.getAttribute("empresa");
                osNew.setEmpresa(empresa);
                numOS += empresa.getId().toString();

                try {
                    Veiculo vec = new Veiculo(placa,modelo,tipoVeiculo,cor);
                    osNew.setVeiculo(Tools.fixVeiculo(vec));

                    ClienteDAO daoC = new ClienteDAO();
                    Cliente clienteB = daoC.buscar(getCPF(clienteT));
                    daoC.close();
                    numOS += clienteB.getId().toString();
                    osNew.setClinte(clienteB);
                } catch (ErroDAO e) {
                    throw new RuntimeException(e);
                }
                try {
                    osNew.setServicosOrdem(Tools.fixServicos(servicoFiltro(servicoInputs)));

                    DAOInterface<OrdemServico> daoOS = new OrdemServicoDAO();
                    daoOS.inserir(osNew);
                    numOS += osNew.getId().toString();
                    osNew.setNumeroOS(Integer.parseInt(numOS));
                    daoOS.editar(osNew);
                    daoOS.close();

                    aplicacao.setAttribute("ordemSevico",Tools.getOS(func));

                    response.sendRedirect("index.jsp?msg=Ordem de Servico Cadastrada com sucesso!");
                } catch (ErroDAO e) {
                    throw new RuntimeException(e);
                }

            } else {
                response.sendRedirect("CadastrarOrdem.jsp?msg=Faltam dados para cadastra a OS");
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

    private String getCPF(String c){
        return c.split(" -- ")[1];
    }

}

