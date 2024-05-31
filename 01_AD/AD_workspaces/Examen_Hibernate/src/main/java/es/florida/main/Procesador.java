package es.florida.main;

public class Procesador {
	
	private int id;
	private String modelo;
	private int precio;
	private int precioOferta;
	private String disponibilidad;
	
	public Procesador() {};
	
	public Procesador(int id, String modelo, int precio, int precioOferta, String disponiblidad) {
		
		this.id = id;
		this.modelo = modelo;
		this.precio = precio;
		this.precioOferta = precioOferta;
		this.disponibilidad = disponiblidad;
		
	};
	
	public Procesador(String modelo, int precio, int precioOferta, String disponiblidad) {
		this.modelo = modelo;
		this.precio = precio;
		this.precioOferta = precioOferta;
		this.disponibilidad = disponiblidad;
		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * @return the precio
	 */
	public int getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(int precio) {
		this.precio = precio;
	}

	/**
	 * @return the precioOferta
	 */
	public int getPrecioOferta() {
		return precioOferta;
	}

	/**
	 * @param precioOferta the precioOferta to set
	 */
	public void setPrecioOferta(int precioOferta) {
		this.precioOferta = precioOferta;
	}

	/**
	 * @return the disponibilidad
	 */
	public String getDisponibilidad() {
		return disponibilidad;
	}

	/**
	 * @param disponibilidad the disponibilidad to set
	 */
	public void setDisponibilidad(String disponibilidad) {
		this.disponibilidad = disponibilidad;
	};
	
	

}
