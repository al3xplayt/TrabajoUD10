package modelo;

public class Empleado {
	private int codigo, codigoLibreria;
	private String nombre, dni;
	
	public Empleado(int codigo, int codigoLibreria, String nombre, String dni) {
		super();
		this.codigo = codigo;
		this.codigoLibreria = codigoLibreria;
		this.nombre = nombre;
		this.dni = dni;
	}

	@Override
	public String toString() {
		return "Empleado [codigo=" + codigo + ", codigoLibreria=" + codigoLibreria + ", nombre=" + nombre + ", dni="
				+ dni + "]";
	}

	public Empleado(int codigoLibreria, String nombre, String dni) {
		super();
		this.codigoLibreria = codigoLibreria;
		this.nombre = nombre;
		this.dni = dni;
	}

	public int getCodigo() {
		return codigo;
	}

	public int getCodigoLibreria() {
		return codigoLibreria;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDni() {
		return dni;
	}
	
}
