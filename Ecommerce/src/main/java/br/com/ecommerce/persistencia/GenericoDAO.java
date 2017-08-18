package br.com.ecommerce.persistencia;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.ecommerce.util.HibernateUtil;

public class GenericoDAO<E> {

	private Class<E> classe;

	@SuppressWarnings("unchecked")
	public GenericoDAO() {
		this.classe = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public void insert(E entidade) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = null;
		try {
			t = session.beginTransaction();
			session.save(entidade);
			t.commit();
		} catch (RuntimeException e) {
			if (t != null)
				t.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	public void delete(E entidade) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = null;
		try {
			t = session.beginTransaction();
			session.delete(entidade);
			t.commit();
		} catch (RuntimeException e) {
			if (t != null)
				t.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	public void update(E entidade) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = null;
		try {
			t = session.beginTransaction();
			session.update(entidade);
			t.commit();
		} catch (RuntimeException e) {
			if (t != null)
				t.rollback();
			throw e;
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<E> selectAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria consulta = session.createCriteria(classe);
			return consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<E> selectAll(String filtro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria consulta = session.createCriteria(classe);
			consulta.addOrder(Order.asc(filtro));
			return consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public E selectToId(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria consulta = session.createCriteria(classe);
			consulta.add(Restrictions.idEq(id));
			return (E) consulta.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}
	
	public void merge(E entidade) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = null;
		try {
			t = session.beginTransaction();
			session.merge(entidade);
			t.commit();
		} catch (RuntimeException e) {
			if (t != null)
				t.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

}
