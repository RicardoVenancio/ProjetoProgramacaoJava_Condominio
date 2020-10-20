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
    @FXML
    private Label LabelTESTE;
        
    
//    public void visibilidadeCadastro(String teste1, String teste2) {
//    	loginDao logindao = new loginDao();
//     	
//    	String resultado = logindao.authenticateUser(teste1, teste2);
//    	
//    	if(resultado == "Funcionario") {
//    		LabelTESTE.setVisible(true);
//    		LBTeste.setVisible(false);
//        	TXTTeste.setVisible(false);
//        	BTNTeste.setVisible(false);
//    	}
//    	if(resultado == null) {
//    		System.out.println("NULL");
//    		
//    	}
// 
//
//    }
	public void initialize(URL location, ResourceBundle resources) {

	//	visibilidadeCadastro(TXTTeste.getText(), LBTeste.getText());
	}	 

}
