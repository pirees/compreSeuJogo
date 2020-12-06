package compreseujogo.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import compreseujogo.model.entity.Produto;
import compreseujogo.util.EntityManagerUtil;

public class ProdutoDao {

	private static EntityManager em = EntityManagerUtil.getEntityManager();

	public List<Produto> list(String parametro, Produto produto) throws Exception {
		Query q = null;
		if (parametro.equals("Categoria")) {
			q = em.createQuery("SELECT p FROM Produto p WHERE p.categoria.id = :id");
			q.setParameter("id", produto.getCategoria().getId());
		} else if (parametro.equals("Plataforma")) {
			q = em.createQuery("SELECT p FROM Produto p WHERE p.plataforma.id = :id");
			q.setParameter("id", produto.getPlataforma().getId());
		} else if (parametro.equals("Marca")) {
			q = em.createQuery("SELECT p FROM Produto p WHERE p.marca.id = :id");
			q.setParameter("id", produto.getMarca().getId());
		} else if (parametro.equals("nome")) {
			q = em.createQuery("SELECT p FROM Produto p WHERE p.nome = :id");
			q.setParameter("id", produto.getNome());
		} else if (parametro.equals("SKU")) {
			q = em.createQuery("SELECT p FROM Produto p WHERE p.sku = :id");
			q.setParameter("id", produto.getSku());
		} else if (parametro.equals("EAN")) {
			q = em.createQuery("SELECT p FROM Produto p WHERE p.EAN = :id");
			q.setParameter("id", produto.getEAN());
		} else if (parametro.equals("listaIndex")) {
			q = em.createQuery(
					"SELECT p FROM Produto p WHERE p.categoria.ativo = true AND p.plataforma.ativo = true AND "
							+ "p.marca.ativo = true AND p.imagem != null AND p.ativo = true AND p.quantEstoque > 0");
		} else if (parametro.equals("carousel")) {
			q = em.createQuery(
					"SELECT p FROM Produto p WHERE p.categoria.ativo = true AND p.plataforma.ativo = true AND "
							+ "p.marca.ativo = true AND p.imagem != null AND p.ativo = true AND p.quantEstoque > 0"
							+ "AND p.id != :id AND p.plataforma.id = :plataforma ORDER BY p.quantConsulta");
			q.setParameter("id", produto.getId());
			q.setParameter("plataforma", produto.getPlataforma().getId());
		}
		return q.getResultList();
	}

	public List<Produto> listSearch(String filter) throws Exception {
		Query q = null;
		if (filter.equals("")) {
			q = em.createQuery(
					"SELECT p FROM Produto p WHERE p.categoria.ativo = true AND p.plataforma.ativo = true AND "
							+ "p.marca.ativo = true AND p.imagem != null AND p.ativo = true AND p.quantEstoque > 0");
		} else if (!filter.equals("")) {
			q = em.createQuery(
					"SELECT p FROM Produto p WHERE p.categoria.ativo = true AND p.plataforma.ativo = true AND "
							+ "p.marca.ativo = true AND p.imagem != null AND p.ativo = true AND p.quantEstoque > 0 AND "
							+ " (p.nome LIKE :filtro OR p.categoria.nome LIKE :filtro OR p.plataforma.nome LIKE :filtro OR "
							+ "p.marca.nome LIKE :filtro OR p.fornecedor.nome LIKE :filtro)");
			q.setParameter("filtro", "%" + filter + "%");
		}
		return q.getResultList();
	}
}
