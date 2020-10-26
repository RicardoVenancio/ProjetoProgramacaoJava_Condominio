package view.sindico;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.sql.Date;

import dao.sindicoDao;
import entity.Sindico;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.login.ControllerLogin;

public class ControllerSindico implements Initializable{
//utilizar com o método de inicialização//public class ControllerSindico extends Application implements Initializable{


    @FXML
    private Button BTNSalvar;

    @FXML
    private Button BTNEditar;

    @FXML
    private Button BTNExcluir;

    @FXML
    private TextField TXNome;

    @FXML
    private TextField TXNascimento;

    @FXML
    private TextField TXSexo;

    @FXML
    private TextField TXEmail;

    @FXML
    private TextField TXTelefone;

    @FXML
    private TextField TXAdmissao;

    @FXML
    private TextArea textAreaLista;

    @FXML
    private TextField TXId;

    @FXML
    private Label Labelid;

    @FXML
    private Label LabelLabel;

    @FXML
    private Button BTNBuscarID;

    @FXML
    private DatePicker datePickerNascimento;
    
    @FXML
    private DatePicker datePickerAdmissao;

    

    @FXML
    void buscarID() {
    	String idString = TXId.getText();
    	Sindico sindico = null;
    	if(!idString.equals("")) {
    		try {
    			int id = Integer.valueOf(idString);
    			sindico = new sindicoDao().findByID(id);
    		} catch (Exception e) {
    	
    		}
    		if(sindico != null) {
    			Labelid.setVisible(true);
    			LabelLabel.setVisible(true);
    			LabelLabel.setText(sindico.getIdSINDICO()+  "");
    			TXNome.setText(sindico.getNomeSindico());
    			datePickerNascimento.setValue(sindico.getDataNascimento().toLocalDate());
    			TXSexo.setText(sindico.getSexoSindico());
    			TXEmail.setText(sindico.getEmailSindico());
    			TXTelefone.setText(sindico.getNumerotelefoneSindico());
    			datePickerAdmissao.setValue(sindico.getDataAdmissao().toLocalDate());
    		}
    	}
    	TXId.clear();
    }
    
    @FXML
    void ExcluirSindico(ActionEvent event) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
  	 alert.setTitle("ATENÇÃO");
	 alert.setHeaderText("EXCLUSÃO DE DADOS");
	 alert.setContentText("VOCÊ TEM CERTEZA QUE DESEJA EXCLUIR O USUÁRIO");
       Optional<ButtonType> result = alert.showAndWait();
       
        if (result.get() == ButtonType.OK){
            Sindico sindico= obtemDadosID();
            int qtde = new sindicoDao().deletar(sindico.getIdSINDICO());
            limpaCampo();
            listarSindicos();
        }
    }

    @FXML
    void SalvarSindico(ActionEvent event) {
    	Sindico sindico = obtemDados();
    	limpaCampo();
    	int qtde = new sindicoDao().inserir(sindico);
    	listarSindicos();
    	System.out.println(qtde);
    	navegacaoTelaLogin();
    	fecharTelaSindico();
    }
    
    @FXML
    void EditarSindico(ActionEvent event) {
    	Sindico sindico = obtemDadosID();
    	limpaCampo();
    	int qtde = new sindicoDao().alterar(sindico);
    	listarSindicos();
    }
    
    private void limpaCampo() {
    	TXNome.clear();
    	datePickerNascimento.setValue(null);
    	TXSexo.clear();
    	TXEmail.clear();
    	TXTelefone.clear();
    	datePickerAdmissao.setValue(null);
    			
    	TXNome.requestFocus();
    	
    	LabelLabel.setVisible(false);
    	LabelLabel.setText("");
    	Labelid.setVisible(false);
    }
    
    
    private Sindico obtemDados() {
    	return new Sindico(TXNome.getText(), java.sql.Date.valueOf(datePickerNascimento.getValue()), TXSexo.getText(), TXEmail.getText(), TXTelefone.getText(), java.sql.Date.valueOf(datePickerAdmissao.getValue()));
    }

    private Sindico obtemDadosID() {
    	
    	return new Sindico(Integer.valueOf(LabelLabel.getText()), TXNome.getText(), java.sql.Date.valueOf(datePickerNascimento.getValue()), TXSexo.getText(), TXEmail.getText(), TXTelefone.getText(), java.sql.Date.valueOf(datePickerAdmissao.getValue()));
    }
    
    private void listarSindicos() {
    	textAreaLista.clear();
    	List<Sindico> listaSindico = new sindicoDao().listAll();
    listaSindico.forEach(sindico -> {
    	textAreaLista.appendText(sindico.toString() +"\n");
    });
    }
    
    public void navegacaoTelaLogin() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ControllerLogin.class.getResource("frontLogin.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void fecharTelaSindico() {
    	Stage stage = (Stage) BTNSalvar.getScene().getWindow();
    	stage.close();
    }
    
//////////////////////////////////////////////////MÉTODO DE INICIALIZAÇÃO//////////////////////////////////////////////////////////
//    public void execute() {
//        launch();
//    }
//
//    @Override
//    public void start(Stage stage) {
//
//        try {
//            AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("frontSindico.fxml"));
//            Scene sc = new Scene(pane);
//            stage.setScene(sc);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }   
//    }   	

    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listarSindicos();
		
	}	 
}