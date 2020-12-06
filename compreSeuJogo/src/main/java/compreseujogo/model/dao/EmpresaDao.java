package compreseujogo.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import compreseujogo.model.entity.Empresa;
import compreseujogo.util.EntityManagerUtil;

public class EmpresaDao<T extends Empresa> {

	private static EntityManager em = EntityManagerUtil.getEntityManager();

	public List<Empresa> list(String parameter, Empresa empresa, Class<T> classe) {
		Query q = null;

		if (parameter.equals("email")) {
			q = em.createQuery("SELECT t FROM " + classe.getSimpleName().toString() + " t WHERE t.email = :email");
			q.setParameter("email", empresa.getEmail());
		} else if (parameter.equals("cnpj")) {
			q = em.createQuery("SELECT t FROM " + classe.getSimpleName().toString() + " t WHERE t.cnpj = :cnpj");
			q.setParameter("cnpj", empresa.getCnpj());
		} else if (parameter.equals("organizada")) {
			q = em.createQuery("SELECT t FROM "+ classe.getSimpleName().toString()+" t ORDER BY t.nome");
		}
		return q.getResultList();

	}

}
