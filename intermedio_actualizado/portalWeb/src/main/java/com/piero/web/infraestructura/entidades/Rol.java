package com.piero.web.infraestructura.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Rol implements Serializable,GrantedAuthority {

	private Long id;
	private String name;
	private Boolean esAdmin=false;
	private Integer orden;
	
	
	@Id
	@SequenceGenerator(name="Role_pk_sequence",sequenceName="Role_sequence",allocationSize=1) 
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="Role_pk_sequence")
	public Long getId() {
		return id;
	}


	@Column(nullable=false, unique=true)
	public String getName() {
		return name;
	}

	public Integer getOrden() {
		return orden;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	@Transient
	public String getAuthority() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
	
	
	public Boolean getEsAdmin() {
		return esAdmin;
	}

	public void setEsAdmin(Boolean esAdmin) {
		this.esAdmin = esAdmin;
	}


	private static final long serialVersionUID = -3271975079778943659L;
}