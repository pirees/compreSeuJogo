package compreseujogo.model.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_ItemDesejo")
public class ItemDesejo extends Item {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private ListaDesejos listaDesejos;

	public ItemDesejo(int id, LocalDate dataAdicionado, double valor, int quantidade, Produto produto,
			ListaDesejos listaDesejos) {
		super(id, dataAdicionado, valor, quantidade, produto);
		this.listaDesejos = listaDesejos;
	}


	public ItemDesejo() {
		super();
		this.listaDesejos = new ListaDesejos(); 
	}

	public ListaDesejos getListaDesejos() {
		return listaDesejos;
	}

	public void setListaDesejos(ListaDesejos listaDesejos) {
		this.listaDesejos = listaDesejos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((listaDesejos == null) ? 0 : listaDesejos.hashCode());
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
		ItemDesejo other = (ItemDesejo) obj;
		if (listaDesejos == null) {
			if (other.listaDesejos != null)
				return false;
		} else if (!listaDesejos.equals(other.listaDesejos))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemDesejo [listaDesejos=" + listaDesejos + ", getId()=" + getId() + ", getDataAdicionado()="
				+ getDataAdicionado() + ", getQuantidade()=" + getQuantidade() + "]";
	}

}
