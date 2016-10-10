package com.eficacia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eficacia.dao.UsuarioDao;
import com.eficacia.model.Usuario;
@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	public Usuario obtenerUsuario(String soeid) {
		
		return usuarioDao.obtenerUsuario(soeid);
	}

	@Override
	public List<Usuario> obtenerUsuarios() {
		return usuarioDao.obtenerUsuarios();
	}

	@Override
	public void agregarUsuario(Usuario usuario) {
		usuarioDao.agregarUsuario(usuario);
	}

	@Override
	public void modificarUsuario(Usuario usuario) {
		Usuario entity = usuarioDao.obtenerUsuario(usuario.getSoeid());
		if(entity != null){
			entity.setSoeid(usuario.getSoeid());
			entity.setPassword(usuario.getPassword());
			entity.setNombre(usuario.getNombre());
			entity.setApellidoPaterno(usuario.getApellidoPaterno());
			entity.setApellidoMaterno(usuario.getApellidoMaterno());
			entity.setTelefono(usuario.getTelefono());
			entity.setRol(usuario.getRol());
		}
		//usuarioDao.modificarUsuario(usuario);
	}

	@Override
	public void eliminarUsuario(String soeid) {
		usuarioDao.eliminarUsuario(soeid);
	}

	@Override
	public List<Usuario> filtrarUsuarios(String soeid) {
		return usuarioDao.filtrarUsuarios(soeid);
	}

}
