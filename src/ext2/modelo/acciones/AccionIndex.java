package ext2.modelo.acciones;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ext2.controlador.Accion;
import ext2.modelo.beans.BeanError;

public class AccionIndex implements Accion{
	
	/**
	 * Información de la vista que se devolverá
	 */
	private String vista;
	
	/**
	 * Información de la vista si no se producen errores
	 */
	private final String vistaOK = "index.jsp";
	
	/**
	 * Información de la vista si hay algún error
	 */
	private final String vistaError = "WEB-INF/gesError.jsp";
	
	/**
	 * Información del modelo que se devolverá
	 */
	private String modelo = "Ha vuelto al indice";
	
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
		this.vista=vistaOK;
		return true;
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
