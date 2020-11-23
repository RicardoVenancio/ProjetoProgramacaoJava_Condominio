package view.morador;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import alerts.ShowAlert;
import dao.ApartamentoDAO;
import dao.moradorDao;
import dao.visitanteDao;
import entity.Apartamento;
import entity.Morador;
import entity.Proprietario;
import entity.Visitante;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.login.ControllerLogin;
import view.menu.ControllerMenuTable;
import view.morador.MoradorController;

public class MoradorController implements Initializable {

	@FXML
	private Pane paneCadastro;

	@FXML
	private JFXTextField txtTelefone;

	@FXML
	private JFXTextField txtEmail;

	@FXML
	private Label lblNumId;

	@FXML
	private JFXTextField txtNome;

	@FXML
	private JFXButton btnUpdate;

	@FXML
	private JFXButton btnCreate;

	@FXML
	private JFXButton btnVoltar;

	@FXML
	private Pane paneList;

	@FXML
	private Label numVisitantes;

	@FXML
	private Label lastVisit;

	@FXML
	private JFXButton btnCadastrar;

	@FXML
	private JFXButton btnEditar;

	@FXML
	private JFXButton btnApagar;

	@FXML
	private TableView<Morador> TableView;

	@FXML
	private TableColumn<Morador, String> tcNome;

	@FXML
	private TableColumn<Morador, String> tcTelefone;

	@FXML
	private TableColumn<Morador, String> tcEmail;

	@FXML
	private TableColumn<Morador, String> tcAp;

	@FXML
	private TextField txtBuscar;

	@FXML
	private Label Fechar;

	@FXML
	private ComboBox<Apartamento> apartamento;

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
			Morador morador = obtemDados();
			limpaCampo();
			int qtde = new moradorDao().create(morador);
			StartTable();
			paneList.toFront();
		} else {
			new ShowAlert().validaAlert();
		}
	}

	@FXML
	void Editar(ActionEvent event) {
		if (validaCampos()) {
			Morador morador = obtemDadosID();
			limpaCampo();
			int qtde = new moradorDao().edit(morador);
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
			alert.setHeaderText("Dados ‡ serem apagados.");
			alert.setContentText("Pressione OK para apagar este usuario.");
			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == ButtonType.OK) {
				System.out.println("Cadastro Apagado");
				Morador morador = obtemDadosIDDeletar();
				int qtde = new moradorDao().delete(morador.getIdMorador());
				limpaCampo();
				StartTable();
			}
		}
	}

	void EditarCadastro() {
		Morador v = TableView.getSelectionModel().getSelectedItem();
		lblNumId.setText(Integer.toString(v.getIdMorador()));
		txtNome.setText(v.getNomeMorador());
		txtTelefone.setText(v.getTelefoneMorador());
		txtEmail.setText(v.getEmailMorador());
	}

	private void limpaCampo() {
		txtNome.clear();
		txtTelefone.clear();
		txtEmail.clear();
		apartamento.setValue(null);
	}

	private Morador obtemDados() {
		String ap = String.valueOf(apartamento.getSelectionModel().getSelectedItem());
		return new Morador(txtNome.getText(), txtTelefone.getText(), txtEmail.getText(), ap);
	}

	private Morador obtemDadosID() {
		String ap = String.valueOf(apartamento.getSelectionModel().getSelectedItem());

		return new Morador(Integer.valueOf(lblNumId.getText()), txtNome.getText(), txtTelefone.getText(),
				txtEmail.getText(), ap);
	}

	private Morador obtemDadosIDDeletar() {
		Morador v = TableView.getSelectionModel().getSelectedItem();
		lblNumId.setText(Integer.toString(v.getIdMorador()));
		txtNome.setText(v.getNomeMorador());
		txtTelefone.setText(v.getTelefoneMorador());
		txtEmail.setText(v.getEmailMorador());
		apartamento.setValue(null);
		String ap = v.getApartamento();

		return new Morador(Integer.valueOf(lblNumId.getText()), txtNome.getText(), txtTelefone.getText(),
				txtEmail.getText(), ap);
	}

	public boolean validaCampos() {
		if (txtNome.getText().isEmpty() | txtTelefone.getText().isEmpty() | txtEmail.getText().isEmpty()) {
			return false;
		}
		else if (!txtNome.getText().matches("^[A-Za-z·‡‚„ÈËÍÌÔÛÙıˆ˙ÁÒ¡¿¬√…»Õœ”‘’÷⁄«— ]+$") 
				| txtTelefone.getText().matches("^[A-Za-z·‡‚„ÈËÍÌÔÛÙıˆ˙ÁÒ¡¿¬√…»Õœ”‘’÷⁄«— ]+$") 
				| !txtEmail.getText().matches("^[a-z0-9.|_|-]+@[a-z0-9]+\\.[a-z]+(\\.[a-z]{2})?$")) {
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
		List<Morador> list = new moradorDao().listAll();
		Morador x = new moradorDao().ultimoCadastro();
		tcNome.setCellValueFactory(new PropertyValueFactory<>("nomeMorador"));
		tcTelefone.setCellValueFactory(new PropertyValueFactory<>("telefoneMorador"));
		tcEmail.setCellValueFactory(new PropertyValueFactory<>("emailMorador"));
		tcAp.setCellValueFactory(new PropertyValueFactory<>("apartamento"));
		TableView.setItems(atualizaTabela());
		numVisitantes.setText(Integer.toString(list.size()));
		lastVisit.setText(x.getNomeMorador());

	}

	// Converter para Collections
	public ObservableList<Morador> atualizaTabela() {
		moradorDao dao = new moradorDao();
		return FXCollections.observableArrayList(dao.listAllName(txtBuscar.getText()));
	}

	public void StartTable2() {
		List<Morador> list = new moradorDao().listAllName(txtBuscar.getText());
		tcNome.setCellValueFactory(new PropertyValueFactory<>("nomeMorador"));
		tcTelefone.setCellValueFactory(new PropertyValueFactory<>("telefoneMorador"));
		tcEmail.setCellValueFactory(new PropertyValueFactory<>("emailMorador"));
		tcAp.setCellValueFactory(new PropertyValueFactory<>("apartamento"));
		TableView.setItems(atualizaTabela2());
		numVisitantes.setText(Integer.toString(list.size()));

	}

	// Converter para Collections
	public ObservableList<Morador> atualizaTabela2() {
		moradorDao dao = new moradorDao();
		return FXCollections.observableArrayList(dao.listAllName(txtBuscar.getText()));
	}

//-------------------------------------------------------------------------------------------------
	// Executar Tela
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {
			ObservableList y = new ApartamentoDAO().listaApartamentos();
			apartamento.setItems(y);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ControllerLogin x = new ControllerLogin();
		if (x.usuario == "funcionario") {
			btnApagar.setVisible(false);
			btnCadastrar.setVisible(false);
			btnEditar.setVisible(false);
		} else {
			btnApagar.setVisible(true);
			btnCadastrar.setVisible(true);
			btnEditar.setVisible(true);
		}
		StartTable();

	}

//-------------------------------------------------------------------------------------------------
	public void handleClicks(ActionEvent actionEvent) throws IOException {

		if (actionEvent.getSource() == btnCadastrar) {
			btnCreate.toFront();
			btnCreate.setVisible(true);
			btnUpdate.setVisible(false);
			paneCadastro.toFront();
		}
		if (actionEvent.getSource() == btnVoltar) {
			paneList.toFront();
			limpaCampo();
		}
		if (actionEvent.getSource() == btnEditar) {
			boolean v = TableView.getSelectionModel().isEmpty();
			System.out.println(v);
			if (v == true) {
				new ShowAlert().SelecionarPessoaEditar();
			} else {
				btnUpdate.toFront();
				btnUpdate.setVisible(true);
				btnCreate.setVisible(false);
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
