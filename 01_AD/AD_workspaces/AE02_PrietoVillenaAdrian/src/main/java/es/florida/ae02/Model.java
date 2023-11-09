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

	public void closeCon() throws SQLException {
		connectDB().close();
		dataCon.setState(false);
	}

	public void changeCon(String nameType) throws SQLException {
		closeCon();
		getDataXMLCon(nameType + ".xml");
		connectDB();
	}

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

	public int confirmationDialog(String mess, String titleMess, int num) {
		return JOptionPane.showConfirmDialog(null, mess, titleMess, JOptionPane.YES_NO_OPTION, num);
	}

	public void messDialog(String mess, String titleMess, int num) {
		JOptionPane.showMessageDialog(null, mess, titleMess, num);
	}

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
