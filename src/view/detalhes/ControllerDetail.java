package view.detalhes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.FuncionarioDao;
import dao.sindicoDao;
import entity.Funcionario;
import entity.Sindico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import view.login.ControllerLogin;

public class ControllerDetail implements Initializable {
	@FXML
	private HBox hboxSindico;

	@FXML
	private Label Id;

	@FXML
	private Label Nome;

	@FXML
	private Label Nascimento;

	@FXML
	private Label Sexo;

	@FXML
	private Label Email;

	@FXML
	private Label Telefone;

	@FXML
	private Label Admissao;

	@FXML
	private HBox hboxFuncionario;

	@FXML
	private Label IdFunc;

	@FXML
	private Label NomeFunc;

	@FXML
	private Label CargoFunc;

	@FXML
	private Label RgFunc;

	@FXML
	private Label CpfFunc;

	@FXML
	private Label TelefoneFunc;

	@FXML
	private Label SexoFunc;

	@FXML
	private Label AdmissaoFunc;

	@FXML
	private Label SalarioFunc;

	@FXML
	void Exit(ActionEvent event) {
		System.exit(1);
	}

	ControllerLogin login = new ControllerLogin();

	void pesquisarUsuario() throws IOException {
		if (login.usuario == "sindico") {
			hboxSindico.setVisible(true);
			hboxFuncionario.setVisible(false);
			Sindico x = new sindicoDao().BuscarDados(login.nome);
			Id.setText(x.getIdSINDICO() + "");
			Nome.setText(x.getNome());
			Nascimento.setText(x.getDataNascimento().toString());
			Sexo.setText(x.getSexoSindico());
			Email.setText(x.getEmailSindico());
			Telefone.setText(x.getTelefone());
			Admissao.setText(x.getDataAdmissao().toString());
		} else if (login.usuario == "funcionario") {
			hboxSindico.setVisible(false);
			hboxFuncionario.setVisible(true);
			Funcionario x = new FuncionarioDao().BuscarDados(login.nome);
			IdFunc.setText(x.getId() + "");
			NomeFunc.setText(x.getNome());
			CargoFunc.setText(x.getCargo());
			RgFunc.setText(x.getRg());
			CpfFunc.setText(x.getCpf());
			TelefoneFunc.setText(x.getTelefone());
			SexoFunc.setText(x.getSexo());
			AdmissaoFunc.setText(x.getDataadmissao().toString());
			SalarioFunc.setText("R$ " + x.getSalario() + ",00");
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			pesquisarUsuario();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
