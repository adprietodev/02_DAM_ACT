package es.florida.t2;

public class DataNeo {

	private String name;
	private Double posNeo;
	private Double speedNeo;
	private Boolean state;
	private String mess;
	private Integer id;
	private Double result;
	
	public DataNeo(String name, Double posNeo, Double speedNeo, Boolean state, String mess, Integer id, Double result) {
		this.name = name;
		this.posNeo = posNeo;
		this.speedNeo = speedNeo;
		this.state = state;
		this.mess = mess;
		this.id = id;
		this.result = result;
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
	
	public Boolean getState() {
		return this.state;
			
	}
	
	public void setStatus (Boolean value) {
		state = value;
	}
	
	public String getMess() {
		return this.mess;
	}
	
	public void setMess(String value) {
		mess = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public Double getResult() {
		return this.result;
	}
	
	public void setResult(Double value) {
		result = value;
	}
}
