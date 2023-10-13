package es.florida.AE01;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import java.awt.Choice;

public class AE01_Program extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private JTextField textWriteDir,textFieldFile01,textFieldFile02,textFieldNewFileName;
	private JComboBox comboBoxSelectDir;
	private JButton btnSearch,btnFusion;
	private JTextArea textAreaData;
	private JRadioButton rdbtnSelectDir,rdbtnWriteDir,rdbtnAsc,rdbtnDesc,rdbtnName,rdbtnSize,rdbtnDate;
	
	
	// Variables creadas desarrollador
	private static File directory;
	private static String[] listTxtFiles;
	private static ArrayList<File> filesTxt = new ArrayList<File>();
	private static ArrayList<File> orderFile = new ArrayList<File>();
	private static File newFile;
	private JTextField textMatch;
	private static int countCom = 0;
	private JButton btnSMatch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AE01_Program frame = new AE01_Program();
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
	public AE01_Program() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 555, 559);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTextInfoSel = new JLabel("Selección de directorio");
		lblTextInfoSel.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextInfoSel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblTextInfoSel.setBounds(6, 6, 543, 16);
		contentPane.add(lblTextInfoSel);
		
		comboBoxSelectDir = new JComboBox();
		comboBoxSelectDir.setBounds(255, 53, 294, 27);
		contentPane.add(comboBoxSelectDir);
		comboBoxSelectDir.setModel(new DefaultComboBoxModel(new String[] {"01_Docs", "02_Facturas", "03_Manuales", "04_Admin"}));
		comboBoxSelectDir.setEnabled(false);
		
		btnSearch = new JButton("BUSQUEDA");
		btnSearch.setEnabled(false);
		btnSearch.setBounds(6, 257, 151, 43);
		contentPane.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				filesTxt.clear();
				textAreaData.setText("");
				
				if(textWriteDir.isEnabled()) {
					directory = new File(textWriteDir.getText());
				}
				if(comboBoxSelectDir.isEnabled()) {
					directory = new File(comboBoxSelectDir.getSelectedItem().toString());
				}
				if(directory.exists()) {
					listTxtFiles = directory.list(new FilterExtension("txt"));
					for(int i = 0; i < listTxtFiles.length; i++) {
						filesTxt.add(new File(directory+"/"+listTxtFiles[i]));
					}
				}
				
				String strAscDes = "Ascendente";
				Enumeration radioButtonsAD = buttonGroup_1.getElements();
				int numButtonsAD = buttonGroup_1.getButtonCount();
				for(int i = 0; i < numButtonsAD;i++) {
					AbstractButton radioButtonAD = (AbstractButton) radioButtonsAD.nextElement();
					if(radioButtonAD.isSelected()) {
						strAscDes = radioButtonAD.getText();
					}
				}
				
				String strNumSizDate = "Nombre";
				Enumeration radioButtonsNSD = buttonGroup_2.getElements();
				int numButtonsNSD = buttonGroup_2.getButtonCount();
				for(int i = 0; i < numButtonsNSD;i++) {
					AbstractButton radioButtonNSD = (AbstractButton) radioButtonsNSD.nextElement();
					if(radioButtonNSD.isSelected()) {
						strNumSizDate = radioButtonNSD.getText();
					}
				}
				
				textAreaData.append("Orden "+strAscDes+" por "+strNumSizDate+"\n");
				
				orderFiles(strAscDes,strNumSizDate);
				
				if(textMatch.getText().equals("")) {
					for(File file: orderFile) {
						showFiles(file);
					}
				} else {
					matchInFiles();
				}
				
				
				btnFusion.setEnabled(true);
				
			}
		});
		
		rdbtnSelectDir = new JRadioButton("Seleccionar directorio");
		rdbtnSelectDir.setBounds(6, 55, 192, 23);
		contentPane.add(rdbtnSelectDir);
		rdbtnSelectDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearch.setEnabled(true);
				if(rdbtnSelectDir.isSelected()) {
					comboBoxSelectDir.setEnabled(true);
					textWriteDir.setEnabled(false);
					
				}
				if(!btnSMatch.isEnabled()) {
					btnSMatch.setEnabled(true);
				}
			}
		});
		buttonGroup.add(rdbtnSelectDir);
		
		
		rdbtnWriteDir = new JRadioButton("Escribir nombre directorio o ruta");
		rdbtnWriteDir.setBounds(6, 91, 237, 23);
		contentPane.add(rdbtnWriteDir);
		rdbtnWriteDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearch.setEnabled(true);
				if(rdbtnWriteDir.isSelected()) {
					comboBoxSelectDir.setEnabled(false);
					textWriteDir.setEnabled(true);
					
				}
				if(!btnSMatch.isEnabled()) {
					btnSMatch.setEnabled(true);
				}
			}
		});
		buttonGroup.add(rdbtnWriteDir);
		
		textWriteDir = new JTextField();
		textWriteDir.setBounds(255, 88, 294, 26);
		contentPane.add(textWriteDir);
		textWriteDir.setEnabled(false);
		textWriteDir.setColumns(10);
		
		
		
		rdbtnAsc = new JRadioButton("Ascendente");
		buttonGroup_1.add(rdbtnAsc);
		rdbtnAsc.setSelected(true);
		rdbtnAsc.setBounds(16, 187, 105, 23);
		contentPane.add(rdbtnAsc);
		
		rdbtnDesc = new JRadioButton("Descendente");
		buttonGroup_1.add(rdbtnDesc);
		rdbtnDesc.setBounds(16, 222, 113, 23);
		contentPane.add(rdbtnDesc);
		
		JLabel lblTextInfoOrg = new JLabel("Selección organización de ficheros (txt)");
		lblTextInfoOrg.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextInfoOrg.setFont(new Font("Lucida Grande", Font.BOLD, 12));
		lblTextInfoOrg.setBounds(6, 148, 253, 27);
		contentPane.add(lblTextInfoOrg);
		
		rdbtnName = new JRadioButton("Nombre");
		rdbtnName.setSelected(true);
		buttonGroup_2.add(rdbtnName);
		rdbtnName.setBounds(169, 187, 82, 23);
		contentPane.add(rdbtnName);
		
		rdbtnSize = new JRadioButton("Tamaño");
		buttonGroup_2.add(rdbtnSize);
		rdbtnSize.setBounds(169, 222, 82, 23);
		contentPane.add(rdbtnSize);
		
		rdbtnDate = new JRadioButton("Fecha");
		buttonGroup_2.add(rdbtnDate);
		rdbtnDate.setBounds(169, 257, 100, 23);
		contentPane.add(rdbtnDate);
		
		JLabel lblMatches = new JLabel("Revisar concidencias con la palabra:");
		lblMatches.setBounds(6, 312, 237, 27);
		contentPane.add(lblMatches);
		
		textMatch = new JTextField();
		textMatch.setBounds(6, 340, 151, 26);
		contentPane.add(textMatch);
		textMatch.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(281, 151, 268, 207);
		contentPane.add(scrollPane);
		
		textAreaData = new JTextArea();
		scrollPane.setViewportView(textAreaData);
		
		JLabel lblData = new JLabel("Datos");
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setBounds(278, 127, 271, 16);
		contentPane.add(lblData);
		
		JLabel lblFusionFile = new JLabel("Fusionar archivos");
		lblFusionFile.setHorizontalAlignment(SwingConstants.CENTER);
		lblFusionFile.setBounds(16, 376, 533, 16);
		contentPane.add(lblFusionFile);
		
		JLabel lblFile01 = new JLabel("Archivo 01");
		lblFile01.setHorizontalAlignment(SwingConstants.CENTER);
		lblFile01.setBounds(39, 404, 82, 16);
		contentPane.add(lblFile01);
		
		JLabel lblFile02 = new JLabel("Archivo 02");
		lblFile02.setHorizontalAlignment(SwingConstants.CENTER);
		lblFile02.setBounds(236, 404, 82, 16);
		contentPane.add(lblFile02);
		
		JLabel lblNewFile = new JLabel("Nombre nuevo archivo");
		lblNewFile.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewFile.setBounds(407, 404, 142, 16);
		contentPane.add(lblNewFile);
		
		textFieldFile01 = new JTextField();
		textFieldFile01.setBounds(16, 432, 130, 26);
		contentPane.add(textFieldFile01);
		textFieldFile01.setColumns(10);
		
		textFieldFile02 = new JTextField();
		textFieldFile02.setColumns(10);
		textFieldFile02.setBounds(205, 432, 147, 26);
		contentPane.add(textFieldFile02);
		
		textFieldNewFileName = new JTextField();
		textFieldNewFileName.setColumns(10);
		textFieldNewFileName.setBounds(407, 432, 142, 26);
		contentPane.add(textFieldNewFileName);
		
		btnFusion = new JButton("FUSIÓN");
		btnFusion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textFieldFile01.getText().equals("") && !textFieldFile02.getText().equals("") && !textFieldNewFileName.getText().equals("")) {
					
					String file01G = directory+File.separator+textFieldFile01.getText();
					String file02G = directory+File.separator+textFieldFile02.getText();
					String newFileS = directory+File.separator+textFieldNewFileName.getText();
					newFile = new File(newFileS);
					
					
					if(newFile.exists()) {
						String res = JOptionPane.showInputDialog("El archivo "+textFieldNewFileName.getText()+" ya existe ¿Quieres sobrescribirlo? (y/n)");
						if(res.equals("y")) {
								fusionFiles(file01G,file02G,newFileS, false);
						} else {
							fusionFiles(file01G,file02G,newFileS, true);
						}
					}else {
						try {
							newFile.createNewFile();
						}catch(Exception ex) {
							System.out.println(ex);
						}
						fusionFiles(file01G,file02G,newFileS, false);	
					
					}
					
					textAreaData.append("Fusión realizada, puedes comprobarlo dentro del archivo "+textFieldNewFileName.getText()+"\n");
					
				} else {
					textAreaData.append("Algún apartado esta vacío, no se ha podido realizar la fusión.\n");
				}
			}
		});
		btnFusion.setEnabled(false);
		btnFusion.setBounds(169, 470, 208, 55);
		contentPane.add(btnFusion);
		
		btnSMatch = new JButton("Comparar");
		btnSMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				matchInFiles();
			}
		});
		btnSMatch.setEnabled(false);
		btnSMatch.setBounds(157, 339, 117, 27);
		contentPane.add(btnSMatch);
		
		
	}
	
	public void showFiles(File file) {
		textAreaData.append(file.getName());
	}
	
 	public void matchInFiles() {
		
		textAreaData.setText("");
		Scanner word;
		
		for(File file:orderFile) {
			showFiles(file);
			if(!textMatch.getText().equals("")) {
				try {
					word = new Scanner(new File(directory.getName()+"/"+file.getName()));
					while(word.hasNext()) {
						//System.out.println(word.next()+" - "+textMatch.getText());
						if(word.next().equals(textMatch.getText())) {
							countCom++;
						}
					}
				}catch(Exception ex) {
					System.out.println(ex);
				}
				
				textAreaData.append("-> "+countCom+" coincidencias.\n");
				countCom = 0;
			}
			
		}
		if(textMatch.getText().equals("")) {
			textAreaData.append("No se ha escrito palabra para buscar coincidencias.\n");
		}
	}
	
	public void orderFiles(String strAscDes, String strNumSizDate) {

		ArrayList<String> nameTxt = new ArrayList<String>();
		ArrayList<Long> sizeTxt = new ArrayList<Long>();
		ArrayList<Date> dateTxt = new ArrayList<Date>();
		
		nameTxt.clear();
		sizeTxt.clear();
		dateTxt.clear();
		orderFile.clear();
		
		for(File lista:filesTxt) {
			nameTxt.add(lista.getName());
			sizeTxt.add(lista.length());
			dateTxt.add(new Date(lista.lastModified()));
		}
		
		switch(strAscDes) {
		case "Ascendente":
			if(strNumSizDate == "Nombre") {
				Collections.sort(nameTxt);
				for(String nameList: nameTxt) {
					for(File list:filesTxt) {
						if(nameList.equals(list.getName())) {
							orderFile.add(list);
							break;
						}
					}
				}
			}
			
			if(strNumSizDate == "Tamaño") {
				Collections.sort(sizeTxt);
				for(Long sizeList:sizeTxt) {
					for(File list:filesTxt) {
						if(sizeList == list.length()) {
							orderFile.add(list);
							break;
						}
					}
				}
			}
			
			if(strNumSizDate == "Fecha") {
				Collections.sort(dateTxt);
				for(Date dateList:dateTxt) {
					for(File list:filesTxt) {
						if(dateList.equals(new Date(list.lastModified()))) {
							orderFile.add(list);
							break;
						}
					}
				}
			}
			break;
		case "Descendente":
			if(strNumSizDate == "Nombre") {
				Collections.sort(nameTxt, Collections.reverseOrder());
				for(String nameList: nameTxt) {
					for(File list:filesTxt) {
						if(nameList.equals(list.getName())) {
							orderFile.add(list);
							break;
						}
					}
				}
			}
			if(strNumSizDate == "Tamaño") {
				Collections.sort(sizeTxt, Collections.reverseOrder());
				for(Long sizeList:sizeTxt) {
					for(File list:filesTxt) {
						if(sizeList == list.length()) {
							orderFile.add(list);
							break;
						}
					}
				}
			}
			if(strNumSizDate == "Fecha") {
				Collections.sort(dateTxt, Collections.reverseOrder());
				for(Date dateList:dateTxt) {
					for(File list:filesTxt) {
						if(dateList.equals(new Date(list.lastModified()))) {
							orderFile.add(list);
							break;
						}
					}
				}
				
			}
			break;
		}
	}
	
	public void fusionFiles(String srtFile01, String srtFile02, String srtNewFile, Boolean s) {
		
			try {
				
				FileWriter fW = new FileWriter(srtNewFile,s);
				BufferedWriter bW = new BufferedWriter(fW);
				FileReader fR01 = new FileReader(srtFile01);
				BufferedReader bR01 = new BufferedReader(fR01);
				FileReader fR02 = new FileReader(srtFile02);
				BufferedReader bR02 = new BufferedReader(fR02);
				
				String linea = bR01.readLine();
				
				while(linea != null ) {
					bW.write(linea);
					bW.newLine();
					linea = bR01.readLine();
				}
				
				linea = bR02.readLine();
				while(linea != null ) {
					bW.write(linea);
					bW.newLine();
					linea = bR02.readLine();
				}
				
				bR02.close();
				fR02.close();
				bR01.close();
				fR01.close();
				bW.close();
				fW.close();
				
			} catch(Exception e) {
				
			}
		
	}
}
