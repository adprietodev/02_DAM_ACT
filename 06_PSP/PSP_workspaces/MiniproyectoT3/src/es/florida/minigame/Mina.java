package es.florida.minigame;

public class Mina {

	private int stock;
	
	public Mina(int stock) {
		this.stock  = stock;
	}

	public int getStock() {
		return stock;
	}

	public void lessMineral() {
		stock--;
	}
	
	
}
