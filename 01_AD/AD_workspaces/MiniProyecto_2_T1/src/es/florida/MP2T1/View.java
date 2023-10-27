package es.florida.MP2T1;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class View extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchField, replaceField;
	private JScrollPane sPInfoDoc;
	public JButton btnSearch, btnReplace;
	private JScrollPane sPRemplace;
	private JTextPane textReplace, textInfoDoc;
	

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
		setTitle("Aplicacion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 820, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		sPInfoDoc = new JScrollPane();
		sPInfoDoc.setBounds(6, 6, 808, 240);
		contentPane.add(sPInfoDoc);
		
		textInfoDoc = new JTextPane();
		sPInfoDoc.setViewportView(textInfoDoc);
		
		
		
		searchField = new JTextField();
		searchField.setBounds(6, 258, 250, 26);
		contentPane.add(searchField);
		searchField.setColumns(10);
		
		replaceField = new JTextField();
		replaceField.setColumns(10);
		replaceField.setBounds(412, 258, 250, 26);
		contentPane.add(replaceField);
		
		btnSearch = new JButton("Buscar");
		btnSearch.setBounds(268, 258, 117, 29);
		contentPane.add(btnSearch);
		
		btnReplace = new JButton("Remplazar");
		btnReplace.setBounds(685, 258, 117, 29);
		contentPane.add(btnReplace);
		
		sPRemplace = new JScrollPane();
		sPRemplace.setBounds(6, 296, 808, 270);
		contentPane.add(sPRemplace);
		
		textReplace = new JTextPane();
		sPRemplace.setViewportView(textReplace);
		
		
	}
	
	public String getSearchField() {
		return searchField.getText();
	}
	public String getReplaceField() {
		return replaceField.getText();
	}
	
	public JTextPane getTextInfoDoc() {
		return textInfoDoc;
	}
	
	public void setTextInfoDoc(String value) {
		textInfoDoc.setText(value); 
	}
	
	public JTextPane getTextReplace() {
		return textReplace;
	}
	
	public void setTextReplace(String value) {
		textReplace.setText(value);
	}
}
