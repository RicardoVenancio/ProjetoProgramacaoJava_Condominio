package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.ConexaoHSQLDB;
import entity.Sindico;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class loginDao extends ConexaoHSQLDB {
	final String SQL_INSERT_SINDICOO = "INSERT INTO SINDICOO(NOMESINDICO, DATANASCIMENTO, SEXOSINDICO, EMAILSINDICO, NUMEROTELEFONESINDICO, DATAADMISSAO) VALUES (?,?,?,?,?,?)";
	final String SQL_SELECT_SINDICOO = "SELECT * FROM SINDICOO";
	final String SQL_SELECT_SINDICO_ID = "SELECT * FROM SINDICOO WHERE IDSINDICO = ?";
	final String SQL_ALTERA_SINDICO = "UPDATE SINDICOO SET NOMESINDICO=?, DATANASCIMENTO=? , SEXOSINDICO=?, EMAILSINDICO=?, NUMEROTELEFONESINDICO=?, DATAADMISSAO=? WHERE IDSINDICO = ?";
	final String SQL_DELETA_SINDICO = "DELETE FROM SINDICOO WHERE IDSINDICO = ?";
	// final String SQL_LOGIN_SINDICO = "SELECT * FROM Employees WHERE NOMESINDICO =
	// ? AND NUMEROTELEFONESINDICO = ?";
	final String SQL_LOGIN_SINDICO = "SELECT * FROM SINDICOO WHERE NOMESINDICO = ? AND NUMEROTELEFONESINDICO = ?";


	
	public String authenticateUser(String nomesindico, String telefonesindico) {

		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_LOGIN_SINDICO);) {
			pst.setString(1, nomesindico);
			pst.setString(2, telefonesindico);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return "LOGADO COM SUCESSO";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("ATENÇÃO!!!");
		alert.setHeaderText("INCOMPATIBILIDADE NAS INFORMAÇÕES");
		alert.setContentText("USUÁRIO OU SENHA INVÁLIDOS!");
		alert.showAndWait();

		return "ERRO AO EFETUAR O LOGION, VERIFIQUE SEU CPF E SUA SENHA.";
	}
}