package view.visitante;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import alerts.ShowAlert;
import dao.visitanteDao;
import entity.Visitante;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.funcionario.FuncionarioTable;

public class ControllerTable implements Initializable {

	@FXML
	private Pane paneCadastro;

	@FXML
    private JFXButton btnCadastrar;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnApagar;
    
	@FXML
    private JFXButton BTNEditar;

    @FXML
    private JFXButton BTNSalvar;

	@FXML
    private JFXTextField TxtNome;

    @FXML
    private JFXTextField TxtRG;

    @FXML
    private JFXTextField TxtCPF;

    @FXML
    private JFXTextField TxtTelefone;

    @FXML
    private JFXTextField TxtEmail;

    @FXML
    private JFXDatePicker datePickerVisita;

	@FXML
	private Pane paneList;

	@FXML
	private Label numVisitantes;

	@FXML
	private Label LabelLabel;

	@FXML
	private Label lastVisit;

	@FXML
	private TableView<Visitante> TableView;

	@FXML
	private TableColumn<Visitante, String> tcNome;

	@FXML
	private TableColumn<Visitante, String> tcCpf;

	@FXML
	private TableColumn<Visitante, String> tcRg;

	@FXML
	private TableColumn<Visitante, String> tcTelefone;

	@FXML
	private TableColumn<Visitante, String> tcEmail;

	@FXML
	private TableColumn<Visitante, String> tcData;

	@FXML
	private TextField txtBuscar;

	@FXML
	private Label Fechar;
	
	@FXML
	private Button btnFuncionario;

	@FXML
	private Button btnVisitante;

	@FXML
	private Button btnMorador;

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
			Visitante visitante = obtemDados();
			limpaCampo();
			int qtde = new visitanteDao().inserir(visitante);
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
			Visitante visitante = obtemDadosID();
			limpaCampo();
			int qtde = new visitanteDao().alterar(visitante);
			StartTable();
			paneList.toFront();
		} else {
			new ShowAlert().validaAlert();
		}
	}

	@FXML
	void Excluir(ActionEvent event) {
		boolean v = TableView.getSelectionModel().isEmpty();
		System.out.println(v);
		if(v == true) {
			new ShowAlert().SelecionarPessoa();
		}else {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Alerta!");
			alert.setHeaderText("Dados à serem apagados.");
			alert.setContentText("Pressione OK para apagar este usuario.");
			Optional<ButtonType> result = alert.showAndWait();
			
			if (result.get() == ButtonType.OK) {
				System.out.println("Cadastro Apagado");
				Visitante visitante = obtemDadosIDDeletar();
				int qtde = new visitanteDao().deletar(visitante.getId());
				limpaCampo();
				StartTable();
			}
		}
	}
	
	void EditarCadastro() {
		Visitante v = TableView.getSelectionModel().getSelectedItem();
		LabelLabel.setText(Integer.toString(v.getId()));
		TxtNome.setText(v.getNome());
		TxtRG.setText(v.getRG());
		TxtCPF.setText(v.getCPF());
		TxtTelefone.setText(v.getTelefone());
		TxtEmail.setText(v.getEmail());
		datePickerVisita.setValue(v.getDataVisita().toLocalDate());
	}

	private void limpaCampo() {
		TxtNome.clear();
		TxtRG.clear();
		TxtCPF.clear();
		TxtTelefone.clear();
		TxtEmail.clear();
		datePickerVisita.setValue(null);

	}

	private Visitante obtemDados() {
		return new Visitante(TxtNome.getText(), TxtRG.getText(), TxtCPF.getText(), TxtTelefone.getText(),
				TxtEmail.getText(), java.sql.Date.valueOf(datePickerVisita.getValue()));
	}

	private Visitante obtemDadosID() {
		return new Visitante(Integer.valueOf(LabelLabel.getText()), TxtNome.getText(), TxtRG.getText(),
				TxtCPF.getText(), TxtTelefone.getText(), TxtEmail.getText(),
				java.sql.Date.valueOf(datePickerVisita.getValue()));
	
	}
	
	private Visitante obtemDadosIDDeletar() {
		Visitante v = TableView.getSelectionModel().getSelectedItem();
		LabelLabel.setText(Integer.toString(v.getId()));
		TxtNome.setText(v.getNome());
		TxtRG.setText(v.getRG());
		TxtCPF.setText(v.getCPF());
		TxtTelefone.setText(v.getTelefone());
		TxtEmail.setText(v.getEmail());
		datePickerVisita.setValue(v.getDataVisita().toLocalDate());
		
		return new Visitante(Integer.valueOf(LabelLabel.getText()), TxtNome.getText(), TxtRG.getText(),
				TxtCPF.getText(), TxtTelefone.getText(), TxtEmail.getText(),
				java.sql.Date.valueOf(datePickerVisita.getValue()));
		
	}

	public boolean validaCampos() {
		if (TxtNome.getText().isEmpty() | TxtRG.getText().isEmpty() | TxtCPF.getText().isEmpty()
				| TxtTelefone.getText().isEmpty() | TxtEmail.getText().isEmpty()) {
			return false;
		}
		return true;
	}

	// Sair ou fechar o programa
	public void Exit() {
		System.exit(1);
	}

	// Listar cadastros na TableView
	public void StartTable() {
		List<Visitante> list = new visitanteDao().listAll();
		tcNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		tcRg.setCellValueFactory(new PropertyValueFactory<>("RG"));
		tcCpf.setCellValueFactory(new PropertyValueFactory<>("CPF"));
		tcTelefone.setCellValueFactory(new PropertyValueFactory<>("Telefone"));
		tcEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
		tcData.setCellValueFactory(new PropertyValueFactory<>("DataVisita"));
		TableView.setItems(atualizaTabela());
		numVisitantes.setText(Integer.toString(list.size()));
	}

	// Converter para Collections
	public ObservableList<Visitante> atualizaTabela() {
		visitanteDao dao = new visitanteDao();
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
//		Parent root = FXMLLoader.load(getClass().getResource("VisitanteTable.fxml"));
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
	public void handleClicks(ActionEvent actionEvent) {
		if (actionEvent.getSource() == btnFuncionario) {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(FuncionarioTable.class.getResource("FuncionarioTable.fxml"));
	            Parent root1 = fxmlLoader.load();
	            Stage stage = new Stage();
	            stage.initStyle(StageStyle.UNDECORATED);
	            stage.setScene(new Scene(root1));
	            stage.show();
	            stage = (Stage) btnFuncionario.getScene().getWindow();
	    		stage.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		if (actionEvent.getSource() == btnCadastrar) {
			BTNSalvar.toFront();			
			BTNSalvar.setVisible(true);
			BTNEditar.setVisible(false);
			paneCadastro.toFront();
		}
		if (actionEvent.getSource() == btnEditar) {
			BTNEditar.toFront();
			BTNEditar.setVisible(true);
			BTNSalvar.setVisible(false);
			paneCadastro.toFront();
			EditarCadastro();
			
		}
		if (actionEvent.getSource() == btnVisitante) {
			paneList.toFront();
		}
//		if (actionEvent.getSource() == btnOverview) {
//			pnlOverview.setStyle("-fx-background-color : #02030A");
//			pnlOverview.toFront();
//		}
//		if (actionEvent.getSource() == btnVisitante) {
//			changeScreen("visitante");
//			pnlOverview.toFront();
//            pnlOrders.setStyle("-fx-background-color : #464F67");
//            pnlOrders.toFront();
//		}
	}

}
