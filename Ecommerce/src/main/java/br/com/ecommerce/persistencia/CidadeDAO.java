package br.com.ecommerce.persistencia;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.ecommerce.entidade.Cidade;
import br.com.ecommerce.util.HibernateUtil;

public class CidadeDAO extends GenericoDAO<Cidade> {
	
	@SuppressWarnings("unchecked")
	public List<Cidade> selectInnerJoinEstado(Long estado) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria consulta = session.createCriteria(Cidade.class);
			consulta.add(Restrictions.eq("estado.id", estado));
			consulta.addOrder(Order.asc("nome"));
			return consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}
	
}
