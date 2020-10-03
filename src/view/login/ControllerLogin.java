package view.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.loginDao;
import dao.sindicoDao;
import entity.Sindico;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerLogin extends Application{
    @FXML
    private Button BTNCadastrar;

    @FXML
    private TextField TXTCpf;

    @FXML
    private TextField TXTSenha;

    @FXML
    private Button BTNEntrar;

    @FXML
    private Button BTNSair;

    @FXML
    void BTNEntra(ActionEvent event) {
    	

    	
    	String buscaCPF = TXTCpf.getText();
    	Sindico sindico = null;
    	if(!buscaCPF.equals("")) {
    		try {
    			//int id = Integer.valueOf(buscaCPF);
    			float id = Float.valueOf(buscaCPF);
    			sindico = new loginDao().findByID(id);
    		} catch (Exception e) {
    	
    		}
    		
        	if(TXTCpf.getText().equals("10820188999") && TXTSenha.getText().equals("123")) {
    		System.out.println("teste");
        	}
    	}
    }

    @FXML
    void BTNSairsistema(ActionEvent event) {

    }

    @Override
    public void start(Stage stage) {

        try {
            AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("frontSindico.fxml"));
            Scene sc = new Scene(pane);
            stage.setScene(sc);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }

	public void execute() {
		 launch();
	}

}
