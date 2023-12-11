package es.florida.hibernate;

import java.io.Serializable;
import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Principal {

	public static void main(String[] args) {
		
		// Carga la configuracion y crea un session factory
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Cancion.class);
		ServiceRegistry registry = new
		StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		
		// Abre una nueva session de la session factory
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		
		//Aquí la/s operacio/nes CRUD (crear, leer, actualizar, borrar)
		
		//Crear nuevo objeto
		Cancion can = new Cancion("Cornfield chase", "Hans Zimmer", 2014, "RAW");
		Serializable id = session.save(can);
		
		//Commit de la transacción y cierra sesión
		session.getTransaction().commit();
		session.close();

	}

}
