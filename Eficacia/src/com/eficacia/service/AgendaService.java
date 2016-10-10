package com.eficacia.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eficacia.model.Agenda;

public interface AgendaService {

	public Agenda obtenerAgenda(String codigoTransaccion);
	
	public List<Agenda> obtenerAgendas();

	public Long contarRegistros();

	public void agregarAgenda(Agenda agenda);
	
	public List<Agenda> filtrarAgendas(String razonSocial);
	
	public String validarExcel(MultipartFile excelFile);
	
	public void modificarAgenda(Agenda agenda);
	
	public void eliminarAgenda(String codigoTransaccion);
	
	public String validarExcelEliminacion(MultipartFile excelFile);
	
}
