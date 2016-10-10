package com.eficacia.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eficacia.model.Agenda;

@Repository
public class AgendaDaoImpl implements AgendaDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session;
	private Query query;
	
	@Override
	public Agenda obtenerAgenda(String codigoTransaccion) {
		session = sessionFactory.getCurrentSession();
		query = session.createQuery("FROM Agenda a where a.codigoTransaccion = :codigoTransaccion");
		query.setParameter("codigoTransaccion", codigoTransaccion);
		Agenda agenda=(Agenda)query.uniqueResult();
		return agenda;
		/*session = sessionFactory.getCurrentSession();
		List<Agenda> agendas = new ArrayList<Agenda>();
		query = session.createQuery("FROM Agenda a where a.codigoTransaccion = :codigoTransaccion");
		query.setParameter("codigoTransaccion", codigoTransaccion);
		agendas = query.list();
		if(agendas.size() > 0){
			return agendas.get(0);
		}else{
			return null;
		}*/
		
	}
	
	@Override
	public List<Agenda> obtenerAgendas() {
		List<Agenda> agendas = new ArrayList<Agenda>();
		session = sessionFactory.getCurrentSession();
		query = session.createQuery("FROM Agenda");
		agendas = query.list();
		return agendas;
	}

	@Override
	public Long contarRegistros() {
		session = sessionFactory.getCurrentSession();
		query = session.createQuery("SELECT count(a) from Agenda a");
		Long count = (Long) query.uniqueResult();
		return count;
	}

	@Override
	public void agregarAgenda(Agenda agenda) {
		session = sessionFactory.getCurrentSession();
		session.save(agenda);
	}

	@Override
	public void eliminarAgenda(String codigoTransaccion){
		 session = sessionFactory.getCurrentSession();
		 Agenda agenda = obtenerAgenda(codigoTransaccion);
		 session.delete(agenda);
		 //query = session.createQuery("DELETE FROM Agenda a WHERE a.codigoTransaccion = :codigoTransaccion");
		 //query.setParameter("codigoTransaccion", codigoTransaccion);
		 //query.executeUpdate();
	}
	
	@Override
	public List<Agenda> filtrarAgendas(String razonSocial) {
		List<Agenda> agendas;
		session = sessionFactory.getCurrentSession();
		query = session.createQuery("FROM Agenda a where a.razonSocial like :razonSocial");
		query.setParameter("razonSocial", "%"+razonSocial+"%");
		agendas = query.list();
		return agendas;
	}

	@Override
	public String cargaMasiva(List<Agenda> agendas) {
		int tamano = agendas.size();
		SQLQuery query;
		for(Agenda age : agendas){
			
			session = sessionFactory.getCurrentSession();
			session.save(age);
			
			/*session = sessionFactory.openSession();
			query = session.createSQLQuery("INSERT INTO agenda(agendaCodigoTransaccion, agendaFechaTransaccion, agendaFechaCiere, agendaNumeroCliente, "
					+ "agendaRazonSocial, agendaNombreRepresentante, agendaNumeroTelefono , agendaSoeid , agendaEjecutivo , agendaSede) values (?,?,?,?,?,?,?,?,?,?)");
		    query.setParameter(0, age.getCodigoTransaccion());
		    query.setParameter(1, age.getFechaTransaccion());
		    query.setParameter(2, age.getFechaCierre());
		    query.setParameter(3, age.getNumeroCliente());
		    query.setParameter(4, age.getRazonSocial());
		    query.setParameter(5, age.getNombreRepresentante());
		    query.setParameter(6, age.getNumeroTelefono());
		    query.setParameter(7, age.getSoeid());
		    query.setParameter(8, age.getEjecutivo());
		    query.setParameter(9, age.getSede());
		    
		    query.executeUpdate();
			
			session.flush();
			session.clear();*/
		}
		
		return "";
	}
	
	@Override
	public String eliminacionMasiva(List<Agenda> agendas){
		int tamano = agendas.size();
		System.out.println("Tamaño:"+tamano);
		session = sessionFactory.getCurrentSession();
		for(int i = 0; i < tamano; i++){
			System.out.println("Codigo transaccion:"+agendas.get(i).getCodigoTransaccion());
			String codigoTransaccion = agendas.get(i).getCodigoTransaccion();
			
			query = session.createQuery("DELETE FROM Agenda a WHERE a.codigoTransaccion = :codigoTransaccion");
			query.setParameter("codigoTransaccion", codigoTransaccion);
			query.executeUpdate();
		}
		return "";
	}

	@Override
	public void modificarAgenda(Agenda agenda) {
		session = sessionFactory.getCurrentSession();
		session.update(agenda);
	}

}
