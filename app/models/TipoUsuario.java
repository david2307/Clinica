package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

@Entity
public class TipoUsuario extends Model {
	@Required 
	public String descripcion;
	
	public TipoUsuario(String descripcion){
		this.descripcion = descripcion;
	}
	
	
}
