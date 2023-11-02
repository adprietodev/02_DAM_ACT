package es.florida.t2mp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class SQL {

	private OpenCSV op ;
	
	public SQL() {
		op = new OpenCSV();
	}
	
	public void createDB(String nameDB, String nameTable, OpenCSV list) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
			Statement stmt = con.createStatement();
			
			String createDB = "CREATE DATABASE "+nameDB+";";
			System.out.println(createDB);
			stmt.executeUpdate(createDB);
			
			stmt.close();
			con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		createTable(nameDB,nameTable,list);
	}
	
	public void createTable(String nameDB, String nameTable, OpenCSV list) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+nameDB, "root", "");
			Statement stmt = con.createStatement();
			
			String createQuery = "CREATE TABLE "+nameTable.toUpperCase()+" (ID_"+nameTable.toUpperCase()+" INT AUTO_INCREMENT PRIMARY KEY, "+list.titulos.get(0).toUpperCase()+" VARCHAR(100), "+list.autor.get(0).toUpperCase()+" VARCHAR(100), "+list.anyoNacimiento.get(0).toUpperCase()+" INT(4), "+list.anyoPublicacion.get(0).toUpperCase()+" INT(4), "+list.editorial.get(0).toUpperCase()+" VARCHAR(50), "+list.numPaginas.get(0).toUpperCase()+" INT(5));";
			System.out.println(createQuery);
			stmt.executeUpdate(createQuery);
			
			
			stmt.close();
			con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		insertDataTable(nameDB,nameTable,list);
	}
	
	public void insertDataTable(String nameDB, String nameTable, OpenCSV list) {
		
		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+nameDB, "root", "");
		    
		    String insertQuery = "INSERT INTO " + nameTable + " (ID_" + nameTable.toUpperCase() + ", " +list.titulos.get(0).toUpperCase() + ", " +list.autor.get(0).toUpperCase() + ", " +list.anyoNacimiento.get(0).toUpperCase() + ", " +list.anyoPublicacion.get(0).toUpperCase() + ", " +list.editorial.get(0).toUpperCase() + ", " +list.numPaginas.get(0).toUpperCase() + ") VALUES (NULL, ?, ?, ?, ?, ?, ?)";
		    
		    PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
		    
		    for (int i = 1; i < list.titulos.size(); i++) {

		        preparedStatement.setString(1, list.titulos.get(i));
		        preparedStatement.setString(2, list.autor.get(i));
		        preparedStatement.setInt(3, Integer.parseInt(list.anyoNacimiento.get(i)));
		        preparedStatement.setInt(4, Integer.parseInt(list.anyoPublicacion.get(i)));
		        preparedStatement.setString(5, list.editorial.get(i));
		        preparedStatement.setInt(6, Integer.parseInt(list.numPaginas.get(i)));
		        
		        preparedStatement.executeUpdate();
		    }

		    preparedStatement.close();
		    con.close();
		    
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	public void querySQL(String nameDB, String query) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+nameDB, "root", "");
			Statement stmt = con.createStatement();
			
			String dbExistQuery = query;
			
			ResultSet rS = stmt.executeQuery(dbExistQuery);
			
			ResultSetMetaData metaData = (ResultSetMetaData) rS.getMetaData();
			int columnCount = metaData.getColumnCount();
			
			while(rS.next()) {
				String completRow = "";
				for(int i = 0; i < columnCount; i++) {
					String dbQuery = rS.getString(i);
					completRow += dbQuery+" ";
				}
				System.out.println(completRow);
			}
			
			rS.close();
			stmt.close();
			con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String showDB() {
		String databases = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
			Statement stmt = con.createStatement();
			
			String dbExistQuery = "SHOW DATABASES;";
			
			ResultSet rS = stmt.executeQuery(dbExistQuery);
			
			while (rS.next()) {
				databases += rS.getString(1)+" ";
			}
			rS.close();
			stmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return databases;
	}
	
	public String showTables(String nameDB) {
		String tables = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+nameDB, "root", "");
			Statement stmt = con.createStatement();
			
			String dbExistQuery = "SHOW TABLES FROM "+nameDB+";";
			
			ResultSet rS = stmt.executeQuery(dbExistQuery);
			
			while (rS.next()) {
				tables += rS.getString(1)+" ";
			}
			rS.close();
			stmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tables;
		
	}
	
	public boolean checkDBExist(String nameDB){
		boolean exist = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
			Statement stmt = con.createStatement();
			
			String dbExistQuery = "SELECT 1 FROM information_schema.schemata WHERE schema_name = '"+nameDB+"'";
			
			ResultSet rS = stmt.executeQuery(dbExistQuery);
			
			
			if(rS.next()) {
				exist = true;
			} else {
				exist = false;
			}
			rS.close();
			stmt.close();
			con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exist;
		
	}
	
	public boolean checkTableExist(String nameDB,String nameTable){
		boolean exist = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
			Statement stmt = con.createStatement();
			
			String dbExistQuery = "SELECT TABLE_NAME\n"
					+ "FROM INFORMATION_SCHEMA.TABLES\n"
					+ "WHERE TABLE_SCHEMA = '"+nameDB+"'\n"
					+ "  AND TABLE_NAME = '"+nameTable+"';";
			
			
			ResultSet rS = stmt.executeQuery(dbExistQuery);
			
			if(rS.next()) {
				exist = true;
			} else {
				exist = false;
			}
			rS.close();
			stmt.close();
			con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exist;
		
	}
	
}


//for(int i= 1; i < op.titulos.size();i++) {
//System.out.println(op.titulos.get(i)+" - "+op.autor.get(i)+" - "+op.anyoNacimiento.get(i)+" - "+op.anyoPublicacion.get(i)+" - "+op.editorial.get(i)+" - "+op.numPaginas.get(i));
//System.out.println("---------------------------------------------------------------------------");
//}
//for(String titulo : op.titulos) {
//System.out.println(titulo);
//}
//try {
//Class.forName("com.mysql.cj.jdbc.Driver");
//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "");
//Statement stmt = con.createStatement();
//ResultSet rs = stmt.executeQuery("SELECT * FROM country");
//while (rs.next()) {
//	System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + ""
//			+ rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7) + " "
//			+ rs.getString(8) + "" + rs.getString(9) + " " + rs.getString(10) + " " + rs.getString(11) + " "
//			+ rs.getString(12) + " " + rs.getString(13) + "" + rs.getString(14));
//}
//rs.close();
//stmt.close();
//con.close();
//} catch (Exception e) {
//
//}