package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.ConexaoHSQLDB;
import entity.Morador;
import entity.Proprietario;

public class moradorDao extends ConexaoHSQLDB {
	final String SQL_INSERT_MORADOR = "INSERT INTO MORADOR(NOME_MORADOR, TELEFONE_MORADOR, EMAIL_MORADOR, APARTAMENTO) VALUES (?,?,?,?)";
	final String SQL_SELECT_MORADOR = "SELECT * FROM MORADOR";
	final String SQL_SELECT_MORADOR_ID = "SELECT * FROM MORADOR WHERE ID_MORADOR = ?";
	final String SQL_SELECT_MORADOR_NOME = "SELECT * FROM MORADOR WHERE NOME_MORADOR LIKE ?";
	final String SQL_UPDATE_MORADOR = "UPDATE MORADOR SET NOME_MORADOR=?, TELEFONE_MORADOR=?, EMAIL_MORADOR=?, APARTAMENTO=? WHERE ID_MORADOR = ?";
	final String SQL_DELETE_MORADOR = "DELETE FROM MORADOR WHERE ID_MORADOR =?";
	final String SQL_SELECT_MORADOR_LAST_INSERT = "SELECT * FROM MORADOR WHERE ID_MORADOR = (SELECT MAX(ID_MORADOR) FROM MORADOR)";

	public Morador ultimoCadastro() {
		Morador morador = new Morador();
		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_MORADOR_LAST_INSERT);) {

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				morador.setIdMorador(rs.getInt("ID_MORADOR"));
				morador.setNomeMorador(rs.getString("NOME_MORADOR"));
				morador.setTelefoneMorador(rs.getString("TELEFONE_MORADOR"));
				morador.setEmailMorador(rs.getString("EMAIL_MORADOR"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return morador;
	}
	
	public int create(Morador morador) {

		int quantidade = 0;

		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_INSERT_MORADOR);) {
			pst.setString(1, morador.getNomeMorador());
			pst.setString(2, morador.getTelefoneMorador());
			pst.setString(3, morador.getEmailMorador());
			pst.setString(4, morador.getApartamento());
			quantidade = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return quantidade;
	}

	public List<Morador> listAll() {
		List<Morador> listaMorador = new ArrayList<Morador>();

		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_MORADOR);) {

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Morador morador = new Morador();

				morador.setIdMorador(rs.getInt("ID_MORADOR"));
				morador.setNomeMorador(rs.getString("NOME_MORADOR"));
				morador.setTelefoneMorador(rs.getString("TELEFONE_MORADOR"));
				morador.setEmailMorador(rs.getString("EMAIL_MORADOR"));
				morador.setApartamento(rs.getString("APARTAMENTO"));
				listaMorador.add(morador);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaMorador;
	}
	public List<Morador> listAllName(String nome) {
		List<Morador> listaMorador = new ArrayList<Morador>();
		
		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_MORADOR_NOME);) {
			
			pst.setString(1,"%" + nome + "%");
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				Morador morador = new Morador();
				
				morador.setIdMorador(rs.getInt("ID_MORADOR"));
				morador.setNomeMorador(rs.getString("NOME_MORADOR"));
				morador.setTelefoneMorador(rs.getString("TELEFONE_MORADOR"));
				morador.setEmailMorador(rs.getString("EMAIL_MORADOR"));
				morador.setApartamento(rs.getString("APARTAMENTO"));
				listaMorador.add(morador);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaMorador;
	}

	public Morador findByID(int idMorador) {
		Morador morador = null;
		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_MORADOR_ID);) {

			pst.setInt(1, idMorador);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				morador = new Morador();
				morador.setIdMorador(rs.getInt("ID_MORADOR"));
				morador.setNomeMorador(rs.getString("NOME_MORADOR"));
				morador.setTelefoneMorador(rs.getString("TELEFONE_MORADOR"));
				morador.setEmailMorador(rs.getString("EMAIL_MORADOR"));
				morador.setApartamento(rs.getString("APARTAMENTO"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return morador;
	}

	public int edit(Morador morador) {
		int quantidade = 0;

		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_UPDATE_MORADOR);) {
			pst.setString(1, morador.getNomeMorador());
			pst.setString(2, morador.getTelefoneMorador());
			pst.setString(3, morador.getEmailMorador());
			pst.setString(4, morador.getApartamento());
			pst.setInt(5, morador.getIdMorador());

			quantidade = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return quantidade;
	}

	public int delete(int idMorador) {
		int quantidade = 0;
		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_DELETE_MORADOR);) {
			pst.setInt(1, idMorador);

			quantidade = pst.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
		return quantidade;
	}
}
