package gestionDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.ConfigBD;

public class AccesoInventario {
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
}
