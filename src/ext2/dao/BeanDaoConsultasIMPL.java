package ext2.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.Query;

import ext2.modelo.dao.entity.*;
import ext2.modelo.beans.BeanError;

public class BeanDaoConsultasIMPL extends BeanDaoConexionIML implements BeanDaoConsultas {

	public BeanDaoConsultasIMPL(String unidadPersistencia) {
		super(unidadPersistencia);
	}

	@Override
	public List<Artista> listaTodosArtistas() throws Exception, EntityExistsException, IllegalArgumentException {
		boolean conexionNula = false;
		if (em == null) {
			getConexion();
			conexionNula = true;
		}
		List<Artista> listaArtista = new ArrayList();
		try {
			Query query = em.createNamedQuery(Artista.TODOS);
			listaArtista = query.getResultList();
		} finally {
			if (conexionNula) {
				close();
			}
		}
		return listaArtista;
	}

	@Override
	public List<Artista> listaArtistasDni(String dni)
			throws Exception, EntityExistsException, IllegalArgumentException, BeanError{
		boolean conexionNula = false;
		if (em == null) {
			getConexion();
			conexionNula = true;
		}
		List<Artista> listaArtista = new ArrayList<Artista>();
		try {
			String jpql = "SELECT a FROM Artista a WHERE a.dni = :numero";
			Query query = em.createQuery(jpql);
			query.setParameter("numero", dni);
			listaArtista = query.getResultList();
		} finally {
			if (conexionNula) {
				close();
			}
		}
		return listaArtista;
	}

	@Override
	public List<Artista> listaArtistasCategoria(String categoria)
			throws Exception, EntityExistsException, IllegalArgumentException {
		boolean conexionNula = false;
		if (em == null) {
			getConexion();
			conexionNula = true;
		}
		List<Artista> listaArtista = new ArrayList<Artista>();
		try {
			String sql = "SELECT * FROM artista WHERE tipo =:categoria";
			Query query = em.createNativeQuery(sql);
			query.setParameter("categoria", categoria);
			listaArtista = query.getResultList();
		} finally {
			if (conexionNula) {
				close();
			}
		}
		return listaArtista;
	}

	@Override
	public List<Artista> listaArtistasCategoria2(String categoria)
			throws Exception, EntityExistsException, IllegalArgumentException {
		boolean conexionNula = false;
		if (em == null) {
			getConexion();
			conexionNula = true;
		}
		List<Artista> listaArtista = new ArrayList<Artista>();
		try {
			String jpql = "SELECT a FROM Artista a WHERE tipo = :categoria";
			Query query = em.createQuery(jpql);
			query.setParameter("categoria", categoria);
			listaArtista = query.getResultList();
		} finally {
			if (conexionNula) {
				close();
			}
		}
		return listaArtista;
	}

}
