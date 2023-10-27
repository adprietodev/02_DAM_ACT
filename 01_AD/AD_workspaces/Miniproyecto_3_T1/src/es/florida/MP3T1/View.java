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

public class View extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTextPane titleBooks;
	private JLabel lblBooks;
	private JScrollPane scrollPane_1;
	private JLabel lblInfoBook;
	private JTextPane textPane;
	private JButton btnCreateBook;

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
		scrollPane.setBounds(6, 59, 246, 517);
		contentPane.add(scrollPane);
		
		titleBooks = new JTextPane();
		scrollPane.setViewportView(titleBooks);
		
		lblBooks = new JLabel("Libros en la biblioteca");
		lblBooks.setHorizontalAlignment(SwingConstants.CENTER);
		lblBooks.setBounds(6, 31, 246, 16);
		contentPane.add(lblBooks);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(264, 59, 550, 184);
		contentPane.add(scrollPane_1);
		
		textPane = new JTextPane();
		scrollPane_1.setViewportView(textPane);
		
		lblInfoBook = new JLabel("Información Libro");
		lblInfoBook.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfoBook.setBounds(264, 31, 550, 16);
		contentPane.add(lblInfoBook);
		
		btnCreateBook = new JButton("Crear Libro");
		btnCreateBook.setBounds(264, 255, 117, 29);
		contentPane.add(btnCreateBook);
		
		
	}
	
	public JTextPane getTitleBooks() {
		return titleBooks;
	}
	
	public JButton getBtnCreateBook() {
		return btnCreateBook;
	}
}
