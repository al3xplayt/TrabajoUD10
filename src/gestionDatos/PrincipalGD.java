package gestionDatos;

import java.util.List;

import entrada.Teclado;
import modelo.Empleado;
import modelo.Libreria;
import modelo.Libro;

public class PrincipalGD {
	public static void principalGD() {
		String direccion, nombre, dni, autor, titulo;
		double precio = 0;
		int opcion = 0, codLib, codigoEmpleado, codLibro, cantidad;
		boolean datosValidos, autorConocido;
		Empleado emple;
		Libreria libreria;
		Libro libro;
		List<Libro> listaLibros;
		List<Empleado> listaEmple;
		do {
			try {
				menu();
				opcion = Teclado.leerEntero("¿Opción? ");
				switch (opcion) {
				case 0:
					break;
				case 1:
					direccion = Teclado.leerCadena("¿Dirección de la libreria? ");
					libreria = new Libreria(direccion);
					AccesoLibreriaGD.introducirLibreia(libreria);
					break;
				case 2:
					codLib = Teclado.leerNatural("¿Código de la libreria? ");
					datosValidos = true;
					if (AccesoEmpleadoGD.HayEmpleadosEnLaLibreria(codLib)) {
						System.out.println("La libreria tiene empleados");
						datosValidos = false;
					}

					if (AccesoInventarioGD.HayReferenciasCruzadas(codLib)) {
						System.out.println("La libreria contiene libros");
						datosValidos = false;
					}
					if (datosValidos) {
						String mensaje = AccesoLibreriaGD.eliminarLibreriaPorCodigo(codLib)
								? "Se ha logrado eliminar la libreria"
								: "La libreria no existia";
						System.out.println(mensaje);
					}
					break;
				case 3:
					codLib = Teclado.leerNatural("¿Código libreria? ");
					libreria = AccesoLibreriaGD.consultarPorCodigoV2(codLib);
					if (libreria == null) {
						System.out.println("No existe ninguna libreria");
					} else {
						System.out.println(libreria.toString());
					}
					break;
				case 4:
					datosValidos = true;
					nombre = Teclado.leerCadena("Nombre del empleado: ");
					dni = Teclado.leerCadena("Dni del empleado: ");
					if ((emple = AccesoEmpleadoGD.consultarEmpleadoPorDni(dni)) != null) {
						System.out.println("Ya existe un empleado con ese dni");
						datosValidos = false;
						break;
					}
					codLib = Teclado.leerNatural("Codigo de la libreria: ");
					if (AccesoLibreriaGD.consultarPorCodigoV2(codLib) == null) {
						System.out.println("No existe una libreria con ese codigo");
						datosValidos = false;
						break;
					}
					if(datosValidos){
						emple = new Empleado(codLib, nombre, dni);
						AccesoEmpleadoGD.insertarEmpleado(emple);
					}
					break;
				case 5:
					codLib = Teclado.leerNatural("¿Codigo de la libreria? ");
					listaEmple = AccesoEmpleadoGD.consultarEMpleadosPorCodLibreria(codLib);
					if(listaEmple.isEmpty()) {
						System.out.println("No hay ningún empleado");
					} else {
						AccesoEmpleadoGD.escribirListaEmpleado(listaEmple);
					}
					break;
				case 6:
					codigoEmpleado = Teclado.leerNatural("¿Codigo del empleado? ");
					if (AccesoEmpleadoGD.eliminarEmpleadoPorCodigo(codigoEmpleado)) {
						System.out.println("Se ha eliminado correctamente");
					} else {
						System.out.println("El empleado no existe");
					}
					break;
				case 7:
					codLibro = Teclado.leerNatural("¿Código del libro? ");
					libro = AccesoLibroGD.consultarPorCodigoLibro(codLibro);
					String mensaje = libro == null ? "No existe ningun libro con este codigo" : libro.toString();
					System.out.println(mensaje);
					break;
				case 8:
					listaLibros = AccesoLibroGD.cosnultarLibroOrdPorAutor();
					if(listaLibros.isEmpty()) {
						System.out.println("No hay ningún libro");
					} else {
						AccesoLibroGD.escribirLibros(listaLibros);
					}
					break;
				case 9:
					titulo = Teclado.leerCadena("¿Nombre del libro? ");
					precio = Teclado.leerReal("¿Precio libro? ");
					autorConocido = Teclado.leerBooleano("¿Se conoce el autor? (true/false) ");
					if(autorConocido) {
						autor=Teclado.leerCadena("¿Nombre autor? ");
						libro = new Libro(titulo,autor, precio);
					} else {
						libro = new Libro(titulo,precio);
					}
					datosValidos = AccesoLibroGD.hayLibro(libro);
					if (!datosValidos) {
						AccesoLibroGD.insertarLibroSiempre(libro);
						System.out.println("Se ha insertado el libro correctamente");
					} else {
						System.out.println("El libro ya existe");
					}
					break;
				case 10:
					codLibro = Teclado.leerNatural("¿Código del libro? ");
					datosValidos = AccesoInventarioGD.HayReferenciasCruzadasLibro(codLibro);
					if (datosValidos) {
						System.out.println("El libro aparece en el inventario");
						break;
					}
					datosValidos = AccesoLibroGD.EliminarLibro(codLibro);
					mensaje = datosValidos? "Se ha eliminado correctamente" : "El libro con el codigo "+codLibro+" no existe";
					System.out.println(mensaje);
					break;
				case 11:
					codLibro = Teclado.leerNatural("¿Código del libro? ");
					int numLibros = AccesoInventarioGD.cantidadTotalLibros(codLibro);
					System.out.println("Del libro con codigo "+codLibro+" existen un total de "+numLibros+" ejemplares");
					break;
				case 12:
					codLib = Teclado.leerNatural("¿Codigo de la libreria? ");
					codLibro = Teclado.leerNatural("¿Codigo del libro? ");
					cantidad = Teclado.leerNatural("¿Cantidad de libros? ");
					mensaje = AccesoInventarioGD.modificarCantidadLibro(codLibro, codLib, cantidad) ? 
							"Se ha modificado correctamente" : "No se ha podido modificar";
					System.out.println(mensaje);
					break;
				case 13:
					datosValidos = true;
					codLibro = Teclado.leerNatural("¿Codigo del libro? ");
					libro = AccesoLibroGD.consultarPorCodigoLibro(codLibro);
					if (libro == null) {
						System.out.println("El libro no existe");
						datosValidos = false;
					}
					codLib = Teclado.leerNatural("¿Codigo de la libreria? ");
					libreria = AccesoLibreriaGD.consultarPorCodigo(codLib);
					if (libreria == null) {
						System.out.println("La libreria no existe");
						datosValidos = false;
					}
					AccesoInventarioGD.ExisteLaReferenciaCruzada(codLibro, codLib);
					if(!datosValidos) {
						System.out.println("Error");
						break;
					} else {
						cantidad=Teclado.leerNatural("¿Cantidad de libros a añadir? ");
						mensaje = AccesoInventarioGD.AñadirLibroAInventario(codLibro, codLib, cantidad) ? 
								"Se ha añadido correctamente" : "No se ha podido añadir";
						System.out.println(mensaje);
					}
					break;
				case 14:
					System.out.println( AccesoLibreriaGD.consultarLibreriasPorCantidadDeLibros());
					break;
				case 15:
					mensaje= AccesoLibreriaGD.consultarLibreriaPorTotasInvertido();
					System.out.println(mensaje);
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + opcion);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (opcion != 0);
	}

	public static void menu() {
		System.out.println("0) Salir");
		System.out.println("1) Insertar nueva libreria");
		System.out.println("2) Eliminar librería por codigo de librería");
		System.out.println("3) Consultar librerias / por codigo.");
		System.out.println("4) Insertar nuevo empleado");
		System.out.println("5) Consultar empleados de una librería");
		System.out.println("6) Eliminar un empleado por código");
		System.out.println("7) Consultar un libro por código");
		System.out.println("8) Consultar los libros ordenados por autor");
		System.out.println("9) Insertar un libro");
		System.out.println("10) Eliminar un libro por código");
		System.out.println("11) Consultar número total de existencias de un libro");
		System.out.println("12) Modificar la cantidad de libros de una determinada librería por"
				+ " código de libro y de librería.");
		System.out.println("13) Añadir un nuevo libro ya existente a una librería junto a la cantidad"
				+ " de libros.");
		System.out.println("14) Consultar las librerías ordenadas por número de libros de menor"
				+ " número a mayor.");
		System.out.println("15)  Consultar las librerías ordenadas por el dinero que tienen"
				+ " invertido en libros de mayor a menor.");
	}
}
