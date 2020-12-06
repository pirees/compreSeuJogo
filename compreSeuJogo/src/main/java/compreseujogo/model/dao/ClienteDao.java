package compreseujogo.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import compreseujogo.model.entity.Cliente;
import compreseujogo.util.EntityManagerUtil;

public class ClienteDao {

	public List<Cliente> list(String parameter, Cliente cliente) throws Exception {
		EntityManager em = EntityManagerUtil.getEntityManager();
		Query q = null;

		return q.getResultList();
	}

	public Cliente acessCpf(Cliente cliente) throws Exception {
		EntityManager em = EntityManagerUtil.getEntityManager();
		Query q = null;
		q = em.createQuery("SELECT c FROM Cliente c WHERE c.cpf = :cpf");
		q.setParameter("cpf", cliente.getCpf());
		return (Cliente) q.getResultList().get(0);
	}
}
