package es.florida.miniproyectoT4;

import java.io.Serializable;

public class Password implements Serializable{
	
	private String flatText;
	private String encriptText;
	
	public Password() {
		super();
	}
	
	public Password(String flatText, String encriptText) {
		this.flatText = flatText;
		this.encriptText = encriptText;
	}

	public String getFlatText() {
		return flatText;
	}

	public void setFlatText(String flatText) {
		this.flatText = flatText;
	}

	public String getEncriptText() {
		return encriptText;
	}

	public void setEncriptText(String encriptText) {
		this.encriptText = encriptText;
	}
	
}
