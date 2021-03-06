package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.ConexaoHSQLDB;
import entity.Apartamento;
import entity.Proprietario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ApartamentoDAO extends ConexaoHSQLDB {
	final String SQL_INSERT_APARTAMENTO = "INSERT INTO APARTAMENTO(NUMERO_AP, QTD_MORADOR, ANIMAL_ESTIMACAO, QTD_ANIMAL, ANDAR_AP, BLOCO_AP, DATAENTRADA, STATUS_AP, VAGA_VEICULO, PROPRIETARIOAP) VALUES (?,?,?,?,?,?,?,?,?,?)";
	final String SQL_SELECT_APARTAMENTO = "SELECT * FROM APARTAMENTO";
	final String SQL_SELECT_APARTAMENTO_ID = "SELECT * FROM APARTAMENTO WHERE ID_AP = ?";
	final String SQL_UPDATE_APARTAMENTO = "UPDATE APARTAMENTO SET NUMERO_AP=?, QTD_MORADOR=? , ANIMAL_ESTIMACAO=?, QTD_ANIMAL=?, ANDAR_AP =?, BLOCO_AP=?, DATAENTRADA=?, STATUS_AP =?, VAGA_VEICULO=?, PROPRIETARIOAP=? WHERE ID_AP = ?";
	final String SQL_DELETE_APARTAMENTO = "DELETE FROM APARTAMENTO WHERE ID_AP =?";
	final String SQL_SELECT_APARTAMENTO_LAST_INSERT = "SELECT * FROM APARTAMENTO WHERE ID_AP = (SELECT MAX(ID_AP) FROM APARTAMENTO)";
	final String SQL_SELECT_APARTAMENTOS = "SELECT NUMERO_AP FROM APARTAMENTO";

	public ObservableList<String> listaApartamentos() throws SQLException{
		ObservableList options = FXCollections.observableArrayList();
		
		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_APARTAMENTOS);) {
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				options.add(rs.getString("NUMERO_AP"));
			}
		}
		return options;
	}

	public int createAp(Apartamento apartamento) {

		int quantidade = 0;

		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_INSERT_APARTAMENTO);) {
			pst.setInt(1, apartamento.getNumAp());
			pst.setInt(2, apartamento.getQtdMorador());
			pst.setString(3, String.valueOf(apartamento.getAnimalEstimacao()));
			pst.setInt(4, apartamento.getQtdAnimal());
			pst.setInt(5, apartamento.getAndarAp());
			pst.setInt(6, apartamento.getBlocoAp());
			pst.setDate(7, apartamento.getDataEntrada());
			pst.setString(8, apartamento.getStatusAp());
			pst.setInt(9, apartamento.getVagaVeiculo());
			pst.setString(10, apartamento.getProprietarioAp());
			quantidade = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return quantidade;
	}

	public List<Apartamento> listAllAp() {
		List<Apartamento> listaApartamento = new ArrayList<Apartamento>();

		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_APARTAMENTO);) {

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Apartamento apartamento = new Apartamento();

				apartamento.setIdAp(rs.getInt("ID_AP"));
				apartamento.setNumAp(rs.getInt("NUMERO_AP"));
				apartamento.setQtdMorador(rs.getInt("QTD_MORADOR"));
				apartamento.setAnimalEstimacao(rs.getString("ANIMAL_ESTIMACAO"));
				apartamento.setQtdAnimal(rs.getInt("QTD_ANIMAL"));
				apartamento.setAndarAp(rs.getInt("ANDAR_AP"));
				apartamento.setBlocoAp(rs.getInt("BLOCO_AP"));
				apartamento.setDataEntrada(rs.getDate("DATAENTRADA"));
				apartamento.setStatusAp(rs.getString("STATUS_AP"));
				apartamento.setVagaVeiculo(rs.getInt("VAGA_VEICULO"));
				apartamento.setProprietarioAp(rs.getString("PROPRIETARIOAP"));
				listaApartamento.add(apartamento);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaApartamento;
	}

	public Apartamento findByIdAp(int idApartamento) {
		Apartamento apartamento = null;
		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_APARTAMENTO_ID);) {

			pst.setInt(1, idApartamento);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				apartamento = new Apartamento();
				apartamento.setIdAp(rs.getInt("ID_AP"));
				apartamento.setNumAp(rs.getInt("NUMERO_AP"));
				apartamento.setQtdMorador(rs.getInt("QTD_MORADOR"));
				apartamento.setAnimalEstimacao(rs.getString("ANIMAL_ESTIMACAO"));
				apartamento.setQtdAnimal(rs.getInt("QTD_ANIMAL"));
				apartamento.setAndarAp(rs.getInt("ANDAR_AP"));
				apartamento.setBlocoAp(rs.getInt("BLOCO_AP"));
				apartamento.setDataEntrada(rs.getDate("DATAENTRADA"));
				apartamento.setStatusAp(rs.getString("STATUS_AP"));
				apartamento.setVagaVeiculo(rs.getInt("VAGA_VEICULO"));
				apartamento.setProprietarioAp(rs.getString("PROPRIETARIOAP"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return apartamento;
	}

	public int editAp(Apartamento apartamento) {
		int quantidade = 0;

		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_UPDATE_APARTAMENTO);) {
			pst.setInt(1, apartamento.getNumAp());
			pst.setInt(2, apartamento.getQtdMorador());
			pst.setString(3, String.valueOf(apartamento.getAnimalEstimacao()));
			pst.setInt(4, apartamento.getQtdAnimal());
			pst.setInt(5, apartamento.getAndarAp());
			pst.setInt(6, apartamento.getBlocoAp());
			pst.setDate(7, apartamento.getDataEntrada());
			pst.setString(8, apartamento.getStatusAp());
			pst.setInt(9, apartamento.getVagaVeiculo());
			pst.setString(10, apartamento.getProprietarioAp());
			pst.setInt(11, apartamento.getIdAp());
			quantidade = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quantidade;
	}

	public int deleteAp(int idApartamento) {
		int quantidade = 0;
		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_DELETE_APARTAMENTO);) {
			pst.setInt(1, idApartamento);

			quantidade = pst.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
		return quantidade;
	}

	public Apartamento ultimoCadastro() {
		Apartamento apartamento = new Apartamento();
		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_APARTAMENTO_LAST_INSERT);) {

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				apartamento = new Apartamento();
				apartamento.setIdAp(rs.getInt("ID_AP"));
				apartamento.setNumAp(rs.getInt("NUMERO_AP"));
				apartamento.setQtdMorador(rs.getInt("QTD_MORADOR"));
				apartamento.setAnimalEstimacao(rs.getString("ANIMAL_ESTIMACAO"));
				apartamento.setQtdAnimal(rs.getInt("QTD_ANIMAL"));
				apartamento.setAndarAp(rs.getInt("ANDAR_AP"));
				apartamento.setBlocoAp(rs.getInt("BLOCO_AP"));
				apartamento.setDataEntrada(rs.getDate("DATAENTRADA"));
				apartamento.setStatusAp(rs.getString("STATUS_AP"));
				apartamento.setVagaVeiculo(rs.getInt("VAGA_VEICULO"));
				apartamento.setProprietarioAp(rs.getString("PROPRIETARIOAP"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return apartamento;
	}

	public List<Apartamento> listAllName(String text) {
		// TODO Auto-generated method stub
		return null;
	}
}
