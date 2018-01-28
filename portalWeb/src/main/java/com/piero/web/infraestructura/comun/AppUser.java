package com.piero.web.infraestructura.comun;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class AppUser extends User {

	private Long personaId;
	private String personaNombre;
	private String username;
	private String password;
	
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	private static final long serialVersionUID = 3909914989835286267L;

//	public boolean esAdmin() {
//		if (clienteId==null)
//			return true;
//		return false;
//	}

}