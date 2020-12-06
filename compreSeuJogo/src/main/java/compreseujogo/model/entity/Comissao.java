package compreseujogo.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_comissao")
public class Comissao implements EntityBase, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double dinheiro;
	private double porcentagem;
	private LocalDate dataPagamento;

	@OneToOne
	private Vendedor vendedor;

	public Comissao(int id, double dinheiro, double porcentagem, LocalDate dataPagamento, Vendedor vendedor) {
		super();
		this.id = id;
		this.dinheiro = dinheiro;
		this.porcentagem = porcentagem;
		this.dataPagamento = dataPagamento;
		this.vendedor = vendedor;
	}

	public Comissao() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getDinheiro() {
		return dinheiro;
	}

	public void setDinheiro(double dinheiro) {
		this.dinheiro = dinheiro;
	}

	public double getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(double porcentagem) {
		this.porcentagem = porcentagem;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataPagamento == null) ? 0 : dataPagamento.hashCode());
		long temp;
		temp = Double.doubleToLongBits(dinheiro);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		temp = Double.doubleToLongBits(porcentagem);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Comissao other = (Comissao) obj;
		if (dataPagamento == null) {
			if (other.dataPagamento != null)
				return false;
		} else if (!dataPagamento.equals(other.dataPagamento))
			return false;
		if (Double.doubleToLongBits(dinheiro) != Double.doubleToLongBits(other.dinheiro))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(porcentagem) != Double.doubleToLongBits(other.porcentagem))
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
		return "Comissao [id=" + id + ", dinheiro=" + dinheiro + ", porcentagem=" + porcentagem + ", dataPagamento="
				+ dataPagamento + ", vendedor=" + vendedor + "]";
	}

}
