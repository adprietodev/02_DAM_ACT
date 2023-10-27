package es.florida.MP3T1;

import java.awt.event.*;

public class Controller {

	View view = new View();
	Model model = new Model();
	
	public Controller(View view, Model model) {
		this.model = model;
		this.view = view;
		initEventHandlers();
	}
	//view.dispose(); cerrar ventanas
	
	public void initEventHandlers() {
		view.getBtnCreateBook().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.out.println("Entra en el clic");
				
				ViewCreateBook viewCB = new ViewCreateBook();
				
				viewCB.setVisible(true);
			}
		});
	}
}
