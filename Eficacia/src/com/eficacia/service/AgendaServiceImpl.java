package com.eficacia.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.eficacia.dao.AgendaDao;
import com.eficacia.model.Agenda;

@Service
@Transactional
public class AgendaServiceImpl implements AgendaService {

	Calendar date = new GregorianCalendar();
	Random randomGenerator = new Random();
	
	@Autowired
	private AgendaDao agendaDao;
	
	@Override
	public Agenda obtenerAgenda(String codigoTransaccion){
		return agendaDao.obtenerAgenda(codigoTransaccion);
	}
	
	@Override
	public List<Agenda> obtenerAgendas() {
		return agendaDao.obtenerAgendas();
	}

	@Override
	public Long contarRegistros() {
		return agendaDao.contarRegistros();
	}

	@Override
	public void agregarAgenda(Agenda agenda) {
		String fecha = date.get(Calendar.YEAR) + "" + (date.get(Calendar.MONTH)+1) + "" + date.get(Calendar.DAY_OF_MONTH);
		String hora = String.format("%02d%02d%02d", date.get(Calendar.HOUR_OF_DAY), date.get(Calendar.MINUTE), date.get(Calendar.SECOND));
		String noCliente = agenda.getNumeroCliente();
		int cadenaVerificadora = randomGenerator.nextInt(900) + 100;
		String codigoTransaccion = fecha + hora + noCliente + cadenaVerificadora;
		//System.out.println("CODIGO DE TRANSACCION " + codigoTransaccion);
		agenda.setCodigoTransaccion(codigoTransaccion);
		agendaDao.agregarAgenda(agenda);
	}

	@Override
	public List<Agenda> filtrarAgendas(String razonSocial) {
		return agendaDao.filtrarAgendas(razonSocial);
	}

	@Override
	public void eliminarAgenda(String codigoTransaccion){
		agendaDao.eliminarAgenda(codigoTransaccion);
	}
	
	
	@Override
	public void modificarAgenda(Agenda agenda) {
		/*Agenda entity = agendaDao.obtenerAgenda(agenda.getCodigoTransaccion());
		if(entity != null){
			entity.setId(agenda.getId());
			entity.setCodigoTransaccion(agenda.getCodigoTransaccion());
			entity.setFechaTransaccion(agenda.getFechaTransaccion());
			entity.setFechaCierre(agenda.getFechaCierre());
			entity.setNumeroCliente(agenda.getNumeroCliente());
			entity.setRazonSocial(agenda.getRazonSocial());
			entity.setNombreRepresentante(agenda.getNombreRepresentante());
			entity.setNumeroTelefono(agenda.getNumeroTelefono());
			entity.setSoeid(agenda.getSoeid());
			entity.setEjecutivo(agenda.getEjecutivo());
			entity.setSede(agenda.getSede());
		}*/
		agendaDao.modificarAgenda(agenda);
	}
	
	@Override
	public String validarExcel(MultipartFile excelFile) {
		List<String> letras = Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
		String estatusCarga = "";
		int i = 0;
		try{
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			
			List<Agenda> agendas = new ArrayList<>();
			
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile.getInputStream());
			XSSFSheet worksheet = workbook.getSheetAt(0);
			while (i <= worksheet.getLastRowNum()) {
				Agenda agenda = new Agenda();
				XSSFRow row = worksheet.getRow(i++);
				/*if(validarCamposNumericos(row,0)){agenda.setCodigoTransaccion(row.getCell(0).getRawValue());}
				else{estatusCarga = "Error en linea " + i + " celda " + letras.get(0);break;}*/
				
				if(validarCamposFecha(row,0)){agenda.setFechaTransaccion(df.format(row.getCell(0).getDateCellValue()));}
				else{estatusCarga = "Error en linea " + i + " celda " + letras.get(0);break;}
				
				if(validarCamposFecha(row,1)){agenda.setFechaCierre(df.format(row.getCell(1).getDateCellValue()));}
				else{estatusCarga = "Error en linea " + i + " celda " + letras.get(1);break;}
				
				
				if(validarCamposNumericos(row,2)){
					agenda.setNumeroCliente(row.getCell(2).getRawValue());
					System.out.println("dentroi de setNumeroCliente");
					}
				else{
					estatusCarga = "Error en linea " + i + " celda " + letras.get(2);
					break;
					}
				
				String fecha = date.get(Calendar.YEAR) + "" + (date.get(Calendar.MONTH)+1) + "" + date.get(Calendar.DAY_OF_MONTH);
				String hora = String.format("%02d%02d%02d", date.get(Calendar.HOUR_OF_DAY), date.get(Calendar.MINUTE), date.get(Calendar.SECOND));
				String noCliente = agenda.getNumeroCliente();
				int cadenaVerificadora = randomGenerator.nextInt(900) + 100;
				String codigoTransaccion = fecha + hora + noCliente + cadenaVerificadora;
				agenda.setCodigoTransaccion(codigoTransaccion);
				
				agenda.setRazonSocial(row.getCell(3).getStringCellValue());
				agenda.setNombreRepresentante(row.getCell(4).getStringCellValue());
				agenda.setNumeroTelefono(row.getCell(5).getRawValue());
				
				agenda.setSoeid(String.valueOf(row.getCell(6).getStringCellValue()));
				
				agenda.setEjecutivo(row.getCell(7).getStringCellValue());
				agenda.setSede(row.getCell(8).getStringCellValue());
				
				agendas.add(agenda);
			}		
			if(estatusCarga.equals("")){
				agendaDao.cargaMasiva(agendas);
			}
				workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estatusCarga;
	}
	
	public ArrayList<String> registrosNoEncontrados(MultipartFile excelFile){
		ArrayList<String> noEncontrados= new ArrayList<>();
		List<String> letras = Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
		String estatusCarga = "";
		int i = 0;
		
		try{
			List<Agenda> agendas = new ArrayList<>();
			
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile.getInputStream());
			XSSFSheet worksheet = workbook.getSheetAt(0);
			while (i <= worksheet.getLastRowNum()) {
				Agenda agenda = new Agenda();
				XSSFRow row = worksheet.getRow(i++);
				
				if(validarCodigoTransaccion(row,0)){agenda.setCodigoTransaccion(row.getCell(0).getStringCellValue());}
				else{estatusCarga = "Error en linea " + i + " celda " + letras.get(0);break;}
			
				agendas.add(agenda);
			}		
			if(estatusCarga.equals("")){
					noEncontrados=agendaDao.registrosNoEncontrados(agendas);
			}
				workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return noEncontrados;
	}
	
	
	public String validarExcelEliminacion(MultipartFile excelFile) {
		List<String> letras = Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
		String estatusCarga = "";
		int i = 0;
		try{
			List<Agenda> agendas = new ArrayList<>();
			
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile.getInputStream());
			XSSFSheet worksheet = workbook.getSheetAt(0);
			while (i <= worksheet.getLastRowNum()) {
				Agenda agenda = new Agenda();
				XSSFRow row = worksheet.getRow(i++);
				//agenda.setCodigoTransaccion(row.getCell(0).getRawValue());
				
				if(validarCodigoTransaccion(row,0)){agenda.setCodigoTransaccion(row.getCell(0).getStringCellValue());}
				else{estatusCarga = "Error en linea " + i + " celda " + letras.get(0);break;}
								
				
				/*agenda.setRazonSocial(row.getCell(4).getStringCellValue());
				agenda.setNombreRepresentante(row.getCell(5).getStringCellValue());
				agenda.setNumeroTelefono(row.getCell(6).getRawValue());
				agenda.setSoeid(row.getCell(7).getRawValue());
				agenda.setEjecutivo(row.getCell(8).getStringCellValue());
				agenda.setSede(row.getCell(9).getStringCellValue());*/
				
				
				/*String pattern = "dd/MM/yyyy";
			    SimpleDateFormat formatter = new SimpleDateFormat(pattern);*/
				//Date date = formatter.parse(text);
				//agenda.setFechaTransaccion(date);
				// persist data into database in here
			    //agendaService.agregarAgenda(agenda);
				agendas.add(agenda);
			}		
			if(estatusCarga.equals("")){
					agendaDao.eliminacionMasiva(agendas);
			}
				workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estatusCarga;
	}
	
	
	
	public boolean validarCamposFecha(XSSFRow row, int cell){
		boolean estatus = true;
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		System.out.println("NULO " +row.getCell(cell, row.RETURN_BLANK_AS_NULL));
		if(row.getCell(cell) == null){
			estatus = false;
		}
		if(row.getCell(cell).getCellType() == Cell.CELL_TYPE_BLANK){
			estatus = false;
		}
		if(row.getCell(cell).getCellType() != Cell.CELL_TYPE_NUMERIC){
			estatus = false;
		}
		return estatus;
	}

	public boolean validarCamposNumericos(XSSFRow row, int cell){
		boolean estatus = true;
		//System.out.println(row.getCell(cell, row.RETURN_BLANK_AS_NULL));
		if(row.getCell(cell) == null){
			estatus = false;
		}else if(row.getCell(cell).getCellType() == Cell.CELL_TYPE_BLANK){
			estatus = false;
		}else if(row.getCell(cell).getRawValue().length() != 5){
			estatus = false;
		}else if(row.getCell(cell).getCellType() != Cell.CELL_TYPE_NUMERIC){
			estatus = false;
		}else if(!row.getCell(cell).getRawValue().matches("[0-9]+")){
			estatus = false;
		}
		return estatus;
	}
	
	public boolean validarCodigoTransaccion(XSSFRow row, int cell){
		boolean estatus = true;
		//System.out.println(row.getCell(cell, row.RETURN_BLANK_AS_NULL));
		if(row.getCell(cell) == null){
			estatus = false;
		}else if(row.getCell(cell).getCellType() == Cell.CELL_TYPE_BLANK){
			estatus = false;
		}else if(row.getCell(cell).getStringCellValue().length() != 22){
			estatus = false;
		}
		System.out.println("LONIG " + row.getCell(cell).getStringCellValue().length());
		System.out.println("VALOR " + row.getCell(cell).getStringCellValue());
		return estatus;
	}

	@Override
	public List<Agenda> obtenerAgendasPaginacion(Integer offset, Integer limite) {
		return agendaDao.obtenerAgendasPaginacion(offset, limite);
	}
	
}
