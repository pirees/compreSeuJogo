package compreseujogo.model.bo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import compreseujogo.model.dao.GenericDao;
import compreseujogo.model.dao.PessoaDao;
import compreseujogo.model.entity.Administrador;
import compreseujogo.model.entity.Cliente;
import compreseujogo.model.entity.EntityBase;
import compreseujogo.model.entity.Pessoa;
import compreseujogo.model.entity.Vendedor;

public class PessoaBo<T extends Pessoa> {

	public String saveOrUpdate(Pessoa pessoa) throws Exception {
		validarDados(pessoa);
		GenericDao<Pessoa> tcDao = new GenericDao<Pessoa>();
		try {
			return tcDao.saveOrUpdate(pessoa);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public String remove(Pessoa pessoa) throws Exception {
		GenericDao<Pessoa> tcDao = new GenericDao<Pessoa>();
		try {
			return tcDao.remove(Pessoa.class, pessoa.getId());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<Pessoa> list(String parameter, Pessoa pessoa, Class<T> classe) throws Exception {
		;
		try {
			if (parameter.equals("")) {
				GenericDao<Pessoa> pDao = new GenericDao<Pessoa>();
				return pDao.list(Pessoa.class);
			} else {
				PessoaDao pessoaDao = new PessoaDao();
				return pessoaDao.list(parameter, pessoa, classe);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());

		}
	}

	public String desativarAtivar(EntityBase obj) throws Exception {
		Pessoa pessoa = (Pessoa) obj;
		if (pessoa.isAtivo() == true) {
			// pessoa.setAtivo(true);
			saveOrUpdate(pessoa);
			return "Cadastro ativado";
		} else {
			// pessoa.setAtivo(false);
			saveOrUpdate(pessoa);
			return "Cadastro desativado";
		}
	}

	private void validarDados(Pessoa pessoa) throws Exception {
		if (pessoa.getId() < 0) {
			throw new Exception("Id n�o pode ser negativo!");
		} else if (pessoa.getNome().equals("")) {
		} else if (pessoa.getNome().equals("")) {
			throw new Exception("O nome n�o pode ficar em branco!");
		} else if (pessoa.getSobrenome().equals("")) {
			throw new Exception("O sobrenome n�o pode ficar em branco!");
		} else if (pessoa.getEmail().equals("")) {
			throw new Exception("O e-mail n�o pode ficar em branco!");
		} else if (pessoa.getSenha().equals("")) {
			throw new Exception("A senha n�o pode ficar em branco!");
		} else if (pessoa.getEndereco().equals("")) {
			throw new Exception("O endere�o n�o pode ficar em branco!");
		} else if (pessoa.getTelefone().equals("")) {
			throw new Exception("O telefone n�o pode ficar em branco!");
		} else if (pessoa.getCep().equals("")) {
			throw new Exception("O cep n�o pode ficar em branco!");
		} else if (pessoa.getCpf().equals("")) {
			throw new Exception("O cpf n�o pode ficar em branco!");
		}
	}

	public String novaPessoa(Pessoa pessoa, Class<T> classe) throws Exception {
		if (list("email", pessoa, classe).size() >= 1) {
			throw new Exception("Esse e-mail já está registrado!");
		} else if (list("cpf", pessoa, classe).size() >= 1) {
			throw new Exception("Esse cpf já está registrado!");
		} else {
			pessoa.setAtivo(true);
			pessoa.setDataCadastro(LocalDate.now());
			return saveOrUpdate(pessoa);
		}

	}

	public String atualizar(Pessoa pessoa, Class<T> classe) throws Exception {
		return saveOrUpdate(pessoa);
	}

	public Pessoa login(Pessoa pessoa) throws Exception {
		try {
			return new PessoaDao<Pessoa>().login(pessoa);
		} catch (Exception e) {
			throw new Exception("E-mail ou senha está incorreto");
		}
	}
	
	public Pessoa encontrar(int id) {
		return new GenericDao<Pessoa>().findById(Pessoa.class, id);
	}
}
