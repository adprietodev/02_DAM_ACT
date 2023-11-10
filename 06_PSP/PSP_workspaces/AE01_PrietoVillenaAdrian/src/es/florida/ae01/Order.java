package es.florida.ae01;

import java.awt.EventQueue;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class Order extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JTextField textFieldName, textFieldI, textFieldO, textFieldT, textFieldJ, textFieldL, textFieldS,
	textFieldZ;
	private JRadioButton rdbtnSaveInFile, rdbtnShowInConsole;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblInfoSelExit, lblInfoNameFile, lblExtension, lblInfoSelectPieces, lblI, lblO, lblT, lblJ, lblL,
	lblS, lblZ;
	private JButton btnManufacture;
	private String typeSaveExit;
	private JPanel panelLoading;
	
	//Variable creada para llevar la cuenta de procesos que llevamos ejecutados y mostrarlo al finalizar uno.
	private int countProcess;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Order frame = new Order();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
	public Order() {
		typeSaveExit = "";
		countProcess = 0;
		initView();
		initEventHandler();
		
	}

	// Inicio de checkers

	
	/**
	 * Metodo donde revisamos que el texto que cogemos no tenga ningún char inapropiado. 
	 * @return nos devuelve true o false dependiendo si contiene o no.
	 */
	private boolean checkChar() {
		int[][] posChars = new int[][] { { 32, 47 }, { 58, 64 }, { 91, 94 }, { 96, 96 }, { 123, 255 } };
		boolean check = false;
		// System.out.println(posChars[0][0]+" "+posChars[0][1]);
		for (int i = 0; i < posChars.length; i++) {
			for (int j = posChars[i][0]; j <= posChars[i][1]; j++) {
				char c = (char) j;
				if (textFieldName.getText().contains(String.valueOf(c))) {
					check = true;
				}
			}
		}

		return check;
	}

	/**
	 * Metodo donde comprobamos si el nombre es valido, tanto si tiene un caracter inapropiado como si esta vacío.
	 * @return si esta todo correcto devuelve true si no false.
	 */
	private boolean checkNameValid() {
		boolean check = false;
		if (!textFieldName.getText().equals("")) {
			if (!checkChar()) {
				check = true;
			}
		}
		return check;
	}

	/**
	 * Metodo donde revisamos si los field donde hay que meter la cantidad de piezas es un numero y no contenga un string.
	 * @return devolvemos true si son todos numeros.
	 */
	private boolean checkIsNumber() {
		JTextField[] arrTF = new JTextField[] { textFieldI, textFieldO, textFieldT, textFieldJ, textFieldL, textFieldS,
				textFieldZ };
		boolean check = false;
		for (int i = 0; i < arrTF.length; i++) {
			try {
				Integer.parseInt(arrTF[i].getText());
				check = true;
			} catch (NumberFormatException e) {
				check = false;
				break;
			}
		}

		return check;
	}

	/**
	 * Metodo donde comprobamos que hay una opción de salida seleccionada
	 * @return si hay una selecionada devolvemos true.
	 */
	private boolean checkIsOptionExitSelected() {
		boolean check = false;
		// rdbtnSaveInFile, rdbtnShowInConsole;
		Enumeration rdbtn = buttonGroup.getElements();
		int numBtns = buttonGroup.getButtonCount();

		for (int i = 0; i < numBtns; i++) {
			AbstractButton btnTaked = (AbstractButton) rdbtn.nextElement();
			if (btnTaked.isSelected()) {
				check = true;
				break;
			}
		}

		return check;
	}

	// Fin de checkers

	
	/**
	 * Metodo donde iniciamos la parte visual de la aplicación.
	 */
	public void initView() {
		setBackground(new Color(238, 238, 238));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 330);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		

		lblInfoSelExit = new JLabel("Selecciona la manera de mostrar la salida de datos");
		lblInfoSelExit.setBounds(6, 6, 438, 16);
		mainPanel.add(lblInfoSelExit);

		rdbtnSaveInFile = new JRadioButton("Guardar en fichero");

		buttonGroup.add(rdbtnSaveInFile);
		rdbtnSaveInFile.setBounds(16, 34, 200, 23);
		mainPanel.add(rdbtnSaveInFile);

		rdbtnShowInConsole = new JRadioButton("Mostrar por consola");
		buttonGroup.add(rdbtnShowInConsole);
		rdbtnShowInConsole.setBounds(294, 34, 200, 23);
		mainPanel.add(rdbtnShowInConsole);

		lblInfoNameFile = new JLabel("Indica el nombre del fichero donde quieres que se guarde.");
		lblInfoNameFile.setBounds(26, 69, 385, 16);
		mainPanel.add(lblInfoNameFile);

		textFieldName = new JTextField();
		textFieldName.setEnabled(false);
		textFieldName.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldName.setBounds(23, 97, 131, 26);
		mainPanel.add(textFieldName);
		textFieldName.setColumns(10);

		lblExtension = new JLabel(".txt");
		lblExtension.setBounds(155, 102, 61, 16);
		mainPanel.add(lblExtension);

		lblInfoSelectPieces = new JLabel("Inserta la cantidad de piezas que quieres que se fabriquen.");
		lblInfoSelectPieces.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfoSelectPieces.setBounds(6, 135, 488, 16);
		mainPanel.add(lblInfoSelectPieces);

		lblI = new JLabel("I");
		lblI.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblI.setHorizontalAlignment(SwingConstants.CENTER);
		lblI.setBounds(69, 168, 20, 16);
		mainPanel.add(lblI);

		btnManufacture = new JButton("FABRICAR");
		btnManufacture.setBounds(188, 254, 117, 29);
		mainPanel.add(btnManufacture);

		textFieldI = new JTextField();
		textFieldI.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldI.setText("0");
		textFieldI.setBounds(95, 163, 50, 26);
		mainPanel.add(textFieldI);
		textFieldI.setColumns(10);

		lblO = new JLabel("O");
		lblO.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblO.setHorizontalAlignment(SwingConstants.CENTER);
		lblO.setBounds(162, 168, 20, 16);
		mainPanel.add(lblO);

		textFieldO = new JTextField();
		textFieldO.setText("0");
		textFieldO.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldO.setColumns(10);
		textFieldO.setBounds(188, 163, 50, 26);
		mainPanel.add(textFieldO);

		lblT = new JLabel("T");
		lblT.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblT.setHorizontalAlignment(SwingConstants.CENTER);
		lblT.setBounds(250, 168, 20, 16);
		mainPanel.add(lblT);

		textFieldT = new JTextField();
		textFieldT.setText("0");
		textFieldT.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldT.setColumns(10);
		textFieldT.setBounds(276, 163, 50, 26);
		mainPanel.add(textFieldT);

		lblJ = new JLabel("J");
		lblJ.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblJ.setHorizontalAlignment(SwingConstants.CENTER);
		lblJ.setBounds(344, 168, 20, 16);
		mainPanel.add(lblJ);

		textFieldJ = new JTextField();
		textFieldJ.setText("0");
		textFieldJ.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldJ.setColumns(10);
		textFieldJ.setBounds(370, 163, 50, 26);
		mainPanel.add(textFieldJ);

		lblL = new JLabel("L");
		lblL.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblL.setHorizontalAlignment(SwingConstants.CENTER);
		lblL.setBounds(119, 211, 20, 16);
		mainPanel.add(lblL);

		textFieldL = new JTextField();
		textFieldL.setText("0");
		textFieldL.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldL.setColumns(10);
		textFieldL.setBounds(145, 206, 50, 26);
		mainPanel.add(textFieldL);

		lblS = new JLabel("S");
		lblS.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblS.setHorizontalAlignment(SwingConstants.CENTER);
		lblS.setBounds(207, 211, 20, 16);
		mainPanel.add(lblS);

		textFieldS = new JTextField();
		textFieldS.setText("0");
		textFieldS.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldS.setColumns(10);
		textFieldS.setBounds(233, 206, 50, 26);
		mainPanel.add(textFieldS);

		lblZ = new JLabel("Z");
		lblZ.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblZ.setHorizontalAlignment(SwingConstants.CENTER);
		lblZ.setBounds(301, 211, 20, 16);
		mainPanel.add(lblZ);

		textFieldZ = new JTextField();
		textFieldZ.setText("0");
		textFieldZ.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldZ.setColumns(10);
		textFieldZ.setBounds(327, 206, 50, 26);
		mainPanel.add(textFieldZ);
		
		
		
	}

	/**
	 * Metodo que llamamos al inicio donde estan los listeners para cuando pulsamos un botón.
	 */
	public void initEventHandler() {
		rdbtnSaveInFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				typeSaveExit = "file";
				textFieldName.setEnabled(true);
			}
		});
		rdbtnShowInConsole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				typeSaveExit = "console";

			}
		});
		btnManufacture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (checkIsOptionExitSelected()) { //Comprobamos que haya una opción de salida
					if ((typeSaveExit.equals("file") && checkNameValid() && checkIsNumber())
							|| (typeSaveExit.equals("console") && checkIsNumber())) { // Comprobamos que dependiendo de la opción seleccionada realice una comprobación u otra.
						callManufactured(getTypeQuantity()); //Llamamos a Manufacture.
					} else {
						String mess = "Error/es:\n";
						mess += checkNameValid() ? ""
								: "Nombre para el archivo insertado no valido, por favor revisa que no tengaa ningún caracter extraño.\n";
						mess += checkIsNumber() ? ""
								: "Has introducido en alguna casilla un numero que no es valido, por favor revisa que no haya ninguna letra o caracter estraño.\nEn caso de no querer que se fabrique pon un 0 no lo dejes vacío";
						messDialog(mess, "Error datos", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					messDialog("No se ha seleccionado ninguna opción\nde salida de datos.", "Error seleccion",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	/**
	 * Metodo donde mostramos un mensaje en la interfaz
	 * @param mess nos indicara el mensaje que queremos mostrar
	 * @param title pondrá titulo a la ventana que aparece del mensaje.
	 * @param num el numero es el tipo de mensaje que nos mostrara, información, error, warrning, etc....
	 */
	public void messDialog(String mess, String title, int num) {
		JOptionPane.showMessageDialog(null, mess, title, num);
	}

	/**
	 * Metodo donde recogemos todas las piezas y la cantidad puesta por el usuario,
	 * @return devolvemos una string para poder pasarsela al programa manufacture.
	 */
	public String getTypeQuantity() {
		JTextField[] arrTF = new JTextField[] { textFieldI, textFieldO, textFieldT, textFieldJ, textFieldL, textFieldS,
				textFieldZ };
		JLabel[] arrLabel = new JLabel[] { lblI, lblO, lblT, lblJ, lblL, lblS, lblZ };
		String data = "";

		for (int i = 0; i < arrTF.length; i++) {

			data += arrLabel[i].getText() + " " + arrTF[i].getText() + ";";
		}

		return data;

	}
	
	/**
	 * Metodo donde llamamos a Manufacture y le pasamos los parametros.
	 * @param data datos necesaario para la fabricación de las piezas.
	 */
	public void callManufactured(String data) {
		countProcess++;
		try {

			String clase = "es.florida.ae01.Manufacture";
			String javaHome = System.getProperty("java.home");
			String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
			String classpath = System.getProperty("java.class.path");
			String className = clase;

			List<String> command = new ArrayList<>();
			command.add(javaBin);
			command.add("-cp");
			command.add(classpath);
			command.add(className);
			command.add(data);
			command.add(String.valueOf(countProcess));

			ProcessBuilder builder = new ProcessBuilder(command);
			if (typeSaveExit.equals("console")) {
				builder.inheritIO();
			}
			if (typeSaveExit.equals("file")) {
				builder.redirectOutput(new File(textFieldName.getText() + ".txt"));
			}

			Process process = builder.start();
			
			//process.waitFor();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
