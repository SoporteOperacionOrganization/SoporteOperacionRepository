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
	public Long contarRegistrosPag(String razonSocial) {
		return agendaDao.contarRegistrosPag(razonSocial);
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
	public List<Agenda> filtrarAgendas(String razonSocial, Integer offset, Integer limite) {
		return agendaDao.filtrarAgendas(razonSocial, offset, limite);
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
		  System.out.println("validarExcel");
          List<String> letras = Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
          String estatusCarga = "";
          int i = 3;
          try{
                 DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                 
                 List<Agenda> agendas = new ArrayList<>();
                 //System.out.println("Excel File: " + excelFile.);
                 XSSFWorkbook workbook = new XSSFWorkbook(excelFile.getInputStream());          
                 XSSFSheet worksheet = workbook.getSheetAt(0);
                 while (i <= worksheet.getLastRowNum()) {
                        Agenda agenda = new Agenda();
                        XSSFRow row = worksheet.getRow(i++);
                        /*if(validarCamposNumericos(row,0)){agenda.setCodigoTransaccion(row.getCell(0).getRawValue());}
                        else{estatusCarga = "Error en linea " + i + " celda " + letras.get(0);break;}*/
                        
                        if(validarCamposFecha(row,0)){agenda.setFechaTransaccion(df.format(row.getCell(0).getDateCellValue()));}
                        else{estatusCarga = "Error en linea " + i + " celsda " + letras.get(0);break;}
                        
                        if(validarCamposFecha(row,1)){agenda.setFechaCierre(df.format(row.getCell(1).getDateCellValue()));}
                        else{estatusCarga = "Error en linea " + i + " celda " + letras.get(1);break;}          
                        /*System.out.println("Antes del if  validarCamposNumericos");
                        if(validarCamposNumericos(row,2)){
                               //agenda.setNumeroCliente(row.getCell(2).getRawValue());
                        	  //agenda.setNumeroCliente(row.getCell(2).getStringCellValue());
                        		String cliente=row.getCell(2).getStringCellValue();
                        		System.out.println("Cliente validarExcel: "+cliente);
                                agenda.setNumeroCliente(cliente);
                               }
                        else{
                               estatusCarga = "Error en linea " + i + " celda " + letras.get(2);
                               break;
                               }
                               */
                        //System.out.println("return blank as null " +row.getCell(2, row.RETURN_BLANK_AS_NULL));
                        String noCliente=row.getCell(2, row.RETURN_BLANK_AS_NULL).getStringCellValue();
                        //System.out.println("No cliente convertido " +noCliente2);
                        agenda.setNumeroCliente(noCliente);
   
                        String fecha = date.get(Calendar.YEAR) + "" + (date.get(Calendar.MONTH)+1) + "" + date.get(Calendar.DAY_OF_MONTH);
                        String hora = String.format("%02d%02d%02d", date.get(Calendar.HOUR_OF_DAY), date.get(Calendar.MINUTE), date.get(Calendar.SECOND));
                        //String noCliente = agenda.getNumeroCliente();
                        System.out.println("#Cliente: "+noCliente);
                        int cadenaVerificadora = randomGenerator.nextInt(900) + 100;
                        String codigoTransaccion = fecha + hora + noCliente + cadenaVerificadora;
                        agenda.setCodigoTransaccion(codigoTransaccion);
                        System.out.println("setCodigoTransaccion");
                        agenda.setRazonSocial(row.getCell(3).getStringCellValue());
                        agenda.setNombreRepresentante(row.getCell(4).getStringCellValue());
                        String noTelefono=row.getCell(5,row.RETURN_BLANK_AS_NULL).getStringCellValue();
                        agenda.setNumeroTelefono(noTelefono);
                        //agenda.setNumeroTelefono(row.getCell(5).getRawValue());                        
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
		int i = 3;
		
		try{
			List<Agenda> agendas = new ArrayList<>();
			
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile.getInputStream());
			XSSFSheet worksheet = workbook.getSheetAt(0);
			while (i <= worksheet.getLastRowNum()) {
				Agenda agenda = new Agenda();
				XSSFRow row = worksheet.getRow(i++);
				
				//if(validarCodigoTransaccion(row,0)){agenda.setCodigoTransaccion(row.getCell(0).getStringCellValue());}
				//else{estatusCarga = "Error en linea " + i + " celda " + letras.get(0);break;}
				agenda.setCodigoTransaccion(row.getCell(0).getStringCellValue());
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
		int i = 3;
		try{
			List<Agenda> agendas = new ArrayList<>();
			
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile.getInputStream());
			XSSFSheet worksheet = workbook.getSheetAt(0);
			while (i <= worksheet.getLastRowNum()) {
				Agenda agenda = new Agenda();
				XSSFRow row = worksheet.getRow(i++);
				//agenda.setCodigoTransaccion(row.getCell(0).getRawValue());
				
				//if(validarCodigoTransaccion(row,0)){agenda.setCodigoTransaccion(row.getCell(0).getStringCellValue());}
				//else{estatusCarga = "Error en linea " + i + " celda " + letras.get(0);break;}
				agenda.setCodigoTransaccion(row.getCell(0).getStringCellValue());
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
		System.out.println("Dato " +row.getCell(cell, row.RETURN_BLANK_AS_NULL));
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
		System.out.println("Dato" +row.getCell(cell, row.RETURN_BLANK_AS_NULL));
		
		if(row.getCell(cell) == null){
			System.out.println("Entro a validacion de nulos");
			estatus = false;
		}else if(row.getCell(cell).getCellType() == Cell.CELL_TYPE_BLANK){
			System.out.println("Entro a validacion de celda vacia");
			estatus = false;
		}else //if(row.getCell(cell).getRawValue().length() != 5){
			//System.out.println("Entro a validacion de longitud de 5 digitos");
			//estatus = false;
		//}else 
			//if(row.getCell(cell).getCellType() != Cell.CELL_TYPE_NUMERIC){
			//System.out.println("Entro a validacion de formato de celda numerica");
			//estatus = false;
		//}else 
			//if(!row.getCell(cell).getRawValue().matches("[0-9]+")){
			//System.out.println("Entro a validacion de solamente digitos");
			//estatus = false;
		//}
		System.out.println("Clietne " + row.getCell(cell).getRawValue()+ "estatus:"+estatus);
		return estatus;
	}
	
	public boolean validarCamposNoNumericos(XSSFRow row, int cell){
		boolean estatus = true;
		if(row.getCell(cell) == null){
			estatus = false;
		}
		return estatus;
	}
	
	/*public boolean validarCodigoTransaccion(XSSFRow row, int cell){
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
	}*/

	@Override
	public List<Agenda> obtenerAgendasPaginacion(Integer offset, Integer limite) {
		return agendaDao.obtenerAgendasPaginacion(offset, limite);
	}
	
}
