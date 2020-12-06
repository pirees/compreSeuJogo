package compreseujogo.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_listaDesejos")
public class ListaDesejos implements EntityBase, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne
	private Cliente cliente;

	@OneToMany(mappedBy = "listaDesejos", cascade = CascadeType.ALL)
	private List<ItemDesejo> item;

	public ListaDesejos(int id, Cliente cliente) {
		super();
		this.id = id;
		this.cliente = cliente;
		item = new ArrayList<ItemDesejo>();
	}

	public ListaDesejos() {
		super();
		item = new ArrayList<ItemDesejo>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemDesejo> getItem() {
		return item;
	}

	public void setItem(ItemDesejo item) {
		this.item.add(item);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListaDesejos other = (ListaDesejos) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ListaDesejos [id=" + id + ", cliente=" + cliente + "]";
	}

}