package compreseujogo.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import compreseujogo.model.bo.LojaBo;
import compreseujogo.model.entity.Loja;

public class LojaTest {

	@Test
	public void save() throws Exception {
		Loja loja = new Loja();
		loja.setAtivo(true);
		loja.setCep("84562-123");
		loja.setBairro("Portão");
		loja.setCidade("Curitiba");
		loja.setNome("Compres seu jogo");
		loja.setEstado("PR");
		loja.setEmail("compreseujogo@compreseujogo.com");
		loja.setTelefone("(41) 3551-9515");
		loja.setCnpj("57.157.188/0001-01");
		loja.setUrl("compreseujogo.com");
		loja.setEndereco("Rua do jogo");

		new LojaBo().saveOrUpdate(loja);
		
		boolean teste= false;
		if (loja.getId() > 0) {
			teste = true;
		}
		assertEquals(true, teste);

	}
}
