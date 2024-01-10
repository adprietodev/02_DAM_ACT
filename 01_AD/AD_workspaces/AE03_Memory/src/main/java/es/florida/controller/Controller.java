package es.florida.controller;
import java.awt.Image;
import es.florida.view.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import es.florida.model.*;

public class Controller {
	
	private View view;
	private Model model;
	private List<Card> cardsTurned;
	private List<Card> cards;

	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
		initEventHandlersLog();
		cardsTurned = new ArrayList();
		cards = new ArrayList();
	}
	
	public Controller(View view) {
		this.view = view;
		initEventHandlersLog();
		cardsTurned = new ArrayList();
		cards = new ArrayList();
	}

	public void initEventHandlersLog() {
		view.getBtnLogin().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				view.openLogin();
				
				
				view.getBtnOk().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg) {
						
						if(model.login(view)) {
							
							view.getLoginFrame().dispose();
							JOptionPane.showMessageDialog(null, "User login successfully", "Login correct", JOptionPane.INFORMATION_MESSAGE);
							view.getPanelIsLogued().setBackground(Color.GREEN);
							view.getLblIsLogued().setText("Logued");
							model.setLogin(true);
							
						} else {
							
							JOptionPane.showMessageDialog(null, "User error login", "Login error", JOptionPane.ERROR_MESSAGE);
							view.getTextFieldUser().setText("");
							view.getPasswordField().setText("");
							view.getRepeatPasswordField().setText("");
						}
						
					}});
				
				view.getBtnCancel().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg) {
						view.getLoginFrame().dispose();
					}});
			}
		});
		
		view.getBtnRegister().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				
				view.openRegister();
				
				view.getBtnOk().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg) {
						
						if(model.register(view)) {
							view.getRegisterFrame().dispose();
							JOptionPane.showMessageDialog(null, "User register successfully", "Register correct", JOptionPane.INFORMATION_MESSAGE);
						} else {
							
							JOptionPane.showMessageDialog(null, "User error login", "Login error", JOptionPane.ERROR_MESSAGE);
							view.getTextFieldUser().setText("");
							view.getPasswordField().setText("");
							view.getRepeatPasswordField().setText("");
						}
					}});
				
				view.getBtnCancel().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg) {
						view.getRegisterFrame().dispose();
					}});
			}
		});
		
		view.getBtnPlay().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				
				if(model.isLogin()) {
					view.selectMode();
					view.getBtn24().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg) {
							view.imgPanel(8);
							model.generateCard(view.getBtnsImgs(), 4);
							view.getSelectMode().dispose();
							for(int i = 0; i < view.getBtnsImgs().length; i++) {
								view.getBtnsImgs()[i].addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg) {
										for(Card card : model.getCards()) {
											if(card.getBtnImg() == (JButton) arg.getSource()) {
												System.out.println(card.getId()+" - "+card.getName()+" - ");
												card.turnCard();
											}
										}
									}
								});
							}
							
							
						}});
					
					view.getBtn44().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg) {
							view.imgPanel(16);
							model.generateCard(view.getBtnsImgs(), 8);
							view.getSelectMode().dispose();
						}});
				} else {
					JOptionPane.showMessageDialog(null, "User not logued", "User not logued, login first.", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		
		view.getBtnSave().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				 
			}
		});
		
		view.getBtnHallOfFame().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				
			}
		});
		
				
	}

}
