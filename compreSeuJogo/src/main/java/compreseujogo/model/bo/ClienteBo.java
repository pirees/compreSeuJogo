package compreseujogo.model.bo;

import java.util.List;

import compreseujogo.model.dao.ClienteDao;
import compreseujogo.model.dao.GenericDao;
import compreseujogo.model.entity.Cliente;



public class ClienteBo extends PessoaBo<Cliente> {

	public List<Cliente> list(String parameter, Cliente cliente) throws Exception {
		try {
			if (parameter.equals("")) {
				GenericDao<Cliente> pDao = new GenericDao<Cliente>();
				return pDao.list(Cliente.class);
			} else {
				ClienteDao clienteDao = new ClienteDao();
				return clienteDao.list(parameter, cliente);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());

		}
	}
	
	public String salvar(Cliente cliente) throws Exception {
		if (cliente.getId() > 0) {
			return atualizar(cliente, Cliente.class);			
		} else {
			cliente.setListaDesejos(null);
			cliente.setCarrinho(null);
			novaPessoa(cliente, Cliente.class);
			return createDepency(cliente);
		}
	}
	
	public String createDepency(Cliente cliente) throws Exception {
		CarrinhoBo carrinhoBo = new CarrinhoBo();
		ListaDesejoBo listaDesejosBo = new ListaDesejoBo();
		cliente.setCarrinho(carrinhoBo.novo(cliente));
		cliente.setListaDesejos(listaDesejosBo.novo(cliente));
		try {
			return saveOrUpdate(cliente);
		} catch (Exception e) {
			remove(cliente);
			throw new Exception("Erro ao executar o cadastro" + cliente.getNome());
		}
	}
	public Cliente accesCpf(Cliente cliente) throws Exception{
		try {
			return new ClienteDao().acessCpf(cliente);
		} catch (Exception e) {
			throw new Exception("Não foi possível localizar o cliente");
		}
	}
}
