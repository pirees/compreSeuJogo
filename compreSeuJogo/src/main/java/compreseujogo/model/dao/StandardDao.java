package compreseujogo.model.dao;

import java.util.List;

import compreseujogo.model.entity.EntityBase;

public interface StandardDao  <T extends EntityBase>{
	
	public List <T> list (String parameter, T obj);
}
