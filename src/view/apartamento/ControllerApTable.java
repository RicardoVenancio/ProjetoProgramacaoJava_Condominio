package view.apartamento;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import alerts.ShowAlert;
import dao.ApartamentoDAO;
import dao.proprietarioDao;
import entity.Apartamento;
import entity.Proprietario;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.login.ControllerLogin;

public class ControllerApTable implements Initializable {

	@FXML
	private Pane paneList;

	@FXML
	private Label numAps;

	@FXML
	private JFXButton btnCadastrar;

	@FXML
	private JFXButton btnEditar;

	@FXML
	private JFXButton btnApagar;

	@FXML
	private TableView<Apartamento> TableView;

	@FXML
	private TableColumn<Apartamento, Integer> TbNumAp;

	@FXML
	private TableColumn<Apartamento, Integer> TbNumMoradores;

	@FXML
	private TableColumn<Apartamento, Integer> TbAndar;

	@FXML
	private TableColumn<Apartamento, Integer> TbBloco;

	@FXML
	private TableColumn<Apartamento, String> TbStatus;

	@FXML
	private TableColumn<Apartamento, Integer> TbVeiculo;

	@FXML
	private TableColumn<Apartamento, String> TbAnimais;

	@FXML
	private TableColumn<Apartamento, Integer> TbProprietario;

	@FXML
	private TableColumn<Apartamento, Date> TbDataEntrada;

	@FXML
	private TextField txtBuscar;

	@FXML
	private Label Fechar;

	@FXML
	private Label lastAp;

	@FXML
	private Label dateEntrada;

	@FXML
	private Pane paneCadastro;

	@FXML
	private JFXTextField numAp;

	@FXML
	private JFXTextField andar;

	@FXML
	private JFXToggleButton status;

	@FXML
	private JFXToggleButton veiculo;

	@FXML
	private JFXToggleButton animal;

	@FXML
	private JFXTextField numMoradores;

	@FXML
	private JFXTextField bloco;

	@FXML
	private JFXDatePicker dataEntrada;

	@FXML
	private JFXTextField numAnimal;

	@FXML
	private Label LabelLabel;

	@FXML
	private JFXButton BTNEditar;

	@FXML
	private JFXButton BTNSalvar;

	@FXML
	private JFXButton btnVoltar;

	@FXML
	private ComboBox<Proprietario> prop;

	@FXML
	void find(ActionEvent event) {
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
			Apartamento apartamento = obtemDados();
			limpaCampo();
			int qtde = new ApartamentoDAO().createAp(apartamento);
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
			Apartamento apartamento = obtemDadosID();
			limpaCampo();
			int qtde = new ApartamentoDAO().editAp(apartamento);
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
				Apartamento apartamento = obtemDadosIDDeletar();
				int qtde = new ApartamentoDAO().deleteAp(apartamento.getIdAp());
				limpaCampo();
				StartTable();
			}
		}
	}

	void EditarCadastro() {
		Apartamento v = TableView.getSelectionModel().getSelectedItem();
		LabelLabel.setText(Integer.toString(v.getIdAp()));
		numAp.setText(v.getNumAp() + "");
		numMoradores.setText(v.getQtdMorador() + "");
		andar.setText(v.getAndarAp() + "");
		bloco.setText(v.getBlocoAp() + "");
		dataEntrada.setValue(v.getDataEntrada().toLocalDate());
		numAnimal.setText(v.getQtdAnimal() + "");
		if (v.getAnimalEstimacao() == "Sim") {
			animal.selectedProperty().set(true);
		} else {
			animal.selectedProperty().set(false);
		}

		if (v.getVagaVeiculo() > 0) {
			veiculo.selectedProperty().set(true);
		} else {
			veiculo.selectedProperty().set(false);
		}

		if (v.getStatusAp() == "Ocupado") {
			status.selectedProperty().set(true);
		} else {
			status.selectedProperty().set(false);
		}
	}

	private void limpaCampo() {
		numAp.clear();
		numMoradores.clear();
		andar.clear();
		bloco.clear();
		dataEntrada.setValue(null);
		numAnimal.clear();
		status.selectedProperty().set(false);;
		animal.selectedProperty().set(false);
		veiculo.selectedProperty().set(false);
		prop.setValue(null);

	}

	private Apartamento obtemDados() {
		int numveiculo;
		String stats;
		String animais;
		if (veiculo.selectedProperty().get() == true) {
			numveiculo = 1;
		} else {
			numveiculo = 0;
		}

		if (status.selectedProperty().get() == true) {
			stats = "Ocupado";
		} else {
			stats = "Aberto";
		}

		if (animal.selectedProperty().get() == true) {
			animais = "Sim";
		} else {
			animais = "Não";
		}
		String nomeprop = String.valueOf(prop.getSelectionModel().getSelectedItem());
		return new Apartamento(Integer.parseInt(numAp.getText()), Integer.parseInt(numMoradores.getText()), animais,
				Integer.parseInt(numAnimal.getText()), Integer.parseInt(andar.getText()),
				Integer.parseInt(bloco.getText()), java.sql.Date.valueOf(dataEntrada.getValue()), stats, numveiculo,
				nomeprop);
	}

	private Apartamento obtemDadosID() {
		int numveiculo;
		String stats;
		String animais;
		if (veiculo.selectedProperty().get() == true) {
			numveiculo = 1;
		} else {
			numveiculo = 0;
		}

		if (status.selectedProperty().get() == true) {
			stats = "Ocupado";
		} else {
			stats = "Aberto";
		}

		if (animal.selectedProperty().get() == true) {
			animais = "Sim";
		} else {
			animais = "Não";
		}
		String nomeprop = String.valueOf(prop.getSelectionModel().getSelectedItem());
		return new Apartamento(Integer.valueOf(LabelLabel.getText()), Integer.parseInt(numAp.getText()),
				Integer.parseInt(numMoradores.getText()), animais, Integer.parseInt(numAnimal.getText()),
				Integer.parseInt(andar.getText()), Integer.parseInt(bloco.getText()),
				java.sql.Date.valueOf(dataEntrada.getValue()), stats, numveiculo,nomeprop);
	}

	private Apartamento obtemDadosIDDeletar() {
		int numveiculo;
		String stats;
		String animais;
		Apartamento v = TableView.getSelectionModel().getSelectedItem();
		LabelLabel.setText(Integer.toString(v.getIdAp()));
		numAp.setText(v.getNumAp() + "");
		numMoradores.setText(v.getQtdMorador() + "");
		andar.setText(v.getAndarAp() + "");
		bloco.setText(v.getBlocoAp() + "");
		dataEntrada.setValue(v.getDataEntrada().toLocalDate());
		numAnimal.setText(v.getQtdAnimal() + "");
		if (v.getAnimalEstimacao() == "Sim") {
			animal.selectedProperty().set(true);
			animais = "Sim";
		} else {
			animal.selectedProperty().set(false);
			animais = "Não";
		}

		if (v.getVagaVeiculo() > 0) {
			veiculo.selectedProperty().set(true);
			numveiculo = 1;
		} else {
			veiculo.selectedProperty().set(false);
			numveiculo = 0;
		}

		if (v.getStatusAp() == "Ocupado") {
			status.selectedProperty().set(true);
			stats = "Ocupado";
		} else {
			status.selectedProperty().set(false);
			stats = "Aberto";
		}
		String nomeprop = String.valueOf(prop.getSelectionModel().getSelectedItem());

		return new Apartamento(Integer.valueOf(LabelLabel.getText()), Integer.parseInt(numAp.getText()),
				Integer.parseInt(numMoradores.getText()), animais, Integer.parseInt(numAnimal.getText()),
				Integer.parseInt(andar.getText()), Integer.parseInt(bloco.getText()),
				java.sql.Date.valueOf(dataEntrada.getValue()), stats, numveiculo,nomeprop);

	}

	public boolean validaCampos() {
		if (numAp.getText().isEmpty() | numMoradores.getText().isEmpty() | numAnimal.getText().isEmpty()
				| andar.getText().isEmpty() | bloco.getText().isEmpty()) {
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
		List<Apartamento> list = new ApartamentoDAO().listAllAp();
		Apartamento apartamento = new ApartamentoDAO().ultimoCadastro();

		TbNumAp.setCellValueFactory(new PropertyValueFactory<>("numAp"));
		TbNumMoradores.setCellValueFactory(new PropertyValueFactory<>("qtdMorador"));
		TbAndar.setCellValueFactory(new PropertyValueFactory<>("andarAp"));
		TbBloco.setCellValueFactory(new PropertyValueFactory<>("blocoAp"));
		TbStatus.setCellValueFactory(new PropertyValueFactory<>("statusAp"));
		TbVeiculo.setCellValueFactory(new PropertyValueFactory<>("vagaVeiculo"));
		TbAnimais.setCellValueFactory(new PropertyValueFactory<>("animalEstimacao"));
		TbProprietario.setCellValueFactory(new PropertyValueFactory<>("proprietarioAp"));
		TbDataEntrada.setCellValueFactory(new PropertyValueFactory<>("dataEntrada"));

		TableView.setItems(atualizaTabela());
		numAps.setText(Integer.toString(list.size()));
		lastAp.setText(apartamento.getNumAp() + "");
		dateEntrada.setText(apartamento.getDataEntrada().toString());

	}

	// Converter para Collections
	public ObservableList<Apartamento> atualizaTabela() {
		ApartamentoDAO dao = new ApartamentoDAO();
		return FXCollections.observableArrayList(dao.listAllAp());
	}

	public void StartTable2() {
		List<Apartamento> list = new ApartamentoDAO().listAllName(txtBuscar.getText());
		TbNumAp.setCellValueFactory(new PropertyValueFactory<>("numAp"));
		TbNumMoradores.setCellValueFactory(new PropertyValueFactory<>("qtdMorador"));
		TbAndar.setCellValueFactory(new PropertyValueFactory<>("andarAp"));
		TbBloco.setCellValueFactory(new PropertyValueFactory<>("blocoAp"));
		TbStatus.setCellValueFactory(new PropertyValueFactory<>("statusAp"));
		TbVeiculo.setCellValueFactory(new PropertyValueFactory<>("vagaVeiculo"));
		TbAnimais.setCellValueFactory(new PropertyValueFactory<>("animalEstimacao"));
		TbProprietario.setCellValueFactory(new PropertyValueFactory<>("proprietarioAp"));
		TbDataEntrada.setCellValueFactory(new PropertyValueFactory<>("dataEntrada"));
		TableView.setItems(atualizaTabela2());
		numAps.setText(Integer.toString(list.size()));

	}

	// Converter para Collections
	public ObservableList<Apartamento> atualizaTabela2() {
		ApartamentoDAO dao = new ApartamentoDAO();
		return FXCollections.observableArrayList(dao.listAllName(txtBuscar.getText()));
	}

//-------------------------------------------------------------------------------------------------
	// Executar Tela
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			ObservableList y = new proprietarioDao().listaProprietarios();
			prop.setItems(y);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ControllerLogin x = new ControllerLogin();
		if (x.usuario == "funcionario") {
			btnApagar.setVisible(false);
		} else {
			btnApagar.setVisible(true);
		}
		StartTable();

	}

//-------------------------------------------------------------------------------------------------
	public void handleClicks(ActionEvent actionEvent) throws IOException {
		if (actionEvent.getSource() == btnCadastrar) {
			BTNSalvar.toFront();
			BTNSalvar.setVisible(true);
			BTNEditar.setVisible(false);
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
