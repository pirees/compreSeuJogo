package compreseujogo.teste;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import compreseujogo.facade.Facade;
import compreseujogo.model.entity.Administrador;
import compreseujogo.model.entity.Estado;
import compreseujogo.model.entity.Sexo;

public class AdministradorTest {

	@Test
	public void novoAdministrador() throws Exception {
		Facade facade = new Facade();
		Administrador administrador = new Administrador(0, "Jennifer", "Luciana Isabela Duarte",
				"jjenniferlucianaisabeladuarte@vnews.com.br", "1234", null, "Praça General Osório 45",
				"(41) 99108-8676", "80020-930", "296.416.029-50", true, "Curitiba", "Centro", Sexo.Masculino,
				null, Estado.PR);
		facade.salvarAdministrador(administrador);
		boolean teste = false;
		if (administrador.getId() > 0) {
			teste = true;
		}
		assertEquals(true, teste);
	}
	/*
	 * @Test public void login() throws Exception { Facade facade = new Facade();
	 * Administrador administrador = new Administrador();
	 * administrador.setEmail("teste@gmail.com"); administrador.setSenha("1234");
	 * System.out.println(facade.loginAdminstrador(administrador));
	 * System.out.println(administrador); boolean teste = false; if
	 * (administrador.getId() > 0) { teste = true; } // assertEquals(true, teste); }
	 */
}
