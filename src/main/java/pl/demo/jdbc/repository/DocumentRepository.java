package pl.demo.jdbc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.demo.jdbc.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

	//@Query(" select d from Document d where d.creator.id=:appUserId")
	List<Document> findByCreatorId(Long appUserId);
	
}
