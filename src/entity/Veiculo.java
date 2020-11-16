package entity;

import java.sql.Date;

public class Veiculo {
	private int id;
	private String Placaveiculo;
	private String Modeloveiculo;
	private String Corveiculo;
	private String Anoveiculo;
	
	
	
	// GET E SET
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlacaveiculo() {
		return Placaveiculo;
	}
	public void setPlacaveiculo(String placaveiculo) {
		Placaveiculo = placaveiculo;
	}
	public String getModeloveiculo() {
		return Modeloveiculo;
	}
	public void setModeloveiculo(String modeloveiculo) {
		this.Modeloveiculo = modeloveiculo;
	}
	public String getCorveiculo() {
		return Corveiculo;
	}
	public void setCorveiculo(String corveiculo) {
		Corveiculo = corveiculo;
	}
	public String getAnoveiculo() {
		return Anoveiculo;
	}
	public void setAnoveiculo(String anoveiculo) {
		Anoveiculo = anoveiculo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Anoveiculo == null) ? 0 : Anoveiculo.hashCode());;
		result = prime * result + ((Corveiculo == null) ? 0 : Corveiculo.hashCode());
		result = prime * result + ((Placaveiculo == null) ? 0 : Placaveiculo.hashCode());
		result = prime * result + id;
		result = prime * result + ((Modeloveiculo == null) ? 0 : Modeloveiculo.hashCode());
		return result;
	}
	
	
	//HASH E EQUALS
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Veiculo other = (Veiculo) obj;
		if (Anoveiculo != other.Anoveiculo)
			return false;
		if (Corveiculo == null) {
			if (other.Corveiculo != null)
				return false;
		} else if (!Corveiculo.equals(other.Corveiculo))
			return false;
		if (Placaveiculo == null) {
			if (other.Placaveiculo != null)
				return false;
		} else if (!Placaveiculo.equals(other.Placaveiculo))
			return false;
		if (id != other.id)
			return false;
		if (Modeloveiculo == null) {
			if (other.Modeloveiculo != null)
				return false;
		} else if (!Modeloveiculo.equals(other.Modeloveiculo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Veiculo [id=" + id + ", Placaveiculo=" + Placaveiculo + ", modeloveiculo=" + Modeloveiculo
				+ ", Corveiculo=" + Corveiculo + ", Anoveiculo=" + Anoveiculo + "]";
	}
	
	
	public Veiculo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	 
	public Veiculo(int id, String placaveiculo, String modeloveiculo, String corveiculo, String anoveiculo) {
		super();
		this.id = id;
		Placaveiculo = placaveiculo;
		Modeloveiculo = modeloveiculo;
		Corveiculo = corveiculo;
		Anoveiculo = anoveiculo;
	}
	public Veiculo( String placaveiculo, String modeloveiculo, String corveiculo, String anoveiculo) {
		super();
		Placaveiculo = placaveiculo;
		Modeloveiculo = modeloveiculo;
		Corveiculo = corveiculo;
		Anoveiculo = anoveiculo;
	}


	

	
	
	
	
}

