package org.upiita.spring.testing;

import static org.springframework.util.Assert.notEmpty;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.upiita.spring.dao.PostDAO;
import org.upiita.spring.entidades.Post;

public class PostDAOTest {

		//HAY DOS TIPOS DE PRUEBAS
		// PRUEBA UNITARIA()
		// PRUEBAS DE INTEGRACION(BD O CHECAR QUE LA APLICACION WEB FUNCIONA)
		private static ApplicationContext contexto;
		
		private static PostDAO postDAO;
		
		@BeforeClass
		//ESTE METODO SE EJECUTA ANTES DE TODOS LOS TEST
		public static void inicializar(){
			
			contexto = new ClassPathXmlApplicationContext("dao-context-testing.xml");
			postDAO = (PostDAO) contexto.getBean("postDAO");
			
		}
		
		@Test
		public void buscaPostTest(){
			
			Post post = postDAO.buscaPorId(1);
			
			System.out.println("Post titulo:" + post.getTitulo());
			System.out.println("comentarios:" + post.getComentarios());
			System.out.println("categorias:" + post.getCategorias());
			
			Assert.assertNotNull("El metodo para buscar post regresa datos vacios",post);
		}
		
		@Test
		@Ignore
		public void guardarPostTest(){
			
			//ESTA ES LA ENTIDAD DE PRUEBA A GUARDAR
			Post post = new Post();
			//COMENTAMOS POR LA SECUENCIA
			//post.setId(3);
			post.setTitulo("titulo test");
			post.setContenido("Contenido Test");
			
			Date fechaActual = new Date();
			post.setFechaCreacion(fechaActual);
			
			//GUARDAMOS EL POST Y OBTENEMOS SU ID QUE LE ASIGNO
			//EN LA BASE
			Integer postIdBD = postDAO.guardar(post);
			
			Post postBD = postDAO.buscaPorId(postIdBD);
			
			Assert.assertNotNull(postBD);
			Assert.assertNotNull(postBD.getId());
			Assert.assertNotNull(postBD.getTitulo());
			Assert.assertNotNull(postBD.getContenido());
			
			Assert.assertEquals(post.getId(), postBD.getId());
			Assert.assertEquals(post.getContenido(),postBD.getContenido());
			Assert.assertEquals(post.getTitulo(),postBD.getTitulo());
			
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			
			Assert.assertEquals(formato.format(post.getFechaCreacion()),formato.format(postBD.getFechaCreacion()));
		}
		
		@Test
		@Ignore
		public void buscarPorTituloTest(){
			
			List<Post> postsEncontrados = postDAO.buscaPorTitulo("post");
			
			//Assert.assertNotNull(postsEncontrados);
			System.out.println("Post Encontrados" +  postsEncontrados);
			//NOTEMPTY REVIS QUE LA COLECION NO SEA NULA Y QUE NO VENGA VACIA
			//ES DECIR QUE TENGA UNO O MAS ELEMENTOS
			notEmpty(postsEncontrados);
		}
		
}
