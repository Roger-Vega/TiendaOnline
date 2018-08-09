package com.tienda.online.services;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tienda.online.models.Usuario;
import com.tienda.online.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	private UsuarioRepository usuarioRepository;
	
	private UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public Usuario guardar(Usuario usuario) {
		usuario.setFecha(new Date());
		if(usuarioRepository.findByEmail(usuario.getEmail()) == null) {
			return usuarioRepository.save(usuario);	
		}
		return null;
	}
	
	public List<Usuario> obtenerUsuarios(){
		return (List<Usuario>) usuarioRepository.findAll();
	}

}