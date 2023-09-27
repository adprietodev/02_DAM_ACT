package es.florida.t1ejercicios;

public class ej35 {

	public static void main(String[] args) {
		
		int[] numbers = {2,4,54,22,65,23,77,32,65,24,121,566,99,93,23};
		
		System.out.println("El numero mas grande de la array es: "+maxNum(numbers));

	}
	
	public static int maxNum(int[] num) {
		int max=0;
		for(int i=0; i<num.length; i++) {
			if(num[i]>max) max=num[i];
		}
		
		return max;
	}

}
