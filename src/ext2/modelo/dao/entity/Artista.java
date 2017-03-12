package ext2.modelo.dao.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the artista database table.
 * 
 */
@Entity
@Table(name="artista")
@NamedQuery(name="Artista.findAll", query="SELECT a FROM Artista a")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public class Artista implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String TODOS = "Artista.findAll";

	@Id
	private String dni;

	private String apellidos;

	private int edad;

	private String nombre;

	public Artista() {
	}
	
	public Artista(String dni, String nombre, String apellidos, int edad){
		this.dni=dni;
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.edad=edad;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getEdad() {
		return this.edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}