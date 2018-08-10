package com.tienda.online.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.online.dto.response.DataResponse;
import com.tienda.online.dto.response.DataResponseList;
import com.tienda.online.models.Compra;
import com.tienda.online.services.CompraService;

@RestController
@RequestMapping("/api/compra")
public class CompraController extends BaseController{
	
	private CompraService compraService;
	
	public CompraController(CompraService compraService) {
		this.compraService = compraService;
	}
	
	@PostMapping
	public DataResponse<Compra> guardarUsuario(@RequestBody Compra compra) {
		return new DataResponse<Compra>(compraService.guardar(compra));
	}
	
	@GetMapping
	public DataResponseList<Compra> obtenerCompras(){
		List<Compra> compraList = compraService.obtenerCompras(); 
		return new DataResponseList<Compra>(compraList, compraList.size());
	}

}
