package es.florida.t2mp;

import java.io.File;
import java.sql.*;
import java.util.*;

public class Principal {

	private OpenCSV op;
	private SQL dbSQL;

	private Scanner keyB;
	private String acction;

	public Principal() {

		this.op = new OpenCSV();
		this.dbSQL = new SQL();

		this.keyB = new Scanner(System.in);
		this.acction = "";
	}

	public static void main(String[] args) {

		Principal p = new Principal();

		do {
			System.out
					.println("¿Quieres crear una base de datos o consultar una base de datos?(crear/consultar/salir)");
			p.acction = p.keyB.nextLine();

			if (p.acction.toLowerCase().equals("crear")) {

				if (p.createDB()) {
					System.out.println("La base de datos se ha creado correctamente.");
				} else {
					System.out.println("La base de datos no se ha creado.");
				}
			}

			if (p.acction.toLowerCase().equals("consultar")) {
				String nameDB = "";
				do {
					System.out.println("Las bases de datos disponibles son: " + p.dbSQL.showDB());
					System.out.println("Indicame el nombre de la base de datos ");
					nameDB = p.keyB.nextLine();

					if (p.dbSQL.checkDBExist(nameDB)) {
						System.out.println("Las tablas disponibles en " + nameDB + " son: " + p.dbSQL.showTables(nameDB));
						System.out.println("Indicame el la consulta que quieres hacer a " + nameDB);
						String query = p.keyB.nextLine();
						p.dbSQL.querySQL(nameDB, query);
					}else {
						System.out.println("La base de datos indicada aanteriormente no esta en las indicadas, por favor selecciona una valida, recuerda que el nombre debe ser el mismo.\n\n");
					}
				}while(!p.dbSQL.checkDBExist(nameDB));
			}

		} while (!p.acction.toLowerCase().equals("salir"));

	}

	public boolean createDB() {

		System.out.println(
				"Para crear una base de datos necesito que me indiques que archivo .csv quieres que leeamos para introducir los datos. El nombre de la tabla se definira ppor el nombre del csv. (NOMBRE_ARCHIVO.csv)");
		String nameCSV = keyB.nextLine();
		//System.out.println(nameCSV);
		String[] docSplit = nameCSV.split("\\.");
		System.out.println("¿Que nombre quieres ponerle a la base de datos? ");
		String nameDB = keyB.nextLine();

		if (new File(nameCSV).exists()) {
			op.readCSV(nameCSV);
		}
		
		String nameTable = docSplit[0];
		boolean create = false;

		do {

			if (!dbSQL.checkDBExist(nameDB)) {
				if (!dbSQL.checkTableExist(nameDB, nameTable)) {
					dbSQL.createDB(nameDB, nameTable, op);
					create = true;
				} else {
					System.out.println("La tabla " + nameTable + " ya existe dento de" + nameDB
							+ "¿Quieres ponerle otro nombre? (s/n)");
					String res = keyB.nextLine();

					if (res.toLowerCase().equals("s")) {
						System.out.println("¿Que nombre quieres ponerle?");
						nameTable = keyB.nextLine();
					} else {
						break;
					}
				}
			} else {
				System.out.println("La base de datos " + nameDB + " ya existe ¿Quieres ponerle otro nombre? (s/n)");
				String res = keyB.nextLine();

				if (res.toLowerCase().equals("s")) {
					System.out.println("¿Que nombre quieres ponerle?");
					nameDB = keyB.nextLine();
				} else {
					break;
				}
			}

		} while (!create);

		return create;
	}

}
