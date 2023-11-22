<section>
    <div>
        <h3>Funcionario</h3>
        <form method="post" action="login">
            <input type="hidden" name="tipo" value="f">
            <label>
                Login
                <input type="text" name="login" placeholder="Seu Login">
            </label>
            <label>
                Senha
                <input type="text" name="senha" placeholder="Sua Senha">
            </label>
            <input type="submit" value="Logar">
        </form>
        <button type="button" onclick="mudarLogin(event)">Cliente</button>
    </div>
</section>