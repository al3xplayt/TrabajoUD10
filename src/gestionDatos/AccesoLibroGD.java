package gestionDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.ConfigBD;
import modelo.Libro;

public class AccesoLibroGD {
	public static Libro consultarPorCodigoLibro(int codigo) throws ClassNotFoundException, SQLException {
		Libro lib = null;
		Connection conexion = null;
		try {
			conexion = ConfigBD.conectarseABD();
			String codigoSQL = "SELECT * FROM Libro WHERE Codigo = ? ";
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(codigoSQL);
			sentenciaPreparada.setInt(1, codigo);
			ResultSet resultados = sentenciaPreparada.executeQuery();
			if (resultados.next()) {
				String titulo = resultados.getString("Titulo");
				String autor = resultados.getString("Autor");
				double precio = resultados.getDouble("Precio");
				lib = new Libro(codigo, titulo, autor, precio);
			}
		} finally {
			ConfigBD.desconectar(conexion);
		}

		return lib;
	}

	public static List<Libro> cosnultarLibroOrdPorAutor() throws ClassNotFoundException, SQLException {
		List<Libro> listaADev = new ArrayList<>();
		Connection conexion = null;
		Libro lib;
		try {
			conexion = ConfigBD.conectarseABD();
			String codigoSQL = "SELECT * FROM Libro ORDER BY Autor";
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(codigoSQL);
			ResultSet resultados = sentenciaPreparada.executeQuery();
			while (resultados.next()) {
				int codigo = resultados.getInt("Codigo");
				String titulo = resultados.getString("Titulo");
				String autor = resultados.getString("Autor");
				double precio = resultados.getDouble("Precio");
				lib = new Libro(codigo, titulo, autor, precio);
				listaADev.add(lib);
			}
		} finally {
			ConfigBD.desconectar(conexion);
		}

		return listaADev;
	}

	public static void escribirLibros(List<Libro> listaAMostrar) {
		if (listaAMostrar.isEmpty()) {
			System.out.println("La lista esta vacía");

		} else {
			String cadena = "";
			for (Libro librito : listaAMostrar) {
				cadena += librito.toString()+"\n";
			}
			System.out.println(cadena);
		}
	}
	public static void insertarLibroSiempre (Libro libroAInsertar) throws ClassNotFoundException, SQLException {
		Connection conexion = null;
		try {
			conexion = ConfigBD.conectarseABD();
			String sentenciaSQL = "INSERT INTO Libro (Titulo, Autor, Precio) VALUES (?,?,?)";
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL);
			sentenciaPreparada.setString(1, libroAInsertar.getTitulo());
			sentenciaPreparada.setString(2, libroAInsertar.getAutor());
			sentenciaPreparada.setDouble(3, libroAInsertar.getPrecio());
			sentenciaPreparada.executeUpdate();
		} finally {
			ConfigBD.desconectar(conexion);
		}
	}
	public static boolean hayLibro(Libro libro) throws SQLException, ClassNotFoundException {
		Connection conexion = null;
		int numFilas = 0;
		try {
			conexion = ConfigBD.conectarseABD();
			String codigoSQL = "SELECT * FROM Libro WHERE Titulo = ? AND Autor = ?";
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(codigoSQL);
			sentenciaPreparada.setString(1, libro.getTitulo());
			sentenciaPreparada.setString(2, libro.getAutor());
			ResultSet resultados = sentenciaPreparada.executeQuery();
			if (resultados.next()) {
				return true;
			}
		} finally {
			ConfigBD.desconectar(conexion);
		}
		return false;
	}
	public static boolean EliminarLibro (int codigoLibro) throws ClassNotFoundException, SQLException {
		Connection conexion = null;
		int numFilas = 0;
		try {
			conexion = ConfigBD.conectarseABD();
			String sentenciaSQL = "DELETE FROM Libro where Codigo = ?";
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL);
			sentenciaPreparada.setInt(1, codigoLibro);
			numFilas = sentenciaPreparada.executeUpdate();
			return numFilas == 1;
		}finally {
			ConfigBD.desconectar(conexion);
		}
	}
}
