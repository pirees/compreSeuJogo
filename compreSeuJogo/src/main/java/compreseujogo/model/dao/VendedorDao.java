package compreseujogo.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import compreseujogo.model.entity.Vendedor;
import compreseujogo.util.EntityManagerUtil;


public class VendedorDao {

	public List<Vendedor> list(String parameter, Vendedor vendedor) throws Exception {
		EntityManager em = EntityManagerUtil.getEntityManager();
		Query q = null;
		if (parameter.equals("login")) {
			q = em.createQuery("SELECT c FROM Vendedor c");
		}

		return q.getResultList();
	}

}
