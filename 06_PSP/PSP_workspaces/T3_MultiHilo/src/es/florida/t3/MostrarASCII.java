package es.florida.t3;

public class MostrarASCII implements Runnable{

	int tipo;
	
	public MostrarASCII(int tipo) {
		this.tipo = tipo;
	}

	@Override
	public void run() {
		for(int i = 32; i < 255; i++) {
			if(tipo == 1 && (i%2 == 1)) { //Impares
				System.out.println("ASCII impar: "+(char) i+" ");
			}
			if(tipo == 2 && (i%2 == 0)) {
				System.out.println("ASCII par: "+(char) i+" ");
			}
		}
	}

}
