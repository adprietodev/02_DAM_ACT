package es.florida.ej_t3;

public class Cancion {

	private String name;
	private String author;
	private int year;
	

	private String format;
	
	public Cancion (String name, String author, int year, String format) {
		this.name = name;
		this.author = author;
		this.year = year;
		this.format = format;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
