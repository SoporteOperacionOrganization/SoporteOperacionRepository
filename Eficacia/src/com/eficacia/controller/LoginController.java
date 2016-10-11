package com.eficacia.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.eficacia.custommodel.CustomUsuario;

@Controller
@SessionAttributes("UsuarioSesion")
public class LoginController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model){
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/prueba", method = RequestMethod.GET)
	public String prueba(Model model){
		return "redirect:/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model){
		return "login/login";
	}
	
	@RequestMapping(value = "/inicio", method = RequestMethod.GET)
	public String inicio(Model model){
		CustomUsuario principal = (CustomUsuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("UsuarioSesion", principal);
		return "login/inicio";
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String forbidden(Model model){
		return "errors/403";
	}
	
	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public String recursoNoEncontrado(Model model){
		return "errors/404";
	}
	
}
