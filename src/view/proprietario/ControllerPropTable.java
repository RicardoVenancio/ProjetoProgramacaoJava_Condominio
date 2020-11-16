package view.proprietario;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import alerts.ShowAlert;
import dao.proprietarioDao;
import dao.visitanteDao;
import entity.Proprietario;
import entity.Visitante;
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
import view.recado.ControllerRecadoTable;
import view.visitante.ControllerTable;

public class ControllerPropTable implements Initializable {

	@FXML
	private Pane paneCadastro;

	@FXML
	private JFXTextField TxtRg;

	@FXML
	private JFXTextField TxtFone;

	@FXML
	private JFXTextField TxtCpf;

	@FXML
	private JFXTextField TxtEmail;

	@FXML
	private Label LabelLabel;

	@FXML
	private JFXTextField TxtNome;

	@FXML
	private JFXButton btnAlterar;

	@FXML
	private JFXButton btnSalvar;

	@FXML
	private Pane paneList;

	@FXML
	private Label numProprietarios;

	@FXML
	private Label lastVisit;

	@FXML
	private JFXButton btnCadastrar;

	@FXML
	private JFXButton btnVoltar;

	@FXML
	private JFXButton BTNEditar;

	@FXML
	private JFXButton btnApagar;

	@FXML
	private TableView<Proprietario> TableView;

	@FXML
	private TableColumn<Proprietario, String> tcNome;

	@FXML
	private TableColumn<Proprietario, String> tcCpf;

	@FXML
	private TableColumn<Proprietario, String> tcRg;

	@FXML
	private TableColumn<Proprietario, String> tcTelefone;

	@FXML
	private TableColumn<Proprietario, String> tcEmail;

	@FXML
	private TextField txtBuscar;

	@FXML
	private Label Fechar;

	@FXML
	private Button btnFuncionario;

	@FXML
	private Button btnVisitante;

	@FXML
	private Button btnRecado;

	@FXML
	private Button btnProprietario;

	@FXML
	private Button btnMenus;

	@FXML
	private Button btnPackages;

	@FXML
	private Button btnSettings;

	@FXML
	private Button btnSignout;

	@FXML
	void findByName(ActionEvent event) {
		if (txtBuscar.getText().equals("")) {
			StartTable();
		} else {
			StartTable2();
			txtBuscar.setText("");
		}
	}

	@FXML
	void Salvar(ActionEvent event) {
		if (validaCampos()) {
			Proprietario proprietario = obtemDados();
			limpaCampo();
			int qtde = new proprietarioDao().inserir(proprietario);
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
			Proprietario proprietario = obtemDadosID();
			limpaCampo();
			int qtde = new proprietarioDao().alterar(proprietario);
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
				Proprietario proprietario = obtemDadosIDDeletar();
				int qtde = new proprietarioDao().deletar(proprietario.getIdProprietario());
				limpaCampo();
				StartTable();
			}
		}
	}

	void EditarCadastro() {
		Proprietario v = TableView.getSelectionModel().getSelectedItem();
		LabelLabel.setText(Integer.toString(v.getIdProprietario()));
		TxtNome.setText(v.getNome());
		TxtRg.setText(v.getRgProprietario());
		TxtCpf.setText(v.getCpfProprietario());
		TxtFone.setText(v.getTelefone());
		TxtEmail.setText(v.getEmailProprietario());
	}

	private void limpaCampo() {
		TxtNome.clear();
		TxtRg.clear();
		TxtCpf.clear();
		TxtFone.clear();
		TxtEmail.clear();

	}

	private Proprietario obtemDados() {
		return new Proprietario(TxtNome.getText(), TxtCpf.getText(), TxtRg.getText(), TxtFone.getText(),
				TxtEmail.getText());
	}

	private Proprietario obtemDadosID() {
		return new Proprietario(Integer.valueOf(LabelLabel.getText()), TxtNome.getText(), TxtRg.getText(),
				TxtCpf.getText(), TxtFone.getText(), TxtEmail.getText());

	}

	private Proprietario obtemDadosIDDeletar() {
		Proprietario v = TableView.getSelectionModel().getSelectedItem();
		LabelLabel.setText(Integer.toString(v.getIdProprietario()));
		TxtNome.setText(v.getNome());
		TxtRg.setText(v.getRgProprietario());
		TxtCpf.setText(v.getCpfProprietario());
		TxtFone.setText(v.getTelefone());
		TxtEmail.setText(v.getEmailProprietario());

		return new Proprietario(Integer.valueOf(LabelLabel.getText()), TxtNome.getText(), TxtRg.getText(),
				TxtCpf.getText(), TxtFone.getText(), TxtEmail.getText());
	}

	public boolean validaCampos() {
		if (TxtNome.getText().isEmpty() | TxtRg.getText().isEmpty() | TxtCpf.getText().isEmpty()
				| TxtFone.getText().isEmpty() | TxtEmail.getText().isEmpty()) {
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
		List<Proprietario> list = new proprietarioDao().listAll();
		Proprietario x = new proprietarioDao().ultimoCadastro();
		tcNome.setCellValueFactory(new PropertyValueFactory<>("nomeProprietario"));
		tcCpf.setCellValueFactory(new PropertyValueFactory<>("cpfProprietario"));
		tcRg.setCellValueFactory(new PropertyValueFactory<>("rgProprietario"));
		tcTelefone.setCellValueFactory(new PropertyValueFactory<>("numerotelefoneProprietario"));
		tcEmail.setCellValueFactory(new PropertyValueFactory<>("emailProprietario"));
		TableView.setItems(atualizaTabela());
		numProprietarios.setText(Integer.toString(list.size()));
		lastVisit.setText(x.getNomeProprietario());
	}

	// Converter para Collections
	public ObservableList<Proprietario> atualizaTabela() {
		proprietarioDao dao = new proprietarioDao();
		return FXCollections.observableArrayList(dao.listAll());
	}

	public void StartTable2() {
		List<Proprietario> list = new proprietarioDao().listAllName(txtBuscar.getText());
		tcNome.setCellValueFactory(new PropertyValueFactory<>("nomeProprietario"));
		tcCpf.setCellValueFactory(new PropertyValueFactory<>("cpfProprietario"));
		tcRg.setCellValueFactory(new PropertyValueFactory<>("rgProprietario"));
		tcTelefone.setCellValueFactory(new PropertyValueFactory<>("numerotelefoneProprietario"));
		tcEmail.setCellValueFactory(new PropertyValueFactory<>("emailProprietario"));
		TableView.setItems(atualizaTabela2());
		numProprietarios.setText(Integer.toString(list.size()));
	}

	// Converter para Collections
	public ObservableList<Proprietario> atualizaTabela2() {
		proprietarioDao dao = new proprietarioDao();
		return FXCollections.observableArrayList(dao.listAllName(txtBuscar.getText()));
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
//		Parent root = FXMLLoader.load(getClass().getResource("ProprietarioTable.fxml"));
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
	public void handleClicks(ActionEvent actionEvent) throws IOException {
		if (actionEvent.getSource() == btnVoltar) {
			paneList.toFront();
			limpaCampo();
		}
		if (actionEvent.getSource() == btnCadastrar) {
			btnSalvar.toFront();
			btnSalvar.setVisible(true);
			btnAlterar.setVisible(false);
			paneCadastro.toFront();
		}
		if (actionEvent.getSource() == BTNEditar) {
			boolean v = TableView.getSelectionModel().isEmpty();
			System.out.println(v);
			if (v == true) {
				new ShowAlert().SelecionarPessoaEditar();
			} else {
				btnAlterar.toFront();
				btnAlterar.setVisible(true);
				btnSalvar.setVisible(false);
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
