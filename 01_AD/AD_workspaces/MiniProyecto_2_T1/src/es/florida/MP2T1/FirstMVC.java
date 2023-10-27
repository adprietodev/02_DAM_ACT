package es.florida.MP2T1;


public class FirstMVC {

	public static void main(String[] args) {
		View view = new View();
		Model model = new Model();
		Controller controller = new Controller(view, model);
		
		controller.iniView();
		view.setVisible(true);
		
	}

}
