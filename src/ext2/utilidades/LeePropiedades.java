package ext2.utilidades;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import ext2.modelo.beans.BeanError;

public abstract class LeePropiedades {
	
	public static Properties getPropiedades(String fichero) {
		Properties propiedades = new Properties();
		FileInputStream entrada = null;
		try {
			entrada = new FileInputStream(fichero);
		} catch (FileNotFoundException fnfe) {
			new BeanError(5,"No se ha encontrado el archivo propiedades");
			fnfe.printStackTrace();
		}
		try {
			propiedades.load(entrada);
		} catch (IOException ioe) {
			new BeanError(6,"Error al leer del archivo propiedades", ioe);
			ioe.printStackTrace();
		}
		return propiedades;
	}

}
