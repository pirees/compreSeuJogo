package compreseujogo.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import compreseujogo.model.entity.Categoria;
import compreseujogo.util.EntityManagerUtil;

public class CategoriaDao {

	public List<Categoria> listar(String parametro, Categoria categoria) throws Exception {
		EntityManager em = EntityManagerUtil.getEntityManager();
		Query q = null;
		if (parametro.equals("novo")) {
			q = em.createQuery("SELECT c FROM Categoria c WHERE c.nome= :nome");
			q.setParameter("nome", categoria.getNome());
		} else {
			q = em.createQuery("SELECT c FROM Categoria c");
		}
		
		return q.getResultList();
	}
}
