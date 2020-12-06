package compreseujogo.model.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import compreseujogo.model.entity.Venda;
import compreseujogo.util.EntityManagerUtil;

public class VendaDao {

	private static EntityManager em = EntityManagerUtil.getEntityManager();

	public List<Venda> list(String parameter, Venda venda) {
		Query q = null;
		if (parameter.equals("Cliente")) {
			q = em.createQuery("SELECT v FROM Venda v WHERE v.cliente.id = :cliente ORDER BY v.dataCadastro");
			q.setParameter("cliente", venda.getCliente().getId());
		} else if (parameter.equals("Cliente")) {
			q = em.createQuery("SELECT v FROM Venda v WHERE v.vendedor.id = :id ORDER BY v.dataCadastro");
			q.setParameter("id", venda.getVendedor().getId());
		} else if (parameter.equals("PVendas")) {
			q = em.createQuery(
					"SELECT v FROM Venda v WHERE v.entrega = FALSE ORDER BY v.dataEntrega");
		}
		return q.getResultList();
	}
}
