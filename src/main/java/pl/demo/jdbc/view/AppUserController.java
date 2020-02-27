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
import pl.demo.jdbc.model.AppUser;
import pl.demo.jdbc.model.Document;
import pl.demo.jdbc.service.AppUserService;

@RestController()
@RequestMapping("/users")
@CrossOrigin //(origins = {"http://localhost:4200", "**"})
@Log4j2
public class AppUserController {

	@Autowired
	AppUserService appUserService;
	
	@PostMapping()
	public AppUser addUser(@RequestBody AppUser appUser) {
		return appUserService.addAppUser(appUser);
	}
	
	@GetMapping()
	public List<AppUser> getAll() {
		return appUserService.getAll();
	}
	
	@GetMapping("/{id}")
	public AppUser getById(@PathVariable Long id) {
		return appUserService.getById(id);
	}
	
	@GetMapping("/name/{name}")
	public AppUser getByName(@PathVariable String name) {
		return appUserService.getByName(name);
	}
	
}
