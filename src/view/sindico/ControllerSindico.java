package view.sindico;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import alerts.ShowAlert;

import java.sql.Date;

import dao.sindicoDao;
import entity.Sindico;
import javafx.application.Application;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.ValidaCPF;
import view.login.ControllerLogin;

public class ControllerSindico implements Initializable {
	// utilizar com o mÈtodo de inicializaÁ„o//public class ControllerSindico
	// extends Application implements Initializable{

	@FXML
	private JFXButton BTNSalvar;

	@FXML
	private JFXTextField TXNome;

	@FXML
	private JFXTextField TXCpf;

	@FXML
	private JFXTextField TXSexo;

	@FXML
	private JFXTextField TXEmail;

	@FXML
	private JFXTextField TXTelefone;

	@FXML
	private JFXDatePicker datePickerNascimento;

	@FXML
	private JFXDatePicker datePickerAdmissao;

	@FXML
	private Button BTNEditar;

	@FXML
	private Button BTNExcluir;

	@FXML
	private TextField TXNascimento;

	@FXML
	private TextField TXAdmissao;

	@FXML
	private TextField TXId;

	@FXML
	private Label Labelid;

	@FXML
	private Label LabelLabel;

	@FXML
	private Button BTNBuscarID;

	@FXML
	private Label invalidoCPF;

	@FXML
	void Exit(ActionEvent event) {
//	    		fecharTelaLogin();
		System.exit(1);
	}

	public boolean validaCPF() {
		boolean x = ValidaCPF.isCPF(TXCpf.getText());

		if (x == false) {
			invalidoCPF.setVisible(true);
			return false;
		} else {
			invalidoCPF.setVisible(false);
			return true;
		}
	}

	@FXML
	void buscarID() {
		String idString = TXId.getText();
		Sindico sindico = null;
		if (!idString.equals("")) {
			try {
				int id = Integer.valueOf(idString);
				sindico = new sindicoDao().findByID(id);
			} catch (Exception e) {

			}
			if (sindico != null) {
				Labelid.setVisible(true);
				LabelLabel.setVisible(true);
				LabelLabel.setText(sindico.getIdSINDICO() + "");
				TXNome.setText(sindico.getNome());
				TXCpf.setText(sindico.getCpf());
				datePickerNascimento.setValue(sindico.getDataNascimento().toLocalDate());
				TXSexo.setText(sindico.getSexoSindico());
				TXEmail.setText(sindico.getEmailSindico());
				TXTelefone.setText(sindico.getTelefone());
				datePickerAdmissao.setValue(sindico.getDataAdmissao().toLocalDate());
			}
		}
		TXId.clear();
	}

	@FXML
	void ExcluirSindico(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("ATEN«√O");
		alert.setHeaderText("EXCLUS√O DE DADOS");
		alert.setContentText("VOC  TEM CERTEZA QUE DESEJA EXCLUIR O USU¡RIO");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			Sindico sindico = obtemDadosID();
			int qtde = new sindicoDao().deletar(sindico.getIdSINDICO());
			limpaCampo();
		}
	}

	@FXML
	void SalvarSindico(ActionEvent event) throws IOException {
		if (validaCPF() == true) {
			if (validaCampos()) {
				Sindico sindico = obtemDados();
				limpaCampo();
				int qtde = new sindicoDao().inserir(sindico);
				System.out.println(qtde);
				navegacaoTelaLogin();
			} else {
				new ShowAlert().validaAlert();
			}
		}
	}

	@FXML
	void EditarSindico(ActionEvent event) {
		Sindico sindico = obtemDadosID();
		limpaCampo();
		int qtde = new sindicoDao().alterar(sindico);
	}

	private void limpaCampo() {
		TXNome.clear();
		TXCpf.clear();
		datePickerNascimento.setValue(null);
		TXSexo.clear();
		TXEmail.clear();
		TXTelefone.clear();
		datePickerAdmissao.setValue(null);

		LabelLabel.setVisible(false);
		LabelLabel.setText("");
	}

	private Sindico obtemDados() {
		return new Sindico(TXNome.getText(), TXCpf.getText(), java.sql.Date.valueOf(datePickerNascimento.getValue()),
				TXSexo.getText(), TXEmail.getText(), TXTelefone.getText(),
				java.sql.Date.valueOf(datePickerAdmissao.getValue()));
	}

	private Sindico obtemDadosID() {

		return new Sindico(TXNome.getText(), TXCpf.getText(), Integer.valueOf(LabelLabel.getText()),
				java.sql.Date.valueOf(datePickerNascimento.getValue()), TXSexo.getText(), TXEmail.getText(),
				TXTelefone.getText(), java.sql.Date.valueOf(datePickerAdmissao.getValue()));
	}

	public boolean validaCampos() {
		if (TXNome.getText().isEmpty() | TXSexo.getText().isEmpty() | TXEmail.getText().isEmpty()
				| TXTelefone.getText().isEmpty() | TXEmail.getText().isEmpty()) {
			return false;
		}else if(!TXNome.getText().matches("^[A-Za-z·‡‚„ÈËÍÌÔÛÙıˆ˙ÁÒ¡¿¬√…»Õœ”‘’÷⁄«— ]+$") 
                | !TXSexo.getText(). matches("^[A-Za-z·‡‚„ÈËÍÌÔÛÙıˆ˙ÁÒ¡¿¬√…»Õœ”‘’÷⁄«— ]+$")
                | TXTelefone.getText().matches("^[A-Za-z·‡‚„ÈËÍÌÔÛÙıˆ˙ÁÒ¡¿¬√…»Õœ”‘’÷⁄«— ]+$") 
                | !TXEmail.getText().matches("^[a-z0-9.|_|-]+@[a-z0-9]+\\.[a-z]+(\\.[a-z]{2})?$")) {
            return false;
        }
		return true;
	}

	public void navegacaoTelaLogin() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(ControllerLogin.class.getResource("telafront.fxml"));
		Parent root1 = fxmlLoader.load();
		Stage stage = new Stage();
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setScene(new Scene(root1));
		stage.show();
		fecharTelaSindico();

	}

	public void fecharTelaSindico() {
		Stage stage = (Stage) BTNSalvar.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}