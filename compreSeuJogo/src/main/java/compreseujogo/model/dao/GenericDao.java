package compreseujogo.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import compreseujogo.model.entity.EntityBase;
import compreseujogo.util.EntityManagerUtil;

public class GenericDao<T extends EntityBase> {

	private static EntityManager em = EntityManagerUtil.getEntityManager();

	public String saveOrUpdate(T obj) throws Exception {
		try {
			em.getTransaction().begin();
			em.flush();
			if (obj.getId() == 0) {
				em.persist(obj);
			} else {
				em.merge(obj);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			throw new Exception("Erro Gravando \n" + e.getMessage());
		}
		return "Salvo com sucesso";
	}

	public String remove(Class<T> classe, int id) throws Exception {
		T t = findById(classe, id);
		try {
			em.getTransaction().begin();
			em.remove(t);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception("Erro Deletando \n" + e.getMessage());
		}
		return "Deletado";
	}

	public List<T> list(Class<T> classe) {
		Query q = em.createQuery("select t from " + classe.getSimpleName().toString() + " t");
		return q.getResultList();
	}

	// Recebe uma entidade modelo
	public T findById(Class<T> classe, int id) {
		return em.find(classe, id);
	}

}