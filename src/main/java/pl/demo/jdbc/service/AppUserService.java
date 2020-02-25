package pl.demo.jdbc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.demo.jdbc.model.AppUser;
import pl.demo.jdbc.repository.AppUserRepository;

@Service
public class AppUserService {
	
	@Autowired
	AppUserRepository repository;

	public AppUser addAppUser(AppUser user) {
		return repository.save(user);
	}
	
	public List<AppUser> getAll() {
		return repository.findAll();
	}
	
	public AppUser getById(Long id) {
		return repository.findById(id).orElseThrow();
	}
	
	public AppUser getByName(String userName) {
		return repository.findByName(userName);
	}
	
}
