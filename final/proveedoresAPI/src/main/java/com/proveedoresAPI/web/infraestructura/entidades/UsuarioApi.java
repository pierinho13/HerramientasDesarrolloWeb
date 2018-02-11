package com.proveedoresAPI.web.infraestructura.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(indexes = { @Index(columnList = "personaapi_id"), @Index(columnList = "username") }, uniqueConstraints = {
		@UniqueConstraint(columnNames = { "username" }) })
public class UsuarioApi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String username;
	private String password;
	private Set<RolApi> roles = new HashSet<RolApi>();
	private PersonaApi personaApi;

	@Id
	@SequenceGenerator(name="Usuarioapi_pk_sequence",sequenceName="Usuarioapi_sequence",allocationSize=1,initialValue = 20) 
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="Usuarioapi_pk_sequence")
	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}
	

	@ManyToMany(fetch=FetchType.EAGER)
	public Set<RolApi> getRoles() {
		return roles;
	}

	public void setRoles(Set<RolApi> roles) {
		this.roles = roles;
	}

	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	public PersonaApi getPersonaApi() {
		return personaApi;
	}

	public void setPersonaApi(PersonaApi personaApi) {
		this.personaApi = personaApi;
	}

	public String getPassword() {
		return password;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

}