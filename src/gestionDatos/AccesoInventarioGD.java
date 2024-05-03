package gestionDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.ConfigBD;

public class AccesoInventarioGD {
	public static boolean HayReferenciasCruzadas(int codigoLibreria)
			throws SQLException, ClassNotFoundException {
		Connection conexion = null;
		try {
			conexion = ConfigBD.conectarseABD();

			String codigoSQL = "SELECT * FROM Inventario WHERE codigo_libreria = ?";
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
	public static boolean HayReferenciasCruzadasLibro(int codigoLibro)
			throws SQLException, ClassNotFoundException {
		Connection conexion = null;
		try {
			conexion = ConfigBD.conectarseABD();

			String codigoSQL = "SELECT * FROM Inventario WHERE Codigo_libro = ?";
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(codigoSQL);

			sentenciaPreparada.setInt(1, codigoLibro);
			ResultSet resultados = sentenciaPreparada.executeQuery();
			if (resultados.next())
				return true;
			else
				return false;
		} finally {
			ConfigBD.desconectar(conexion);
		}
	}
	public static int cantidadTotalLibros (int codigoLibro) throws ClassNotFoundException, SQLException {
		Connection conexion =null;
		int totalLibros = 0;
		try {
			conexion = ConfigBD.conectarseABD();
			String sentenciaSQL = "SELECT Codigo_Libro,sum(Cantidad) "
					+ "as total_Libros from Inventario where Codigo_Libro = ? GROUP BY Codigo_Libro";
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL);
			sentenciaPreparada.setInt(1, codigoLibro);
			ResultSet resultados = sentenciaPreparada.executeQuery();
			if (resultados.next()) {
				totalLibros = resultados.getInt("total_Libros");
			}
			return totalLibros;
		} finally {
			ConfigBD.desconectar(conexion);
		}
	}
	public static boolean modificarCantidadLibro (int codigoLibro, int CodigoLibreria, int cantidad) throws ClassNotFoundException, SQLException {
		Connection conexion =null;
		int numfilas = 0;
		try {
			conexion = ConfigBD.conectarseABD();
			String sentenciaSQL = "UPDATE FROM Inventario SET Cantidad = ? "
					+ "WHERE Codigo_libreria = ? "
					+ "AND Codigo_Libro = ?";
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL);
			sentenciaPreparada.setInt(1, cantidad);
			sentenciaPreparada.setInt(2, CodigoLibreria);
			sentenciaPreparada.setInt(3, codigoLibro);
			numfilas = sentenciaPreparada.executeUpdate();
			return numfilas == 1;
		} finally {
			ConfigBD.desconectar(conexion);
		}
	}
	public static boolean AñadirLibroAInventario (int codigoLibro, int CodigoLibreria, int cantidad) throws ClassNotFoundException, SQLException {
		Connection conexion =null;
		int numfilas = 0;
		try {
			conexion = ConfigBD.conectarseABD();
			String sentenciaSQL = "INSERT INTO Inventario values (?,?,?)";
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL);
			sentenciaPreparada.setInt(1, CodigoLibreria);
			sentenciaPreparada.setInt(2, codigoLibro);
			sentenciaPreparada.setInt(3, cantidad);
			numfilas = sentenciaPreparada.executeUpdate();
			return numfilas == 1;
		} finally {
			ConfigBD.desconectar(conexion);
		}
	}
	public static boolean ExisteLaReferenciaCruzada (int codigoLibro, int CodigoLibreria) throws ClassNotFoundException, SQLException {
		Connection conexion =null;
		int numfilas = 0;
		try {
			conexion = ConfigBD.conectarseABD();
			String sentenciaSQL = "SELECT * FROM Invetario WHERE Codigo_Libreria = ? AND Codigo_Libro = ? ";
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL);
			sentenciaPreparada.setInt(1, CodigoLibreria);
			sentenciaPreparada.setInt(2, codigoLibro);
			ResultSet resultados = sentenciaPreparada.executeQuery();
			if (resultados.next()) {
				return true;
			} else {
				return false;
			}
			
		} finally {
			ConfigBD.desconectar(conexion);
		}
	}
}
