package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

public class TipoSintoma extends Model {
	
	@Required 
	public String descripcion;
	
	public TipoSintoma(String descripcion){
		this.descripcion = descripcion;
	}

}
