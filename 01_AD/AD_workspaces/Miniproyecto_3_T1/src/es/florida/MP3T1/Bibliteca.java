package es.florida.MP3T1;

public class Bibliteca {

	public static void main(String[] args) {
		
		View view = new View();
		Model model = new Model();
		Controller controller = new Controller(view,model);
		
		view.setVisible(true);

	}

}
