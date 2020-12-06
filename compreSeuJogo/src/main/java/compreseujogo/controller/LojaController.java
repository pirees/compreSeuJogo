package compreseujogo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import compreseujogo.model.entity.Loja;

@RequestScoped
@ManagedBean(name = "lojaBean")
public class LojaController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Loja loja;
	private List<Loja> lista;
	
	public LojaController() {
		this.loja = new Loja();
		this.lista = new ArrayList<Loja>();
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public List<Loja> getLista() {
		return lista;
	}

	public void setLista(List<Loja> lista) {
		this.lista = lista;
	}
}
