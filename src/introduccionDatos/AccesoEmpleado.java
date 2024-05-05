package introduccionDatos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.ConfigBD;
import modelo.Empleado;


public class AccesoEmpleado {
	private static List<Empleado> empleados = new ArrayList<>();
	private static final String rutacv = "data/ArchivosSeparados/Empleados.csv";

	public static void leerDatosCV() throws IOException, ClassNotFoundException, SQLException {

		try {
			BufferedReader lector = new BufferedReader(new FileReader(rutacv));
			String linea = lector.readLine();
			Empleado emple;
			empleados.clear();
			while ((linea = lector.readLine()) != null) {
				String[] vector = linea.split(",");
				String codigoSr = vector[0];
				String nombre = vector[1];
				String dni = vector [2];
				String direccion = vector[3];
				int codigoLibreria = AccesoLibreria.consultarCodLibreriraPorDireccion(direccion);
				emple = new Empleado(Integer.parseInt(codigoSr),codigoLibreria, nombre, dni);
				empleados.add(emple);
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
			String sentenciaSQL = "INSERT INTO Empleado VALUES (?, ?, ?, ?)";
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL);
			for (Empleado emple : empleados) {
				sentenciaPreparada.setInt(1, emple.getCodigo());
				sentenciaPreparada.setString(2, emple.getNombre());
				sentenciaPreparada.setString(3, emple.getDni());
				sentenciaPreparada.setInt(4, emple.getCodigoLibreria());
				sentenciaPreparada.executeUpdate();
			}
		} finally {
			ConfigBD.desconectar(conexion);
		}
	}
	public static void EscribirDatosCV() {
		for(Empleado emple : empleados) {
			System.out.println(emple.toString());
		}
	}
}
