package view.menu;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import dao.FuncionarioDao;
import entity.Funcionario;
import entity.Mouse;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.login.ControllerLogin;

public class ControllerMenuFuncionario extends Application implements Initializable {

	@FXML
	public BorderPane borderpane;

	@FXML
	private Button btnMenus;

	@FXML
	private Button btnSettings;

	@FXML
	private JFXButton btnNome;

	@FXML
	private JFXButton btnVisitante;

	@FXML
	private JFXButton btnMorador;

	@FXML
	private JFXButton btnRecado;

	@FXML
	private Pane paneMenu;

	@FXML
	private Button btnSignout;

	@FXML
	void Exit(ActionEvent event) {
		System.exit(1);
	}

	// ----------Troca de tela-------------
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
		Funcionario func = new FuncionarioDao().BuscarCPF(x.cpf);
		btnNome.setText(func.getNome());
		mapPanels.put(btnVisitante, "/view/visitante/VisitanteTable.fxml");
		mapPanels.put(btnRecado, "/view/recado/RecadoTable.fxml");
		mapPanels.put(btnMorador, "/view/morador/MoradorTable.fxml");
		mapPanels.put(btnNome, "/view/detalhes/Details.fxml");

		// -----Inicio metodo de mover a tela-----
		Mouse mouse = new Mouse();
		borderpane.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				mouse.setX(t.getX());
				mouse.setY(t.getY());
			}
		});
		borderpane.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				if (t.getY() <= 40) {
					borderpane.getScene().getWindow().setX(t.getScreenX() - mouse.getX() - 1);
					borderpane.getScene().getWindow().setY(t.getScreenY() - mouse.getY() - 1);
				}
			}
		});// -----Fim metodo mover a tela------
	}

	ControllerLogin x = new ControllerLogin();

	public void handleClicks(ActionEvent actionEvent) throws IOException, InterruptedException {
		if (actionEvent.getSource() == btnMenus) {
			borderpane.setCenter(paneMenu);
		}
		if (actionEvent.getSource() == btnSignout) {
			Thread.sleep(1500);
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

	// --------------------------Fim de troca de
	// tela-------------------------------------
	public void execute() {
		launch();
	}

	@Override
	public void start(Stage stage) {

		try {
			Pane pane = FXMLLoader.load(getClass().getResource("TelaMenuFuncionario.fxml"));
			Scene sc = new Scene(pane);
			stage.setScene(sc);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
