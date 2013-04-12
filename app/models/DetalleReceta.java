package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

public class DetalleReceta extends Model{
	
	@Required
	public String descripcion;
	
	@Required
	public String cantidad;
	
	@Required
	public String tiempo;
	
	@ManyToOne
	public Receta receta;
	
	@ManyToOne
	public Tratamiento tratamiento;
	
	@ManyToOne
	public PlanTratamiento planTratamiento;
	
	public DetalleReceta(String descripcion, String cantidad, String tiempo, Receta receta, Tratamiento tratamiento, PlanTratamiento planTratamiento){
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.tiempo = tiempo;
		this.receta = receta;
		this.tratamiento = tratamiento;
		this.planTratamiento = planTratamiento;
	}

}
