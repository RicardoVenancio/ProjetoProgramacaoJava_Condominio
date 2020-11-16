package view.funcionario;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import alerts.ShowAlert;
import dao.FuncionarioDao;
import entity.Funcionario;
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
import view.login.ControllerLogin;
import view.menu.ControllerMenuTable;
import view.proprietario.ControllerPropTable;
import view.recado.ControllerRecadoTable;
import view.visitante.ControllerTable;

public class ControllerFuncionario implements Initializable {

	@FXML
	private Pane paneCadastro;

	@FXML
	private JFXTextField PrRg;

	@FXML
	private JFXTextField PrCargo;

	@FXML
	private JFXTextField PrTelefone;

	@FXML
	private JFXTextField PrCpf;

	@FXML
	private JFXTextField PrSexo;

	@FXML
	private JFXTextField PrSalario;

	@FXML
	private Label LabelLabel;

	@FXML
	private JFXTextField PrNome;

	@FXML
	private JFXButton BTNEditar;

	@FXML
	private JFXButton BTNSalvar;
	@FXML
	private JFXButton BTNVoltar;

	@FXML
	private JFXDatePicker datePickerEmissao;

	@FXML
	private Pane paneList;

	@FXML
	private Label numFuncionarios;

	@FXML
	private Label lastVisit;

	@FXML
	private Button btnCadastrar;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnApagar;

	@FXML
	private TableView<Funcionario> TableView;

	@FXML
	private TableColumn<Funcionario, String> colNome;

	@FXML
	private TableColumn<Funcionario, String> colCargo;

	@FXML
	private TableColumn<Funcionario, String> colRg;

	@FXML
	private TableColumn<Funcionario, String> colCpf;

	@FXML
	private TableColumn<Funcionario, String> colTelefone;

	@FXML
	private TableColumn<Funcionario, String> colSalario;

	@FXML
	private TableColumn<Funcionario, String> colSexo;

	@FXML
	private TableColumn<Funcionario, String> colAdmissao;

	@FXML
	private TextField txtBuscar;

	@FXML
	private Label Fechar;

	@FXML
	private Button btnFuncionario;

	@FXML
	private Button btnRecado;

	@FXML
	private Button btnVisitante;

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

	}

	@FXML
	void Salvar(ActionEvent event) {
		if (validaCampos()) {
			Funcionario funcionario = obtemDados();
			limpaCampo();
			int qtde = new FuncionarioDao().inserir(funcionario);
			StartTable();
			paneList.toFront();
			System.out.println(qtde);
		} else {
			new ShowAlert().validaAlert();
		}
	}

	@FXML
	void Salvarfuncionario(ActionEvent event) throws IOException {
		if (validaCampos()) {
			Funcionario funcionario = obtemDados();
			limpaCampo();
			int qtde = new FuncionarioDao().inserir(funcionario);
			navegacaoTelaLogin();
			System.out.println(qtde);
		} else {
			new ShowAlert().validaAlert();
		}
	}

	@FXML
	void Editar(ActionEvent event) {
		if (validaCampos()) {
			Funcionario funcionario = obtemDadosID();
			limpaCampo();
			int qtde = new FuncionarioDao().alterar(funcionario);
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
				Funcionario funcionario = obtemDadosIDDeletar();
				int qtde = new FuncionarioDao().deletar(funcionario.getId());
				limpaCampo();
				StartTable();
			}
		}
	}

	void EditarCadastro() {
		Funcionario v = TableView.getSelectionModel().getSelectedItem();
		LabelLabel.setText(Integer.toString(v.getId()));
		PrNome.setText(v.getNome());
		PrCargo.setText(v.getCargo());
		PrRg.setText(v.getRg());
		PrCpf.setText(v.getCpf());
		PrTelefone.setText(v.getTelefone());
		PrSexo.setText(v.getSexo());
		datePickerEmissao.setValue(v.getDataadmissao().toLocalDate());
		PrSalario.setText(v.getSalario());
	}

	private void limpaCampo() {
		PrNome.clear();
		PrRg.clear();
		PrCpf.clear();
		PrTelefone.clear();
		PrCargo.clear();
		PrSexo.clear();
		PrSalario.clear();
		datePickerEmissao.setValue(null);
	}

	private Funcionario obtemDados() {
		return new Funcionario(PrNome.getText(), PrCargo.getText(), PrRg.getText(), PrCpf.getText(),
				PrTelefone.getText(), PrSexo.getText(), java.sql.Date.valueOf(datePickerEmissao.getValue()),
				PrSalario.getText());
	}

	private Funcionario obtemDadosID() {
		return new Funcionario(Integer.valueOf(LabelLabel.getText()), PrNome.getText(), PrCargo.getText(),
				PrRg.getText(), PrCpf.getText(), PrTelefone.getText(), PrSexo.getText(),
				java.sql.Date.valueOf(datePickerEmissao.getValue()), PrSalario.getText());

	}

	private Funcionario obtemDadosIDDeletar() {
		Funcionario v = TableView.getSelectionModel().getSelectedItem();
		LabelLabel.setText(Integer.toString(v.getId()));
		PrNome.setText(v.getNome());
		PrCargo.setText(v.getCargo());
		PrRg.setText(v.getRg());
		PrCpf.setText(v.getCpf());
		PrTelefone.setText(v.getTelefone());
		PrSexo.setText(v.getSexo());
		datePickerEmissao.setValue(v.getDataadmissao().toLocalDate());
		PrSalario.setText(v.getSalario());

		return new Funcionario(Integer.valueOf(LabelLabel.getText()), PrNome.getText(), PrCargo.getText(),
				PrRg.getText(), PrCpf.getText(), PrTelefone.getText(), PrSexo.getText(),
				java.sql.Date.valueOf(datePickerEmissao.getValue()), PrSalario.getText());

	}

	public boolean validaCampos() {
		if (PrNome.getText().isEmpty() | PrRg.getText().isEmpty() | PrCpf.getText().isEmpty()
				| PrTelefone.getText().isEmpty() | PrSalario.getText().isEmpty() | PrCargo.getText().isEmpty()
				| PrSexo.getText().isEmpty()) {
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
		List<Funcionario> list = new FuncionarioDao().listAll();
		colNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		colCargo.setCellValueFactory(new PropertyValueFactory<>("Cargo"));
		colRg.setCellValueFactory(new PropertyValueFactory<>("Rg"));
		colCpf.setCellValueFactory(new PropertyValueFactory<>("Cpf"));
		colTelefone.setCellValueFactory(new PropertyValueFactory<>("Telefone"));
		colSexo.setCellValueFactory(new PropertyValueFactory<>("Sexo"));
		colAdmissao.setCellValueFactory(new PropertyValueFactory<>("Dataadmissao"));
		colSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
		TableView.setItems(atualizaTabela());
		numFuncionarios.setText(Integer.toString(list.size()));
	}

	// Converter para Collections
	public ObservableList<Funcionario> atualizaTabela() {
		FuncionarioDao dao = new FuncionarioDao();
		return FXCollections.observableArrayList(dao.listAll());
	}

	public void navegacaoTelaLogin() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(ControllerLogin.class.getResource("telafront.fxml"));
		Parent root1 = fxmlLoader.load();
		Stage stage = new Stage();
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setScene(new Scene(root1));
		stage.show();
		fecharTela();

	}

	public void fecharTela() {
		Stage stage = (Stage) BTNSalvar.getScene().getWindow();
		stage.close();
	}

//-------------------------------------------------------------------------------------------------
	ControllerLogin x = new ControllerLogin();

	// Executar Tela
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

//-------------------------------------------------------------------------------------------------

	public void handleClicks(ActionEvent actionEvent) throws IOException {
		if (actionEvent.getSource() == btnMenus) {
			FXMLLoader fxmlLoader = new FXMLLoader(ControllerMenuTable.class.getResource("MenuTable.fxml"));
			abrirNovaTela(fxmlLoader);
			Stage stage = (Stage) btnMenus.getScene().getWindow();
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
		if (actionEvent.getSource() == btnSignout) {
			FXMLLoader fxmlLoader = new FXMLLoader(ControllerLogin.class.getResource("telafront.fxml"));
			abrirNovaTela(fxmlLoader);
			Stage stage = (Stage) btnSignout.getScene().getWindow();
			stage.close();
		}
		if (actionEvent.getSource() == btnFuncionario) {
			paneList.toFront();
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
