package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

public class EstadoTratamiento extends Model {
	
	@Required
	public String descripcion;
	
	public EstadoTratamiento(String descripcion){
		this.descripcion = descripcion;
	}

}
