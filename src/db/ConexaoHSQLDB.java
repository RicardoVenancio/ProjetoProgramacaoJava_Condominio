package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoHSQLDB {

	private String usuario = "SA";
	private String senha = "";
	//private String PathBase = "C:\\Users\\ricar\\Desktop\\Trabalho facul\\4_semestre\\Projeto_Condominio\\ProjetoProgramacaoJava_Condominio\\Dados\\dados";
	private String PathBase = "C:\\Users\\wcwil\\Desktop\\ProjetoPP\\ProjetoProgramacaoJava_Condominio\\Dados\\dados";
	private String URL = "jdbc:hsqldb:file:" + PathBase + ";";
	
	public Connection conectar() {
		try {
			return DriverManager.getConnection(URL, usuario, senha);
		} catch (SQLException e) {
			throw new Error("SQLException");
		}
	}
}
