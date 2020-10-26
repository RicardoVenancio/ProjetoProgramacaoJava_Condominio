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

public class sindicoDao extends ConexaoHSQLDB{
	final String SQL_INSERT_SINDICOO = "INSERT INTO SINDICOO(NOMESINDICO, DATANASCIMENTO, SEXOSINDICO, EMAILSINDICO, NUMEROTELEFONESINDICO, DATAADMISSAO) VALUES (?,?,?,?,?,?)";
	final String SQL_SELECT_SINDICOO = "SELECT * FROM SINDICOO";
	final String SQL_SELECT_SINDICO_ID = "SELECT * FROM SINDICOO WHERE IDSINDICO = ?";
	final String SQL_ALTERA_SINDICO = "UPDATE SINDICOO SET NOMESINDICO=?, DATANASCIMENTO=? , SEXOSINDICO=?, EMAILSINDICO=?, NUMEROTELEFONESINDICO=?, DATAADMISSAO=? WHERE IDSINDICO = ?";
	final String SQL_DELETA_SINDICO = "DELETE FROM SINDICOO WHERE IDSINDICO = ?";

public int inserir(Sindico sindicoo) {
	int quantidade = 0;

	//INSERIR
	try (Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_INSERT_SINDICOO);) {
		pst.setString(1, sindicoo.getNomeSindico());
		pst.setDate(2, java.sql.Date.valueOf(sindicoo.getDataNascimento().toString()));
		pst.setString(3, sindicoo.getSexoSindico());
		pst.setString(4, sindicoo.getEmailSindico());
		pst.setString(5, sindicoo.getNumerotelefoneSindico());
		pst.setDate(6, java.sql.Date.valueOf(sindicoo.getDataAdmissao().toString()));
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
			sindico.setNomeSindico(rs.getString("NOMESINDICO"));
			sindico.setDataNascimento(java.sql.Date.valueOf((rs.getString("DATANASCIMENTO"))));
			sindico.setSexoSindico(rs.getString("SEXOSINDICO"));
			sindico.setEmailSindico(rs.getString("EMAILSINDICO"));
			sindico.setNumerotelefoneSindico(rs.getString("NUMEROTELEFONESINDICO"));
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

	//ALTERAR SINDICO
public int alterar(Sindico sindico) {
	int quantidade = 0;

	try (Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_ALTERA_SINDICO);) {
		pst.setString(1, sindico.getNomeSindico());
		//pst.setString(2, sindico.getDataNascimento());
		//Cliente.setNascimento(rs.getDate("NASCIMENTO"));
		pst.setDate(2, java.sql.Date.valueOf(sindico.getDataNascimento().toString()));
		pst.setString(3,  sindico.getSexoSindico());
		pst.setString(4, sindico.getEmailSindico());
		pst.setString(5, sindico.getNumerotelefoneSindico());
		pst.setDate(6, java.sql.Date.valueOf(sindico.getDataAdmissao().toString()));
		pst.setInt(7, sindico.getIdSINDICO());

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
