package compreseujogo.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("folder");	

	// getConnection
	public static EntityManager getEntityManager() {		
		return emf.createEntityManager();
	}
}