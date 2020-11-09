package view.apartamento;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.ApartamentoDAO;
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
import javafx.stage.Stage;
import entity.Apartamento;

public class ApartamentoController extends Application implements Initializable {

    @FXML
    private AnchorPane anchor;

    @FXML
    private Label lblNumeroAp;

    @FXML
    private Label lblQtdMorador;

    @FXML
    private Label lblAnimal;

    @FXML
    private TextField txtNumAp;

    @FXML
    private TextField txtQtdMorador;

    @FXML
    private TextField txtAnimal;

    @FXML
    private TextField txtSearchId;

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSearch;

    @FXML
    private Label lblTitleId;

    @FXML
    private Label lblNumId;

    @FXML
    private Button btnUpdate;

    @FXML
    private Label lblQtdAnimal;

    @FXML
    private TextField txtQtdAnimal;

    @FXML
    private TextField txtAndar;

    @FXML
    private TextField txtBloco;

    @FXML
    private Label lblAndar;

    @FXML
    private Label lblBloco;

    @FXML
    private Label lblData;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblVaga;

    @FXML
    private TextField txtStatus;

    @FXML
    private TextField txtVaga;

    @FXML
    private TextArea txtAreaApartamento;

    @FXML
    private DatePicker datePicker;

    @FXML
    void cadastrarApartamento(ActionEvent event) {
	  	Apartamento apartamento = getData(); 
    	clearFields();
    	int qtde = new ApartamentoDAO().createAp(apartamento);
    	listarApartamento();
    }

    @FXML
    void editarApartamento(ActionEvent event) {
		Apartamento apartamento = getDataById();
		clearFields();
    	int qtde = new ApartamentoDAO().editAp(apartamento);
    	listarApartamento();
    }

    @FXML
    void excluirApartamento(ActionEvent event) {
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Atençao");
		alert.setHeaderText("");
		alert.setContentText("Deseja realmente excluir o cadastro ID " + lblNumId.getText() + "?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			Apartamento apartamento = getDataById();
			int qtde = new ApartamentoDAO().deleteAp(apartamento.getIdAp());
			clearFields();
			listarApartamento();
		}
    }
	
	//public Apartamento(int idAp, int numAp, int qtdMorador, char animalEstimacao, int qtdAnimal, int andarAp,
	//		int blocoAp, Date dataEntrada, String statusAp, int vagaVeiculo) {
    @FXML
    void pesquisarApartamento(ActionEvent event) {
		String idString = txtSearchId.getText();
		Apartamento apartamento = null;
		if (!idString.equals("IDNUM")) {
			try {
				int id = Integer.valueOf(idString);
				apartamento = new ApartamentoDAO().findByIdAp(id);
			} catch (Exception e) {

			}
			if (apartamento != null) {
				lblNumId.setVisible(true);
				lblTitleId.setVisible(true);
				lblNumId.setText(apartamento.getIdAp() + "");
				txtNumAp.setText(apartamento.getNumAp() +"");
				txtQtdMorador.setText(apartamento.getQtdMorador() +"");
				txtAnimal.setText(apartamento.getAnimalEstimacao() +"");
				txtQtdAnimal.setText(apartamento.getQtdAnimal() +"");
				txtAndar.setText(apartamento.getAndarAp() +"");
				txtBloco.setText(apartamento.getBlocoAp() +"");
				datePicker.setValue(apartamento.getDataEntrada().toLocalDate());
				txtStatus.setText(apartamento.getStatusAp());
				txtVaga.setText(apartamento.getVagaVeiculo() +"");
			}
			
		}
		txtSearchId.clear();
    }
    

	
	private void clearFields() {
		txtNumAp.clear();
		txtQtdMorador.clear();
		txtQtdAnimal.clear();
		datePicker.setValue(null);;
		txtAnimal.clear();
		txtAndar.clear();
		txtBloco.clear();
		txtStatus.clear();
		txtVaga.clear();
		lblNumId.setText("");
		lblNumId.setVisible(false);
		lblTitleId.setVisible(false);		
		txtNumAp.requestFocus();
	}

	private Apartamento getData() {
		return new Apartamento(Integer.valueOf(txtNumAp.getText()), Integer.valueOf(txtQtdMorador.getText()),
				String.valueOf(txtAnimal.getText()).charAt(0), Integer.valueOf(txtQtdAnimal.getText()),
				Integer.valueOf(txtAndar.getText()), Integer.valueOf(txtBloco.getText()),
				java.sql.Date.valueOf(datePicker.getValue()), txtStatus.getText(), Integer.valueOf(txtVaga.getText()));
	}

	private Apartamento getDataById() {

		return new Apartamento(Integer.valueOf(lblNumId.getText()), Integer.valueOf(txtNumAp.getText()),
				Integer.valueOf(txtQtdMorador.getText()), String.valueOf(txtAnimal.getText()).charAt(0),
				Integer.valueOf(txtQtdAnimal.getText()), Integer.valueOf(txtAndar.getText()),
				Integer.valueOf(txtBloco.getText()), java.sql.Date.valueOf(datePicker.getValue()), txtStatus.getText(),
				Integer.valueOf(txtVaga.getText()));
	}

	private void listarApartamento() {
		txtAreaApartamento.clear();
		List<Apartamento> listaMorador = new ApartamentoDAO().listAllAp();
		listaMorador.forEach(apartamento -> {
			txtAreaApartamento.appendText(apartamento.toString() + "\n");
		});
	}

	public void execute() {
		launch();
	}

	@Override
	public void start(Stage stage) {

		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("ApartamentoView.fxml"));
			Scene sc = new Scene(pane);
			stage.setScene(sc);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listarApartamento();
	}
}
