package pl.demo.jdbc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.demo.jdbc.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

	AppUser findByName(String name);
		
}
