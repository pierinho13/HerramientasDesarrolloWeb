package com.piero.web.controllers;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
			
			if (StringUtils.isBlank(producto.getCodigo())) {
				Date fecha = new Date();
				producto.setCodigo(producto.getNombre()+ fecha.getTime());
			}
			productoRepository.save(producto);
			
			model.addAttribute("productos",productoRepository.getAllProducts());
		} catch (Exception e) {
			model.addAttribute("mensaje","SE HA PRODUCIDO ERROR INTENTELO NUEVAMENTE");
			return "listado";
		}
		
		return "listado";
	}
	
	@RequestMapping(value="/tiendaVirtual/postComprar", method = RequestMethod.POST)
	public String postCompra(@ModelAttribute("productoForm") Producto producto,Model model, AppUser user) {
		
		try {
			if (StringUtils.isBlank(producto.getCodigo())) {
				model.addAttribute("mensaje","NO SE HA ESCRITO NINGUN CODIGO");
				return "errorCompra";
			} 
			
			Producto p = productoRepository.findByCodigoIgnoreCase(producto.getCodigo());
			
			if (p == null) {
				model.addAttribute("mensaje","NO SE ENCUENTRA NINGUN PRODUCTO PARA EL CODIGO: " +producto.getCodigo());
				return "errorCompra";
			}
			if (p.getCantidad()!= null && p.getCantidad()<1) {
				model.addAttribute("mensaje","NO SE DISPONE STOCK DEL PRODUCTO: " +p.getNombre());
				return "errorCompra";
			}
			Integer diferenciaCantidad = p.getCantidad()-producto.getCantidad();
			
			if (diferenciaCantidad<0) {
				model.addAttribute("mensaje","SE DISPONE DEL PRODUCTO PERO NO TANTA CANTIDAD. SOLO QUEDA: " +p.getCantidad());
				return "errorCompra";
			}
			
			p.setCantidad(diferenciaCantidad);
			productoRepository.save(p);
			
			model.addAttribute("mensaje","COMPRA REALIZADA CORRECTAMENTE");
		} catch (Exception e) {
			model.addAttribute("mensaje","SE HA PRODUCIDO ERROR INTENTELO NUEVAMENTE");
			return "compra";
		}
		
		return "index";
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
		
		Producto producto = new Producto();
		
		model.addAttribute("productoForm", producto);
		
		
		return "compra";
	}
}