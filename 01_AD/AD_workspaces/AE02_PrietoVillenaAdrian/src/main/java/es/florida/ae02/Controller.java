package es.florida.ae02;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Controller {

	View view = new View();
	Model model = new Model();

	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
		initEH();
	}
	
	/**
	 * Metodo donde comprobaremos que panel esta activo y iniciaremos sus eventlisteners
	 */
	public void initEH() { 
		if(view.getTitle().equals("Inicio de sesión")) {
			initEventHandlersLog();
		}
		if(view.getTitle().equals("Realizar consultas")) {
			initEventHandlersQ();
		}
	}

	/**
	 * Metodo que llamamos en caso de estar en el panel de login para iniciar los botones del panel login
	 */
	public void initEventHandlersLog() {

		view.getBtnLog().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				boolean stateCon = model.checkState(view);

				String insertUser = view.getUserField().getText();
				String insertPass = view.getPassField().getText();

				if (model.checkCorrectType(insertUser) && model.checkPassword(insertUser, insertPass)) {
					view.queryView();
					view.getLogPanel().setVisible(false);

					view.getLblInfoLoged()
							.setText("Usuario logeado: " + model.userLog + " - " + model.dataCon.getUser());
					initEventHandlersQ();
				}

				if (!stateCon) {
					model.messDialog(
							"Estas desconectado de la base de datos, hac clic en Conectar DB para establecer conexión",
							"Error conexion Base de Datos", JOptionPane.WARNING_MESSAGE);
				} else {

					if (insertUser.replace(" ", "").equals("") || insertPass.replace(" ", "").equals("")) {
						model.messDialog("Uno o ambos apartados estan vacíos, por favor instroduce las credenciales.",
								"Error de accesos", JOptionPane.INFORMATION_MESSAGE);
					} else if (!model.checkCorrectType(insertUser) || !model.checkPassword(insertUser, insertPass)) {
						model.messDialog("El usuario o contraseña son incorrectos", "Error de accesos",
								JOptionPane.INFORMATION_MESSAGE);
					}

				}

				model.checkState(view);
				
				
			}
		});

		view.getbtnDescDBLog().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				boolean stateCon = model.checkState(view);

				try {

					if (stateCon) {
						if (model.confirmationDialog(
								"¿Estas seguro que quieres cerrar la conexión con la base de datos?", "Confirmación",
								JOptionPane.QUESTION_MESSAGE) == 0) {
							model.closeCon();
						}
					} else {
						model.connectDB();
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				model.checkState(view);
			}
		});

		

	}
	
	/**
	 * Metodo que llamamos cuando cambiamos de panel una vez ya logueado para iniciar los eventos de los botones de esa pantalla
	 */
	public void initEventHandlersQ() {
		
		view.getBtnDescBDQ().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				boolean stateCon = model.checkState(view);
				try {
					if (stateCon) {
						if (model.confirmationDialog(
								"¿Estas seguro que quieres cerrar la conexión con la base de datos?", "Confirmación",
								JOptionPane.QUESTION_MESSAGE) == 0) {
							model.closeCon();
						}
					} else {
						model.connectDB();
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				model.checkState(view);
			}
		});
		
		view.getBtnQuery().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				if(model.dataCon.isState()) {
					model.userQuery(view);
				} else {
					model.messDialog(
							"Estas desconectado de la base de datos, hac clic en Conectar DB para establecer conexión",
							"Error conexion Base de Datos", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		
		view.getBtnLogout().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				if(model.confirmationDialog("¿Estas seguro/a que quieres cerrar sesión?","Cerrar sesión", JOptionPane.QUESTION_MESSAGE) == 0) {
					view.loginView();
					view.getQueryPanel().setVisible(false);
					initEventHandlersLog();
					model.checkState(view);
				}
			}
		});
	}

}
