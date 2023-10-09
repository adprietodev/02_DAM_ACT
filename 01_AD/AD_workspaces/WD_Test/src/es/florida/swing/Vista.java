package es.florida.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.awt.event.ActionEvent;

public class Vista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldSearch;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista frame = new Vista();
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
	public Vista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelTop = new JPanel();
		panelTop.setForeground(new Color(0, 0, 0));
		panelTop.setBounds(6, 6, 438, 50);
		contentPane.add(panelTop);
		panelTop.setLayout(null);
		
		JLabel lblSearchN = new JLabel("NAME:");
		lblSearchN.setBounds(6, 17, 61, 16);
		panelTop.add(lblSearchN);
		
		textFieldSearch = new JTextField();
		textFieldSearch.setBounds(75, 12, 200, 26);
		panelTop.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Entra cuando hacemos clic");
				String textSearch = textFieldSearch.getText();
				JOptionPane.showMessageDialog(null, textSearch, "ACTION BUTTON SEARCH",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnSearch.setBounds(287, 12, 117, 29);
		panelTop.add(btnSearch);
		
		JPanel panelBottom = new JPanel();
		panelBottom.setBounds(6, 68, 438, 198);
		contentPane.add(panelBottom);
		panelBottom.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ADVANCED");
		lblNewLabel.setBounds(6, 6, 79, 16);
		panelBottom.add(lblNewLabel);
		
		JComboBox comboBoxItems = new JComboBox();
		comboBoxItems.setModel(new DefaultComboBoxModel(new String[] {"Item 1", "Item 2", "Item 3", "Item 4"}));
		comboBoxItems.setSelectedIndex(0);
		comboBoxItems.setToolTipText("");
		comboBoxItems.setBounds(6, 34, 141, 27);
		panelBottom.add(comboBoxItems);
		
		JCheckBox chckbxCaracteristica = new JCheckBox("Include this");
		chckbxCaracteristica.setBounds(6, 66, 128, 23);
		panelBottom.add(chckbxCaracteristica);
		
		JRadioButton rdbtnOption1 = new JRadioButton("Option 1");
		buttonGroup.add(rdbtnOption1);
		rdbtnOption1.setBounds(6, 101, 141, 23);
		panelBottom.add(rdbtnOption1);
		
		JRadioButton rdbtnOption2 = new JRadioButton("Option 2");
		buttonGroup.add(rdbtnOption2);
		rdbtnOption2.setBounds(6, 126, 141, 23);
		panelBottom.add(rdbtnOption2);
		
		JRadioButton rdbtnOption3 = new JRadioButton("Option 3");
		buttonGroup.add(rdbtnOption3);
		rdbtnOption3.setBounds(6, 152, 141, 23);
		panelBottom.add(rdbtnOption3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(159, 34, 273, 126);
		panelBottom.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnSearchAvanced = new JButton("SEARCH");
		btnSearchAvanced.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Entra cuando hacemos clic");
				String strComboBox = comboBoxItems.getSelectedItem().toString();
				
				String strCheckBox = "Do not include this";
				if(chckbxCaracteristica.isSelected()) {
					strCheckBox = "Include this";
				}
				
				String strRadioButton = "No radiobutton selected";
				Enumeration radioButtons = buttonGroup.getElements();
				int numButtons = buttonGroup.getButtonCount();
				for(int i = 0; i < numButtons;i++) {
					AbstractButton radioButton = (AbstractButton) radioButtons.nextElement();
					if(radioButton.isSelected()) {
						strRadioButton = radioButton.getText();
					}
				}
				
				String freeText = textArea.getText();
				
				String strTotal = strComboBox + "\n"+strCheckBox+"\n"+strRadioButton+"\n"+freeText;
				
				JOptionPane.showMessageDialog(null, strTotal,"ACTION BUTTON SEARCH", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		btnSearchAvanced.setBounds(159, 163, 117, 29);
		panelBottom.add(btnSearchAvanced);
		
		JLabel lblTextField = new JLabel("Free text Field:");
		lblTextField.setFont(new Font("Helvetica", Font.PLAIN, 11));
		lblTextField.setBounds(159, 8, 184, 16);
		panelBottom.add(lblTextField);
	}
}
