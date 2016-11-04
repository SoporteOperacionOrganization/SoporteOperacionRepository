package com.eficacia.controller;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eficacia.custommodel.CustomUsuario;
import com.eficacia.model.Usuario;
import com.eficacia.service.UsuarioService;

@Controller
@SessionAttributes("UsuarioSesion")
public class LoginController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model){
		return "redirect:/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model){
		return "login/login";
	}
	
	@RequestMapping(value = "/inicio", method = RequestMethod.GET)
	public String inicio(Model model) throws ParseException{
	
		CustomUsuario principal = (CustomUsuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				boolean verificacionFechaUltimaModificacion = usuarioService.validarExpiracionContrasena(principal.getUsername());
		if(!verificacionFechaUltimaModificacion ){
			usuarioService.modificarCredencialesExpiradas(verificacionFechaUltimaModificacion, principal.getUsername());
		}
	
		Usuario usuario = usuarioService.obtenerUsuario(principal.getUsername());
		if(!usuario.isUsuarioCredencialesNoExpiradas()){
		System.out.println("Prueba Rol "  + usuario.getRol().getNombre());
			
			System.out.println("Prueba Rol "  + usuario.getRol().getNombre());
			return "redirect:/credencialesExpiradas";
		}
			model.addAttribute("UsuarioSesion", principal);
		return "login/inicio";
		}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String forbidden(Model model){
		return "error/403";
	}

	@RequestMapping(value = "/credencialesExpiradas", method = RequestMethod.GET)
	public String contrasenaExpirada(Model model){
		return "login/credencialesExpiradas";
	}
	
	@RequestMapping(value = "/contrasenaExpirada", method = RequestMethod.GET)
	public String contrasenaExpirada(Model model,  @RequestParam("passwordActual") String passwordActual,  @RequestParam("passwordNuevo") String passwordNuevo, @RequestParam("confirmacionPasswordNuevo") String confirmacionPasswordNuevo, RedirectAttributes redirect){
		CustomUsuario principal = (CustomUsuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int validacion = usuarioService.validarCambioContraseña(principal, passwordActual, passwordNuevo, confirmacionPasswordNuevo);
		switch(validacion){
			case 1:
				redirect.addFlashAttribute("error", "La contraseña actual no coincide");
			break;
			case 2:
				redirect.addFlashAttribute("error", "Las nuevas contraseñas deben coindidir");
			break;
			case 3:
				redirect.addFlashAttribute("error", "La contraseña nueva debe ser diferente a la anterior");
			break;
			case 4:
				redirect.addFlashAttribute("error", "La contraseña nueva debe cumplir con patrón de seguridad");
			break;
		}
		if(validacion != 0){
			redirect.addFlashAttribute("passActual", passwordActual);
			redirect.addFlashAttribute("passNuevo", passwordNuevo);
			redirect.addFlashAttribute("confirmacionPasswordNuevo", confirmacionPasswordNuevo);
			return "redirect:/credencialesExpiradas";
		}
		return "redirect:/login";
	}
	
}
