package es.florida.MP2T1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Model {

	private List<String> lineasDoc;
	//Clase que gestiona los datos de la aplicación y permite las operaciones.
	
	public Model() {
		lineasDoc =  new ArrayList<String>();
		
	}
	
	public List<String> readDocument(File doc) {
		
		if(lineasDoc.size() > 0) {
			lineasDoc.clear();
		}
		
		try {
			FileReader fR = new FileReader(doc);
			BufferedReader bR = new BufferedReader(fR);
			
			String linea = bR.readLine();
			
			while(linea != null) {
				this.lineasDoc.add(linea);
				linea = bR.readLine();
			}
			
			bR.close();
			fR.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this.lineasDoc;
	}
	
	public void writeDocument(File doc,List<String> newText) {
		
		try {
			FileWriter fW = new FileWriter(doc);
			BufferedWriter bW = new BufferedWriter(fW);
			
			for(String linea : newText) {
				bW.write(linea);
				bW.newLine();
			}
			
			bW.close();
			fW.close();
			
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public int searchMatch(String word) {
		int count = 0;
		
		if(word.equals("")) {
			return count = 0;
		} else {
			List<String> words = new ArrayList<String>();
			for(String linea : lineasDoc) {
				String[] sL = linea.split("[,.\\-:;!¡?¿ ]");
				for(String sW : sL) {
					words.add(sW);
				}
			}
			if(checkWord(word)) {
				for(String w : words) {
					if(word.toLowerCase().equals(w.toLowerCase())) {
						count++;
					}
				}
			} 
			
			return count;
		}
		
	}
	
	public List<String> remplaceWord(String wordInDoc, String wordRemplace){
		List<String> lineaReplaced = new ArrayList<String>();
		if(checkWord(wordInDoc)) {
			for(String linea : lineasDoc) {
				lineaReplaced.add(linea.replaceAll("\\b"+wordInDoc+"\\b", wordRemplace));
			}
		}
		return lineaReplaced;
		
	}
	
	public boolean checkSimbol(String word) {
		String[] simbols = {
			    "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "+", "=",
			    "{", "}", "[", "]", "|", "\\", ":", ";", "<", ">", ",", ".", "?", "/",
			    "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",""
			};
		boolean check = false;;
		for(String s : simbols) {
			if(s.equals(word)) {
				check = true;
				break;
			}else {
				check = false;
			}
		}
		return check;
	}
	
	private Boolean checkWord(String word) {
		String[] check = word.split("[,.\\-:;!¡?¿ ]"); 
		if(check.length != 1) {
			return false;
		}else {
			return true;
		}
	}
	
	public String printInfoDoc() {
		String textIni = "";
		for(String linea : readDocument(new File("ET1.txt"))) {
			textIni += linea+"\n";
		}
		return textIni;
	}
	
	public List<String> getLineasDoc(){
		return lineasDoc;
	}
	
	
	
}
