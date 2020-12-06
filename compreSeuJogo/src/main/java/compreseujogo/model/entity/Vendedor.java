package compreseujogo.model.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_vendedor")
public class Vendedor extends Pessoa {

	private static final long serialVersionUID = 1L;

	@OneToOne
	private Comissao comissao;

	@ManyToOne
	private Loja loja;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vendedor")
	List<Venda> venda;

	public Vendedor(int id, String nome, String sobrenome, String email, String senha, Date dataNascimento,
			String endereco, String telefone, String cep, String cpf, boolean ativo, String cidade, String bairro,
			Sexo sexo, Comissao comissao, Loja loja, Estado estado) {
		super(id, nome, sobrenome, email, senha, dataNascimento, endereco, telefone, cep, cpf, ativo, cidade, bairro,
				sexo, estado);
		this.comissao = comissao;
		this.loja = loja;
		this.venda = new ArrayList<Venda>();
	}

	public Vendedor() {
		super();
		this.comissao = new Comissao();
		this.loja = new Loja();
		this.venda = new ArrayList<Venda>();
	}

	public List<Venda> getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda.add(venda);
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public Comissao getComissao() {
		return comissao;
	}

	public void setComissao(Comissao comissao) {
		this.comissao = comissao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((comissao == null) ? 0 : comissao.hashCode());
		result = prime * result + ((loja == null) ? 0 : loja.hashCode());
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
		Vendedor other = (Vendedor) obj;
		if (comissao == null) {
			if (other.comissao != null)
				return false;
		} else if (!comissao.equals(other.comissao))
			return false;
		if (loja == null) {
			if (other.loja != null)
				return false;
		} else if (!loja.equals(other.loja))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Nome=" + getNome();
	}

}
