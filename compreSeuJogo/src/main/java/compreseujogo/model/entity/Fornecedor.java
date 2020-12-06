package compreseujogo.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_fornecedor")
public class Fornecedor extends Empresa {

	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fornecedor")
	private List<Produto> produto;

	public Fornecedor(int id, String nome, String cnpj, String email, String bairro, String cidade,String endereco, String cep, String estado,
			String telefone, String url, Boolean ativo) {
		super(id, nome, cnpj, email, bairro, cidade, endereco, cep, estado, telefone, url, ativo);
		produto = new ArrayList<Produto>();
	}

	public Fornecedor() {
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
		return "Fornecedor getId()=" + getId() + ", getNome()=" + getNome() + ", getCnpj()=" + getCnpj()
				+ ", getEmail()=" + getEmail() + ", getEndereco()=" + getEndereco() + ", getCep()=" + getCep()
				+ ", getEstado()=" + getEstado() + ", getTelefone()=" + getTelefone() + ", getUrl()=" + getUrl()
				+ ", getAtivo()=" + getAtivo() + "]";
	}
}
