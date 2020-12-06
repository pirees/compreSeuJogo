package compreseujogo.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import compreseujogo.facade.Facade;
import compreseujogo.model.entity.Marca;

public class MarcaTest {
	
	@Test
	public void inserirMarca() throws Exception {
		Marca marca = new Marca(0, "Corrida", true);
		Facade facade = new Facade();
		facade.salvarMarca(marca);
		int i = marca.getId();
		boolean teste = false;
		if(i > 0 ) {
			teste = true;
		}
		assertEquals(true, teste);
	}
	
	@Test
	public void listar() throws Exception {
		Marca marca = new Marca(0, "Corrida", true);
		Facade facade = new Facade();
		facade.listaMarca(marca).forEach(System.out::println);;
	}

}
