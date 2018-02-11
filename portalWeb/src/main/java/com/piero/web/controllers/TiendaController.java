package com.piero.web.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.piero.web.infraestructura.comun.AppUser;
import com.piero.web.infraestructura.entidades.Producto;
import com.piero.web.infraestructura.repository.ProductoRepository;
import com.piero.web.security.RestService;
import com.piero.web.utils.MensajeRespuesta;
import com.piero.web.utils.ProductoProveedorDTO;

import ch.qos.logback.access.jetty.JettyServerAdapter;


@Controller
public class TiendaController {
	
	@Autowired
	ProductoRepository productoRepository;
	
	@Autowired
	private RestService restService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	
	@RequestMapping(value="/tiendaVirtual/alta", method = RequestMethod.GET)
    public String getAlta(Model model, AppUser user) {
		
		
		Producto producto = new Producto();
		
		model.addAttribute("productoForm", producto);
		
        return "alta";
    }
	
	@RequestMapping(value="/tiendaVirtual/importarDesdeAPIForm", method = RequestMethod.GET)
	public String getImportarDesdeAPI(Model model, AppUser user) {
		
		
		Producto producto = new Producto();
		
		model.addAttribute("productoForm", producto);
		
		return "importarDesdeAPI";
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
	
	@RequestMapping(value="/tiendaVirtual/importarProductosAPi", method = RequestMethod.GET)
	public String getImportarProductos(HttpServletRequest request, Model model) {
		
		
		try {
			
			List<Producto> productos = new ArrayList<>();
			
			Map<String, Object> params = this.obtieneParametrosRequest( request );
			
			String host=  restService.APIHOST ;
			
			if (params.containsKey("api")) {
				host = (String) params.get("api");
			}
			
			LinkedHashMap<String, Object> respuesta = (LinkedHashMap<String, Object>) restService.get( host+"/"+restService.API_PREFIX+"/producto/porCantidadYFecha", params );
			
			if(respuesta != null) {
				
			String estado = (String)respuesta.get("estado");
			List<LinkedHashMap> productosDto = (List<LinkedHashMap> ) respuesta.get("productos");
			
			if (estado.equals("OK")) {
				
				if(CollectionUtils.isNotEmpty(productosDto)) {
					
					for (LinkedHashMap dto: productosDto) {
						Gson gson = new Gson();
						Producto producto = new Producto();
						JsonElement jsonElement = gson.toJsonTree(dto);
						JsonObject jsonObject = jsonElement.getAsJsonObject();
						jsonObject.remove("fechaEntrada");
						jsonObject.remove("id");
						ProductoProveedorDTO productoProveedorDTO = gson.fromJson(jsonObject, ProductoProveedorDTO.class);
						BeanUtils.copyProperties(producto, productoProveedorDTO);
						producto.setId(null);
						Date now = new Date();
						producto.setNombre(producto.getNombre()+"-API-"+now.getTime());
						producto.setCodigo(producto.getCodigo()+"-API-"+now.getTime());
						producto.setCantidad(Integer.valueOf((String)params.get("cantidad")));
						productos.add(producto);
						
						productoRepository.save(producto);
					}
					
					
					model.addAttribute("productos", productoRepository.getAllProducts());
					model.addAttribute("mensaje", "IMPORTADOS CORRECTAMENTE");
					
				} else {
					model.addAttribute("productos", productoRepository.getAllProducts());
					model.addAttribute("mensaje", "NO SE HA ENCONTRADO UN PRODUCTO QUE SE AJUSTE A LO REQUERIDO");
				}
				
				return "listado";
			 } else {
					model.addAttribute("mensaje", "NO SE HA ENCONTRADO NINGUN  PRODUCTO CON ESAS CARACTERISTICAS");
					return "listado";
				}
			}else {
				
				model.addAttribute("mensaje", "NO SE HA ENCONTRADO NINGUN  PRODUCTO CON ESAS CARACTERISTICAS O NO SE HA PODIDO HACER CONEXION CON EL API");
				return "listado";
			
			} 
		}catch (Exception e) {
			model.addAttribute("mensaje", "NO SE HA PODIDO IMPORTAR DESDE EL API REST. ERROR INTERNO");
			e.printStackTrace();
			return "listado";
		}
	}
	
	private Map<String, Object> obtieneParametrosRequest( HttpServletRequest request )
	{
		try {
			request.getParts();
		} catch( Exception _ex ) {} // no me has mandado nada 
		
		if( request == null || request.getParameterNames() == null || ! request.getParameterNames().hasMoreElements() ){
			return null;
		}
			

		Map<String, Object> ret = new HashMap<>();

		Enumeration<String> params = request.getParameterNames();
		
		while( params.hasMoreElements() )  {
			String param = params.nextElement();
			
			logger.trace( "Param: " + param + " value: " + request.getParameter( param ) );
			
			ret.put( param, request.getParameter( param ) );
		}
		
		return ret;
	}
}