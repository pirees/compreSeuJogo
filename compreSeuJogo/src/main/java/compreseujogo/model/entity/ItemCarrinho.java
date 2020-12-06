package compreseujogo.model.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_itemCarrinho")
public class ItemCarrinho extends Item {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Carrinho carrinho;

	public ItemCarrinho(int id, LocalDate dataAdicionado, double valor, int quantidade, Produto produto,
			Carrinho carrinho) {
		super(id, dataAdicionado, valor, quantidade, produto);
		this.carrinho = carrinho;
		this.setValor(getProduto().getValor() * getQuantidade());
	}


	public ItemCarrinho() {
		super();
		this.carrinho = new Carrinho();
		this.setValor(getProduto().getValor() * getQuantidade());
	}

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((carrinho == null) ? 0 : carrinho.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemCarrinho other = (ItemCarrinho) obj;
		if (carrinho == null) {
			if (other.carrinho != null)
				return false;
		} else if (!carrinho.equals(other.carrinho))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "itemCarrinho [carrinho=" + carrinho.getId() + ", getId()=" + getId() + ", getDataAdicionado()="
				+ getDataAdicionado() +" , valor= "+getValor()+ ", getQuantidade()=" + getQuantidade() + ", getProduto()=" + getProduto().getId() + "]";
	}
}
