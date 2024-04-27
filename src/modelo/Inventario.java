package modelo;

public class Inventario {
	private int codigoLibro, codigoLibreria, cantidad;

	public Inventario(int codigoLibro, int codigoLibreria, int cantidad) {
		super();
		this.codigoLibro = codigoLibro;
		this.codigoLibreria = codigoLibreria;
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Inventario [codigoLibro=" + codigoLibro + ", codigoLibreria=" + codigoLibreria + ", cantidad="
				+ cantidad + "]";
	}

	public int getCodigoLibro() {
		return codigoLibro;
	}

	public int getCodigoLibreria() {
		return codigoLibreria;
	}

	public int getCantidad() {
		return cantidad;
	}
		
}
