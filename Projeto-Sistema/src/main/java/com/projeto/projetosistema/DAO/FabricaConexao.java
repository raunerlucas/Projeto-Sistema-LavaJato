package com.projeto.projetosistema.DAO;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {
    public static Connection pegaConexao() throws ErroDAO {
    /*    try {
            Connection con;
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("ResourcePostgres");
            con = ds.getConnection();
            return con;
        } catch (NamingException | SQLException e) {
            throw new ErroDAO("Erro na Fabrica de conexão: "+e);
        }
*/
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost/projetoPW","postgres","123");
        } catch (ClassNotFoundException | SQLException e) {
            throw new ErroDAO("Erro na Fabrica de conexão: "+e);
        }
    }
}
