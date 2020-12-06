package compreseujogo.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import compreseujogo.model.entity.EntityBase;
import compreseujogo.model.entity.Pessoa;
import compreseujogo.util.EntityManagerUtil;

public class PessoaDao<T extends Pessoa> {

	private static EntityManager em = EntityManagerUtil.getEntityManager();

	public List<T> list(String parameter, Pessoa pessoa, Class<T> classe) {
		Query q = null;

		if (parameter.equals("email")) {
			q = em.createQuery("SELECT t FROM Pessoa t WHERE t.email = :email");
			q.setParameter("email", pessoa.getEmail());
		} else if (parameter.equals("cpf")) {
			q = em.createQuery("SELECT t FROM Pessoa t WHERE t.cpf = :cpf");
			q.setParameter("cpf", pessoa.getCpf());
		} else if (parameter.equals("login")) {
			q = em.createQuery("SELECT t FROM " + classe.getSimpleName().toString() + " t WHERE t.email = :email "
					+ "AND t.senha = :senha");
			q.setParameter("email", pessoa.getEmail());
			q.setParameter("senha", pessoa.getSenha());
		} else if (parameter.equals("organizada")) {
			q = em.createQuery("SELECT t FROM " + classe.getSimpleName().toString() + " t ORDER BY t.nome");
		}
		return q.getResultList();
	}

	public Pessoa login(Pessoa pessoa) {
		Query q = em.createQuery("SELECT p FROM Pessoa p WHERE p.email = :email AND p.senha = :senha");
		q.setParameter("email", pessoa.getEmail());
		q.setParameter("senha", pessoa.getSenha());
		return (Pessoa) q.getSingleResult();
	}
}
