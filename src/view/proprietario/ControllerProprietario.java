package view.proprietario;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import alerts.ShowAlert;
import dao.proprietarioDao;
import entity.Proprietario;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerProprietario extends Application implements Initializable{

    @FXML
    private TextField TxtNome;

    @FXML
    private TextField TxtFone;

    @FXML
    private TextField TxtEmail;

    @FXML
    private TextField TxtCpf;
    
    @FXML
    private Label LabelLabel;


    @FXML
    private TextField TxtRg;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnExcluir;

    @FXML
    private TextArea TextArea;

    @FXML
    private TextField TxtID;

    @FXML
    void Editar(ActionEvent event) {
    	if(validaCampos()) {
	    	Proprietario proprietario = obtemDadosID();
	    	limpaCampo();
	    	int qtde = new proprietarioDao().alterar(proprietario);
	    	listarProprietarios();
    	}else {
    	new ShowAlert().validaAlert();
    	}
    }

    @FXML
    void Excluir(ActionEvent event) {
    	 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      	 alert.setTitle("Alerta!");
    	 alert.setHeaderText("Dados à serem apagados.");
    	 alert.setContentText("Pressione OK para apagar este usuario.");
           Optional<ButtonType> result = alert.showAndWait();
           
            if (result.get() == ButtonType.OK){
            	System.out.println("Cadastro Apagado");
                Proprietario proprietario = obtemDadosID();
                int qtde = new proprietarioDao().deletar(proprietario.getIdProprietario());
                limpaCampo();
                listarProprietarios();
            }
    }

    private void listarProprietarios() {
    	TextArea.clear();
    	List<Proprietario> listaProprietario = new proprietarioDao().listAll();
    listaProprietario.forEach(proprietario -> {
    	TextArea.appendText(proprietario.toString() +"\n");
    });
    	
	}

	@FXML
    void Salvar(ActionEvent event) {
		if(validaCampos()) {
	    	Proprietario proprietario = obtemDados();
	    	limpaCampo();
	    	int qtde = new proprietarioDao().inserir(proprietario);
	    	listarProprietarios();
	    	System.out.println(qtde);
    	}else {
    	new ShowAlert().validaAlert();
    	}
    }

    @FXML
    void buscarID(ActionEvent event) {
    	String idString = TxtID.getText();
    	Proprietario proprietario = null;
    	if(!idString.equals("")) {
    		try {
    			int id = Integer.valueOf(idString);
    			proprietario = new proprietarioDao().findByID(id);
    		} catch (Exception e) {
    	
    		}
    		if(proprietario != null) {
    			LabelLabel.setText(proprietario.getIdProprietario()+  "");
    			TxtNome.setText(proprietario.getNomeProprietario());
    			TxtRg.setText(proprietario.getRgProprietario());
    			TxtCpf.setText(proprietario.getCpfProprietario());
    			TxtFone.setText(proprietario.getNumerotelefoneProprietario());
    			TxtEmail.setText(proprietario.getEmailProprietario());
    		}
    	}
    	TxtID.clear();
    }


	private void limpaCampo() {
		TxtNome.clear();
		TxtRg.clear();
		TxtCpf.clear();
		TxtFone.clear();
		TxtEmail.clear();				
		TxtNome.requestFocus();
		
	}

	private Proprietario obtemDados() {
    	return new Proprietario(TxtNome.getText(),TxtRg.getText(), TxtCpf.getText(), TxtFone.getText(), TxtEmail.getText());
    }

    private Proprietario obtemDadosID() {
    	
    	return new Proprietario(Integer.valueOf(LabelLabel.getText()), TxtNome.getText(), TxtRg.getText(), TxtCpf.getText(), TxtFone.getText(), TxtEmail.getText());
    }
    
    public boolean validaCampos() {	
  		if(TxtNome.getText().isEmpty() | TxtRg.getText().isEmpty() | TxtCpf.getText().isEmpty() | TxtFone.getText().isEmpty() | TxtEmail.getText().isEmpty()) {
  			return false;
  		}	
  		return true;
  	}
    
    public void execute() {
        launch();
    }

    @Override
    public void start(Stage stage) {

        try {
            AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("ProprietarioView.fxml"));
            Scene sc = new Scene(pane);
            stage.setScene(sc);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }   	
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listarProprietarios();
		btnAlterar.setDefaultButton(false);
		
	}	 
    
}
