package es.florida.model;

public class Record implements Comparable<Record>{

	private String user;
	private int dificult;
	private String timestamp;
	private int duration;
	
	public Record() {
		
	}
	
	public Record(String user, int dificult, String timestamp, int duration) {
		this.user = user;
		this.dificult = dificult;
		this.timestamp = timestamp;
		this.duration = duration;
	}
	
	/**
	 * Metodo para comparar la duración y ordenarlo.
	 */
	@Override
	public int compareTo(Record otherRecord) {
		return Integer.compare(this.duration, otherRecord.duration);
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @return the dificult
	 */
	public int getDificult() {
		return dificult;
	}

	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}
	
	
	
}
