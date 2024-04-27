package modelo;

public class Libro {
	private int Codigo;
	private String Titulo, Autor;
	private double Precio;
	public Libro(int codigo, String titulo, String autor, double precio) {
		super();
		Codigo = codigo;
		Titulo = titulo;
		Autor = autor;
		Precio = precio;
	}
	public Libro(int codigo, String titulo, double precio) {
		super();
		Codigo = codigo;
		Titulo = titulo;
		Autor = "Anonimo";
		Precio = precio;
	}
	@Override
	public String toString() {
		return "Libro [Codigo=" + Codigo + ", Titulo=" + Titulo + ", Autor=" + Autor + ", Precio=" + Precio + "]";
	}
	public int getCodigo() {
		return Codigo;
	}
	public String getTitulo() {
		return Titulo;
	}
	public String getAutor() {
		return Autor;
	}
	public double getPrecio() {
		return Precio;
	}
	
	
}
