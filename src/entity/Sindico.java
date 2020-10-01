package entity;

import java.sql.Date;

public class Sindico {
	public Sindico(int idSINDICO, String nomeSindico, Date dataNascimento, String sexoSindico, String emailSindico,
			String numerotelefoneSindico, Date dataAdmissao) {
		super();
		this.idSINDICO = idSINDICO;
		this.nomeSindico = nomeSindico;
		this.dataNascimento = dataNascimento;
		this.sexoSindico = sexoSindico;
		this.emailSindico = emailSindico;
		this.numerotelefoneSindico = numerotelefoneSindico;
		this.dataAdmissao = dataAdmissao;
	}
	
	public Sindico() {
		super();
	}
	
	public Sindico(String nomeSindico, Date dataNascimento, String sexoSindico, String emailSindico,
			String numerotelefoneSindico, Date dataAdmissao) {
		super();
		this.nomeSindico = nomeSindico;
		this.dataNascimento = dataNascimento;
		this.sexoSindico = sexoSindico;
		this.emailSindico = emailSindico;
		this.numerotelefoneSindico = numerotelefoneSindico;
		this.dataAdmissao = dataAdmissao;
	}

	private int idSINDICO;
	private String nomeSindico;
	private Date dataNascimento;
	private String sexoSindico;
	private String emailSindico;
	private String numerotelefoneSindico;
	private Date dataAdmissao;
	
	
	
	
	/////////////////////////////////////GET SET/////////////////////////////////////////////
	public int getIdSINDICO() {
		return idSINDICO;
	}
	public void setIdSINDICO(int idSINDICO) {
		this.idSINDICO = idSINDICO;
	}
	public String getNomeSindico() {
		return nomeSindico;
	}
	public void setNomeSindico(String nomeSindico) {
		this.nomeSindico = nomeSindico;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getSexoSindico() {
		return sexoSindico;
	}
	public void setSexoSindico(String sexoSindico) {
		this.sexoSindico = sexoSindico;
	}
	public String getEmailSindico() {
		return emailSindico;
	}
	public void setEmailSindico(String emailSindico) {
		this.emailSindico = emailSindico;
	}
	public String getNumerotelefoneSindico() {
		return numerotelefoneSindico;
	}
	public void setNumerotelefoneSindico(String numerotelefoneSindico) {
		this.numerotelefoneSindico = numerotelefoneSindico;
	}
	public Date getDataAdmissao() {
		return dataAdmissao;
	}
	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	
	
	/////////////////////////////////////TOSTRING/////////////////////////////////////////////
	@Override
	public String toString() {
		return "Sindico [idSINDICO=" + idSINDICO + ", nomeSindico=" + nomeSindico + ", dataNascimento=" + dataNascimento
				+ ", sexoSindico=" + sexoSindico + ", emailSindico=" + emailSindico + ", numerotelefoneSindico="
				+ numerotelefoneSindico + ", dataAdmissao=" + dataAdmissao + "]";
	}
	
	
	
	
	/////////////////////////////////////HASHCODE/////////////////////////////////////////////
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataAdmissao == null) ? 0 : dataAdmissao.hashCode());
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((emailSindico == null) ? 0 : emailSindico.hashCode());
		result = prime * result + idSINDICO;
		result = prime * result + ((nomeSindico == null) ? 0 : nomeSindico.hashCode());
		result = prime * result + ((numerotelefoneSindico == null) ? 0 : numerotelefoneSindico.hashCode());
		result = prime * result + ((sexoSindico == null) ? 0 : sexoSindico.hashCode());
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
		Sindico other = (Sindico) obj;
		if (dataAdmissao == null) {
			if (other.dataAdmissao != null)
				return false;
		} else if (!dataAdmissao.equals(other.dataAdmissao))
			return false;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (emailSindico == null) {
			if (other.emailSindico != null)
				return false;
		} else if (!emailSindico.equals(other.emailSindico))
			return false;
		if (idSINDICO != other.idSINDICO)
			return false;
		if (nomeSindico == null) {
			if (other.nomeSindico != null)
				return false;
		} else if (!nomeSindico.equals(other.nomeSindico))
			return false;
		if (numerotelefoneSindico == null) {
			if (other.numerotelefoneSindico != null)
				return false;
		} else if (!numerotelefoneSindico.equals(other.numerotelefoneSindico))
			return false;
		if (sexoSindico == null) {
			if (other.sexoSindico != null)
				return false;
		} else if (!sexoSindico.equals(other.sexoSindico))
			return false;
		return true;
	}
	
}
