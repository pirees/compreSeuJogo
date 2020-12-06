package compreseujogo.model.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_transporte")
public class Transporte extends Empresa {

	private static final long serialVersionUID = 1L;
	
	@Column(name ="dataEntrega",nullable = false)
	private int dataEntrega;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "transporte")
	List<Venda> venda;

	public Transporte(int id, String nome, String cnpj, String email, String bairro, String cidade, String endereco, String cep, String estado,
			String telefone, String url, Boolean ativo, int dataEntrega) {
		super(id, nome, cnpj, email, bairro, cidade, endereco, cep, estado, telefone, url, ativo);
		venda = new ArrayList<Venda>();
	}

	public Transporte() {
		super();
		venda = new ArrayList<Venda>();
	}

	public List<Venda> getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda.add(venda);
	}
	
	public int getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(int dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Nome=" + getNome();
	}

}