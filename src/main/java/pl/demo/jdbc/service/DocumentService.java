package pl.demo.jdbc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import pl.demo.jdbc.config.SecurityUtils;
import pl.demo.jdbc.model.AppUser;
import pl.demo.jdbc.model.Document;
import pl.demo.jdbc.model.Principal;
import pl.demo.jdbc.repository.DocumentRepository;

@Service
public class DocumentService {
	
	@Autowired
	DocumentRepository repository;

	public Document addDocument(Document document) {
		Principal principal = SecurityUtils.getCurrentPrincipal();
		
		//guarantees that a document is created under a current user ID
		AppUser thisUserStab = new AppUser(principal.getUserId(), null, null, null);
		document.setCreator(thisUserStab);
		
		return repository.save(document);
	}
	
	public List<Document> getAll() {
		Principal principal = SecurityUtils.getCurrentPrincipal();
		
		if ( SecurityUtils.hasAuthority(principal,"ROLE_ADMIN") )  {
			return repository.findAll();
		} else {
			return repository.findByCreatorId(principal.getUserId());
		}
	}
	
	@PostAuthorize("hasRole('ROLE_ADMIN') or returnObject.creator.id==authentication.principal.userId")
	public Document getById(Long id) {
		return repository.findById(id).orElseThrow();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or #userId==authentication.principal.userId")
	public List<Document> getByUserId(Long userId) {
		return repository.findByCreatorId(userId);
	}
	
}
