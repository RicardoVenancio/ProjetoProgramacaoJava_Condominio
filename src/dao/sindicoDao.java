package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import db.ConexaoHSQLDB;
import entity.Sindico;
import entity.Visitante;

public class sindicoDao extends ConexaoHSQLDB{
	final String SQL_INSERT_SINDICOO = "INSERT INTO SINDICOO(NOMESINDICO, CPF, DATANASCIMENTO, SEXOSINDICO, EMAILSINDICO, NUMEROTELEFONESINDICO, DATAADMISSAO) VALUES (?,?,?,?,?,?)";
	final String SQL_SELECT_SINDICOO = "SELECT * FROM SINDICOO";
	final String SQL_SELECT_SINDICO_ID = "SELECT * FROM SINDICOO WHERE IDSINDICO = ?";
	final String SQL_ALTERA_SINDICO = "UPDATE SINDICOO SET NOMESINDICO=?, CPF=?, DATANASCIMENTO=? , SEXOSINDICO=?, EMAILSINDICO=?, NUMEROTELEFONESINDICO=?, DATAADMISSAO=? WHERE IDSINDICO = ?";
	final String SQL_DELETA_SINDICO = "DELETE FROM SINDICOO WHERE IDSINDICO = ?";
	final String SQL_SELECT_SINDICOO_NOME = "SELECT * FROM SINDICOO WHERE NOMESINDICO = ?";

	public Sindico BuscarDados(String nome) {
		Sindico sindico = new Sindico();
		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_SINDICOO_NOME);) {

			pst.setString(1, nome);
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
	
public int inserir(Sindico sindicoo) {
	int quantidade = 0;

	//INSERIR
	try (Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_INSERT_SINDICOO);) {
		//pst.setString(1, sindicoo.getNomeSindico());
		pst.setString(1,sindicoo.getNome()); //COM HERANÇA DA CLASSE PESSOA
		pst.setString(2, sindicoo.getCpf());
		pst.setDate(3, java.sql.Date.valueOf(sindicoo.getDataNascimento().toString()));
		pst.setString(4, sindicoo.getSexoSindico());
		pst.setString(5, sindicoo.getEmailSindico());
		//pst.setString(5, sindicoo.getNumerotelefoneSindico());
		pst.setString(6, sindicoo.getTelefone()); //COM HERANÇA DA CLASSE PESSOA
		pst.setDate(7, java.sql.Date.valueOf(sindicoo.getDataAdmissao().toString()));
		quantidade = pst.executeUpdate();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}

	return quantidade;
	}

	//LISTAR
public List<Sindico> listAll(){
	List<Sindico> listaSindico = new ArrayList<Sindico>();
	
	try (Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_SELECT_SINDICOO);){

		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			Sindico sindico = new Sindico();
			
			sindico.setIdSINDICO(rs.getInt("IDSINDICO"));
			sindico.setNome(rs.getString("NOMESINDICO"));
			sindico.setCpf(rs.getString("CPF"));
			sindico.setDataNascimento(java.sql.Date.valueOf((rs.getString("DATANASCIMENTO"))));
			sindico.setSexoSindico(rs.getString("SEXOSINDICO"));
			sindico.setEmailSindico(rs.getString("EMAILSINDICO"));
			sindico.setTelefone(rs.getString("NUMEROTELEFONESINDICO"));
			sindico.setDataAdmissao(java.sql.Date.valueOf((rs.getString("DATAADMISSAO"))));
			
			listaSindico.add(sindico);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return listaSindico;
	}
	
	//BUSCAR PELO ID
public Sindico findByID(int idSindico) {
	Sindico sindicoo = null;
	try (Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_SELECT_SINDICO_ID);){

		pst.setInt(1, idSindico);
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			sindicoo = new Sindico();
			
			sindicoo.setIdSINDICO(rs.getInt("IDSINDICO"));
			sindicoo.setNome(rs.getString("NOMESINDICO"));
			sindicoo.setCpf(rs.getString("CPF"));
			sindicoo.setDataNascimento(rs.getDate("DATANASCIMENTO"));
			sindicoo.setSexoSindico(rs.getString("SEXOSINDICO"));
			sindicoo.setEmailSindico(rs.getString("EMAILSINDICO"));
			sindicoo.setTelefone(rs.getString("NUMEROTELEFONESINDICO"));
			sindicoo.setDataAdmissao(rs.getDate("DATAADMISSAO"));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return sindicoo;
	}

	//ALTERAR SINDICO
public int alterar(Sindico sindico) {
	int quantidade = 0;

	try (Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_ALTERA_SINDICO);) {
		pst.setString(1, sindico.getNome());
		pst.setString(2, sindico.getCpf());
		pst.setDate(3, java.sql.Date.valueOf(sindico.getDataNascimento().toString()));
		pst.setString(4,  sindico.getSexoSindico());
		pst.setString(5, sindico.getEmailSindico());
		pst.setString(6, sindico.getTelefone());
		pst.setDate(7, java.sql.Date.valueOf(sindico.getDataAdmissao().toString()));
		pst.setInt(8, sindico.getIdSINDICO());

		quantidade = pst.executeUpdate();

	} catch (SQLException e) {
		e.printStackTrace();
	}

	return quantidade;
	}

	//DELETAR SINDICO
public int deletar(int idSindico) {
        int quantidade = 0;
        try (Connection connection = this.conectar();
                PreparedStatement pst = connection.prepareStatement(SQL_DELETA_SINDICO);) {
            pst.setInt(1, idSindico);
            
            quantidade = pst.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return quantidade;
    }
}
