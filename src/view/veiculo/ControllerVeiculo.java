package view.veiculo;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;



import dao.VeiculoDao;

import java.sql.Date;



import entity.Veiculo;
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

public class ControllerVeiculo extends Application implements Initializable{

	  @FXML
	    private TextField PrPlaca;

	    @FXML
	    private TextField PrModelo;

	    @FXML
	    private TextField PrCor;

	    @FXML
	    private Button BtSalvar;

	    @FXML
	    private Button BtEditar;

	    @FXML
	    private Button BtExcluir;

	    @FXML
	    private Button BTBuscarID;

	    @FXML
	    private TextField PrId;

	    @FXML
	    private TextField PrAno;

	    @FXML
	    private Label LabelLabel;

	    @FXML
	    private Label Labelid;

	    @FXML
	    private TextArea textAreaLista;
	    
    @FXML
    void buscarID(ActionEvent event) {
    	String idString = PrId.getText();
    	Veiculo veiculo = null;
    	if(!idString.equals("")) {
    		try {
    			int id = Integer.valueOf(idString);
    			veiculo = new VeiculoDao().findByID(id);
    		} catch (Exception e) {
    	
    		} 
    		if(veiculo != null) {
    			Labelid.setVisible(true);
    			LabelLabel.setVisible(true);
    			LabelLabel.setText(veiculo.getId()+  "");
    			PrPlaca.setText(veiculo.getPlacaveiculo());
    			PrModelo.setText(veiculo.getModeloveiculo());
    			PrCor.setText(veiculo.getCorveiculo());
    			PrAno.setText(veiculo.getAnoveiculo());
    			
    		}
    	}
    	PrId.clear();
    }
    
    @FXML
    void ExcluirVeiculo(ActionEvent event) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
  	 alert.setTitle("ATENÇÃO");
	 alert.setHeaderText("EXCLUIR DADOS");
	 alert.setContentText("VOCÊ TEM CERTEZA QUE DESEJA EXCLUIR O VEICULO?");
       Optional<ButtonType> result = alert.showAndWait();
       
        if (result.get() == ButtonType.OK){
        	System.out.println("olá");
            Veiculo veiculo= obtemDadosID();
            int qtde = new VeiculoDao().deletar(veiculo.getId());
            limpaCampo();
            listarVeiculo();
            
        }
    }

    @FXML
    void SalvarVeiculo(ActionEvent event) {
    	Veiculo veiculo = obtemDados();
    	limpaCampo();
    	int qtde = new VeiculoDao().inserir(veiculo);
    	listarVeiculo();
    	System.out.println(qtde);
    }
    
    @FXML 
    void EditarVeiculo(ActionEvent event) {
    	Veiculo veiculo = obtemDadosID();
    	limpaCampo();
    	int qtde = new VeiculoDao().alterar(veiculo);
    	listarVeiculo();
    }
    
    private void limpaCampo() {
    	PrPlaca.clear();
    	PrModelo.clear();
    	PrCor.clear();
    	PrAno.clear();

    	PrPlaca.requestFocus();
    	
    }
    
    
    private Veiculo obtemDados() {
    	return new Veiculo(PrPlaca.getText(), PrModelo.getText(), PrCor.getText(), PrAno.getText());
    }

    private Veiculo obtemDadosID() {
    	
    	return new Veiculo(Integer.valueOf(LabelLabel.getText()), PrPlaca.getText(), PrModelo.getText(), PrCor.getText(), PrAno.getText());
    }
    
    
    private void listarVeiculo() {
    	textAreaLista.clear();
    	List<Veiculo> listaVeiculo = new VeiculoDao().listAll();
    listaVeiculo.forEach(veiculo -> {
    	textAreaLista.appendText(veiculo.toString() +"\n");
    });
 
    }
    
    public void execute() {
        launch();
    }

    @Override
    public void start(Stage stage) {

        try {
            AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("VeiculoView.fxml"));
            Scene sc = new Scene(pane);
            stage.setScene(sc);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }   	

    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listarVeiculo();
		
	}	 
}