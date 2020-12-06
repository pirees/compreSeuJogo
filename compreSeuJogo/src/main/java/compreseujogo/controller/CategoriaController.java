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
import compreseujogo.model.entity.Categoria;

@RequestScoped
@ManagedBean(name = "categoriaBean")
public class CategoriaController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Categoria categoria;
	private List<Categoria> lista;

	public CategoriaController() {
		this.categoria = new Categoria();
		this.lista = new ArrayList<Categoria>();
	}

	public List<Categoria> carregarLista() {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			return facade.listaCategoria(categoria);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return lista;
	}

	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			context.addMessage(null, new FacesMessage(facade.salvarCategoria(categoria), FacesMessage.FACES_MESSAGES));
			return "listaCategoria.xhtml";
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			context.addMessage(null, message);
		}
		return null;
	}

	public String alterar(Categoria c) {
		this.categoria = c;
		return "cadastroCategoria.xhtml";
	}

	public void excluir(Categoria categoria) {

	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getLista() throws Exception {
		Facade facade = new Facade();
		lista = facade.listaCategoria(categoria);
		return lista;
	}

	public void setLista(List<Categoria> lista) {
		this.lista = lista;
	}
}
