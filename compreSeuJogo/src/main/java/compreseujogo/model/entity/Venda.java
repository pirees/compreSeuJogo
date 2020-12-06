package compreseujogo.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_venda")
public class Venda implements EntityBase, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate dataCadastro;
	private LocalDate dataEntrega;
	private double valor;
	private boolean entrega;

	@ManyToOne
	private Vendedor vendedor;

	@ManyToOne
	private Cliente cliente;

	@ManyToOne
	private Transporte transporte;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "venda")
	private List<ItemVenda> item;

	public Venda(int id, LocalDate dataEntrega, double valor, boolean entrega, Vendedor vendedor, Cliente cliente,
			Transporte transporte) {
		super();
		this.id = id;
		this.dataEntrega = dataEntrega;
		this.valor = valor;
		this.entrega = entrega;
		this.vendedor = vendedor;
		this.cliente = cliente;
		this.transporte = transporte;
		item = new ArrayList<ItemVenda>();
	}

	public Venda() {
		super();
		this.vendedor = new Vendedor();
		this.cliente = new Cliente();
		this.transporte = new Transporte();
		item = new ArrayList<ItemVenda>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate data) {
		this.dataCadastro = data;
	}

	public boolean isEntrega() {
		return entrega;
	}

	public void setEntrega(boolean entrega) {
		this.entrega = entrega;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Transporte getTransporte() {
		return transporte;
	}

	public void setTransporte(Transporte transporte) {
		this.transporte = transporte;
	}

	public List<ItemVenda> getItem() {
		return item;
	}

	public void setItem(List<ItemVenda> list) {
		this.item = list;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + (entrega ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((transporte == null) ? 0 : transporte.hashCode());
		result = prime * result + ((vendedor == null) ? 0 : vendedor.hashCode());
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
		Venda other = (Venda) obj;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (entrega != other.entrega)
			return false;
		if (id != other.id)
			return false;
		if (transporte == null) {
			if (other.transporte != null)
				return false;
		} else if (!transporte.equals(other.transporte))
			return false;
		if (vendedor == null) {
			if (other.vendedor != null)
				return false;
		} else if (!vendedor.equals(other.vendedor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Venda [id=" + id + ", data=" + dataCadastro + ", entrega=" + entrega + ", vendedor=" + vendedor
				+ ", transporte=" + transporte + "]";
	}

}