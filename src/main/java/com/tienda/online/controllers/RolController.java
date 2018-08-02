package com.tienda.online.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.online.models.Rol;
import com.tienda.online.services.RolService;

@RestController
@RequestMapping("/api/rol")
public class RolController {

	private RolService rolService;
	
	public RolController(RolService rolService) {
		this.rolService = rolService;
	}
	
	@PostMapping
	public Rol guardarRol(@RequestBody Rol rol) {
		return rolService.guardar(rol);
	}
	
	@GetMapping
	public List<Rol> obtenerRoles(){
		return rolService.obtenerRoles();
	}
	
	@PutMapping
	public Rol actualizarRol(@RequestBody Rol rol) {
		return rolService.guardar(rol);
	}
	
	@DeleteMapping(path = "/{id}")
	public void eliminarRol(@PathVariable(value="id") Integer id) {
		rolService.eliminarRol(id);
	}
}
