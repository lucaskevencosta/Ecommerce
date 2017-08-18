package br.com.ecommerce.util.test;

import org.hibernate.Session;
import org.junit.Test;

import br.com.ecommerce.util.HibernateUtil;

public class HibernateUtilTest {
	
	@Test
	public void conectar() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("Sucesso! A conexão foi bem sucedida.");
		session.close();
	}
	
}
