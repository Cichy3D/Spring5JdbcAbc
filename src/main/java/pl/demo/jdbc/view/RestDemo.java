package pl.demo.jdbc.view;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import pl.demo.jdbc.model.AppUser;

@RestController()
@RequestMapping("/demo")
@Log4j2
public class RestDemo {
	
	@Value("${demo.wartosc}")
	private String x;

	@GetMapping("/hello")
	public String hello() {
		return "Hello! " + x;
	}
	
	@Secured({ "ROLE_ADMIN" })
	@GetMapping("/sec")
	public String sec(Authentication authentication) {
		return "Jesteś w metodzie chronionej, jesteś " + authentication.getName();
	}
	
	@PreAuthorize("hasRole('ROLE_USER') and #x=='thekey'")
	@GetMapping("/pre")
	public String pre(@RequestParam String x) {
		return "Jesteś w metodzie chronionej 2, x = "+x;
	}
	
	@PostAuthorize("returnObject == 'abc123'")
	@GetMapping("/post")
	public String post(@RequestParam String x) {
		return "abc"+x;
	}
	
	@GetMapping("/csrf")
	public String csrf(HttpServletRequest request)  {
	    CsrfToken csrfToken = (CsrfToken)request.getAttribute("_csrf");

	    return ""+csrfToken.getHeaderName() + " :: " + csrfToken.getToken();
	}

	@GetMapping("/user/{id}")
	public AppUser getUser(@PathVariable long id) {
		log.info( () -> "abc"+"sdfsdf" );
		return new AppUser(id, "Ala", "tajne", "USER");
	}
	
	@GetMapping("/add")
	public long add(@RequestParam long x, @RequestParam long y) {
		return x+y;
	}
	
	@PostMapping(value = "/user")
	public AppUser helloName(@RequestBody AppUser user) {
		return new AppUser(123L, user.getName(), user.getPassword(), user.getRole());
	}
}
