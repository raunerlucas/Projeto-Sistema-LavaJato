<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<section>
    <div>
        <h3>Funcionario</h3>
        <form method="post" action="login">
            <input type="hidden" name="tipo" value="f">
            <label>
                Login
                <input type="text" required name="login" placeholder="Seu Login">
            </label>
            <label>
                Senha
                <input type="password" required name="senha" placeholder="Sua Senha">
            </label>
            <input type="submit" value="Logar">
        </form>
        <button type="button" onclick="mudarLogin(event)">Cliente</button>
    </div>
</section>