package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

public class ClasificacionTratamiento extends Model{
	
	public String observaciones;
	
	@ManyToOne
	public EstadoTratamiento estadoTratamiento;
	
	@ManyToOne
	public Tratamiento tratamiento;
	
	public ClasificacionTratamiento(String observaciones, EstadoTratamiento estadoTratamiento, Tratamiento tratamiento){
		this.observaciones = observaciones;
		this.estadoTratamiento = estadoTratamiento;
		this.tratamiento = tratamiento;
	}
	

}
