package compreseujogo.model.bo;

import java.util.List;

import compreseujogo.model.dao.GenericDao;
import compreseujogo.model.dao.MarcaDao;
import compreseujogo.model.dao.PlataformaDao;
import compreseujogo.model.entity.Marca;
import compreseujogo.model.entity.Plataforma;
import compreseujogo.model.entity.Produto;

public class MarcaBo extends TipoBo<Marca> {

	public List<Marca> listar(String parameter, Marca marca) throws Exception {
		try {
			if (parameter.equals("")) {
				GenericDao<Marca> gDao = new GenericDao<Marca>();
				return gDao.list(Marca.class);
			} else {
				MarcaDao dao = new MarcaDao();
				return dao.list(parameter, marca);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public String apagar(Marca marca) throws Exception {
		ProdutoBo produtoBo = new ProdutoBo();
		Produto produto = new Produto();
		produto.setMarca(marca);
		if (produtoBo.list("Marca", produto).size() >= 1) {
			throw new Exception("J� existem jogos cadastrados nessa marca: " + marca.getNome());
		} else {
			return remove(marca);
		}
	}

	public String salvar(Marca marca) throws Exception {
		if (marca.getId() > 0) {
			return nova(marca, Marca.class);
		} else {
			return nova(marca, Marca.class);
		}
	}
}
