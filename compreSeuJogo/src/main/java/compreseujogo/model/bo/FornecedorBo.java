package compreseujogo.model.bo;

import java.util.List;

import compreseujogo.model.dao.FornecedorDao;
import compreseujogo.model.dao.GenericDao;
import compreseujogo.model.entity.Fornecedor;


public class FornecedorBo extends EmpresaBo<Fornecedor> {

	public List<Fornecedor> list(String parameter, Fornecedor fornecedor) throws Exception {
		try {
			if (parameter.equals("")) {
				GenericDao<Fornecedor> pDao = new GenericDao<Fornecedor>();
				return pDao.list(Fornecedor.class);
			} else {
				FornecedorDao fornecedorDao = new FornecedorDao();
				return fornecedorDao.list(parameter, fornecedor);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());

		}
	}
}
