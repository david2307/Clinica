package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

public class TipoOrgano extends Model {

	@Required
	public String descripcion;
	
	@Required
	public int tipo;
	
	public TipoOrgano(String descripcion, int tipo){
		this.descripcion = descripcion;
		this.tipo = tipo;
		
	}
}
