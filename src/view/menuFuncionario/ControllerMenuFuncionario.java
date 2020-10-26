package view.menuFuncionario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.funcionario.FuncionarioTable;
import view.login.ControllerLogin;

public class ControllerMenuFuncionario {


    @FXML
    private Button btnLogout;

	public void handleClicks(ActionEvent actionEvent) {
		if (actionEvent.getSource() == btnLogout) {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(ControllerLogin.class.getResource("telafront.fxml"));
				Parent root1 = fxmlLoader.load();
				Stage stage = new Stage();
				stage.initStyle(StageStyle.UNDECORATED);
				stage.setScene(new Scene(root1));
				stage.show();
				stage = (Stage) btnLogout.getScene().getWindow();
				stage.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
