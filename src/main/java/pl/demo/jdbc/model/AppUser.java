package pl.demo.jdbc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	String name;
	String password;
	String role;
	
}


//pakiet DTO - Dozer
//public class AppUserDTO {
//	
//	Long id;
//	String name;
//	
//	List<Long> docIds;
//	
//}
//
//class DocumentDTO {
//	Long id;
//	String name;
//	String content;
//	
//	Long creatorUserId;
//}
