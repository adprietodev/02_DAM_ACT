package es.florida.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class Main {


	public static void main(String[] args) {

		//SpringApplication.run(Main.class, args);
		
		try {
			System.out.println("Entramos al try");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://84.127.69.12:3306/biblioteca","adprietodev","2Oa+V96i£[)KE;yz");
			System.out.println("Pasamos la conexión");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM titles");
			System.out.println("Antes del while");
			while(rs.next()) {
				System.out.println("Dentro del while");
				System.out.println(rs.getInt(1));
				
				rs.close();
				stmt.close();
				con.close();
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		

	}

}