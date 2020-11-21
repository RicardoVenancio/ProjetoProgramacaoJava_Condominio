package entity;

import java.sql.Date;

public class Sindico extends Pessoa {

	public Sindico(String nome, String cpf, int idSINDICO, Date dataNascimento, String sexoSindico, String emailSindico,
			String telefone, Date dataAdmissao) {
		super(nome, telefone);
		this.idSINDICO = idSINDICO;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.sexoSindico = sexoSindico;
		this.emailSindico = emailSindico;
		this.dataAdmissao = dataAdmissao;
	}

	public Sindico(String nome, String cpf, Date dataNascimento, String sexoSindico, String emailSindico,
			String telefone, Date dataAdmissao) {
		super(nome, telefone);

		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.sexoSindico = sexoSindico;
		this.emailSindico = emailSindico;
		this.dataAdmissao = dataAdmissao;
	}

	public Sindico() {
		super();
	}

	public Sindico(String nome, String telefone) {
		super(nome, telefone);
		// TODO Auto-generated constructor stub
	}

	private int idSINDICO;
	private String cpf;
	private Date dataNascimento;
	private String sexoSindico;
	private String emailSindico;
	private Date dataAdmissao;

	///////////////////////////////////// GET
	///////////////////////////////////// SET/////////////////////////////////////////////

	public int getIdSINDICO() {
		return idSINDICO;
	}

	public void setIdSINDICO(int idSINDICO) {
		this.idSINDICO = idSINDICO;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	///////////////////////////////////// TOSTRING/////////////////////////////////////////////

	@Override
	public String toString() {
		return "\r Sindico [idSINDICO=" + idSINDICO + ",getNome()=" + getNome() + ",\r cpf=" + cpf + ", dataNascimento="
				+ dataNascimento + ", sexoSindico=" + sexoSindico + ", emailSindico=" + emailSindico
				+ ",\r Telefone=" + getTelefone() + ", dataAdmissao=" + dataAdmissao + "]";
	}

	///////////////////////////////////// HASHCODE/////////////////////////////////////////////

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((dataAdmissao == null) ? 0 : dataAdmissao.hashCode());
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((emailSindico == null) ? 0 : emailSindico.hashCode());
		result = prime * result + idSINDICO;
		result = prime * result + ((sexoSindico == null) ? 0 : sexoSindico.hashCode());
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
		Sindico other = (Sindico) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
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
		if (sexoSindico == null) {
			if (other.sexoSindico != null)
				return false;
		} else if (!sexoSindico.equals(other.sexoSindico))
			return false;
		return true;
	}

}