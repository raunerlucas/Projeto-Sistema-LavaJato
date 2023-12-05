package com.projeto.projetosistema.controller;

import com.projeto.projetosistema.DAO.ErroDAO;
import com.projeto.projetosistema.DAO.deployment.VeiculoDAO;
import com.projeto.projetosistema.model.*;
import com.projeto.projetosistema.utils.Tools;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.*;

@WebServlet(name = "buscar", value = "/buscar")
public class Buscar extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-16");

        String busca = request.getParameter("buscar");

        ServletContext aplicacao = getServletContext();
        HttpSession sessao = request.getSession();
        Funcionario userS = (Funcionario) sessao.getAttribute("userSessao");

        if (userS != null && Tools.validaValor(busca)) {
            Set<Buscado> buscados = new HashSet<Buscado>();

            List<OrdemServico> osA = (List<OrdemServico>) aplicacao.getAttribute("ordemSevico");
            for (String t : getPalavras(busca)) {
                for (OrdemServico os : osA) {
                    try {
                        if (os.getNumeroOS().equals(Integer.parseInt(t))){
                            buscados.add(new Buscado("viewOS.jsp?numOS=" + os.getNumeroOS(),"Ordem de servico => "+os.getNumeroOS()));
                        }
                    } catch (NumberFormatException e) {
                        e.getStackTrace();
                    }
                    if (os.getClinte().getNome().toLowerCase().startsWith(t.toLowerCase()) ||
                            os.getClinte().getCPF().toLowerCase().startsWith(t.toLowerCase()) ||
                            os.getClinte().getSobrenome().toLowerCase().startsWith(t.toLowerCase()) ||
                            os.getFuncionario().getNome().toLowerCase().startsWith(t.toLowerCase()) ||
                            os.getFuncionario().getSobrenome().toLowerCase().startsWith(t.toLowerCase()) ||
                            os.getVeiculo().getPlaca().toLowerCase().startsWith(t.toLowerCase()) ||
                            os.getVeiculo().getTipo().toLowerCase().startsWith(t.toLowerCase())) {
                        buscados.add(new Buscado("viewOS.jsp?numOS=" + os.getNumeroOS(),
                                "Ordem de servico => "+os.getNumeroOS()));
                    }
                    for (Servico s : os.getServicosOrdem()) {
                        if (s.getDescricao().toLowerCase().startsWith(t.toLowerCase())) {
                            buscados.add(new Buscado("viewOS.jsp?numOS=" + os.getNumeroOS(),
                                    "Ordem de servico => "+os.getNumeroOS()));
                        }
                    }
                }
            }
            if (userS.isFuncionario()) {
                List<Cliente> clientA = (List<Cliente>) aplicacao.getAttribute("clientes");
                for (String t : getPalavras(busca)) {
                    for (Cliente cl : clientA) {
                        if (cl.getNome().toLowerCase().startsWith(t.toLowerCase()) ||
                                cl.getCPF().toLowerCase().startsWith(t.toLowerCase()) ||
                                cl.getSobrenome().toLowerCase().startsWith(t.toLowerCase())) {
                            buscados.add(new Buscado("viewC.jsp?numC=" + cl.getId(),
                                    "Cliente => "+cl.getNome()));
                        }
                    }
                }

                if (userS.isAdmin()) {
                    List<Funcionario> funcA = (List<Funcionario>) aplicacao.getAttribute("funcionarios");
                    for (String t : getPalavras(busca)) {
                        for (Funcionario f : funcA) {
                            if (f.getNome().toLowerCase().startsWith(t.toLowerCase()) ||
                                    f.getCPF().toLowerCase().startsWith(t.toLowerCase()) ||
                                    f.getSobrenome().toLowerCase().startsWith(t.toLowerCase())) {
                                buscados.add(new Buscado("viewF.jsp?numF=" + f.getId(),
                                        "Funcionario => "+f.getNome()));
                            }
                        }
                    }
                }
            }
            aplicacao.setAttribute("buscados", null);
            aplicacao.setAttribute("buscados", buscados);
            response.sendRedirect("index.jsp");
//            response.getWriter().println(buscados);
        } else {
            response.sendRedirect("index.jsp?msg=Favor logar");
        }
    }

    private String[] getPalavras(String txt) {
        return txt.split(" ");
    }

}


