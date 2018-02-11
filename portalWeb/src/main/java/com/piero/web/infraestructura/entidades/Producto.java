package com.piero.web.infraestructura.entidades;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames= "codigo")})
public class Producto implements Serializable {
	
	private Long id;
	private String nombre;
	private String codigo;
	private BigDecimal precio;
	private Integer cantidad;

	@Id
	@SequenceGenerator(name="Producto_pk_sequence",sequenceName="Producto_sequence",allocationSize=5,initialValue = 20) 
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="Producto_pk_sequence")
	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	private static final long serialVersionUID = -1323016656165223410L;
	
}