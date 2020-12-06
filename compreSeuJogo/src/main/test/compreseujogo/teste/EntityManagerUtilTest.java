package compreseujogo.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;

import compreseujogo.util.EntityManagerUtil;

public class EntityManagerUtilTest {
	
	@Test
	public void testeConexao() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("folder");	
		EntityManager em = emf.createEntityManager();
	}
	
	
	@Test
	public void testeFabrica() {
		EntityManager em = EntityManagerUtil.getEntityManager();
	}
}
