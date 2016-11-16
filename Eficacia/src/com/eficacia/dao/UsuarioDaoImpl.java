package com.eficacia.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.eficacia.model.Agenda;
import com.eficacia.model.Usuario;
@Repository
public class UsuarioDaoImpl implements UsuarioDao {
	
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session;
	private Query query;
	@Override
	public Usuario obtenerUsuario(String soeid) {
		session = sessionFactory.getCurrentSession();
		List<Usuario> userList = new ArrayList<Usuario>();
		query = session.createQuery("FROM Usuario u where u.soeid = :soeid");
		query.setParameter("soeid", soeid);
		userList = query.list();
		if(userList.size() > 0){
		
			return userList.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public List<Usuario> obtenerUsuarios() {
		session = sessionFactory.getCurrentSession();
		List<Usuario> usuarios = new ArrayList<Usuario>();
		query = session.createQuery("FROM Usuario");
		usuarios = query.list();
		return usuarios;
	}
	
	@Override
	public List<Usuario> obtenerUsuariosPaginacion(Integer offset, Integer limite) {
		session = sessionFactory.getCurrentSession();
		query = session.createQuery("FROM Usuario");
		query.setFirstResult(offset!=null?offset:0);
		query.setMaxResults(limite!=null?limite:15);
		List<Usuario> usuarios = query.list();
		return usuarios;
	}
	

	@Override
	public void agregarUsuario(Usuario usuario) {
		session = sessionFactory.getCurrentSession();
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		session.save(usuario);
	}

	@Override
	public void eliminarUsuario(String soeid) {
		session = sessionFactory.getCurrentSession();
		Usuario usuario = obtenerUsuario(soeid);
		session.delete(usuario);
	}

	@Override
	public void modificarUsuario(Usuario usuario) {
		session = sessionFactory.getCurrentSession();
		session.update(usuario);
	}

	@Override
	public List<Usuario> filtrarUsuarios(String soeid, Integer offset, Integer limite) {
		session = sessionFactory.getCurrentSession();
		List<Usuario> usuarios ;
		query = session.createQuery("FROM Usuario u where u.soeid like :soeid");
		query.setParameter("soeid", "%"+soeid+"%");
		query.setFirstResult(offset!=null?offset:0);
		query.setMaxResults(limite!=null?limite:15);
		usuarios = query.list();
		return usuarios;
		
	}

	@Override
	public Long contarRegistros() {
		session = sessionFactory.getCurrentSession();
		query = session.createQuery("SELECT count(u) from Usuario u");
		Long count = (Long) query.uniqueResult();
		return count;
	}
	
	@Override
	public Long contarRegistrosCond(String soeid) {
		session = sessionFactory.getCurrentSession();
		query = session.createQuery("SELECT count(u) from Usuario u WHERE u.soeid like :soeid");
		query.setParameter("soeid",  "%"+soeid+"%");
		Long count = (Long) query.uniqueResult();
		return count;
	}
	
	@Override
	public Long validarExistenciaUsuario(String soeid) {
		session = sessionFactory.getCurrentSession();
		query = session.createQuery("SELECT count(u) from Usuario u WHERE u.soeid = :soeid");
		query.setParameter("soeid", soeid);
		Long count = (Long) query.uniqueResult();
		return count;
	}

	@Override
	public void modificarCredencialesExpiradas(boolean estatusCredencialesExpiradas, String soeid) {
		session = sessionFactory.getCurrentSession();
		query = session.createQuery("UPDATE Usuario u SET usuarioCredencialesNoExpiradas = :usuarioCredencialesNoExpiradas WHERE soeid = :soeid");
		query.setParameter("usuarioCredencialesNoExpiradas", estatusCredencialesExpiradas);
		query.setParameter("soeid", soeid);
		query.executeUpdate();
	}

	@Override
	public void renovarCredenciales(String passwordActual, String passwordNuevo, String fechaTransaccion, String soeid) {
		session = sessionFactory.getCurrentSession();
		query = session.createQuery("UPDATE Usuario u SET password = :password, usuarioContrasenaAnterior = :usuarioContrasenaAnterior, usuarioCredencialesNoExpiradas = :usuarioCredencialesNoExpiradas, usuarioFechaExpiracionContrasena = :usuarioFechaExpiracionContrasena WHERE soeid = :soeid");
		query.setParameter("password", passwordNuevo);
		query.setParameter("usuarioContrasenaAnterior", passwordActual);
		query.setParameter("soeid", soeid);
		query.setParameter("usuarioCredencialesNoExpiradas", true);
		query.setParameter("usuarioFechaExpiracionContrasena", fechaTransaccion);
		query.executeUpdate();
	}

	/*@Override
	public void modificarContrase�aReset(String soeid) {
		session = sessionFactory.getCurrentSession();
	}*/

}
