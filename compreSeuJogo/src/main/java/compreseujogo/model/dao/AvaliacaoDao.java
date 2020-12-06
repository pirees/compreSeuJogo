package compreseujogo.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import compreseujogo.model.entity.Avaliacao;
import compreseujogo.util.EntityManagerUtil;

public class AvaliacaoDao {

	private static EntityManager em = EntityManagerUtil.getEntityManager();

	public List<Avaliacao> list(String parameter, Avaliacao avaliacao) {
		Query q = null;
		if (parameter.equals("Produto")) {
			q = em.createQuery("SELECT a FROM Avaliacao a WHERE a.produto.id = :produto");
			q.setParameter("produto", avaliacao.getProduto().getId());
		} else if (parameter.equals("Nota")) {
			q = em.createQuery("SELECT a FROM Avaliacao a WHERE a.produto.id = :produto AND a.cliente.id = :cliente");
			q.setParameter("produto", avaliacao.getProduto().getId());
			q.setParameter("cliente", avaliacao.getCliente().getId());
		}
		return q.getResultList();
	}
	
	public List<Avaliacao> nota(Avaliacao avaliacao) {
		Query q = null;
			q = em.createQuery("SELECT AVG(a.pontos) FROM Avaliacao a WHERE a.produto.id = :produto");
			q.setParameter("produto", avaliacao.getProduto().getId());
		return q.getResultList();
	}
}
