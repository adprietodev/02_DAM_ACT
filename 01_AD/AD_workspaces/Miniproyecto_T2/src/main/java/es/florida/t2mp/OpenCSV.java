package es.florida.t2mp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

public class OpenCSV {
	
	List<String[]> listArr;
	
	List<String> titulos;
	List<String> autor;
	List<String> anyoNacimiento;
	List<String> anyoPublicacion;
	List<String> editorial;
	List<String> numPaginas;
	
	public OpenCSV() {
		this.listArr = new ArrayList<String[]>();
		this.titulos = new ArrayList<String>();
		this.autor = new ArrayList<String>();
		this.anyoNacimiento = new ArrayList<String>();
		this.anyoPublicacion = new ArrayList<String>();
		this.editorial = new ArrayList<String>();
		this.numPaginas = new ArrayList<String>();
	}
	
	public void readCSV(String nameFile) {
		
		try (CSVReader reader = new CSVReader(new FileReader(nameFile)))  {
			String[] row;
			while((row = reader.readNext()) != null) {
				listArr.add(row[0].split(";"));
			}
			int posCol = 0;
			for(String[] arr : listArr) {
				for(int i = 0; i < arr.length; i++) {
					if(!arr[i].equals("")) {
						if(posCol == 0) {
							
							if(i == 0) {
								titulos.add(arr[i].replace(" ", "_"));
							}
							if(i == 1) {
								autor.add(arr[i].replace(" ", "_"));
							}
							if(i == 2) {
								anyoNacimiento.add(arr[i].replace(" ", "_"));
							}
							if(i == 3) {
								anyoPublicacion.add(arr[i].replace(" ", "_"));
							}
							if(i == 4) {
								editorial.add(arr[i].replace(" ", "_"));
							}
							if(i == 5) {
								numPaginas.add(arr[i].replace(" ", "_"));
							}
						} else {
							if(i == 0) {
								titulos.add(arr[i]);
							}
							if(i == 1) {
								autor.add(arr[i]);
							}
							if(i == 2) {
								anyoNacimiento.add(arr[i]);
							}
							if(i == 3) {
								anyoPublicacion.add(arr[i]);
							}
							if(i == 4) {
								editorial.add(arr[i]);
							}
							if(i == 5) {
								numPaginas.add(arr[i]);
							}
						}
					} else {
						if(i == 0) {
							titulos.add("N.C.");
						}
						if(i == 1) {
							autor.add("N.C.");
						}
						if(i == 2) {
							anyoNacimiento.add("0");
						}
						if(i == 3) {
							anyoPublicacion.add("0");
						}
						if(i == 4) {
							editorial.add("N.C.");
						}
						if(i == 5) {
							numPaginas.add("0");
						}
					}
				}
				
				posCol++;
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
//		for(int i= 0; i < titulos.size();i++) {
//			System.out.println(titulos.get(i)+" - "+autor.get(i)+" - "+anyoNacimiento.get(i)+" - "+anyoPublicacion.get(i)+" - "+editorial.get(i)+" - "+numPaginas.get(i));
//			System.out.println("---------------------------------------------------------------------------");
//		}
		
	}
	
	
	
}
