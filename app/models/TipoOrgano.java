package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

public class TipoOrgano extends Model {

	@Required
	public String descripcion;
	
	public TipoOrgano(String descripcion){
		this.descripcion = descripcion;
	}
}
