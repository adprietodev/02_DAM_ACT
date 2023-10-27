package es.florida.MP3T1;

public class Libro {

	private int id;
	private String title;
	private String autor;
	private String yearPub;
	private String publisher;
	private int numPages;
	
	public Libro(int id, String title, String autor, String yearPub, String publisher, int numPages) {
		
		this.setId(id);
		this.setTitle(title);
		this.setAutor(autor);
		this.setYearPub(yearPub);
		this.setPublisher(publisher);
		this.setNumPages(numPages);
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getYearPub() {
		return yearPub;
	}

	public void setYearPub(String yearPub) {
		this.yearPub = yearPub;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getNumPages() {
		return numPages;
	}

	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}
	
	
}
