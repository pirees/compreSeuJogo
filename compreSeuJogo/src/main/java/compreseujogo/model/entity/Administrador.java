package compreseujogo.model.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_administrador")
public class Administrador extends Pessoa {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Loja loja;

	public Administrador(int id, String nome, String sobrenome, String email, String senha, Date dataNascimento,
			String endereco, String telefone, String cep, String cpf, boolean ativo, String cidade,
			String bairro, Sexo sexo, Loja loja, Estado estado) {
		super(id, nome, sobrenome, email, senha, dataNascimento, endereco, telefone, cep, cpf, ativo, cidade,
				bairro, sexo, estado);
		this.loja = loja;
	}

	public Administrador() {
		super();
		this.loja = new Loja();
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
		int result = super.hashCode();
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
		Administrador other = (Administrador) obj;
		if (loja == null) {
			if (other.loja != null)
				return false;
		} else if (!loja.equals(other.loja))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Administrador [loja=" + loja + ", getLoja()=" + getLoja() + ", hashCode()=" + hashCode() + ", getId()="
				+ getId() + ", getNome()=" + getNome() + ", getSobrenome()=" + getSobrenome() + ", getEmail()="
				+ getEmail() + ", getSenha()=" + getSenha() + ", getDataNascimento()=" + getDataNascimento()
				+ ", getDataCadastro()=" + getDataCadastro() + ", getEndereco()=" + getEndereco() + ", getTelefone()="
				+ getTelefone() + ", getCep()=" + getCep() + ", getCpf()=" + getCpf() + ", isAtivo()=" + isAtivo()
				+ ", getCidade()=" + getCidade() + ", getBairro()=" + getBairro() + ", getSexo()=" + getSexo()
				+ ", getEstado()=" + getEstado() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ "]";
	}

}
