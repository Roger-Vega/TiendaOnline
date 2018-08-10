package com.tienda.online.services;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tienda.online.models.Compra;
import com.tienda.online.models.Producto;
import com.tienda.online.repositories.CompraRepository;

@Service
public class CompraService {

	private CompraRepository compraRepository;
	private ProductoService productoService;
	
	private CompraService(CompraRepository compraRepository, ProductoService productoService) {
		this.compraRepository = compraRepository;
		this.productoService = productoService;
	}
	
	public Compra guardar(Compra compra) {
		compra.setFecha(new Date());
		compra.setNroDocumento("00000"+compraRepository.count()+1);
		compra.getDetalleCompraList().forEach(detalle -> {
			Producto producto = productoService.obtenerById(detalle.getId());
			if(producto != null) {
				producto.setCantidad(producto.getCantidad() - detalle.getCantidad());
				productoService.guardar(producto);
				detalle.setCompra(compra);
			}
		});
		
		return compraRepository.save(compra);
	}
	
	public List<Compra> obtenerCompras(){
		return (List<Compra>) compraRepository.findAll();
	}
	
}
