package com.eficacia.dao;

import java.util.List;

import com.eficacia.model.Agenda;

public interface AgendaDao {

	public Agenda obtenerAgenda(String codigoTransaccion);
	
	public List<Agenda> obtenerAgendas();
	 
	public Long contarRegistros();
	
	public void agregarAgenda(Agenda agenda);
	
	public void eliminarAgenda(String codigoTransaccion);
	
	public List<Agenda> filtrarAgendas(String razonSocial);
	 
	public String cargaMasiva(List<Agenda> agendas);
	
	public void modificarAgenda(Agenda agenda);
	
	public String eliminacionMasiva(List<Agenda> agendas);
	
	public List<Agenda> obtenerAgendasPaginacion(Integer offset, Integer limite);
	
}
