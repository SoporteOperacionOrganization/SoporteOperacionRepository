package com.eficacia.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eficacia.custommodel.CustomUsuario;
import com.eficacia.dao.UsuarioDao;
import com.eficacia.model.Usuario;
@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Usuario obtenerUsuario(String soeid) {
		return usuarioDao.obtenerUsuario(soeid);
	}
	
	@Override
	public List<Usuario> obtenerUsuariosPaginacion(Integer offset, Integer limite){
		return usuarioDao.obtenerUsuariosPaginacion(offset, limite);
	}

	@Override
	public List<Usuario> obtenerUsuarios() {
		return usuarioDao.obtenerUsuarios();
	}

	@Override
	public void agregarUsuario(Usuario usuario) {
		Calendar fecha = new GregorianCalendar();
		String fechaActual = fecha.get(Calendar.YEAR)+"/"+(fecha.get(Calendar.MONTH)+1)+"/"+fecha.get(Calendar.DAY_OF_MONTH);
		usuario.setUsuarioFechaExpiracionContrasena(fechaActual);
		usuario.setUsuarioCredencialesNoExpiradas(true);
		usuarioDao.agregarUsuario(usuario);
	}

	@Override
	public void modificarUsuario(Usuario usuario) {
		Usuario entity = usuarioDao.obtenerUsuario(usuario.getSoeid());
		if(entity != null){
			entity.setSoeid(usuario.getSoeid());
			if(!usuario.getPassword().equals("")){
				entity.setPassword(passwordEncoder.encode(usuario.getPassword()));
			}
			entity.setNombre(usuario.getNombre());
			entity.setApellidoPaterno(usuario.getApellidoPaterno());
			entity.setApellidoMaterno(usuario.getApellidoMaterno());
			entity.setTelefono(usuario.getTelefono());
			entity.setRol(usuario.getRol());
		}
		//usuarioDao.modificarUsuario(usuario);
	}

	@Override
	public void eliminarUsuario(String soeid) {
		usuarioDao.eliminarUsuario(soeid);
	}

	@Override
	public List<Usuario> filtrarUsuarios(String soeid, Integer offset, Integer limite) {
		return usuarioDao.filtrarUsuarios(soeid, offset, limite);
	}

	@Override
	public Long contarRegistros() {
		return usuarioDao.contarRegistros();
	}
	
	@Override
	public Long contarRegistrosCond(String soeid) {
		return usuarioDao.contarRegistrosCond(soeid);
	}

	@Override
	public boolean validarExpiracionContrasena(String soeid) throws ParseException {
		boolean validacion = true;
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Usuario usuario = usuarioDao.obtenerUsuario(soeid);
		
		String fechaUltimaModificacionContrasena = usuario.getUsuarioFechaExpiracionContrasena().substring(0, 4) + "/" + usuario.getUsuarioFechaExpiracionContrasena().substring(5, 7) + "/" + usuario.getUsuarioFechaExpiracionContrasena().substring(8, 10);
		Calendar fecha = new GregorianCalendar();
		String fechaActual = fecha.get(Calendar.YEAR)+"/"+(fecha.get(Calendar.MONTH)+1)+"/"+fecha.get(Calendar.DAY_OF_MONTH);
		Date fechaUltim = null;
		Date fechaAct= null;
		fechaUltim = format.parse(fechaUltimaModificacionContrasena);
		fechaAct = format.parse(fechaActual);
		
		Calendar startCal = Calendar.getInstance();
	    startCal.setTime(fechaUltim);        
	    Calendar endCal = Calendar.getInstance();
	    endCal.setTime(fechaAct);
	    int diferencia = 0;
	    if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
	        
	    }
	    if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
	        startCal.setTime(fechaAct);
	        endCal.setTime(fechaUltim);
	    }
	    do {
	        startCal.add(Calendar.DAY_OF_MONTH, 1);
	        if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
	            ++diferencia;
	        }
	    } while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); //excluding end date
	  
		if(diferencia > 15){
			validacion = false;
		}
		
		return validacion;
	}

	@Override
	public void modificarCredencialesExpiradas(boolean estatusCredencialesExpiradas, String soeid) {
		usuarioDao.modificarCredencialesExpiradas(estatusCredencialesExpiradas, soeid);
	}

	@Override
	public void renovarCredenciales(String passwordActual, String passwordNuevo, String fechaTransaccion, String soeid) {
		usuarioDao.renovarCredenciales(passwordActual, passwordNuevo, fechaTransaccion, soeid);
	}

	@Override
	public int validarCambioContraseña(CustomUsuario principal, String  passwordActual, String passwordNuevo, String confirmacionPasswordNuevo) {
		int validacion = 0;
		String patronContraseñaSegura = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%./_-]).{6,20})";
		Calendar fecha = new GregorianCalendar();
		String fechaTransaccion= fecha.get(Calendar.DAY_OF_MONTH)+"/"+(fecha.get(Calendar.MONTH)+1)+"/"+fecha.get(Calendar.YEAR);
		
		Usuario usuario = usuarioDao.obtenerUsuario(principal.getUsername());
		if(!passwordEncoder.matches(passwordActual,usuario.getPassword())){
			validacion = 1;
		}else if(!passwordNuevo.equals(confirmacionPasswordNuevo)){
			validacion = 2;
		}else if(passwordNuevo.equals(passwordActual)){
			validacion = 3;
		}else if(!passwordNuevo.matches(patronContraseñaSegura)){
			validacion = 4;
		}else{
			usuarioDao.renovarCredenciales(passwordEncoder.encode(passwordActual), passwordEncoder.encode(passwordNuevo), fechaTransaccion, principal.getUsername());
		}
		return validacion;
	}

}
