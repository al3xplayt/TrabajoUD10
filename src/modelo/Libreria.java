package modelo;

public class Libreria {
	private int codigo;
	private String direccion;
	public Libreria(int codigo, String direccion) {
		super();
		this.codigo = codigo;
		this.direccion = direccion;
	}
	
	public Libreria(String direccion) {
		super();
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Libreria [codigo=" + codigo + ", direccion=" + direccion + "]";
	}
	public int getCodigo() {
		return codigo;
	}
	public String getDireccion() {
		return direccion;
	}
	
}
