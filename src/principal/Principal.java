package principal;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.apartamento.ApartamentoController;
import view.login.ControllerLogin;
import view.morador.MoradorController;
import view.sindico.ControllerSindico;

public class Principal {

	public static void main(String[] args) {

		//new ControllerSindico().execute();
		new ApartamentoController().execute();
		//new MoradorController().execute();
	}
}
