package org.upiita.spring.testing;

import static org.springframework.util.Assert.notNull;
import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.upiita.spring.dao.UsuarioDAO;
import org.upiita.spring.entidades.DatosUsuario;
import org.upiita.spring.entidades.Usuario;


public class UsuarioDAOTest {

	//HAY DOS TIPOS DE PRUEBAS
	//PRUEBA UNITARIA()
	//PRUEBAS DE INTEGRACION(BD O CHECAR QUE LA APLICACIÓN WEB FUNCIONA)
	private static ApplicationContext contexto;
			
	private static UsuarioDAO usuarioDAO;
			
	@BeforeClass
	//ESTE MÉTODO SE EJECUTA ANTES DE TODOS LOS TEST
	public static void inicializar(){
				
		contexto = new ClassPathXmlApplicationContext("dao-context-testing.xml");
		usuarioDAO = (UsuarioDAO) contexto.getBean("usuarioDAO");
			
	}
			
	@Test
	@Ignore
	public void buscaUsuarioTest(){
		
		Usuario usuario = usuarioDAO.buscarUsuario("juan@email.com", "1234"); 
		System.out.println("Usuario:" + usuario);
		notNull(usuario);
	}

	@Test
	@Ignore
	public void guardarUsuarioTest(){
		Usuario usuario = new Usuario();
		
		//SE COMENTA POR LA SECUENCIA
		//usuario.setId(3);
		usuario.setEmail("francisco@email.com");
		usuario.setNombre("francisco");
		usuario.setPassword("password");
		
		
		Integer usuarioIdD = usuarioDAO.guardar(usuario);
		
		Usuario usuarioBD = usuarioDAO.buscaPorId(usuarioIdD);
		
		
		Assert.assertNotNull(usuarioBD);
		Assert.assertNotNull(usuarioBD.getEmail());
		Assert.assertNotNull(usuario.getNombre());
		Assert.assertNotNull(usuario.getPassword());
		
		Assert.assertEquals(usuario.getEmail(), usuarioBD.getEmail());
		Assert.assertEquals(usuario.getPassword(),usuarioBD.getPassword());
		Assert.assertEquals(usuario.getNombre(),usuarioBD.getNombre());
	}
	
	@Test
	@Ignore
	public void mapeoUsuarioDatosUsuarioTest(){
		Usuario usuarioBD = usuarioDAO.buscaPorId(1);
		
		System.out.println("usuario nombre:" + usuarioBD.getNombre());
		System.out.println("Datos usuario:" + usuarioBD.getDatosUsuario());
		
		DatosUsuario datosUsuario = usuarioBD.getDatosUsuario();
		
		System.out.println("Usuario Nombre(desde datos usuario):" + datosUsuario.getUsuario().getNombre());
	}
	
	
	@Test
	public void mapeoUsuarioPostTest(){
		Usuario usuarioBD = usuarioDAO.buscaPorId(1);
		
		System.out.println("Usuario nombre:" + usuarioBD.getNombre());
		System.out.println("Posts Usuario:" + usuarioBD.getPosts());
	}
	// TO DO
	//HACER UNA FUNCIÓN PARA QUE APARTIR DE DATOS USUARIO SE OBTENGA EL USUARIO
}
