package introduccionDatos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.ConfigBD;
import modelo.Libro;


public class AccesoLibro {
	private static List<Libro> libros = new ArrayList<>();
	private static final String rutacv = "data/ArchivosSeparados/Libros.csv";



	public static void leerDatosCV() throws IOException {
		try {
			BufferedReader lector = new BufferedReader(new FileReader(rutacv));
			String linea = lector.readLine();
			Libro libro;
			libros.clear();
			while ((linea=lector.readLine()) != null) {
	            linea = linea.replaceAll("\"+|\"+$|€", ""); // Eliminar comillas dobles
	            String[] vector = linea.split(",");
	            String codigoSr = vector[0];
	            String titulo = vector[1];
	            String autor = vector[2];
	            String precioEntero = vector[3];
	            String precioDecimal = vector[4];
	            String precioFinal = precioEntero+"."+precioDecimal;
				libro = new Libro(Integer.parseInt(codigoSr), titulo, autor, Double.parseDouble(precioFinal));
				libros.add(libro);
			}
			lector.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void cargarDatosBD() throws ClassNotFoundException, SQLException {
		Connection conexion = null;
		try {
			conexion = ConfigBD.conectarseABD();
			String sentenciaSQL = "INSERT INTO Libro VALUES (?, ?, ? , ?)";
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL);
			for(Libro libro : libros) {
			sentenciaPreparada.setInt(1, libro.getCodigo());
			sentenciaPreparada.setString(2, libro.getTitulo());
			sentenciaPreparada.setString(3, libro.getAutor());
			sentenciaPreparada.setDouble(4, libro.getPrecio());
			sentenciaPreparada.executeUpdate();
			}
		} finally {
			ConfigBD.desconectar(conexion);
		}
	}
	public static int consultarCodLibreriraPorDireccion (String direccion) throws ClassNotFoundException, SQLException {
		Connection conexion = null;
		try {
			conexion = ConfigBD.conectarseABD();
			String sentenciaSQL = "SELECT Codigo FROM Libreria WHERE Direccion = ? ";
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL);
			sentenciaPreparada.setString(1, direccion);
			ResultSet resultados = sentenciaPreparada.executeQuery();
			int codigo = 0;
			if(resultados.next()) {
				codigo = resultados.getInt("Codigo");
			}
			return codigo;
		}finally {
				ConfigBD.desconectar(conexion);
			}
		}
	public static void EscribirDatosCV() {
		for(Libro lib : libros) {
			System.out.println(lib.toString());
		}
	}
}
