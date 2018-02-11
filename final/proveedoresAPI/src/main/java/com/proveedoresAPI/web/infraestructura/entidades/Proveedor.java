package com.proveedoresAPI.web.infraestructura.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames= "cif")})
public class Proveedor implements Serializable {
	
	private static final long serialVersionUID = -3036062166971375283L;
	
	private Long id;
	private String nombre;
	private String cif;
	private String direccion;
	private String telefono;
	private Set<ProductoProveedor> productos = new HashSet<ProductoProveedor>();

	@Id
	@SequenceGenerator(name="Proveedor_pk_sequence",sequenceName="Proveedor_sequence",allocationSize=1) 
	@GeneratedValue(strategy=GenerationType.AUTO,generator="Proveedor_pk_sequence")
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

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)
	public Set<ProductoProveedor> getProductos() {
		return productos;
	}

	public void setProductos(Set<ProductoProveedor> productos) {
		this.productos = productos;
	}
	
	
	
}