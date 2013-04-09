package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

public class Municipio {

	@Required
	public String nombre;
	
	@OneToMany
	public Departamento departamento;
	
	public Municipio(String nombre, Departamento departamento){
		this.nombre = nombre;
		this.departamento = departamento;
	}
}
