package compreseujogo.model.bo;

import java.util.List;

import compreseujogo.model.dao.GenericDao;
import compreseujogo.model.dao.LojaDao;
import compreseujogo.model.entity.Loja;

public class LojaBo extends EmpresaBo<Loja> {
	
	public List<Loja> list(String parameter, Loja loja) throws Exception {
		try {
			if (parameter.equals("")) {
				GenericDao<Loja> pDao = new GenericDao<Loja>();
				return pDao.list(Loja.class);
			} else {
				LojaDao lojaDao = new LojaDao();
				return lojaDao.list(parameter, loja);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());

		}
	}
}
