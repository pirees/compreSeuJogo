package compreseujogo.model.bo;

import java.time.LocalDate;
import java.util.List;

import compreseujogo.model.dao.GenericDao;
import compreseujogo.model.dao.ProdutoDao;
import compreseujogo.model.entity.ItemCarrinho;
import compreseujogo.model.entity.Plataforma;
import compreseujogo.model.entity.Produto;

public class ProdutoBo {
	
	private ProdutoDao dao;
	private GenericDao<Produto> gDao;
	
	
	public ProdutoBo() {
		super();
		this.dao = new ProdutoDao();
		this.gDao = new GenericDao<Produto>();
	}

	public String saveOrUpdate(Produto produto) throws Exception {
		validarDados(produto);
		GenericDao<Produto> tcDao = new GenericDao<Produto>();
		try {
			return tcDao.saveOrUpdate(produto);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public String deletar(Produto produto) throws Exception {
		GenericDao<Produto> tcDao = new GenericDao<Produto>();
		try {
			return tcDao.remove(Produto.class, produto.getId());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<Produto> list(String parameter, Produto produto) throws Exception {
		try {
			if (parameter.equals("")) {
				GenericDao<Produto> tcDao = new GenericDao<Produto>();
				return tcDao.list(Produto.class);
			} else {
				ProdutoDao produtoDao = new ProdutoDao();
				return produtoDao.list(parameter, produto);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<Produto> listSearch(String filter) throws Exception {
		try {
			ProdutoDao produtoDao = new ProdutoDao();
			return produtoDao.listSearch(filter);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private void validarDados(Produto produto) throws Exception {
		if (produto.getId() < 0) {
			throw new Exception("Id n�o pode ser negativo!");
		} else if (produto.getNome().equals("")) {
			throw new Exception("O nome n�o pode ficar em branco!");
		} else if (produto.getQuantEstoque() < 0) {
			throw new Exception("A quantidade de estoque n�o pode ser negativa!");
		} else if (produto.getValor() < 0) {
			throw new Exception("O valor n�o pode ser negativo!");
		} else if (produto.getSku().equals("")) {
			throw new Exception("O SKU n�o pode ficar em branco!");
		} else if (produto.getEAN() < 0) {
			throw new Exception("O EAN n�o pode ser negativo!");
		}
	}

	public void dimuirQuantidade(List<ItemCarrinho> list) throws Exception {
		for (ItemCarrinho item : list) {
			item.getProduto().setQuantEstoque(item.getProduto().getQuantEstoque()-item.getQuantidade());
			saveOrUpdate(item.getProduto());
		}
	}

	public String desativarAtivar(Produto produto) throws Exception {
		if (produto.isAtivo()) {
			produto.setAtivo(false);
			saveOrUpdate(produto);
			return "Foi desativado o produto: " + produto.getNome();
		} else {
			produto.setAtivo(true);
			saveOrUpdate(produto);
			return "Foi ativado o produto: " + produto.getNome();
		}
	}

	public String novo(Produto produto) throws Exception {
		produto.setSku(gerarSku(produto));
		produto.setNota(5);
		produto.setDataCadastro(LocalDate.now());
		if (list("nome", produto).size() > 0) {
			throw new Exception("Já existe um produto cadastrado com esse nome!");
		} else if (list("SKU", produto).size() > 0) {
			throw new Exception("Já existe um produto cadastrado com esse SKU!");
		} else if (list("EAN", produto).size() > 0) {
			throw new Exception("Já existe um produto cadastrado com esse EAN!");
		} else {
			return saveOrUpdate(produto);
		}
	}

	public String salvar(Produto produto) throws Exception {
		if (produto.getId() > 0) {
			return atualizar(produto);
		} else {
			return novo(produto);
		}
	}

	public String atualizar(Produto produto) throws Exception {
		try {
			return saveOrUpdate(produto);
		} catch (Exception e) {
			throw new Exception("Erro ao atualizar o produto");
		}

	}

	private String gerarSku(Produto produto) throws Exception {
		System.out.println("Vazio");
		int i = list("Plataforma", produto).size();
		System.out.println("Tamanho lista: " + i);
		System.out.println("SHOW");
		int size = list("Plataforma", produto).size();
		try {
			return produto.getPlataforma().getCodigoSku() + "-" + gerarNumeroSku(++size);
		} catch (Exception e) {
			throw new Exception("Falha ao criar o SKU do produto");
		}
	}

	private String gerarNumeroSku(int size) {
		if (size < 10) {
			return "000" + size;
		} else if (size < 100) {
			return "00" + size;
		} else if (size < 1000) {
			return "0" + size;
		} else {
			return "falha";
		}
	}
	public Produto encontrar(int id) {
		return gDao.findById(Produto.class, id);
	}
}
