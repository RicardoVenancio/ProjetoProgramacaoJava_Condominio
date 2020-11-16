package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import db.ConexaoHSQLDB;

import entity.Veiculo;
 
public class VeiculoDao extends ConexaoHSQLDB{
	final String SQL_INSERT_VEICULO = "INSERT INTO VEICULO(PLACAVEICULO, MODELOVEICULO, CORVEICULO, ANOVEICULO) VALUES (?,?,?,?)";
	final String SQL_SELECT_VEICULO = "SELECT * FROM VEICULO";
	final String SQL_SELECT_VEICULO_ID = "SELECT * FROM VEICULO WHERE ID = ?";
	final String SQL_ALTERA_VEICULO = "UPDATE VEICULO SET PLACAVEICULO=?, MODELOVEICULO=? , CORVEICULO=? , ANOVEICULO=? WHERE ID = ?";
	final String SQL_DELETA_VEICULO = "DELETE FROM VEICULO WHERE ID = ?";

public int inserir(Veiculo veiculo) {
	int quantidade = 0;

	//INSERIR
try (Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_INSERT_VEICULO);) {
		pst.setString(1, veiculo.getPlacaveiculo());
		pst.setString(2, veiculo.getModeloveiculo());
		pst.setString(3, veiculo.getCorveiculo());
		pst.setString(4, veiculo.getAnoveiculo());
	 
	
		
		quantidade = pst.executeUpdate();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
 
	return quantidade;
	}
//LISTAR
public List<Veiculo> listAll(){
	List<Veiculo> listaVeiculo = new ArrayList<>();
	
	try (Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_SELECT_VEICULO);){

		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			Veiculo veiculo = new Veiculo();
			
			veiculo.setId(rs.getInt("ID"));
			veiculo.setPlacaveiculo(rs.getString("PLACAVEICULO"));
			veiculo.setModeloveiculo(rs.getString("MODELOVEICULO"));
			veiculo.setCorveiculo(rs.getString("CORVEICULO"));
			veiculo.setAnoveiculo(rs.getString("ANOVEICULO"));
			
			listaVeiculo.add(veiculo);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return listaVeiculo;
	}


//BUSCAR PELO ID
public Veiculo findByID(int idVeiculo) {
	Veiculo veiculo = null;
	try (Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_SELECT_VEICULO_ID);){

		pst.setInt(1, idVeiculo);
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			veiculo = new Veiculo();
			
			veiculo.setId(rs.getInt("ID"));
			veiculo.setPlacaveiculo(rs.getString("PLACAVEICULO"));
			veiculo.setModeloveiculo(rs.getString("MODELOVEICULO"));
			veiculo.setCorveiculo(rs.getString("CORVEICULO"));
			veiculo.setAnoveiculo(rs.getString("ANOVEICULO"));
				
			}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	
	return veiculo;
	}
 
	//ALTERAR 
	public int alterar(Veiculo veiculo) {
		int quantidade = 0;

		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_ALTERA_VEICULO);) {
			pst.setString(1, veiculo.getPlacaveiculo());
			pst.setString(2, veiculo.getModeloveiculo());
			pst.setString(3, veiculo.getCorveiculo());
			pst.setString(4, veiculo.getAnoveiculo());
			pst.setInt(5, veiculo.getId());

			quantidade = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return quantidade;
		}

	//DELETAR 
public int deletar(int idVeiculo) {
        int quantidade = 0;
        try (Connection connection = this.conectar();
                PreparedStatement pst = connection.prepareStatement(SQL_DELETA_VEICULO);) {
            pst.setInt(1, idVeiculo);
            
            quantidade = pst.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return quantidade;
    }
}