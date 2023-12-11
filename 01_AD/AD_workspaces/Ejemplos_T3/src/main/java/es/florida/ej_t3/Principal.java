package es.florida.ej_t3;

import java.io.*;
import java.util.*;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;
import org.hibernate.service.*;

import com.mysql.cj.xdevapi.SessionFactory;

public class Principal {
	
	public static void main(String[] args) {
		
		// Carga la configuracion y crea un session factory
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Cancion.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = (SessionFactory) configuration.buildSessionFactory(registry);
		
		// Abre una nueva session de la session factory
		Session session = ((org.hibernate.SessionFactory) sessionFactory).openSession();
		session.beginTransaction();
		
		//Aquí la/s operacio/nes CRUD (crear, leer, actualizar, borrar)
		//Commit de la transacción y cierra sesión
		session.getTransaction().commit();
		
		
		//Crear nuevo objeto
		Cancion can = new Cancion("Cornfield chase", "Hans Zimmer", 2014, "RAW");
		Serializable id = session.save(can);
		
		session.close();
		
		//Recuperar lista de objetos
//		List listaCanciones = new ArrayList();
//		listaCanciones = session.createQuery("FROM Cancion").list();
		
		//Recuperar un objeto a partir de su id
		//Cancion cancion = (Cancion) session.get(Cancion.class, 1);
		
		// Actualiza la información de un objeto dado su id
//		Cancion cancion = (Cancion) session.load(Cancion.class, 5);
//		cancion.setYear(2010);
//		cancion.setFormat("MP3");
//		session.update(cancion);
		
		//Borrar un objeto dado su id
//		Cancion cancion = new Cancion();
//		cancion.setId(5);
//		session.delete(cancion);
		
		//Borrar todo
//		Query queryObject = session.createQuery("DELETE FROM canciones");
//		queryObject.executeUpdate();
	}
	
}
