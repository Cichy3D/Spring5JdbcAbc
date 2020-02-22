package pl.demo.jdbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import pl.demo.jdbc.model.AppUser;
import pl.demo.jdbc.model.Principal;

@Service
@Log4j2
public class PrincipalService implements UserDetailsService {

	@Autowired
	AppUserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.debug("loadUserByUsername: "+username);
		AppUser user = userService.getByName(username);
		log.debug("user found: "+user);
		UserDetails userDetails = new Principal(user);
		log.debug("userDetails: "+userDetails);
		return userDetails;
	}

}
