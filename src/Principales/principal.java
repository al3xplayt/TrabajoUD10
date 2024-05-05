package Principales;

import entrada.Teclado;
import gestionDatos.PrincipalGD;
import introduccionDatos.PrincipalID;

public class principal {

	public static void main(String[] args) {
		int opcion = 0;
		do {
			menu();
			opcion = Teclado.leerEntero("¿Opcion? ");
			switch (opcion) {
			case 0:
				break;
			case 2:
				PrincipalGD.principalGD();
				break;
			case 1:
				PrincipalID.principalIDatos();
				break;
			}
		} while (opcion != 0);
		
	}
public static void menu() {
	System.out.println("0) Salir");
	System.out.println("1) Cargar datos");
	System.out.println("2) Gestionar datos");
}
}
