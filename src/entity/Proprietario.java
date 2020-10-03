package entity;

public class Proprietario {

	private int idProprietario;
	private String nomeProprietario;
	private String cpfProprietario;
	private String rgProprietario;
	private String numerotelefoneProprietario;
	private String emailProprietario;
	
	public Proprietario() {
		
	}
	
	public Proprietario(int id, String nome, String cpf, String rg, String telefone, String email) {
		super();
		this.idProprietario = id;
		this.nomeProprietario = nome;
		this.cpfProprietario = cpf;
		this.rgProprietario = rg;
		this.numerotelefoneProprietario = telefone;
		this.emailProprietario = email;
	}
	
	public Proprietario(String nome, String cpf, String rg, String telefone, String email) {
		super();
		this.nomeProprietario = nome;
		this.cpfProprietario = cpf;
		this.rgProprietario = rg;
		this.numerotelefoneProprietario = telefone;
		this.emailProprietario = email;
	}

	public int getIdProprietario() {
		return idProprietario;
	}

	public void setIdProprietario(int idProprietario) {
		this.idProprietario = idProprietario;
	}

	public String getNomeProprietario() {
		return nomeProprietario;
	}

	public void setNomeProprietario(String nomeProprietario) {
		this.nomeProprietario = nomeProprietario;
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

	public String getNumerotelefoneProprietario() {
		return numerotelefoneProprietario;
	}

	public void setNumerotelefoneProprietario(String numerotelefoneProprietario) {
		this.numerotelefoneProprietario = numerotelefoneProprietario;
	}

	public String getEmailProprietario() {
		return emailProprietario;
	}

	public void setEmailProprietario(String emailProprietario) {
		this.emailProprietario = emailProprietario;
	}

	@Override
	public String toString() {
		return "Proprietario [idProprietario=" + idProprietario + ", nomeProprietario=" + nomeProprietario
				+ ", cpfProprietario=" + cpfProprietario + ", rgProprietario=" + rgProprietario
				+ ", numerotelefoneProprietario=" + numerotelefoneProprietario + ", emailProprietario="
				+ emailProprietario + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpfProprietario == null) ? 0 : cpfProprietario.hashCode());
		result = prime * result + ((emailProprietario == null) ? 0 : emailProprietario.hashCode());
		result = prime * result + idProprietario;
		result = prime * result + ((nomeProprietario == null) ? 0 : nomeProprietario.hashCode());
		result = prime * result + ((numerotelefoneProprietario == null) ? 0 : numerotelefoneProprietario.hashCode());
		result = prime * result + ((rgProprietario == null) ? 0 : rgProprietario.hashCode());
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
		if (nomeProprietario == null) {
			if (other.nomeProprietario != null)
				return false;
		} else if (!nomeProprietario.equals(other.nomeProprietario))
			return false;
		if (numerotelefoneProprietario == null) {
			if (other.numerotelefoneProprietario != null)
				return false;
		} else if (!numerotelefoneProprietario.equals(other.numerotelefoneProprietario))
			return false;
		if (rgProprietario == null) {
			if (other.rgProprietario != null)
				return false;
		} else if (!rgProprietario.equals(other.rgProprietario))
			return false;
		return true;
	}
	
	
}
