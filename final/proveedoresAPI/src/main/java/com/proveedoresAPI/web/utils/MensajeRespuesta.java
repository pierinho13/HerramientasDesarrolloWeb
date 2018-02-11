package com.proveedoresAPI.web.utils;

import java.io.Serializable;
import java.util.List;

import com.proveedoresAPI.web.infraestructura.entidades.ProductoProveedor;

public class MensajeRespuesta  implements Serializable{
	
	private String estado;
	private List<ProductoProveedor> productos;
	
	

	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}

	




	public List<ProductoProveedor> getProductos() {
		return productos;
	}



	public void setProductos(List<ProductoProveedor> productos) {
		this.productos = productos;
	}






	private static final long serialVersionUID = -3001209419205197037L;
}
