package es.florida.main.services;

import java.io.File;
import java.io.FilenameFilter;

public class FilterExtension implements FilenameFilter {

	private String extension;

	public FilterExtension(String extension) {
		this.extension = extension;
	}

	@Override
	public boolean accept(File dir, String name) {
		return name.endsWith(extension);
	}

}
