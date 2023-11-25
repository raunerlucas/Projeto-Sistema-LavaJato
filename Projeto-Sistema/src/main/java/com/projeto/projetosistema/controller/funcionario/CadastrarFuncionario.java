package com.projeto.projetosistema.controller.funcionario;

import com.projeto.projetosistema.DAO.DAOInterface;
import com.projeto.projetosistema.DAO.ErroDAO;
import com.projeto.projetosistema.DAO.deployment.ClienteDAO;
import com.projeto.projetosistema.DAO.deployment.EnderecoDAO;
import com.projeto.projetosistema.DAO.deployment.FuncionarioDAO;
import com.projeto.projetosistema.model.Cliente;
import com.projeto.projetosistema.model.Endereco;
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
import java.util.List;

@WebServlet(name = "cadastrarFuncionario", value = "/cadastrarFuncionario")
public class CadastrarFuncionario extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //pessoal
        String nome = request.getParameter("nome");
        String sobrenome = request.getParameter("sobrenome");
        String cpf = Tools.trataCPF(request.getParameter("cpf"));
        String telefone = Tools.trataTelefone(request.getParameter("telefone"));
        String email = request.getParameter("email");
        //login
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String admin = request.getParameter("admin");
        //endereco
        String cep = request.getParameter("cep");
        String numero = request.getParameter("numero");
        String complemento = request.getParameter("complemento");
        String logradouro = request.getParameter("logradouro");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("estado");

        ServletContext aplicacao = getServletContext();
        HttpSession sessao = request.getSession();
        Funcionario userS = (Funcionario) sessao.getAttribute("userSessao");
        if (userS != null && userS.isFuncionario() && userS.isAdmin()) {
            Funcionario funcNew = null;
            if (Tools.validaValor(nome) && Tools.validaValor(sobrenome) && Tools.validaValor(cpf)
                    && Tools.validaValor(telefone)) {
                try (FuncionarioDAO dao = new FuncionarioDAO()) {
                    Funcionario funcBC = dao.buscar(cpf) ;

                    if (funcBC == null) {
                        if (Tools.validaValor(login) && Tools.validaValor(senha)
                                && Tools.validaValor(email) && Tools.validaValor(admin)) {
                            funcNew = new Funcionario(nome, sobrenome, cpf, telefone, login, senha, email,
                                    admin.equals("sim"), null);

                            if (Tools.validaValor(cep) && Tools.validaValor(numero) && Tools.validaValor(cep) &&
                                    Tools.validaValor(complemento) && Tools.validaValor(logradouro) &&
                                    Tools.validaValor(bairro) && Tools.validaValor(cidade) &&
                                    Tools.validaValor(estado)) {

                                Endereco endereco = new Endereco(Integer.parseInt(cep), Integer.parseInt(numero), logradouro,
                                        complemento, bairro, cidade, estado);
                                EnderecoDAO daoE = new EnderecoDAO();
                                daoE.inserir(endereco);
                                daoE.close();

                                funcNew.setEndereco(endereco);
                                dao.inserir(funcNew);
                                aplicacao.setAttribute("funcionarios",getFuncionarios());

                                response.sendRedirect("index.jsp?msg=Funcionario Cadastrado com sucesso ");

                            } else {
                                response.sendRedirect("index.jsp?msg=Falta dados para o endereco do Funcionario");
                            }
                        } else {
                            response.sendRedirect("index.jsp?msg=Falta dados para o login do Funcionario");
                        }
                    } else {
                        response.sendRedirect("index.jsp?msg=Funcionario já existe");
                    }
                } catch (ErroDAO e) {
                    throw new RuntimeException(e);
                }
            } else {
                response.sendRedirect("index.jsp?msg=Faltam dados do Funcionario");
            }
        } else {
            response.sendRedirect("index.jsp?msg=O funcionario pracisa estar logado");
        }

    }
    private List<Funcionario> getFuncionarios() throws ErroDAO {
        DAOInterface<Funcionario> dao = new FuncionarioDAO();
        List<Funcionario> fs = dao.buscar();
        return fs;
    }
}
