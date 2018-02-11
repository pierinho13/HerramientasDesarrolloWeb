package com.piero.web.utils;

import java.util.List;

public class MensajeRespuesta {
	
	private String estado;
	private List<ProductoProveedorDTO> productos;
	
	

	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}



	public List<ProductoProveedorDTO> getProductos() {
		return productos;
	}



	public void setProductos(List<ProductoProveedorDTO> productos) {
		this.productos = productos;
	}
	

}
