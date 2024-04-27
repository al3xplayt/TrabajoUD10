package introduccionDatos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import modelo.Libreria;

public class AccesoLibreria {
	private static List<Libreria> librerias = new ArrayList<>();
	private static final String rutacv = "data/ArchivosSeparados/Librerías.csv";

	public static void leerDatosCV() {
		try {
			BufferedReader lector = new BufferedReader (new FileReader(rutacv));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void cargarDatosBD() {
		
	}
}
