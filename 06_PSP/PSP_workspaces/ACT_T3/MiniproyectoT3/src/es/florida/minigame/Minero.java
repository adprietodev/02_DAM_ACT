package es.florida.minigame;

public class Minero implements Runnable{

	private int bolsa;
	private int tiempoExtraccion;
	private Mina mina;
	
	public Minero(Mina mina,int bolsa, int tiempoExtraccion){
		this.bolsa = bolsa;
		this.tiempoExtraccion = tiempoExtraccion;
		this.mina = mina;
	}
	
	public  void extraerMineral() {
		synchronized (mina) {
			 try {
                 if (mina.getStock() > 0) {
                     mina.lessMineral();
                     bolsa++;
                 }
             } catch (Exception e) {
                 e.printStackTrace();
             }
        }
		
	}
	
	
	public int getBolsa() {
		return bolsa;
	}

	@Override
	public void run() {
		
		while(mina.getStock() > 0) {
			//System.out.println(mina.getStock());
			extraerMineral();
			
			try {
				Thread.sleep(tiempoExtraccion);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
