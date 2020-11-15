package entity;

import java.sql.Date;

public class Apartamento {

	private int idAp;
	private int numAp;
	private int qtdMorador;
	private char animalEstimacao;
	private int qtdAnimal;
	private int andarAp;
	private int blocoAp;
	private Date dataEntrada;
	private String statusAp;
	private int vagaVeiculo;

	public Apartamento(int idAp, int numAp, int qtdMorador, char animalEstimacao, int qtdAnimal, int andarAp,
			int blocoAp, Date dataEntrada, String statusAp, int vagaVeiculo) {
		super();
		this.idAp = idAp;
		this.numAp = numAp;
		this.qtdMorador = qtdMorador;
		this.animalEstimacao = animalEstimacao;
		this.qtdAnimal = qtdAnimal;
		this.andarAp = andarAp;
		this.blocoAp = blocoAp;
		this.dataEntrada = dataEntrada;
		this.statusAp = statusAp;
		this.vagaVeiculo = vagaVeiculo;
	}

	public Apartamento(int numAp, int qtdMorador, char animalEstimacao, int qtdAnimal, int andarAp, int blocoAp,
			Date dataEntrada, String statusAp, int vagaVeiculo) {
		super();
		this.numAp = numAp;
		this.qtdMorador = qtdMorador;
		this.animalEstimacao = animalEstimacao;
		this.qtdAnimal = qtdAnimal;
		this.andarAp = andarAp;
		this.blocoAp = blocoAp;
		this.dataEntrada = dataEntrada;
		this.statusAp = statusAp;
		this.vagaVeiculo = vagaVeiculo;
	}

	public Apartamento() {
		super();
	}

	public int getIdAp() {
		return idAp;
	}

	public void setIdAp(int idAp) {
		this.idAp = idAp;
	}

	public int getNumAp() {
		return numAp;
	}

	public void setNumAp(int numAp) {
		this.numAp = numAp;
	}

	public int getQtdMorador() {
		return qtdMorador;
	}

	public void setQtdMorador(int qtdMorador) {
		this.qtdMorador = qtdMorador;
	}

	public char getAnimalEstimacao() {
		return animalEstimacao;
	}

	public void setAnimalEstimacao(char animalEstimacao) {
		this.animalEstimacao = animalEstimacao;
	}

	public int getQtdAnimal() {
		return qtdAnimal;
	}

	public void setQtdAnimal(int qtdAnimal) {
		this.qtdAnimal = qtdAnimal;
	}

	public int getAndarAp() {
		return andarAp;
	}

	public void setAndarAp(int andarAp) {
		this.andarAp = andarAp;
	}

	public int getBlocoAp() {
		return blocoAp;
	}

	public void setBlocoAp(int blocoAp) {
		this.blocoAp = blocoAp;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public String getStatusAp() {
		return statusAp;
	}

	public void setStatusAp(String statusAp) {
		this.statusAp = statusAp;
	}

	public int getVagaVeiculo() {
		return vagaVeiculo;
	}

	public void setVagaVeiculo(int vagaVeiculo) {
		this.vagaVeiculo = vagaVeiculo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + andarAp;
		result = prime * result + animalEstimacao;
		result = prime * result + blocoAp;
		result = prime * result + ((dataEntrada == null) ? 0 : dataEntrada.hashCode());
		result = prime * result + idAp;
		result = prime * result + numAp;
		result = prime * result + qtdAnimal;
		result = prime * result + qtdMorador;
		result = prime * result + ((statusAp == null) ? 0 : statusAp.hashCode());
		result = prime * result + vagaVeiculo;
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
		Apartamento other = (Apartamento) obj;
		if (andarAp != other.andarAp)
			return false;
		if (animalEstimacao != other.animalEstimacao)
			return false;
		if (blocoAp != other.blocoAp)
			return false;
		if (dataEntrada == null) {
			if (other.dataEntrada != null)
				return false;
		} else if (!dataEntrada.equals(other.dataEntrada))
			return false;
		if (idAp != other.idAp)
			return false;
		if (numAp != other.numAp)
			return false;
		if (qtdAnimal != other.qtdAnimal)
			return false;
		if (qtdMorador != other.qtdMorador)
			return false;
		if (statusAp == null) {
			if (other.statusAp != null)
				return false;
		} else if (!statusAp.equals(other.statusAp))
			return false;
		if (vagaVeiculo != other.vagaVeiculo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Apartamento [idAp=" + idAp + ", numAp=" + numAp + ", qtdMorador=" + qtdMorador + ", animalEstimacao="
				+ animalEstimacao + ", qtdAnimal=" + qtdAnimal + ", andarAp=" + andarAp + ", blocoAp=" + blocoAp
				+ ", dataEntrada=" + dataEntrada + ", statusAp=" + statusAp + ", vagaVeiculo=" + vagaVeiculo + "]";
	}
}
