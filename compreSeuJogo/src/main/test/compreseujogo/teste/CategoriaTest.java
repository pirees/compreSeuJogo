package compreseujogo.teste;



import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import compreseujogo.facade.Facade;
import compreseujogo.model.entity.Categoria;

public class CategoriaTest {
	
	@Test
	public void inserirCategoria() throws Exception {
		Categoria categoria = new Categoria(0, "Corrida", true);
		Facade facade = new Facade();
		facade.salvarCategoria(categoria);
		int i = categoria.getId();
		boolean teste = false;
		if(i > 0 ) {
			teste = true;
		}
		assertEquals(true, teste);
	}
	
	@Test
	public void listar() throws Exception {
		Categoria categoria = new Categoria(0, "Corrida", true);
		Facade facade = new Facade();
		facade.listaCategoria(categoria).forEach(System.out::println);;
	}

}
