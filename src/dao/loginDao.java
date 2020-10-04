package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.ConexaoHSQLDB;
import entity.Sindico;

public class loginDao extends ConexaoHSQLDB{
	final String SQL_INSERT_SINDICOO = "INSERT INTO SINDICOO(NOMESINDICO, DATANASCIMENTO, SEXOSINDICO, EMAILSINDICO, NUMEROTELEFONESINDICO, DATAADMISSAO) VALUES (?,?,?,?,?,?)";
	final String SQL_SELECT_SINDICOO = "SELECT * FROM SINDICOO";
	final String SQL_SELECT_SINDICO_ID = "SELECT * FROM SINDICOO WHERE IDSINDICO = ?";
	final String SQL_ALTERA_SINDICO = "UPDATE SINDICOO SET NOMESINDICO=?, DATANASCIMENTO=? , SEXOSINDICO=?, EMAILSINDICO=?, NUMEROTELEFONESINDICO=?, DATAADMISSAO=? WHERE IDSINDICO = ?";
	final String SQL_DELETA_SINDICO = "DELETE FROM SINDICOO WHERE IDSINDICO = ?";
	final String SQL_LOGIN_SINDICO = "SELECT * FROM Employees WHERE NOMESINDICO = ? AND NUMEROTELEFONESINDICO = ?";



		public void authenticateUser() {
			Sindico sindico = new Sindico();

			String nomesindico = sindico.getNomeSindico();
			String telefone = sindico.getNumerotelefoneSindico();			
			
			try (Connection connection = this.conectar();
					PreparedStatement pst = connection.prepareStatement(SQL_LOGIN_SINDICO);){
					pst.setString(1, nomesindico);
					pst.setString(2, telefone);
				
				ResultSet rs = pst.executeQuery();

				if(!rs.next()) {
					System.out.println("erro");
				}
				else {
					System.out.println("LOGADO COM SUCESSO!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
