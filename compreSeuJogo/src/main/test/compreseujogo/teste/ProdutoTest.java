package compreseujogo.teste;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import compreseujogo.facade.Facade;
import compreseujogo.model.entity.Produto;

public class ProdutoTest {

	/*
	 * @Test public void novoProduto() throws Exception { GenericDao<Plataforma>
	 * gDao = new GenericDao<Plataforma>(); Facade facade = new Facade(); Plataforma
	 * plataforma = gDao.findById(Plataforma.class, 3); Produto produto = new
	 * Produto(0, "Rico", "Deve ser apagado", 150.0, 125, 1502, "", true, 1, null,
	 * null, plataforma, null, null, null); facade.salvarProduto(produto); boolean
	 * teste = false; if(produto.getId()>0) { teste = true; } assertEquals(true,
	 * teste); }
	 */
	/*
	 * @Test public void lista() throws Exception { ProdutoBo bo = new ProdutoBo();
	 * Produto produto = new Produto(); Plataforma plataforma = new Plataforma();
	 * plataforma.setId(1); produto.setPlataforma(plataforma); bo.list("Plataforma",
	 * produto).forEach(System.out::println); int i = bo.list("Plataforma",
	 * produto).size(); System.out.println("Tamanho: "+i); }
	 */
	/*
	 * @Test public void lista() throws Exception { Facade facade = new Facade();
	 * ArrayList<Produto> prd = new ArrayList<Produto>(); prd = (ArrayList<Produto>)
	 * facade.listaProduto("pesquisa", "Fall", null);
	 * 
	 * boolean teste = false; if (prd.size() > 0) { teste = true; }
	 * prd.forEach(System.out::println); assertEquals(true, teste); }
	 */
	@Test
	public void encontrar() {
		Facade facade = new Facade();
		Produto produto = facade.encontrarProduto(2);
		System.out.println(produto);
	}
}
