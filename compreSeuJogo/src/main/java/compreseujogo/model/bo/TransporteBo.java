package compreseujogo.model.bo;

import java.util.List;

import compreseujogo.model.dao.GenericDao;
import compreseujogo.model.dao.TransporteDao;
import compreseujogo.model.entity.Transporte;


public class TransporteBo extends EmpresaBo<Transporte>{

	public List<Transporte> list(String parameter, Transporte transporte) throws Exception {
		try {
			if (parameter.equals("")) {
				GenericDao<Transporte> pDao = new GenericDao<Transporte>();
				return pDao.list(Transporte.class);
			} else {
				TransporteDao transporteDao = new TransporteDao();
				return transporteDao.list(parameter, transporte);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());

		}
	}
}
