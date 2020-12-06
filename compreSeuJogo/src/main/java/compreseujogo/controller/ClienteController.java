package compreseujogo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import compreseujogo.facade.Facade;
import compreseujogo.model.entity.Cliente;
import compreseujogo.model.entity.Estado;
import compreseujogo.model.entity.Sexo;


@ManagedBean(name = "clienteBean")
@SessionScoped
public class ClienteController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Cliente cliente;
	private List<Cliente> lista;
	private boolean logado;

	public ClienteController() {
		super();
		this.cliente = new Cliente();
		this.lista = new ArrayList<Cliente>();
		this.logado = false;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setLista(List<Cliente> lista) {
		this.lista = lista;
	}
	
	public Sexo[] getSexo() {
		return Sexo.values();
	}

	public Estado[] getEstado() {
		return Estado.values();
	}
	
	public List<Cliente> getLista() throws Exception {
		Facade facade = new Facade();
		lista = facade.listaCliente(cliente);
		
		return lista;
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			context.addMessage(null,
					new FacesMessage(facade.salvarCliente(this.cliente), FacesMessage.FACES_MESSAGES));
			cliente = new Cliente();
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			e.printStackTrace();
		}
		
		cliente = new Cliente();
	}
	
	public List<Cliente> carregarLista() {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			return facade.listaCliente(cliente);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return lista;
	}
	
	public String alterar(Cliente c) {
		this.cliente = c;
		System.out.println(c.getId());
		return "alterarCliente.xhtml";
	}
	
	public String atualizarStatus() {

		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			context.addMessage(null,
					new FacesMessage(facade.atualizarCliente(this.cliente), FacesMessage.FACES_MESSAGES));
			cliente = new Cliente();
			
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		
		System.out.println(cliente.getId());
		return "listaCliente.xhtml";
		
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}
	
	
	
}
