package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.ConexaoHSQLDB;
import entity.Sindico;

public class loginDao extends ConexaoHSQLDB{
	final String SQL_INSERT_LOGIN = "INSERT INTO LOGIN ( CPF, SENHA) VALUES (?,?)";
	final String SQL_SELECT_LOGIN = "SELECT * FROM LOGIN";
	final String SQL_SELECT_LOGIN_ID = "SELECT * FROM LOGIN WHERE ID = ?";
	final String SQL_ALTERA_LOGIN = "UPDATE LOGIN SET CPF=?, SENHA=? WHERE ID = ?";
	final String SQL_DELETA_LOGIN = "DELETE FROM LOGIN WHERE ID = ?";

	//BUSCAR PELO ID
	public Sindico findByID(float idSindico) {
		Sindico sindicoo = null;
		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_LOGIN_ID);){

			pst.setFloat(1, idSindico);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				sindicoo = new Sindico();
				
				sindicoo.setIdSINDICO(rs.getInt("IDSINDICO"));
				sindicoo.setNomeSindico(rs.getString("NOMESINDICO"));
				sindicoo.setDataNascimento(rs.getDate("DATANASCIMENTO"));
				sindicoo.setSexoSindico(rs.getString("SEXOSINDICO"));
				sindicoo.setEmailSindico(rs.getString("EMAILSINDICO"));
				sindicoo.setNumerotelefoneSindico(rs.getString("NUMEROTELEFONESINDICO"));
				sindicoo.setDataAdmissao(rs.getDate("DATAADMISSAO"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return sindicoo;
		}

}
