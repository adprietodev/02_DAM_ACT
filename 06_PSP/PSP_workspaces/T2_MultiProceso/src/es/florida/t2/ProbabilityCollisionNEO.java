package es.florida.t2;

public class ProbabilityCollisionNEO {

	public double calculateProbability(double posNeo, double speedNeo) {
		double posEarth = 1;
		double speedEarth = 100;
		
		for(int i = 0; i < (50*365*24*60*60); i++) {
			posNeo = posNeo + speedNeo*i;
			posEarth = posEarth + speedEarth*i;
		}
		
		return 100 * Math.random() * Math.pow(((posNeo-posEarth)/(posNeo+posEarth)),2);
		
	}
	

}
