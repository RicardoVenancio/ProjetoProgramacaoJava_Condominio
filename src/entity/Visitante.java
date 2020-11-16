package entity;

import java.sql.Date;

public class Visitante extends Pessoa {
	private int id;
	private String RG;
	private String CPF;
	private String Email;
	private Date DataVisita;

	public Visitante(int id, String nome, String RG, String CPF, String telefone, String Email, Date DataVisita) {
		super(nome, telefone);
		this.id = id;
		this.RG = RG;
		this.CPF = CPF;
		this.Email = Email;
		this.DataVisita = DataVisita;
	}

	public Visitante(String nome, String RG, String CPF, String telefone, String Email, Date DataVisita) {
		super(nome, telefone);
		this.RG = RG;
		this.CPF = CPF;
		this.Email = Email;
		this.DataVisita = DataVisita;
	}

	public Visitante() {
		super();
	}

	public Visitante(String nome, String telefone) {
		super(nome, telefone);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRG() {
		return RG;
	}

	public void setRG(String rG) {
		RG = rG;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Date getDataVisita() {
		return DataVisita;
	}

	public void setDataVisita(Date dataVisita) {
		DataVisita = dataVisita;
	}

	// TO STRING
	@Override
	public String toString() {
		return "Visitante [id=" + id + ", getNome()=" + getNome() + ", RG=" + RG + ", CPF=" + CPF + ", getTelefone()="
				+ getTelefone() + ", Email=" + Email + ", DataVisita=" + DataVisita + "]";
	}

	// HASH CODE
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((CPF == null) ? 0 : CPF.hashCode());
		result = prime * result + ((DataVisita == null) ? 0 : DataVisita.hashCode());
		result = prime * result + ((Email == null) ? 0 : Email.hashCode());
		result = prime * result + ((RG == null) ? 0 : RG.hashCode());
		result = prime * result + id;
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
		Visitante other = (Visitante) obj;
		if (CPF == null) {
			if (other.CPF != null)
				return false;
		} else if (!CPF.equals(other.CPF))
			return false;
		if (DataVisita == null) {
			if (other.DataVisita != null)
				return false;
		} else if (!DataVisita.equals(other.DataVisita))
			return false;
		if (Email == null) {
			if (other.Email != null)
				return false;
		} else if (!Email.equals(other.Email))
			return false;
		if (RG == null) {
			if (other.RG != null)
				return false;
		} else if (!RG.equals(other.RG))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
