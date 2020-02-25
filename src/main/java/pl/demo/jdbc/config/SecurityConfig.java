package pl.demo.jdbc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;

import pl.demo.jdbc.model.AppUser;
import pl.demo.jdbc.model.Principal;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService principalService;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		
//		auth.inMemoryAuthentication().withUser("admin").password(encoder.encode("pass")).roles("ADMIN");
//		auth.inMemoryAuthentication().withUser("user").password(encoder.encode("pass")).roles("USER");
//		auth.inMemoryAuthentication().withUser("ala").password(encoder.encode("pass")).roles("USER");
		
		auth.userDetailsService(principalService);
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				
//				.antMatchers(HttpMethod.GET, "/users").hasAnyRole("USER")
//				.antMatchers(HttpMethod.POST, "/users").hasAnyRole("ADMIN")
				.anyRequest().permitAll()//authenticated()
				.and()
			.formLogin().and()
			.csrf().disable()
			.httpBasic();
	}
	
}
