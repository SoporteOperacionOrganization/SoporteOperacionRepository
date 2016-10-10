package com.eficacia.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
			
			// Creates a workbook object from the uploaded excelfile
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile.getInputStream());
			// Creates a worksheet object representing the first sheet
			XSSFSheet worksheet = workbook.getSheetAt(0);
			// Reads the data in excel file until last row is encountered
			while (i <= worksheet.getLastRowNum()) {
				// Creates an object for the UserInfo Model
				Agenda agenda = new Agenda();
				// Creates an object representing a single row in excel
				XSSFRow row = worksheet.getRow(i++);
				// Sets the Read data to the model class
				//String text = row.getCell(1).getRawValue();
				//agenda.setCodigoTransaccion(row.getCell(0).getRawValue());
				if(validarCamposNumericos(row,0)){agenda.setCodigoTransaccion(row.getCell(0).getRawValue());}
				else{estatusCarga = "Error en linea " + i + " celda " + letras.get(0);break;}
				
				if(validarCamposFecha(row,1)){agenda.setFechaTransaccion(df.format(row.getCell(1).getDateCellValue()));}
				else{estatusCarga = "Error en linea " + i + " celda " + letras.get(1);break;}
				
				/*if(validarCamposFecha(row,1)){agenda.setFechaTransaccion(row.getCell(1).getDateCellValue());}
				else{estatusCarga = "Error en linea " + i + " celda " + letras.get(1);break;}*/
				
				if(validarCamposFecha(row,2)){agenda.setFechaCierre(df.format(row.getCell(2).getDateCellValue()));}
				else{estatusCarga = "Error en linea " + i + " celda " + letras.get(2);break;}
				
				//agenda.setFechaCierre(row.getCell(2).getDateCellValue());
				
				if(validarCamposNumericos(row,3)){agenda.setNumeroCliente(row.getCell(3).getRawValue());}
				else{estatusCarga = "Error en linea " + i + " celda " + letras.get(3);break;}
				
				
				agenda.setRazonSocial(row.getCell(4).getStringCellValue());
				agenda.setNombreRepresentante(row.getCell(5).getStringCellValue());
				agenda.setNumeroTelefono(row.getCell(6).getRawValue());
				
				agenda.setSoeid(String.valueOf(row.getCell(7).getStringCellValue()));
				
				agenda.setEjecutivo(row.getCell(8).getStringCellValue());
				agenda.setSede(row.getCell(9).getStringCellValue());
				
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
	
	public String validarExcelEliminacion(MultipartFile excelFile) {
		List<String> letras = Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
		String estatusCarga = "";
		int i = 0;
		try{
			List<Agenda> agendas = new ArrayList<>();
			
			// Creates a workbook object from the uploaded excelfile
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile.getInputStream());
			// Creates a worksheet object representing the first sheet
			XSSFSheet worksheet = workbook.getSheetAt(0);
			// Reads the data in excel file until last row is encountered
			while (i <= worksheet.getLastRowNum()) {
				// Creates an object for the UserInfo Model
				Agenda agenda = new Agenda();
				// Creates an object representing a single row in excel
				XSSFRow row = worksheet.getRow(i++);
				// Sets the Read data to the model class
				//String text = row.getCell(1).getRawValue();
				//agenda.setCodigoTransaccion(row.getCell(0).getRawValue());
				
				if(validarCamposNumericos(row,0)){agenda.setCodigoTransaccion(row.getCell(0).getRawValue());}
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
		//System.out.println("FECHA " + df.format(row.getCell(cell).getDateCellValue()));
		if(row.getCell(cell) == null){
			estatus = false;
			System.out.println("EL CAMPO ES NULO");
		}
		if(row.getCell(cell).getCellType() == Cell.CELL_TYPE_BLANK){
			estatus = false;
		}
		if(row.getCell(cell).getCellType() == Cell.CELL_TYPE_NUMERIC){
			/*if(DateUtil.isCellDateFormatted(row.getCell(cell))){
				System.out.println("FECHA CORRECTA --------------");
			}else{
				estatus = false;
			}*/
		}else{
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
		}
		return estatus;
	}
	
}
