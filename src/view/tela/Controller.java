package view.tela;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller extends Application implements Initializable {

	@FXML
	private VBox pnItems = null;
	@FXML
	private Button btnOverview;

	@FXML
	private Button btnVisitante;

	@FXML
	private Button btnFuncionario;

	@FXML
	private Button btnMenus;

	@FXML
	private Button btnPackages;

	@FXML
	private Button btnSettings;

	@FXML
	private Button btnSignout;

	@FXML
	private Pane pnlCustomer;

	@FXML
	private Pane pnlOrders;

	@FXML
	private Pane pnlOverview;

	@FXML
	private Pane pnlMenus;

	public void Exit() {
		System.exit(1);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		changeScreen("main");
	}

	public void changeScreen(String tela) {
		Node[] nodes = new Node[1];

		for (int i = 0; i < nodes.length; i++) {

			try {
				if (tela == "main") {
					nodes[i] = FXMLLoader.load(getClass().getResource("Item.fxml"));
					pnlOverview.getChildren().add(nodes[i]);
				}
				if (tela == "visitante") {
					pnlOverview.getChildren().clear();
					nodes[i] = FXMLLoader.load(getClass().getResource("/view/visitante/VisitanteTable.fxml"));
					pnlOverview.getChildren().add(nodes[i]);
				}
//	               
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void execute() {
		launch();
	}

	public void handleClicks(ActionEvent actionEvent) {
		if (actionEvent.getSource() == btnFuncionario) {
			pnlCustomer.setStyle("-fx-background-color : #1620A1");
			pnlCustomer.toFront();
		}
		if (actionEvent.getSource() == btnMenus) {
			pnlMenus.setStyle("-fx-background-color : #53639F");
			pnlMenus.toFront();
		}
		if (actionEvent.getSource() == btnOverview) {
			pnlOverview.setStyle("-fx-background-color : #02030A");
			pnlOverview.toFront();
		}
		if (actionEvent.getSource() == btnVisitante) {
			changeScreen("visitante");
			pnlOverview.toFront();
//            pnlOrders.setStyle("-fx-background-color : #464F67");
//            pnlOrders.toFront();
		}
	}

	private double x, y;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
		primaryStage.setScene(new Scene(root));
		// set stage borderless
		primaryStage.initStyle(StageStyle.UNDECORATED);

		// drag it here
		root.setOnMousePressed(event -> {
			x = event.getSceneX();
			y = event.getSceneY();
		});
		root.setOnMouseDragged(event -> {

			primaryStage.setX(event.getScreenX() - x);
			primaryStage.setY(event.getScreenY() - y);

		});
		primaryStage.show();
	}
}
