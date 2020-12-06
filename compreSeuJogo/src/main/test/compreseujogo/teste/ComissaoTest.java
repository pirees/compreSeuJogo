package compreseujogo.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import compreseujogo.model.bo.ComissaoBo;
import compreseujogo.model.dao.GenericDao;
import compreseujogo.model.entity.Comissao;
import compreseujogo.model.entity.Venda;

class ComissaoTest {

	@Test
	void testAdicionarVenda() throws Exception {

		Comissao comissao = new GenericDao<Comissao>().findById(Comissao.class, 1);
		Venda venda = new GenericDao<Venda>().findById(Venda.class, 18);

		Double teste = comissao.getDinheiro() + venda.getValor() * comissao.getPorcentagem();
		new ComissaoBo().adicionarVenda(venda);

		assertEquals(teste, comissao.getDinheiro());
	}

}
