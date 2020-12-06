package compreseujogo.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import compreseujogo.model.entity.ItemVenda;
import compreseujogo.util.EntityManagerUtil;

public class ItemVendaDao {

	private static EntityManager em = EntityManagerUtil.getEntityManager();

	public List<ItemVenda> list(String parameter, ItemVenda item) {
		Query q = null;
		if (parameter.equals("Nota")) {
			q = em.createQuery(
					"SELECT item FROM ItemVenda item WHERE item.venda.cliente.id = :cliente AND item.produto.id = :produto");
			q.setParameter("cliente", item.getVenda().getCliente().getId());
			q.setParameter("produto", item.getProduto().getId());
		} else if (parameter.equals("Vendidos")) {
			q = em.createQuery("SELECT item FROM ItemVenda item INNER JOIN item.p");
		} else if (parameter.equals("Venda")) {
			q = em.createQuery("SELECT item FROM ItemVenda item WHERE item.venda.id = :venda");
			q.setParameter("venda", item.getVenda().getId());
		}

		return q.getResultList();
	}
}
