package com.letsaspire.dashboard.services;

import java.util.Collection;
import java.util.Collections;

import javax.annotation.PostConstruct;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.letsaspire.dashboard.domain.UserCredentials;


@Component
public class CustomUserDetail implements UserDetails {

	private static final long serialVersionUID = -2369522927593289455L;
	private UserCredentials user;

	
	public CustomUserDetail(UserCredentials user) {
		this.user = user;
	}
	public CustomUserDetail() {
		
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
	}

	@Override
	public String getPassword() {

		return  user.getPassword();
	}

	@Override
	public String getUsername() {

		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
