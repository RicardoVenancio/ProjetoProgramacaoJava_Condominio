package principal;

import java.util.List;

import dao.FuncionarioDao;
import dao.sindicoDao;
import entity.Funcionario;
import entity.Sindico;
import services.ValidaCPF;
import view.login.ControllerLogin;
import view.menu.ControllerMenuFuncionario;
import view.menu.ControllerMenuSindico;

public class Principal {

	public static void main(String[] args) {
//		new ControllerMenuSindico().execute();
//		new ControllerMenuFuncionario().execute();
		List<Funcionario> func = new FuncionarioDao().listAll(); 
		List<Sindico> sind = new sindicoDao().listAll(); 
		System.out.println("Funcionarios");
		System.out.println(func);
		System.out.println("Sindicos");
		System.out.println(sind);
		new ControllerLogin().execute();
	}

}
