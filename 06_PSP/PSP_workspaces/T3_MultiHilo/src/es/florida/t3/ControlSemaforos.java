package es.florida.t3;

public class ControlSemaforos  {
	
	private int  stateTrafficLight = 1;
	
	public synchronized void turnOnTrafficLight1() {
		try {
			while(stateTrafficLight != 1) {
				wait();
			}
			
			System.out.println("Semaforo 1 esta en verde");
			Thread.sleep(5000);
			
			stateTrafficLight = 2;
			System.err.println("Semaforo 1 esta en rojo");
			
			notify();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void turnOnTrafficLight2() {
		try {
			while(stateTrafficLight != 2) {
				wait();
			}
			
			System.out.println("Semaforo 2 esta en verde");
			Thread.sleep(5000);
			
			stateTrafficLight = 1;
			System.err.println("Semaforo 2 esta en rojo");
			
			notify();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {

		ControlSemaforos cS = new ControlSemaforos();
		
		Thread tL1 = new Thread(new Runnable () {
			public void run (){
				while(true) {
					cS.turnOnTrafficLight1();
				}
			}
		});
		
		Thread tL2 = new Thread(new Runnable () {
			public void run (){
				while(true) {
					cS.turnOnTrafficLight2();
				}
			}
		});
		
		tL1.start();
		tL2.start();
	}
	
}
