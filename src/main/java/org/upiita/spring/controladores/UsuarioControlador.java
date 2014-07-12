package org.upiita.spring.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.upiita.spring.dao.UsuarioDAO;
import org.upiita.spring.entidades.Usuario;
import org.upiita.spring.forma.UsuarioForma;

/* --------------------- SOLUCIONES EJERCICIOS ------------------*/

@Controller
public class UsuarioControlador {

	@Autowired
	private UsuarioDAO usuarioDAO;

	@RequestMapping(value = "/usuario/{usuarioId:[0-9]+}")
	public String muestraUsuario(@PathVariable Integer usuarioId, Model modelo) {

		System.out.println("mostrando /usuario/" + usuarioId);

		Usuario usuario = usuarioDAO.buscaPorId(usuarioId);
		modelo.addAttribute("usuario", usuario);

		return "usuario";
	}

	@RequestMapping(value = "/usuario/{usuarioId:[0-9]+}/editar")
	public String mostrarEditor(@RequestParam(required=false)Boolean actualizado,@PathVariable("usuarioId") Integer usuarioId,Model modelo ) {

		System.out.println("MODELO:" + modelo);
		//SI HUBO ERRORES AL GUARDAR
		if(modelo.containsAttribute("usuario")){
			System.out.println("hubo errores, MODELO:" + modelo);
		}else{
			System.out.println("mostrando el editor para el usuario");
			System.out.println("Actualizado:" + actualizado);
			
			Usuario usuario = (Usuario) usuarioDAO.buscaPorId(usuarioId);
			
			System.out.println("Usuario:" + usuario);
			System.out.println("password:" + usuario.getPassword());
			
			modelo.addAttribute("usuario",usuario);
			modelo.addAttribute("actualizado", actualizado);
		}
		
		
		return "usuario_editar";
	}

	@RequestMapping(value = "/usuario/guardar", method=RequestMethod.POST)
	public String guardarUsuario(@Valid @ModelAttribute("usuario") UsuarioForma forma, BindingResult validacion, RedirectAttributes atributos) {
		
		String urlRedirect = null;
		Usuario usuario = new Usuario();
		
		System.out.println("guardando usuario, id:" + forma.getId() + ",nombre:" + forma.getNombre() + ", email:"
				+ forma.getEmail() + ", password:" + forma.getPassword());
		if(validacion.hasErrors()){
			//	-- esto esta disponible desde la version 3.1
			// ES PARA PRESERVAR DATOS EN EL REDIRECT
			atributos.addFlashAttribute("post",forma);
			atributos.addFlashAttribute("org.springframework.validation.BindingResult.usuario",validacion);
			urlRedirect = "redirect:/usuario/" + forma.getId() + "/editar";
		}else{
			usuario.setEmail(forma.getEmail());
			usuario.setId(forma.getId());
			usuario.setNombre(forma.getNombre());
			usuario.setPassword(forma.getPassword());
			Integer usuarioIdGuardado = usuarioDAO.guardar(usuario);
			// @TODO: MAS ADELANTE GUARDAMOS ESTOS EN LA BASE DE DATOS
			urlRedirect = "redirect:/usuario/" + usuarioIdGuardado + "/editar?actualizado=true";
		}
		

		// @TODO: MAS ADELANTE CAMBIERAMOS ESTO POR EL PATRON
		// POST - GET- REDIRECT
		return urlRedirect;

	}

}
