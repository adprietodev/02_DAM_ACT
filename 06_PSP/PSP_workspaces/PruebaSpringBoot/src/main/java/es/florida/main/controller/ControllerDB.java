package es.florida.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.florida.main.TitleRepository;
import es.florida.main.data.Title;


@Controller
@RequestMapping(path="/library")
public class ControllerDB {

	@Autowired
	private TitleRepository titleRepository;
	
	@PostMapping(path="/add") //Map Only Post Request
	public @ResponseBody String addNewTitle(@RequestParam String title, @RequestParam Integer author,@RequestParam Integer year, @RequestParam Integer editorial, @RequestParam Integer pages ) {
		
		Title titleAdd = new Title();
		titleAdd.setTitle(title);
		titleAdd.setAuthor(author);
		titleAdd.setYear(year);
		titleAdd.setEditorial(editorial);
		titleAdd.setPages(pages);
		
		return "Saved";
		
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Title> getAllTitles(){
		return titleRepository.findAll();
	}
	
}
