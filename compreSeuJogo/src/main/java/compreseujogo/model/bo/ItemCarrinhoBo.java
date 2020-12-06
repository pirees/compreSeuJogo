package compreseujogo.model.bo;

import java.util.List;

import compreseujogo.model.dao.GenericDao;
import compreseujogo.model.dao.ItemCarrinhoDao;
import compreseujogo.model.entity.ItemCarrinho;

public class ItemCarrinhoBo extends ItemBo {

	private ItemCarrinhoDao dao;

	public List<ItemCarrinho> listar(String parameter, ItemCarrinho itemCarrinho) throws Exception {
		try {
			if (parameter.equals("")) {
				GenericDao<ItemCarrinho> itemDao = new GenericDao<ItemCarrinho>();
				return itemDao.list(ItemCarrinho.class);
			} else {
				ItemCarrinhoDao itemDao = new ItemCarrinhoDao();
				return itemDao.listar(parameter, itemCarrinho);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());

		}
	}

	public ItemCarrinho novo(ItemCarrinho item) throws Exception {
		if (item.getProduto().getQuantEstoque() < item.getQuantidade()) {
			throw new Exception("No momento não temos essa quantidade do produto em estoque");
		}
		item.setValor(item.getProduto().getValor() * item.getQuantidade());
		saveOrUpdate(item);
		return item;
	}

	public void validarQuantidade(List<ItemCarrinho> lista) throws Exception {
		for (ItemCarrinho item : lista) {
			if (item.getProduto().getQuantEstoque() < item.getQuantidade()) {
				throw new Exception("No momento não temos essa quantidade do produto: " + item.getProduto().getNome()
						+ " em estoque");
			}
		}
	}

	public void apagarItens(List<ItemCarrinho> itens) throws Exception {
		for (ItemCarrinho item : itens) {
			try {
				item.setProduto(null);
				item.setCarrinho(null);
				saveOrUpdate(item);
				remove(item);
			} catch (Exception e) {
				throw new Exception("Falha ao apagar os item do carrinho");
			}
		}
	}
}
