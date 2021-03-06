package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

@Entity
public class PlanTratamiento extends Model{
	
	@Required
	public double extra;
	
	public String Observaciones;
	
	@Required
	public int estado;
	
	@ManyToOne
	public Procedimiento procedimiento;
	
	@ManyToOne
	public DiagnosticoOrgano diagnosticoOrgano;
	
	public PlanTratamiento(double extra, String observaciones, int estado, Procedimiento procedimiento, DiagnosticoOrgano diagnosticoOrgano){
		this.extra = extra;
		this.estado = estado;
		this.Observaciones = observaciones;
		this.procedimiento = procedimiento;
		this.diagnosticoOrgano = diagnosticoOrgano;
	}
}
