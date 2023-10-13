package es.florida.AE01;

import java.io.*;

public class FilterExtension implements FilenameFilter {
	
	String ext;
	
	FilterExtension(String ext){
		this.ext = ext;
	}
	
	public boolean accept(File dir, String name) {
		return name.endsWith(ext);
	}

}
