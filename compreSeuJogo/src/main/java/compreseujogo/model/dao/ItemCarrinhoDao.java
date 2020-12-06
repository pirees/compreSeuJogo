package compreseujogo.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import compreseujogo.model.entity.Item;
import compreseujogo.model.entity.ItemCarrinho;
import compreseujogo.util.EntityManagerUtil;

public class ItemCarrinhoDao {
	private static EntityManager em = EntityManagerUtil.getEntityManager();

	public List<ItemCarrinho> listar(String parametro, ItemCarrinho item) throws Exception {

		Query q = null;
		if (parametro.equals("Carrinho")) {
			q = em.createQuery("SELECT ic FROM ItemCarrinho ic WHERE ic.carrinho.id = :carrinho ORDER BY produto.nome");
			q.setParameter("carrinho", item.getCarrinho().getId());
		}

		return q.getResultList();
	}

	public Double carrinho() {
		Query q = em.createQuery("SELECT SUM(ic.valor) FROM ItemCarrinho ic WHERE ic.carrinho.id = 1");
		return (Double) q.getSingleResult();
	}
}
