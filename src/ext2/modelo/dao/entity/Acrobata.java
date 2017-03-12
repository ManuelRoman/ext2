package ext2.modelo.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="artista")
@NamedQuery(name="Acrobata.findAll", query="SELECT a FROM Artista a")
public class Acrobata extends Artista implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String actuacion;
	
	public Acrobata(){
		
	}
	
	public Acrobata(String dni, String nombre, String apellidos, int edad, String actuacion){
		super(dni, nombre, apellidos, edad);
		this.actuacion = actuacion;
	}

	@Column(name = "actuacion")
	public String getActuacion() {
		return "El acróbata realizará: "+actuacion;
	}

	public void setActuacion(String actuacion) {
		this.actuacion = actuacion;
	}
	
	

}
