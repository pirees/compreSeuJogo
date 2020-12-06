package compreseujogo.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import compreseujogo.model.entity.Item;
import compreseujogo.util.EntityManagerUtil;

public class ItemDao<T extends Item> {

	private static EntityManager em = EntityManagerUtil.getEntityManager();

	public List<Item> listar(String parametro, Item item) throws Exception {

		Query q = null;
		if (parametro.equals("")) {

		}
		return q.getResultList();
	}
}
