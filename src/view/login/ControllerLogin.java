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
import dao.sindicoDao;
import entity.Sindico;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.funcionario.ControllerFuncionario;
import view.funcionario.FuncionarioTable;
import view.menu.ControllerMenu;
import view.menuFuncionario.ControllerMenuFuncionario;
import view.sindico.ControllerSindico;
import view.visitante.ControllerTable;

public class ControllerLogin extends Application {
//	@FXML
//	private Button BTNCadastrar;
//
//	@FXML
//	private TextField TXTCpf;
//
//	@FXML
//	private TextField TXTSenha;
//
//	@FXML
//	private Button BTNEntrar;

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
	void BTNEntra(ActionEvent event) {

		validacaoLogin();

	}

	@FXML
	void BTNCadastro(ActionEvent event) {
		List<String> choices = new ArrayList<>();
		choices.add("S�ndico");
		choices.add("Funcion�rio");
		ChoiceDialog<String> dialog = new ChoiceDialog<>(" ", choices);
		dialog.setTitle("CADASTRO");
		dialog.setHeaderText("Voc� est� prestes a escolher uma op��o de cadastro");
		dialog.setContentText("Escolha uma op��o:");

		Optional<String> result = dialog.showAndWait();
		String lista = "";
		if (result.get().isEmpty()) {
		} else {
			lista = result.get();
		}

		switch (lista) {
		case "S�ndico":
			if (result.isPresent()) {
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(ControllerSindico.class.getResource("SindicoFront.fxml"));
					Parent root1 = fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root1));
					stage.initStyle(StageStyle.UNDECORATED);
					stage.show();
					fecharTelaLogin();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;

		case "Funcion�rio":
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(ControllerFuncionario.class.getResource("FuncionarioView.fxml"));
				Parent root1 = fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root1));
				stage.show();
				fecharTelaLogin();

			} catch (Exception e) {
				e.printStackTrace();
			}
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

	public String validacaoLogin() {
		loginDao logindao = new loginDao();

		if (!TXTCpf.getText().isEmpty() && !TXTSenha.getText().isEmpty()) {

			String resultadoSindico = logindao.authenticateUser(TXTCpf.getText(), TXTSenha.getText());
			String resultadoFuncionario = logindao.authenticateUserFuncionario(TXTCpf.getText(), TXTSenha.getText());

			if (resultadoFuncionario == null && resultadoSindico == null) {
				return "" + alerta();

			}

			if (resultadoSindico == null || resultadoSindico == "") {
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(
							ControllerMenuFuncionario.class.getResource("frontMenuFuncionario.fxml"));
					Parent root1 = fxmlLoader.load();
					Stage stage = new Stage();
					stage.initStyle(StageStyle.UNDECORATED);
					stage.setScene(new Scene(root1));
					stage.show();
					fecharTelaLogin();

				} catch (Exception e) {
					e.printStackTrace();
				}
				return "Funcionario";
			}

			if (resultadoFuncionario == null || resultadoFuncionario == "") {
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(ControllerTable.class.getResource("VisitanteTable.fxml"));
					Parent root1 = fxmlLoader.load();
					Stage stage = new Stage();
					stage.initStyle(StageStyle.UNDECORATED);
					stage.setScene(new Scene(root1));
					stage.show();
					fecharTelaLogin();

				} catch (Exception e) {
					e.printStackTrace();
				}
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
	public void start(Stage stage) {

		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(ControllerLogin.class.getResource("telafront.fxml"));
			Scene sc = new Scene(pane);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(sc);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
