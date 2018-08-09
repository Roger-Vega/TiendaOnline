package com.tienda.online.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.tienda.online.models.IngresoProducto;
import com.tienda.online.models.Producto;
import com.tienda.online.repositories.IngresoProductoRepository;

@Service
public class IngresoProductoService {

	private IngresoProductoRepository ingresoProductoRepository;
	private ProductoService productoService;
	
	private IngresoProductoService(IngresoProductoRepository ingresoProductoRepository,  @Lazy ProductoService productoService) {
		this.ingresoProductoRepository = ingresoProductoRepository;
		this.productoService = productoService;
	}
	
	public IngresoProducto guardar(IngresoProducto ingresoProducto) {
		ingresoProducto.setFechaIngreso(new Date());
		Producto producto = productoService.obtenerById(ingresoProducto.getProducto().getId());
		if(producto != null) {
			producto.setCantidad(producto.getCantidad() + ingresoProducto.getCantidad());
			productoService.guardar(producto);
			return guardarIngreso(ingresoProducto, producto);
		}
		return null;
	}
	
	public List<IngresoProducto> obtenerIngresoProductos(){
		return (List<IngresoProducto>) ingresoProductoRepository.findAll();
	}
	
	public IngresoProducto guardarIngreso(IngresoProducto ingresoProducto, Producto producto) {
		ingresoProducto.setTotal(producto.getPrecio().multiply(new BigDecimal(ingresoProducto.getCantidad())));
		return ingresoProductoRepository.save(ingresoProducto);
	}
	
}
