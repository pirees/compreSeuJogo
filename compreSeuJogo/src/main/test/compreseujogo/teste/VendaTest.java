package compreseujogo.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import compreseujogo.facade.Facade;
import compreseujogo.model.dao.GenericDao;
import compreseujogo.model.entity.Cliente;
import compreseujogo.model.entity.Transporte;
import compreseujogo.model.entity.Venda;

public class VendaTest {

	@Test
	public void novaVendaOnline() {

		Venda venda = new Venda();
		venda.setCliente(new GenericDao<Cliente>().findById(Cliente.class, 1));
		venda.setTransporte(new GenericDao<Transporte>().findById(Transporte.class, 3));
		venda.setDataEntrega(LocalDate.now());

		try {
			assertEquals("vendido", new Facade().novaVenda("online",venda));
			venda.getItem().forEach(item -> System.out.println(item.getProduto()));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
