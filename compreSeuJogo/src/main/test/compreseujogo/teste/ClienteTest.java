package compreseujogo.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import compreseujogo.facade.Facade;
import compreseujogo.model.entity.Cliente;
import compreseujogo.model.entity.Estado;
import compreseujogo.model.entity.Sexo;

public class ClienteTest {
	@Test
	public void novoCliente() throws Exception {
		Facade facade = new Facade();
		Cliente cliente = new Cliente(0, "Jennifer", "Luciana Isabela Duarte", "gea@vnews.com.br", "1234", null,
				"Praça General Osório 45", "(41) 99108-8676", "80020-930", "094.920.590-70", true, "Curitiba", "Centro",
				Sexo.Feminino, null, null, Estado.PR);
		facade.salvarCliente(cliente);
		boolean teste = false;
		if (cliente.getId() > 0) {
			teste = true;
		}
		assertEquals(true, teste);
	}

}
