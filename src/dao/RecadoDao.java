package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import db.ConexaoHSQLDB;

import entity.Funcionario;
import entity.Recado;
 
public class RecadoDao extends ConexaoHSQLDB{
	final String SQL_INSERT_RECADO = "INSERT INTO RECADO(NOME, TEXTO, DATARECADO) VALUES (?,?,?)";
	final String SQL_SELECT_RECADO = "SELECT * FROM RECADO";
	final String SQL_SELECT_RECADO_ID = "SELECT * FROM RECADO WHERE ID = ?";
	final String SQL_ALTERA_RECADO = "UPDATE RECADO SET NOME=?, TEXTO=? , DATARECADO=? WHERE ID = ?";
	final String SQL_DELETA_RECADO = "DELETE FROM RECADO WHERE ID = ?";

public int inserir(Recado recado) {
	int quantidade = 0;

	//INSERIR
try (Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_INSERT_RECADO);) {
		pst.setString(1, recado.getNome());
		pst.setString(2, recado.getTexto());
		pst.setDate(3,java.sql.Date.valueOf(recado.getDatarecado().toString()));
		
		quantidade = pst.executeUpdate();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
 
	return quantidade;
	}
//LISTAR
public List<Recado> listAll(){
	List<Recado> listaRecado = new ArrayList<>();
	
	try (Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_SELECT_RECADO);){

		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			Recado recado = new Recado();
			
			recado.setId(rs.getInt("ID"));
			recado.setNome(rs.getString("NOME"));
			recado.setTexto(rs.getString("TEXTO"));
			recado.setDatarecado(rs.getDate("DATARECADO"));
			
			listaRecado.add(recado);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return listaRecado;
	}


//BUSCAR PELO ID
public Recado findByID(int idRecado) {
	Recado recado = null;
	try (Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_SELECT_RECADO_ID);){

		pst.setInt(1, idRecado);
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			recado = new Recado();
			
			recado.setId(rs.getInt("ID"));
			recado.setNome(rs.getString("NOME"));
			recado.setTexto(rs.getString("TEXTO"));
			recado.setDatarecado(rs.getDate("DATARECADO"));
				
			}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return recado;
	}
 
	//ALTERAR 
	public int alterar(Recado recado) {
		int quantidade = 0;

		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_ALTERA_RECADO);) {
			pst.setString(1, recado.getNome());
			pst.setString(2, recado.getTexto());
			pst.setDate(3, java.sql.Date.valueOf(recado.getDatarecado().toString()));
			pst.setInt(4, recado.getId());

			quantidade = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return quantidade;
		}

	//DELETAR 
public int deletar(int idRecado) {
        int quantidade = 0;
        try (Connection connection = this.conectar();
                PreparedStatement pst = connection.prepareStatement(SQL_DELETA_RECADO);) {
            pst.setInt(1, idRecado);
            
            quantidade = pst.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return quantidade;
    }
}