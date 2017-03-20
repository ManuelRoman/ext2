package ext2.dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import ext2.modelo.beans.BeanError;
import ext2.modelo.dao.entity.*;

public interface BeanDaoConsultas {
	
	public List<Artista> listaTodosArtistas() throws Exception, EntityExistsException, IllegalArgumentException;
	
	public List<Artista> listaArtistasDni(String dni) throws Exception, EntityExistsException, IllegalArgumentException, BeanError;
	
	public List<Artista> listaArtistasCategoria(String Categoria) throws Exception, EntityExistsException, IllegalArgumentException;
	
	public List<Artista> listaArtistasCategoria2(String Categoria) throws Exception, EntityExistsException, IllegalArgumentException;
	
	public ArrayList<String> obtenerDNIs(String dato) throws Exception, EntityExistsException, IllegalArgumentException;
	
}
