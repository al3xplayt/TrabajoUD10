package gestionDatos;

import java.awt.Menu;

import entrada.Teclado;
import introduccionDatos.AccesoLibreria;

public class Principal {
	public static void main(String[] args) {
		String direccion;
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
					datosValidos =true;
					if (AccesoEmpleado.consultarEmpleadosPorCodLibreria(codLib)) {
						System.out.println("La libreria se ha eliminado");
						datosValidos = false;
					}

					if (AccesoInventario.HayReferenciasCruzadas(codLib)) {
						System.out.println("La");
						datosValidos=false;
					}
					if (datosValidos) {
						String mensaje = AccesoLibreliaGD.eliminarLibreriaPorCodigo(codLib)? "Se ha logrado eliminar la libreria":"La libreria no existia";
					}
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

	}
}
