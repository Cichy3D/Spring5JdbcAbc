package pl.demo.jdbc.view;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import pl.demo.jdbc.model.Document;
import pl.demo.jdbc.service.DocumentService;

@RestController()
@RequestMapping("/documents")
@CrossOrigin(origins = "http://localhost:4200")
@Log4j2
public class DocumentController {

	@Autowired
	DocumentService documentService;
	
	@PostMapping()
	public Document addDocument(@RequestBody Document document) {
		// log.info( () -> "" + document );
		return documentService.addDocument(document);
	}
	
	@GetMapping()
	public List<Document> getAll() {
		return documentService.getAll();
	}
	
	@GetMapping("/{id}")
	public Document getById(@PathVariable Long id) {
		return documentService.getById(id);
	}
	
	@GetMapping("/user-id/{id}")
	public Collection<Document>getByUserId(@PathVariable Long id) {
		return documentService.getByUserId(id);
	}
}
