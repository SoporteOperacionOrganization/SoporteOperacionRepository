package com.eficacia.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
	public Long contarRegistrosPag(String razonSocial) {
		session = sessionFactory.getCurrentSession();
		query = session.createQuery("SELECT count(a) from Agenda a WHERE a.razonSocial like :razonSocial");
		query.setParameter("razonSocial", "%"+razonSocial+"%");
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
	public List<Agenda> filtrarAgendas(String razonSocial, Integer offset, Integer limite) {
		List<Agenda> agendas;
		session = sessionFactory.getCurrentSession();
		query = session.createQuery("FROM Agenda a where a.razonSocial like :razonSocial");
		query.setParameter("razonSocial", "%"+razonSocial+"%");
		query.setFirstResult(offset!=null?offset:0);
		query.setMaxResults(limite!=null?limite:5);
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
		String totalNoEncontrados="";
		int NoEncontrados=0;
		session = sessionFactory.getCurrentSession();
		for(int i = 0; i < tamano; i++){
			
			String codigoTransaccion = agendas.get(i).getCodigoTransaccion();
			
			query = session.createQuery("DELETE FROM Agenda a WHERE a.codigoTransaccion = :codigoTransaccion");
			query.setParameter("codigoTransaccion", codigoTransaccion);
			Integer resultado=query.executeUpdate();
			if(resultado==0){
				NoEncontrados ++;
				}
		}

		totalNoEncontrados=Integer.toString(NoEncontrados);
	
		return totalNoEncontrados;
	}
	
	
	@Override
	public ArrayList<String> registrosNoEncontrados(List<Agenda> agendas){
		int tamano = agendas.size();
		ArrayList<String> noEncontrados= new ArrayList<>();
		session = sessionFactory.getCurrentSession();
		
		for(int i = 0; i < tamano; i++){
			
			String codigoTransaccion = agendas.get(i).getCodigoTransaccion();
			
			query = session.createQuery("FROM Agenda a WHERE a.codigoTransaccion = :codigoTransaccion");
			query.setParameter("codigoTransaccion", codigoTransaccion);
			List resultado=query.list();
			
			if(resultado.isEmpty()){
				noEncontrados.add(codigoTransaccion);
		}
		}
return noEncontrados;
	}

	@Override
	public void modificarAgenda(Agenda agenda) {
		session = sessionFactory.getCurrentSession();
		session.update(agenda);
	}

	@Override
	public List<Agenda> obtenerAgendasPaginacion(Integer offset, Integer limite) {
		session = sessionFactory.getCurrentSession();
		query = session.createQuery("FROM Agenda");
		query.setFirstResult(offset!=null?offset:0);
		query.setMaxResults(limite!=null?limite:15);
		List<Agenda> agendas = query.list();
		return agendas;
	}
	
}
