package principal;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.funcionario.ControllerFuncionario;
import view.funcionario.FuncionarioTable;
import view.login.ControllerLogin;
import view.sindico.ControllerSindico;
import view.visitante.ControllerTable;

public class Principal {

	public static void main(String[] args) {

		//new ControllerSindico().execute();
		//new ControllerTable().execute();
			new FuncionarioTable().execute();
//		new ControllerLogin().execute();
			//new ControllerFuncionario().execute();
	}

}
