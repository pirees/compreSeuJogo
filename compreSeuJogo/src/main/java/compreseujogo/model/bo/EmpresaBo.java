package compreseujogo.model.bo;

import java.util.List;

import compreseujogo.model.dao.EmpresaDao;
import compreseujogo.model.dao.GenericDao;
import compreseujogo.model.entity.Empresa;
import compreseujogo.model.entity.EntityBase;

public class EmpresaBo<T extends Empresa> {

	public String saveOrUpdate(Empresa empresa) throws Exception {
		validarDados(empresa);
		GenericDao<Empresa> tcDao = new GenericDao<Empresa>();
		try {
			return tcDao.saveOrUpdate(empresa);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private void validarDados(Empresa empresa) throws Exception {
		if (empresa.getId() < 0) {
			throw new Exception("O id não pode ser negativo");
		} else if (empresa.getNome().equals("")) {
			throw new Exception("O nome não pode ficar em branco");
		} else if (empresa.getEmail().equals("")) {
			throw new Exception("O e-mail não pode ficar em branco");
		} else if (empresa.getEstado().equals("")) {
			throw new Exception("O estado não pode ficar em branco");
		} else if (empresa.getUrl().equals("")) {
			throw new Exception("A url não pode ficar em branco");
		} else if (empresa.getCnpj().equals("")) {
			throw new Exception("O cnpj não pode ficar em branco");
		} else if (empresa.getCep().equals("")) {
			throw new Exception("O cep não pode ficar em branco");
		} else if (empresa.getEndereco().equals("")) {
			throw new Exception("O endereço não pode ficar em branco");
		} else if (empresa.getTelefone().equals("")) {
			throw new Exception("O telefone não pode ficar em branco");
		}
	}

	public String remove(Empresa empresa) throws Exception {
		GenericDao<Empresa> tcDao = new GenericDao<Empresa>();
		try {
			return tcDao.remove(Empresa.class, empresa.getId());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<Empresa> list(String parameter, Empresa empresa, Class<T> classe) throws Exception {
		try {
			if (parameter.equals("")) {
				GenericDao<Empresa> pDao = new GenericDao<Empresa>();
				return pDao.list(Empresa.class);
			} else {
				EmpresaDao empresaDao = new EmpresaDao();
				return empresaDao.list(parameter, empresa, classe);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());

		}
	}

	public String desativarAtivar(Empresa empresa) throws Exception {
		if (empresa.getAtivo() == true) {
			// empresa.setAtivo(false);
			saveOrUpdate(empresa);
			return "Cadastro ativado";
		} else {
			// empresa.setAtivo(true);
			saveOrUpdate(empresa);
			return "Cadastro desativado";
		}
	}

	public String novaEmpresa(Empresa empresa, Class<T> classe) throws Exception {
		if (list("email", empresa, classe).size() > 0) {
			throw new Exception("Esse e-mail já está cadastrado no sistema");
		} else if (list("cnpj", empresa, classe).size() > 0) {
			throw new Exception("Esse cnpj já está cadastrado no sistema");
		} else {
			return saveOrUpdate(empresa);
		}
	}

	public Empresa encontrar(Empresa empresa) {
		return new GenericDao<Empresa>().findById(Empresa.class, empresa.getId());
	}
}
