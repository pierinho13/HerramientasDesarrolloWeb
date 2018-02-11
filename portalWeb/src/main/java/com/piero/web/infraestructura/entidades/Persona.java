package com.piero.web.infraestructura.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames= "codigo")})
public class Persona implements Serializable {
	
	private Long id;
	private String nombre;
	private String codigo;

	@Id
	@SequenceGenerator(name="Persona_pk_sequence",sequenceName="Persona_sequence",allocationSize=5,initialValue = 20) 
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="Persona_pk_sequence")
	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
	private static final long serialVersionUID = 4263309047250016374L;
}