package com.eficacia.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eficacia.custommodel.CustomUsuario;
import com.eficacia.model.Rol;
import com.eficacia.model.Usuario;
import com.eficacia.propertyeditors.RolPropertyEditor;
import com.eficacia.service.RolService;
import com.eficacia.service.UsuarioService;
import com.eficacia.validator.UsuarioValidator;

@Controller
public class UsuariosController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RolService rolService;
	
	@Autowired
	private RolPropertyEditor rolPropertyEditor;
	
	@Autowired
	private UsuarioValidator usuarioValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.registerCustomEditor(Rol.class, rolPropertyEditor);
		webDataBinder.setValidator(usuarioValidator);
	}
	
	@RequestMapping(value = "/listarRoles", method = RequestMethod.GET)
	public String test(Model model){
		List<Rol> roles = rolService.obtenerRoles();
		model.addAttribute("roles", roles);
		return "roles/listarRoles";
	}
	
	@RequestMapping(value = "/listarUsuarios", method = RequestMethod.GET)
	public String listarUsuarios(Model model){
		List<Usuario> usuarios = usuarioService.obtenerUsuarios();
		model.addAttribute("usuarios", usuarios);
		return "usuarios/listarUsuarios";
	}
	
	@RequestMapping(value = "/filtrarUsuarios", method = RequestMethod.GET)
	public String filtrarUsuarios(Model model, @RequestParam("soeid") String soeid){
		List<Usuario> usuarios = usuarioService.filtrarUsuarios(soeid);
		model.addAttribute("usuarios", usuarios);
		return "usuarios/listarUsuarios";
	}
	
	@RequestMapping(value = "/agregarUsuario", method = RequestMethod.GET)
	public String agregarUsuario(Model model){
		List<Rol> roles = rolService.obtenerRoles();
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("roles",roles);
		return "usuarios/formularioUsuario";
	}
	
	@RequestMapping(value = "/agregarUsuario", method = RequestMethod.POST)
	public String registrarUsuario(Model model, @Valid Usuario usuario, BindingResult result){
		if(result.hasErrors()){
			List<Rol> roles = rolService.obtenerRoles();
			model.addAttribute("roles",roles);
			return "usuarios/formularioUsuario";
		}
		usuarioService.agregarUsuario(usuario);
		return "redirect:/listarUsuarios";
	}
	
	@RequestMapping(value = "/editarUsuario/{soeid}", method = RequestMethod.GET)
	public String editarUsuario(Model model, @PathVariable("soeid") String soeid){
		Usuario usuario = usuarioService.obtenerUsuario(soeid);
		List<Rol> roles = rolService.obtenerRoles();
		model.addAttribute("edit", true);
		model.addAttribute("usuario", usuario);
		model.addAttribute("roles",roles);
		//model.addAttribute("passwordConfirmation", usuario.getPassword());
		return "usuarios/formularioUsuarioModificar";
	}
	
	@RequestMapping(value = "/editarUsuario/{soeid1}", method = RequestMethod.POST)
	public String modificarUsuario(Model model,  @Valid Usuario usuario, BindingResult result){
		if(result.hasErrors()){
			List<Rol> roles = rolService.obtenerRoles();
			model.addAttribute("roles",roles);
			return "usuarios/formularioUsuarioModificar";
		}
		usuarioService.modificarUsuario(usuario);
		return "redirect:/listarUsuarios";
	}
	
	@RequestMapping(value = "/eliminarUsuario/{soeid}", method = RequestMethod.GET)
	public String eliminarUsuario(Model model, @PathVariable String soeid){
		usuarioService.eliminarUsuario(soeid);
		return "redirect:/listarUsuarios";
	}
	
}
