package es.florida.ae02;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;


public class View extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel fatherPane,logPanel,queryPanel;
	private JScrollPane scrollPaneInfo,scrollPanelQuery;
	private JLabel lblInfoLoged,lblState,lblInfoWQ,lblTitle,lblPass,lblUser;
	private JTextPane textQuery;
	private JTable table;
	private JButton btnQuery,btnDescDBQ,btnDescDBLog,btnLogout,btnLog;
	private JTextField userField;
	private JPasswordField passField;

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
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 820, 610);
		fatherPane = new JPanel();
		fatherPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(fatherPane);
		fatherPane.setLayout(null);
		
		loginView();
		//queryView();
	}
	
	/**
	 * Metodo donde iniciamos el panel de login
	 */
	public void loginView() {
		setTitle("Inicio de sesión");
		logPanel = new JPanel();
		logPanel.setBounds(6, 6, 808, 570);
		fatherPane.add(logPanel);
		logPanel.setLayout(null);
		
		lblUser = new JLabel("Usuario:");
		lblUser.setFont(new Font("Arial", Font.PLAIN, 16));
		lblUser.setBounds(268, 168, 272, 18);
		logPanel.add(lblUser);
		
		userField = new JTextField();
		userField.setBounds(268, 198, 272, 42);
		logPanel.add(userField);
		userField.setColumns(10);
		
		lblPass = new JLabel("Contraseña:");
		lblPass.setFont(new Font("Arial", Font.PLAIN, 16));
		lblPass.setBounds(268, 252, 272, 18);
		logPanel.add(lblPass);
		
		btnLog = new JButton("ACCEDER");
		btnLog.setBounds(303, 360, 207, 42);
		logPanel.add(btnLog);
		
		lblTitle = new JLabel("Inicio de sesión");
		lblTitle.setFont(new Font("Arial", Font.PLAIN, 24));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(6, 58, 796, 42);
		logPanel.add(lblTitle);
		
		lblState = new JLabel("Estado conexion BBDD:");
		lblState.setHorizontalAlignment(SwingConstants.CENTER);
		lblState.setFont(new Font("Arial", Font.PLAIN, 16));
		lblState.setBounds(6, 433, 796, 18);
		logPanel.add(lblState);
		
		btnDescDBLog = new JButton("Desconectar BBDD");
		btnDescDBLog.setBounds(303, 463, 207, 29);
		logPanel.add(btnDescDBLog);
		
		passField = new JPasswordField();
		passField.setBounds(268, 276, 272, 42);
		logPanel.add(passField);
	}
	
	/**
	 * Metodo donde iniciamos el panel del Query una vez ya logueados
	 */
	public void queryView() {
		
		
		setTitle("Realizar consultas");
		queryPanel = new JPanel();
		queryPanel.setBounds(6, 6, 808, 570);
		fatherPane.add(queryPanel);
		queryPanel.setLayout(null);
		
		scrollPaneInfo = new JScrollPane();
		scrollPaneInfo.setBounds(6, 330, 796, 234);
		queryPanel.add(scrollPaneInfo);
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));

        // Personalización de encabezados de columna
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setBackground(Color.LIGHT_GRAY);

        // Personalización de colores de selección
        table.setSelectionForeground(Color.WHITE);
        table.setSelectionBackground(Color.BLUE);
		
		scrollPaneInfo.setViewportView(table);
		
		lblInfoLoged = new JLabel("Usuario logeado: ");
		lblInfoLoged.setBounds(6, 6, 269, 16);
		queryPanel.add(lblInfoLoged);
		
		lblState = new JLabel("Estado conexión BBDD: ");
		lblState.setHorizontalAlignment(SwingConstants.TRAILING);
		lblState.setBounds(533, 6, 269, 16);
		queryPanel.add(lblState);
		
		scrollPanelQuery = new JScrollPane();
		scrollPanelQuery.setBounds(6, 65, 796, 148);
		queryPanel.add(scrollPanelQuery);
		
		textQuery = new JTextPane();
		scrollPanelQuery.setViewportView(textQuery);
		
		lblInfoWQ = new JLabel("Escribe tu consulta:");
		lblInfoWQ.setHorizontalAlignment(SwingConstants.LEFT);
		lblInfoWQ.setBounds(6, 37, 796, 16);
		queryPanel.add(lblInfoWQ);
		
		btnQuery = new JButton("Realizar consulta");
		btnQuery.setBounds(6, 225, 190, 60);
		queryPanel.add(btnQuery);
		
		btnDescDBQ = new JButton("Desconectar DB");
		btnDescDBQ.setBounds(612, 225, 190, 60);
		queryPanel.add(btnDescDBQ);
		
		btnLogout = new JButton("Cerrar sesión");
		btnLogout.setBounds(410, 225, 190, 60);
		queryPanel.add(btnLogout);
		
		JLabel lblNewLabel = new JLabel("Datos consulta (SELECT) realizada");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 297, 796, 21);
		queryPanel.add(lblNewLabel);
	}

	
	
	/**
	 * Generamos todos los Getters que vamos a necesitar para manipular la interfaz.
	 */
	
	/**
	 * Metodo getter label del estado.
	 * @return
	 */
	public JLabel getlblState() {
		return lblState;
	}
	
	
	/**
	 * Metodo get label de la información del login
	 * @return
	 */
	public JLabel getLblInfoLoged() {
		return lblInfoLoged;
	}
	
	/**
	 * Metodo get del boton de Conectar - Desconectar en el panel login.
	 * @return
	 */
	public JButton getbtnDescDBLog() {
		return btnDescDBLog;
	}
	
	/**
	 *  Metodo get del boton de Conectar - Desconectar en el panel del Query una vez ya logeado.
	 * @return
	 */
	public JButton getBtnDescBDQ() {
		return btnDescDBQ;
	}
	
	
	/**
	 * Metodo get del botón de login
	 * @return
	 */
	public JButton getBtnLog() {
		return btnLog;
	}
	
	/**
	 * Metodo get del boton para realizar la consulta
	 * @return
	 */
	public JButton getBtnQuery() {
		return btnQuery;
	}
	
	/**
	 * Metodo get del boton para cerrar sesión
	 * @return
	 */
	public JButton getBtnLogout() {
		return btnLogout;
	}
	
	/**
	 * Metodo get del field del usuario insertado
	 * @return
	 */
	public JTextField getUserField() {
		return userField;
	}
	
	/**
	 * Metodo get field de la contraseña insertada
	 * @return
	 */
	public JTextField getPassField() {
		return passField;
	}
	
	/**
	 * Metodo get del panel donde el usuario escribe la query
	 * @return
	 */
	public JTextPane getTextQuery() {
		return textQuery;
	}
	
	/**
	 * Metodo donde recogemos el panel del login
	 * @return
	 */
	public JPanel getLogPanel() {
		return logPanel;
	}
	
	/**
	 * Metodo dodne recogemos el panel una vez ya logeado
	 * @return
	 */
	public JPanel getQueryPanel() {
		return queryPanel;
	}
	
	/**
	 * Metodo donde cogemos la tabla
	 * @return
	 */
	public JTable getTable() {
		return table;
	}
}
