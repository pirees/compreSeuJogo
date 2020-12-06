package compreseujogo.model.bo;

import java.util.List;

import compreseujogo.model.dao.GenericDao;
import compreseujogo.model.dao.PlataformaDao;
import compreseujogo.model.entity.Plataforma;
import compreseujogo.model.entity.Produto;

public class PlataformaBo extends TipoBo<Plataforma> {

	public List<Plataforma> listar(String parametro, Plataforma plataforma) throws Exception {
		try {
			if (parametro.equals("")) {
				GenericDao<Plataforma> tcDao = new GenericDao<Plataforma>();
				return tcDao.list(Plataforma.class);
			} else {
				PlataformaDao plataformaDao = new PlataformaDao();
				return plataformaDao.ListaPlataforma(parametro, plataforma);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public String apagar(Plataforma plataforma) throws Exception {
		ProdutoBo produtoBo = new ProdutoBo();
		Produto produto = new Produto();
		produto.setPlataforma(plataforma);
		if (produtoBo.list("Plataforma", produto).size() >= 1) {
			throw new Exception("J� existem jogos cadastrados nessa plataforma: " + plataforma.getNome());
		} else {
			return remove(plataforma);
		}
	}

	public Plataforma encontrar(Plataforma plataforma) {
		GenericDao<Plataforma> dao = new GenericDao<Plataforma>();
		return dao.findById(Plataforma.class, plataforma.getId());
	}

	public String salvar(Plataforma plataforma) throws Exception {
		if (plataforma.getId() > 0) {
			return atualizar(plataforma, Plataforma.class);
		} else {
			return nova(plataforma, Plataforma.class);
		}
	}
}
