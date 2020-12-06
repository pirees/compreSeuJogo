package compreseujogo.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_marca")
public class Marca extends Tipo {

	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "marca")
	private List<Produto> produto;

	public Marca(int id, String nome, boolean ativo) {
		super(id, nome, ativo);
		produto = new ArrayList<Produto>();
	}

	public Marca() {
		super();
		produto = new ArrayList<Produto>();
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto.add(produto);
	}

	@Override
	public String toString() {
		return "Marca id: " + getId() + ", nome: " + getNome() + ", ativo: " + isAtivo();
	}

}
