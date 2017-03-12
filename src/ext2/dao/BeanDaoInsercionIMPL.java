package ext2.dao;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;
import javax.persistence.TransactionRequiredException;

import ext2.modelo.beans.BeanError;
import ext2.modelo.dao.entity.Artista;
import ext2.dao.BeanDaoConexionIML;


public class BeanDaoInsercionIMPL extends BeanDaoConexionIML implements BeanDaoInsercion{
	
	public BeanDaoInsercionIMPL(String unidadPersistencia) {
		super(unidadPersistencia);
	}

	@Override
	public void insertaArtista(Artista artista) throws Exception, EntityExistsException, IllegalArgumentException,
			TransactionRequiredException, RollbackException, BeanError {
		boolean conexionNula = false;
		if (em == null) {
			getConexion();
			conexionNula = true;
		}
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(artista);
			tx.commit();
		} finally {
			if (tx.isActive()){
				tx.rollback();
			}
			if (conexionNula) {
				close();
			}
		}	
		
	}

}
