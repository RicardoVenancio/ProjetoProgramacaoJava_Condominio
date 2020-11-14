package entity;

public abstract class Pessoa {

	
	private String nome;
	private String telefone;
	
	
	public Pessoa() { //CONSTRUCTOR VAZIO
		super();
	}
	
	public Pessoa(String nome, String telefone) { //CONSTRUCTOR POVOADO
		super();
		this.nome = nome;
		this.telefone = telefone;
	}

	
	public String getNome() { // GETS E SETS
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	@Override
	public String toString() { // TO STRING
		return "Pessoa [nome=" + nome + ", telefone=" + telefone + "]";
	}

	@Override
	public int hashCode() { //HASH CODE
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}
}