package pl.demo.jdbc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.demo.jdbc.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

	List<Document> findByCreatorId(Long appUserId);
	
}
