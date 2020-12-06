package compreseujogo.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import compreseujogo.facade.Facade;
import compreseujogo.model.entity.Fornecedor;

public class FornecedorTest {

	@Test
	public void novoFornecedor() throws Exception {
		Fornecedor fornecedor = new Fornecedor(0, "Zé comeia jogos", "28.833.000/4201-47", "ze_dacomeia@gmail.com.br",
				"Jardinete Roberto", "81330-432", "PR", "(41) 95445-9459", "zedacomeia.com.br", "hahah", "hahaha", true);
		Facade facade = new Facade();
		facade.salvarFornecedor(fornecedor);
		int i = fornecedor.getId();
		boolean teste = false;
		if(i > 0 ) {
			teste = true;
		}
		assertEquals(true, teste);
	}
	
	
	@Test
	public void listar() throws Exception {
		Fornecedor fornecedor = new Fornecedor();
		Facade facade = new Facade();
		facade.listarFornecedorNome(fornecedor).forEach(System.out::println);;
	}

}
