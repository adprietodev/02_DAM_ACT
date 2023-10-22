package es.florida.t2;

public class DataNeo {

	private String name;
	private Double posNeo;
	private Double speedNeo;
	
	public DataNeo(String name, Double posNeo, Double speedNeo) {
		this.name = name;
		this.posNeo = posNeo;
		this.speedNeo = speedNeo;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Double getPosNeo() {
		return this.posNeo;
	}
	public Double getSpeedNeo() {
		return this.speedNeo;
	}
}
