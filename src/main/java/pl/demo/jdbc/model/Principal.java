package pl.demo.jdbc.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

public class Principal implements UserDetails {

	private static final long serialVersionUID = 8324923878560295741L;
	
	private AppUser appUser;

	public Principal(AppUser appUser) {
		super();
		this.appUser = appUser;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if( StringUtils.isEmpty(appUser.getRole()) ) {
			return Collections.emptyList();
		}
		return Collections.singleton(() -> "ROLE_"+appUser.getRole());
	}

	@Override
	public String getPassword() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return encoder.encode(appUser.getPassword());
	}
	
	public Long getUserId() {
		return appUser.getId();
	}

	@Override
	public String getUsername() {
		return appUser.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String toString() {
		return "Principal [appUser=" + appUser + "]";
	}

	
	
}
