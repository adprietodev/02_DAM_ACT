package es.florida.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.florida.controller.Controller;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Panel;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JInternalFrame;

public class View extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, imgPanel;
	private Panel panelIsLogued;
	private JTextField textFieldUser;
	private JPasswordField passwordField;
	private JPasswordField repeatPasswordField;
	private JLabel lblPassword, lblRepeatPassword, lblIsLogued, lblUser, lblResults24, lblResults44;
	private JButton btnLogin, btnOk, btnCancel, btnRegister, btnPlay, btnSave, btnHallOfFame, btn24, btn44;
	private JButton[] btnsImgs;
	private JFrame selectMode, loginFrame, registerFrame, recordsFrame;
	private JTextPane textPane24, textPane44;

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

		mainView();
	}

	/**
	 * Metodo donde invocamos la pantalla principal
	 */
	private void mainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnLogin = new JButton("Login");
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setBounds(6, 135, 200, 42);
		contentPane.add(btnLogin);

		btnRegister = new JButton("Register");
		btnRegister.setBounds(6, 36, 200, 42);
		contentPane.add(btnRegister);

		btnPlay = new JButton("Play");
		btnPlay.setBounds(6, 250, 200, 42);
		contentPane.add(btnPlay);

		btnSave = new JButton("Save");
		btnSave.setBounds(6, 494, 200, 42);
		contentPane.add(btnSave);

		btnHallOfFame = new JButton("Hall of fame");
		btnHallOfFame.setBounds(6, 686, 200, 42);
		contentPane.add(btnHallOfFame);

		panelIsLogued = new Panel();
		panelIsLogued.setBackground(Color.RED);
		panelIsLogued.setBounds(13, 181, 186, 32);
		contentPane.add(panelIsLogued);
		panelIsLogued.setLayout(null);

		lblIsLogued = new JLabel("Not logued");
		lblIsLogued.setHorizontalAlignment(SwingConstants.CENTER);
		lblIsLogued.setBounds(6, 6, 174, 20);
		panelIsLogued.add(lblIsLogued);
		
		
		
		
		
	}

	/**
	 * Metodo donde ponemos el panel donde estan los botones que simbolizan cartas
	 * @param size pasamos la cantidad de cartas que va haber
	 */
	public void imgPanel(int size) {
		
		btnsImgs = new JButton[size];

		imgPanel = new JPanel();
		imgPanel.setBounds(266, 36, 520, 520);
		contentPane.add(imgPanel);
		imgPanel.setLayout(null);

		int posX = 0;
		int posY = 0;

		for (int i = 0; i < btnsImgs.length; i++) {
		
			btnsImgs[i] = new JButton("");
			btnsImgs[i].setBounds(posX, posY, 120, 120);
			imgPanel.add(btnsImgs[i]);

			posX += 130;

			if ((i + 1) % 4 == 0) {
				posY += 130;
				posX = 0;
			}

		}

	}

	/**
	 * Metodo donde aparece frame que selecionamos dificultad del juego
	 */
	public void selectMode() {

		selectMode = new JFrame("Game Mode");
		selectMode.setSize(200, 200);

		selectMode.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		selectMode.setVisible(true);

		selectMode.getContentPane().setLayout(null);

		centerScreenFrame(selectMode);

		btn24 = new JButton("2x4");
		btn24.setBounds(6, 40, 188, 42);
		selectMode.getContentPane().add(btn24);

		btn44 = new JButton("4x4");
		btn44.setBounds(6, 94, 188, 42);
		selectMode.getContentPane().add(btn44);

	}

	/**
	 * Metodo donde abrimos el frame para hacer login
	 */
	public void openLogin() {
		loginFrame = new JFrame("Login");
		loginFrame.setSize(400, 200);

		loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		loginFrame.setVisible(true);

		loginFrame.getContentPane().setLayout(null);

		centerScreenFrame(loginFrame);

		lblUser = new JLabel("User:");
		lblUser.setBounds(6, 32, 150, 16);
		loginFrame.getContentPane().add(lblUser);

		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(6, 60, 150, 16);
		loginFrame.getContentPane().add(lblPassword);

		lblRepeatPassword = new JLabel("Repeat password:");
		lblRepeatPassword.setBounds(6, 86, 150, 16);
		loginFrame.getContentPane().add(lblRepeatPassword);

		textFieldUser = new JTextField();
		textFieldUser.setBounds(168, 27, 226, 26);
		loginFrame.getContentPane().add(textFieldUser);
		textFieldUser.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(168, 55, 226, 26);
		loginFrame.getContentPane().add(passwordField);

		repeatPasswordField = new JPasswordField();
		repeatPasswordField.setBounds(168, 81, 226, 26);
		loginFrame.getContentPane().add(repeatPasswordField);

		btnOk = new JButton("OK");
		btnOk.setBounds(65, 124, 117, 29);
		loginFrame.getContentPane().add(btnOk);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(194, 124, 117, 29);
		loginFrame.getContentPane().add(btnCancel);

	}

	
	/**
	 * Metodo donde abrimos el frame de registro.
	 */
	public void openRegister() {
		registerFrame = new JFrame("Register");
		registerFrame.setSize(400, 200);

		registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo la ventana secundaria al hacer
																			// clic en cerrar
		registerFrame.setVisible(true);

		registerFrame.getContentPane().setLayout(null);

		centerScreenFrame(registerFrame);

		lblUser = new JLabel("User:");
		lblUser.setBounds(6, 30, 150, 16);
		registerFrame.getContentPane().add(lblUser);

		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(6, 58, 150, 16);
		registerFrame.getContentPane().add(lblPassword);

		lblRepeatPassword = new JLabel("Repeat password:");
		lblRepeatPassword.setBounds(6, 86, 150, 16);
		registerFrame.getContentPane().add(lblRepeatPassword);

		textFieldUser = new JTextField();
		textFieldUser.setBounds(168, 25, 226, 26);
		registerFrame.getContentPane().add(textFieldUser);
		textFieldUser.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(168, 53, 226, 26);
		registerFrame.getContentPane().add(passwordField);

		repeatPasswordField = new JPasswordField();
		repeatPasswordField.setBounds(168, 81, 226, 26);
		registerFrame.getContentPane().add(repeatPasswordField);

		btnOk = new JButton("OK");
		btnOk.setBounds(65, 124, 117, 29);
		registerFrame.getContentPane().add(btnOk);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(194, 124, 117, 29);
		registerFrame.getContentPane().add(btnCancel);
	}

	/**
	 * Metodo muestra los records guardados en la base de datos por orden de tiempo.
	 */
	public void openRecords() {

		recordsFrame = new JFrame("Records");
		recordsFrame.setSize(800, 600);

		recordsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo la ventana secundaria al hacer
																		// clic en cerrar
		recordsFrame.setVisible(true);

		recordsFrame.getContentPane().setLayout(null);

		textPane44 = new JTextPane();
		textPane44.setEditable(false);
		textPane44.setBounds(402, 66, 360, 480);
		recordsFrame.getContentPane().add(textPane44);

		textPane24 = new JTextPane();
		textPane24.setEditable(false);
		textPane24.setBounds(30, 66, 360, 480);
		recordsFrame.getContentPane().add(textPane24);

		lblResults24 = new JLabel("RESULTS 2X4");
		lblResults24.setHorizontalAlignment(SwingConstants.CENTER);
		lblResults24.setBounds(30, 36, 360, 16);
		recordsFrame.getContentPane().add(lblResults24);

		lblResults44 = new JLabel("RESULTS 4X4");
		lblResults44.setHorizontalAlignment(SwingConstants.CENTER);
		lblResults44.setBounds(402, 38, 360, 16);
		recordsFrame.getContentPane().add(lblResults44);

		centerScreenFrame(recordsFrame);

	}

	/**
	 * Metodo que utilizamos para centrar el frame en medio de la pantalla
	 * @param frame
	 */
	private void centerScreenFrame(JFrame frame) {
		// Centrar en la pantalla.
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (pantalla.width - frame.getWidth()) / 2;
		int y = (pantalla.height - frame.getHeight()) / 2;
		frame.setLocation(x, y);
	}

	// Getters and Setter

	/**
	 * @return the textFieldUser
	 */
	public JTextField getTextFieldUser() {
		return textFieldUser;
	}

	/**
	 * @return the imgPanel
	 */
	public JPanel getImgPanel() {
		return imgPanel;
	}

	/**
	 * @return the textPane24
	 */
	public JTextPane getTextPane24() {
		return textPane24;
	}

	/**
	 * @return the textPane44
	 */
	public JTextPane getTextPane44() {
		return textPane44;
	}

	/**
	 * @return the panelIsLogued
	 */
	public Panel getPanelIsLogued() {
		return panelIsLogued;
	}

	/**
	 * @return the lblIsLogued
	 */
	public JLabel getLblIsLogued() {
		return lblIsLogued;
	}

	/**
	 * @return the selectMode
	 */
	public JFrame getSelectMode() {
		return selectMode;
	}

	/**
	 * @return the loginFrame
	 */
	public JFrame getLoginFrame() {
		return loginFrame;
	}

	/**
	 * @return the registerFrame
	 */
	public JFrame getRegisterFrame() {
		return registerFrame;
	}

	/**
	 * @param textFieldUser the textFieldUser to set
	 */
	public void setTextFieldUser(JTextField textFieldUser) {
		this.textFieldUser = textFieldUser;
	}

	/**
	 * @return the passwordField
	 */
	public JPasswordField getPasswordField() {
		return passwordField;
	}

	/**
	 * @param passwordField the passwordField to set
	 */
	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	/**
	 * @return the repeatPasswordField
	 */
	public JPasswordField getRepeatPasswordField() {
		return repeatPasswordField;
	}

	/**
	 * @param repeatPasswordField the repeatPasswordField to set
	 */
	public void setRepeatPasswordField(JPasswordField repeatPasswordField) {
		this.repeatPasswordField = repeatPasswordField;
	}

	/**
	 * @return the btnLogin
	 */
	public JButton getBtnLogin() {
		return btnLogin;
	}

	/**
	 * @param btnLogin the btnLogin to set
	 */
	public void setBtnLogin(JButton btnLogin) {
		this.btnLogin = btnLogin;
	}

	/**
	 * @return the btnOk
	 */
	public JButton getBtnOk() {
		return btnOk;
	}

	/**
	 * @param btnOk the btnOk to set
	 */
	public void setBtnOk(JButton btnOk) {
		this.btnOk = btnOk;
	}

	/**
	 * @return the btnCancel
	 */
	public JButton getBtnCancel() {
		return btnCancel;
	}

	/**
	 * @param btnCancel the btnCancel to set
	 */
	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}

	/**
	 * @return the btnRegister
	 */
	public JButton getBtnRegister() {
		return btnRegister;
	}

	/**
	 * @param btnRegister the btnRegister to set
	 */
	public void setBtnRegister(JButton btnRegister) {
		this.btnRegister = btnRegister;
	}

	/**
	 * @return the btnPlay
	 */
	public JButton getBtnPlay() {
		return btnPlay;
	}

	/**
	 * @param btnPlay the btnPlay to set
	 */
	public void setBtnPlay(JButton btnPlay) {
		this.btnPlay = btnPlay;
	}

	/**
	 * @return the btnSave
	 */
	public JButton getBtnSave() {
		return btnSave;
	}

	/**
	 * @param btnSave the btnSave to set
	 */
	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}

	/**
	 * @return the btnHallOfFame
	 */
	public JButton getBtnHallOfFame() {
		return btnHallOfFame;
	}

	/**
	 * @param btnHallOfFame the btnHallOfFame to set
	 */
	public void setBtnHallOfFame(JButton btnHallOfFame) {
		this.btnHallOfFame = btnHallOfFame;
	}

	/**
	 * @return the btn24
	 */
	public JButton getBtn24() {
		return btn24;
	}

	/**
	 * @param btn24 the btn24 to set
	 */
	public void setBtn24(JButton btn24) {
		this.btn24 = btn24;
	}

	/**
	 * @return the btn44
	 */
	public JButton getBtn44() {
		return btn44;
	}

	/**
	 * @param btn44 the btn44 to set
	 */
	public void setBtn44(JButton btn44) {
		this.btn44 = btn44;
	}

	/**
	 * @return the btnsImgs
	 */
	public JButton[] getBtnsImgs() {
		return btnsImgs;
	}

	/**
	 * @param btnsImgs the btnsImgs to set
	 */
	public void setBtnsImgs(JButton[] btnsImgs) {
		this.btnsImgs = btnsImgs;
	}
}
