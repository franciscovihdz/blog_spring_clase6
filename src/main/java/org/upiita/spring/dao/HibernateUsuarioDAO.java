package org.upiita.spring.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.upiita.spring.entidades.Post;
import org.upiita.spring.entidades.Usuario;

@Component("usuarioDAO")
@Transactional
public class HibernateUsuarioDAO implements UsuarioDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	/* (non-Javadoc)
	 * @see org.upiita.spring.dao.UsuarioDAO#buscaPorId(java.lang.Integer)
	 */
	@Override
	public Usuario buscaPorId(Integer id){
		Usuario usuario = null;
		System.out.println("buscando usuario con id:" + id);
		Session sesion = sessionFactory.openSession();
		sesion.beginTransaction();
		
		usuario = (Usuario) sesion.get(Usuario.class, id);
		//SIMULAMOS LA CONSULTA A LA BASE
		/*usuario = new Usuario();
		usuario.setEmail("usuario@email.com");
		usuario.setNombre("Usuario Test");
		*/
		
		sesion.close();
		if (usuario == null){
			
		}
		
		return usuario;
	}
	
	/* (non-Javadoc)
	 * @see org.upiita.spring.dao.UsuarioDAO#guardar(org.upiita.spring.entidades.Usuario)
	 */
	@Override
	public Integer guardar(Usuario usuario){
		
		//TRANSACCION PROGRAMATICA
		Session sesion  = sessionFactory.openSession();
		sesion.beginTransaction();
						
		sesion.saveOrUpdate(usuario);
		sesion.getTransaction().commit();
		//TERMINADO TODO LO QUE NECESITAMOS HACER EN LA BASE, CERRAMOS LA SESION DE HIBERNATE
		sesion.close();
				
		return usuario.getId();
	}
	
	@Override
	public Usuario buscarUsuario(String email, String password){
		Criteria criterio = sessionFactory.getCurrentSession().createCriteria(Usuario.class);
		
		criterio.add(Restrictions.eq("password", password));
		criterio.add(Restrictions.and(Restrictions.eq("email", email)));
		
		
		return (Usuario) criterio.uniqueResult();
	}

}
