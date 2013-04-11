package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

public class DiagnosticoGeneral extends Model {
	
	@Required
	public String descripcion;
	
	@Required
	public int tipo;
	
	@OneToMany
	public Cita cita;
	
	public DiagnosticoGeneral(String descripcion, int tipo, Cita cita){
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.cita = cita;
	}
}
