package compreseujogo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import compreseujogo.facade.Facade;
import compreseujogo.model.entity.Estado;
import compreseujogo.model.entity.Fornecedor;
import compreseujogo.model.entity.Transporte;

@RequestScoped
@ManagedBean(name = "fornecedorBean")
public class FornecedorController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Fornecedor fornecedor;
	private List<Fornecedor> lista;

	public FornecedorController() {
		this.fornecedor = new Fornecedor();
		this.lista = new ArrayList<Fornecedor>();
	}

	@PostConstruct
	public void carregarLista() {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			lista = facade.listarFornecedorNome(fornecedor);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}
	
	public void excluir(Fornecedor fornecedor) {
		
	}
	public Estado[] getEstado() {
		return Estado.values();
	}
	
	/*@PostConstruct
	public List<Fornecedor> carregarLista() {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			return facade.listarFornecedorNome(fornecedor);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return lista;
	}*/

	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			context.addMessage(null, new FacesMessage(facade.salvarFornecedor(fornecedor), FacesMessage.FACES_MESSAGES));
			return "";
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return null;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Fornecedor> getLista() throws Exception {
		Facade facade = new Facade();
		lista = facade.listarFornecedorNome(fornecedor);
		return lista;
	}

	public void setLista(List<Fornecedor> lista) {
		this.lista = lista;
	}
	
	public String alterar(Fornecedor f) {
		this.fornecedor = f;
		return "alterarFornecedor.xhtml";
	}
	
	public String atualizarStatus() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			context.addMessage(null,
					new FacesMessage(facade.atualizarFornecedor(this.fornecedor), FacesMessage.FACES_MESSAGES));
			fornecedor = new Fornecedor();
			
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		
		return "listaFornecedor.xhtml";
	}
}
