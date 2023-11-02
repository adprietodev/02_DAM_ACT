package es.florida.ae02;

public class App {

	public static void main(String[] args)  {
		
		
		View view = new View();
		Model model = new Model();
		Controller controller =  new Controller(view, model);
		
		view.setVisible(true);
		
		model.getDataXMLCon("client.xml");
		model.connectDB();
		
		model.checkState(view);
		
	}

}
