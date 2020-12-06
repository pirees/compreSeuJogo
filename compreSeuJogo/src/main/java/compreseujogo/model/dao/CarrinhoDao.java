package compreseujogo.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import compreseujogo.model.entity.Carrinho;
import compreseujogo.util.EntityManagerUtil;


public class CarrinhoDao {

	private static EntityManager em = EntityManagerUtil.getEntityManager();

	public List<Carrinho> list(String parameter, Carrinho carrinho) throws Exception {

		Query a = em.createQuery("select c from Carrinho c");

		return a.getResultList();
	}
}
