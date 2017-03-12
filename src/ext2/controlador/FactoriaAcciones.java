/**
 * Instancia objetos de tipo Acción.
 * Es una clase abstracta que impide que se puedan instanciar objetos de ella,
 * pero permite que se obtengan clases derivadas.
 * Se encarga de obtener los objetos Acción específicos para una determinada acción.
 */
package ext2.controlador;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import ext2.modelo.acciones.AccionIndex;
import ext2.utilidades.LeePropiedades;

/**
 * Factoría que devuelve los objetos Accion que procesarán las peticiones
 */
public abstract class FactoriaAcciones {
	
	/**
	 * Información de la lista de acciones disponibles
	 */
	private static HashMap<String, Accion> listaAcciones = null;
	
	/**
	 * Método estático que crea un objeto acción.
	 * @param accion
	 * @return
	 */
	public static Accion creaAccion(String accion, String archivoAcciones) {
		System.out.println("Acceso a creaAccion, acción: " + accion);
		if (listaAcciones == null) {
			System.out.println("Accede al archivo propiedades");
			Properties propiedades = LeePropiedades.getPropiedades(archivoAcciones);
			listaAcciones = new HashMap<String, Accion>();
			Enumeration e = propiedades.keys();
			while (e.hasMoreElements()) {
				String clave = (String) e.nextElement();
				String valorAccion = (String) propiedades.get(clave);
				Class clase = null;
				try {
					clase = Class.forName(valorAccion);
					Accion valor = (Accion) clase.newInstance();
					listaAcciones.put(clave, valor);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} //Poner finally con una acción "error", donde se informe que la aplicación no está operativa?
			}
		}
		// Acción por defecto. Conduce a index.html.
		Accion accionSeleccionada = new AccionIndex();
		if (accion!= null){
			accionSeleccionada = listaAcciones.get(accion);
		}
		return accionSeleccionada;
	}

}
