package compreseujogo.model.bo;

import java.util.List;

import compreseujogo.model.dao.GenericDao;
import compreseujogo.model.dao.ListaDesejosDao;
import compreseujogo.model.entity.Cliente;
import compreseujogo.model.entity.ListaDesejos;

public class ListaDesejoBo {

	public String saveOrUpdate(ListaDesejos lista) throws Exception {
		validarDados(lista);
		GenericDao<ListaDesejos> tcDao = new GenericDao<ListaDesejos>();
		try {
			return tcDao.saveOrUpdate(lista);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public String remove(ListaDesejos lista) throws Exception {
		GenericDao<ListaDesejos> tcDao = new GenericDao<ListaDesejos>();
		try {
			return tcDao.remove(ListaDesejos.class, lista.getId());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<ListaDesejos> list(String parameter, ListaDesejos lista) throws Exception {
		try {
			if (parameter.equals("")) {
				GenericDao<ListaDesejos> pDao = new GenericDao<ListaDesejos>();
				return pDao.list(ListaDesejos.class);
			} else {
				ListaDesejosDao listaDao = new ListaDesejosDao();
				return listaDao.list(parameter, lista);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());

		}
	}

	private void validarDados(ListaDesejos lista) throws Exception {
		if (lista.getId() < 0) {
			throw new Exception("Id n�o pode ser negativo!");
		}
	}

	public ListaDesejos novo(Cliente cliente) throws Exception {
		ListaDesejos listaDesejos = new ListaDesejos(0, cliente);
		saveOrUpdate(listaDesejos);
		return listaDesejos;
	}
}
