package view.recado;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import alerts.ShowAlert;
import dao.RecadoDao;
import entity.Recado;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.funcionario.FuncionarioTable;
import view.login.ControllerLogin;
import view.menu.ControllerMenuTable;
import view.proprietario.ControllerPropTable;
import view.visitante.ControllerTable;

public class ControllerRecadoTable implements Initializable {

	@FXML
	private Pane paneList;

	@FXML
	private Label numRecados;

	@FXML
	private Label lastVisit;

	@FXML
	private JFXButton btnCadastrar;

	@FXML
	private JFXButton btnEditar;

	@FXML
	private JFXButton btnApagar;

	@FXML
	private TableView<Recado> TableView;

	@FXML
	private TableColumn<Recado, String> colNome;

	@FXML
	private TableColumn<Recado, String> colData;

	@FXML
	private TableColumn<Recado, String> colRecado;

	@FXML
	private TextField txtBuscar;

	@FXML
	private Label Fechar;

	@FXML
	private Pane paneCadastro;

	@FXML
	private JFXTextField PrNome;

	@FXML
	private JFXDatePicker datePickerRecado;

	@FXML
	private JFXTextField PrRecado;

	@FXML
	private Label LabelLabel;

	@FXML
	private JFXButton BTNEditar;

	@FXML
	private JFXButton BTNSalvar;

	@FXML
	private Button btnFuncionario;

	@FXML
	private Button btnVisitante;

	@FXML
	private Button btnProprietario;

	@FXML
	private Button btnMenus;

	@FXML
	private Button btnRecados;

	@FXML
	private Button btnSettings;

	@FXML
	private Button btnSignout;

	@FXML
	void findByName(ActionEvent event) {

	}

	@FXML
	void Salvar(ActionEvent event) {
		if (validaCampos()) {
			Recado recado = obtemDados();
			limpaCampo();
			int qtde = new RecadoDao().inserir(recado);
			StartTable();
			paneList.toFront();
			System.out.println(qtde);
		} else {
			new ShowAlert().validaAlert();
		}
	}

	@FXML
	void Editar(ActionEvent event) {
		if (validaCampos()) {
			Recado recado = obtemDadosID();
			limpaCampo();
			int qtde = new RecadoDao().alterar(recado);
			StartTable();
			paneList.toFront();
		} else {
			new ShowAlert().validaAlert();
		}
	}

	@FXML
	void Excluir(ActionEvent event) {
		boolean v = TableView.getSelectionModel().isEmpty();
		if (v == true) {
			new ShowAlert().SelecionarPessoa();
		} else {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Alerta!");
			alert.setHeaderText("Dados à serem apagados.");
			alert.setContentText("Pressione OK para apagar este usuario.");
			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == ButtonType.OK) {
				System.out.println("Cadastro Apagado");
				Recado recado = obtemDadosIDDeletar();
				int qtde = new RecadoDao().deletar(recado.getId());
				limpaCampo();
				StartTable();
			}
		}
	}

	void EditarCadastro() {
		Recado v = TableView.getSelectionModel().getSelectedItem();
		LabelLabel.setText(Integer.toString(v.getId()));
		PrNome.setText(v.getNome());
		PrRecado.setText(v.getTexto());
		datePickerRecado.setValue(v.getDatarecado().toLocalDate());
	}

	private void limpaCampo() {
		PrNome.clear();
		PrRecado.clear();
		datePickerRecado.setValue(null);

	}

	private Recado obtemDados() {
		return new Recado(PrNome.getText(), PrRecado.getText(), java.sql.Date.valueOf(datePickerRecado.getValue()));
	}

	private Recado obtemDadosID() {
		return new Recado(Integer.valueOf(LabelLabel.getText()), PrNome.getText(), PrRecado.getText(),
				java.sql.Date.valueOf(datePickerRecado.getValue()));
	}

	private Recado obtemDadosIDDeletar() {
		Recado v = TableView.getSelectionModel().getSelectedItem();
		LabelLabel.setText(Integer.toString(v.getId()));
		PrNome.setText(v.getNome());
		PrRecado.setText(v.getTexto());
		datePickerRecado.setValue(v.getDatarecado().toLocalDate());

		return new Recado(Integer.valueOf(LabelLabel.getText()), PrNome.getText(), PrRecado.getText(),
				java.sql.Date.valueOf(datePickerRecado.getValue()));
	}

	public boolean validaCampos() {
		if (PrNome.getText().isEmpty() | PrRecado.getText().isEmpty()) {
			return false;
		}
		return true;
	}

	// Sair ou fechar o programa
	public void Exit() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Alerta!");
		alert.setHeaderText("Sair");
		alert.setContentText("Deseja sair do programa?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			System.exit(1);
		}
	}

	// Listar cadastros na TableView
	public void StartTable() {
		List<Recado> list = new RecadoDao().listAll();
		colNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		colData.setCellValueFactory(new PropertyValueFactory<>("Datarecado"));
		colRecado.setCellValueFactory(new PropertyValueFactory<>("Texto"));
		TableView.setItems(atualizaTabela());
		numRecados.setText(Integer.toString(list.size()));

	}

	// Converter para Collections
	public ObservableList<Recado> atualizaTabela() {
		RecadoDao dao = new RecadoDao();
		return FXCollections.observableArrayList(dao.listAll());
	}

//-------------------------------------------------------------------------------------------------
	// Executar Tela
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		StartTable();
	}

//	public void execute() {
//		launch();
//	}
//
//	private double x, y;
//
//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		Parent root = FXMLLoader.load(getClass().getResource("RecadoTable.fxml"));
//		primaryStage.setScene(new Scene(root));
//		// set stage borderless
//		primaryStage.initStyle(StageStyle.UNDECORATED);
//
//		// drag it here
//		root.setOnMousePressed(event -> {
//			x = event.getSceneX();
//			y = event.getSceneY();
//		});
//		root.setOnMouseDragged(event -> {
//
//			primaryStage.setX(event.getScreenX() - x);
//			primaryStage.setY(event.getScreenY() - y);
//
//		});
//		primaryStage.show();
//	}

//-------------------------------------------------------------------------------------------------
	public void handleClicks(ActionEvent actionEvent) throws IOException, InterruptedException  {
		if (actionEvent.getSource() == btnRecados) {
			paneList.toFront();
		}
		if (actionEvent.getSource() == btnMenus) {
				FXMLLoader fxmlLoader = new FXMLLoader(ControllerMenuTable.class.getResource("MenuTable.fxml"));
				abrirNovaTela(fxmlLoader);
				Stage stage = (Stage) btnMenus.getScene().getWindow();
				stage.close();
		}
		if (actionEvent.getSource() == btnVisitante) {
			FXMLLoader fxmlLoader = new FXMLLoader(ControllerTable.class.getResource("VisitanteTable.fxml"));
			abrirNovaTela(fxmlLoader);
			Stage stage = (Stage) btnVisitante.getScene().getWindow();
			stage.close();
		}
		if (actionEvent.getSource() == btnFuncionario) {

				FXMLLoader fxmlLoader = new FXMLLoader(FuncionarioTable.class.getResource("FuncionarioTable.fxml"));
				abrirNovaTela(fxmlLoader);
				Stage stage = (Stage) btnFuncionario.getScene().getWindow();
				stage.close();
		}
		if (actionEvent.getSource() == btnProprietario) {

				FXMLLoader fxmlLoader = new FXMLLoader(ControllerPropTable.class.getResource("ProprietarioTable.fxml"));
				abrirNovaTela(fxmlLoader);
				Stage stage = (Stage) btnProprietario.getScene().getWindow();
				stage.close();
		}
		if (actionEvent.getSource() == btnSignout) {
			Thread.sleep(1500);
				FXMLLoader fxmlLoader = new FXMLLoader(ControllerLogin.class.getResource("telafront.fxml"));
				abrirNovaTela(fxmlLoader);
				Stage stage = (Stage) btnSignout.getScene().getWindow();
				stage.close();
		}
		if (actionEvent.getSource() == btnCadastrar) {
			BTNSalvar.toFront();
			BTNSalvar.setVisible(true);
			BTNEditar.setVisible(false);
			paneCadastro.toFront();
		}
		if (actionEvent.getSource() == btnEditar) {
			boolean v = TableView.getSelectionModel().isEmpty();
			System.out.println(v);
			if (v == true) {
				new ShowAlert().SelecionarPessoaEditar();
			} else {
				BTNEditar.toFront();
				BTNEditar.setVisible(true);
				BTNSalvar.setVisible(false);
				paneCadastro.toFront();
				EditarCadastro();
			}
		}
	}

	void abrirNovaTela(FXMLLoader y) throws IOException {
		FXMLLoader fxmlLoader = y;
		Parent root1 = fxmlLoader.load();
		Stage stage = new Stage();
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setScene(new Scene(root1));
		stage.show();
	}

}
