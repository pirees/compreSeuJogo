package compreseujogo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import compreseujogo.facade.Facade;
import compreseujogo.model.entity.Estado;
import compreseujogo.model.entity.Sexo;
import compreseujogo.model.entity.Vendedor;
import compreseujogo.util.WebServiceCep;

@ManagedBean(name = "vendedorBean")
@RequestScoped
public class VendedorController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Vendedor vendedor;
	private List<Vendedor> lista;
	private List <Estado> estados;
	
	public void excluir(Vendedor vendedor) {
		
	}
	
	public Sexo[] getSexo() {
		return Sexo.values();
	}

	public Estado[] getEstado() {
		return Estado.values();
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	
	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Vendedor> getLista() throws Exception {
		Facade facade = new Facade();
		lista = facade.listaVendedor(vendedor);
		
		return lista;
	}

	public void setLista(List<Vendedor> lista) {
		this.lista = lista;
	}

	public VendedorController() {
		vendedor = new Vendedor();
		lista = new ArrayList<Vendedor>();
	}

	public void buscaCep() {
		WebServiceCep w = WebServiceCep.searchCep(vendedor.getCep());

		vendedor.setCidade(w.getCidade());
		vendedor.setEndereco(w.getLogradouro());
		// vendedor.setEstado(w.getUf());
		vendedor.setBairro(w.getBairro());

		System.out.println(w.getCidade());
	}

	public List<Vendedor> carregarLista() {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			return facade.listaVendedor(vendedor);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return lista;
	}
	
	public String alterar(Vendedor v) {
		this.vendedor = v;
		return "alterarVendedor.xhtml";
	}
		
	
	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			context.addMessage(null,
					new FacesMessage(facade.salvarVendedor(this.vendedor), FacesMessage.FACES_MESSAGES));
			vendedor = new Vendedor();
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		
		vendedor = new Vendedor();
	}
	
	public String atualizarStauts() {
	
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			context.addMessage(null,
					new FacesMessage(facade.atualizarVendedor(this.vendedor), FacesMessage.FACES_MESSAGES));
			vendedor = new Vendedor();
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		
		return "listaVendedor.xhtml";
		
	}
}
