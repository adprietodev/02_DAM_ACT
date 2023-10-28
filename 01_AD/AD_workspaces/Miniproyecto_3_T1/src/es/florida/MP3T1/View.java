package es.florida.MP3T1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Button;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JList;
import javax.swing.JComboBox;

public class View extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTextPane titleBooks;
	private JLabel lblBooks;
	private JButton btnCreateBook,btnShowBooks, btnShowDetailsBook, btnUpdateBook, btnDeleteBook,btnCloseBiblio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
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
	public View() {
		initComponents();
	}
	
	public void initComponents() {
		
		setResizable(false);
		setTitle("Biblioteca");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 820, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 59, 396, 517);
		contentPane.add(scrollPane);
		
		titleBooks = new JTextPane();
		scrollPane.setViewportView(titleBooks);
		
		lblBooks = new JLabel("Libros en la biblioteca");
		lblBooks.setHorizontalAlignment(SwingConstants.CENTER);
		lblBooks.setBounds(6, 31, 396, 16);
		contentPane.add(lblBooks);
		
		btnCreateBook = new JButton("3. Crear Libro");
		btnCreateBook.setBounds(420, 247, 381, 48);
		contentPane.add(btnCreateBook);
		
		btnShowBooks = new JButton("1. Mostrar todos los libros");
		btnShowBooks.setBounds(420, 127, 381, 48);
		contentPane.add(btnShowBooks);
		
		btnShowDetailsBook = new JButton("2. Mostar información de un libro");
		btnShowDetailsBook.setBounds(420, 187, 381, 48);
		contentPane.add(btnShowDetailsBook);
		
		btnUpdateBook = new JButton("4. Actualizar libro");
		btnUpdateBook.setBounds(420, 307, 381, 48);
		contentPane.add(btnUpdateBook);
		
		btnDeleteBook = new JButton("5. Eliminar libro");
		btnDeleteBook.setBounds(420, 367, 381, 48);
		contentPane.add(btnDeleteBook);
		
		btnCloseBiblio = new JButton("6. Cerrar biblioteca");
		btnCloseBiblio.setBounds(420, 427, 381, 48);
		contentPane.add(btnCloseBiblio);
		
		
	}
	
	public JTextPane getTitleBooks() {
		return titleBooks;
	}
	
	
	public JButton getBtnCreateBook() {
		return btnCreateBook;
	}
	public JButton getBtnShowBooks() {
		return btnShowBooks;
	}
	public JButton getBtnShowDetailsBook() {
		return btnShowDetailsBook;
	}
	public JButton getBtnUpdateBook() {
		return btnUpdateBook;
	}
	public JButton getBtnDeleteBook() {
		return btnDeleteBook;
	}
	public JButton getBtnCloseBiblio() {
		return btnCloseBiblio;
	}
}
