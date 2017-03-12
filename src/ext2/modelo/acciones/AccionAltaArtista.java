package ext2.modelo.acciones;

import java.io.IOException;

import javax.persistence.EntityExistsException;
import javax.persistence.RollbackException;
import javax.persistence.TransactionRequiredException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ext2.controlador.Accion;
import ext2.dao.BeanDaoInsercionIMPL;
import ext2.modelo.beans.BeanError;
import ext2.modelo.dao.entity.*;

/**
 * Encapsula la acción de dar de alta un artistas en la base de datos
 */
public class AccionAltaArtista implements Accion{
	
	/**
	 * Información de la vista que se devolverá
	 */
	private String vista;
	
	/**
	 * Información de la vista si no se producen errores
	 */
	private final String vistaOK = "WEB-INF/resultadoAlta.jsp";
	
	/**
	 * Información de la vista si hay algún error
	 */
	private final String vistaError = "WEB-INF/gesError.jsp";
	
	/**
	 * Información del modelo que se devolverá
	 */
	private String modelo = "Ha sido dado de alta";
	
	/**
	 * Información del error producido
	 */
	private BeanError error;
	
	/**
	 * Objeto que encapsula la información a nivel de la aplicación
	 */
	private ServletContext sc;
	
	/**
	 * Información de la base de datos
	 */
	private String up;	
	
	/**
	 * Información de la sesion
	 */
	private HttpSession sesion;
	
	/** 
	 * Ejecuta el proceso asociado a la acción.
	 * @param request Objeto que encapsula la petición.
	 * @param response Objeto que encapsula la respuesta.
	 * @return true o false en función de que no se hayan producido errores o lo contrario.
	 * @see fotogramas.controlador.Accion#ejecutar(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public boolean ejecutar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean resultado = true;
		String tipo = request.getParameter("tipo");
		String dni = request.getParameter("dni");
		String nombre= request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		Integer edad = Integer.parseInt(request.getParameter("edad"));
		String actuacion = request.getParameter("actuacion");
		BeanDaoInsercionIMPL daoInsercion = (BeanDaoInsercionIMPL) sesion.getAttribute("daoInsercion");
		if (daoInsercion == null){
			daoInsercion = new BeanDaoInsercionIMPL(this.up);
			sesion.setAttribute("daoInsercion", daoInsercion);
		}
		Artista artista = null;
		switch (tipo) {
		case "cantante":
			artista = new Cantante(dni, nombre, apellidos, edad, actuacion);
			System.out.println(artista.getClass());
			break;
		case "acrobata":
			artista = new Acrobata(dni, nombre, apellidos, edad, actuacion);
			break;
		case "musico":
			artista = new Musico(dni, nombre, apellidos, edad, actuacion);
		}
		try {
			daoInsercion.getConexion();
			daoInsercion.insertaArtista(artista);
		} catch(EntityExistsException e){
			error = new BeanError(3, "El Artista ya existe, debe haberse equivacado en el DNI"); //debería ser este
			resultado= false;
			e.printStackTrace();
		} catch (TransactionRequiredException e) {
			resultado= false;
			e.printStackTrace();
		} catch (RollbackException e) {
			error = new BeanError(3, "El Artista ya existe, debe haberse equivacado en el DNI",e);
			resultado= false;
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			resultado= false;
			e.printStackTrace();
			error = new BeanError(4, "Error en la base de datos",e);
		} catch (BeanError be) {
			resultado= false;
			error = be;
			be.printStackTrace();
		} catch (Exception e) {
			resultado= false;
			error = new BeanError(4, "Error en la base de datos",e);
			e.printStackTrace();
		} finally {
			try {
				daoInsercion.close();
			} catch (Exception e) {
				System.out.println("Error al cerrar la conexión.");
				e.printStackTrace();
			}
		}
		if (resultado == true) {
			vista = vistaOK;
			sesion.setAttribute("artista", artista);
		} else
			vista = vistaError;
		return resultado;
	}

	/**
	* Devuelve la vista actual.
	* @param La vista a devolver al usuario.
	*/
	@Override
	public String getVista() {
		return this.vista;
	}

	/**
	 * Devuelve el modelo con el que trabajará la vista.
	 * @return El modelo a procesar por la vista. 
	 */
	@Override
	public Object getModelo() {
		return this.modelo;
	}

	/**
	 * Establece el contexto del servlet (nivel aplicación)
	 * @param sc Objeto ServletContext que encapsula el ámbito de aplicación.
	 */
	@Override
	public void setSc(ServletContext sc) {
		this.sc=sc;
	}

	/**
	 * Devuelve un objeto de error asociado al procesamiento de la acción.
	 * @return Objeto que encapsula una situación de error producida durante
	 * la ejecución de la acción.
	 */
	@Override
	public BeanError getError() {
		return this.error;
	}

	/**
	 * Establece el datasource con el que se trabajará
	 * @param ds Datasource
	 */
	@Override
	public void setUP(String up) {
		this.up=up;
	}

	/**
	 * Establece el objeto que encapsula la sesión actual.
	 * @param sesion La sesión a establecer en la acción.
	 */
	@Override
	public void setSesion(HttpSession sesion) {
		this.sesion=sesion;
	}

}
