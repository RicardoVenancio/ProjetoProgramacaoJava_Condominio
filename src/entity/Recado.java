package entity;

import java.sql.Date;

public class Recado {
	private int id;
	private String Nome;
	private String Texto;
	private Date Datarecado;
 
	// GET E SET  
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getTexto() {
		return Texto;
	}
	public void setTexto(String texto) {
		Texto = texto;
	}
	public Date getDatarecado() {
		return Datarecado;
	}
	public void setDatarecado(Date datarecado) {
		Datarecado = datarecado;
	}
	
	
	//HHASH E EQUALS
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Datarecado == null) ? 0 : Datarecado.hashCode());
		result = prime * result + ((Nome == null) ? 0 : Nome.hashCode());
		result = prime * result + ((Texto == null) ? 0 : Texto.hashCode());
		result = prime * result + id;
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
		Recado other = (Recado) obj;
		if (Datarecado == null) {
			if (other.Datarecado != null)
				return false;
		} else if (!Datarecado.equals(other.Datarecado))
			return false;
		if (Nome == null) {
			if (other.Nome != null)
				return false;
		} else if (!Nome.equals(other.Nome))
			return false;
		if (Texto == null) {
			if (other.Texto != null)
				return false;
		} else if (!Texto.equals(other.Texto))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
	//TO STRING
	
	@Override
	public String toString() {
		return "Recado [id=" + id + ", Nome=" + Nome + ", Texto=" + Texto + ", Datarecado=" + Datarecado + "]";
	}
	
	public Recado() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Recado(int id, String nome, String texto, Date datarecado) {
		super();
		this.id = id;
		Nome = nome;
		Texto = texto;
		Datarecado = datarecado;
	}
	public Recado(String nome, String texto, Date datarecado) {
		super();
		Nome = nome;
		Texto = texto;
		Datarecado = datarecado;
	}

	
}
