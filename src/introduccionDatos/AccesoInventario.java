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
import modelo.Inventario;

public class AccesoInventario {
	private static List<Inventario> existencias = new ArrayList<>();
	private static final String rutacv = "data/ArchivosSeparados/Inventario.csv";

	public static void leerDatosCV() throws IOException {
		try {
			BufferedReader lector = new BufferedReader(new FileReader(rutacv));
			String linea = lector.readLine();
			Inventario inventario;
			existencias.clear();
			while ((linea=lector.readLine()) != null) {
				String [] vector = linea.split(",");
				String codigoLibro = vector[0];
				String codigoLibreria = vector[1];
				String cantidad = vector[2];
				inventario = new Inventario(Integer.parseInt(codigoLibreria), Integer.parseInt(codigoLibro), Integer.parseInt(cantidad));
				existencias.add(inventario);
			}
			lector.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static boolean existeReferenciaCruzada(int codLibreria, int codLibro) throws ClassNotFoundException, SQLException {
		Connection conexion = null;
		try {
			conexion = ConfigBD.conectarseABD();
			String sentenciaSQL = "SELECT * FROM Inventario WHERE codigo_libreria = ? AND Codigo_Libro = ?";
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL);
			sentenciaPreparada.setInt(1,  codLibreria);
			sentenciaPreparada.setInt(2, codLibro);
			ResultSet resultados = sentenciaPreparada.executeQuery();
			if(resultados.next()) {
				return true;
			} else {
				return false;
			}
		} finally {
			ConfigBD.desconectar(conexion);
		}
	}
	public static void insertarDatosENElinventarioBD(Inventario inventario) throws ClassNotFoundException, SQLException {
		Connection conexion = null;
		try {
			conexion = ConfigBD.conectarseABD();
			String sentenciaSQL = "INSERT INTO Inventario VALUES (?, ?, ?)";
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL);
			sentenciaPreparada.setInt(1, inventario.getCodigoLibreria());
			sentenciaPreparada.setInt(2, inventario.getCodigoLibro());
			sentenciaPreparada.setInt(3, inventario.getCantidad());
			sentenciaPreparada.executeUpdate();
		} finally {
			ConfigBD.desconectar(conexion);
		}
	}
	public static void ActualizarDatosDelInventarioBD(Inventario inventario) throws ClassNotFoundException, SQLException {
		Connection conexion = null;
		try {
			conexion = ConfigBD.conectarseABD();
			String sentenciaSQL = "UPDATE Inventario SET Cantidad = ? WHERE codigo_libreria = ? AND Codigo_Libro = ?";
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL);
			sentenciaPreparada.setInt(1, inventario.getCantidad());
			sentenciaPreparada.setInt(2, inventario.getCodigoLibreria());
			sentenciaPreparada.setInt(3, inventario.getCodigoLibro());
			sentenciaPreparada.executeUpdate();
		} finally {
			ConfigBD.desconectar(conexion);
		}
	}
	public static void CargarDAtosBD() throws ClassNotFoundException, SQLException {
		for(Inventario inventario: existencias) {
			if(existeReferenciaCruzada(inventario.getCodigoLibreria(), inventario.getCodigoLibro())) {
				ActualizarDatosDelInventarioBD(inventario);
			} else {
				insertarDatosENElinventarioBD(inventario);
			}
		}
	}
	public static void EscribirDatosCV() {
		for(Inventario inv : existencias) {
			System.out.println(inv.toString());
		}
	}

}
