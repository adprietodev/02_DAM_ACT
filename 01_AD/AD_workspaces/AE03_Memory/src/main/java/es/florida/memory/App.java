package es.florida.memory;

import es.florida.view.*;

import java.io.File;

import es.florida.controller.*;
import es.florida.model.*;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		View view = new View();
		Model model = new Model();
		Controller controller = new Controller(view, model);

		view.setVisible(true);
		
		if(new File("./imgs").list().length > 0) {
			model.deleteFiles();
		}
	}

}
