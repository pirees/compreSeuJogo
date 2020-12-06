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
import compreseujogo.model.entity.Administrador;
import compreseujogo.model.entity.Estado;
import compreseujogo.model.entity.Sexo;
import compreseujogo.model.entity.Vendedor;

@RequestScoped
@ManagedBean(name = "administradorBean")
public class AdministradorController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Administrador administrador;
	private List<Administrador> lista;
	private List <Estado> estados;

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public List<Administrador> getLista() throws Exception {
		Facade facade = new Facade();
		lista = facade.listaAdministrador(administrador);
		
		return lista;
	}

	public void setLista(List<Administrador> lista) {
		this.lista = lista;
	}

	public Estado[] getEstado() {
		return Estado.values();
	}
	
	public Sexo[] getSexo() {
		return Sexo.values();
	}
	
	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	
	public AdministradorController() {
		this.administrador = new Administrador();
		lista = new ArrayList<Administrador>();
	}
	
	public String alterar(Administrador adm) {
		this.administrador = adm;
		return "alterarAdministrador.xhtml";
	}
	
	public void excluir(Administrador adm) {
		
	}
	public List<Administrador> carregarLista() {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			return facade.listaAdministrador(administrador);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return lista;
	}
	
	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			context.addMessage(null,
					new FacesMessage(facade.salvarAdministrador(this.administrador), FacesMessage.FACES_MESSAGES));
			administrador = new Administrador();
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		
		administrador = new Administrador();
	}
	
	public String atualizarStauts() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			context.addMessage(null,
					new FacesMessage(facade.atualizarAdministrador(this.administrador), FacesMessage.FACES_MESSAGES));
			administrador = new Administrador();
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		
		return "listaAdministrador.xhtml";
		
	}

}
