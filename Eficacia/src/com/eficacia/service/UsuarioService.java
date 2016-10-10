package com.eficacia.service;

import java.util.List;

import com.eficacia.model.Usuario;

public interface UsuarioService {

	public Usuario obtenerUsuario(String soeid);
	
	public List<Usuario> filtrarUsuarios(String soeid);
	
	public List<Usuario> obtenerUsuarios();
		
	public void agregarUsuario(Usuario usuario);
	
	public void modificarUsuario(Usuario usuario);
	
	public void eliminarUsuario(String soeid);
	
}
