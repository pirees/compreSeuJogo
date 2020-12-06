package compreseujogo.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_produto")
@NamedQuery(name = "Produto.nome", query = "SELECT p FROM Produto p WHERE p.nome = :nome")
public class Produto implements EntityBase, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String descricao;
	private String imagem;
	private double valor;
	@Column(name = "nota", nullable = true)
	private int nota;
	@Column(name = "quantidadeEstoque")
	private int quantEstoque;
	private int EAN;
	private String sku;
	private boolean ativo;
	@Column(name = "quantidadeConsulta")
	private int quantConsulta;
	private LocalDate dataCadastro;
	private Date dataLancamento;

	@ManyToOne
	private Categoria categoria;

	@ManyToOne
	private Plataforma plataforma;

	@ManyToOne
	private Marca marca;

	@ManyToOne
	private Fornecedor fornecedor;

	@ManyToOne
	private Loja loja;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "produto")
	private List<Avaliacao> avaliacao;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "produto")
	private List<Item> item;

	public Produto(int id, String nome, String descricao, double valor, int quantEstoque, int eAN, String sku,
			boolean ativo, int quantConsulta, Date dataLancamento, Categoria categoria, Plataforma plataforma,
			Marca marca, Fornecedor fornecedor, Loja loja) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.quantEstoque = quantEstoque;
		this.EAN = eAN;
		this.sku = sku;
		this.ativo = ativo;
		this.quantConsulta = quantConsulta;
		this.dataLancamento = dataLancamento;
		this.categoria = categoria;
		this.plataforma = plataforma;
		this.marca = marca;
		this.fornecedor = fornecedor;
		this.loja = loja;
		avaliacao = new ArrayList<Avaliacao>();
		item = new ArrayList<Item>();
	}

	public Produto() {
		super();
		this.categoria = new Categoria();
		this.plataforma = new Plataforma();
		this.marca = new Marca();
		this.fornecedor = new Fornecedor();
		this.loja = new Loja();
		avaliacao = new ArrayList<Avaliacao>();
		item = new ArrayList<Item>();
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getQuantEstoque() {
		return quantEstoque;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public void setQuantEstoque(int quantEstoque) {
		this.quantEstoque = quantEstoque;
	}

	public int getQuantConsulta() {
		return quantConsulta;
	}

	public void setQuantConsulta(int quantConsulta) {
		this.quantConsulta = quantConsulta;
	}

	public int getEAN() {
		return EAN;
	}

	public void setEAN(int eAN) {
		EAN = eAN;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Plataforma getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Avaliacao> getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao.add(avaliacao);
	}

	public List<Item> getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item.add(item);
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + EAN;
		result = prime * result + (ativo ? 1231 : 1237);
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((dataLancamento == null) ? 0 : dataLancamento.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((fornecedor == null) ? 0 : fornecedor.hashCode());
		result = prime * result + id;
		result = prime * result + ((loja == null) ? 0 : loja.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((plataforma == null) ? 0 : plataforma.hashCode());
		result = prime * result + quantConsulta;
		result = prime * result + quantEstoque;
		result = prime * result + ((sku == null) ? 0 : sku.hashCode());
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
		Produto other = (Produto) obj;
		if (EAN != other.EAN)
			return false;
		if (ativo != other.ativo)
			return false;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (dataLancamento == null) {
			if (other.dataLancamento != null)
				return false;
		} else if (!dataLancamento.equals(other.dataLancamento))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (fornecedor == null) {
			if (other.fornecedor != null)
				return false;
		} else if (!fornecedor.equals(other.fornecedor))
			return false;
		if (id != other.id)
			return false;
		if (loja == null) {
			if (other.loja != null)
				return false;
		} else if (!loja.equals(other.loja))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (plataforma == null) {
			if (other.plataforma != null)
				return false;
		} else if (!plataforma.equals(other.plataforma))
			return false;
		if (quantConsulta != other.quantConsulta)
			return false;
		if (quantEstoque != other.quantEstoque)
			return false;
		if (sku == null) {
			if (other.sku != null)
				return false;
		} else if (!sku.equals(other.sku))
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}

}