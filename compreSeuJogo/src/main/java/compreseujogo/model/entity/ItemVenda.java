package compreseujogo.model.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_itemVenda")
public class ItemVenda extends Item {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Venda venda;

	public ItemVenda(int id, LocalDate dataAdicionado, double valor, int quantidade, Produto produto, Venda venda) {
		super(id, dataAdicionado, valor, quantidade, produto);
		this.venda = venda;
	}

	public ItemVenda() {
		super();
		this.venda = new Venda();
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((venda == null) ? 0 : venda.hashCode());
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
		ItemVenda other = (ItemVenda) obj;
		if (venda == null) {
			if (other.venda != null)
				return false;
		} else if (!venda.equals(other.venda))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "itemVenda [venda=" + venda + ", getId()=" + getId() + ", getDataAdicionado()=" + getDataAdicionado()
				+ ", getQuantidade()=" + getQuantidade() + ", getProduto()=" + getProduto() + "]";
	}
}
