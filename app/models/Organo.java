package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

@Entity
public class Organo extends Model{
	
	@Required
	public String nombre;
	
	@ManyToOne
	public TipoOrgano tipoOrgano;
	
	 public Organo(String nombre, TipoOrgano tipoOrgano){
		this.nombre = nombre;
		this.tipoOrgano = tipoOrgano;
	}
}
