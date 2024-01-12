package es.florida.controller;

import java.awt.Image;
import java.awt.Dialog.ModalExclusionType;

import es.florida.view.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import es.florida.model.*;
import es.florida.model.Record;

public class Controller {

	private View view;
	private Model model;

	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
		initEventHandlersLog();

	}

	public Controller(View view) {
		this.view = view;
		initEventHandlersLog();

	}

	/**
	 * Metodo para que coger el clic de los botones.
	 */
	public void initEventHandlersLog() {
		view.getBtnLogin().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				view.openLogin();

				view.getBtnOk().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg) {

						if (model.login(view)) {

							view.getLoginFrame().dispose();
							JOptionPane.showMessageDialog(null, "User login successfully", "Login correct",
									JOptionPane.INFORMATION_MESSAGE);
							view.getPanelIsLogued().setBackground(Color.GREEN);
							view.getLblIsLogued().setText("Logued");
							model.setLogin(true);

						} else {

							JOptionPane.showMessageDialog(null, "User error login", "Login error",
									JOptionPane.ERROR_MESSAGE);
							view.getTextFieldUser().setText("");
							view.getPasswordField().setText("");
							view.getRepeatPasswordField().setText("");
						}

					}
				});

				view.getBtnCancel().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg) {
						view.getLoginFrame().dispose();
					}
				});
			}
		});

		view.getBtnRegister().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {

				view.openRegister();

				view.getBtnOk().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg) {

						if (model.register(view)) {
							view.getRegisterFrame().dispose();
							JOptionPane.showMessageDialog(null, "User register successfully", "Register correct",
									JOptionPane.INFORMATION_MESSAGE);
						} else {

							JOptionPane.showMessageDialog(null,
									"El usuario que has elegido ya existe o has introducido un caracter invalido, revisalo de nuevo por favor.",
									"USUARIO INVALIDO", JOptionPane.ERROR_MESSAGE);
							view.getTextFieldUser().setText("");
							view.getPasswordField().setText("");
							view.getRepeatPasswordField().setText("");
						}
					}
				});

				view.getBtnCancel().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg) {
						view.getRegisterFrame().dispose();
					}
				});
			}
		});

		view.getBtnPlay().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				
				if(view.getImgPanel() != null) {
					view.getImgPanel().removeAll();
					view.getImgPanel().revalidate();
					view.getImgPanel().repaint();
				}
				
				if (model.isLogin()) {
					
					view.selectMode();
					

					view.getBtn24().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg) {
							
							view.imgPanel(8);
							model.generateCard(view.getBtnsImgs(), 4);
							
							view.getSelectMode().dispose();
							
							disabledBtnsLeft();

							model.setInitTime(System.currentTimeMillis());

							// Botones de imagenes
							for (int i = 0; i < view.getBtnsImgs().length; i++) {
								
								final int currentIndex = i;
								
								view.getBtnsImgs()[i].addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg) {
										
										for (Card card : model.getCards()) {
											
											if (card.isClikable()) {
												if (card.getId() == currentIndex) {
													
													card.turnOnCard();
													model.getCardsTurned().add(card);
												}

												if (model.getCardsTurned().size() == 2) {

													if (model.compareCards()) {
														model.setCountPairCards(model.getCountPairCards()+1);
														//System.out.println(model.getCountPairCards());
													}

													model.getCardsTurned().clear();
												}
											}
										}

										if (model.getCountPairCards() == 4) {
											// Finalizamos el juego
											int time = (int) model.generateTotalTime(model.getInitTime(),
													System.currentTimeMillis());
											String timestamp = model.generateTimeStamp();

											model.setCurrentRecord(
													new Record(model.getCurrentUserLogued(), 8, timestamp, time));

											if(model.isBestDuration(8, time)) {
												JOptionPane.showMessageDialog(null,
														"!Enhorabuena has sacado el mejor tiempo!. \n Tiempo transcurrido: " + time,
														"Juego Finalizado", JOptionPane.INFORMATION_MESSAGE);
											} else {
												JOptionPane.showMessageDialog(null,
														"Juego finalizado. \n Tiempo transcurrido: " + time,
														"Juego Finalizado", JOptionPane.INFORMATION_MESSAGE);
											}
											model.getCardsTurned().clear();
											model.deleteFiles();
											model.getCards().clear();
											model.setCountPairCards(0);
											enabledBtnsLeft();
										}
									}
								});
							}

						}
					});

					view.getBtn44().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg) {

							view.imgPanel(16);
							model.generateCard(view.getBtnsImgs(), 8);
							
							view.getSelectMode().dispose();
							disabledBtnsLeft();

							model.setInitTime(System.currentTimeMillis());

							// Botones de imagenes
							for (int i = 0; i < view.getBtnsImgs().length; i++) {
								
								final int currentIndex = i;
								
								view.getBtnsImgs()[i].addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg) {
										
										for (Card card : model.getCards()) {
											
											if (card.isClikable()) {
												if (card.getId() == currentIndex) {
													
													card.turnOnCard();
													model.getCardsTurned().add(card);
												}

												if (model.getCardsTurned().size() == 2) {

													if (model.compareCards()) {
														model.setCountPairCards(model.getCountPairCards()+1);
														//System.out.println(model.getCountPairCards());
													}

													model.getCardsTurned().clear();
												}
											}
										}

										if (model.getCountPairCards() == 8) {
											// Finalizamos el juego
											int time = (int) model.generateTotalTime(model.getInitTime(),
													System.currentTimeMillis());
											String timestamp = model.generateTimeStamp();

											model.setCurrentRecord(
													new Record(model.getCurrentUserLogued(), 16, timestamp, time));

											if(model.isBestDuration(16, time)) {
												JOptionPane.showMessageDialog(null,
														"!Enhorabuena has sacado el mejor tiempo!. \n Tiempo transcurrido: " + time,
														"Juego Finalizado", JOptionPane.INFORMATION_MESSAGE);
											} else {
												JOptionPane.showMessageDialog(null,
														"Juego finalizado. \n Tiempo transcurrido: " + time,
														"Juego Finalizado", JOptionPane.INFORMATION_MESSAGE);
											}
											
											model.getCardsTurned().clear();
											model.deleteFiles();
											model.getCards().clear();
											model.setCountPairCards(0);
											enabledBtnsLeft();
											
										}
									}
								});
							}
						}
					});
				} else {
					JOptionPane.showMessageDialog(null, "User not logued", "User not logued, login first.",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		view.getBtnSave().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				if (model.isLogin()) {
					model.saveCurrentRecord();
				}else {
					JOptionPane.showMessageDialog(null, "User not logued", "User not logued, login first.",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});

		view.getBtnHallOfFame().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				if (model.isLogin()) {
					view.openRecords();
					model.showRecords(view);
				}else {
					JOptionPane.showMessageDialog(null, "User not logued", "User not logued, login first.",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});

	}
	
	
	
	/**
	 * Lo utilizamos para volver habilitarlos
	 */
	private void enabledBtnsLeft() {
		view.getBtnHallOfFame().setEnabled(true);
		view.getBtnLogin().setEnabled(true);
		view.getBtnPlay().setEnabled(true);
		view.getBtnRegister().setEnabled(true);
		view.getBtnSave().setEnabled(true);
	}
	
	/**
	 * Lo utilizamos para deshabilitar los botones de la izquierda mientras jugamos
	 */
	private void disabledBtnsLeft() {
		view.getBtnHallOfFame().setEnabled(false);
		view.getBtnLogin().setEnabled(false);
		view.getBtnPlay().setEnabled(false);
		view.getBtnRegister().setEnabled(false);
		view.getBtnSave().setEnabled(false);
	}

}
