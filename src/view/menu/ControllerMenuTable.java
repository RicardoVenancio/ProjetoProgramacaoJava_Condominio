package view.menu;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import alerts.ShowAlert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.funcionario.FuncionarioTable;
import view.login.ControllerLogin;
import view.proprietario.ControllerPropTable;
import view.recado.ControllerRecadoTable;
import view.visitante.ControllerTable;

public class ControllerMenuTable implements Initializable {

	@FXML
	private Pane paneCadastro;

	@FXML
	private JFXButton btnFuncionario;

	@FXML
	private JFXButton btnVisitante;

	@FXML
	private JFXButton btnProprietario;

	@FXML
	private ImageView imgSindico;

	@FXML
	private JFXButton btnRecado;

	@FXML
	private JFXButton btnX;

	@FXML
	private JFXButton btnY;

	@FXML
	private Button btnMenus;

	@FXML
	private Button btnSettings;

	@FXML
	private Button btnSignout;

	@FXML
	void Exit(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Alerta!");
		alert.setHeaderText("Sair");
		alert.setContentText("Deseja sair do programa?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			System.exit(1);
		}
	}

	@FXML
	void handleClicks(ActionEvent actionEvent) throws IOException, InterruptedException {
		if (actionEvent.getSource() == btnRecado) {
			FXMLLoader fxmlLoader = new FXMLLoader(ControllerRecadoTable.class.getResource("RecadoTable.fxml"));
			abrirNovaTela(fxmlLoader);
			Stage stage = (Stage) btnRecado.getScene().getWindow();
			stage.close();
		}
		if (actionEvent.getSource() == btnVisitante) {
			FXMLLoader fxmlLoader = new FXMLLoader(ControllerTable.class.getResource("VisitanteTable.fxml"));
			abrirNovaTela(fxmlLoader);
			Stage stage = (Stage) btnVisitante.getScene().getWindow();
			stage.close();
		}
		if (actionEvent.getSource() == btnFuncionario) {
			FXMLLoader fxmlLoader = new FXMLLoader(FuncionarioTable.class.getResource("FuncionarioTable.fxml"));
			abrirNovaTela(fxmlLoader);
			Stage stage = (Stage) btnFuncionario.getScene().getWindow();
			stage.close();
		}
		if (actionEvent.getSource() == btnProprietario) {
			FXMLLoader fxmlLoader = new FXMLLoader(ControllerPropTable.class.getResource("ProprietarioTable.fxml"));
			abrirNovaTela(fxmlLoader);
			Stage stage = (Stage) btnProprietario.getScene().getWindow();
			stage.close();
		}
		if (actionEvent.getSource() == btnSignout) {
			Thread.sleep(1500);
			FXMLLoader fxmlLoader = new FXMLLoader(ControllerLogin.class.getResource("telafront.fxml"));
			abrirNovaTela(fxmlLoader);
			Stage stage = (Stage) btnSignout.getScene().getWindow();
			stage.close();
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
	
	void abrirNovaTela(FXMLLoader y) throws IOException {
		FXMLLoader fxmlLoader = y;
		Parent root1 = fxmlLoader.load();
		Stage stage = new Stage();
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setScene(new Scene(root1));
		stage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
