package view.login;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import dao.loginDao;
import entity.Funcionario;
import entity.Mouse;
import entity.Sindico;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.ValidaCPF;
import view.funcionario.FuncionarioTable;
import view.menu.ControllerMenuTable;
import view.sindico.ControllerSindico;

public class ControllerLogin extends Application implements Initializable {
	@FXML
	private AnchorPane anchorPane;

	@FXML
	private Button BTNSair;

	@FXML
	private JFXTextField TXTCpf;

	@FXML
	private JFXPasswordField TXTSenha;

	@FXML
	private JFXButton BTNEntrar;

	@FXML
	private JFXButton BTNCadastrar;

	@FXML
	void Exit(ActionEvent event) {
//    		fecharTelaLogin();
		System.exit(5);
	}

	@FXML
	void BTNEntra(ActionEvent event) throws IOException {
		validacaoLogin();
	}

	public static String usuario;
	public static String cpf;

	@FXML
	void BTNCadastro(ActionEvent event) throws IOException {
		List<String> choices = new ArrayList<>();
		choices.add("S�ndico");
		choices.add("Funcion�rio");
		ChoiceDialog<String> dialog = new ChoiceDialog<>("Selecione um cadastro", choices);
		dialog.setTitle("CADASTRO");
		dialog.setHeaderText("Voc� est� prestes a escolher uma op��o de cadastro");
		dialog.setContentText("Escolha uma op��o:");

		Optional<String> result = dialog.showAndWait();
		String lista = "";
		if (!result.get().equals("")) {
			lista = result.get();
		}
		switch (lista) {
		case "S�ndico":
			if (result.isPresent()) {
				FXMLLoader fxmlLoader = new FXMLLoader(ControllerSindico.class.getResource("SindicoFront.fxml"));
				Parent root1 = fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root1));
				stage.initStyle(StageStyle.UNDECORATED);
				stage.show();
				fecharTelaLogin();
				break;
			}

		case "Funcion�rio":
			FXMLLoader fxmlLoader = new FXMLLoader(FuncionarioTable.class.getResource("FuncionarioFront.fxml"));
			Parent root1 = fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root1));
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
			fecharTelaLogin();
			break;
		}
	}

	@FXML
	void BTNSairsistema(ActionEvent event) {
		System.exit(0);
	}

	public String alerta() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("ATEN��O!!!");
		alert.setHeaderText("INCOMPATIBILIDADE NAS INFORMA��ES");
		alert.setContentText("USU�RIO OU SENHA INV�LIDOS!");
		alert.showAndWait();
		return "INFORMATION";
	}

	public String validacaoLogin() throws IOException {
		loginDao logindao = new loginDao();
		usuario = "";
		String senha = ValidaCPF.imprimeTelefone(TXTSenha.getText());
		
		if (!TXTCpf.getText().isEmpty() && !TXTSenha.getText().isEmpty()) {

			String resultadoSindico = logindao.authenticateUser(TXTCpf.getText(), senha);
			String resultadoFuncionario = logindao.authenticateUserFuncionario(TXTCpf.getText(), senha);

			if (resultadoFuncionario == null && resultadoSindico == null) {
				return "" + alerta();
			}
			if (resultadoSindico == null || resultadoSindico == "") {
				usuario = "funcionario";
				cpf = TXTCpf.getText();
				FXMLLoader fxmlLoader = new FXMLLoader(
						ControllerMenuTable.class.getResource("TelaMenuFuncionario.fxml"));
				Parent root1 = fxmlLoader.load();
				Stage stage = new Stage();
				stage.initStyle(StageStyle.UNDECORATED);
				stage.setScene(new Scene(root1));
				stage.show();
				fecharTelaLogin();
				return "Funcionario";
			}
			if (resultadoFuncionario == null || resultadoFuncionario == "") {
				usuario = "sindico";
				cpf = TXTCpf.getText();
				FXMLLoader fxmlLoader = new FXMLLoader(ControllerMenuTable.class.getResource("TelaMenuSindico.fxml"));
				Parent root1 = fxmlLoader.load();
				Stage stage = new Stage();
				stage.initStyle(StageStyle.UNDECORATED);
				stage.setScene(new Scene(root1));
				stage.show();
				fecharTelaLogin();
				return "Sindico";
			}
		}
		return alerta();
	}

	public void fecharTelaLogin() {
		Stage stage = (Stage) BTNCadastrar.getScene().getWindow();
		stage.close();
	}

	public void execute() {
		launch();
	}

	@Override
	public void start(Stage stage) throws IOException {
		AnchorPane pane = (AnchorPane) FXMLLoader.load(ControllerLogin.class.getResource("telafront.fxml"));
		Scene sc = new Scene(pane);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setScene(sc);
		stage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// -----Inicio metodo de mover a tela-----
		Mouse mouse = new Mouse();
		anchorPane.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
//				System.out.println("Mouse : " + t.getX() + " | " + t.getY());
				mouse.setX(t.getX());
				mouse.setY(t.getY());
			}
		});
		anchorPane.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				if (t.getY() <= 40) {
					anchorPane.getScene().getWindow().setX(t.getScreenX() - mouse.getX() - 1);
					anchorPane.getScene().getWindow().setY(t.getScreenY() - mouse.getY() - 1);
				}
			}
		});// -----Fim metodo mover a tela------
	}
}
