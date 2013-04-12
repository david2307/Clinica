package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

public class Municipio extends Model {

	@Required
	public String nombre;
	
	@ManyToOne
	public Departamento departamento;
	
	public Municipio(String nombre, Departamento departamento){
		this.nombre = nombre;
		this.departamento = departamento;
	}
}
