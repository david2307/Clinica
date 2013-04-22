package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;


public class CitaProcedimiento extends Model{

	@ManyToOne
	public Procedimiento procedimiento;
	
	@ManyToOne
	public Cita cita;
	
	public CitaProcedimiento(Procedimiento procedimiento, Cita cita){
		this.procedimiento = procedimiento;
		this.cita = cita;
	}
}
