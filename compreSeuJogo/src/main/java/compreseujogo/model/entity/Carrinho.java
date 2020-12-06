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
@Table(name = "tb_carrinho")
public class Carrinho implements EntityBase, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Double valor;

	@OneToOne
	private Cliente cliente;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "carrinho")
	private List<ItemCarrinho> item;

	public Carrinho(int id, Double valor, Cliente cliente) {
		super();
		this.id = id;
		this.valor = valor;
		this.cliente = cliente;
		item = new ArrayList<ItemCarrinho>();
	}

	public Carrinho() {
		super();
		item = new ArrayList<ItemCarrinho>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemCarrinho> getItem() {
		return item;
	}

	public void setItem(ArrayList<ItemCarrinho> arrayList) {
		this.item = arrayList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + id;
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		Carrinho other = (Carrinho) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (id != other.id)
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Carrinho [id=" + id + ", valor=" + valor + ", cliente=" + cliente.getEmail() + "]";
	}

}