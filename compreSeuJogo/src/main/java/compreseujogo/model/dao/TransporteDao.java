package compreseujogo.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import compreseujogo.model.entity.Transporte;
import compreseujogo.util.EntityManagerUtil;

public class TransporteDao {

	private static EntityManager em = EntityManagerUtil.getEntityManager();

	public List<Transporte> list(String parameter, Transporte transporte) {

		return null;
	}

}
