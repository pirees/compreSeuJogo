package compreseujogo.model.bo;

import java.time.LocalDate;
import java.util.List;

import compreseujogo.model.dao.GenericDao;
import compreseujogo.model.dao.VendedorDao;
import compreseujogo.model.entity.Comissao;
import compreseujogo.model.entity.Vendedor;



public class VendedorBo extends PessoaBo<Vendedor> {

	public List<Vendedor> list(String parameter, Vendedor vendedor) throws Exception {
		try {
			if (parameter.equals("")) {
				GenericDao<Vendedor> pDao = new GenericDao<Vendedor>();
				return pDao.list(Vendedor.class);
			} else {
				VendedorDao vendedorDao = new VendedorDao();
				return vendedorDao.list(parameter, vendedor);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());

		}
	}
	
	public String salvar(Vendedor vendedor) throws Exception {
		if (vendedor.getId() > 0) {
			return atualizar(vendedor, Vendedor.class);			
		} else {
			vendedor.setComissao(null);
			vendedor.setLoja(null);
			novaPessoa(vendedor, Vendedor.class);
			return createDepency(vendedor);
		}
	}
	
	public String createDepency(Vendedor vendedor) throws Exception {
		LojaBo bo = new LojaBo();
		ComissaoBo cBo = new ComissaoBo();
		Comissao comissao = new Comissao(0, 0, 0.15, LocalDate.now(), vendedor);
		cBo.saveOrUpdate(comissao);
		vendedor.setLoja(bo.list("", null).get(0));
		vendedor.setComissao(comissao);
		try {
			return saveOrUpdate(vendedor);
		} catch (Exception e) {
			remove(vendedor);
			throw new Exception("Erro no cadastro de " + vendedor.getNome());
		}
	}
}
