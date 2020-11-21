package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.ConexaoHSQLDB;
import entity.Funcionario;
import entity.Sindico;
import entity.Visitante;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class loginDao extends ConexaoHSQLDB {
	
///////////////////////////////////////////////////////////////SINDICO///////////////////////////////////////////////////////////////
	final String SQL_SELECT_SINDICO_CPF = "SELECT * FROM SINDICOO WHERE CPF = ?";
	final String SQL_ALTERA_SINDICO = "UPDATE SINDICOO SET NOMESINDICO=?, DATANASCIMENTO=? , SEXOSINDICO=?, EMAILSINDICO=?, NUMEROTELEFONESINDICO=?, DATAADMISSAO=? WHERE IDSINDICO = ?";
	final String SQL_DELETA_SINDICO = "DELETE FROM SINDICOO WHERE IDSINDICO = ?";
	final String SQL_LOGIN_SINDICO = "SELECT * FROM SINDICOO WHERE CPF = ? AND NUMEROTELEFONESINDICO = ?";

	
///////////////////////////////////////////////////////////////FUNCIONÁRIO///////////////////////////////////////////////////////////////
	final String SQL_SELECT_FUNCIONARIO_CPF = "SELECT * FROM FUNCIONARIO WHERE CPF = ?";
	final String SQL_ALTERA_FUNCIONARIO = "UPDATE FUNCIONARIO SET NOME=?, CARGO=? , RG=?, CPF=?, TELEFONE=?, SEXO? DATAADMISSAO=?, SALARIO=? WHERE ID = ?";
	final String SQL_DELETA_FUNCIONARIO = "DELETE FROM FUNCIONARIO WHERE ID = ?";
	final String SQL_LOGIN_FUNCIONARIO = "SELECT * FROM FUNCIONARIO WHERE CPF = ? AND TELEFONE = ?";


///////////////////////////////////////////////////////////////SINDICO///////////////////////////////////////////////////////////////
	public Sindico SelectSindico(String cpf) {
		Sindico sindico = new Sindico();

		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_SINDICO_CPF);) {

			pst.setString(1, "%" + cpf + "%");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				sindico.setIdSINDICO(rs.getInt("IDSINDICO"));
				sindico.setNome(rs.getString("NOMESINDICO"));
				sindico.setCpf(rs.getString("CPF"));
				sindico.setDataNascimento(java.sql.Date.valueOf((rs.getString("DATANASCIMENTO"))));
				sindico.setSexoSindico(rs.getString("SEXOSINDICO"));
				sindico.setEmailSindico(rs.getString("EMAILSINDICO"));
				sindico.setTelefone(rs.getString("NUMEROTELEFONESINDICO"));
				sindico.setDataAdmissao(java.sql.Date.valueOf((rs.getString("DATAADMISSAO"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sindico;
	}
	
	public String authenticateUser(String cpf, String telefonesindico) {
		
		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_LOGIN_SINDICO);) {
			pst.setString(1, cpf);
			pst.setString(2, telefonesindico);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return "LOGADO COM SUCESSO";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


///////////////////////////////////////////////////////////////ALERTA///////////////////////////////////////////////////////////////
	private String alerta() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("ATENÇÃO!!!");
		alert.setHeaderText("INCOMPATIBILIDADE NAS INFORMAÇÕES");
		alert.setContentText("USUÁRIO OU SENHA INVÁLIDOS!");
		alert.showAndWait();
		return "";
	}

///////////////////////////////////////////////////////////////FUNCIONÁRIO///////////////////////////////////////////////////////////////
	
	public Funcionario SelectFuncionario(String cpf) {
		Funcionario funcionario = new Funcionario();

		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_FUNCIONARIO_CPF);) {

			pst.setString(1, "%" + cpf + "%");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				funcionario.setId(rs.getInt("ID"));
				funcionario.setNome(rs.getString("NOME"));
				funcionario.setCargo(rs.getString("CARGO"));
				funcionario.setRg(rs.getString("RG"));
				funcionario.setCpf(rs.getString("CPF"));
				funcionario.setTelefone(rs.getString("TELEFONE"));
				funcionario.setSexo(rs.getString("SEXO"));
				funcionario.setDataadmissao(java.sql.Date.valueOf((rs.getString("DATAADMISSAO"))));
				funcionario.setSalario(rs.getString("SALARIO"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return funcionario;
	}
	
	public String authenticateUserFuncionario(String cpf, String telefone) {

			try (Connection connection = this.conectar();
					PreparedStatement pst = connection.prepareStatement(SQL_LOGIN_FUNCIONARIO);) {
			pst.setString(1, cpf);
			pst.setString(2, telefone);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return "LOGADO COM SUCESSO";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}