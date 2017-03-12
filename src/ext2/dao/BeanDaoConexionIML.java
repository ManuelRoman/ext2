package ext2.dao;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BeanDaoConexionIML implements BeanDaoConexion{
	
	protected String unidadPersistencia;
	protected EntityManagerFactory emf;
	protected EntityManager em;
	
	public BeanDaoConexionIML(String unidadPersistencia){
		this.unidadPersistencia = unidadPersistencia;
	}

	@Override
	public void getConexion() throws Exception,EntityExistsException, IllegalStateException{
		if (this.em == null){
			this.emf = Persistence.createEntityManagerFactory(this.unidadPersistencia);
			this.em = emf.createEntityManager();
		}	
	}

	@Override
	public void close() throws Exception,EntityExistsException, IllegalStateException {
		if (this.em != null){
			this.em.close();
			this.emf.close();
		}
		this.em = null;
		this.emf = null;
	}

}
