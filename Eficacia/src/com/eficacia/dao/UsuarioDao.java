package com.eficacia.dao;

import java.util.List;

import com.eficacia.model.Usuario;

public interface UsuarioDao {
	
	public Usuario obtenerUsuario(String Soeid);
	
	public List<Usuario> filtrarUsuarios(String soeid);

	public List<Usuario> obtenerUsuarios();
	
	public List<Usuario> obtenerUsuariosPaginacion(Integer offset, Integer limite);
	
	public void agregarUsuario(Usuario usuario);
	
	public void eliminarUsuario(String soeid);
	
	public void modificarUsuario(Usuario usuario);
	
	public Long contarRegistros();
	
}
