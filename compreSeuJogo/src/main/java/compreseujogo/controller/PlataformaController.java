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
import compreseujogo.model.entity.Plataforma;

@RequestScoped
@ManagedBean(name = "plataformaBean")
public class PlataformaController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Plataforma plataforma;
	private List<Plataforma> lista;

	public PlataformaController() {
		this.plataforma = new Plataforma();
		this.lista = new ArrayList<Plataforma>();
	}

	@PostConstruct
	public void carregar() {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			lista = facade.listaPlataforma(plataforma);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}

	public String novo() {
		this.plataforma = new Plataforma();
		return "cadastroPlataforma.xhtml";
	}

	public String alterar(Plataforma plataforma) {
		this.plataforma = plataforma;
		this.lista = new ArrayList<Plataforma>();
		return "cadastroPlataforma.xhtml";
	}
	public void excluir(Plataforma plataforma) {
		
	}

	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			context.addMessage(null,
					new FacesMessage(facade.salvarPlataforma(plataforma), FacesMessage.FACES_MESSAGES));
			plataforma = new Plataforma();
			return "listaPlataforma.xhtml";
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return "cadastroPlataforma.xhtml";
	}

	public Plataforma getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}

	public List<Plataforma> getLista() {
		return lista;
	}

	public void setLista(List<Plataforma> lista) {
		this.lista = lista;
	}

}
