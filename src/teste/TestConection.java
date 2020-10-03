package teste;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.ConexaoHSQLDB;

public class TestConection {

	public static void main(String[] args) {
		ConexaoHSQLDB conn = new ConexaoHSQLDB();
		
		Connection connection = conn.conectar();
		System.out.println(connection);
		
		// INSERIR DADOS
		final String SQL_INSERT_SINDICOO = "INSERT INTO SINDICOO(NOMESINDICO, DATANASCIMENTO, SEXOSINDICO, EMAILSINDICO, NUMEROTELEFONESINDICO, DATAADMISSAO) VALUES (?,?,?,?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(SQL_INSERT_SINDICOO);
			pst.setString(1,  "Ricardo Venancio");
			pst.setString(2,  "24/10/1998");
			pst.setString(3, "Masculino");
			pst.setString(4, "ricardoicastro@hotmail.com");
			pst.setString(5 , "41996006603");
			pst.setString(6, "25/10/1999");
			int qtde = pst.executeUpdate();
			System.out.println("Quantidade inserido: "+ qtde);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//MOSTRAR TODOS
		final String SQL_SELECT_SINDICOO = "SELECT * FROM SINDICOO";

		try {
			PreparedStatement pst = connection.prepareStatement(SQL_SELECT_SINDICOO);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				int idsindico = rs.getInt("idSINDICO");
				String nomesindico = rs.getString("NOMESINDICO");
				String datanascimento = rs.getString("DATANASCIMENTO");
				String sexosindico = rs.getString("SEXOSINDICO");
				String emailsindico = rs.getString("EMAILSINDICO");
				String numerotelefonesindico = rs.getString("NUMEROTELEFONESINDICO");
				String dataadmissao = rs.getString("DATAADMISSAO");
				
				System.out.println(idsindico + " " + nomesindico + " " + datanascimento + " " + sexosindico + " " + emailsindico + " " + numerotelefonesindico + " " + dataadmissao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			connection.close(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
