package compreseujogo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import compreseujogo.facade.Facade;
import compreseujogo.model.entity.Avaliacao;
import compreseujogo.model.entity.Cliente;
import compreseujogo.model.entity.Produto;
import compreseujogo.model.entity.Transporte;
import compreseujogo.model.entity.Venda;

@RequestScoped
@ManagedBean(name = "avaliacaoBean")
public class AvaliacaoController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Avaliacao avaliacao;
	private List<Avaliacao> lista;

	public AvaliacaoController() {
		this.avaliacao = new Avaliacao();
		this.lista = new ArrayList<Avaliacao>();
	}

	public List<Avaliacao> listaAvaliacao(Produto produto) {
		avaliacao.setProduto(produto);
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			return facade.listaAvaliacao("Produto", this.avaliacao);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return null;

	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public List<Avaliacao> getLista() {
		return lista;
	}

	public void setLista(List<Avaliacao> lista) {
		this.lista = lista;
	}
	
	public void salvar(Cliente cliente, Produto produto) {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		avaliacao.setCliente(cliente);
		avaliacao.setProduto(produto);
		try {
			context.addMessage(null,
					new FacesMessage(facade.salvarAvaliacao(this.avaliacao), FacesMessage.FACES_MESSAGES));
			avaliacao = new Avaliacao();
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			e.printStackTrace();
		}
	}
	
	public String avaliar(Produto p) {
		this.avaliacao.setProduto(p);
		return "avaliacaoCliente.xhtml?id=" + p.getId() + "&faces-redirect=true";
	}
}
