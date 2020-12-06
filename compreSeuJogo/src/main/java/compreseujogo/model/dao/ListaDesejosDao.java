package compreseujogo.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import compreseujogo.model.entity.ListaDesejos;
import compreseujogo.util.EntityManagerUtil;

public class ListaDesejosDao {

	private static EntityManager em = EntityManagerUtil.getEntityManager();

	public List<ListaDesejos> list(String parameter, ListaDesejos lista) {

		Query a = em.createQuery("select l from ListaDesejos l");

		return a.getResultList();
	}

}
