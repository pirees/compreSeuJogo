package compreseujogo.model.bo;

import java.util.List;

import compreseujogo.model.dao.AdministradorDao;
import compreseujogo.model.dao.GenericDao;
import compreseujogo.model.entity.Administrador;
import compreseujogo.model.entity.Loja;

public class AdministradorBo extends PessoaBo<Administrador> {

	public List<Administrador> list(String parameter, Administrador administrador) throws Exception {
		try {
			if (parameter.equals("")) {
				GenericDao<Administrador> genericDao = new GenericDao<Administrador>();
				return genericDao.list(Administrador.class);
			} else {
				AdministradorDao administradorDao = new AdministradorDao();
				return administradorDao.list(parameter, administrador);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());

		}
	}

	public String salvar(Administrador administrador, Loja loja) throws Exception {
		if (administrador.getId() > 0) {
			return atualizar(administrador, Administrador.class);			
		} else {
			administrador.setLoja(loja);
			return novaPessoa(administrador, Administrador.class);
		}
	}
}
