package view.morador;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.moradorDao;
import entity.Morador;
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
import view.morador.MoradorController;

public class MoradorController extends Application implements Initializable {

	@FXML
	private Label lblTitleId;

	@FXML
	private Label lblNumId;

	@FXML
	private Label lblNome;

	@FXML
	private Label lblTelefone;

	@FXML
	private Label lblEmail;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtTelefone;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtSearchId;

	@FXML
	private TextArea txtAreaMorador;

	@FXML
	private Button btnCreate;

	@FXML
	private Button btnUpdate;

	@FXML
	private Button btnDelete;

	@FXML
	private Button btnSearch;

	@FXML
	void cadastrarMorador(ActionEvent event) {
		Morador morador = getData();
		clearFields();
		int qtde = new moradorDao().create(morador);
		listarMorador();
	}

	@FXML
	void editarMorador(ActionEvent event) {
		Morador morador = getDataById();
		clearFields();
		int qtde = new moradorDao().edit(morador);
		listarMorador();
	}

	@FXML
	void excluirMorador(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Atençao");
		alert.setHeaderText("");
		alert.setContentText("Deseja realmente excluir o cadastro ID " + lblNumId.getText() + "?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			Morador morador = getDataById();
			int qtde = new moradorDao().delete(morador.getIdMorador());
			clearFields();
			listarMorador();
		}
	}

	@FXML
	void pesquisarMorador(ActionEvent event) {
		String idString = txtSearchId.getText();
		Morador morador = null;
		if (!idString.equals("IDNUM")) {
			try {
				int id = Integer.valueOf(idString);
				morador = new moradorDao().findByID(id);
			} catch (Exception e) {

			}
			if (morador != null) {
				lblNumId.setVisible(true);
				lblTitleId.setVisible(true);
				lblNumId.setText(morador.getIdMorador() + "");
				txtNome.setText(morador.getNomeMorador());
				txtTelefone.setText(morador.getTelefoneMorador());
				txtEmail.setText(morador.getEmailMorador());
			}
		}
		txtSearchId.clear();
	}

	private void clearFields() {
		txtNome.clear();
		txtTelefone.clear();
		txtEmail.clear();
		txtNome.requestFocus();

		lblTitleId.setVisible(false);
		lblNumId.setText("");
		lblNumId.setVisible(false);
	}

	private Morador getData() {
		return new Morador(txtNome.getText(), txtTelefone.getText(), txtEmail.getText());
	}

	private Morador getDataById() {

		return new Morador(Integer.valueOf(lblNumId.getText()), txtNome.getText(), txtTelefone.getText(),
				txtEmail.getText());
	}

	private void listarMorador() {
		txtAreaMorador.clear();
		List<Morador> listaMorador = new moradorDao().listAll();
		listaMorador.forEach(morador -> {
			txtAreaMorador.appendText(morador.toString() + "\n");
		});
	}

	public void execute() {
		launch();
	}

	@Override
	public void start(Stage stage) {

		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("MoradorView.fxml"));
			Scene sc = new Scene(pane);
			stage.setScene(sc);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listarMorador();
	}

}
