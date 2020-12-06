package compreseujogo.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import compreseujogo.model.entity.Administrador;

@SessionScoped
@ManagedBean(name = "regraBean")
public class RegraController {

	public boolean menuAdm(Administrador administrador) {
		if (administrador.equals(null)) {
			return false;
		} else {
			return true;
		}
	}
}
