package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

public class Organo {
	
	@Required
	public String nombre;
	
	public int tipo;
	
	@OneToMany
	public TipoOrgano tipoOrgano;
	
	 public Organo(String nombre, int tipo, TipoOrgano tipoOrgano){
		this.nombre = nombre;
		this.tipo = tipo;
		this.tipoOrgano = tipoOrgano;
	}
}
