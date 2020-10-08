package view.recado;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


import dao.RecadoDao;

import java.sql.Date;

import dao.RecadoDao;
import entity.Recado;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class ControllerRecado extends Application implements Initializable{

	@FXML
    private TextField PrNome;

    @FXML
    private TextField PrRecado;

    @FXML
    private DatePicker datePickerRecado;

    @FXML
    private TextArea textAreaLista;

    @FXML
    private Button BtSalvar;

    @FXML
    private Button BtEditar;

    @FXML
    private Button BtExcluir;

    @FXML
    private TextField PrId;

    @FXML
    private Button BTBuscarID;
    
    @FXML
    private Label Labelid;

    @FXML
    private Label LabelLabel;


    @FXML
    void buscarID(ActionEvent event) {
    	String idString = PrId.getText();
    	Recado recado = null;
    	if(!idString.equals("")) {
    		try {
    			int id = Integer.valueOf(idString);
    			recado = new RecadoDao().findByID(id);
    		} catch (Exception e) {
    	
    		} 
    		if(recado != null) {
    			Labelid.setVisible(true);
    			LabelLabel.setVisible(true);
    			LabelLabel.setText(recado.getId()+  "");
    			PrNome.setText(recado.getNome());
    			PrRecado.setText(recado.getTexto());
    			datePickerRecado.setValue(recado.getDatarecado().toLocalDate());
    		}
    	}
    	PrId.clear();
    }
    
    @FXML
    void ExcluirRecado(ActionEvent event) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
  	 alert.setTitle("ATENÇÃO");
	 alert.setHeaderText("EXCLUIR DADOS");
	 alert.setContentText("VOCÊ TEM CERTEZA QUE DESEJA EXCLUIR O RECADO?");
       Optional<ButtonType> result = alert.showAndWait();
       
        if (result.get() == ButtonType.OK){
        	System.out.println("olá");
            Recado recado= obtemDadosID();
            int qtde = new RecadoDao().deletar(recado.getId());
            limpaCampo();
            listarRecado();
            
        }
    }

    @FXML
    void SalvarFuncionario(ActionEvent event) {
    	Recado recado = obtemDados();
    	limpaCampo();
    	int qtde = new RecadoDao().inserir(recado);
    	listarRecado();
    	System.out.println(qtde);
    }
    
    @FXML
    void EditarFuncionario(ActionEvent event) {
    	Recado recado = obtemDadosID();
    	limpaCampo();
    	int qtde = new RecadoDao().alterar(recado);
    	listarRecado();
    }
    
    private void limpaCampo() {
    	PrNome.clear();
    	PrRecado.clear();
    	datePickerRecado.setValue(null); 
    	
    			
    	PrNome.requestFocus();
    	
    	
    }
    
    
    private Recado obtemDados() {
    	return new Recado(PrNome.getText(), PrRecado.getText(), java.sql.Date.valueOf(datePickerRecado.getValue()));
    }

    private Recado obtemDadosID() {
    	
    	return new Recado(Integer.valueOf(LabelLabel.getText()), PrNome.getText(), PrRecado.getText(), java.sql.Date.valueOf(datePickerRecado.getValue()));
    }
    
    
    private void listarRecado() {
    	textAreaLista.clear();
    	List<Recado> listaRecado = new RecadoDao().listAll();
    listaRecado.forEach(recado -> {
    	textAreaLista.appendText(recado.toString() +"\n");
    });
 
    }
    
    public void execute() {
        launch();
    }

    @Override
    public void start(Stage stage) {

        try {
            AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("RecadoView.fxml"));
            Scene sc = new Scene(pane);
            stage.setScene(sc);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }   	

    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listarRecado();
		
	}	 
}