package org.upiita.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.upiita.spring.entidades.Post;

@Component(value="postDAO")
@Transactional
public class HibernatePostDAO implements PostDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	/* (non-Javadoc)
	 * @see org.upiita.spring.dao.PostDAO#buscaPorId(java.lang.Integer)
	 */
	@Override
	public Post buscaPorId(Integer id){
		Post elPost = null;
		
		//TRANSACCION PROGRAMATICA
		//Session sesion  = sessionFactory.openSession();
		//sesion.beginTransaction();
		Session sesion = sessionFactory.getCurrentSession();
		//UNA VEZ NICIADA LA TRANSACCION PODEMOS
		//HACER CONSULTAS O MODIFICAR LA BASE
		elPost= (Post) sesion.get(Post.class, id);
		//LE INDICA A HIBERNATE QUE QUEREMOS SUS COMENTARIOS ASOCIADOS
		//Hibernate.initialize(elPost.getComentarios());
		Hibernate.initialize(elPost.getCategorias());
		//TERMINADO TODO LO QUE NECESITAMOS HACER EN LA BASE, CERRAMOS LA SESION DE HIBERNATE
		//sesion.close();
		
		if(elPost == null){
			
		}
		
		//SIMULAMOS LA CONSULTA  A LA BASE
		//NOS REGRESO ESTE OBJETO
		/*
		elPost = new Post();
		elPost.setTitulo("titulo prueba");
		elPost.setContenido("contenido prueba");		
		*/
		return elPost;
	}
	
	/* (non-Javadoc)
	 * @see org.upiita.spring.dao.PostDAO#guardar(org.upiita.spring.entidades.Post)
	 */
	@Override
	public Integer guardar(Post post){
		
		//TRANSACCION PROGRAMATICA
		//Session sesion  = sessionFactory.openSession();
		//sesion.beginTransaction();
		
		Session sesion = sessionFactory.getCurrentSession();
		
		sesion.saveOrUpdate(post);
		//sesion.getTransaction().commit();
		//TERMINADO TODO LO QUE NECESITAMOS HACER EN LA BASE, CERRAMOS LA SESION DE HIBERNATE
		//sesion.close();
		
		return post.getId();
	}
	
	@Override
	public List<Post> buscaPorTitulo(String titulo){
		
		Session sesion = sessionFactory.getCurrentSession();
		//APARTIR DE LA SESSION CREMOS EL CRITERIA
		//USANDO EL .CLASS QUE REPRESENTA A LA TABLA
		Criteria criterio = sesion.createCriteria(Post.class);
		
		//AGREGAMOS CRITERIOS DE BUSQUEDA
		//PRIMER ARGUMENTO DE LIKE (EN GENERL DE LA RESTRICCION)
		//ES LA PROPIEDAD DE LA ENTIDAD A BUSCAR
		criterio.add(Restrictions.like("titulo", "%" + titulo + "%"));
		List<Post> postsEncontrados = criterio.list();
		
		return postsEncontrados;
		
	}
	
	@Override
	public List<Post> buscaDiferentePorTitulo(String titulo){
		
		Session sesion = sessionFactory.getCurrentSession();
		//APARTIR DE LA SESSION CREMOS EL CRITERIA
		//USANDO EL .CLASS QUE REPRESENTA A LA TABLA
		Criteria criterio = sesion.createCriteria(Post.class);
		
		//AGREGAMOS CRITERIOS DE BUSQUEDA
		//PRIMER ARGUMENTO DE LIKE (EN GENERL DE LA RESTRICCION)
		//ES LA PROPIEDAD DE LA ENTIDAD A BUSCAR
		criterio.add(Restrictions.not(Restrictions.like("titulo", "%" + titulo + "%")));
		List<Post> postsEncontrados = criterio.list();
		
		return postsEncontrados;
		
	}

}
