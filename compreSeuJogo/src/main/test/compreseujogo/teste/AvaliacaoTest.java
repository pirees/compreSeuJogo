package compreseujogo.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import compreseujogo.facade.Facade;
import compreseujogo.model.bo.AvaliacaoBo;
import compreseujogo.model.dao.GenericDao;
import compreseujogo.model.entity.Avaliacao;
import compreseujogo.model.entity.Cliente;
import compreseujogo.model.entity.Produto;

public class AvaliacaoTest {

	@Test
	public void list() throws Exception {
		Facade facade = new Facade();
		Avaliacao avaliacao = new Avaliacao();
		Produto produto = new Produto();
		produto.setId(2);
		avaliacao.setProduto(produto);
		facade.listaAvaliacao("Produto", avaliacao).forEach(System.out::println);
		;
	}

	@Test
	public void calcularNota() throws Exception {
		Produto produto = new GenericDao<Produto>().findById(Produto.class, 4);
		Cliente cliente = new GenericDao<Cliente>().findById(Cliente.class, 1);
		List<Avaliacao> lista = new ArrayList<Avaliacao>();
		Avaliacao a = null;
		for (int i = 0; i < 3; i++) {
			a = new Avaliacao(0, 5 - i, "Titulo", "Comentario", cliente, produto);
			new AvaliacaoBo().saveOrUpdate(a);
			lista.add(a);
		}
		double total = lista.stream().mapToInt(Avaliacao::getPontos).average().getAsDouble();
		int media = ((total > 4) ? 5 : (total > 3) ? 4 : (total > 2) ? 3 : (total > 1) ? 2 : 1);
		assertEquals(media, new AvaliacaoBo().calcularNota(a));
		System.out.println(media);
	}
}
