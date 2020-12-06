package compreseujogo.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import compreseujogo.model.entity.EntityBase;
import compreseujogo.model.entity.Fornecedor;
import compreseujogo.util.EntityManagerUtil;

public class FornecedorDao {
	
	private static EntityManager em = EntityManagerUtil.getEntityManager();

	public List <Fornecedor> list(String parameter, EntityBase obj) {
		Fornecedor fornecedor = (Fornecedor) obj;
		Query q= null;
		
		
		return q.getResultList();
	}
	
	
}
