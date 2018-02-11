package com.piero.web.infraestructura.comun;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class AppUser extends User {

	private Long personaId;
	private String personaNombre;
	private Boolean isAdmin;
	
	
	public AppUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public Long getPersonaId() {
		return personaId;
	}


	public void setPersonaId(Long personaId) {
		this.personaId = personaId;
	}


	public String getPersonaNombre() {
		return personaNombre;
	}


	public void setPersonaNombre(String personaNombre) {
		this.personaNombre = personaNombre;
	}
	
	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}


	private static final long serialVersionUID = 3909914989835286267L;

}