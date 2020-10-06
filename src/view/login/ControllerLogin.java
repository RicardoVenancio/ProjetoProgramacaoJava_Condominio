package view.login;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

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
import view.sindico.ControllerSindico;

public class ControllerLogin extends Application {
	@FXML
	private Button BTNCadastrar;

	@FXML
	private TextField TXTCpf;

	@FXML
	private TextField TXTSenha;

	@FXML
	private Button BTNEntrar;

	@FXML
	private Button BTNSair;

	// loginDao LoginDao = new loginDao();
	@FXML
	void BTNEntra(ActionEvent event) {

			loginDao logindao = new loginDao();

			if(!TXTCpf.getText().isEmpty() && !TXTSenha.getText().isEmpty()) {
			String resultado = logindao.authenticateUser(TXTCpf.getText(),TXTSenha.getText());
			
			System.out.println("" + resultado);
//			Alert alert = new Alert(AlertType.INFORMATION);
//			alert.setTitle("ATENÇÃO!!!");
//			alert.setHeaderText("LOGIN REALIZADO COM SUCESSO");
//			alert.setContentText("SEJA BEM VINDO(A)");
//			alert.showAndWait();
		}
		else {
			System.out.println("error");
//			Alert alert = new Alert(AlertType.INFORMATION);
//			alert.setTitle("ATENÇÃO!!!");
//			alert.setHeaderText("INCOMPATIBILIDADE NAS INFORMAÇÕES");
//			alert.setContentText("USUÁRIO OU SENHA INVÁLIDO");
//			alert.showAndWait();
		}
	}

	@FXML
	void BTNCadastro(ActionEvent event) {
		List<String> choices = new ArrayList<>();
		choices.add("Síndico");
		choices.add("Morador");
		ChoiceDialog<String> dialog = new ChoiceDialog<>(" ", choices);
		dialog.setTitle("CADASTRO");
		dialog.setHeaderText("Você está prestes a escolher uma opção de cadastro");
		dialog.setContentText("Escolha uma opção:");
		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(ControllerSindico.class.getResource("frontSindico.fxml"));
				Parent root1 = fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root1));
				stage.show();
				fecharTelaLogin();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	@FXML
	void BTNSairsistema(ActionEvent event) {
   		 System.exit(0);
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
			// FXMLLoader fxmlLoader = new
			// FXMLLoader(ControllerLogin.class.getResource("frontLogin.fxml"));
			AnchorPane pane = (AnchorPane) FXMLLoader.load(ControllerLogin.class.getResource("frontLogin.fxml"));
			// getClass().getResource("frontSindico.fxml"));
			Scene sc = new Scene(pane);
			stage.setScene(sc);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
