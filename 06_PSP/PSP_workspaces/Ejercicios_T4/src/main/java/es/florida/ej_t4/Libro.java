package es.florida.ej_t4;

import java.io.Serializable;

public class Libro implements Serializable{

	private String titulo;
	private String autor;
	
	public Libro(String titulo, String autor) {
		this.titulo = titulo;
		this.autor = autor;
	}
	public Libro() {super();}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	
}
