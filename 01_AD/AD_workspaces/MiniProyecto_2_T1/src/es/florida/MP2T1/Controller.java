package es.florida.MP2T1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

public class Controller {

	//Clase que incorpora los gestores de eventos(Listeners) a cada elemento de la vista.
	
	private View view;
	private Model model;
	
	//Constructor recibe instnacias de la vista y el modelo
	public Controller(View view, Model model) {
		this.model = model;
		this.view = view;
		initEventHandlers();
		
		
	}
	
	public void iniView() {
		
		view.setTextInfoDoc(model.printInfoDoc());
		
	}
	
	public void initEventHandlers() {
		view.btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(model.searchMatch(view.getSearchField()) == 0 ) {
					JOptionPane.showMessageDialog(null, "Si lo dejas vacío, con un simbolo o mas de 1 palabra para comparar, por favor inserta una sola palabra.", "ACTION BUTTON SEARCH",JOptionPane.INFORMATION_MESSAGE);
				} else {
					String text = "";
					
					for(String lineas : model.readDocument(new File("ET1.txt"))) {
						text += lineas+"\n";
					}
					
					JOptionPane.showMessageDialog(null, "La cantidad de coincidencias con la palabra "+view.getSearchField()+" seleccionada es: "+model.searchMatch(view.getSearchField()), "ACTION BUTTON SEARCH",JOptionPane.INFORMATION_MESSAGE);
				}
				
				
			}
		});
		
		view.btnReplace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(model.searchMatch(view.getSearchField()) <= 0) {
					JOptionPane.showMessageDialog(null, "Hay 0 coincidencias no es posible remplazar una palabra que no esta en el texto original.", "ACTION BUTTON SEARCH",JOptionPane.INFORMATION_MESSAGE);
				} else {
					if(model.checkSimbol(view.getSearchField()) || model.checkSimbol(view.getReplaceField())) {
						JOptionPane.showMessageDialog(null, "Has puesto algún simbolo, numero o dejado vacio en el apartado, por favor rellenalo de una palabra valida", "ACTION BUTTON SEARCH",JOptionPane.INFORMATION_MESSAGE);
					}else {
						model.writeDocument(new File("ET1.txt"), model.remplaceWord(view.getSearchField(), view.getReplaceField()));;
					}
				}
				
				view.setTextReplace(model.printInfoDoc());
				
			}
		});
	}
	

}
