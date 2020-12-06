package compreseujogo.model.bo;

import java.util.List;

import compreseujogo.model.dao.ComissaoDao;
import compreseujogo.model.dao.GenericDao;
import compreseujogo.model.entity.Comissao;
import compreseujogo.model.entity.Venda;

public class ComissaoBo {

	public String saveOrUpdate(Comissao comissao) throws Exception {
		validarDados(comissao);
		GenericDao<Comissao> tcDao = new GenericDao<Comissao>();
		try {
			return tcDao.saveOrUpdate(comissao);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private void validarDados(Comissao comissao) throws Exception {
		if (comissao.getId() < 0) {
			throw new Exception("O id da comiss�o pn�o pode ser negativo!");
		} else if (comissao.getDinheiro() < 0) {
			throw new Exception("O dinheiro da comiss�o n�o pode ficar negativo");
		} else if (comissao.getPorcentagem() < 0) {
			throw new Exception("A porcentagem da comiss�o n�o pode ser negativo");
		}
	}

	public String remove(Comissao comissao) throws Exception {
		GenericDao<Comissao> tcDao = new GenericDao<Comissao>();
		try {
			return tcDao.remove(Comissao.class, comissao.getId());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<Comissao> list(String parameter, Comissao comissao) throws Exception {
		try {
			if (parameter.equals("")) {
				GenericDao<Comissao> tcDao = new GenericDao<Comissao>();
				return tcDao.list(Comissao.class);
			} else {
				ComissaoDao comissaoDao = new ComissaoDao();
				return comissaoDao.list(parameter, comissao);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void adicionarVenda(Venda venda) throws Exception {
		venda.getVendedor().getComissao().setDinheiro(venda.getVendedor().getComissao().getDinheiro()
				+ venda.getVendedor().getComissao().getPorcentagem() * venda.getValor());
		try {
			saveOrUpdate(venda.getVendedor().getComissao());
		} catch (Exception e) {
			throw new Exception("Falha ao adicionar a comissão");
		}
	}
}
