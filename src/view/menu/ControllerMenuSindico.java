package view.menu;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.login.ControllerLogin;

public class ControllerMenuSindico extends Application implements Initializable {

	@FXML
	public BorderPane borderpane;

	@FXML
	private Button btnMenus;

	@FXML
	private Button btnSettings;
	
	@FXML
    private Label labelNome;

	@FXML
	private JFXButton btnFuncionario;

	@FXML
	private JFXButton btnVisitante;
	
	@FXML
	private JFXButton btnMorador;

	@FXML
	private JFXButton btnProprietario;

	@FXML
	private JFXButton btnRecado;

	@FXML
	private Button btnSignout;

	@FXML
	private Pane paneMenu;

	@FXML
	void Exit(ActionEvent event) {
		System.exit(1);
	}

	private Map<Object, String> mapPanels = new HashMap<Object, String>();

	@FXML
	public void trataMenu(ActionEvent actionEvent) {
		Object objeto = actionEvent.getSource();
		Pane pane = carregaFXML(mapPanels.get(objeto));
		try {
			borderpane.setCenter(pane);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private Pane carregaFXML(String fxml) {
		try {
			return FXMLLoader.load(getClass().getResource(fxml));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		labelNome.setText(x.nome);
		mapPanels.put(btnVisitante, "/view/visitante/VisitanteTable.fxml");
		mapPanels.put(btnProprietario, "/view/proprietario/ProprietarioTable.fxml");
		mapPanels.put(btnSettings, "/view/visitante/VisitTela.fxml");
		mapPanels.put(btnFuncionario, "/view/funcionario/FuncionarioTable.fxml");
		mapPanels.put(btnRecado, "/view/recado/RecadoTable.fxml");
		mapPanels.put(btnMorador, "/view/morador/MoradorTable.fxml");
		
	}

	ControllerLogin x = new ControllerLogin();
	public void handleClicks(ActionEvent actionEvent) throws IOException {
		if (actionEvent.getSource() == btnMenus) {
			borderpane.setCenter(paneMenu);
		}
		if (actionEvent.getSource() == btnSignout) {
			FXMLLoader fxmlLoader = new FXMLLoader(ControllerLogin.class.getResource("telafront.fxml"));
			abrirNovaTela(fxmlLoader);
			Stage stage = (Stage) btnSignout.getScene().getWindow();
			stage.close();
			x.usuario = "";
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

	public void execute() {
		launch();
	}

	@Override
	public void start(Stage stage) {

		try {
			Pane pane = FXMLLoader.load(getClass().getResource("TelaMenuSindico.fxml"));
			Scene sc = new Scene(pane);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(sc);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
