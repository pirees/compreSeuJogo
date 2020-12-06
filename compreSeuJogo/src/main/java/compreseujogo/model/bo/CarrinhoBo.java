package compreseujogo.model.bo;

import java.util.List;

import compreseujogo.model.dao.CarrinhoDao;
import compreseujogo.model.dao.GenericDao;
import compreseujogo.model.entity.Carrinho;
import compreseujogo.model.entity.Cliente;
import compreseujogo.model.entity.Item;



public class CarrinhoBo {

	public String saveOrUpdate(Carrinho carrinho) throws Exception {
		validarDados(carrinho);
		GenericDao<Carrinho> tcDao = new GenericDao<Carrinho>();
		try {
			return tcDao.saveOrUpdate(carrinho);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<Carrinho> list(String parameter, Carrinho carrinho) throws Exception {
		try {
			if (parameter.equals("")) {
				GenericDao<Carrinho> tcDao = new GenericDao<Carrinho>();
				return tcDao.list(Carrinho.class);
			} else {
				CarrinhoDao carrinhoDao = new CarrinhoDao();
				return carrinhoDao.list(parameter, carrinho);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public String remove(Carrinho carrinho) throws Exception {
		GenericDao<Carrinho> tcDao = new GenericDao<Carrinho>();
		try {
			return tcDao.remove(Carrinho.class, carrinho.getId());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private void validarDados(Carrinho carrinho) throws Exception {
		if (carrinho.getId() < 0) {
			throw new Exception("Id n�o pode ser negativo!");
		} else if (carrinho.getValor() < 0) {
			throw new Exception("O valor total dos itens no carrinho est� com erro!");
		}
	}

	public Carrinho novo(Cliente cliente) throws Exception {
		Carrinho carrinho = new Carrinho(0, 0.0, cliente);
		saveOrUpdate(carrinho);
		return carrinho;
	}
	public String aumentorValor(Item item, Carrinho carrinho) throws Exception {
		carrinho.setValor(carrinho.getValor()+item.getValor());
		return saveOrUpdate(carrinho);
	}
	public String diminuirValor(Item item, Carrinho carrinho) throws Exception {
		carrinho.setValor(carrinho.getValor()-item.getValor());
		return saveOrUpdate(carrinho);
	}

	public void zerar(Carrinho carrinho) throws Exception {
		try {
			carrinho.setValor(0.0);
			saveOrUpdate(carrinho);
		} catch (Exception e) {
			throw new Exception("Falha ao zerar o valor do carrinho!");
		}
		
	}
}