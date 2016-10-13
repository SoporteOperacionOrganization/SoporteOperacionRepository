package com.eficacia.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eficacia.model.Agenda;
import com.eficacia.model.File;
import com.eficacia.service.AgendaService;
import com.eficacia.validator.AgendaValidator;
import com.eficacia.validator.FileValidator;

@Controller
public class AgendaController {

	@Autowired
	private AgendaService agendaService;
	
	@Autowired
	private AgendaValidator agendaValidator;
	
	@Autowired
	private FileValidator fileValidator;
	
	@InitBinder("agenda")
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.setValidator(agendaValidator);
	}
	
	@InitBinder("file")
	public void initFileBinder(WebDataBinder webDataBinder){
		webDataBinder.setValidator(fileValidator);
	}
	
	@RequestMapping(value = "/listarAgendasPaginacion", method = RequestMethod.GET)
	public String listarAgendasPaginacion(Model model, Integer offset, Integer maxResults){
		List<Agenda> agendas = agendaService.obtenerAgendasPaginacion(offset, maxResults);
		model.addAttribute("agendas", agendas);
		model.addAttribute("offset", offset);
		return "agendas/listarAgendasPaginacion";
	}
	
	@RequestMapping(value = "/listarAgendas", method = RequestMethod.GET)
	public String listarAgendas(Model model){
		List<Agenda> agendas = agendaService.obtenerAgendas();
		model.addAttribute("agendas", agendas);
		return "agendas/listarAgendas";
	}
	
	@RequestMapping(value = "/filtrarAgendas", method = RequestMethod.GET)
	public String filtrarAgendas(Model model, @RequestParam("razonSocial") String razonSocial){
		List<Agenda> agendas = agendaService.filtrarAgendas(razonSocial);
		model.addAttribute("agendas", agendas);
		return "agendas/listarAgendas";
	}
	
	@RequestMapping(value = "/agregarAgenda", method = RequestMethod.GET)
	public String agregarAgenda(Model model){
		Calendar fecha = new GregorianCalendar();
		String fechaTransaccion= fecha.get(Calendar.DAY_OF_MONTH)+"/"+(fecha.get(Calendar.MONTH)+1)+"/"+fecha.get(Calendar.YEAR);
		model.addAttribute("agenda", new Agenda());
		model.addAttribute("fechaTransaccion", fechaTransaccion);
		return "agendas/formularioAgenda";
	}
	
	@RequestMapping(value = "/agregarAgenda", method = RequestMethod.POST)
	public String insertarAgenda(Model model, @Valid Agenda agenda, BindingResult result){
		if(result.hasErrors()){
			return "agendas/formularioAgenda";
		}
		agendaService.agregarAgenda(agenda);
		return "redirect:/listarAgendas";
	}
	
	@RequestMapping(value = "/editarAgenda/{codigoTransaccion}", method = RequestMethod.GET)
	public String editarAgenda(Model model, @PathVariable String codigoTransaccion){
		Agenda agenda = agendaService.obtenerAgenda(codigoTransaccion);
		model.addAttribute("agenda",agenda);
		
		return "agendas/formularioAgendaModificar";
	}
	
	@RequestMapping(value = "/editarAgenda/{codigoTransaccion1}", method = RequestMethod.POST)
	public String modificarAgenda(@Valid Agenda agenda, BindingResult result,@PathVariable("codigoTransaccion1") String codigoTransaccion, Model model){
		if(result.hasErrors()){
			return "agendas/formularioAgendaModificar";
		}
		agendaService.modificarAgenda(agenda);
		return "redirect:/listarAgendas";
	}
	
	@RequestMapping(value="/eliminarAgenda/{codigoTransaccion}", method = RequestMethod.GET)
	public String eliminarAgenda(Model model, @PathVariable String codigoTransaccion){
		agendaService.eliminarAgenda(codigoTransaccion);
		return "redirect:/listarAgendas";
	}	
	
	@RequestMapping(value = "/exportarAgendas", method = RequestMethod.GET)
	public String exportarAgendas(Model model){
		List<Agenda> agendas = agendaService.obtenerAgendas();
		model.addAttribute("agendas", agendas);
		return "VistaExcel";
	}
	
	@RequestMapping(value = "/cargaMasiva", method = RequestMethod.GET)
	public String cargaMasiva(Model model){
		model.addAttribute("file", new File());
		return "agendas/cargaMasiva";
	}
	
	@RequestMapping(value = "/cargarExcel", method = RequestMethod.POST)
	public String cargarExcel(@RequestParam("archivo") MultipartFile excelfile, @Valid File file, BindingResult result, final RedirectAttributes redirectAttributes , Model model){
		if(result.hasErrors()){
			return "agendas/cargaMasiva";
		}
		String extension = excelfile.getOriginalFilename().substring(excelfile.getOriginalFilename().lastIndexOf(".") + 1);
		System.out.println("extsion " + extension);
		if(!"xlsx".equals(extension)){
			redirectAttributes.addFlashAttribute("extensionError","Verifica que el archivo tenga la extensión solicitada xlsx, xls.");
		}else{
			String estatus = agendaService.validarExcel(excelfile);
			if("".equals(estatus)){
				redirectAttributes.addFlashAttribute("procesoCorrecto", "El proceso ha terminado exitosamente");
			}
			redirectAttributes.addFlashAttribute("estatus", estatus);
		}
		return "redirect:/cargaMasiva";
	}
	
	@RequestMapping(value = "/eliminacionMasiva", method = RequestMethod.GET)
	public String eliminacionMasiva(Model model){
		model.addAttribute("file", new File());
		return "agendas/eliminacionMasiva";
	}
	
	@RequestMapping(value = "/cargarExcelEliminacion", method = RequestMethod.POST)
	public String cargarExcelEliminacion(@RequestParam("archivo") MultipartFile excelfile,  @Valid File file, BindingResult result, Model model){
		if(result.hasErrors()){	
			return "agendas/eliminacionMasiva";
		}
		String estatus = agendaService.validarExcelEliminacion(excelfile);
		if(estatus.equals("")){
			model.addAttribute("procesoCorrecto", "El proceso ha terminado exitosamente");
		}
		model.addAttribute("estatus", estatus);
		return "agendas/eliminacionMasiva";
	}
	
}
