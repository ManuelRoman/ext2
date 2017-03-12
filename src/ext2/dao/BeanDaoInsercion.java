package ext2.dao;

import java.util.ArrayList;

import javax.persistence.EntityExistsException;
import javax.persistence.RollbackException;
import javax.persistence.TransactionRequiredException;

import ext2.modelo.beans.BeanError;
import ext2.modelo.dao.entity.*;

/**
 * Encapsula los procesos de inserción y actualización con la base de datos.
 */
public interface BeanDaoInsercion {
	
	public void insertaArtista(Artista artista) throws Exception, EntityExistsException, IllegalArgumentException,
	TransactionRequiredException, RollbackException, BeanError;
	
}
