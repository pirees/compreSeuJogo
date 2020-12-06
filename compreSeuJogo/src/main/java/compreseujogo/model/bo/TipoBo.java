package compreseujogo.model.bo;

import java.util.List;

import compreseujogo.model.dao.GenericDao;
import compreseujogo.model.dao.TipoDao;
import compreseujogo.model.entity.EntityBase;
import compreseujogo.model.entity.Tipo;

public class TipoBo<T extends Tipo> {

	public String saveOrUpdate(EntityBase obj) throws Exception {
		validarDados((Tipo) obj);
		GenericDao<Tipo> tcDao = new GenericDao<Tipo>();
		try {
			return tcDao.saveOrUpdate((Tipo) obj);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public String remove(EntityBase obj) throws Exception {
		GenericDao<Tipo> tcDao = new GenericDao<Tipo>();
		try {
			return tcDao.remove(Tipo.class, obj.getId());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<Tipo> list(String parameter, Tipo tipo, Class<T> classe) throws Exception {
		try {
			if (parameter.equals("")) {
				GenericDao<Tipo> pDao = new GenericDao<Tipo>();
				return pDao.list(Tipo.class);
			} else {
				TipoDao tipoDao = new TipoDao();
				return tipoDao.list(parameter, tipo, classe);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());

		}
	}

	public String desativarAtivar(Tipo tipo) throws Exception {
		if (tipo.isAtivo()) {
			tipo.setAtivo(false);
			saveOrUpdate(tipo);
			return "Foi desativada " + tipo.getNome();
		} else {
			tipo.setAtivo(true);
			saveOrUpdate(tipo);
			return "Foi ativada " + tipo.getNome();
		}
	}

	private void validarDados(Tipo tipo) throws Exception {

		if (tipo.getId() < 0) {
			throw new Exception("Id n�o pode ser negativo!");
		} else if (tipo.getNome().equals("")) {
			throw new Exception("O nome n�o pode ficar em branco!");
		}
	}

	public String nova(Tipo tipo, Class<T> classe) throws Exception {
		if (list("novo", tipo, classe).size() >= 1) {
			throw new Exception("Já está cadastrado esse nome");
		} else {
			tipo.setAtivo(true);
			return saveOrUpdate(tipo);
		}
	}

	public String atualizar(Tipo tipo, Class<T> classe) throws Exception {
		return saveOrUpdate(tipo);
	}

}
