package es.florida.MP3T1;

import java.io.File;

public class Bibliteca {

	public static void main(String[] args) {
		
		View view = new View();
		Model model = new Model();
		Controller controller = new Controller(view,model);
		
		model.generateListAtXML(new File("libros.xml"));
		view.setVisible(true);

		
		
	}

}
