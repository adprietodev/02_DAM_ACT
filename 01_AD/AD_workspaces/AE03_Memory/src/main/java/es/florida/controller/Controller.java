package es.florida.controller;

import es.florida.view.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import es.florida.model.*;

public class Controller implements ActionListener {
	
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
							cards = model.getCards();
							view.getSelectMode().dispose();
							
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton clickedButton = (JButton) e.getSource();
	     int buttonIndex = getButtonIndex(clickedButton);
	    //System.out.println("Se hizo clic en el Botón " + (buttonIndex));
	     
	     for(Card card : cards) {
	    	 if(card.getId() == buttonIndex) {
	    		 clickedButton.setIcon(card.getImgIcon());
	    	 }
	     }
	}
	
	private int getButtonIndex(JButton button) {
        JButton[] btnsImgs = view.getBtnsImgs();
        for (int i = 0; i < btnsImgs.length; i++) {
            if (btnsImgs[i] == button) {
                return i;
            }
        }
        return -1;
    }

}
