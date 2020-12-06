package compreseujogo.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import compreseujogo.model.entity.Plataforma;
import compreseujogo.util.EntityManagerUtil;

public class PlataformaDao {

	private static EntityManager em = EntityManagerUtil.getEntityManager();

	public List<Plataforma> ListaPlataforma(String parameter, Plataforma plataforma) throws Exception {

		Query q = null;

		return q.getResultList();
	}
}
