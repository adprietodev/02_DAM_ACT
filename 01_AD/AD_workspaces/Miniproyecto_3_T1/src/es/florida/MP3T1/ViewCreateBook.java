package es.florida.MP3T1;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class ViewCreateBook extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField titleField,autorField,publisherField,yearPField,numPageField;
	private JLabel lblTitleBook,lblAutorBook,lblPublisher,lblYearP,lblNumPages,lblTilteView;
	private JButton  btnAddBook,btnEndCB,btnCancelCB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCreateBook frame = new ViewCreateBook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewCreateBook() {
		
		setResizable(false);
		setTitle("Crear Libro");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 570, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitleBook = new JLabel("Titulo del libro:");
		lblTitleBook.setBounds(6, 49, 145, 16);
		contentPane.add(lblTitleBook);
		
		titleField = new JTextField();
		titleField.setBounds(163, 44, 401, 26);
		contentPane.add(titleField);
		titleField.setColumns(10);
		
		lblAutorBook = new JLabel("Autor del libro:");
		lblAutorBook.setBounds(6, 87, 145, 16);
		contentPane.add(lblAutorBook);
		
		autorField = new JTextField();
		autorField.setColumns(10);
		autorField.setBounds(163, 82, 401, 26);
		contentPane.add(autorField);
		
		lblPublisher = new JLabel("Editorial del libro:");
		lblPublisher.setBounds(6, 125, 145, 16);
		contentPane.add(lblPublisher);
		
		publisherField = new JTextField();
		publisherField.setColumns(10);
		publisherField.setBounds(163, 120, 401, 26);
		contentPane.add(publisherField);
		
		lblYearP = new JLabel("Fecha de publicación:");
		lblYearP.setBounds(6, 163, 145, 16);
		contentPane.add(lblYearP);
		
		yearPField = new JTextField();
		yearPField.setColumns(10);
		yearPField.setBounds(163, 158, 401, 26);
		contentPane.add(yearPField);
		
		lblNumPages = new JLabel("Numero de paginas:");
		lblNumPages.setBounds(6, 201, 145, 16);
		contentPane.add(lblNumPages);
		
		numPageField = new JTextField();
		numPageField.setColumns(10);
		numPageField.setBounds(163, 196, 401, 26);
		contentPane.add(numPageField);
		
		btnAddBook = new JButton("+ AÑADIR");
		btnAddBook.setBounds(190, 234, 184, 55);
		contentPane.add(btnAddBook);
		
		btnEndCB = new JButton("TERMINAR");
		btnEndCB.setBounds(6, 332, 117, 44);
		contentPane.add(btnEndCB);
		
		btnCancelCB = new JButton("CANCELAR");
		btnCancelCB.setBounds(447, 332, 117, 44);
		contentPane.add(btnCancelCB);
		
		lblTilteView = new JLabel("Datos creación libro:");
		lblTilteView.setHorizontalAlignment(SwingConstants.CENTER);
		lblTilteView.setBounds(6, 16, 558, 16);
		contentPane.add(lblTilteView);
	}
	
	public JButton  getBtnAddBook() {
		return btnAddBook;
	}
	
	public JButton getBtnEndCB() {
		return btnEndCB;
	}
	
	public JButton getBtnCancelCB() {
		return btnCancelCB;
	}
	
	public JTextField getTextTitleField() {
		return titleField;
	}
	
	public JTextField getTextAutorField() {
		return autorField;
	}
	
	public JTextField getTextPublisherField() {
		return publisherField;
	}
	
	public JTextField getYearPField() {
		return yearPField;
	}
	
	public JTextField getNumPageField() {
		return numPageField;
	}
	
}
