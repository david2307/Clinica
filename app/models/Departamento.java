package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

@Entity
public class Departamento extends  Model {
	
	@Required
	public String nombre;
	
	public Departamento(String nombre){
		this.nombre = nombre;
	}
		
}
