package com.piero.web.infraestructura.entidades;

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
@Table(indexes = { @Index(columnList = "persona_id"), @Index(columnList = "username") }, uniqueConstraints = {
		@UniqueConstraint(columnNames = { "username" }) })
public class Usuario implements Serializable {

	private Long id;
	private String username;
	private String password;
	private Set<Rol> roles = new HashSet<Rol>();
	private Persona persona;

	@Id
	@SequenceGenerator(name="Usuario_pk_sequence",sequenceName="Usuario_sequence",allocationSize=1) 
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="Usuario_pk_sequence")
	public Long getId() {
		return id;
	}

	@ManyToMany(fetch=FetchType.EAGER)
	public Set<Rol> getRoles() {
		return roles;
	}

	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getUsername() {
		return username;
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

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	private static final long serialVersionUID = -8466993893698810206L;
}