package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

@Entity
public class Receta extends Model{
	
	@Required
	public String descripcion;
	
	@Required
	public Date fecha;
	
	@ManyToOne
	public Cita cita;
	
	public Receta(String descripcion, Date fecha, Cita cita){
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.cita = cita;
	}
}
