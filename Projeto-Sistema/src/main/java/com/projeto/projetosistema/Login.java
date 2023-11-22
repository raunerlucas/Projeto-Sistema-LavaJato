package com.projeto.projetosistema;

import java.io.*;

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
        System.out.println("tipo = "+tipo+" login = "+login+" senha = "+senha);
        if (Tools.validaValor(tipo) && Tools.validaValor(login) && Tools.validaValor(senha)){
            out.println("tipo = "+tipo+" login = "+login+" senha = "+senha);
        }else{
            response.sendRedirect("index.jsp?msg=faltaDados");
        }
    }

    public void destroy() {
    }
}