package entity;

import entity.Morador;

public class Morador {

	private int idMorador;
	private String nomeMorador;
	private String telefoneMorador;
	private String emailMorador;
	
	public Morador(int idMorador, String nomeMorador, String telefoneMorador, String emailMorador) {
		super();
		this.idMorador = idMorador;
		this.nomeMorador = nomeMorador;
		this.telefoneMorador = telefoneMorador;
		this.emailMorador = emailMorador;
	}
	public Morador(String nomeMorador, String telefoneMorador, String emailMorador) {
		super();
		this.nomeMorador = nomeMorador;
		this.telefoneMorador = telefoneMorador;
		this.emailMorador = emailMorador;
	}
	public Morador() {
		super();
	}
	public int getIdMorador() {
		return idMorador;
	}
	public void setIdMorador(int idMorador) {
		this.idMorador = idMorador;
	}
	public String getNomeMorador() {
		return nomeMorador;
	}
	public void setNomeMorador(String nomeMorador) {
		this.nomeMorador = nomeMorador;
	}
	public String getTelefoneMorador() {
		return telefoneMorador;
	}
	public void setTelefoneMorador(String telefoneMorador) {
		this.telefoneMorador = telefoneMorador;
	}
	public String getEmailMorador() {
		return emailMorador;
	}
	public void setEmailMorador(String emailMorador) {
		this.emailMorador = emailMorador;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailMorador == null) ? 0 : emailMorador.hashCode());
		result = prime * result + idMorador;
		result = prime * result + ((nomeMorador == null) ? 0 : nomeMorador.hashCode());
		result = prime * result + ((telefoneMorador == null) ? 0 : telefoneMorador.hashCode());
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
		Morador other = (Morador) obj;
		if (emailMorador == null) {
			if (other.emailMorador != null)
				return false;
		} else if (!emailMorador.equals(other.emailMorador))
			return false;
		if (idMorador != other.idMorador)
			return false;
		if (nomeMorador == null) {
			if (other.nomeMorador != null)
				return false;
		} else if (!nomeMorador.equals(other.nomeMorador))
			return false;
		if (telefoneMorador == null) {
			if (other.telefoneMorador != null)
				return false;
		} else if (!telefoneMorador.equals(other.telefoneMorador))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Morador [idMorador=" + idMorador + ", nomeMorador=" + nomeMorador + ", telefoneMorador="
				+ telefoneMorador + ", emailMorador=" + emailMorador + "]";
	}	
	
}
