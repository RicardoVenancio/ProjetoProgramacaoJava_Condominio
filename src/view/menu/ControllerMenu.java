package view.menu;

import java.net.URL;
import java.util.ResourceBundle;

import dao.loginDao;
import view.login.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControllerMenu implements Initializable{

    @FXML
    private Label LBTeste;

    @FXML
    private TextField TXTTeste;

    @FXML
    private Button BTNTeste;
    
    
    
    ///////////////////////////////TESTE/////////////////////////////////////
    @FXML
    private Button BTNAparecer;

    @FXML
    void BTNAparecimento(ActionEvent event) {
 		LBTeste.setVisible(true);
    	TXTTeste.setVisible(true);
    	BTNTeste.setVisible(true);
    }
    ///////////////////////////////TESTE/////////////////////////////////////
    
    
    
    public void visibilidadeCadastro(String teste1, String teste2) {
    	loginDao logindao = new loginDao();
     	
    	String resultado = logindao.authenticateUser(teste1, teste2);
    	
    	if(resultado.isEmpty()) {
    		LBTeste.setVisible(false);
        	TXTTeste.setVisible(false);
        	BTNTeste.setVisible(false);
    	}
    	
    	//CRIAÇÃO NOVA BRANCH
    	else {
    		LBTeste.setVisible(true);
        	TXTTeste.setVisible(true);
        	BTNTeste.setVisible(true);
    	}
    	
    }
	public void initialize(URL location, ResourceBundle resources) {

		visibilidadeCadastro(TXTTeste.getText(), LBTeste.getText());
	}	 

}
