package es.florida.hibernate;

public class Cancion {

	private int id;
	private String titulo;
	private String artista;
	private int anyo;
	private String formato;
	
	public Cancion() {
		
	}
	
	public Cancion(int id, String titulo, String artista, int anyo, String formato) {
		this.id = id;
		this.titulo = titulo;
		this.artista = artista;
		this.anyo = anyo;
		this.formato = formato;
	}
	
	public Cancion(String titulo, String artista, int anyo, String formato) {
		this.titulo = titulo;
		this.artista = artista;
		this.anyo = anyo;
		this.formato = formato;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public int getAnyo() {
		return anyo;
	}

	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}
	
	
}
