package es.florida.t3;


public class Principal {

	public static void main(String[] args) {
		
		Principal p = new Principal();
		
//		Thread hilo01 = new Thread(new MostrarASCII(1));
//		Thread hilo02 = new Thread(new MostrarASCII(2));
//		
//		hilo01.start();
//		hilo02.start();
		
//		Thread cH01 = new Thread(new Contador("Contador Numero 1",3,32));
//		Thread cH02 = new Thread(new Contador("Contador Numero 2",1,60));
//		Thread cH03 = new Thread(new Contador("Contador Numero 3",0,9));
//		Thread cH04 = new Thread(new Contador("Contador Numero 4",40,50));
//		Thread cH05 = new Thread(new Contador("Contador Numero 5",500,555));
//		
//		cH01.start();
//		cH02.start();
//		cH03.start();
//		cH04.start();
//		cH05.start();
		
		
		
		Thread caracolHilo01 = new Thread(new Caracol(0.5,"Adrián"));
		Thread caracolHilo02 = new Thread(new Caracol(0.5,"Raquel"));
		Thread caracolHilo03 = new Thread(new Caracol(0.5,"Toni"));
		Thread caracolHilo04 = new Thread(new Caracol(0.5,"German"));
		Thread caracolHilo05 = new Thread(new Caracol(0.5,"Aitor"));
		
		caracolHilo01.setPriority(Thread.MAX_PRIORITY);
		caracolHilo02.setPriority(Thread.NORM_PRIORITY);
		caracolHilo03.setPriority(Thread.NORM_PRIORITY);
		caracolHilo04.setPriority(Thread.MIN_PRIORITY);
		caracolHilo05.setPriority(Thread.MAX_PRIORITY);
		
		
		caracolHilo01.start();
		caracolHilo02.start();
		caracolHilo03.start();
		caracolHilo04.start();
		caracolHilo05.start();

	}
	
	private Double speedRandom() {
		Double randomSpeed = Math.random() * 0.9;
        String formattedValue = String.format("%.2f", randomSpeed).replace(",", ".");
        randomSpeed = Double.parseDouble(formattedValue);
        return randomSpeed;
	}

}
