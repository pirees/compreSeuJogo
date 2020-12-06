package compreseujogo.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_loja")
public class Loja extends Empresa {

	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "loja")
	private List<Administrador> administrador;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "loja")
	private List<Vendedor> vendedor;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "loja")
	private List<Produto> produto;

	public Loja(int id, String nome, String cnpj, String email, String bairro, String cidade, String endereco,
			String cep, String estado, String telefone, String url, Boolean ativo) {
		super(id, nome, cnpj, email, bairro, cidade, endereco, cep, estado, telefone, url, ativo);
		administrador = new ArrayList<Administrador>();
		vendedor = new ArrayList<Vendedor>();
		produto = new ArrayList<Produto>();
	}

	public Loja() {
		super();
		administrador = new ArrayList<Administrador>();
		vendedor = new ArrayList<Vendedor>();
		produto = new ArrayList<Produto>();
	}

	public List<Administrador> getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador.add(administrador);
	}

	public List<Vendedor> getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor.add(vendedor);
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto.add(produto);
	}

	@Override
	public String toString() {
		return "Loja [getId()=" + getId() + ", getNome()=" + getNome() + ", getCnpj()=" + getCnpj() + ", getEmail()="
				+ getEmail() + ", getEndereco()=" + getEndereco() + ", getCep()=" + getCep() + ", getEstado()="
				+ getEstado() + ", getTelefone()=" + getTelefone() + ", getUrl()=" + getUrl() + "]";
	}

}
