package compreseujogo.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import compreseujogo.facade.Facade;
import compreseujogo.model.entity.Administrador;
import compreseujogo.model.entity.Cliente;
import compreseujogo.model.entity.Pessoa;
import compreseujogo.model.entity.Vendedor;

@SessionScoped
@ManagedBean(name = "UsePessoaBean")
public class UsePessoaBean {

	private String email;
	private String senha;
	private Administrador administrador;
	private Cliente cliente;
	private Vendedor vendedor;
	private boolean logadoAdm;
	private boolean logadoCliente;
	private boolean logadoVendedor;

	public UsePessoaBean() {
		super();
		this.administrador = new Administrador();
		this.cliente = new Cliente();
		this.vendedor = new Vendedor();
	}

	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			Pessoa pessoa = new Facade().login(administrador);

			if (pessoa instanceof Administrador) {
				administrador = (Administrador) pessoa;
				logadoAdm = true;
				return "homeAdm.xhtml?faces-redirect=true";
			} else if (pessoa instanceof Vendedor) {
				vendedor = (Vendedor) pessoa;
				logadoVendedor = true;
				return "homeAdm.xhtml?faces-redirect=true";
			} else if (pessoa instanceof Cliente) {
				cliente = (Cliente) pessoa;
				logadoCliente = true;
				return "index.xhtml?faces-redirect=true";
			}
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
		return null;
	}

	public String acessoVendedor() {
		FacesMessage message = null;
		try {
			cliente = new Facade().clienteAcessoCpf(cliente);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", cliente.getNome());
			this.logadoCliente = true;
			return "index.xhtml?faces-redirect=true";
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), e.getMessage());
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
		return null;
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		this.logadoAdm = false;
		this.logadoVendedor = false;
		this.logadoCliente = false;
		return "index.xhtml?faces-redirect=true";
	}

	public String logoutClienteVendedor() {
		logadoCliente = false;
		return "homeAdm.xhtml?faces-redirect=true";

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public boolean isLogadoAdm() {
		return logadoAdm;
	}

	public void setLogadoAdm(boolean logadoAdm) {
		this.logadoAdm = logadoAdm;
	}

	public boolean isLogadoCliente() {
		return logadoCliente;
	}

	public void setLogadoCliente(boolean logadoCliente) {
		this.logadoCliente = logadoCliente;
	}

	public boolean isLogadoVendedor() {
		return logadoVendedor;
	}

	public void setLogadoVendedor(boolean logadoVendedor) {
		this.logadoVendedor = logadoVendedor;
	}
}
