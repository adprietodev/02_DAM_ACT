package es.florida.main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * List<Procesador> procesadores = new ArrayList<>(); procesadores.add(new
		 * Procesador("Intel Core i7",450,435,"Si")); procesadores.add(new
		 * Procesador("Intel Core i5",350,299,"Si")); procesadores.add(new
		 * Procesador("Intel Core i3",150,120,"No")); procesadores.add(new
		 * Procesador("AMD Ryzen 7",390,375,"Si"));
		 */

		// Carga la configuracion y crea un session factory
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Procesador.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
				.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);

		// Abre una nueva session de la session factory
		Session session = sessionFactory.openSession();

		// Aquí la/s operacio/nes CRUD (crear, leer, actualizar, borrar)

		// Crear nuevo objeto

		Serializable id;

		/*
		 * for(Procesador procesador : procesadores) { session.beginTransaction(); id =
		 * session.save(procesador); session.getTransaction().commit(); }
		 */

		getInfoProcesadores(session);
		modifyProcesador(session);
		deleteNoDiponibilidad(session);

		// Commit de la transacción y cierra sesión

		session.close();
		

	}

	private static void getInfoProcesadores(Session session) {
		session.beginTransaction();

		List<Procesador> procesadores = new ArrayList<>();
		procesadores = session.createQuery("FROM Procesador").list();

		for (Procesador procesador : procesadores) {
			System.out.println(procesador.getId() + " - " + procesador.getModelo() + " - " + procesador.getPrecio()
					+ " - " + procesador.getPrecioOferta() + " - " + procesador.getDisponibilidad());
		}

		session.getTransaction().commit();

	}

	private static void modifyProcesador(Session session) {
		Scanner kB = new Scanner(System.in);
		session.beginTransaction();
		boolean ok = false;
		int id = 0;

		String idString = null;
		System.out.println("======================================================");
		System.out.println("¿Que procesado quieres modificar?(ID)");
		System.out.println("======================================================");

		while (!ok) {

			idString = kB.nextLine();
			id = Integer.parseInt(idString);

			Procesador procesador = session.get(Procesador.class, id);
			boolean modifying = true;

			while (modifying) {
				if (procesador != null) {
					System.out.println("======================================================");
					System.out.println("¿Que quieres modificar?");
					System.out.println("======================================================");
					System.out.println("1.- Modelo");
					System.out.println("2.- Precio");
					System.out.println("3.- Precio de Oferta");
					System.out.println("4.- Disponibilidad");
					System.out.println("5.- Cancelar.");

					String res = kB.nextLine();

					switch (Integer.parseInt(res)) {
					case 1:
						System.out.println("¿Que modelo quieres poner?");
						String modelo = kB.nextLine();
						procesador.setModelo(modelo);
						break;
					case 2:
						System.out.println("¿Que precio quieres poner?");
						int precio = Integer.parseInt(kB.nextLine());
						procesador.setPrecio(precio);
						break;
					case 3:
						System.out.println("¿Que precio oferta quieres poner?");
						int precioOferta = Integer.parseInt(kB.nextLine());
						procesador.setPrecioOferta(precioOferta);
						break;
					case 4:
						System.out.println("¿Hay disponibilidad?");
						String disponibilidad = kB.nextLine();
						procesador.setDisponibilidad(disponibilidad);
						break;
					case 5:
						modifying = false;
						ok = true;
						break;
					default:
						System.err.println(
								"No has seleccionado ninguna de las acciones anteriores, por favor elige una opción del 1 al 7.");
						break;
					}
					boolean correctSelect = false;

					while (!correctSelect) {
						System.out.println("¿Quieres modificar algo mas?(s/n)");
						String confirm = kB.nextLine();
						if (confirm.toLowerCase().equals("s")) {
							correctSelect = true;
						} else if (confirm.toLowerCase().equals("n")) {
							correctSelect = true;
							modifying = false;
							ok = true;
						}
					}


				}
				
				if(!modifying) {
					System.out.println(procesador.getId() + " - " + procesador.getModelo() + " - " + procesador.getPrecio()
					+ " - " + procesador.getPrecioOferta() + " - " + procesador.getDisponibilidad());
					session.update(procesador);
					session.getTransaction().commit();
				}
				
			}
		}
	}
	
	private static void deleteNoDiponibilidad(Session session) {
		session.beginTransaction();
		try {

			Query queryObject = session.createQuery("DELETE FROM Procesador WHERE disponibilidad='No'");
			queryObject.executeUpdate();

			session.getTransaction().commit();

		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("La ID facilitada no esta en la base de datos");
		}
	}
}
