package es.florida.ejT3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class App {

	private boolean connection = true;
	private Scanner kB = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		App app = new App();

		// Carga la configuracion y crea un session factory
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Libro.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
				.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);

		System.out.println("------------------------------------------------");
		System.out.println("Bienvenido a la biblioteca nacional");
		System.out.println("------------------------------------------------");

		while (app.connection) {
			// Abre una nueva session de la session factory
			Session session = sessionFactory.openSession();
			String numString = app.selectAction();
			if (app.isNumber(numString)) {
				int num = Integer.parseInt(numString);
				switch (num) {
				case 1:
					app.showBooks(session);
					break;
				case 2:
					app.getInfoBook(session);
					break;
				case 3:
					app.addBook(session);
					break;
				case 4:
					app.modifyBook(session);
					break;
				case 5:
					app.deleteBook(session);
					break;
				case 6:
					System.out.println("-------------------------------------------------------------");
					System.out.println("Gracias por usar nuestra biblioteca nacional ¡Hasta pronto!");
					System.out.println("-------------------------------------------------------------");
					app.connection = false;
					break;
				default:
					System.err.println(
							"No has seleccionado ninguna de las acciones anteriores, por favor elige una opción del 1 al 6.");
					break;
				}
			} else {
				System.err.println("El numero insertado no es valido.");
			}
		}

		// Commit de la transacción y cierra sesión

	}

	private String selectAction() {

		System.out.println("======================================================");
		System.out.println("¿Que acción quieres realizar?");
		System.out.println("======================================================");
		System.out.println("1.- Mostrar todos los titulos.");
		System.out.println("2.- Información detallada de un libro.");
		System.out.println("3.- Añadir un nuevo libro en la biblioteca");
		System.out.println("4.- Modificar datos de un libro.");
		System.out.println("5.- Borrar un libro de la biblioteca.");
		System.out.println("6.- Salir");
		System.out.println("======================================================");
		String option = kB.nextLine();

		return option;
	}

	private boolean isNumber(String num) {
		try {
			Integer.parseInt(num);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private void showBooks(Session session) {
		session.beginTransaction();
		List<Libro> listaLibros = new ArrayList();
		listaLibros = session.createQuery("FROM Libro").list();

		for (Libro libro : listaLibros) {
			System.out.println(libro.getIdLibros() + " - " + libro.getTitul());
		}

		session.getTransaction().commit();
		session.close();
	}

	private void getInfoBook(Session session) {
		session.beginTransaction();
		boolean ok = false;

		while (!ok) {
			String idString = null;
			System.out.println("======================================================");
			System.out.println("¿Que libro quieres que te mostremos?(ID)");
			System.out.println("======================================================");

			while (!isNumber(idString)) {
				idString = kB.nextLine();
			}

			int id = Integer.parseInt(idString);

			Libro libro = (Libro) session.get(Libro.class, id);
			if (libro != null) {
				System.out.println("======================================================");
				System.out.println("Información libro seleccionado");
				System.out.println("======================================================");
				System.out.println("ID: " + libro.getIdLibros());
				System.out.println("Titulo: " + libro.getTitul());
				System.out.println("Autor: " + libro.getAutor());
				System.out.println("Año de nacimiento: " + libro.getAnyoNacimiento());
				System.out.println("Año de publicación: " + libro.getAnyoPublicacion());
				System.out.println("Editorial: " + libro.getEditorial());
				System.out.println("Numero de paginas: " + libro.getNumeroDePaginas());
				ok = true;
			} else {
				System.err.println("Esa ID no existe dentro de la base de datos, por favor selecciona una valida.");
			}

		}
		session.getTransaction().commit();
		session.close();

	}

	private void addBook(Session session) {
		session.beginTransaction();
		System.out.println("======================================================");
		System.out.println("Información del libro que quieres añadir");
		System.out.println("======================================================");
		System.out.println("Indicame el titulo: ");
		String titulo = kB.nextLine();
		System.out.println("Indicame el autor: ");
		String autor = kB.nextLine();
		System.out.println("Indicame el año de nacimiento: ");
		int anyoNac = Integer.parseInt(kB.nextLine());
		System.out.println("Indicame el año de publicación: ");
		int anyoPub = Integer.parseInt(kB.nextLine());
		System.out.println("Indicame la editorial: ");
		String editorial = kB.nextLine();
		System.out.println("Indicame el numero de paginas: ");
		int numPages = Integer.parseInt(kB.nextLine());

		Libro lib = new Libro(titulo, autor, anyoNac, anyoPub, editorial, numPages);
		Serializable id = session.save(lib);

		session.getTransaction().commit();

		System.out.println("======================================================");
		System.out.println("Libro añadido correctamente");
		System.out.println("======================================================");
	}

	private void modifyBook(Session session) {
		session.beginTransaction();
		boolean ok = false;
		int id = 0;

		String idString = null;
		System.out.println("======================================================");
		System.out.println("¿Que libro quieres modificar?(ID)");
		System.out.println("======================================================");

		while (!ok) {
			while (!isNumber(idString)) {
				idString = kB.nextLine();
				if (!isNumber(idString)) {
					System.err.println("Esa ID no es valida, por favor repite de nuevo la acción.");
				}
			}

			id = Integer.parseInt(idString);

			Libro libro = (Libro) session.get(Libro.class, id);
			boolean modifying = true;

			while (modifying) {
				if (libro != null) {
					System.out.println("======================================================");
					System.out.println("¿Que quieres modificar?");
					System.out.println("======================================================");
					System.out.println("1.- Titulo");
					System.out.println("2.- Autor");
					System.out.println("3.- Año de nacimiento");
					System.out.println("4.- Año de publicación");
					System.out.println("5.- Editorial");
					System.out.println("6.- Numero de paginas");
					System.out.println("7.- Cancelar.");

					String res = kB.nextLine();

					if (isNumber(res)) {

						switch (Integer.parseInt(res)) {
						case 1:
							System.out.println("¿Que titulo quieres poner?");
							String titulo = kB.nextLine();
							libro.setTitul(titulo);
							break;
						case 2:
							System.out.println("¿Que autor quieres poner?");
							String autor = kB.nextLine();
							libro.setAutor(autor);
							break;
						case 3:
							System.out.println("¿Que año de nacimiento quieres poner?");
							String anyoNac = kB.nextLine();
							if (isNumber(anyoNac)) {
								libro.setAnyoNacimiento(Integer.parseInt(anyoNac));
							} else {
								System.err.println(
										"No has no has facilitado un numero como año de nacimiento, por lo que no se realizara la modificación");
							}
							break;
						case 4:
							System.out.println("¿Que año de publicación quieres poner?");
							String anyoPubl = kB.nextLine();
							if (isNumber(anyoPubl)) {
								libro.setAnyoPublicacion(Integer.parseInt(anyoPubl));
							} else {
								System.err.println(
										"No has no has facilitado un numero como año de publicación, por lo que no se realizara la modificación");
							}
							break;
						case 5:
							System.out.println("¿Que editorial quieres poner?");
							String editorial = kB.nextLine();
							libro.setEditorial(editorial);
							;
							break;
						case 6:
							System.out.println("¿Que numero de paginas quieres poner?");
							String numPag = kB.nextLine();
							if (isNumber(numPag)) {
								libro.setNumeroDePaginas(Integer.parseInt(numPag));
							} else {
								System.err.println(
										"No has no has facilitado un numero de paginas valido, por lo que no se realizara la modificación");
							}
							break;
						case 7:
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
							} else {
								System.err.println(
										"No has seleccionado la opción s(SI) o n(NO), por favor seleciona una de estas dos opciones.");
							}
						}

					} else {
						System.err.println("El numero insertado no es valido.");
					}

				} else {
					System.err.println("Esa ID no existe dentro de la base de datos, por favor selecciona una valida.");
				}
			}
			session.update(libro);

		}

		session.getTransaction().commit();
	}

	private void deleteBook(Session session) {
		session.beginTransaction();
		boolean ok = false;
		int id = 0;

		String idString = null;
		System.out.println("======================================================");
		System.out.println("¿Que libro quieres eliminar?(ID)");
		System.out.println("======================================================");

		try {
			while (!isNumber(idString)) {
				idString = kB.nextLine();
				if (!isNumber(idString)) {
					System.err.println("Esa ID no es valida, por favor repite de nuevo la acción.");
				}
			}

			id = Integer.parseInt(idString);
			Libro libro = new Libro();
			libro.setIdLibros(id);
			session.delete(libro);

			session.getTransaction().commit();
			//session.clear(); - no es necesario cerrar sesión.

		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("La ID facilitada no esta en la base de datos");
		}

		session.close();
	}

}

//-------- Ejercicios - PUNTOS --------
//Punto 4
//List<Libro> listaLibros =  new ArrayList();
//listaLibros = session.createQuery("FROM Libro").list();
//
//for(Libro libro : listaLibros) {
//	System.out.println(libro.getIdLibros()+" - "+libro.getTitul()+" - "+libro.getAutor()+" - "+libro.getAnyoNacimiento()+" - "+libro.getAnyoPublicacion()+" - "+libro.getEditorial()+" - "+libro.getNumeroDePaginas());
//}

//Punto5
//Libro lib = new Libro("Elantris","Brandon Sanderson",1975,2019,"MAXI",1200);
//Serializable id = session.save(lib);

//Punto6
//Libro libro = (Libro) session.get(Libro.class, 15);
//libro.setNumeroDePaginas(900);
//session.update(libro);

//Punto7
//Libro libro = new Libro();
//libro.setIdLibros(15);
//session.delete(libro);
