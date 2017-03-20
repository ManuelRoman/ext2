package ext2.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.persistence.EntityExistsException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ext2.dao.BeanDaoConsultasIMPL;


public class Sugerir extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Fuente de datos.
	 */
	private String unidadPersistencia;
	
	/**
	 * Objeto encapsula toda la información a nivel de aplicación.
	 */
	private ServletContext sc;
	
	public Sugerir(){
		super();
	}
	
	/**
	 * Inicializa el servlet, y le proporciona un objeto, ServletConfig con
	 * información de nivel de aplicación sobre el contexto de datos que rodea
	 * al servlet en el contenedor web.
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// Imprescindible llamar a super.init(config) para tener acceso a la
		// configuración del servlet a nivel de contenedor web.
		super.init(config);
		// En este punto se procedería a obtener las referencias a las fuentes
		// de datos de la aplicación.
		sc = config.getServletContext();
		setUPApp((String) sc.getInitParameter("unidadPersistencia"));
		sc.setAttribute("ext2romangracia", getUPApp());
		sc.setAttribute("appOperativa", "true");
		if (getUPApp() == null){
			System.out.println("la unidad de persistencia es null.");
			sc.setAttribute("appOperativa", "false");
		}
	}
	
	/**
	 * Prepara el servlet para su eliminación.
	 */
	public void destroy() {
		// Elimina el datasource del ámbito de aplicación, liberando todos los
		// recursos que tuviera asignados.
		sc.removeAttribute("ext2romangracia");
		sc.removeAttribute("appOperativa");
		// Elimina el ámbito de aplicación.
		sc = null;
	}
	
	/**
	 * Establece la fuente de datos para la aplicación.
	 */
	public void setUPApp(String unidadPersistencia) {
		this.unidadPersistencia = unidadPersistencia;
	}

	/**
	 * Obtiene la referencia a la fuente de datos de la aplicación.
	 */
	public String getUPApp() {
		return this.unidadPersistencia;
	}
	
	/**
	 * Procesamiento de la petición en modo GET. Se reenvía al método doPost()
	 * @param request Petición
	 * @param response Respuesta
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * Procesamiento de la petición en modo POST
	 * @param request Petición
	 * @param response Respuesta
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dato=request.getParameter("dato");
		System.out.println("Dato a buscar: "+ dato);
		ArrayList<String> listaDNIs = new ArrayList();
		StringBuilder objJSON = null;
		BeanDaoConsultasIMPL daoConsultas = (BeanDaoConsultasIMPL) sc.getAttribute("daoConsultas");
		if (daoConsultas == null){
			daoConsultas = new BeanDaoConsultasIMPL(getUPApp());
		}
		try{
			daoConsultas.getConexion();
			listaDNIs = daoConsultas.obtenerDNIs(dato);
		} catch (EntityExistsException e) {
			System.out.println("Excepción de existencia previa de la entidad.");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println("Excepción de instancia no es tipo entidad.");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error desconocido en transacción.");
			e.printStackTrace();
		} finally {
			try {
				daoConsultas.close();
			} catch (Exception e) {
				System.out.println("Error al cerrar la conexión.");
				e.printStackTrace();
			}
		}
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (listaDNIs.size() >0) {
			objJSON = new StringBuilder("[");
            for (int i = 0; i<listaDNIs.size(); i++) {
				objJSON.append("\""+listaDNIs.get(i)+"\",");
    		}
			objJSON.replace(objJSON.length()-1, objJSON.length(), "]");
		} else {
			objJSON = new StringBuilder("[]");
		}
		System.out.println("Cadena que se enviará: "+objJSON.toString());
		out.write(objJSON.toString());
		out.close();
	}
}
