package ext2.modelo.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="artista")
@NamedQuery(name="Cantante.findAll", query="SELECT c FROM Artista c")
public class Cantante extends Artista implements Serializable {
	private static final long serialVersionUID = 1L;

	private String actuacion;
	
	public Cantante(){
		
	}
	
	public Cantante(String dni, String nombre, String apellidos, int edad, String actuacion){
		super(dni, nombre, apellidos, edad);
		this.actuacion = actuacion;
	}

	@Column(name = "actuacion")
	public String getActuacion() {
		return "El cantate, cantará: "+actuacion;
	}

	public void setActuacion(String actuacion) {
		this.actuacion = actuacion;
	}
	
	
}
