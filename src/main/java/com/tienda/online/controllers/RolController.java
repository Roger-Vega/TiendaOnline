package com.tienda.online.controllers;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.online.dto.response.DataResponse;
import com.tienda.online.dto.response.DataResponseList;
import com.tienda.online.models.Rol;
import com.tienda.online.services.RolService;

@RestController
@RequestMapping("/api/rol")
public class RolController extends BaseController{

	private RolService rolService;
	
	public RolController(RolService rolService) {
		this.rolService = rolService;
	}
	
	@PostMapping
	public DataResponse<Rol> guardarRol(@RequestBody Rol rol) {
		Rol rolGuardado = rolService.guardar(rol);
		if(rolGuardado == null) {
			throw new DataIntegrityViolationException("Ya existe un rol " + rol.getNombreRol());
		}
		return new DataResponse<Rol>(rolGuardado);
	}
	
	@GetMapping
	public DataResponseList<Rol> obtenerRoles(){
		List<Rol> rolLista = rolService.obtenerRoles(); 
		return new DataResponseList<Rol>(rolLista, rolLista.size());
	}
	
	@PutMapping
	public DataResponse<Rol> actualizarRol(@RequestBody Rol rol) {
		return new DataResponse<Rol>(rolService.actualizarRol(rol));
	}
	
	@DeleteMapping(path = "/{id}")
	public void eliminarRol(@PathVariable(value="id") Integer id) {
		rolService.eliminarRol(id);
	}

}
