package gestionDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.ConfigBD;
import modelo.Empleado;

public class AccesoEmpleadoGD {
	public static boolean consultarEmpleadosPorCodLibreria(int codigoLibreria)
			throws SQLException, ClassNotFoundException {
		Connection conexion = null;
		try {
			conexion = ConfigBD.conectarseABD();

			String codigoSQL = "SELECT * FROM Empleado WHERE codigo_libreria = ?";
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(codigoSQL);

			sentenciaPreparada.setInt(1, codigoLibreria);
			ResultSet resultados = sentenciaPreparada.executeQuery();
			if (resultados.next())
				return true;
			else
				return false;
		} finally {
			ConfigBD.desconectar(conexion);
		}
	}
	public static boolean existeEMpleadoConDni()
	public static void insertarEmpleado (Empleado empleado) throws ClassNotFoundException, SQLException {
			Connection conexion = null;
			try {
				conexion = ConfigBD.conectarseABD();
				String codigoSQL = "INSERT INTO Empleado (Nombre, Dni, codigo_libreria) values (?,?,?)";
				PreparedStatement sentencia = conexion.prepareStatement(codigoSQL);
				String nombre = empleado.getNombre();
				String dni = empleado.getDni();
				int codLibrerira = empleado.getCodigo();
				sentencia.setString(1, nombre);
				sentencia.setString(2, dni);
				sentencia.setInt(3, codLibrerira);
				int numfilas= sentencia.executeUpdate();
			} finally {
				ConfigBD.desconectar(conexion);
			}
	}
}
