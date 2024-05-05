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
import modelo.Libreria;

public class AccesoLibreria {
	private static List<Libreria> librerias = new ArrayList<>();
	private static final String rutacv = "data/ArchivosSeparados/Librerías.csv";
	

	public static void leerDatosCV() throws IOException {
		try {
			BufferedReader lector = new BufferedReader(new FileReader(rutacv));
			String linea = lector.readLine();
			Libreria libreria;
			librerias.clear();
			while ((linea=lector.readLine()) != null) {
				String [] vector = linea.split(",");
				String codigoSr = vector[0];
				String direccion = vector[1];
				libreria = new Libreria(Integer.parseInt(codigoSr), direccion);
				librerias.add(libreria);
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
			String sentenciaSQL = "INSERT INTO Libreria VALUES (?, ?)";
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL);
			for(Libreria libreira : librerias) {
			sentenciaPreparada.setInt(1, libreira.getCodigo());
			sentenciaPreparada.setString(2, libreira.getDireccion());
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
		for(Libreria lib : librerias) {
			System.out.println(lib.toString());
		}
	}
	}

