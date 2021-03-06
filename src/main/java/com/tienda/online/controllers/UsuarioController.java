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
import com.tienda.online.models.Usuario;
import com.tienda.online.services.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController extends BaseController{

	private UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	public DataResponse<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
		Usuario usuarioGuardado = usuarioService.guardar(usuario);
		if(usuarioGuardado == null) {
			throw new DataIntegrityViolationException("Ya existe un usuario con email " + usuario.getEmail());
		}
		return new DataResponse<Usuario>(usuarioGuardado);
	}
	
	@GetMapping
	public DataResponseList<Usuario> obtenerUsuarios(){
		List<Usuario> usuarioLista = usuarioService.obtenerUsuarios();
		return new DataResponseList<Usuario>(usuarioLista, usuarioLista.size());
	}
	
}
