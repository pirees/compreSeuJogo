package compreseujogo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import compreseujogo.facade.Facade;
import compreseujogo.model.entity.Marca;

@RequestScoped
@ManagedBean(name = "marcaBean")
public class MarcaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Marca marca;
	@Inject
	private List<Marca> lista;

	public MarcaController() {
		this.marca = new Marca();
		this.lista = new ArrayList<Marca>();
	}

	@PostConstruct
	public void carregarLista() {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			lista = facade.listaMarca(marca);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}

	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();

		try {
			context.addMessage(null, new FacesMessage(facade.salvarMarca(marca), FacesMessage.FACES_MESSAGES));
			return "";
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return null;
	}

	public String alterar(Marca marca) {
		this.marca = marca;
		return "cadastroMarca.xhtml";
	}
	public void excluir(Marca marca) {

	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public List<Marca> getLista() {
		return lista;
	}

	public void setLista(List<Marca> lista) {
		this.lista = lista;
	}

}
