package com.eficacia.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eficacia.model.Usuario;
@Repository
public class UsuarioDaoImpl implements UsuarioDao {
	
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
			System.out.println("ID: " + userList.get(0).getId());
			System.out.println("Soeid: " + userList.get(0).getSoeid());
			System.out.println("Password: " + userList.get(0).getPassword());
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
	public void agregarUsuario(Usuario usuario) {
		session = sessionFactory.getCurrentSession();
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
	public List<Usuario> filtrarUsuarios(String soeid) {
		session = sessionFactory.getCurrentSession();
		List<Usuario> usuarios ;
		query = session.createQuery("FROM Usuario u where u.soeid like :soeid");
		query.setParameter("soeid", "%"+soeid+"%");
		usuarios = query.list();
		return usuarios;
		
	}

}
