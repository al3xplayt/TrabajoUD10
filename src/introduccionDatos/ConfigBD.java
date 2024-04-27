package introduccionDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.sqlite.SQLiteConfig;

public class ConfigBD {
	//Esta clase debe hacer cosas
	private final static String CLASE_DRIVE ="org.sqlite.JDBC";
	private final static String RUTA_BD ="data/LibreriaMartinez.db";
	private final static String URL_CONEXION ="jdbc:sqlite:"+RUTA_BD;
	public static Connection conectarseABD() throws SQLException, ClassNotFoundException {
		Connection conexion = null;
		//conexion
		Class.forName(CLASE_DRIVE);
		SQLiteConfig config = new SQLiteConfig();
		config.enforceForeignKeys(true);
		conexion = DriverManager.getConnection(URL_CONEXION, config.toProperties());
		
		return conexion;
	}
	public static void desconectar(Connection conexion) throws SQLException {
		if (conexion != null && conexion.isClosed()) {
			conexion.close();
		} 
	}
}
