package gestionDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.ConfigBD;
import modelo.Libreria;

public class AccesoLibreliaGD {
	public static Libreria consultarPorCodigo(int codigo) throws ClassNotFoundException, SQLException {
		Connection conexion = null;
		try {
			Libreria libreriaADev = null;
			conexion = ConfigBD.conectarseABD();
			String sentenciaSQL = "SELECT * FROM libreria WHERE CODIGO = " + codigo;
			Statement sentencia = conexion.createStatement();
			ResultSet resultado = sentencia.executeQuery(sentenciaSQL);
			if (resultado.next()) {
				String direccion = resultado.getString("direccion");
				libreriaADev = new Libreria(codigo, direccion);
			}
			return libreriaADev;
		} finally {
			ConfigBD.desconectar(conexion);
		}
	}
	public static Libreria consultarPorCodigoV2(int codigo) throws ClassNotFoundException, SQLException {
		Connection conexion = null;
		try {
			Libreria libreriaADev = null;
			conexion = ConfigBD.conectarseABD();
			String sentenciaSQL = "SELECT * FROM libreria WHERE CODIGO = ?";
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
			sentencia.setInt(1, codigo);
			
			ResultSet resultado = sentencia.executeQuery();
			if (resultado.next()) {
				String direccion = resultado.getString("direccion");
				libreriaADev = new Libreria(codigo, direccion);
			}
			return libreriaADev;
		} finally {
			ConfigBD.desconectar(conexion);
		}
	}
	
	public static void introducirLibreia (Libreria libreria) throws ClassNotFoundException, SQLException {
		Connection conexion = null;
		try {
			Libreria libreriaADev = null;
			conexion = ConfigBD.conectarseABD();
			String direccionLibreria = libreria.getDireccion();
			String sentenciaSQL = "INSERT INTO Libreria (Direccion) VALUES  (?)";
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
			sentencia.setString(1, direccionLibreria);
			int resultado = sentencia.executeUpdate();
		} finally {
			ConfigBD.desconectar(conexion);
		}
	}
	public static boolean eliminarLibreriaPorCodigo(int cod) throws ClassNotFoundException, SQLException {
		Connection conexion = null;
		try {
			conexion = ConfigBD.conectarseABD();
			String codigoSQL = "DELETE FROM Libreria WHERE codigo = ? ";
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(codigoSQL);
			
			sentenciaPreparada.setInt(1, cod);
			int numfilas = sentenciaPreparada.executeUpdate();
			return numfilas == 1;
		} finally {
			ConfigBD.desconectar(conexion);
		}
	}
}
