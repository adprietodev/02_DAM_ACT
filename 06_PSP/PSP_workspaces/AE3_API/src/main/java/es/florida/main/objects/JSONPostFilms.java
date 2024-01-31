package es.florida.main.objects;

public class JSONPostFilms {

	private String user;
	private String id;
	private String review;
	private String title;

	public JSONPostFilms() {
	}

	// Para nueva reseña
	public JSONPostFilms(String user, String id, String review) {
		this.user = user;
		this.id = id;
		this.review = review;
	}

	// Para nuevaa peli
	public JSONPostFilms(String user, String title) {
		this.user = user;
		this.title = title;
	}

	// Para nuevo usuario
	public JSONPostFilms(String user) {
		this.user = user;
	}

	public String getUser() {
		return user;
	}

	public String getTitle() {
		return title;
	}

	public String getId() {
		return id;
	}

	public String getReview() {
		return review;
	}

}
