package compreseujogo.model.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_cliente")
public class Cliente extends Pessoa {

	private static final long serialVersionUID = 1L;

	@OneToOne
	private Carrinho carrinho;

	@OneToOne
	private ListaDesejos listaDesejos;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
	private List<Avaliacao> avaliacao;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
	private List<Venda> venda;

	public Cliente(int id, String nome, String sobrenome, String email, String senha, Date dataNascimento,
			String endereco, String telefone, String cep, String cpf, boolean ativo, String cidade, String bairro,
			Sexo sexo, Carrinho carrinho, ListaDesejos listaDesejos, Estado estado) {
		super(id, nome, sobrenome, email, senha, dataNascimento, endereco, telefone, cep, cpf, ativo, cidade, bairro,
				sexo, estado);
		this.carrinho = carrinho;
		this.listaDesejos = listaDesejos;
		this.avaliacao = new ArrayList<Avaliacao>();
		this.venda = new ArrayList<Venda>();
	}

	public Cliente() {
		super();
		this.carrinho = new Carrinho();
		this.listaDesejos = new ListaDesejos();
		this.avaliacao = new ArrayList<Avaliacao>();
		this.venda = new ArrayList<Venda>();
	}

	public List<Avaliacao> getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao.add(avaliacao);
	}

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	public List<Venda> getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda.add(venda);
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
		result = prime * result + ((carrinho == null) ? 0 : carrinho.hashCode());
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
		Cliente other = (Cliente) obj;
		if (carrinho == null) {
			if (other.carrinho != null)
				return false;
		} else if (!carrinho.equals(other.carrinho))
			return false;
		if (listaDesejos == null) {
			if (other.listaDesejos != null)
				return false;
		} else if (!listaDesejos.equals(other.listaDesejos))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getNome();
	}

}