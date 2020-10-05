package entity ;

import java.sql.Date;

public class Funcionario {
private int id;
private String nome;
private String cargo;
private String rg;
private String cpf;
private String telefone;
private String sexo;
private Date dataadmissao;
private String salario;


// GET E SET

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getCargo() {
	return cargo;
}
public void setCargo(String cargo) {
	this.cargo = cargo;
}
public String getRg() {
	return rg;
}
public void setRg(String rg) {
	this.rg = rg;
}
public Date getDataadmissao() {
	return dataadmissao;
}
public void setDataadmissao(Date dataadmissao) {
	this.dataadmissao = dataadmissao;
}
public String getCpf() {
	return cpf;
}
public void setCpf(String cpf) {
	this.cpf = cpf;
}
public String getTelefone() {
	return telefone;
}
public void setTelefone(String telefone) {
	this.telefone = telefone;
}
public String getSexo() {
	return sexo;
}
public void setSexo(String sexo) {
	this.sexo = sexo;
}
public String getSalario() {
	return salario;
}
public void setSalario( String salario) {
	this.salario = salario;
}


//HASH E EQUALS

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((cargo == null) ? 0 : cargo.hashCode());
	result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
	result = prime * result + ((dataadmissao == null) ? 0 : dataadmissao.hashCode());
	result = prime * result + id;
	result = prime * result + ((nome == null) ? 0 : nome.hashCode());
	result = prime * result + ((rg == null) ? 0 : rg.hashCode());
	result = prime * result + ((salario == null) ? 0 : salario.hashCode());
	result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
	result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
	return result;
} 
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Funcionario other = (Funcionario) obj;
	if (cargo == null) {
		if (other.cargo != null)
			return false;
	} else if (!cargo.equals(other.cargo))
		return false;
	if (cpf == null) {
		if (other.cpf != null)
			return false;
	} else if (!cpf.equals(other.cpf))
		return false;
	if (dataadmissao == null) {
		if (other.dataadmissao != null)
			return false;
	} else if (!dataadmissao.equals(other.dataadmissao))
		return false;
	if (id != other.id)
		return false;
	if (nome == null) {
		if (other.nome != null)
			return false;
	} else if (!nome.equals(other.nome))
		return false;
	if (rg == null) {
		if (other.rg != null)
			return false;
	} else if (!rg.equals(other.rg))
		return false;
	if (salario == null) {
		if (other.salario != null)
			return false;
	} else if (!salario.equals(other.salario))
		return false;
	if (sexo == null) {
		if (other.sexo != null)
			return false;
	} else if (!sexo.equals(other.sexo))
		return false;
	if (telefone == null) {
		if (other.telefone != null)
			return false;
	} else if (!telefone.equals(other.telefone))
		return false;
	return true;
}

//TO STRING



public Funcionario() {
	super();
	// TODO Auto-generated constructor stub
}

@Override
public String toString() {
	return "Funcionario [id=" + id + ", nome=" + nome + ", cargo=" + cargo + ", rg=" + rg + ", cpf=" + cpf
			+ ", telefone=" + telefone + ", sexo=" + sexo + ", dataadmissao=" + dataadmissao + ", salario=" + salario
			+ "]";
}
public Funcionario(int id, String nome, String cargo, String rg, String cpf, String telefone, String sexo,
		Date dataadmissao, String salario) {
	super();
	this.id = id;
	this.nome = nome;
	this.cargo = cargo;
	this.rg = rg;
	this.cpf = cpf;
	this.telefone = telefone;
	this.sexo = sexo;
	this.dataadmissao = dataadmissao;
	this.salario = salario;
}
public Funcionario(String nome, String cargo, String rg, String cpf, String telefone, String sexo, Date dataadmissao,
		String salario) {
	super();
	this.nome = nome;
	this.cargo = cargo;
	this.rg = rg;
	this.cpf = cpf;
	this.telefone = telefone;
	this.sexo = sexo;
	this.dataadmissao = dataadmissao;
	this.salario = salario;
}



} 


	


