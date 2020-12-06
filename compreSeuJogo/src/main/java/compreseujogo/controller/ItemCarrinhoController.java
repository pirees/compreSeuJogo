package compreseujogo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import compreseujogo.facade.Facade;
import compreseujogo.model.entity.Cliente;
import compreseujogo.model.entity.ItemCarrinho;
import compreseujogo.model.entity.Produto;

@RequestScoped
@ManagedBean(name = "itemCarrinhoBean")
public class ItemCarrinhoController implements Serializable {

	private static final long serialVersionUID = 1L;

	private ItemCarrinho item;
	private List<ItemCarrinho> lista;

	public ItemCarrinhoController() {
		super();
		this.item = new ItemCarrinho();
		item.setProduto(new Produto());
		this.lista = new ArrayList<ItemCarrinho>();
	}

	public void salvar(Produto produto, UsePessoaBean use) {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			item.setCarrinho(use.getCliente().getCarrinho());
			item.setProduto(produto);
			item.getProduto();
			facade.adicionarItemCarrinho(item);
			context.addMessage(null, new FacesMessage(
					"O " + item.getProduto().getNome() + " foi adicionado ao carrinho", FacesMessage.FACES_MESSAGES));
			if (!use.getCliente().getCarrinho().getItem().contains(item)) {
				use.getCliente().getCarrinho().getItem().add(item);
			}
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}

	public String adicionarItem(Produto produto, UsePessoaBean use) {
		FacesContext context = FacesContext.getCurrentInstance();
		if (!use.isLogadoCliente()) {
			context.addMessage(null,
					new FacesMessage("Olá, você deve logar no sistema para adicionar um produto ao seu carrinho",
							FacesMessage.FACES_MESSAGES));
			return null;
		} else {
			salvar(produto, use);
			return null;
		}

	}

	public void removerItem(ItemCarrinho item) {
		FacesContext context = FacesContext.getCurrentInstance();
		Facade facade = new Facade();
		try {
			context.addMessage(null, new FacesMessage(facade.removerItemCarrinho(item), FacesMessage.FACES_MESSAGES));
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}

	public ItemCarrinho getItem() {
		return item;
	}

	public void setItem(ItemCarrinho item) {
		this.item = item;
	}

	public List<ItemCarrinho> getLista() {
		return lista;
	}

	public void setLista(List<ItemCarrinho> lista) {
		this.lista = lista;
	}
}
