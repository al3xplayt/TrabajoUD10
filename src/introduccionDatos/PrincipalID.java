package introduccionDatos;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import entrada.Teclado;
import modelo.Empleado;
import modelo.Inventario;
import modelo.Libreria;
import modelo.Libro;

public class PrincipalID {
	// Orden de insercion
//	Primero - Libreria
//	Segundo - Empleados
//	Tercero - Libro
//	Cuarto - Inventario
	public static void principalIDatos() {
		int opcion=0;
		do {
			try {
				menu();
				opcion = Teclado.leerEntero("¿Opcion? ");
				switch(opcion) {
				case 0: 
					break;
				case 1:
					AccesoLibreria.leerDatosCV();
					AccesoLibreria.EscribirDatosCV();
					break;
				case 2:
					AccesoLibreria.cargarDatosBD();
					System.out.println("Se ha cargado correctamente");
					break;
				case 3:
					AccesoEmpleado.leerDatosCV();
					AccesoEmpleado.EscribirDatosCV();
					break;
				case 4:
					AccesoEmpleado.cargarDatosBD();
					System.out.println("Se ha cargado correctamente");
					break;
				case 5: 
					AccesoLibro.leerDatosCV();
					AccesoLibro.EscribirDatosCV();
					break;
				case 6:
					AccesoLibro.cargarDatosBD();
					System.out.println("Se ha cargado correctamente");
					break;
				case 7:
					AccesoInventario.leerDatosCV();
					AccesoInventario.EscribirDatosCV();
					break;
				case 8:
					AccesoInventario.CargarDAtosBD();
					System.out.println("Se ha cargado correctamente");
					break;
				default:
					System.out.println("Opcion no valida");
				}
			} catch (IOException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

		} while (opcion != 0);

	}
	public static void menu() {
		System.out.println("0) Salir");
		System.out.println("1) Leer datos Libreria");
		System.out.println("2) Cargar datos Libreria");
		System.out.println("3) Leer datos Empleados");
		System.out.println("4) Cargar datos Empleados");
		System.out.println("5) Leer datos Libros");
		System.out.println("6) Cargar datos Libros");
		System.out.println("7) Leer datos Inventario");
		System.out.println("8) Cargar datos Inventario");
	}
}
