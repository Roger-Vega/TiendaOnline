package com.tienda.online.controllers;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.online.dto.response.DataResponse;
import com.tienda.online.dto.response.DataResponseList;
import com.tienda.online.models.Producto;
import com.tienda.online.services.ProductoService;

@RestController
@RequestMapping("/api/producto")
public class ProductoController extends BaseController{

	ProductoService productoService;
	
	public ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}
	
	@PostMapping
	public DataResponse<Producto> guardarProducto(@RequestBody Producto producto) {
		Producto productoGuardado = productoService.guardar(producto);
		if(productoGuardado == null) {
			throw new DataIntegrityViolationException("Ya existe un producto con nombre " + producto.getNombre());
		}
		return new DataResponse<Producto>(productoGuardado);
	}
	
	@GetMapping
	public DataResponseList<Producto> obtenerProductos(){
		List<Producto> productoLista = productoService.obtenerProductos(); 
		return new DataResponseList<Producto>(productoLista, productoLista.size());
	}
	
	
}
