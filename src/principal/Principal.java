package principal;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.funcionario.FuncionarioTable;
import view.login.ControllerLogin;
import view.proprietario.ControllerPropTable;
import view.recado.ControllerRecadoTable;
import view.sindico.ControllerSindico;
import view.visitante.ControllerTable;

public class Principal {

	public static void main(String[] args) {

		new ControllerLogin().execute();
	}

}
