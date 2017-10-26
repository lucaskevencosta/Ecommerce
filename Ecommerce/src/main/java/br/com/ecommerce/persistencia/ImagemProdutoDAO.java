package br.com.ecommerce.persistencia;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.ecommerce.entidade.ImagemProduto;
import br.com.ecommerce.util.HibernateUtil;

public class ImagemProdutoDAO extends GenericoDAO<ImagemProduto> {
	
	public ImagemProduto mergeImagem(ImagemProduto imagem) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = null;
		try {
			t = session.beginTransaction();
			ImagemProduto imagemProduto = (ImagemProduto) session.merge(imagem);
			t.commit();
			
			return imagemProduto;
		} catch (RuntimeException e) {
			if (t != null)
				t.rollback();
			throw e;
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ImagemProduto> selectWhileProduto(Long produto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria consulta = session.createCriteria(ImagemProduto.class);
			
			//where
			consulta.add(Restrictions.eq("produto.id", produto));
			
			//order by
			consulta.addOrder(Order.asc("id"));
			
			return consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}
	
}
