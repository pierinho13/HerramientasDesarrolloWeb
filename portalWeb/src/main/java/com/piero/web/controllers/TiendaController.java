package com.piero.web.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.piero.web.infraestructura.comun.AppUser;
import com.piero.web.infraestructura.entidades.Producto;
import com.piero.web.infraestructura.repository.ProductoRepository;


@Controller
public class TiendaController {
	
	@Autowired
	ProductoRepository productoRepository;
	
	
	@RequestMapping(value="/tiendaVirtual/alta", method = RequestMethod.GET)
    public String getAlta(Model model, AppUser user) {
		
		
		Producto producto = new Producto();
		
		model.addAttribute("productoForm", producto);
		
        return "alta";
    }
	
	@RequestMapping(value="/tiendaVirtual/editarProducto/{productId}", method = RequestMethod.GET)
	public String getEditarProducto(@PathVariable Long productId,Model model, AppUser user) {
		
		
		Producto producto = productoRepository.findById(productId);
		
		model.addAttribute("productoForm", producto);
		model.addAttribute("producto", producto);
		
		return "alta";
	}
	
	@RequestMapping(value="/tiendaVirtual/crearAlta", method = RequestMethod.POST)
	public String postAlta(@ModelAttribute("productoForm") Producto producto,Model model, AppUser user) {
		
		try {
			if (producto.getId() != null) {
				model.addAttribute("mensaje","SE HA MODIFICADO EXITOSAMENTE");
			} else {
				model.addAttribute("mensaje","SE HA DADO DE ALTA EXITOSAMENTE");
			}
			productoRepository.save(producto);
			
			model.addAttribute("productos",productoRepository.getAllProducts());
		} catch (Exception e) {
			model.addAttribute("mensaje","SE HA PRODUCIDO ERROR INTENTELO NUEVAMENTE");
			return "listado";
		}
		
		return "listado";
	}
	
	@RequestMapping(value="/tiendaVirtual/baja", method = RequestMethod.GET)
	public String getBaja(Model model, AppUser user) {
		
	List<Producto> productos = productoRepository.getAllProducts();
		
		model.addAttribute("esAdmin", user.getIsAdmin());
		model.addAttribute("productos", productos);
		
		return "baja";
	}
	
	@RequestMapping(value="/tiendaVirtual/darBaja/{productoId}", method = RequestMethod.GET)
	public String getBaja(Model model,@PathVariable Long productoId , AppUser user) {
		
		try {
			
			productoRepository.delete(productoId);
			List<Producto> productos = productoRepository.getAllProducts();
			
			model.addAttribute("esAdmin", user.getIsAdmin());
			model.addAttribute("productos", productos);
			model.addAttribute("mensaje", "SE HA BORRADO EXITOSAMENTE");
		} catch (Exception e) {
			List<Producto> productos = productoRepository.getAllProducts();
			model.addAttribute("productos", productos);
			return "listado";
		}
		
		return "listado";
	}
	
	@RequestMapping(value="/tiendaVirtual/modificacion", method = RequestMethod.GET)
	public String getModificacion(Model model, AppUser user) {
		
		
		List<Producto> productos = productoRepository.getAllProducts();
		
		model.addAttribute("esAdmin", user.getIsAdmin());
		model.addAttribute("productos", productos);
		
		
		return "modificacion";
	}
	
	@RequestMapping(value="/tiendaVirtual/listado", method = RequestMethod.GET)
	public String getListado(Model model, AppUser user) {
		
		
		List<Producto> productos = productoRepository.getAllProducts();
		
		model.addAttribute("esAdmin", user.getIsAdmin());
		model.addAttribute("productos", productos);
		
		return "listado";
	}
	
	
	@RequestMapping(value="/tiendaVirtual/reposicion", method = RequestMethod.GET)
	public String getReposicion(Model model, AppUser user) {
		
		Producto producto  = new Producto();
		model.addAttribute("productoForm", producto);
		model.addAttribute("productos", productoRepository.getAllProducts());
		
		return "reponer";
	}
	
	@RequestMapping(value="/tiendaVirtual/postReponer", method = RequestMethod.POST)
	public String postReposicion(@ModelAttribute("productoForm") Producto producto, Model model, AppUser user) {
		
		try {
			Integer cantidad = producto.getCantidad();
			producto = productoRepository.findById(producto.getId());
			producto.setCantidad(cantidad);
			productoRepository.save(producto);
			model.addAttribute("productos",productoRepository.getAllProducts());
			model.addAttribute("mensaje","SE HA REPUESTO CORRECTAMENTE LA CANTIDAD A " +cantidad);
		} catch (Exception e) {
			model.addAttribute("mensaje","SE HA PRODUCIDO ERROR INTENTELO NUEVAMENTE");
			return "listado";
		}
		
		return "listado";
	}
	
	@RequestMapping(value="/tiendaVirtual/compra", method = RequestMethod.GET)
	public String getCompra(Model model, AppUser user) {
		
		if (user==null) {
			user = new AppUser("piero", "pass", Arrays.asList());
		}
		
		model.addAttribute("esAdmin", user.getIsAdmin());
		
		return "index";
	}
}