package org.upiita.spring.testing;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.upiita.spring.dao.UsuarioDAO;

public class LoggingAspectoDemostracion {

	private static ApplicationContext contexto;

	private static UsuarioDAO usuarioDAO;
	
	public static void main(String args[]) {

		contexto = new ClassPathXmlApplicationContext("dao-context-testing.xml");
		usuarioDAO = (UsuarioDAO) contexto.getBean("usuarioDAO");
		
		usuarioDAO.buscaPorId(1);
		System.out.println("");
		
	}
	
	public Object alrededor(ProceedingJoinPoint joinPoint) throws
	Throwable {
		Object resultado;
		resultado = joinPoint.proceed();
		return resultado;
	}

}
