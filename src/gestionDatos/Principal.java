package gestionDatos;

import java.awt.Menu;

import entrada.Teclado;
import introduccionDatos.AccesoLibreria;
import modelo.Empleado;

public class Principal {
	public static void main(String[] args) {
		String direccion, nombre, dni;
		int opcion = 0, codLib;
		boolean datosValidos;
		do {
			try {
				menu();
				opcion = Teclado.leerEntero("¿Opcion?");
				switch (opcion) {
				case 1:

					break;
				case 2:
					codLib = Teclado.leerEntero("¿Cod de la libreria?");
					datosValidos = true;
					if (AccesoEmpleadoGD.consultarEmpleadosPorCodLibreria(codLib)) {
						System.out.println("La libreria se ha eliminado");
						datosValidos = false;
					}

					if (AccesoInventarioGD.HayReferenciasCruzadas(codLib)) {
						System.out.println("La");
						datosValidos = false;
					}
					if (datosValidos) {
						String mensaje = AccesoLibreliaGD.eliminarLibreriaPorCodigo(codLib)
								? "Se ha logrado eliminar la libreria"
								: "La libreria no existia";
					}
					break;
				case 4:
					boolean seguir;
					nombre = Teclado.leerCadena("Nombre del empleado: ");
					dni = Teclado.leerCadena("Dni del empleado: ");
					do {
						codLib = Teclado.leerEntero("Codigo de la libreria");
						seguir = AccesoLibreliaGD.existeLibreria(codLib) ? true : false;
					} while (!seguir);
					Empleado emple = new Empleado(codLib, nombre, dni);
					AccesoEmpleadoGD.insertarEmpleado(emple);
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + opcion);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		} while (opcion != 0);
	}

	public static void menu() {
		System.out.println("0) Salir");
		System.out.println("1) Insetrtar nueva libreria");
		System.out.println("2) Eliminar librería por codigo de librería");
		System.out.println("3) Consultar librerias / por codigo.");
		System.out.println("4) Inserat nuevo empleado");
		System.out.println("5) Cousltar empleados de una librería");
		System.out.println("6) Eliminar un empleado por código");
	}
}
