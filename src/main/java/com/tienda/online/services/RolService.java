package com.tienda.online.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tienda.online.models.Rol;
import com.tienda.online.repositories.RolRepository;

@Service
public class RolService {
	
	private RolRepository rolRepository;
	
	public RolService(RolRepository rolRepository) {
		this.rolRepository = rolRepository;
	}
	
	public Rol guardar(Rol rol) {
		if(rolRepository.findByNombreRol(rol.getNombreRol()) != null) {
			return null;
		}
		return rolRepository.save(rol);
	}
	
	public Rol actualizarRol(Rol rol) {
		if(rolRepository.findByNombreRol(rol.getNombreRol()) != null) {
			return rolRepository.save(rol);
		}
		return null;
	}

	public List<Rol> obtenerRoles(){
		return (List<Rol>) rolRepository.findAll();
	}
	
	public void eliminarRol(Integer idRol) {
		rolRepository.deleteById(idRol);
	}
	
	
}
