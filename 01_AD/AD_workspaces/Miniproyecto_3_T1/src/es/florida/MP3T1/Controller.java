package es.florida.MP3T1;

import java.awt.event.*;

import javax.swing.JOptionPane;

public class Controller {

	View view = new View();
	Model model = new Model();
	ViewCreateBook viewCB = new ViewCreateBook();
	Libro book;
	
	
	public Controller(View view, Model model) {
		this.model = model;
		this.view = view;
		book = new Libro(0,"","","","",0);
		initEventHandlers();
	}
	//view.dispose(); cerrar ventanas
	
	public void initEventHandlers() {
		view.getBtnCreateBook().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				viewCB.setVisible(true);
				viewCB.setTitle("Crear Libro");
			}
		});
		
		view.getBtnShowBooks().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				String text = "";
				for(Libro book : model.getListBooks()) {
					
					text = text+book.getTitle()+"\n";
					
				}
				view.getTitleBooks().setText(text);
			}
		});
		
		view.getBtnShowDetailsBook().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				book = model.showInfoBookSelected(Integer.parseInt(model.selectBook()));
				model.showDialog("ID:"+book.getId()+"\nTitulo: "+book.getTitle()+"\nAutor: "+book.getAutor()+"\nAño de publicación: "+book.getYearPub()+"\nEditorial: "+book.getPublisher()+"\nNumero de paginas: "+book.getNumPages(),"Información Libro",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		view.getBtnUpdateBook().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				book = model.showInfoBookSelected(Integer.parseInt(model.selectBook()));
				viewCB.setVisible(true);
				viewCB.getBtnAddBook().setVisible(false);
				viewCB.setTitle("Actualizar Libro");
				
				String titleField = book.getTitle();
				String autorField = book.getAutor();
				String publisherField = book.getPublisher();
				String yearField = book.getYearPub();
				String numPagesField = String.valueOf(book.getNumPages());
				
				viewCB.getTextTitleField().setText(titleField);
				viewCB.getTextAutorField().setText(autorField);
				viewCB.getTextPublisherField().setText(publisherField);
				viewCB.getYearPField().setText(yearField);
				viewCB.getNumPageField().setText(numPagesField);
			}
		});
		
		view.getBtnCloseBiblio().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				model.writeXmlFile(model.getListBooks());
				System.exit(0);
			}
		});
		
		viewCB.getBtnAddBook().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addBook();
				clearField();
			}
		});
		
		viewCB.getBtnEndCB().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(viewCB.getTitle().equals("Crear Libro")) {
					addBook();
					viewCB.dispose();
				}
				
				if(viewCB.getTitle().equals("Actualizar Libro")) {
					
					String titleField = viewCB.getTextTitleField().getText();
					String autorField = viewCB.getTextAutorField().getText();
					String publisherField = viewCB.getTextPublisherField().getText();
					String yearField = viewCB.getYearPField().getText();
					String numPagesField = viewCB.getNumPageField().getText();
					
					String mess = model.checkStringsField(titleField, autorField, publisherField, yearField, numPagesField);
					
					if(mess.equals("valid")) {
						book.setTitle(viewCB.getTextTitleField().getText());
						book.setAutor(viewCB.getTextAutorField().getText());
						book.setPublisher(viewCB.getTextPublisherField().getText());
						book.setYearPub(viewCB.getYearPField().getText());
						book.setNumPages(Integer.parseInt(viewCB.getNumPageField().getText()));
						model.updateBook(book);
						viewCB.dispose();
					}else {
						model.showDialog(mess, "ERROR AL ACTUALIZAR LIBRO", JOptionPane.WARNING_MESSAGE);
					}
				}
				
			}
		});
		
		viewCB.getBtnCancelCB().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viewCB.dispose();
			}
		});
		
	}
	
	private void addBook() {
		String titleField = viewCB.getTextTitleField().getText();
		String autorField = viewCB.getTextAutorField().getText();
		String publisherField = viewCB.getTextPublisherField().getText();
		String yearField = viewCB.getYearPField().getText();
		String numPagesField = viewCB.getNumPageField().getText();
		
		String mess = model.checkStringsField(titleField, autorField, publisherField, yearField, numPagesField);
		
		if(!mess.equals("valid")){
			model.showDialog(mess, "ERROR CREACIÓN LIBRO", JOptionPane.WARNING_MESSAGE);
		} else {
			if(!model.checkBookExist(titleField)) {
				Libro lastBook = model.createBook(titleField, autorField, publisherField, yearField, Integer.parseInt(numPagesField));
				model.showDialog("El libro se ha creado correctamente con los siguientes datos:\nId: "+lastBook.getId()+"\nTitulo: "+lastBook.getTitle()+"\nAutor: "+lastBook.getAutor()+"\nEditorial: "+lastBook.getPublisher()+"\nAño de publicación: "+lastBook.getYearPub()+"\nPaginas: "+lastBook.getNumPages(), "Libro Creado", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	private void clearField() {
		viewCB.getTextTitleField().setText("");
		viewCB.getTextAutorField().setText("");
		viewCB.getTextPublisherField().setText("");
		viewCB.getYearPField().setText("");
		viewCB.getNumPageField().setText("");
	}
}
