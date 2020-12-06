package compreseujogo.teste;

import java.util.List;

import org.junit.jupiter.api.Test;

import compreseujogo.facade.Facade;
import compreseujogo.model.entity.Transporte;

public class TransporteTest {

	@Test
	public void lista() throws Exception{
		List<Transporte> t = new Facade().listaTransporte(null);
		t.forEach(System.out::println);
	}

}
