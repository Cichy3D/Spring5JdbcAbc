package pl.demo.jdbc.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import pl.demo.jdbc.model.Principal;

public class SecurityUtils {

	public static Principal getCurrentPrincipal() { // throws Exception {
		SecurityContext sc = SecurityContextHolder.getContext();
		if (sc == null) {
			// throw new SecurityException("No SecurityContextHolder found");
			return null;
		}
		Authentication auth = sc.getAuthentication();
		if (auth == null) {
			// throw new SecurityException("No Authentication object found");
			return null;
		}
		Object principal = auth.getPrincipal();
		if ( !(principal instanceof Principal) ) {
			// throw new SecurityException("Principal object is of invalid type: "+principal.getClass().getName());
			return null;
		}
		return (Principal)principal;
	}
	
	public static boolean hasAuthority(Principal principal, String authority) {
		return principal.getAuthorities().stream().anyMatch( ga -> ga.getAuthority().equals(authority) );
	}
	
}
