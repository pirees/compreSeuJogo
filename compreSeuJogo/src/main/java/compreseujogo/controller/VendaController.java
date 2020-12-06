package compreseujogo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import compreseujogo.facade.Facade;
import compreseujogo.model.entity.Cliente;
import compreseujogo.model.entity.ItemCarrinho;
import compreseujogo.model.entity.ItemVenda;
import compreseujogo.model.entity.Transporte;
import compreseujogo.model.entity.Venda;
import compreseujogo.model.entity.Vendedor;

@RequestScoped
@ManagedBean(name = "vendaBean")
public class VendaController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Venda venda;
	private List<Venda> lista;
	private List<Transporte> transporte;

	public VendaController() {
		super();
		this.venda = new Venda();
		this.lista = new ArrayList<Venda>();
		this.transporte = new ArrayList<Transporte>();
	}

	public String salvar(UsePessoaBean use) {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		venda.setCliente(use.getCliente());
		try {
			context.addMessage(null, new FacesMessage(facade.novaVenda("online", venda), FacesMessage.FACES_MESSAGES));
			use.getCliente().getCarrinho().setItem(new ArrayList<ItemCarrinho>());
			ItemVenda item = new ItemVenda();
			item.setVenda(venda);
			venda.setItem(new Facade().listaItemVenda(item));
			return selecionar(venda);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return null;
	}

	public String salvarVendedor(UsePessoaBean use) {
		FacesContext context = FacesContext.getCurrentInstance();
		venda.setVendedor(use.getVendedor());
		venda.setCliente(use.getCliente());
		try {
			context.addMessage(null,
					new FacesMessage(new Facade().novaVenda("fisica", venda), FacesMessage.FACES_MESSAGES));
			use.setCliente((Cliente) new Facade().encontrarPessoa(use.getCliente()));
			use.setVendedor((Vendedor) new Facade().encontrarPessoa(use.getVendedor()));
			use.setLogadoCliente(false);
			use.getCliente().getCarrinho().setItem(new ArrayList<ItemCarrinho>());
			use.setCliente(new Cliente());
			ItemVenda item = new ItemVenda();
			item.setVenda(venda);
			venda.setItem(new Facade().listaItemVenda(item));
			return selecionar(venda);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			return null;
		}
	}
	
	public List<Venda> proximasVenda(){
		return new Facade().listaVenda("PVendas", venda);
	}
	public List<Venda> listaCliente(Cliente cliente) {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			venda.setCliente(cliente);
			return facade.listaVenda("Cliente", venda);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			return null;
		}
	}
	
	public void encontrar() {
		venda = new Facade().encontrarVenda(venda.getId());
	}

	public String selecionar(Venda venda) {
		return "pedido.xhtml?id=" + venda.getId() + "&faces-redirect=true";
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public List<Venda> getLista() {
		return lista;
	}

	public void setLista(List<Venda> lista) {
		this.lista = lista;
	}

	public List<Transporte> getTransporte() {
		return transporte;
	}

	public void setTransporte(List<Transporte> transporte) {
		this.transporte = transporte;
	}
	

	@PostConstruct
	public void carregarLista() {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			transporte = facade.listaTransporte(null);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}
	
	@PostConstruct
	public List<Venda> carregarVendas() {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			return facade.listaVenda("", venda);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return lista;
	}
	
	
}
