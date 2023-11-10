package es.florida.ae02;

import org.apache.commons.codec.digest.DigestUtils;
import org.w3c.dom.*;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.*;
import java.io.*;
import java.sql.*;

public class Model {

	DataCon dataCon;
	String userLog;

	public Model() {
		this.dataCon = new DataCon("", "", "");
		this.userLog = "";
	}

	/**
	 * Metodo que utilziamos para extraer los datos necesarios del xml correspondiente
	 * @param nameFile nombre del archivo xml
	 */
	public void getDataXMLCon(String nameFile) {

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new File(nameFile));

			NodeList nList = doc.getElementsByTagName("conection");

			if (nList.getLength() > 0) {
				Element eElement = (Element) nList.item(0);

				dataCon.setUrl(eElement.getElementsByTagName("url").item(0).getTextContent());
				dataCon.setUser(eElement.getElementsByTagName("user").item(0).getTextContent());
				dataCon.setPass(eElement.getElementsByTagName("password").item(0).getTextContent());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Metodo que utilizamos para conectarnos a la base de datos
	 * @return retornamos la conexión para utilizarla en otros metodos.
	 */
	public Connection connectDB() {
		Connection con = null;
		try {
			// System.out.println(dataCon.getUrl()+" "+dataCon.getUser()+"
			// "+dataCon.getPass());
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dataCon.getUrl(), dataCon.getUser(), dataCon.getPass());
			dataCon.setState(true);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * Metodo donde identificamos que tipo de query es para realizar un statement o otro
	 * @param view en el caso del select necesitamos pasarle el VIEW para mas adelante generar la tabla.
	 */
	public void userQuery(View view) {
		String[] arrQuery = view.getTextQuery().getText().split(" ");
		String queryUser = view.getTextQuery().getText();


			if (arrQuery[0].toUpperCase().equals("SELECT")) {
				querySelect(queryUser, view);
				
			} else if (confirmationDialog("¿Estas realizar esta modificación en la base de datos?", "Confirmación",
					JOptionPane.QUESTION_MESSAGE) == 0) {
				query(queryUser, arrQuery[0]);
			}
		
	}

	/**
	 * Metodo que utilizamos para desconectarnos de la base de datos.
	 * @throws SQLException arrojamos el error de SQL en caso de haberlo
	 */
	public void closeCon() throws SQLException {
		connectDB().close();
		dataCon.setState(false);
	}

	/**
	 * Metodo al que llamamos para cambiar de tipo de conexión con la base de datos en caso de que seas cliente o administrador
	 * @param nameType le pasamos el nombre del tipo de conexión que es
	 * @throws SQLException arrojamos el error de SQL en caso de haberlo
	 */
	public void changeCon(String nameType) throws SQLException {
		closeCon();
		getDataXMLCon(nameType + ".xml");
		connectDB();
	}

	/**
	 * Metodo que llamamos en caso de que sea la query tipo Select
	 * @param queryUser le pasamos la query escrita por el usuario
	 * @param view le pasamos el view generar la tabla con los datos que nos proporcione el select.
	 */
	private void querySelect(String queryUser, View view) {

		try {
			Connection con = connectDB();
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(queryUser);

			int numRow = getRow(rs);
			rs = stmt.executeQuery(queryUser);
			int numCol = getCountCol(rs);

			Object[][] cTable = new Object[numRow][numCol];
			rs = stmt.executeQuery(queryUser);
			String[] namesColumns = getColumnName(rs);

			rs = stmt.executeQuery(queryUser);
			int iniRow = 0;
			while (rs.next()) {
				for (int i = 1; i <= numCol; i++) {
					String data = rs.getString(i);
					cTable[iniRow][i - 1] = data;
				}
				iniRow++;
			}

			view.getTable().setModel(new DefaultTableModel(cTable, namesColumns));

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			messDialog("Codigo de error :" + e.getErrorCode(), "Error SELECT", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Metodo al que llamaremos en caso de que no sea tipo SELECT la query para realizar cambios en la base de datos.
	 * @param queryUser le pasamos la consulta escrita por el usuario
	 * @param queryUsed Identificamos cual es la primera palabra escrita para saber el tipo de query y mostrarlo en mensajes posteriores.
	 */
	private void query(String queryUser, String queryUsed) {

		try {

			PreparedStatement psQueryUser = connectDB().prepareStatement(queryUser);

			int resultPS = psQueryUser.executeUpdate();

			if (resultPS == 0) {
				messDialog(
						"Las lineas modificadas con " + queryUsed
								+ " han sido 0, en caso de que la ejecución\n no deba devolver una linea como con:\n INSERT, UPDATE o DELETE\n La ejecución es correcta no debes de preocuparte, en caso contrario revisa la consulta.",
						"Información " + queryUsed, JOptionPane.WARNING_MESSAGE);
			} else {
				messDialog("Consulta realizada correctamente, total de lineas modificadas " + resultPS,
						"Correct " + queryUsed, JOptionPane.INFORMATION_MESSAGE);
			}
			psQueryUser.close();

		} catch (SQLException e) {

			if (e.getErrorCode() == 1142) {
				messDialog("No tienes los permisos suficientes para ejecutar esta sentencia SQL.\nCodigo de error :"
						+ e.getErrorCode(), "Error " + queryUsed, JOptionPane.ERROR_MESSAGE);
			} else {
				messDialog("Error al ejecutar la sentencia de SQL.\nCodigo de error :" + e.getErrorCode(),
						"Error " + queryUsed, JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	/**
	 * Metodo que revisamos si el tipo de conexión en la base de datos con el usuario es correcto.
	 * @param insertUser Le pasamos el usuario escrito
	 * @return Si el tipo del usuario introducido es igual al que esta iniciado en la base de datos devolvera true
	 */
	public boolean checkCorrectType(String insertUser) {

		if (dataCon.isState()) {
			String currentType = dataCon.getUser();
			String typeSelected = getTypeUserDB(insertUser);

			if (!typeSelected.equals("admin") && !typeSelected.equals("client")) {
				return false;
			} else {
				if (currentType.equals(typeSelected)) {
					return true;
				} else {
					try {
						changeCon(typeSelected);
					} catch (SQLException e) {
						e.toString();
					}
					return true;
				}
			}
		} else {
			return false;
		}
	}

	/**
	 * Metodo que utilizamos para revisar que la contraseña sea correcta
	 * @param user pasamos el usuario
	 * @param password la contraseña escrita
	 * @return si es coinciden retornamos true
	 */
	public boolean checkPassword(String user, String password) {

		String passHashed = DigestUtils.md5Hex(password);
		boolean valid = false;

		if (dataCon.isState()) {
			try {
				Statement stmt = connectDB().createStatement();

				ResultSet rs = stmt.executeQuery("SELECT user,pass FROM users");

				while (rs.next()) {
					if (user.equals(rs.getString(1)) && passHashed.equals(rs.getString(2))) {
						valid = true;
						break;
					}
				}

				rs.close();
				stmt.close();

			} catch (Exception e) {
				e.toString();
			}
		}

		return valid;
	}

	/**
	 * Metodo en el que comprobamos si estamos conectados en la base de datos y realizamos los cambios pertinentes en la interfaz
	 * @param view le pasamos view para realizar cambios
	 * @return retornamos el estado de la base de datos. conectado = true
	 */
	public boolean checkState(View view) {

		if (view.getTitle().equals("Inicio de sesión")) {
			if (dataCon.isState()) {
				view.getlblState().setText("Estado conexion BBDD: Activa");
				view.getbtnDescDBLog().setText("Desconectar DB");
			} else {
				view.getlblState().setText("Estado conexion BBDD: Desactivada");
				view.getbtnDescDBLog().setText("Conectar DB");
			}
		}

		if (view.getTitle().equals("Realizar consultas")) {
			if (dataCon.isState()) {
				view.getlblState().setText("Estado conexion BBDD: Activa");
				view.getBtnDescBDQ().setText("Desconectar DB");
			} else {
				view.getlblState().setText("Estado conexion BBDD: Desactivada");
				view.getBtnDescBDQ().setText("Conectar DB");
			}
		}

		return dataCon.isState();
	}

	/**
	 * Metodo donde cogemos el tipo del usuario que hemos insertado en el field. administrador1 devolvera admin
	 * @param textUserField le pasamos el usuario escrito en field
	 * @return retornamos el tipo al que pertenece.
	 */
	public String getTypeUserDB(String textUserField) {
		String type = "";
		try {
			Statement stmt = connectDB().createStatement();

			ResultSet rs = stmt.executeQuery("SELECT user,type FROM users");

			while (rs.next()) {
				if (textUserField.toLowerCase().equals(rs.getString(1).toLowerCase())) {
					userLog = rs.getString(1).toLowerCase();
					type = rs.getString(2);
					break;
				}
			}

			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.toString();
		}

		return type.toLowerCase();

	}

	/**
	 * Metodo que llamamos para saber la cantidad de columnas que tiene la tabla.
	 * @param rs le pasamso el ResultSet al que hayamos llamado
	 * @return retornamos un numero entero con la cantidad de columnas
	 */
	public int getCountCol(ResultSet rs) {
		int columnCount = 0;
		try {
			ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
			columnCount = metaData.getColumnCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return columnCount;

	}

	
	/**
	 * Metodo que llamamos para coger el nombre de las columnas
	 * @param rs le pasamso el ResultSet al que hayamos llamado
	 * @return retornamos una array tipo string con esos nombres cogidos.
	 */
	public String[] getColumnName(ResultSet rs) {

		int columCount = getCountCol(rs);
		String[] namesColumns = new String[columCount];

		try {
			ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
			for (int i = 1; i <= columCount; i++) {
				String nameC = metaData.getColumnName(i);
				namesColumns[i - 1] = nameC.toUpperCase();
			}
		} catch (SQLException e) {
			messDialog("Error al generar la tabla " + e.getMessage(), "Error Table", JOptionPane.ERROR_MESSAGE);
		}
		return namesColumns;
	}

	/**
	 * Metodo donde recogemos la cantidad de filas que tiene la tabal
	 * @param rs le pasamso el ResultSet al que hayamos llamado
	 * @return retornamos un numero entero con la cantidad de filas.
	 */
	public int getRow(ResultSet rs) {
		int count = 0;

		try {
			while (rs.next()) {
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

	/**
	 * Metodo que utilizamos para mostrar un mensaje de confirmación
	 * @param mess mensaje que queremos mostrar
	 * @param titleMess titulo del mensaje
	 * @param num numero de tipo de mensaje 
	 * @return retornamos la elección del usuario
	 */
	public int confirmationDialog(String mess, String titleMess, int num) {
		return JOptionPane.showConfirmDialog(null, mess, titleMess, JOptionPane.YES_NO_OPTION, num);
	}

	/**
	 * Metodo que utilziamos para mostrar mensajes informativos
	 * @param mess mensaje que queremos mostrar
	 * @param titleMess titulo del mensaje
	 * @param num numero de tipo de mensaje.
	 */
	public void messDialog(String mess, String titleMess, int num) {
		JOptionPane.showMessageDialog(null, mess, titleMess, num);
	}

	/**
	 * Información de la conexión.
	 */
	public class DataCon {

		private String url;
		private String user;
		private String pass;
		private boolean state;

		public DataCon(String url, String user, String pass) {
			this.user = user;
			this.url = url;
			this.pass = pass;
			this.state = false;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public String getPass() {
			return pass;
		}

		public void setPass(String pass) {
			this.pass = pass;
		}

		public boolean isState() {
			return state;
		}

		public void setState(boolean state) {
			this.state = state;
		}
	}

}

//SELECT * FROM authors;
//INSERT INTO titles VALUES (NULL,'Elantris',53,2005,12,794);
//INSERT INTO titles VALUES (NULL,'El imperio final',53,2006,12,541);
//UPDATE titles SET pages=500 WHERE pages < '200';
//DELETE FROM titles WHERE author=53;
//ALTER TABLE authors ADD COLUMN Prueba_err VARCHAR(20);
