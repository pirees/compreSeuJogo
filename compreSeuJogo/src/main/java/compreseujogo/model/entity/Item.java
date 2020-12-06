package compreseujogo.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item implements EntityBase, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "inc")
	@GenericGenerator(name = "inc", strategy = "increment")
	private int id;
	private LocalDate dataAdicionado;
	private double valor;
	private int quantidade;

	@ManyToOne
	private Produto produto;

	public Item(int id, LocalDate dataAdicionado, double valor, int quantidade, Produto produto) {
		super();
		this.id = id;
		this.dataAdicionado = dataAdicionado;
		this.valor = valor;
		this.quantidade = quantidade;
		this.produto = produto;
	}

	public Item() {
		super();
		this.produto = new Produto();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDataAdicionado() {
		return dataAdicionado;
	}

	public void setDataAdicionado(LocalDate dataAdicionado) {
		this.dataAdicionado = dataAdicionado;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataAdicionado == null) ? 0 : dataAdicionado.hashCode());
		result = prime * result + id;
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + quantidade;
		long temp;
		temp = Double.doubleToLongBits(valor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Item other = (Item) obj;
		if (dataAdicionado == null) {
			if (other.dataAdicionado != null)
				return false;
		} else if (!dataAdicionado.equals(other.dataAdicionado))
			return false;
		if (id != other.id)
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (quantidade != other.quantidade)
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		return true;
	}

}