package compreseujogo.model.bo;

import java.util.List;

import compreseujogo.model.entity.EntityBase;

public interface StandardBo <T extends EntityBase> {

	public String saveOrUpdate(T obj) throws Exception;

	public String remove(T obj) throws Exception;

	public List<T> list(String parameter, T obj) throws Exception;
	
}
