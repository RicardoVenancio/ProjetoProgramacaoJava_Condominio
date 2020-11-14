package entity;

public class Proprietario extends Pessoa {

	private int idProprietario;
	private String cpfProprietario;
	private String rgProprietario;
	private String emailProprietario;

	public int getIdProprietario() {
		return idProprietario;
	}

	public void setIdProprietario(int idProprietario) {
		this.idProprietario = idProprietario;
	}

	public String getCpfProprietario() {
		return cpfProprietario;
	}

	public void setCpfProprietario(String cpfProprietario) {
		this.cpfProprietario = cpfProprietario;
	}

	public String getRgProprietario() {
		return rgProprietario;
	}

	public void setRgProprietario(String rgProprietario) {
		this.rgProprietario = rgProprietario;
	}

	public String getEmailProprietario() {
		return emailProprietario;
	}

	public void setEmailProprietario(String emailProprietario) {
		this.emailProprietario = emailProprietario;
	}

	// HASH CODE

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cpfProprietario == null) ? 0 : cpfProprietario.hashCode());
		result = prime * result + ((emailProprietario == null) ? 0 : emailProprietario.hashCode());
		result = prime * result + idProprietario;
		result = prime * result + ((rgProprietario == null) ? 0 : rgProprietario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proprietario other = (Proprietario) obj;
		if (cpfProprietario == null) {
			if (other.cpfProprietario != null)
				return false;
		} else if (!cpfProprietario.equals(other.cpfProprietario))
			return false;
		if (emailProprietario == null) {
			if (other.emailProprietario != null)
				return false;
		} else if (!emailProprietario.equals(other.emailProprietario))
			return false;
		if (idProprietario != other.idProprietario)
			return false;
		if (rgProprietario == null) {
			if (other.rgProprietario != null)
				return false;
		} else if (!rgProprietario.equals(other.rgProprietario))
			return false;
		return true;
	}

	// TO STRING
	@Override
	public String toString() {
		return "Proprietario [idProprietario=" + idProprietario + ", getNome()=" + getNome() + ", cpfProprietario="
				+ cpfProprietario + ", rgProprietario=" + rgProprietario + ", getTelefone()=" + getTelefone()
				+ ", emailProprietario=" + emailProprietario + "]";
	}

	// CONSTRUCTOR COM VÁZIO
	public Proprietario() {
		super();
	}

	// CONSTRUCTOR COM SUPER DE PESSOA VÁZIO
	public Proprietario(String nome, String telefone) {
		super(nome, telefone);
	}

	// CONSTRUCTOR COM SUPER DE PESSOA COM ID
	public Proprietario(int idProprietario, String nome, String cpfProprietario, String rgProprietario, String telefone,
			String emailProprietario) {
		super(nome, telefone);
		this.idProprietario = idProprietario;
		this.cpfProprietario = cpfProprietario;
		this.rgProprietario = rgProprietario;
		this.emailProprietario = emailProprietario;
	}

	// CONSTRUCTOR COM SUPER DE PESSOA SEM ID
	public Proprietario(String nome, String cpfProprietario, String rgProprietario, String telefone,
			String emailProprietario) {
		super(nome, telefone);
		this.cpfProprietario = cpfProprietario;
		this.rgProprietario = rgProprietario;
		this.emailProprietario = emailProprietario;
	}

}
