package principal;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.login.ControllerLogin;
import view.sindico.ControllerSindico;
import view.visitante.ControllerTable;

public class Principal {

	public static void main(String[] args) {

		//new ControllerSindico().execute();
		new ControllerTable().execute();
//		new ControllerLogin().execute();
	}

}
