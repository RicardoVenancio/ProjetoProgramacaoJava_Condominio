package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.ConexaoHSQLDB;
import entity.Proprietario;

public class proprietarioDao extends ConexaoHSQLDB{
	
	final String SQL_INSERT_PROPRIETARIO = "INSERT INTO PROPRIETARIO(NOMEPROPRIETARIO, CPFPROPRIETARIO, RGPROPRIETARIO, NUMEROTELEFONEPROPRIETARIO, EMAILPROPRIETARIO) VALUES (?,?,?,?,?)";
	final String SQL_SELECT_PROPRIETARIO = "SELECT * FROM PROPRIETARIO";
	final String SQL_SELECT_PROPRIETARIO_ID = "SELECT * FROM PROPRIETARIO WHERE IDPROPRIETARIO = ?";
	final String SQL_ALTERA_PROPRIETARIO = "UPDATE PROPRIETARIO SET NOMEPROPRIETARIO=?, CPFPROPRIETARIO=? , RGPROPRIETARIO=?, NUMEROTELEFONEPROPRIETARIO=?, EMAILPROPRIETARIO=? WHERE IDPROPRIETARIO = ?";
	final String SQL_DELETA_PROPRIETARIO = "DELETE FROM PROPRIETARIO WHERE IDPROPRIETARIO = ?";

	public int inserir(Proprietario proprietario) {
		int quantidade = 0;
		try(Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_INSERT_PROPRIETARIO);) {
			pst.setString(1, proprietario.getNomeProprietario());
			pst.setString(2, proprietario.getCpfProprietario());
			pst.setString(3, proprietario.getRgProprietario());
			pst.setString(4, proprietario.getNumerotelefoneProprietario());
			pst.setString(5, proprietario.getEmailProprietario());
			quantidade = pst.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return quantidade;
	}
	
	public List<Proprietario> listAll(){
		List<Proprietario> listaProprietario = new ArrayList<Proprietario>();
		
		try(Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_PROPRIETARIO);) {
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				Proprietario proprietario = new Proprietario();
				
				proprietario.setIdProprietario(rs.getInt("IDPROPRIETARIO"));
				proprietario.setNomeProprietario(rs.getString("NOMEPROPRIETARIO"));
				proprietario.setCpfProprietario(rs.getString("CPFPROPRIETARIO"));
				proprietario.setRgProprietario(rs.getString("RGPROPRIETARIO"));
				proprietario.setNumerotelefoneProprietario(rs.getString("NUMEROTELEFONEPROPRIETARIO"));
				proprietario.setEmailProprietario(rs.getString("EMAILPROPRIETARIO"));
				
				listaProprietario.add(proprietario);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaProprietario;
	}
	
	public Proprietario findByID(int idProprietario) {
		Proprietario proprietario = null;
		
		try(Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_PROPRIETARIO_ID);){
			
			pst.setInt(1, idProprietario);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				proprietario = new Proprietario();
				
				proprietario.setIdProprietario(rs.getInt("IDPROPRIETARIO"));
				proprietario.setNomeProprietario(rs.getString("NOMEPROPRIETARIO"));
				proprietario.setCpfProprietario(rs.getString("CPFPROPRIETARIO"));
				proprietario.setRgProprietario(rs.getString("RGPROPRIETARIO"));
				proprietario.setNumerotelefoneProprietario(rs.getString("NUMEROTELEFONEPROPRIETARIO"));
				proprietario.setEmailProprietario(rs.getString("EMAILPROPRIETARIO"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return proprietario;
	}
	
	public int alterar(Proprietario proprietario) {
		int quantidade = 0;
		
		try(Connection connection = this.conectar();
		PreparedStatement pst = connection.prepareStatement(SQL_ALTERA_PROPRIETARIO);){
			pst.setString(1, proprietario.getNomeProprietario());
			pst.setString(2, proprietario.getCpfProprietario());
			pst.setString(3, proprietario.getRgProprietario());
			pst.setString(4, proprietario.getNumerotelefoneProprietario());
			pst.setString(5, proprietario.getEmailProprietario());
			pst.setInt(6, proprietario.getIdProprietario());
			
			quantidade = pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quantidade;
	}
	
	public int deletar(int idProprietario) {
		int quantidade = 0;
		try(Connection connection = this.conectar();
                PreparedStatement pst = connection.prepareStatement(SQL_DELETA_PROPRIETARIO);) {
			pst.setInt(1, idProprietario);
			
			quantidade = pst.executeUpdate();
		} catch (Exception e) {
            System.out.println(e);
        }
        return quantidade;
	}
}
