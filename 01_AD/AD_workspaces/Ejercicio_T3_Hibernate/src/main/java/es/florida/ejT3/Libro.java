package es.florida.ejT3;

public class Libro {

	private int idLibros;
	private String titul;
	private String autor;
	private int anyoNacimiento;
	private int anyoPublicacion;
	private String editorial;
	private int numeroDePaginas;

	public Libro() {
	}

	public Libro(int idLibros, String titul, String autor, int anyoNacimiento, int anyoPublicacion, String editorial, int numeroDePaginas) {
		this.idLibros = idLibros;
		this.titul = titul;
		this.autor = autor;
		this.anyoNacimiento = anyoNacimiento;
		this.anyoPublicacion = anyoPublicacion;
		this.editorial = editorial;
		this.numeroDePaginas = numeroDePaginas;

	}

	public Libro(String titul, String autor, int anyoNacimiento, int anyoPublicacion, String editorial, int numeroDePaginas) {
		this.titul = titul;
		this.autor = autor;
		this.anyoNacimiento = anyoNacimiento;
		this.anyoPublicacion = anyoPublicacion;
		this.editorial = editorial;
		this.numeroDePaginas = numeroDePaginas;
	}

	public int getIdLibros() {
		return idLibros;
	}

	public void setIdLibros(int idLibros) {
		this.idLibros = idLibros;
	}

	public String getTitul() {
		return titul;
	}

	public void setTitul(String titul) {
		this.titul = titul;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getAnyoNacimiento() {
		return anyoNacimiento;
	}

	public void setAnyoNacimiento(int anyoNacimiento) {
		this.anyoNacimiento = anyoNacimiento;
	}

	public int getAnyoPublicacion() {
		return anyoPublicacion;
	}

	public void setAnyoPublicacion(int anyoPublicacion) {
		this.anyoPublicacion = anyoPublicacion;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public int getNumeroDePaginas() {
		return numeroDePaginas;
	}

	public void setNumeroDePaginas(int numeroDePaginas) {
		this.numeroDePaginas = numeroDePaginas;
	}

}
