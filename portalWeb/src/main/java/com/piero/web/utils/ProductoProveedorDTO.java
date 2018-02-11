package com.piero.web.utils;

import java.math.BigDecimal;
import java.util.Date;

public class ProductoProveedorDTO  {
	
	private Long id;
	private String nombre;
	private String codigo;
	private BigDecimal precio;
	private Integer cantidad;
	private Date fechaEntrada;

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

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	
}